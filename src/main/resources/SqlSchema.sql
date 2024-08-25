CREATE TABLE jobs (
                      id SERIAL PRIMARY KEY,
                      job_name VARCHAR(255) NOT NULL,
                      status VARCHAR(50) NOT NULL,
                      created_at TIMESTAMP NOT NULL,
                      updated_at TIMESTAMP NOT NULL,
                      job_type VARCHAR(50) NOT NULL
);

-- INSERT INTO jobs (job_name, status, created_at, updated_at, job_type) VALUES
--     ('Deploy Application', 'Completed', NOW(), NOW(), 'Deploy');
-- ('Build Project', 'Pending', NOW(), NOW(), 'Build'),
-- ('Run Tests', 'Running', NOW(), NOW(), 'Test'),
