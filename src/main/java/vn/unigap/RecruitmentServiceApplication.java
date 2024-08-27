package vn.unigap;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.crypto.SecretKey;
import java.security.Key;

import static com.mysql.cj.conf.PropertyKey.logger;

@Slf4j
@SpringBootApplication
@EnableCaching
public class RecruitmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentServiceApplication.class, args);
//		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//		String secretString = Encoders.BASE64.encode(key.getEncoded());
//		log.info("Secret key: " + secretString);
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
