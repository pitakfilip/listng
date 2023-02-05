package sk.fmfi.listng.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.fmfi.listng.user.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> getByEmail (String email);
}
