#
# -- Create additional table to transfer data between Jobs, JobProvince and JobField tables
# CREATE TABLE JobsToJobProvince (id BIGINT AUTO_INCREMENT PRIMARY KEY,
#                                 job_id BIGINT NOT NULL,
#                                 job_province_id INT NOT NULL,
#                                 FOREIGN KEY (job_id) REFERENCES job_db.jobs(id),
#                                 FOREIGN KEY (job_province_id) REFERENCES job_db.job_province(id)
#                                );
#
# CREATE TABLE JobsToJobField (id BIGINT AUTO_INCREMENT PRIMARY KEY,
#                                 job_id BIGINT NOT NULL,
#                                 job_field_id BIGINT NOT NULL,
#                                 FOREIGN KEY (job_id) REFERENCES job_db.jobs(id),
#                                 FOREIGN KEY (job_field_id) REFERENCES job_db.job_field(id)
#                             );
#
# -- Create additional table to transfer data between Resume, JobProvince and JobField tables
# CREATE TABLE ResumeToJobProvince (id BIGINT AUTO_INCREMENT PRIMARY KEY,
#                                resume_id BIGINT NOT NULL,
#                                job_province_id INT NOT NULL,
#                                FOREIGN KEY (resume_id) REFERENCES job_db.resume(id),
#                                FOREIGN KEY (job_province_id) REFERENCES job_db.job_province(id)
# );
#
# CREATE TABLE ResumeToJobField (id BIGINT AUTO_INCREMENT PRIMARY KEY,
#                              resume_id BIGINT NOT NULL,
#                              job_field_id BIGINT NOT NULL,
#                              FOREIGN KEY (resume_id) REFERENCES job_db.resume(id),
#                              FOREIGN KEY (job_field_id) REFERENCES job_db.job_field(id)
# );
#
# -- Delete records that are not filled job_province in the new table
# DELETE FROM job_db.resume_to_job_province
# WHERE job_province_id = 0;
#
# -- Delete records that are not filled job_field in the new table
# DELETE FROM job_db.resume_to_job_field
# WHERE job_field_id = 0;
#
# -- Delete province and field columns from original table after migrating data to the new table
# ALTER TABLE job_db.resume
# DROP COLUMN fields,
# DROP COLUMN provinces;