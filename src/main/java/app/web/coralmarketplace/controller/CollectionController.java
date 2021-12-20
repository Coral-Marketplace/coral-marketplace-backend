package app.web.coralmarketplace.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.web.coralmarketplace.dto.CollectionDto;
import app.web.coralmarketplace.mapper.CollectionMapper;
import app.web.coralmarketplace.service.CollectionService;

@RestController
@CrossOrigin
@RequestMapping("/collection")
public class CollectionController {

    private CollectionService collectionService;
    private CollectionMapper collectionMapper;

    public CollectionController(CollectionService collectionService, CollectionMapper collectionMapper) {
        this.collectionService = collectionService;
        this.collectionMapper = collectionMapper;
    }

    @GetMapping("/{id}")
    public CollectionDto getCollection(@PathVariable Long id) throws Exception {
        return this.collectionMapper.mapEntityToDto(collectionService.getById(id));
    }

    @GetMapping()
    public List<CollectionDto> getAllCollections() {
        return collectionService.getAll().stream().map(collection -> collectionMapper.mapEntityToDto(collection))
                .collect(Collectors.toList());
    }

    @GetMapping("/by-user/{userPublicAddress}")
    public List<CollectionDto> getCollectionsByUser(@PathVariable String userPublicAddress) {
        return collectionService.getByUserPublicAddress(userPublicAddress).stream()
                .map(collection -> collectionMapper.mapEntityToDto(collection)).collect(Collectors.toList());
    }

    @PostMapping()
    public CollectionDto createCollection(@RequestParam String name, @RequestParam(required = false) String description,
            @RequestParam(required = false) MultipartFile logo, @RequestParam(required = false) MultipartFile banner)
            throws Exception {
        return collectionMapper.mapEntityToDto(collectionService.save(name, description, logo, banner));
    }

    @PostMapping("/{id}")
    public CollectionDto updateCollection(@PathVariable Long id, @RequestParam String name,
            @RequestParam(required = false) String description, @RequestParam(required = false) MultipartFile logo,
            @RequestParam(required = false) MultipartFile banner) throws Exception {
        return collectionMapper.mapEntityToDto(collectionService.update(id, name, description, logo, banner));
    }

    @DeleteMapping("/{id}")
    public void deleteCollection(@PathVariable Long id) {
        collectionService.delete(id);
    }
}
