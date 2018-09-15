package com.code.example.services.impl;

import com.code.example.exceptions.NotFoundException;
import com.code.example.persistence.entities.Customer;
import com.code.example.persistence.repositories.CustomerRepository;
import com.code.example.services.CustomerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by veljko on 17.8.18.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {

    private final @NonNull
    CustomerRepository customerRepository;

    @Override
    public Set<Customer> getCustomers() {
        log.debug("Get Customers service");

        Set<Customer> customerSet = new HashSet<>();
        customerRepository.findAll().iterator().forEachRemaining(customerSet::add);
        return customerSet;

    }

    @Override
    public Set<Customer> getCustomersByUser(Long userId) {
        log.debug("Get customer by user service");

        Set<Customer> customerSet = new HashSet<>();
        customerRepository.findByUser_Id(userId).iterator().forEachRemaining(customerSet::add);
        return customerSet;
    }

    @Override
    public Customer findById(Long id) {

        Optional<Customer> customer = customerRepository.findById(id);

        if(!customer.isPresent()) {
            throw new NotFoundException("Customer not found. For ID value: " + id.toString());
        }

        return customer.get();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Customer saveCustomer = customerRepository.save(customer);

        log.debug("Saved CustomerId: " + saveCustomer.getId());

        return saveCustomer;
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
