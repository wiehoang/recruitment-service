package vn.unigap.api.jdbc.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import vn.unigap.api.jdbc.model.EmployerData;
import vn.unigap.api.jdbc.model.JobProvinceData;
import vn.unigap.api.jdbc.model.JobsData;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JobsMapper implements RowMapper<JobsData> {
    @Override
    public JobsData mapRow(ResultSet rs, int rowNum) throws SQLException {

        // Map with Jobs's model
        JobsData jobsData = new JobsData();
        jobsData.setId(rs.getLong("id"));
        Long employerId = rs.getLong("employer_id");
        jobsData.setTitle(rs.getString("title"));
        jobsData.setQuantity(rs.getInt("quantity"));
        jobsData.setDescription(rs.getString("description"));
        jobsData.setSalary(rs.getInt("salary"));
        jobsData.setFields(rs.getString("fields"));
        jobsData.setFieldsName(rs.getString("fields_name"));  // Mapping with initialize column from GROUP_CONCAT query
        jobsData.setProvinces(rs.getString("provinces"));
        jobsData.setProvincesName(rs.getString("provinces_name"));  // Mapping with initialize column from GROUP_CONCAT query
        jobsData.setCreatedAt(rs.getDate("createdAt"));
        jobsData.setUpdatedAt(rs.getDate("updatedAt"));
        jobsData.setExpiredAt(rs.getDate("expiredAt"));

        // Only map with Employer's model when employerId is not null
        if (!rs.wasNull()) {
            EmployerData employerData = new EmployerData();
            employerData.setId(employerId);
            employerData.setName(rs.getString("name"));
            jobsData.setEmployerData(employerData);
        }

        return jobsData;
    }
}
