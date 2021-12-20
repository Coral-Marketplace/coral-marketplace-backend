package app.web.coralmarketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.web.coralmarketplace.model.MarketItem;
import app.web.coralmarketplace.repository.MarketItemRepo;

@Service
public class MarketItemServiceImp implements MarketItemService {

    private final MarketItemRepo marketItemRepo;

    @Autowired
    public MarketItemServiceImp(MarketItemRepo marketItemRepo) {
        this.marketItemRepo = marketItemRepo;
    }

    @Override
    public MarketItem getById(Long id) {
        return this.marketItemRepo.findById(id).orElse(null);
    }

    @Override
    public MarketItem createOrUpdate(MarketItem marketItem) {
        return this.marketItemRepo.save(marketItem);
    }

}
