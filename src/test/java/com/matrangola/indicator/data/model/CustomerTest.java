package com.matrangola.indicator.data.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer customer;
    private Calendar cal;

    @Before
    public void setUp() throws Exception {
        cal = Calendar.getInstance();
        cal.set(1999, Calendar.OCTOBER, 28);
        customer = new Customer(UUID.randomUUID(), "First", "Last", "test@example.com", cal.getTime(), 21044);
//                UUID.randomUUID(), "First", "Last", 21044,
//                "test@example.com", cal.getTime());
    }

    @Test
    public void getFirstName() {
        assertEquals("First", customer.getFirstName());
    }

    @Test
    public void setFirstName() {
        Customer fn = new Customer();
        fn.setFirstName("first2");
        assertEquals("first2", fn.getFirstName());
    }

    @Test
    public void getLastName() {
        assertEquals("Last", customer.getLastName());
    }

    @Test
    public void setLastName() {
        Customer ln = new Customer();
        ln.setLastName("last2");
        assertEquals("last2", ln.getLastName());
    }

    @Test
    public void getBirthday() {
        assertEquals(cal.getTime(), customer.getBirthday());
    }

    @Test
    public void setBirthday() {
        Date date = new Date();
        Customer bd = new Customer();
        bd.setBirthday(date);
        assertEquals(date, bd.getBirthday());
    }
}