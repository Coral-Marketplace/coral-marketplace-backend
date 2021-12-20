package app.web.coralmarketplace.repository;

import org.springframework.data.repository.CrudRepository;

import app.web.coralmarketplace.model.User;

public interface UserRepo extends CrudRepository<User, String> {

    User findByName(String name);

}
