package com.code.example.integration;

import com.code.example.persistence.entities.Customer;
import com.code.example.persistence.repositories.CustomerRepository;
import com.code.example.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;


/**
 * Created by veljko on 18.8.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIT {

    public static final String NEW_EMAIL = "test@test.com";

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
//    @Test
    public void testSaveOfEmail() {
        Iterable<Customer> customers = customerRepository.findAll();
        Customer testCustomer = customers.iterator().next();

        testCustomer.setEmail(NEW_EMAIL);
        Customer savedCustomer = customerService.saveCustomer(testCustomer);

        assertEquals(NEW_EMAIL, savedCustomer.getEmail());
        assertEquals(testCustomer.getId(), savedCustomer.getId());

    }

}
