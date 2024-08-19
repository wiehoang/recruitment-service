package vn.unigap.api.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.auth.entity.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
