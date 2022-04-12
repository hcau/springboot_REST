package fr.caujolle.herve.restservices.controllers;

import fr.caujolle.herve.restservices.models.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // @RequestMapping(method = RequestMethod.GET, path = "/helloworld")
    @GetMapping("/helloworld")
    public String HelloWorld(){
        return "Hello World";
    }

    @GetMapping("/helloworld-bean")
    public UserDetails helloWorldBean(){
        return new UserDetails("Hervé", "Caujolle", "Toulouse");
    }
}
