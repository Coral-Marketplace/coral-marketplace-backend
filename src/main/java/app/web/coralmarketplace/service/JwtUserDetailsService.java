package app.web.coralmarketplace.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.web.coralmarketplace.model.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String publicAddress) {

        User user = userService.getByPublicAddress(publicAddress);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with address: " + publicAddress);
        }

        return new org.springframework.security.core.userdetails.User(user.getPublicAddress(),
                user.getNonce().toString(), new ArrayList<>());
    }

}
