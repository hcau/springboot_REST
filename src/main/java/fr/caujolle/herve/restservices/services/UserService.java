package fr.caujolle.herve.restservices.services;

import fr.caujolle.herve.restservices.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    Optional<User> getUserById(Long id);
    User updateUserById(Long id, User user);
    void deleteUserById(Long id);
    User getUserByUsername(String username);
}

