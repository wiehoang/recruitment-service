package vn.unigap.api.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unigap.api.entity.Employer;


/** Manages `Employer` entity with CRUD operations and custom queries. */
@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

  Optional<Employer> findByEmail(String email);

  Page<Employer> findAll(Pageable page);

}
