package vn.unigap.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.unigap.api.common.errorcode.ErrorCode;
import vn.unigap.api.common.exception.*;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.entity.JobProvince;
import vn.unigap.api.repository.EmployerRepository;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import vn.unigap.api.repository.JobProvinceRepository;


@Service
@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private final EmployerRepository employerRepository;
    private final JobProvinceRepository jobProvinceRepository;

    @Override
    public EmployerDtoOut getEmployerById(Long id) {
        Employer employer = employerRepository.findById(id)
                            .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer not found"));
        return EmployerDtoOut.from(employer);
    }

    @Override
    public EmployerDtoOut createEmployer(EmployerDtoIn employerDtoIn) {

        // Handle exists email
        if (employerRepository.findByEmail(employerDtoIn.getEmail()).isPresent()) {
            throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Email already exists");
        }

        // Handle invalid province id
        JobProvince province = jobProvinceRepository.findById((long) employerDtoIn.getProvinceId())
                .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Invalid province id"));

        // Create a new record if passing validation
        Employer employer = new Employer();
        employer.setEmail(employerDtoIn.getEmail());
        employer.setName(employerDtoIn.getName());
        employer.setProvince(province);
        employer.setCreatedAt(currentDateTime());
        employerRepository.save(employer);

        return EmployerDtoOut.from(employer);
    }

    @Override
    public EmployerDtoOut updateEmployer(Long id, EmployerDtoIn employerDtoIn) {

        // Handle invalid employer id
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer not found"));

        // Handle invalid province id
        JobProvince province = jobProvinceRepository.findById((long) employerDtoIn.getProvinceId())
                .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Invalid province id"));

        // Update a record
        employer.setName(employerDtoIn.getName());
        employer.setProvince(province);
        employer.setDescription(employerDtoIn.getDescription());
        employer.setUpdatedAt(currentDateTime());
        employerRepository.save(employer);

        return EmployerDtoOut.from(employer);
    }

    @Override
    public PageDtoOut<EmployerDtoOut> getAllEmployers(PageDtoIn pageDtoIn) {
        Page<Employer> pageEmployer = this.employerRepository.findAll(PageRequest.of(pageDtoIn.getPage() - 1, pageDtoIn.getPageSize(), Sort.by("name").ascending()));
        return PageDtoOut.from(pageDtoIn.getPage() - 1,
                            pageDtoIn.getPageSize(),
                            pageEmployer.getTotalElements(),
                            pageEmployer.getTotalPages(),
                            pageEmployer.stream().map(EmployerDtoOut::from).toList());
    }

    @Override
    public boolean deleteEmployer(Long id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer not found"));
        employerRepository.delete(employer);
        return true;
    }


    // Create a fetching local time function
    public static LocalDateTime currentDateTime() {
        return LocalDateTime.now();
    }
}
