package app.web.coralmarketplace.service;

import app.web.coralmarketplace.model.MarketItem;

public interface MarketItemService {

    MarketItem getById(Long id);

    MarketItem createOrUpdate(MarketItem marketItem);

}
