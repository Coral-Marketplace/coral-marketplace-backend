package app.web.coralmarketplace.model;

import java.io.Serializable;

public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String publicAddress;
    private String signature;
    private String evmAddress;

    // default constructor for JSON Parsing
    public JwtRequest() {}

    public JwtRequest(String publicAddress, String signature, String evmAddress) {
        super();
        this.publicAddress = publicAddress;
        this.signature = signature;
        this.evmAddress = evmAddress;
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEvmAddress() {
        return evmAddress;
    }

    public void setEvmAddress(String evmAddress) {
        this.evmAddress = evmAddress;
    }

}
