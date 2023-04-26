package sk.fmfi.listng.user.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.domain.user.User;
import sk.fmfi.listng.user.application.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Deprecated // Only used by spring security for authentification
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
        user.setPassword("");
        return user;
    }

    public User getUserById(Long userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isEmpty()) {
            return null;
        }
        
        User user = optional.get();
        user.setPassword("");
        return user;
    }
    
    public boolean exists(String email) {
        return userRepository.existsByEmail(email);
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }
    
    public List<User> save(List<User> users){
        return userRepository.saveAll(users);
    }
    
    public void delete(User user){
        userRepository.deleteByEmail(user.getEmail());
    }

    public void delete(List<User> users){
        userRepository.deleteAllInBatch(users);
    }
}
