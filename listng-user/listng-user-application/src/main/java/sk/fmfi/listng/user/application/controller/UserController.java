package sk.fmfi.listng.user.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.domain.enums.Role;
import sk.fmfi.listng.domain.user.User;
import sk.fmfi.listng.user.api.UserApi;
import sk.fmfi.listng.user.application.repository.UserRepository;

@RestController
public class UserController implements UserApi {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping
    public String testing (){
        return "ako sa dari?";
    }

    @GetMapping(value = "/new")
    public User newUser() {
        User user = new User();
        user.setName("Fero");
        user.setEmail("f@f.sk");
        user.setPassword("1234");
        user.setRole(Role.STUDENT);
        userRepository.save(user);

        return user;
    }

}
