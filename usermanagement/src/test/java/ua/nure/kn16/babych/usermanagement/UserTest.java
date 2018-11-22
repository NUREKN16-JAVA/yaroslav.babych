package ua.nure.kn16.babych.usermanagement;


import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class UserTest extends TestCase {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "Иван";
    public static final String LAST_NAME = "Иванов";

    public static int currentDay;


    // Test if string concatenation works
    public void testGetFullName() {
        User user = new User(ID, FIRST_NAME, LAST_NAME, null);
        assertEquals("Иванов, Иван", user.getFullName());
    }

    // These tests were written in October of 2018
    // Some of them may break in the future

    // Test getAge
    // when birthday was earlier this year
    public void testAgeAfter(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.APRIL, 11);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(19, user.getAge());
    }

    // Test getAge
    // when birthday is coming later this year
    public void testAgeBefore(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.DECEMBER, 11);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(18, user.getAge());
    }

    // Test getAge
    // when birthday was earlier this month
    public void testAgeSameMonthAfter(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.OCTOBER, 3);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(19, user.getAge());
    }

    // Test getAge
    // when birthday is coming later this month
    public void testAgeSameMonthBefore(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.OCTOBER, 29);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(18, user.getAge());
    }

    public void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        currentDay = calendar.get(Calendar.DATE);
    }

    // Test getAge
    // when birthday is today
    public void testAgeSameDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.OCTOBER, currentDay);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(19, user.getAge());
    }
}