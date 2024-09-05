package vn.unigap;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.unigap.api.controller.EmployerController;
import vn.unigap.api.controller.JobsController;
import vn.unigap.api.controller.ResumeController;
import vn.unigap.api.controller.SeekerController;

@SpringBootTest
class RecruitmentServiceApplicationTests {

  @Autowired
  private EmployerController employerController;

  @Autowired
  private JobsController jobsController;

  @Autowired
  private SeekerController seekerController;

  @Autowired
  private ResumeController resumeController;

  @Test
  void contextLoads() throws Exception {
    Assertions.assertThat(employerController).isNotNull();
    Assertions.assertThat(jobsController).isNotNull();
    Assertions.assertThat(seekerController).isNotNull();
    Assertions.assertThat(resumeController).isNotNull();
  }

}
