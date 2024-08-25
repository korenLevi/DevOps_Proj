package hit.final_devops_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface CICDJobRepository extends JpaRepository<CICDJob, Long> {
    List<CICDJob> findByStatus(String status);
    List<CICDJob> findByJobType(String jobType);
    List<CICDJob> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}