package app.web.coralmarketplace.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import app.web.coralmarketplace.model.Collection;

public interface CollectionService {

    Collection getById(Long id) throws Exception;

    Collection getByName(String name);

    List<Collection> getAll();

    List<Collection> getByUserPublicAddress(String userPublicAddress);

    Collection save(String name, String description, MultipartFile logo, MultipartFile banner) throws Exception;

    Collection update(Long id, String name, String description, MultipartFile logo, MultipartFile banner)
            throws Exception;

    void delete(Long id);

}
