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
//        Product product1 = new Product();
//        product1.setName("Xiaomi MiBand 2");
//        product1.setPrice(18.25);
//        product1.setQuantity(105);
//        productRepository.save(product1);
//
//        Product product2 = new Product();
//        product2.setName("H4 Swimming Fitness Bracelet");
//        product2.setPrice(18.12);
//        product2.setQuantity(10);
//        productRepository.save(product2);
//
//        Product product3 = new Product();
//        product3.setName("Lungfish High-speed FLAT HDMI cable 30cm");
//        product3.setPrice(0.78);
//        product3.setQuantity(997);
//        productRepository.save(product3);
    }

    @Test
    public void test() {
        Iterable<Product> p = productRepository.findAll();
        System.out.println("debaguj me");
    }
}


