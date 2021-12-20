package app.web.coralmarketplace.service;

import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import app.web.coralmarketplace.model.User;
import app.web.coralmarketplace.repository.UserRepo;
import io.emeraldpay.polkaj.schnorrkel.Schnorrkel;
import io.emeraldpay.polkaj.schnorrkel.SchnorrkelException;
import io.emeraldpay.polkaj.types.Address;

@Service
public class UserServiceImp implements UserService {

    @Value("${aws.folder.user.avatar}")
    private String avatarFolder;

    @Value("${aws.folder.user.banner}")
    private String bannerFolder;

    private static final String SIGN_MESSAGE = "Sign this nonce to authenticate in Coral Marketplace: ";

    private final UserRepo userRepo;
    private final AwsService awsService;

    @Autowired
    public UserServiceImp(UserRepo userRepo, AwsService awsService) {
        this.userRepo = userRepo;
        this.awsService = awsService;
    }

    @Override
    public User getByPublicAddress(String publicAddress) {
        return userRepo.findById(publicAddress).orElse(null);
    }

    @Override
    public User getByName(String name) {
        return userRepo.findByName(name);
    }

    @Override
    public User update(String publicAddress, String name, MultipartFile avatar, MultipartFile coverImage)
            throws Exception {
        User user = userRepo.findById(publicAddress).orElse(null);
        if (user == null) {
            return null;
        }

        if (user.getName() == null || !user.getName().equals(name)) {
            user.setName(name);
        }

        if (avatar != null) {
            String avatarUrl = awsService.saveFile(avatarFolder, publicAddress, avatar, user.getAvatar());
            user.setAvatar(avatarUrl);
        }

        if (coverImage != null) {
            String coverImageUrl = awsService.saveFile(bannerFolder, publicAddress, coverImage, user.getCoverImage());
            user.setCoverImage(coverImageUrl);
        }

        return userRepo.save(user);
    }

    @Override
    public void delete(String publicAddress) {
        userRepo.deleteById(publicAddress);
    }

    @Override
    public Integer getNonce(String publicAddress) {
        User user = userRepo.findById(publicAddress).orElse(null);

        if (user == null) {
            user = new User(publicAddress);
        }

        Integer nonce = new Random().nextInt(999999);

        user.setNonce(nonce);
        userRepo.save(user);

        return nonce;
    }

    @Override
    public void authenticate(String publicAddress, String signature, String evmAddress) throws Exception {
        User user = userRepo.findById(publicAddress).orElse(null);
        if (user == null) {
            throw new Exception("User not found.");
        }

        if (!validateSignature(publicAddress, signature, user.getNonce().toString())) {
            throw new Exception("Authentication failed.");
        }

        if (!user.isActivated()) {
            user.setEvmAddress(evmAddress);
            user.setActivated(true);
            userRepo.save(user);
        }
    }

    private boolean validateSignature(String publicAddress, String signature, String nonce) throws SchnorrkelException {
        String message = SIGN_MESSAGE + nonce;
        String wrappedMessage = "<Bytes>" + message + "</Bytes>";
        Address address = Address.from(publicAddress);
        Schnorrkel.PublicKey signer = new Schnorrkel.PublicKey(address.getPubkey());
        byte[] signatureBytes = DatatypeConverter.parseHexBinary(signature.toLowerCase().replace("0x", ""));
        boolean valid = Schnorrkel.getInstance().verify(signatureBytes, wrappedMessage.getBytes(), signer);

        if (!valid) {
            valid = Schnorrkel.getInstance().verify(signatureBytes, message.getBytes(), signer);
        }

        return valid;
    }

}
