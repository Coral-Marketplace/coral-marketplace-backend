package app.web.coralmarketplace.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.web.coralmarketplace.dto.UserDto;
import app.web.coralmarketplace.mapper.UserMapper;
import app.web.coralmarketplace.service.UserService;
import app.web.coralmarketplace.validation.UserValidations;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;
    private UserValidations userValidations;

    public UserController(UserService userService, UserMapper userMapper, UserValidations userValidations) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userValidations = userValidations;
    }

    @GetMapping("/nonce/{publicAddress}")
    public Integer getUserNonce(@PathVariable String publicAddress) {
        return userService.getNonce(publicAddress);
    }

    @GetMapping("/{publicAddress}")
    public UserDto getUser(@PathVariable String publicAddress) {
        return userMapper.mapEntityToDto(userService.getByPublicAddress(publicAddress));
    }

    @PostMapping("/{publicAddress}")
    public UserDto updateUser(@PathVariable String publicAddress, @RequestParam String name,
            @RequestParam(required = false) MultipartFile avatar,
            @RequestParam(required = false) MultipartFile coverImage) throws Exception {
        this.userValidations.userUpdate(publicAddress, name);
        return userMapper.mapEntityToDto(userService.update(publicAddress, name, avatar, coverImage));
    }

    @DeleteMapping("/{publicAddress}")
    public void deleteUser(@PathVariable String publicAddress) {
        userService.delete(publicAddress);
    }
}
