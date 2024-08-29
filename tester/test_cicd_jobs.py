
import logging
import pytest
import requests

# Configure the logger
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)

BASE_URL = "http://localhost:8080/jobs"

# Create a logging utility function
def log_response(response):
    logger.info(f"Status Code: {response.status_code}")
    logger.info(f"Response Body: {response.text}")

# Define test fixtures
@pytest.fixture(scope="module")
def setup_module():
    logger.info("Setting up test module")
    yield
    logger.info("Tearing down test module")

@pytest.fixture(scope="function")
def create_job():
    job = {
        "jobName": "Test Job",
        "status": "Pending",
        "createdAt": "2024-01-01T00:00:00",
        "updatedAt": "2024-01-01T00:00:00",
        "jobType": "Build"
    }
    response = requests.post(BASE_URL, json=job)
    created_job = response.json()
    yield created_job
    requests.delete(f"{BASE_URL}/{created_job['id']}")

# Write test functions
def test_get_all_jobs(setup_module):
    logger.info("Starting test_get_all_jobs")
    response = requests.get(BASE_URL)
    log_response(response)
    assert response.status_code == 200
    logger.info("Finished test_get_all_jobs successfully")

def test_create_job(setup_module):
    logger.info("Starting test_create_job")
    job = {
        "jobName": "Test Job",
        "status": "Pending",
        "createdAt": "2024-01-01T00:00:00",
        "updatedAt": "2024-01-01T00:00:00",
        "jobType": "Build"
    }
    response = requests.post(BASE_URL, json=job)
    log_response(response)
    assert response.status_code == 200
    created_job = response.json()
    assert created_job["jobName"] == job["jobName"]
    assert created_job["status"] == job["status"]
    logger.info("Finished test_create_job successfully")

def test_get_job_by_id(setup_module, create_job):
    logger.info("Starting test_get_job_by_id")
    job_id = create_job["id"]
    response = requests.get(f"{BASE_URL}/{job_id}")
    log_response(response)
    assert response.status_code == 200
    logger.info("Finished test_get_job_by_id successfully")

def test_update_job_status(setup_module, create_job):
    logger.info("Starting test_update_job_status")
    job_id = create_job["id"]
    update_data = {"status": "Completed"}
    response = requests.put(f"{BASE_URL}/{job_id}", json=update_data)
    log_response(response)
    assert response.status_code == 200

    # Verify the update
    updated_job = response.json()
    assert updated_job["status"] == "Completed"
    assert updated_job["jobName"] == create_job["jobName"]
    assert updated_job["jobType"] == create_job["jobType"]
    logger.info("Finished test_update_job_status successfully")

def test_delete_job(setup_module, create_job):
    logger.info("Starting test_delete_job")
    job_id = create_job["id"]
    response = requests.delete(f"{BASE_URL}/{job_id}")
    log_response(response)
    assert response.status_code == 200
    logger.info("Finished test_delete_job successfully")
