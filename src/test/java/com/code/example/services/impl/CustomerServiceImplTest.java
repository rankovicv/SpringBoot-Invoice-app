package com.code.example.services.impl;

import com.code.example.exceptions.NotFoundException;
import com.code.example.persistence.entities.Customer;
import com.code.example.persistence.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by veljko on 18.8.18.
 */
public class CustomerServiceImplTest {

    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void getCustomersTest() {

        Customer customer = new Customer();
        HashSet customerData = new HashSet();
        customerData.add(customer);

        when(customerService.getCustomers()).thenReturn(customerData);

        Set<Customer> customerSet = customerService.getCustomers();

        assertEquals(customerSet.size(), 1);
        verify(customerRepository, times(1)).findAll();
        verify(customerRepository, never()).findById(anyLong());
    }

    @Test
    public void findByIdTest() {

        Customer customer = new Customer();
        customer.setId(1L);
        Optional<Customer> customerOptional = Optional.of(customer);

        when(customerRepository.findById(anyLong())).thenReturn(customerOptional);

        Customer customerReturned = customerService.findById(1L);

        assertNotNull("Null pointer returned. ", customerReturned);
        verify(customerRepository, times(1)).findById(anyLong());
        verify(customerRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void findByIdTestNotFound() {

        Optional<Customer> customerOptional = Optional.empty();

        when(customerRepository.findById(anyLong())).thenReturn(customerOptional);

        Customer customer = customerService.findById(1L);
    }

    @Test
    public void deleteByIdTest() {

        Long idToDelete = Long.valueOf(2L);

        customerService.deleteById(idToDelete);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}