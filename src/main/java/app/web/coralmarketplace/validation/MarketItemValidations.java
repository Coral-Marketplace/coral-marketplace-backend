package app.web.coralmarketplace.validation;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import app.web.coralmarketplace.dto.CollectionDto;
import app.web.coralmarketplace.dto.MarketItemDto;
import app.web.coralmarketplace.model.Collection;
import app.web.coralmarketplace.model.MarketItem;
import app.web.coralmarketplace.service.CollectionService;
import app.web.coralmarketplace.service.MarketItemService;

@Service
public class MarketItemValidations {

    private MarketItemService marketItemService;

    private CollectionService collectionService;

    public MarketItemValidations(MarketItemService marketItemService, CollectionService collectionService) {
        this.marketItemService = marketItemService;
        this.collectionService = collectionService;
    }

    public void itemCreation(MarketItemDto marketItemDto) throws Exception {
        checkCollection(marketItemDto.getCollection());
    }

    public void itemUpdate(MarketItemDto marketItemDto) throws Exception {
        existingItem(marketItemDto.getId());
        checkCollection(marketItemDto.getCollection());
    }

    private void existingItem(Long id) throws Exception {
        MarketItem marketItem = marketItemService.getById(id);
        if (marketItem == null) {
            throw new Exception("Market item not found.");
        }
    }

    private void checkCollection(CollectionDto collectionDto) throws Exception {
        if (collectionDto == null) {
            return;
        }

        Collection collection = collectionService.getById(collectionDto.getId());
        if (collection == null) {
            throw new Exception("Collection not found.");
        }

        if (!SecurityContextHolder.getContext().getAuthentication().getName()
                .equals(collection.getUser().getPublicAddress())) {
            throw new Exception("You are not the owner of the collection.");
        }

    }

}
