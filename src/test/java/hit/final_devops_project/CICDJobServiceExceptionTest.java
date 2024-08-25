package hit.final_devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CICDJobServiceExceptionTest {

    @Autowired
    private CICDJobService service;

    @Autowired
    private CICDJobRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void testDeleteNonExistentJob() {
        Long jobId = 1L;

        assertThrows(RuntimeException.class, () -> service.deleteJob(jobId));
    }

    @Test
    public void testUpdateJobWithInvalidData() {
        Long jobId = 1L;
        CICDJob jobDetails = new CICDJob();
        jobDetails.setStatus("Completed");

        assertThrows(RuntimeException.class, () -> service.updateJob(jobId, jobDetails));
    }
}
