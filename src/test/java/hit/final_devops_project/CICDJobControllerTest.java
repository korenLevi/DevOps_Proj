package hit.final_devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CICDJobControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CICDJobRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void testGetAllJobs() {
        CICDJob job = new CICDJob();
        job.setJobName("Test Job");
        job.setStatus("Pending");
        job.setCreatedAt(LocalDateTime.now());
        job.setUpdatedAt(LocalDateTime.now());
        job.setJobType("Build");

        repository.save(job);

        ResponseEntity<CICDJob[]> response = restTemplate.getForEntity("/jobs", CICDJob[].class);
        CICDJob[] jobs = response.getBody();

        assertNotNull(jobs);
        assertEquals(1, jobs.length);
        assertEquals("Test Job", jobs[0].getJobName());
    }

    @Test
    public void testCreateJob() {
        CICDJob job = new CICDJob();
        job.setJobName("Test Job");
        job.setStatus("Pending");
        job.setCreatedAt(LocalDateTime.now());
        job.setUpdatedAt(LocalDateTime.now());
        job.setJobType("Build");

        HttpEntity<CICDJob> request = new HttpEntity<>(job);
        ResponseEntity<CICDJob> response = restTemplate.exchange("/jobs", HttpMethod.POST, request, CICDJob.class);

        assertNotNull(response.getBody());
        assertEquals("Test Job", response.getBody().getJobName());
    }
}
