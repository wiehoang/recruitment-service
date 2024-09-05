package vn.unigap.api.service;

import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.SeekerDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.SeekerDtoOut;


/** Service interface for managing seeker-related operations. */
@Service
public interface SeekerService {

  SeekerDtoOut createSeeker(SeekerDtoIn seekerDtoIn);

  SeekerDtoOut updateSeeker(Long id, SeekerDtoIn seekerDtoIn);

  SeekerDtoOut getSeeker(Long id);

  PageDtoOut<SeekerDtoOut> getAllSeekers(Integer provinceId, PageDtoIn pageDtoIn);

  boolean deleteSeeker(Long id);

}
