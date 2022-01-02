package app.web.coralmarketplace.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import app.web.coralmarketplace.model.Collection;
import app.web.coralmarketplace.model.User;
import app.web.coralmarketplace.repository.CollectionRepo;

@Service
public class CollectionServiceImp implements CollectionService {

    @Value("${aws.folder.collection.logo}")
    private String logoFolder;

    @Value("${aws.folder.collection.banner}")
    private String bannerFolder;

    private final AwsService awsService;

    private final CollectionRepo collectionRepo;

    private final UserService userService;

    @Autowired
    public CollectionServiceImp(CollectionRepo collectionRepo, AwsService awsService, UserService userService) {
        this.collectionRepo = collectionRepo;
        this.awsService = awsService;
        this.userService = userService;
    }

    @Override
    public Collection getById(Long id) throws Exception {
        Collection collection = this.collectionRepo.findById(id).orElse(null);

        if (collection == null) {
            throw new Exception("Collection not found");
        }

        return collection;
    }

    @Override
    public Collection getByName(String name) {
        return collectionRepo.findByName(name);
    }

    @Override
    public List<Collection> getAll() {
        List<Collection> collections = IterableUtils.toList(collectionRepo.findAll());
        Collections.reverse(collections);
        return collections;
    }

    @Override
    public List<Collection> getByUserPublicAddress(String userPublicAddress) {
        return IterableUtils.toList(collectionRepo.findByUserPublicAddress(userPublicAddress));
    }

    @Override
    public Collection save(String name, String description, MultipartFile logo, MultipartFile banner) throws Exception {
        Collection collection = new Collection();
        collection.setName(name);
        if (description != null && !description.equals("undefined")) {
            collection.setDescription(description);
        }
        collection = collectionRepo.save(collection);
        User user = userService.getByPublicAddress(SecurityContextHolder.getContext().getAuthentication().getName());
        collection.setUser(user);

        String logoUrl = awsService.saveFile(logoFolder, collection.getId().toString(), logo, null);
        collection.setLogo(logoUrl);

        if (banner != null) {
            String coverImageUrl = awsService.saveFile(bannerFolder, collection.getId().toString(), banner, null);
            collection.setCoverImage(coverImageUrl);
        }

        return collectionRepo.save(collection);
    }

    @Override
    public Collection update(Long id, String name, String description, MultipartFile logo, MultipartFile banner)
            throws Exception {
        Collection collection = this.collectionRepo.findById(id).orElse(null);

        if (collection == null) {
            throw new Exception("Collection not found");
        }

        collection.setName(name);
        collection.setDescription(description);
        collection = collectionRepo.save(collection);
        User user = userService.getByPublicAddress(SecurityContextHolder.getContext().getAuthentication().getName());
        collection.setUser(user);

        if (logo != null) {
            String logoUrl = awsService.saveFile(logoFolder, collection.getId().toString(), logo, collection.getLogo());
            collection.setLogo(logoUrl);
        }

        if (banner != null) {
            String coverImageUrl = awsService.saveFile(bannerFolder, collection.getId().toString(), banner,
                    collection.getCoverImage());
            collection.setCoverImage(coverImageUrl);
        }

        return collectionRepo.save(collection);
    }

    @Override
    public void delete(Long id) {
        collectionRepo.deleteById(id);
    }

}
