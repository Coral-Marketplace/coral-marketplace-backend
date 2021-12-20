package app.web.coralmarketplace.validation;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import app.web.coralmarketplace.model.User;
import app.web.coralmarketplace.service.UserService;

@Service
public class UserValidations {

    private UserService userService;

    public UserValidations(UserService userService) {
        this.userService = userService;
    }

    public void userUpdate(String publicAddress, String name) throws Exception {
        User user = this.existingUser(publicAddress);
        this.authUser(publicAddress);
        this.uniqueName(user, name);
    }

    private User existingUser(String publicAddress) throws Exception {
        User user = userService.getByPublicAddress(publicAddress);
        if (user == null) {
            throw new Exception("User not found.");
        }
        return user;
    }

    private void authUser(String publicAddress) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!publicAddress.equals(auth.getName())) {
            throw new Exception("Not authorized to perform changes over this user.");
        }
    }

    private void uniqueName(User user, String name) throws Exception {
        if ((user.getName() == null || !user.getName().equals(name)) && userService.getByName(name) != null) {
            throw new Exception("This user name is already being used.");
        }
    }

}
