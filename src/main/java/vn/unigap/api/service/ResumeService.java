package vn.unigap.api.service;

import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.ResumeDtoIn;
import vn.unigap.api.dto.in.UpdateResumeDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.ResumeDtoOut;


@Service
public interface ResumeService {
    ResumeDtoOut createResume(ResumeDtoIn resumeDtoIn);
    ResumeDtoOut updateResume(Long id, UpdateResumeDtoIn updateResumeDtoIn);
    ResumeDtoOut getResume(Long id);
    PageDtoOut<ResumeDtoOut> getAllResumes(Long seekerId, PageDtoIn pageDtoIn);

}
