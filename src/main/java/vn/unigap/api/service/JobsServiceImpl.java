package vn.unigap.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.unigap.api.common.errorcode.ErrorCode;
import vn.unigap.api.common.exception.ApiException;
import vn.unigap.api.dto.in.JobsDtoIn;
import vn.unigap.api.dto.in.UpdateJobsDtoIn;
import vn.unigap.api.dto.out.JobFieldDtoOut;
import vn.unigap.api.dto.out.JobProvinceDtoOut;
import vn.unigap.api.dto.out.JobsDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.entity.*;
import vn.unigap.api.repository.*;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;
import static vn.unigap.api.service.EmployerServiceImpl.currentDateTime;


@Service
@RequiredArgsConstructor
public class JobsServiceImpl implements JobsService {

    @Autowired
    final private EmployerRepository employerRepository;
    final private JobsRepository jobsRepository;
    final private JobsToJobFieldRepository jobsToJobFieldRepository;
    final private JobsToJobProvinceRepository jobsToJobProvinceRepository;
    final private JobFieldRepository jobFieldRepository;
    final private JobProvinceRepository jobProvinceRepository;

    @Override
    @Transactional
    public JobsDtoOut createJob(JobsDtoIn jobsDtoIn) {

        // Handle not exists employer's id
        if (!employerRepository.findById(jobsDtoIn.getEmployerId()).isPresent()) {
            throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer not found");
        }

        // Handle invalid field's ids
        Set<Long> fieldIds = convertStringtoSet(jobsDtoIn.getFieldIds());
        if(jobFieldRepository.findAllById(fieldIds).size() != fieldIds.size()){
            throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Invalid field id");
        }

        // Handle invalid province's ids
        Set<Long> provinceIds = convertStringtoSet(jobsDtoIn.getProvinceIds());
        if(jobProvinceRepository.findAllById(provinceIds).size() != provinceIds.size()) {
            throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Invalid province id");
        }

        // Save a new record in Jobs
        Employer employer = employerRepository.findById(jobsDtoIn.getEmployerId()).orElse(null);
        Jobs jobs = new Jobs();
        jobs.setTitle(jobsDtoIn.getTitle());
        jobs.setEmployer(employer);
        jobs.setQuantity(jobsDtoIn.getQuantity());
        jobs.setDescription(jobsDtoIn.getDescription());
        jobs.setSalary(jobsDtoIn.getSalary());
        jobs.setExpiredAt(jobsDtoIn.getExpiredAt());
        jobs.setCreatedAt(Date.from(currentDateTime().toInstant(ZoneOffset.UTC)));
        jobs.setUpdatedAt(Date.from(currentDateTime().toInstant(ZoneOffset.UTC)));
        jobsRepository.save(jobs);

        // Save a new record in JobsToJobField
        for (Long fieldId : fieldIds) {
            JobField jobField = jobFieldRepository.findById(fieldId).orElse(null);
            JobsToJobField jobsToJobField = new JobsToJobField();
            jobsToJobField.setJobs(jobs);
            jobsToJobField.setJobField(jobField);
            jobsToJobFieldRepository.save(jobsToJobField);
        }

        // Save a new record in JobsToJobProvince
        for (Long provinceId : provinceIds) {
            JobProvince jobProvince = jobProvinceRepository.findById(provinceId).orElse(null);
            JobsToJobProvince jobsToJobProvince = new JobsToJobProvince();
            jobsToJobProvince.setJobs(jobs);
            jobsToJobProvince.setJobProvince(jobProvince);
            jobsToJobProvinceRepository.save(jobsToJobProvince);
        }

        return JobsDtoOut.fromCreate(jobs, fieldIds, provinceIds);
    }

    @Override
    @Transactional
    public JobsDtoOut updateJob(Long id, UpdateJobsDtoIn updateJobsDtoIn) {

        // Handle not exists job's id
        if (!jobsRepository.findById(id).isPresent()) {
            throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Job not found");
        }

        // Handle invalid field's ids
        Set<Long> fieldIds = convertStringtoSet(updateJobsDtoIn.getFieldIds());
        if(jobFieldRepository.findAllById(fieldIds).size() != fieldIds.size()){
            throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Invalid field id");
        }

        // Handle invalid province's ids
        Set<Long> provinceIds = convertStringtoSet(updateJobsDtoIn.getProvinceIds());
        if(jobProvinceRepository.findAllById(provinceIds).size() != provinceIds.size()) {
            throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Invalid province id");
        }

        // Update a record in Jobs
        Jobs jobs = new Jobs();
        jobs.setTitle(updateJobsDtoIn.getTitle());
        jobs.setQuantity(updateJobsDtoIn.getQuantity());
        jobs.setDescription(updateJobsDtoIn.getDescription());
        jobs.setSalary(updateJobsDtoIn.getSalary());
        jobs.setExpiredAt(updateJobsDtoIn.getExpiredAt());
        jobs.setUpdatedAt(Date.from(currentDateTime().toInstant(ZoneOffset.UTC)));
        jobsRepository.save(jobs);

        // Update a record in JobsToJobField
        for (Long fieldId : fieldIds) {
            JobField jobField = jobFieldRepository.findById(fieldId).orElse(null);
            JobsToJobField jobsToJobField = new JobsToJobField();
            jobsToJobField.setJobs(jobs);
            jobsToJobField.setJobField(jobField);
            jobsToJobFieldRepository.save(jobsToJobField);
        }

        // Update a record in JobsToJobProvince
        for (Long provinceId : provinceIds) {
            JobProvince jobProvince = jobProvinceRepository.findById(provinceId).orElse(null);
            JobsToJobProvince jobsToJobProvince = new JobsToJobProvince();
            jobsToJobProvince.setJobs(jobs);
            jobsToJobProvince.setJobProvince(jobProvince);
            jobsToJobProvinceRepository.save(jobsToJobProvince);
        }

        return JobsDtoOut.fromUpdate(jobs, fieldIds, provinceIds);
    }

    @Override
    @Transactional
    public JobsDtoOut getJob(Long id) {

        // Handle not exists job's id
        Jobs jobs = jobsRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Job not found"));

        // Handle employer is null
        if (jobs.getEmployer() == null) {
            Long employerId = jobs.getEmployer().getId() != null ? jobs.getEmployer().getId() : -1;
            String employerName= jobs.getEmployer().getName();
        }

        // Create a set of fields with (id, name) format for each element
        Set<Long> fieldIds = jobsToJobFieldRepository.findFieldIdByJobId(id);
        Set<JobField> jobFields = new HashSet<>(jobFieldRepository.findAllById(fieldIds));
        Set<JobFieldDtoOut> jobFieldsDtoOut = new HashSet<>();
        for(JobField jobField : jobFields){
            JobFieldDtoOut jobFieldDtoOut = JobFieldDtoOut.from(jobField);
            jobFieldsDtoOut.add(jobFieldDtoOut);
        }

        // Create a set of provinces with (id, name) format for each element
        Set<Long> provinceIds = jobsToJobProvinceRepository.findProvinceIdByJobId(id);
        Set<JobProvince> jobProvinces = new HashSet<>(jobProvinceRepository.findAllById(provinceIds));
        Set<JobProvinceDtoOut> jobProvincesDtoOut = new HashSet<>();
        for(JobProvince jobProvince : jobProvinces){
            JobProvinceDtoOut jobProvinceDtoOut = JobProvinceDtoOut.from(jobProvince);
            jobProvincesDtoOut.add(jobProvinceDtoOut);
        }

        return JobsDtoOut.fromGet(jobs, jobFieldsDtoOut, jobProvincesDtoOut);

    }

    @Override
    @Transactional
    public PageDtoOut<JobsDtoOut> getAllJobs(Long employerId, Pageable pageable) {

    }



    public Set<Long> convertStringtoSet(String str) {
        Set<Long> strToSet = Arrays.stream(str.split("[-_, ]"))
                .filter(s -> !s.isEmpty())      // Convert fields type
                .map(Long::parseLong)           // to implement into
                .collect(Collectors.toSet());  // JPA named query
        return strToSet;
    }

}

