package app.web.coralmarketplace.dto;

public class MarketItemDto {

    private Long id;

    private CollectionDto collection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CollectionDto getCollection() {
        return collection;
    }

    public void setCollection(CollectionDto collection) {
        this.collection = collection;
    }

}
