package vn.unigap.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unigap.api.entity.Seeker;


/** Manages `Seeker` entity with CRUD operations and custom queries. */
@Repository
public interface SeekerRepository extends JpaRepository<Seeker, Long> {

  Page<Seeker> findAll(Pageable pageable);

  Page<Seeker> findAllByJobProvinceId(Integer jobProvinceId, Pageable pageable);

}
