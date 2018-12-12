package com.matrangola.indicator.controller;

import com.matrangola.indicator.IndicatorApplication;
import com.matrangola.indicator.data.model.Customer;
import com.matrangola.indicator.data.repository.CustomerRepository;
import com.matrangola.indicator.data.repository.IndicatorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IndicatorApplication.class)
@WebAppConfiguration
public class CustomerControllerTest extends ControllerTest {

    @MockBean
    private IndicatorRepository indicatorRepository;

    @MockBean
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

        customer1 = new Customer(UUID.randomUUID(), "First1", "Last1", "email@example.com", bday, 11111, null);
        Mockito.when(customerRepository.findById(customer1.getId())).thenReturn(Optional.of(customer1));
    }

    @Test
    public void testGetCustomer() throws Exception {
        mockMvc.perform(get("/customers/customer/" + customer1.getId()).contentType(JSON_CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(content().json(json(customer1)));
    }
}
