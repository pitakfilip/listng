package sk.fmfi.listng.user.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.domain.user.User;
import sk.fmfi.listng.user.application.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User getAuthByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        return user;
    }
    
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users; //TODO if null alebo nieco?
    }
    
    public boolean exists(String email) {
        return userRepository.findByEmail(email) != null;
    }
    
    public void save(User user) {
        userRepository.save(user);
    }
}
