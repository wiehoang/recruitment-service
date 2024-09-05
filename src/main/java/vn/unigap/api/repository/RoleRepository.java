package vn.unigap.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.entity.Role;


/** Manages `Role` entity with CRUD operations and custom queries. */
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String roleName);
}
