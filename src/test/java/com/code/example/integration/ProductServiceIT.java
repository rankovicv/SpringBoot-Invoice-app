package com.code.example.integration;

import com.code.example.persistence.entities.Product;
import com.code.example.persistence.repositories.ProductRepository;
import com.code.example.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by veljko on 16.8.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIT {

    public static final String NEW_NAME = "New Name";

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Transactional
    @Rollback
    @Test
    public void testSaveOfName() {
        Iterable<Product> products = productRepository.findAll();
        Product testProduct = products.iterator().next();

        testProduct.setName(NEW_NAME);
        Product savedProduct = productService.saveProduct(testProduct);

        assertEquals(NEW_NAME, savedProduct.getName());
        assertEquals(testProduct.getId(), savedProduct.getId());
    }
}

