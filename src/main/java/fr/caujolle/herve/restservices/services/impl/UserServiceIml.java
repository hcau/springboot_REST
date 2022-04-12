package fr.caujolle.herve.restservices.services.impl;

import fr.caujolle.herve.restservices.entities.User;
import fr.caujolle.herve.restservices.repositories.UserRepository;
import fr.caujolle.herve.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIml implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public User updateUserById(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
