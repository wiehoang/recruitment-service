package vn.unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.entity.Role;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String roleName);
}
