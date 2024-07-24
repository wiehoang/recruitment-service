package vn.unigap.api.jdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vn.unigap.api.jdbc.model.JobsData;
import vn.unigap.api.jdbc.model.mapper.JobsMapper;
import java.util.List;
import java.util.Optional;

@Repository
public class JobsDao {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    JobsDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    public Optional<JobsData> findEmployerNameByEmployerId(Long employerId) {
        String sql = "SELECT e.name FROM jobs j" +
                    "LEFT JOIN employer e ON j.employer_id = e.id" +
                    "WHERE employer_id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new JobsMapper()));
    }

//    Return Jobs table with employer's name, provinces's name, fields's name
//    SELECT j.id, j.employer_id, e.name, j.title, j.fields, j.provinces, GROUP_CONCAT(p.name SEPARATOR '-') AS provinces_name, GROUP_CONCAT(f.name SEPARATOR '-') AS fields_name
//    FROM job_db.jobs j
//    LEFT JOIN job_db.employer e ON j.employer_id = e.id
//    LEFT JOIN job_db.job_province p ON CONCAT('-', j.provinces, '-') LIKE CONCAT('%-', p.id, '-%')
//    LEFT JOIN job_db.job_field f ON CONCAT('-', j.fields, '-') LIKE CONCAT('%-', f.id, '-%')
//    GROUP BY j.id;
}

