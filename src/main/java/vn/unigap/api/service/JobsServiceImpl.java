package vn.unigap.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.unigap.api.dto.in.JobsDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.UpdateJobsDtoIn;
import vn.unigap.api.dto.out.JobFieldDtoOut;
import vn.unigap.api.dto.out.JobProvinceDtoOut;
import vn.unigap.api.dto.out.JobsDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.entity.*;
import vn.unigap.api.repository.*;
import vn.unigap.common.exception.ApiException;
import java.util.*;
import static vn.unigap.common.Common.convertStringToSet;
import static vn.unigap.common.Common.currentDateTime;


@Service
@RequiredArgsConstructor
public class JobsServiceImpl implements JobsService {

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
        Employer employer = employerRepository.findById(jobsDtoIn.getEmployerId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Employer not found"));

        // Handle invalid field's ids
        Set<Long> fieldIds = convertStringToSet(jobsDtoIn.getFieldIds());
        List<JobField> jobFields = jobFieldRepository.findAllById(fieldIds);
        if(jobFields.size() != fieldIds.size()){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid field id");
        }

        // Handle invalid province's ids
        Set<Long> provinceIds = convertStringToSet(jobsDtoIn.getProvinceIds());
        List<JobProvince> jobProvinces = jobProvinceRepository.findAllById(provinceIds);
        if(jobProvinces.size() != provinceIds.size()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid province id");
        }

        // Save a new record in Jobs
        Jobs jobs = new Jobs();
        jobs.setTitle(jobsDtoIn.getTitle());
        jobs.setEmployer(employer);
        jobs.setQuantity(jobsDtoIn.getQuantity());
        jobs.setDescription(jobsDtoIn.getDescription());
        jobs.setSalary(jobsDtoIn.getSalary());
        jobs.setExpiredAt(jobsDtoIn.getExpiredAt());
        jobs.setCreatedAt(currentDateTime());
        jobs.setUpdatedAt(currentDateTime());
        jobsRepository.save(jobs);

        // Save a new record in JobsToJobField
        for (JobField jobField : jobFields) {
            JobsToJobField jobsToJobField = new JobsToJobField();
            jobsToJobField.setJobs(jobs);
            jobsToJobField.setJobField(jobField);
            jobsToJobFieldRepository.save(jobsToJobField);
        }

        // Save a new record in JobsToJobProvince
        for (JobProvince jobProvince : jobProvinces) {
            JobsToJobProvince jobsToJobProvince = new JobsToJobProvince();
            jobsToJobProvince.setJobs(jobs);
            jobsToJobProvince.setJobProvince(jobProvince);
            jobsToJobProvinceRepository.save(jobsToJobProvince);
        }

        return JobsDtoOut.create(jobs, fieldIds, provinceIds);
    }

    @Override
    @Transactional
    public JobsDtoOut updateJob(Long id, UpdateJobsDtoIn updateJobsDtoIn) {

        // Handle not exists job's id
        Jobs jobs = jobsRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Job not found"));

        // Handle invalid field's ids
        Set<Long> fieldIds = convertStringToSet(updateJobsDtoIn.getFieldIds());
        List<JobField> jobFields = jobFieldRepository.findAllById(fieldIds);
        if (jobFields.size() != fieldIds.size()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid field id");
        }

        // Handle invalid province's ids
        Set<Long> provinceIds = convertStringToSet(updateJobsDtoIn.getProvinceIds());
        List<JobProvince> jobProvinces = jobProvinceRepository.findAllById(provinceIds);
        if (jobProvinces.size() != provinceIds.size()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid province id");
        }

        // Update a record in Jobs
        jobs.setTitle(updateJobsDtoIn.getTitle());
        jobs.setQuantity(updateJobsDtoIn.getQuantity());
        jobs.setDescription(updateJobsDtoIn.getDescription());
        jobs.setSalary(updateJobsDtoIn.getSalary());
        jobs.setExpiredAt(updateJobsDtoIn.getExpiredAt());
        jobs.setUpdatedAt(currentDateTime());
        jobsRepository.save(jobs);

        // Update a record in JobsToJobField
        for (JobField jobField : jobFields) {
            JobsToJobField jobsToJobField = new JobsToJobField();
            jobsToJobField.setJobs(jobs);
            jobsToJobField.setJobField(jobField);
            jobsToJobFieldRepository.save(jobsToJobField);
        }

        // Update a record in JobsToJobProvince
        for (JobProvince jobProvince : jobProvinces) {
            JobsToJobProvince jobsToJobProvince = new JobsToJobProvince();
            jobsToJobProvince.setJobs(jobs);
            jobsToJobProvince.setJobProvince(jobProvince);
            jobsToJobProvinceRepository.save(jobsToJobProvince);
        }

        return JobsDtoOut.update(jobs, fieldIds, provinceIds);
    }

    @Override
    @Transactional
    public JobsDtoOut getJob(Long id) {

        // Handle not exists job's id
        Jobs jobs = jobsRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Job not found"));

        // Create a set of fields with (id, name) format for each element
        Set<JobField> jobFields = jobsToJobFieldRepository.findJobFieldByJobId(id);
        Set<JobFieldDtoOut> jobFieldsDtoOut = new HashSet<>();
        for(JobField jobField : jobFields){
            JobFieldDtoOut jobFieldDtoOut = JobFieldDtoOut.from(jobField);
            jobFieldsDtoOut.add(jobFieldDtoOut);
        }

        // Create a set of provinces with (id, name) format for each element
        Set<JobProvince> jobProvinces = jobsToJobProvinceRepository.findJobProvinceByJobId(id);
        Set<JobProvinceDtoOut> jobProvincesDtoOut = new HashSet<>();
        for(JobProvince jobProvince : jobProvinces){
            JobProvinceDtoOut jobProvinceDtoOut = JobProvinceDtoOut.from(jobProvince);
            jobProvincesDtoOut.add(jobProvinceDtoOut);
        }

        return JobsDtoOut.get(jobs, jobFieldsDtoOut, jobProvincesDtoOut);

    }

    @Override
    @Transactional
    public PageDtoOut<JobsDtoOut> getAllJobs(Long employerId, PageDtoIn pageDtoIn) {
        if (employerId == -1) {
            Page<Jobs> pageJobs = this.jobsRepository.findAll(
                    PageRequest.of(pageDtoIn.getPage(),
                    pageDtoIn.getPageSize() -1,
                    Sort.by("expiredAt").descending()
                    .and(Sort.by("employerName").ascending())));

            return PageDtoOut.from(pageDtoIn.getPage(),
                    pageDtoIn.getPageSize(),
                    pageJobs.getTotalElements(),
                    pageJobs.getTotalPages(),
                    pageJobs.stream().map(JobsDtoOut::getPage).toList());
        } else {
            Page<Jobs> pageJobsById = jobsRepository.findAllByEmployerId(employerId,
                    PageRequest.of(pageDtoIn.getPage(),
                            pageDtoIn.getPageSize() - 1,
                            Sort.by("expiredAt").descending()
                            .and(Sort.by("employerName").ascending())));

            return PageDtoOut.from(pageDtoIn.getPage(),
                    pageDtoIn.getPageSize(),
                    pageJobsById.getTotalElements(),
                    pageJobsById.getTotalPages(),
                    pageJobsById.stream().map(JobsDtoOut::getPage).toList());

        }
    }

    @Override
    @Transactional
    public boolean deleteJob(Long id) {
        Jobs jobs = jobsRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Job not found"));
        jobsRepository.delete(jobs);
        return true;
    }

}

