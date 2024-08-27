package vn.unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.entity.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}