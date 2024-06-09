package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Calendar;

public class Date implements Comparable<Date>, Serializable {

    /**
     * Date year
     */
    private int year;

    /**
     * Date month
     */
    private int month;

    /**
     * Date day
     */
    private int day;

    /**
     * Default year
     */
    private static final int DEFAULT_YEAR = 1;

    /**
     * Default month
     */
    private static final int DEFAULT_MONTH = 1;

    /**
     * Default day.
     */
    private static final int DEFAULT_DAY = 1;

    /**
     * Number of days of the month
     */
    private static final int[] monthDay = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30,
            31, 30, 31};

    /**
     * Names of the months of the year.
     */
    private static final String[] monthName = {"Invalid", "January", "February",
            "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December"};

    /**
     * Creates an instance of Date receiving the year, month and day.
     *
     * @param year date year.
     * @param month date month.
     * @param day date day.
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Creates an instance of Date with default values.
     */
    public Date() {
        year = DEFAULT_YEAR;
        month = DEFAULT_MONTH;
        day = DEFAULT_DAY;
    }

    /**
     * Creates an instance of Date with the same characteristics of the date received by parameter.
     *
     * @param otherDate date with characteristics to copy.
     */
    public Date(Date otherDate) {
        year = otherDate.year;
        month = otherDate.month;
        day = otherDate.day;
    }

    /**
     * Returns the date year.
     *
     * @return date year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the date month.
     *
     * @return date month.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns the date day.
     *
     * @return date day.
     */
    public int getDay() {
        return day;
    }

    /**
     * Changes the date year, month and day.
     *
     * @param year the new date year.
     * @param month the new date month,
     * @param day the new date day.
     */
    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Returns the date in format:%04d/%02d/%02d.
     *
     * @return date characteristics.
     */
    public String toYearMonthDayString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    /**
     * Compare the date with the received object.
     *
     * @param otherObject the object to compare with date.
     * @return true if received object represents a equivalent date to date, else returns false
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Date otherDate = (Date) otherObject;
        return year == otherDate.year && month == otherDate.month
                && day == otherDate.day;
    }

    /**
     * Compares the date with another received by parameter.
     *
     * @param otherDate date to be compared.
     * @return value 0 if otherDate received is equal to date; value -1 if
     * otherDate is later than date; value 1 if otherDate is before the date
     */
    @Override
    public int compareTo(Date otherDate) {
        return (otherDate.isGreater(this)) ? -1 : (isGreater(otherDate)) ? 1 : 0;
    }

    /**
     * Returns true if the date is greater than the date received by parameter. If the date is less than or equal to
     * the date received by parameter, returns false.
     *
     * @param otherDate the other date to compare the date with.
     * @return true if the date is greater than the date received by parameter, otherwise return false.
     */
    public boolean isGreater(Date otherDate) {

        if (otherDate == null) {
            return false;
        }
        int totalDays = countDays();
        int totalDays1 = otherDate.countDays();

        return totalDays > totalDays1;
    }

    /**
     * Returns the difference in number of days between the date and the date received by parameter
     *
     * @param otherDate the other date that will be compared to the date to calculate the difference
     *                 in number of days.
     * @return difference in number of days between date and the date received by parameter
     */
    public int difference(Date otherDate) {
        int totalDays = countDays();
        int totalDays1 = otherDate.countDays();

        return totalDays - totalDays1;
    }

    /**
     * Return true if the year received by parameter is a leap year. If the year is not a leap year, return false.
     *
     * @param year the year to be validated.
     * @return true if the year received by parameter is a leap year, if not return false
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * Returns the current system date.
     *
     * @return the current system date.
     */
    public static Date currentDate() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1;    // january is represented by 0
        int day = today.get(Calendar.DAY_OF_MONTH);
        return new Date(year, month, day);
    }

    /**
     * Return the number of days since 1/1/1 till date.
     *
     * @return number of days since 1/1/1 till date.
     */
    private int countDays() {
        int totalDays = 0;

        for (int i = 1; i < year; i++) {
            totalDays += isLeapYear(i) ? 366 : 365;
        }
        for (int i = 1; i < month; i++) {
            totalDays += monthDay[i];
        }
        totalDays += (isLeapYear(year) && month > 2) ? 1 : 0;
        totalDays += day;

        return totalDays;
    }

    /**
     * Returns a string representation of the date in the format "dd/MM/yyyy".
     *
     * @return a string representation of the date
     */

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }
}

