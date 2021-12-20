package app.web.coralmarketplace.repository;

import org.springframework.data.repository.CrudRepository;

import app.web.coralmarketplace.model.Collection;

public interface CollectionRepo extends CrudRepository<Collection, Long> {
    Iterable<Collection> findByUserPublicAddress(String userPublicAddress);

    Collection findByName(String name);

}
