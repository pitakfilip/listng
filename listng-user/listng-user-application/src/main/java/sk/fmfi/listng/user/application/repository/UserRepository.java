package sk.fmfi.listng.user.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.fmfi.listng.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
