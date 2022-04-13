package fr.caujolle.herve.restservices.services.impl;

import fr.caujolle.herve.restservices.entities.User;
import fr.caujolle.herve.restservices.exceptions.UserExistsException;
import fr.caujolle.herve.restservices.exceptions.UserNotFoundException;
import fr.caujolle.herve.restservices.repositories.UserRepository;
import fr.caujolle.herve.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public User createUser(User user) throws UserExistsException {
        // if exist using username
        User existingUser = userRepository.findByUsername(user.getUsername());

        // si username existe déjà en base
        // if not exists throw UserExistsException
        if(existingUser != null){
            throw new UserExistsException("User already exists in repository");
        }

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User not found in user Repository.");
        }
        return userOptional;
    }

    @Override
    public User updateUserById(Long id, User user) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User not found in user Repository, provide the correct user id.");
        }

        // Ici on retourne User et pas List<User> car id est unique.
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {

//        if(userRepository.findById(id).isPresent()){
//            userRepository.deleteById(id);
//        }

        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found in user Repository, provide the correct user id.");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
