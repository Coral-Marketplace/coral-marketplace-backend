package app.web.coralmarketplace.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import app.web.coralmarketplace.dto.CollectionDto;
import app.web.coralmarketplace.model.Collection;
import app.web.coralmarketplace.model.MarketItem;
import app.web.coralmarketplace.model.User;

@Mapper
public interface CollectionMapper {

    @Mapping(source = "user", target = "userPublicAddress", qualifiedByName = "userToPublicAddress")
    @Mapping(source = "marketItems", target = "itemIds", qualifiedByName = "toIds")
    CollectionDto mapEntityToDto(Collection collection);

    Collection mapDtoToEntity(CollectionDto collectionDto);

    @Named("toIds")
    public static List<Long> toIds(Set<MarketItem> items) {
        List<Long> ids = new ArrayList<Long>();
        for (MarketItem item : items) {
            ids.add(item.getId());
        }
        return ids;
    }

    @Named("userToPublicAddress")
    public static String userToPublicAddress(User user) {
        return user.getPublicAddress();
    }
}
