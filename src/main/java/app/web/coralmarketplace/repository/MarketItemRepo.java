package app.web.coralmarketplace.repository;

import org.springframework.data.repository.CrudRepository;

import app.web.coralmarketplace.model.MarketItem;

public interface MarketItemRepo extends CrudRepository<MarketItem, Long> {

}
