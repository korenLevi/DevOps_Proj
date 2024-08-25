package hit.final_devops_project;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CICDJobServiceNestedTest {

    @Autowired
    private CICDJobService service;

    @Autowired
    private CICDJobRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Nested
    class CreateJobTests {
        @Test
        public void testAddJob() {
            CICDJob job = new CICDJob();
            job.setJobName("Test Job");
            job.setStatus("Pending");
            job.setCreatedAt(LocalDateTime.now());
            job.setUpdatedAt(LocalDateTime.now());
            job.setJobType("Build");

            CICDJob createdJob = service.createJob(job);

            assertNotNull(createdJob);
            assertEquals("Test Job", createdJob.getJobName());
        }
    }

    @Nested
    class GetJobTests {
        @Test
        public void testGetJob() {
            CICDJob job = new CICDJob();
            job.setJobName("Test Job");
            job.setStatus("Pending");
            job.setCreatedAt(LocalDateTime.now());
            job.setUpdatedAt(LocalDateTime.now());
            job.setJobType("Build");

            CICDJob savedJob = repository.save(job);

            CICDJob foundJob = service.getJobById(savedJob.getId());

            assertNotNull(foundJob);
            assertEquals("Test Job", foundJob.getJobName());
        }
    }
}

