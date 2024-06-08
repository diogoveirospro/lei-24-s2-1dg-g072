package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testDefaultConstructor() {
        Date date = new Date();
        assertEquals(1, date.getYear());
        assertEquals(1, date.getMonth());
        assertEquals(1, date.getDay());
    }

    @Test
    void testParameterizedConstructor() {
        Date date = new Date(2023, 6, 8);
        assertEquals(2023, date.getYear());
        assertEquals(6, date.getMonth());
        assertEquals(8, date.getDay());
    }

    @Test
    void testCopyConstructor() {
        Date date1 = new Date(2023, 6, 8);
        Date date2 = new Date(date1);
        assertEquals(2023, date2.getYear());
        assertEquals(6, date2.getMonth());
        assertEquals(8, date2.getDay());
    }

    @Test
    void testSetDate() {
        Date date = new Date();
        date.setDate(2023, 6, 8);
        assertEquals(2023, date.getYear());
        assertEquals(6, date.getMonth());
        assertEquals(8, date.getDay());
    }

    @Test
    void testToYearMonthDayString() {
        Date date = new Date(2023, 6, 8);
        assertEquals("2023-06-08", date.toYearMonthDayString());
    }

    @Test
    void testEquals() {
        Date date1 = new Date(2023, 6, 8);
        Date date2 = new Date(2023, 6, 8);
        Date date3 = new Date(2024, 7, 9);
        assertEquals(date1, date2);
        assertNotEquals(date1, date3);
    }

    @Test
    void testCompareTo() {
        Date date1 = new Date(2023, 6, 8);
        Date date2 = new Date(2023, 6, 8);
        Date date3 = new Date(2024, 7, 9);
        assertEquals(0, date1.compareTo(date2));
        assertEquals(-1, date1.compareTo(date3));
        assertEquals(1, date3.compareTo(date1));
    }

    @Test
    void testIsGreater() {
        Date date1 = new Date(2023, 6, 8);
        Date date2 = new Date(2024, 7, 9);
        assertTrue(date2.isGreater(date1));
        assertFalse(date1.isGreater(date2));
    }

    @Test
    void testDifference() {
        Date date1 = new Date(2023, 6, 8);
        Date date2 = new Date(2023, 6, 10);
        assertEquals(2, date2.difference(date1));
        assertEquals(-2, date1.difference(date2));
    }

    @Test
    void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2020));
        assertFalse(Date.isLeapYear(2021));
        assertTrue(Date.isLeapYear(2000));
        assertFalse(Date.isLeapYear(1900));
    }

    @Test
    void testCurrentDate() {
        Date currentDate = Date.currentDate();
        Calendar today = Calendar.getInstance();
        assertEquals(today.get(Calendar.YEAR), currentDate.getYear());
        assertEquals(today.get(Calendar.MONTH) + 1, currentDate.getMonth());
        assertEquals(today.get(Calendar.DAY_OF_MONTH), currentDate.getDay());
    }

    @Test
    void testToString() {
        Date date = new Date(2023, 6, 8);
        assertEquals("08/06/2023", date.toString());
    }
}