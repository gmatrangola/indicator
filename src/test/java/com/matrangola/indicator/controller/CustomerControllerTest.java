package com.matrangola.indicator.controller;

import com.matrangola.indicator.IndicatorApplication;
import com.matrangola.indicator.data.model.Customer;
import com.matrangola.indicator.data.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IndicatorApplication.class)
@WebAppConfiguration
public class CustomerControllerTest extends ControllerTest {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer1;
    private Customer customer2;

    @Before
    public void setup() {
        super.setup();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990, Calendar.JUNE, 28, 1, 0, 0);
        calendar.setTimeZone(TimeZone.getDefault());
        Date bday = new Date(calendar.getTimeInMillis());

        customer1 = customerRepository.save(new Customer(UUID.randomUUID(), "First1", "Last1", "email@example.com", bday, 11111, null));
        customer2 = customerRepository.save(new Customer(UUID.randomUUID(), "First2", "Last2", "email@example.com", bday, 11112, null));
    }

    @Test
    public void testGetCustomer() throws Exception {
        mockMvc.perform(get("/customers/customer/" + customer1.getId()).contentType(JSON_CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(content().json(json(customer1)));
    }
}
