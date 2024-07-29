package vn.unigap.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.unigap.api.common.errorcode.ErrorCode;
import vn.unigap.api.common.exception.ApiException;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.SeekerDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.SeekerDtoOut;
import vn.unigap.api.entity.JobProvince;
import vn.unigap.api.entity.Seeker;
import vn.unigap.api.mapper.SeekerMapper;
import vn.unigap.api.repository.JobProvinceRepository;
import vn.unigap.api.repository.SeekerRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class SeekerServiceImpl implements SeekerService {

    private final SeekerRepository seekerRepository;
    private final JobProvinceRepository jobProvinceRepository;
    private final SeekerMapper seekerMapper;

    @Override
    @Transactional
    public SeekerDtoOut createSeeker(SeekerDtoIn seekerDtoIn) {

        // Handle invalid province's id
        JobProvince jobProvince = jobProvinceRepository.findById((long) seekerDtoIn.getProvince())
                .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Invalid province's id"));

        // Save a new record
        Seeker seeker = new Seeker();
        seeker.setName(seekerDtoIn.getName());
        seeker.setBirthday(formatBirthday(seekerDtoIn.getBirthday()));
        seeker.setAddress(seekerDtoIn.getAddress());
        seeker.setJobProvince(jobProvince);
        seeker.setCreatedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        seeker.setUpdatedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        seekerRepository.save(seeker);

        return seekerMapper.create(seeker);
    }

    @Override
    @Transactional
    public SeekerDtoOut updateSeeker(Long id, SeekerDtoIn seekerDtoIn) {

        // Handle invalid seeker's id
        Seeker seeker = seekerRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Seeker not found"));

        // Handle invalid province's id
        JobProvince jobProvince = jobProvinceRepository.findById((long) seekerDtoIn.getProvince())
                .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Invalid province's id"));

        // Update the record
        seeker.setName(seekerDtoIn.getName());
        seeker.setBirthday(formatBirthday(seekerDtoIn.getBirthday()));
        seeker.setAddress(seekerDtoIn.getAddress());
        seeker.setJobProvince(jobProvince);
        seeker.setUpdatedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        seekerRepository.save(seeker);

        return seekerMapper.update(seeker);
    }

    @Override
    @Transactional
    public SeekerDtoOut getSeeker(Long id) {

        // Handle invalid seeker's id
        Seeker seeker = seekerRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Seeker not found"));

        return seekerMapper.get(seeker);
    }

    @Override
    @Transactional
    public PageDtoOut<SeekerDtoOut> getAllSeekers(Integer jobProvinceId, PageDtoIn pageDtoIn) {

        if (jobProvinceId == -1) {
            Page<Seeker> pageSeeker = seekerRepository.findAll(PageRequest.of(pageDtoIn.getPage(),
                    pageDtoIn.getPageSize() - 1,
                    Sort.by("name").ascending()));

            return PageDtoOut.from(pageDtoIn.getPage(),
                    pageDtoIn.getPageSize(),
                    pageSeeker.getTotalElements(),
                    pageSeeker.getTotalPages(),
                    pageSeeker.stream().map(seekerMapper::get).toList());
        } else {
            Page<Seeker> pageSeekerByProvince = seekerRepository.findAllByJobProvinceId(jobProvinceId, PageRequest.of(pageDtoIn.getPage(),
                    pageDtoIn.getPageSize() - 1,
                    Sort.by("name").ascending()));

            return PageDtoOut.from(pageDtoIn.getPage(),
                    pageDtoIn.getPageSize(),
                    pageSeekerByProvince.getTotalElements(),
                    pageSeekerByProvince.getTotalPages(),
                    pageSeekerByProvince.stream().map(seekerMapper::get).toList());
        }
    }
    @Override
    @Transactional
    public boolean deleteSeeker(Long id) {
        Seeker seeker = seekerRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Seeker not found"));
        seekerRepository.delete(seeker);
        return true;
    }

    public static String formatBirthday(Date birthday) {
        LocalDate localDateBirthday = birthday.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(localDateBirthday);
    }
}