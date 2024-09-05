package vn.unigap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * The main class and the entry point of the Recruitment Service application.
 * The application is configured to start at @SpringBootApplication
 * and enable caching through @EnableCaching annotation.
 */
@SpringBootApplication
@EnableCaching
public class RecruitmentServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(RecruitmentServiceApplication.class, args);
  }

}