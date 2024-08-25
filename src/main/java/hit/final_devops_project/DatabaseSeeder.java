package hit.final_devops_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;


@Configuration
public class DatabaseSeeder {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    @Bean
    CommandLineRunner initDatabase(CICDJobRepository CICDJobRepository) {
        return args -> {
            logger.info("Initializing database...");
            logger.info("Creating CI/CD Jobs...");
            CICDJob job1 = new CICDJob("Job1", "Pending", LocalDateTime.now(), LocalDateTime.now(), "Build");
            CICDJob job2 = new CICDJob("Job2", "Completed", LocalDateTime.now(), LocalDateTime.now(), "Test");
            CICDJob job3 = new CICDJob("Job3", "Failed", LocalDateTime.now(), LocalDateTime.now(), "Deploy");

            CICDJobRepository.save(job1);
            logger.info("Create CI/CD Job: {}",job1);

            CICDJobRepository.save(job2);
            logger.info("Create CI/CD Job: {}",job2);

            CICDJobRepository.save(job3);
            logger.info("Create CI/CD Job: {}",job3);


        };
    }
}
