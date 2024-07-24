//package vn.unigap.api.service.migration;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import vn.unigap.api.entity.Jobs;
//import vn.unigap.api.entity.JobsToJobField;
//import vn.unigap.api.entity.JobsToJobProvince;
//import vn.unigap.api.repository.JobsRepository;
//import vn.unigap.api.repository.JobsToJobFieldRepository;
//import vn.unigap.api.repository.JobsToJobProvinceRepository;
//import java.util.Arrays;
//import java.util.List;
//
//
//@Service
//@RequiredArgsConstructor
//public class DatabaseMigrationService {
//    @Autowired
//    private final JobsRepository jobsRepository;
//    private final JobsToJobProvinceRepository jobsToJobProvinceRepository;
//    private final JobsToJobFieldRepository jobsToJobFieldRepository;
//
//    @Transactional
//    public void migrateJobsToJobProvince() {
//        List<Jobs> jobs = jobsRepository.findAll();
//        for (Jobs job : jobs) {
//            List<Long> provinceIds = Arrays.stream(job.getProvinces().split("-"))
//                    .filter(s -> !s.isEmpty())
//                    .map(Long::parseLong)
//                    .toList();
//            for (Long provinceId : provinceIds) {
//                JobsToJobProvince jobsToJobProvince = new JobsToJobProvince();
//                jobsToJobProvince.setJobId(job.getId());
//                jobsToJobProvince.setJobProvinceId(provinceId);
//                jobsToJobProvinceRepository.save(jobsToJobProvince);
//            }
//        }
//    }
//
//    @Transactional
//    public void migrateJobsToJobField() {
//        List<Jobs> jobs = jobsRepository.findAll();
//        for (Jobs job : jobs) {
//            List<Long> fieldIds = Arrays.stream(job.getFields().split("-"))
//                    .filter(s -> !s.isEmpty())
//                    .map(Long::parseLong)
//                    .toList();
//            for (Long fieldId : fieldIds) {
//                JobsToJobField jobsToJobField = new JobsToJobField();
//                jobsToJobField.setJobId(job.getId());
//                jobsToJobField.setJobFieldId(fieldId);
//                jobsToJobFieldRepository.save(jobsToJobField);
//            }
//        }
//    }
//}