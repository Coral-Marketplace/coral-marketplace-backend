package app.web.coralmarketplace.dto;

import java.util.List;

import javax.validation.constraints.Size;

import app.web.coralmarketplace.validation.UniqueUserNameConstraint;
import app.web.coralmarketplace.validation.ValidationUtils;

public class UserDto {

    private String publicAddress;

    @UniqueUserNameConstraint
    @Size(max = ValidationUtils.MAX_LENGTH_SHORT, message = "The name cannot be larger than "
            + ValidationUtils.MAX_LENGTH_SHORT + " characters.")
    private String name;

    private String evmAddress;

    private String avatar;

    private String coverImage;

    private List<CollectionDto> collections;

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvmAddress() {
        return evmAddress;
    }

    public void setEvmAddress(String evmAddress) {
        this.evmAddress = evmAddress;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public List<CollectionDto> getCollections() {
        return collections;
    }

    public void setCollections(List<CollectionDto> collections) {
        this.collections = collections;
    }

}
