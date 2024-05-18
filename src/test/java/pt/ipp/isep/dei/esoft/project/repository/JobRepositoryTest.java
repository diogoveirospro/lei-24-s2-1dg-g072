package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the JobRepository class.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class JobRepositoryTest {
    private JobRepository jobRepository;

    /**
     * Set up before each test method.
     */
    @BeforeEach
    void setUp() {
        jobRepository = new JobRepository();
    }

    /**
     * Test adding a job successfully.
     */
    @Test
    void testAddJob_Successfully() {
        Job job = new Job("AAA");
        assertDoesNotThrow(() -> jobRepository.addJob(job));
        assertTrue(jobRepository.getJobs().contains(job));
    }

    /**
     * Test adding a job throws an exception on invalid job.
     */
    @Test
    void testAddJob_ThrowsExceptionOnInvalidJob() {
        Job job = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> jobRepository.addJob(job));
        assertEquals("Job cannot be null!", exception.getMessage());
    }

    /**
     * Test adding a job throws an exception if the job already exists.
     */
    @Test
    void testAddJob_ThrowsExceptionOnAlreadyExistingJob() {
        Job job = new Job("AAA");
        jobRepository.addJob(job);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> jobRepository.addJob(job));
        assertEquals("Job already exists!", exception.getMessage());
    }

    /**
     * Test getting a job successfully.
     */
    @Test
    void testGetJob_Success() {
        Job job = new Job("AAA");
        jobRepository.addJob(job);
        Job retrievedJob = jobRepository.getJob("AAA");
        assertEquals(job, retrievedJob);
    }

    /**
     * Test getting a job throws an exception if the job does not exist.
     */
    @Test
    void testGetJob_ThrowsExceptionIfJobDoesNotExist() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> jobRepository.getJob("Nonexistent Job"));
        assertEquals("Job Nonexistent Job does not exist.", exception.getMessage());
    }

    /**
     * Test getting jobs returns an immutable list.
     */
    @Test
    void testGetJobs_ReturnsImmutableList() {
        Job job = new Job("AAA");
        jobRepository.addJob(job);
        List<Job> jobs = jobRepository.getJobs();
        assertThrows(UnsupportedOperationException.class, () -> jobs.add(new Job("Tester")));
    }

    /**
     * Test exists method returns true if the job exists.
     */
    @Test
    void testExists_ReturnsTrueIfJobExists() {
        Job job = new Job("AAA");
        jobRepository.addJob(job);
        assertTrue(jobRepository.exists("AAA"));
    }

    /**
     * Test exists method returns false if the job does not exist.
     */
    @Test
    void testExists_ReturnsFalseIfJobDoesNotExist() {
        assertFalse(jobRepository.exists("Nonexistent Job"));
    }
}
