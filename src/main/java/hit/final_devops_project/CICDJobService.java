package hit.final_devops_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CICDJobService {
    private static final Logger logger = LoggerFactory.getLogger(CICDJobController.class);
    private final CICDJobRepository cicdJobRepository;

    public CICDJobService(CICDJobRepository cicdJobRepository) {
        this.cicdJobRepository = cicdJobRepository;
    }

    public List<CICDJob> getAllJobs() {
        return cicdJobRepository.findAll();
    }

    public CICDJob getJobById(Long id) {
        return cicdJobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public CICDJob createJob(CICDJob job) {
        return cicdJobRepository.save(job);
    }

    public List<CICDJob> getJobsByStatus(String status) {
        return cicdJobRepository.findByStatus(status);
    }

    public List<CICDJob> getJobsByJobType(String jobType) {
        return cicdJobRepository.findByJobType(jobType);
    }

    public List<CICDJob> getJobsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return cicdJobRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public CICDJob updateJob(Long id, CICDJob jobDetails) {
        CICDJob job = cicdJobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
        logger.info("getJobName",jobDetails.getJobName());
        if (jobDetails.getJobName() != null) {
            job.setJobName(jobDetails.getJobName());
        }
        if (jobDetails.getStatus() != null) {
            job.setStatus(jobDetails.getStatus());
        }
        if (jobDetails.getUpdatedAt() != null) {
            job.setUpdatedAt(jobDetails.getUpdatedAt());
        }
        if (jobDetails.getJobType() != null) {
            job.setJobType(jobDetails.getJobType());
        }

        return cicdJobRepository.save(job);
    }

    public void deleteJob(Long id) {
        CICDJob job = cicdJobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
        cicdJobRepository.deleteById(id);
    }
}
