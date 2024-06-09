package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GreenSpaceRepositoryTest {

    private GreenSpaceRepository repository;
    private Collaborator mockCollaborator;
    private GreenSpace mockGreenSpace1;
    private GreenSpace mockGreenSpace2;

    @BeforeEach
    void setUp() {
        repository = new GreenSpaceRepository();
        mockCollaborator = mock(Collaborator.class);
        when(mockCollaborator.getName()).thenReturn("GSM");

        mockGreenSpace1 = mock(GreenSpace.class);
        when(mockGreenSpace1.getGreenSpaceManager()).thenReturn(mockCollaborator);
        when(mockGreenSpace1.getParkName()).thenReturn("Park1");

        mockGreenSpace2 = mock(GreenSpace.class);
        when(mockGreenSpace2.getGreenSpaceManager()).thenReturn(mockCollaborator);
        when(mockGreenSpace2.getParkName()).thenReturn("Park2");

        repository.addGreenSpace(mockGreenSpace1);
        repository.addGreenSpace(mockGreenSpace2);
    }

    /**
     * Test to check if getGreenSpaceListSorted returns a sorted list of green spaces by area.
     */
    @Test
    void testGetGreenSpaceListSortedByArea() {
        List<GreenSpace> sortedList = repository.getGreenSpaceListSorted(mockCollaborator, "area");
        assertEquals(2, sortedList.size());
        assertTrue(sortedList.contains(mockGreenSpace1));
        assertTrue(sortedList.contains(mockGreenSpace2));
    }

    /**
     * Test to check if getGreenSpaceListSorted returns a sorted list of green spaces by type.
     */
    @Test
    void testGetGreenSpaceListSortedByType() {
        List<GreenSpace> sortedList = repository.getGreenSpaceListSorted(mockCollaborator, "type");
        assertEquals(2, sortedList.size());
        assertTrue(sortedList.contains(mockGreenSpace1));
        assertTrue(sortedList.contains(mockGreenSpace2));
    }

    /**
     * Test to check if getGreenSpaceListSorted returns unsorted list when sorting option is invalid.
     */
    @Test
    void testGetGreenSpaceListSortedInvalidOption() {
        List<GreenSpace> sortedList = repository.getGreenSpaceListSorted(mockCollaborator, "invalid");
        assertEquals(2, sortedList.size());
        assertTrue(sortedList.contains(mockGreenSpace1));
        assertTrue(sortedList.contains(mockGreenSpace2));
    }

    /**
     * Test to check if getListGreenSpacesManagedByGsm returns a list of green spaces managed by the given collaborator.
     */
    @Test
    void testGetListGreenSpacesManagedByGsm() {
        List<GreenSpace> managedList = repository.getListGreenSpacesManagedByGsm(mockCollaborator);
        assertEquals(2, managedList.size());
        assertTrue(managedList.contains(mockGreenSpace1));
        assertTrue(managedList.contains(mockGreenSpace2));
    }

    /**
     * Test to check if getGreenSpaceByParkName returns the correct green space.
     */
    @Test
    void testGetGreenSpaceByParkName() {
        GreenSpace greenSpace = repository.getGreenSpaceByParkName("Park1");
        assertEquals(mockGreenSpace1, greenSpace);
    }

    /**
     * Test to check if getGreenSpaceByParkName returns null for an invalid park name.
     */
    @Test
    void testGetGreenSpaceByInvalidParkName() {
        GreenSpace greenSpace = repository.getGreenSpaceByParkName("InvalidPark");
        assertNull(greenSpace);
    }

    /**
     * Test to check if addGreenSpace adds a new green space to the repository.
     */
    @Test
    void testAddGreenSpace() {
        GreenSpace newGreenSpace = mock(GreenSpace.class);
        repository.addGreenSpace(newGreenSpace);
        assertTrue(repository.getGreenSpaceList().contains(newGreenSpace));
    }

    /**
     * Test to check if getSortMethods returns the list of sorting methods from properties.
     */
    @Test
    void testGetSortMethods() {
        Properties properties = mock(Properties.class);
        when(properties.getProperty(anyString())).thenReturn("type,area");
        List<String> sortMethods = repository.getSortMethods();
        assertEquals(2, sortMethods.size());
        assertTrue(sortMethods.contains("type"));
        assertTrue(sortMethods.contains("area"));
    }
}
