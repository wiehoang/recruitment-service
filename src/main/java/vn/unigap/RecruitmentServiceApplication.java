package vn.unigap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import vn.unigap.api.service.migration.DatabaseMigrationService;


@SpringBootApplication
public class RecruitmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentServiceApplication.class, args);
	}

//	// Only run this migration script one time
//	@Bean
//	CommandLineRunner run(DatabaseMigrationService databaseMigrationService) {
//		return args -> {
//			databaseMigrationService.migrateResumeToJobProvince();
//			databaseMigrationService.migrateResumeToJobField();
//		};
//	}
}
