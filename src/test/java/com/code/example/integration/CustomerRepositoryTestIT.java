package com.code.example.integration;

import com.code.example.persistence.entities.Product;
import com.code.example.persistence.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Set;


/**
 * Created by veljko on 5.8.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTestIT {

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void test() {
        Iterable<Product> p = productRepository.findAll();
        System.out.println("debaguj me");
    }
}


