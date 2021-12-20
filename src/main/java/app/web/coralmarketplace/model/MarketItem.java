package app.web.coralmarketplace.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "market_items", catalog = "coral_marketplace")
public class MarketItem {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;

    public MarketItem() {}

    public MarketItem(Long id, Collection collection) {
        super();
        this.id = id;
        this.collection = collection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MarketItem other = (MarketItem) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "MarketItem [id=" + id + ", collectionId=" + collection.getId() + "]";
    }

}
