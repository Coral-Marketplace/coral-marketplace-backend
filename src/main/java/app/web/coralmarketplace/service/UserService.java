package app.web.coralmarketplace.service;

import org.springframework.web.multipart.MultipartFile;

import app.web.coralmarketplace.model.User;

public interface UserService {

    User getByPublicAddress(String publicAddress);

    User getByName(String name);

    User update(String publicAddress, String name, MultipartFile avatar, MultipartFile coverImage) throws Exception;

    void delete(String publicAddress);

    Integer getNonce(String publicAddress);

    void authenticate(String publicAddress, String signature, String evmAddress) throws Exception;
}
