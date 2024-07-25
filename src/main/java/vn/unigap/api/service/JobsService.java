package vn.unigap.api.service;

import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.JobsDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.UpdateJobsDtoIn;
import vn.unigap.api.dto.out.JobsDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;


@Service
public interface JobsService {
    JobsDtoOut createJob(JobsDtoIn jobsDtoIn);
    JobsDtoOut updateJob(Long id, UpdateJobsDtoIn updateJobsDtoIn);
    JobsDtoOut getJob(Long id);
    PageDtoOut<JobsDtoOut> getAllJobs(Long employerId, PageDtoIn pageDtoIn);
    boolean deleteJob(Long id);
}
