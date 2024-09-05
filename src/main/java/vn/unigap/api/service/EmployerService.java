package vn.unigap.api.service;

import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;


/** Service interface for managing employer-related operations. */
@Service
public interface EmployerService {

  EmployerDtoOut getEmployerById(Long id);

  EmployerDtoOut createEmployer(EmployerDtoIn employerDtoIn);

  EmployerDtoOut updateEmployer(Long id, EmployerDtoIn employerDtoIn);

  PageDtoOut<EmployerDtoOut> getAllEmployers(PageDtoIn pageDtoIn);

  boolean deleteEmployer(Long id);

}

