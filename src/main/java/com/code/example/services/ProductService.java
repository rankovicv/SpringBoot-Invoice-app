package com.code.example.services;

import com.code.example.persistence.entities.Product;

import java.util.Set;

/**
 * Created by veljko on 5.8.18.
 */
public interface ProductService {

    Set<Product> getProducts();

    Product findById(Long id);

    Product saveProduct(Product product);

    void deleteById(Long id);
}
