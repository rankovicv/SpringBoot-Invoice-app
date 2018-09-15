package com.code.example.persistence.repositories;

import com.code.example.persistence.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by veljko on 4.8.18.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Iterable<Customer> findByUser_Id(@Param("id") Long id);
}

