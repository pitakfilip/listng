package sk.fmfi.listng.user.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sk.fmfi.listng.user.domain.SystemRole;
import sk.fmfi.listng.user.domain.User;

import java.util.List;

    
public interface UserRepository extends JpaRepository<User, Long>{
    
    User findByEmail(String email);
    
    List<User> findAllByIdIn(List<Long> userIds);
    
    boolean existsByEmail(String email);
        
    void deleteById(Long userId);
    
    void deleteByIdIn(List<Long> userIds);
    
    Page<User> findAllByRoleIn(Pageable pageable, List<SystemRole> roles);
}

