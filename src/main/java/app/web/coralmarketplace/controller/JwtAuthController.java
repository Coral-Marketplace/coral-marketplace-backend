package app.web.coralmarketplace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.web.coralmarketplace.config.JwtTokenUtil;
import app.web.coralmarketplace.model.JwtRequest;
import app.web.coralmarketplace.model.JwtResponse;
import app.web.coralmarketplace.service.JwtUserDetailsService;
import app.web.coralmarketplace.service.UserService;

@RestController
@CrossOrigin
public class JwtAuthController {

    private JwtTokenUtil jwtTokenUtil;

    private JwtUserDetailsService userDetailsService;

    private UserService userService;

    public JwtAuthController(JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService,
            UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        userService.authenticate(authenticationRequest.getPublicAddress(), authenticationRequest.getSignature(),
                authenticationRequest.getEvmAddress());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getPublicAddress());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/check-auth-token")
    public void checkAuthToken() {}

}
