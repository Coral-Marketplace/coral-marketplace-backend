package app.web.coralmarketplace.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.web.coralmarketplace.dto.MarketItemDto;
import app.web.coralmarketplace.mapper.MarketItemMapper;
import app.web.coralmarketplace.service.MarketItemService;
import app.web.coralmarketplace.validation.MarketItemValidations;

@RestController
@CrossOrigin
@RequestMapping("/market-item")
public class MarketItemController {

    private MarketItemService marketItemService;
    private MarketItemMapper marketItemMapper;
    private MarketItemValidations marketItemValidations;

    public MarketItemController(MarketItemService marketItemService, MarketItemMapper marketItemMapper,
            MarketItemValidations marketItemValidations) {
        this.marketItemService = marketItemService;
        this.marketItemMapper = marketItemMapper;
        this.marketItemValidations = marketItemValidations;
    }

    @GetMapping("/{id}")
    public MarketItemDto getMarketItem(@PathVariable Long id) {
        return marketItemMapper.mapEntityToDto(marketItemService.getById(id));
    }

    @PostMapping()
    public MarketItemDto createMarketItem(@RequestBody MarketItemDto marketItemDto) throws Exception {
        this.marketItemValidations.itemCreation(marketItemDto);
        return marketItemMapper
                .mapEntityToDto(marketItemService.createOrUpdate(marketItemMapper.mapDtoEntity(marketItemDto)));
    }

    @PutMapping()
    public MarketItemDto updateMarketItem(@RequestBody MarketItemDto marketItemDto) throws Exception {
        this.marketItemValidations.itemUpdate(marketItemDto);
        return marketItemMapper
                .mapEntityToDto(marketItemService.createOrUpdate(marketItemMapper.mapDtoEntity(marketItemDto)));
    }
}
