package ua.nure.kn16.babych.usermanagment;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class UserTest {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "Иван";
    public static final String LAST_NAME = "Иванов";

    public static int currentDay;

    @Test
    public void getFullNameTest() {
        User user = new User(ID, FIRST_NAME, LAST_NAME, null);
        assertEquals("Иванов, Иван", user.getFullName());
    }

    @Test
    public void simpleAgeTestAfter(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.APRIL, 11);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(19, user.getAge());
    }

    @Test
    public void simpleAgeTestBefore(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.DECEMBER, 11);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(18, user.getAge());
    }

    @Test
    public void ageTestSameMonthAfter(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.OCTOBER, 3);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(19, user.getAge());
    }

    @Test
    public void ageTestSameMonthBefore(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.OCTOBER, 29);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(18, user.getAge());
    }


    @Before
    public void initToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        currentDay = calendar.get(Calendar.DATE);
    }

    @Test
    public void ageTestSameDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.OCTOBER, currentDay);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(19, user.getAge());
    }
}