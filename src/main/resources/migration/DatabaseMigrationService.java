//package vn.unigap.api.service.migration;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import vn.unigap.api.entity.*;
//import vn.unigap.api.repository.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//
//@Service
//@RequiredArgsConstructor
//public class DatabaseMigrationService {
//
//    private final ResumeRepository resumeRepository;
//    private final ResumeToJobProvinceRepository resumeToJobProvinceRepository;
//    private final ResumeToJobFieldRepository resumeToJobFieldRepository;
//
//    @Transactional
//    public void migrateResumeToJobProvince() {
//        List<Resume> resumes = resumeRepository.findAll();
//        for (Resume resume : resumes) {
//            List<Long> provinceIds = Arrays.stream(resume.getProvinces().split("-"))
//                    .filter(s -> !s.isEmpty())
//                    .map(Long::parseLong)
//                    .toList();
//            for (Long provinceId : provinceIds) {
//                ResumeToJobProvince resumeToJobProvince = new ResumeToJobProvince();
//                resumeToJobProvince.setResumeId(resume.getId());
//                resumeToJobProvince.setJobProvinceId(provinceId);
//                resumeToJobProvinceRepository.save(resumeToJobProvince);
//            }
//        }
//    }
//
//    @Transactional
//    public void migrateResumeToJobField() {
//        List<Resume> resumes = resumeRepository.findAll();
//        for (Resume resume : resumes) {
//            List<Long> fieldIds = Arrays.stream(resume.getFields().split("-"))
//                    .filter(s -> !s.isEmpty())
//                    .map(Long::parseLong)
//                    .toList();
//            for (Long fieldId : fieldIds) {
//                ResumeToJobField resumeToJobField = new ResumeToJobField();
//                resumeToJobField.setResumeId(resume.getId());
//                resumeToJobField.setJobFieldId(fieldId);
//                resumeToJobFieldRepository.save(resumeToJobField);
//            }
//        }
//    }
//}