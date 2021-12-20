package app.web.coralmarketplace.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import app.web.coralmarketplace.validation.ValidationUtils;

@Entity
@Table(name = "users", catalog = "coral_marketplace")
public class User {

    @Id
    private String publicAddress;

    private Integer nonce;

    @Column(unique = true, length = ValidationUtils.MAX_LENGTH_SHORT)
    private String name;

    @Column(unique = true)
    private String evmAddress;

    private String avatar;

    private String coverImage;

    private boolean activated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Collection> collections = new HashSet<>();

    public User() {}

    public User(String publicAddress) {
        this.publicAddress = publicAddress;
    }

    public User(String publicAddress, Integer nonce, String name, String evmAddress, String avatar, String coverImage,
            boolean activated, Set<Collection> collections) {
        super();
        this.publicAddress = publicAddress;
        this.nonce = nonce;
        this.name = name;
        this.evmAddress = evmAddress;
        this.avatar = avatar;
        this.coverImage = coverImage;
        this.activated = activated;
        this.collections = collections;
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }

    public Integer getNonce() {
        return nonce;
    }

    public void setNonce(Integer nonce) {
        this.nonce = nonce;
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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Set<Collection> getCollections() {
        return collections;
    }

    public void setCollections(Set<Collection> collections) {
        this.collections = collections;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((publicAddress == null) ? 0 : publicAddress.hashCode());
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
        User other = (User) obj;
        if (publicAddress == null) {
            if (other.publicAddress != null)
                return false;
        } else if (!publicAddress.equals(other.publicAddress))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [publicAddress=" + publicAddress + ", name=" + name + ", evmAddress=" + evmAddress + ", avatar="
                + avatar + ", coverImage=" + coverImage + ", activated=" + activated + "]";
    }

}
