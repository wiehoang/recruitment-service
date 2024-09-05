package vn.unigap.api.service;

import static vn.unigap.common.Common.currentDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.entity.JobProvince;
import vn.unigap.api.mapper.EmployerMapper;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.api.repository.JobProvinceRepository;
import vn.unigap.common.exception.ApiException;


/** Implementation of the `EmployerService` interface. */
@Service
@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService {

  private final EmployerRepository employerRepository;
  private final JobProvinceRepository jobProvinceRepository;
  private final EmployerMapper employerMapper;

  @Override
  public EmployerDtoOut getEmployerById(Long id) {
    Employer employer = employerRepository.findById(id)
                        .orElseThrow(() -> new ApiException(
                                              HttpStatus.NOT_FOUND, "Employer not found"));
    return employerMapper.get(employer);
  }

  @Override
  public EmployerDtoOut createEmployer(EmployerDtoIn employerDtoIn) {

    // Handle exists email
    if (employerRepository.findByEmail(employerDtoIn.getEmail()).isPresent()) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "Email already exists");
    }

    // Handle invalid province id
    JobProvince province = jobProvinceRepository.findById((long) employerDtoIn.getProvinceId())
        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Invalid province id"));

    // Create a new record if passing validation
    Employer employer = new Employer();
    employer.setEmail(employerDtoIn.getEmail());
    employer.setName(employerDtoIn.getName());
    employer.setJobProvince(province);
    employer.setCreatedAt(currentDateTime());
    employerRepository.save(employer);

    return employerMapper.create(employer);
  }

  @Override
  public EmployerDtoOut updateEmployer(Long id, EmployerDtoIn employerDtoIn) {

    // Handle invalid employer id
    Employer employer = employerRepository.findById(id)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Employer not found"));

    // Handle invalid province id
    JobProvince province = jobProvinceRepository.findById((long) employerDtoIn.getProvinceId())
        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Invalid province id"));

    // Update a record
    employer.setName(employerDtoIn.getName());
    employer.setJobProvince(province);
    employer.setDescription(employerDtoIn.getDescription());
    employer.setUpdatedAt(currentDateTime());
    employerRepository.save(employer);

    return employerMapper.update(employer);
  }

  @Override
  public PageDtoOut<EmployerDtoOut> getAllEmployers(PageDtoIn pageDtoIn) {
    Page<Employer> pageEmployer = this.employerRepository.findAll(
        PageRequest.of(pageDtoIn.getPage() - 1,
        pageDtoIn.getPageSize(),
        Sort.by("name").ascending()));
    return PageDtoOut.from(pageDtoIn.getPage() - 1,
                        pageDtoIn.getPageSize(),
                        pageEmployer.getTotalElements(),
                        pageEmployer.getTotalPages(),
                        pageEmployer.stream().map(employerMapper::getPage).toList());
  }

  @Override
  public boolean deleteEmployer(Long id) {
    Employer employer = employerRepository.findById(id)
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Employer not found"));
    employerRepository.delete(employer);
    return true;
  }

}
