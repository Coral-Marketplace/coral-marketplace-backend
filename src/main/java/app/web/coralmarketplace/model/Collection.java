package app.web.coralmarketplace.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import app.web.coralmarketplace.validation.ValidationUtils;

@Entity
@Table(name = "collections", catalog = "coral_marketplace")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = ValidationUtils.MAX_LENGTH_SHORT)
    private String name;

    @Column(length = ValidationUtils.MAX_LENGTH_LONG)
    private String description;

    private String logo;

    private String coverImage;

    @ManyToOne
    @JoinColumn(name = "user_public_address")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "collection", fetch = FetchType.EAGER)
    private Set<MarketItem> marketItems = new HashSet<>();

    public Collection() {}

    public Collection(Long id, String name, String description, String logo, String coverImage, User user,
            Set<MarketItem> marketItems) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.coverImage = coverImage;
        this.user = user;
        this.marketItems = marketItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<MarketItem> getMarketItems() {
        return marketItems;
    }

    public void setMarketItems(Set<MarketItem> marketItems) {
        this.marketItems = marketItems;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Collection other = (Collection) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Collection [id=" + id + ", name=" + name + ", description=" + description + ", logo=" + logo
                + ", coverImage=" + coverImage + ", user=" + user.getPublicAddress() + " - " + user.getName() + "]";
    }

}
