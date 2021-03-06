package fr.caujolle.herve.restservices.controllers;

import fr.caujolle.herve.restservices.entities.User;
import fr.caujolle.herve.restservices.exceptions.UserExistsException;
import fr.caujolle.herve.restservices.exceptions.UserNotFoundException;
import fr.caujolle.herve.restservices.exceptions.customized.UserNameNotFoundException;
import fr.caujolle.herve.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder){
        try{
            userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }catch (UserExistsException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

//    @GetMapping("/users/{id}")
//    public Optional<User> getUserById(@PathVariable("id") Long id){
//        Sans Global Exception Handler
//        try{
//            return userService.getUserById(id);
//        }catch (UserNotFoundException exception){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
//        }
//    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id){
        try{
            return userService.getUserById(id);
        }catch (UserNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PutMapping("/users/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        try{
            return userService.updateUserById(id, user);
        }catch (UserNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

    @GetMapping("/users/byusername/{username}")
    public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
        User user = userService.getUserByUsername(username);
        if(user == null)
            throw new UserNameNotFoundException("Username '"+ username + "' not found in User repository.");
        return user;
    }


}
