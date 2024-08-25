package hit.final_devops_project;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
public class CICDJob {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String jobName;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String jobType;

    public CICDJob(String jobName, String status, LocalDateTime createdAt, LocalDateTime updatedAt, String jobType) {
        this.jobName = jobName;
        this.status = status;
        this.jobType = jobType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
