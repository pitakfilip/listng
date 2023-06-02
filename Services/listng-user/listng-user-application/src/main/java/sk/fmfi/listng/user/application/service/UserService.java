package sk.fmfi.listng.user.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.user.application.assembler.UserAssembler;
import sk.fmfi.listng.user.application.repository.UserRepository;
import sk.fmfi.listng.user.domain.SystemRole;
import sk.fmfi.listng.user.domain.User;
import sk.fmfi.listng.user.dto.UserDto;

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

    public List<UserDto> getUsersById(List<Long> ids) {
        return userRepository.findAllByIdIn(ids).stream()
                .map(UserAssembler::toDto)
                .toList();
    }
    
    public PageResponse<User> getUsersPage(Pageable pageable, boolean students) {
        List<SystemRole> roles;
        if (students) {
            roles = List.of(SystemRole.STUDENT);
        }
        else {
            roles = List.of(SystemRole.TEACHER, SystemRole.ROOT);
        }
        
        Page<User> page = userRepository.findAllByRoleIn(pageable, roles);
        return new PageResponse<>(page);
    }
    
    public boolean exists(String email) {
        return userRepository.existsByEmail(email);
    }
    
    public boolean exists(Long id) { return userRepository.existsById(id);}
    
    @Transactional
    public void delete(List<Long> userIds){
        userRepository.deleteByIdIn(userIds);
    }
    
}
