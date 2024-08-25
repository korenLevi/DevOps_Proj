package hit.final_devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CICDJobServiceParameterizedTest {

    @Autowired
    private CICDJobService service;

    @Autowired
    private CICDJobRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pending", "Completed", "Failed"})
    public void testAddJobWithVariousStatuses(String status) {
        CICDJob job = new CICDJob();
        job.setJobName("Test Job");
        job.setStatus(status);
        job.setCreatedAt(LocalDateTime.now());
        job.setUpdatedAt(LocalDateTime.now());
        job.setJobType("Build");

        CICDJob createdJob = service.createJob(job);

        assertNotNull(createdJob);
        assertEquals(status, createdJob.getStatus());
    }
}
