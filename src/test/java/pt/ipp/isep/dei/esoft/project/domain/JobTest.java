package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import static org.junit.jupiter.api.Assertions.*;

public class JobTest {

    @Test
    public void testGetName() {
        Job job = new Job("AAA");
        assertEquals("AAA", job.getName());
    }

    @Test
    public void testSetName() {
        Job job = new Job("AAA");
        job.setName("AA");
        assertEquals("AA", job.getName());
    }

    @Test
    public void testEquals() {
        Job job1 = new Job("AAA");
        Job job2 = new Job("AAA");
        Job job3 = new Job("AA");

        assertTrue(job1.equals(job2));
        assertFalse(job1.equals(job3));
    }

    @Test
    public void testToString() {
        Job job = new Job("AAA");
        assertEquals("AAA", job.toString());
    }

    @Test
    public void testSetNameNull() {
        assertThrows(IllegalArgumentException.class, () -> new Job(null));
    }

    @Test
    public void testSetNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Job(""));
    }

    @Test
    public void testSetNameWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> new Job("   "));
    }
}
