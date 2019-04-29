package com.code.example.services;

import com.code.example.persistence.entities.Customer;

import java.util.List;
import java.util.Set;

/**
 * Created by veljko on 5.8.18.
 */
public interface CustomerService {

    Set<Customer> getCustomers();

    List<Customer> getCustomersByUser(Long userId);

    Customer findById(Long id);

    Customer saveCustomer(Customer customer);

    void deleteById(Long id);
}
