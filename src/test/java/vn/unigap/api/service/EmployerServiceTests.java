package vn.unigap.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.entity.JobProvince;
import vn.unigap.api.mapper.EmployerMapper;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.api.repository.JobProvinceRepository;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmployerServiceTests {

    @Mock
    private EmployerRepository employerRepository;

    @Mock
    private JobProvinceRepository jobProvinceRepository;

    @Spy
    private EmployerMapper employerMapper = Mappers.getMapper(EmployerMapper.class);

    @InjectMocks
    private EmployerServiceImpl employerService;

    // Test exist email exception
    @Test
    void whenEmailExistsAlready_thenThrowApiException() {

        Optional<Employer> optionalEmployer = Optional.of(createEmployerEntity());

        when(employerRepository.findByEmail("mockemployer@gmail.com"))
                .thenReturn(optionalEmployer);

        assertThatThrownBy(() -> employerService.createEmployer(createEmployerDtoIn()))
                .isInstanceOf(RuntimeException.class);

        verify(employerRepository, never()).save(any(Employer.class));
    }

    // Test invalid province id exception
    @Test
    void whenProvinceIdInvalid_thenThrowApiException() {

        when(employerRepository.findByEmail("mockemployer@gmail.com"))
                .thenReturn(Optional.empty());
        when(jobProvinceRepository.findById((long) 3))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> employerService.createEmployer(createEmployerDtoIn()))
                .isInstanceOf(RuntimeException.class);

        verify(employerRepository, never()).save(any(Employer.class));
    }

    // Test full path when creating an employer
    @Test
    void whenCreate_thenReturnDtoOut() {

        Optional<JobProvince> optionalJobProvince = Optional.of(createJobProvinceEntity());

        when(employerRepository.findByEmail("mockemployer@gmail.com"))
                .thenReturn(Optional.empty());
        when(jobProvinceRepository.findById((long) 1))
                .thenReturn(optionalJobProvince);
        when(employerRepository.save(any(Employer.class)))
                .thenReturn(createEmployerEntity());

        EmployerDtoOut actualEmployer = employerService.createEmployer(createEmployerDtoIn());

        assertThat(actualEmployer).isNotNull();

        verify(employerRepository).save(any(Employer.class));
    }

//    @Test
//    void updateEmployer() {
//    }
//
//    @Test
//    void getEmployerById() {
//    }
//
//    @Test
//    void getAllEmployers() {
//    }
//
//    @Test
//    void deleteEmployer() {
//    }


    private Employer createEmployerEntity() {
        Employer employer = new Employer();
        employer.setId(1L);
        employer.setEmail("mockemployer@gmail.com");
        employer.setName("Mock Employer");
        employer.setJobProvince(createJobProvinceEntity());
        employer.setDescription("Mock");
        return employer;
    }

    private JobProvince createJobProvinceEntity() {
        JobProvince jobProvince = new JobProvince();
        jobProvince.setId(1);
        jobProvince.setName("Mock Province");
        return jobProvince;
    }

    private EmployerDtoIn createEmployerDtoIn() {
        EmployerDtoIn employerDtoIn = new EmployerDtoIn();
        employerDtoIn.setEmail("mockemployer@gmail.com");
        employerDtoIn.setName("Mock Employer");
        employerDtoIn.setProvinceId(1);
        employerDtoIn.setDescription("Mock");
        return employerDtoIn;
    }
}