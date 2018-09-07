package com.code.example.persistence.repositories;

import com.code.example.persistence.entities.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by veljko on 4.8.18.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
}
