package vn.unigap.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.entity.JobProvince;
import vn.unigap.api.mapper.EmployerMapper;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.api.repository.JobProvinceRepository;


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

  // Test invalid employer id exception
  @Test
  void whenEmployerIdInvalid_thenThrowApiException() {

    Long invalidEmployerId = 3L;
    when(employerRepository.findById(any(Long.class)))
            .thenReturn(Optional.empty());

    assertThatThrownBy(() -> employerService
            .updateEmployer(invalidEmployerId, createEmployerDtoIn()))
            .isInstanceOf(RuntimeException.class);
    verify(employerRepository, never()).save(any(Employer.class));

  }

  // Test full path when getting an employer record
  @Test
  void whenGet_thenReturnDtoOut() {

    Optional<Employer> optionalEmployer = Optional.of(createEmployer());

    when(employerRepository.findById(1L))
            .thenReturn(optionalEmployer);

    EmployerDtoOut getEmployer = employerService.getEmployerById(1L);
    assertThat(getEmployer).isNotNull();
    verify(employerRepository, times(1)).findById(1L);

  }

  // Test exist email exception
  @Test
  void whenEmailExistsAlready_thenThrowApiException() {

    Optional<Employer> optionalEmployer = Optional.of(createEmployer());

    when(employerRepository.findByEmail("mockemployer@gmail.com"))
            .thenReturn(optionalEmployer);

    assertThatThrownBy(() -> employerService.createEmployer(createEmployerDtoIn()))
            .isInstanceOf(RuntimeException.class);
    verify(employerRepository, never()).save(any(Employer.class));

  }

  // Test invalid province id exception
  @Test
  void whenProvinceIdInvalid_thenThrowApiException() {

    Long invalidProvinceId = 3L;

    when(employerRepository.findByEmail("mockemployer@gmail.com"))
            .thenReturn(Optional.empty());
    when(jobProvinceRepository.findById(invalidProvinceId))
            .thenReturn(Optional.empty());

    assertThatThrownBy(() -> employerService.createEmployer(createEmployerDtoIn()))
            .isInstanceOf(RuntimeException.class);
    verify(employerRepository, never()).save(any(Employer.class));

  }

  // Test full path when creating an employer
  @Test
  void whenCreate_thenReturnDtoOut() {

    Optional<JobProvince> optionalJobProvince = Optional.of(createJobProvince());

    when(employerRepository.findByEmail("mockemployer@gmail.com"))
            .thenReturn(Optional.empty());
    when(jobProvinceRepository.findById(1L))
            .thenReturn(optionalJobProvince);
    when(employerRepository.save(any(Employer.class)))
            .thenReturn(createEmployer());

    EmployerDtoOut createEmployer = employerService.createEmployer(createEmployerDtoIn());
    assertThat(createEmployer).isNotNull();
    verify(employerRepository, times(1)).save(any(Employer.class));

  }

  // Test full path when updating employer
  @Test
  void whenUpdate_thenReturnDtoOut() {

    Optional<Employer> optionalEmployer = Optional.of(createEmployer());
    Optional<JobProvince> optionalJobProvince = Optional.of(createJobProvince());

    when(employerRepository.findById(1L))
            .thenReturn(optionalEmployer);
    when(jobProvinceRepository.findById(1L))
            .thenReturn(optionalJobProvince);
    when(employerRepository.save(any(Employer.class)))
            .thenReturn(createEmployer());

    EmployerDtoOut updateEmployer = employerService.updateEmployer(1L, createEmployerDtoIn());
    assertThat(updateEmployer).isNotNull();
    verify(employerRepository, times(1)).save(any(Employer.class));

  }

  @Test
  void whenGetAll_thenReturnPageDtoOut() {

    List<Employer> employers = createEmployers();
    Page<Employer> pageEmployer = new PageImpl<>(employers);

    when(employerRepository.findAll(any(Pageable.class)))
            .thenReturn(pageEmployer);

    PageDtoOut<EmployerDtoOut> getAllEmployer = employerService.getAllEmployers(createPageDtoIn());
    assertThat(getAllEmployer.getData().size()).isEqualTo(employers.size());
    verify(employerRepository, times(1)).findAll(any(Pageable.class));

  }

  @Test
  void whenDelete_thenReturnTrue() {

    Optional<Employer> optionalEmployer = Optional.of(createEmployer());

    when(employerRepository.findById(any(Long.class)))
            .thenReturn(optionalEmployer);

    boolean deleteEmployer = employerService.deleteEmployer(1L);
    assertThat(deleteEmployer).isTrue();
    verify(employerRepository, times(1)).delete(any(Employer.class));

  }


  private Employer createEmployer() {
    Employer employer = new Employer();
    employer.setId(1L);
    employer.setEmail("mockemployer@gmail.com");
    employer.setName("Mock Employer");
    employer.setJobProvince(createJobProvince());
    employer.setDescription("Mock");
    return employer;
  }

  private List<Employer> createEmployers() {
    List<Employer> employers = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      employers.add(createEmployer());
    }
    return employers;
  }

  private JobProvince createJobProvince() {
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

  private PageDtoIn createPageDtoIn() {
    return new PageDtoIn(1, 10);
  }
}