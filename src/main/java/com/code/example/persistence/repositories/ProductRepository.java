package com.code.example.persistence.repositories;

import com.code.example.persistence.entities.Product;
import com.code.example.persistence.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by veljko on 4.8.18.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findAllByUser_Id(@Param("id") Long id);

    Iterable<Product> findByUser_Id(@Param("id") Long id);
}
