package app.web.coralmarketplace.mapper;

import org.springframework.stereotype.Service;

import app.web.coralmarketplace.dto.MarketItemDto;
import app.web.coralmarketplace.model.MarketItem;

@Service
public class MarketItemMapperImpl implements MarketItemMapper {

    CollectionMapper collectionMapper;

    public MarketItemMapperImpl(CollectionMapper collectionMapper) {
        this.collectionMapper = collectionMapper;
    }

    @Override
    public MarketItemDto mapEntityToDto(MarketItem marketItem) {
        MarketItemDto dto = new MarketItemDto();
        dto.setId(marketItem.getId());
        dto.setCollection(collectionMapper.mapEntityToDto(marketItem.getCollection()));

        return dto;
    }

    @Override
    public MarketItem mapDtoEntity(MarketItemDto marketItemDto) {
        MarketItem entity = new MarketItem();
        entity.setId(marketItemDto.getId());
        entity.setCollection(collectionMapper.mapDtoToEntity(marketItemDto.getCollection()));

        return entity;
    }

}
