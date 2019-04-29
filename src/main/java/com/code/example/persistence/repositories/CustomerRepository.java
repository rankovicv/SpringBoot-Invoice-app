package com.code.example.persistence.repositories;

import com.code.example.persistence.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by veljko on 4.8.18.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByUser_Id(@Param("id") Long id);

}

