package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class JobRepositoryTest {
    private JobRepository jobRepository;
    @BeforeEach
    void setUp() {
        jobRepository = new JobRepository();
    }

    @Test
    void testAddJob_Successfully() {
        Job job = new Job("AAA");
        assertDoesNotThrow(() -> jobRepository.addJob(job));
        assertTrue(jobRepository.getJobs().contains(job));
    }

    @Test
    void testAddJob_ThrowsExceptionOnInvalidJob() {
        Job job = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> jobRepository.addJob(job));
        assertEquals("Job cannot be null!", exception.getMessage());
    }

    @Test
    void testGetJob_Success() {
        Job job = new Job("AAA");
        jobRepository.addJob(job);
        Job retrievedJob = jobRepository.getJob("AAA");
        assertEquals(job, retrievedJob);
    }

    @Test
    void testGetJob_ThrowsExceptionIfJobDoesNotExist() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> jobRepository.getJob("Nonexistent Job"));
        assertEquals("Job Nonexistent Job does not exist.", exception.getMessage());
    }

    @Test
    void testGetJobs_ReturnsImmutableList() {
        Job job = new Job("AAA");
        jobRepository.addJob(job);
        List<Job> jobs = jobRepository.getJobs();
        assertThrows(UnsupportedOperationException.class, () -> jobs.add(new Job("Tester")));
    }

    @Test
    void testExists_ReturnsTrueIfJobExists() {
        Job job = new Job("AAA");
        jobRepository.addJob(job);
        assertTrue(jobRepository.exists("AAA"));
    }

    @Test
    void testExists_ReturnsFalseIfJobDoesNotExist() {
        assertFalse(jobRepository.exists("Nonexistent Job"));
    }
}
