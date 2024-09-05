package vn.unigap.api.service;

import static vn.unigap.common.Common.convertStringToSet;
import static vn.unigap.common.Common.currentDateTime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.ResumeDtoIn;
import vn.unigap.api.dto.in.UpdateResumeDtoIn;
import vn.unigap.api.dto.out.JobFieldDtoOut;
import vn.unigap.api.dto.out.JobProvinceDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.ResumeDtoOut;
import vn.unigap.api.entity.JobField;
import vn.unigap.api.entity.JobProvince;
import vn.unigap.api.entity.Resume;
import vn.unigap.api.entity.ResumeToJobField;
import vn.unigap.api.entity.ResumeToJobProvince;
import vn.unigap.api.entity.Seeker;
import vn.unigap.api.repository.JobFieldRepository;
import vn.unigap.api.repository.JobProvinceRepository;
import vn.unigap.api.repository.ResumeRepository;
import vn.unigap.api.repository.ResumeToJobFieldRepository;
import vn.unigap.api.repository.ResumeToJobProvinceRepository;
import vn.unigap.api.repository.SeekerRepository;
import vn.unigap.common.exception.ApiException;


/** Implementation of the `ResumeService` interface. */
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

  private final SeekerRepository seekerRepository;
  private final ResumeRepository resumeRepository;
  private final ResumeToJobFieldRepository resumeToJobFieldRepository;
  private final ResumeToJobProvinceRepository resumeToJobProvinceRepository;
  private final JobFieldRepository jobFieldRepository;
  private final JobProvinceRepository jobProvinceRepository;

  @Override
  @Transactional
  public ResumeDtoOut createResume(ResumeDtoIn resumeDtoIn) {

    // Handle invalid fieldId
    Set<Long> fieldIds = convertStringToSet(resumeDtoIn.getFieldIds());
    List<JobField> jobFields = jobFieldRepository.findAllById(fieldIds);
    if (jobFields.size() != fieldIds.size()) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid field id");
    }

    // Handle invalid provinceId
    Set<Long> provinceIds = convertStringToSet(resumeDtoIn.getProvinceIds());
    List<JobProvince> jobProvinces = jobProvinceRepository.findAllById(provinceIds);
    if (jobProvinces.size() != provinceIds.size()) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid province id");
    }

    // Handle invalid seekerId
    Seeker seeker = seekerRepository.findById(resumeDtoIn.getSeekerId())
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Seeker not found"));

    // Save a new record to Resume
    Resume resume = new Resume();
    resume.setSeeker(seeker);
    resume.setCareerObj(resumeDtoIn.getCareerObj());
    resume.setTitle(resumeDtoIn.getTitle());
    resume.setSalary(resumeDtoIn.getSalary());
    resume.setCreatedAt(currentDateTime());
    resume.setUpdatedAt(currentDateTime());
    resumeRepository.save(resume);

    // Save new records to ResumeToJobField
    for (JobField jobField : jobFields) {
      ResumeToJobField resumeToJobField = new ResumeToJobField();
      resumeToJobField.setResume(resume);
      resumeToJobField.setJobField(jobField);
      resumeToJobFieldRepository.save(resumeToJobField);
    }

    // Save new records to ResumeToJobProvince
    for (JobProvince jobProvince : jobProvinces) {
      ResumeToJobProvince resumeToJobProvince = new ResumeToJobProvince();
      resumeToJobProvince.setResume(resume);
      resumeToJobProvince.setJobProvince(jobProvince);
      resumeToJobProvinceRepository.save(resumeToJobProvince);
    }

    return ResumeDtoOut.save(resume, fieldIds, provinceIds);
  }

  @Override
  @Transactional
  public ResumeDtoOut updateResume(Long id, UpdateResumeDtoIn updateResumeDtoIn) {

    // Handle invalid fieldId
    Set<Long> fieldIds = convertStringToSet(updateResumeDtoIn.getFieldIds());
    List<JobField> jobFields = jobFieldRepository.findAllById(fieldIds);
    if (jobFields.size() != fieldIds.size()) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid field id");
    }

    // Handle invalid provinceId
    Set<Long> provinceIds = convertStringToSet(updateResumeDtoIn.getProvinceIds());
    List<JobProvince> jobProvinces = jobProvinceRepository.findAllById(provinceIds);
    if (jobProvinces.size() != provinceIds.size()) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid province id");
    }

    // Handle invalid resume's id
    Resume resume = resumeRepository.findById(id)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Resume not found"));

    // Update a record in Resume
    resume.setCareerObj(updateResumeDtoIn.getCareerObj());
    resume.setTitle(updateResumeDtoIn.getTitle());
    resume.setSalary(updateResumeDtoIn.getSalary());
    resume.setUpdatedAt(currentDateTime());
    resumeRepository.save(resume);

    // Update records in ResumeToJobField
    for (JobField jobField : jobFields) {
      ResumeToJobField resumeToJobField = new ResumeToJobField();
      resumeToJobField.setResume(resume);
      resumeToJobField.setJobField(jobField);
      resumeToJobFieldRepository.save(resumeToJobField);
    }

    // Save new records to ResumeToJobProvince
    for (JobProvince jobProvince : jobProvinces) {
      ResumeToJobProvince resumeToJobProvince = new ResumeToJobProvince();
      resumeToJobProvince.setResume(resume);
      resumeToJobProvince.setJobProvince(jobProvince);
      resumeToJobProvinceRepository.save(resumeToJobProvince);
    }

    return ResumeDtoOut.save(resume, fieldIds, provinceIds);
  }

  @Override
  @Transactional
  public ResumeDtoOut getResume(Long id) {

    // Create a set of fields with (id, name) format for each element
    Set<JobField> jobFields = resumeToJobFieldRepository.findJobFieldByResumeId(id);
    Set<JobFieldDtoOut> jobFieldDtoOuts = new HashSet<>();
    if (!jobFields.isEmpty()) { // Continue converting each field to (id, name) format if exist
      for (JobField jobField : jobFields) {
        JobFieldDtoOut jobFieldDtoOut = JobFieldDtoOut.from(jobField);
        jobFieldDtoOuts.add(jobFieldDtoOut);
      }
    }

    // Create a set of provinces with (id, name) format for each element
    Set<JobProvince> jobProvinces = resumeToJobProvinceRepository.findJobProvinceByResumeId(id);
    Set<JobProvinceDtoOut> jobProvinceDtoOuts = new HashSet<>();
    if (!jobProvinces.isEmpty()) { // Converting each province to (id, name) format if exist
      for (JobProvince jobProvince : jobProvinces) {
        JobProvinceDtoOut jobProvinceDtoOut = JobProvinceDtoOut.from(jobProvince);
        jobProvinceDtoOuts.add(jobProvinceDtoOut);
      }
    }

    // Handle invalid resume's id
    Resume resume = resumeRepository.findById(id)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Resume not found"));

    return ResumeDtoOut.get(resume, jobFieldDtoOuts, jobProvinceDtoOuts);
  }

  @Override
  @Transactional
  public PageDtoOut<ResumeDtoOut> getAllResumes(Long seekerId, PageDtoIn pageDtoIn) {

    if (seekerId == -1) {
      Page<Resume> pageResume = resumeRepository.findAll(PageRequest.of(pageDtoIn.getPage(),
              pageDtoIn.getPageSize() - 1,
              Sort.by("title").and(Sort.by("seekerName"))));
      return PageDtoOut.from(pageDtoIn.getPage(),
              pageDtoIn.getPageSize() - 1,
              pageResume.getTotalElements(),
              pageResume.getTotalPages(),
              pageResume.stream().map(ResumeDtoOut::getPage).toList());
    }

    Page<Resume> pageResumeById = resumeRepository.findAllBySeekerId(seekerId,
            PageRequest.of(pageDtoIn.getPage(),
                  pageDtoIn.getPageSize() - 1,
                           Sort.by("title").and(Sort.by("seekerName"))));
    return PageDtoOut.from(pageDtoIn.getPage(),
            pageDtoIn.getPageSize() - 1,
            pageResumeById.getTotalElements(),
            pageResumeById.getTotalPages(),
            pageResumeById.stream().map(ResumeDtoOut::getPage).toList());
  }

  @Override
  @Transactional
  public boolean deleteResume(Long id) {

    // Handle invalid resume's id
    Resume resume = resumeRepository.findById(id)
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Resume not found"));

    resumeRepository.delete(resume);

    return true;

  }
}
