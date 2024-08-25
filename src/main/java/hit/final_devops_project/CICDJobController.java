package hit.final_devops_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class CICDJobController {

    private static final Logger logger = LoggerFactory.getLogger(CICDJobController.class);

    private final CICDJobService cicdJobService;

    public CICDJobController(CICDJobService service){
        this.cicdJobService = service;
    }

    @GetMapping
    public List<CICDJob> getAllJobs() {
        logger.info("Fetching all jobs");
        return cicdJobService.getAllJobs();
    }

    @PostMapping
    public CICDJob createJob(@RequestBody CICDJob job) {
        logger.info("Creating new job: {}", job.getJobName());
        return cicdJobService.createJob(job);
    }

    @GetMapping("/{id}")
    public CICDJob getJobById(@PathVariable Long id) {
        logger.info("Fetching job with ID: {}", id);
        return cicdJobService.getJobById(id);
    }

    @PutMapping("/{id}")
    public CICDJob updateJob(@PathVariable Long id, @RequestBody CICDJob jobDetails) {
        logger.info("Updating job with ID: {}", id);
        return cicdJobService.updateJob(id, jobDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        logger.info("Deleting job with ID: {}", id);
        cicdJobService.deleteJob(id);
    }

    @GetMapping("/status/{status}")
    public List<CICDJob> getJobsByStatus(@PathVariable String status) {
        logger.info("Fetching jobs with status: {}", status);
        return cicdJobService.getJobsByStatus(status);
    }

    @GetMapping("/jobType/{jobType}")
    public List<CICDJob> getJobsByJobType(@PathVariable String jobType) {
        logger.info("Fetching jobs with job type: {}", jobType);
        return cicdJobService.getJobsByJobType(jobType);
    }

    @GetMapping("/date-range")
    public List<CICDJob> getJobsByDateRange(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        logger.info("Fetching jobs between dates: {} and {}", start, end);
        return cicdJobService.getJobsByDateRange(start, end);
    }
}
