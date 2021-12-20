package app.web.coralmarketplace.dto;

import java.util.List;

import javax.validation.constraints.Size;

import app.web.coralmarketplace.validation.UniqueCollectionNameConstraint;
import app.web.coralmarketplace.validation.ValidationUtils;

public class CollectionDto {

    private Long id;

    @UniqueCollectionNameConstraint
    @Size(max = ValidationUtils.MAX_LENGTH_SHORT, message = "The name cannot be larger than "
            + ValidationUtils.MAX_LENGTH_SHORT + " characters.")
    private String name;

    @Size(max = ValidationUtils.MAX_LENGTH_LONG, message = "The name cannot be larger than "
            + ValidationUtils.MAX_LENGTH_LONG + " characters.")
    private String description;

    private String logo;

    private String coverImage;

    private String userPublicAddress;

    private List<Long> itemIds;

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

    public String getUserPublicAddress() {
        return userPublicAddress;
    }

    public void setUserPublicAddress(String userPublicAddress) {
        this.userPublicAddress = userPublicAddress;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }

}
