package fr.caujolle.herve.restservices.services;

import fr.caujolle.herve.restservices.entities.User;
import fr.caujolle.herve.restservices.exceptions.UserExistsException;
import fr.caujolle.herve.restservices.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user) throws UserExistsException;
    Optional<User> getUserById(Long id) throws UserNotFoundException;
    User updateUserById(Long id, User user) throws UserNotFoundException;
    void deleteUserById(Long id);
    User getUserByUsername(String username);
}

