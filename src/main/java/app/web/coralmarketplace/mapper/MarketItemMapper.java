package app.web.coralmarketplace.mapper;

import app.web.coralmarketplace.dto.MarketItemDto;
import app.web.coralmarketplace.model.MarketItem;

public interface MarketItemMapper {

    MarketItemDto mapEntityToDto(MarketItem marketItem);

    MarketItem mapDtoEntity(MarketItemDto marketItemDto);

}
