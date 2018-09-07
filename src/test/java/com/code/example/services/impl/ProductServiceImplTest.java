package com.code.example.services.impl;

import com.code.example.exceptions.NotFoundException;
import com.code.example.persistence.entities.Product;
import com.code.example.persistence.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


/**
 * Created by veljko on 5.8.18.
 */
public class ProductServiceImplTest {

    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void getProductsTest() {

        Product product = new Product();
        HashSet productData = new HashSet();
        productData.add(product);

        when(productService.getProducts()).thenReturn(productData);

        Set<Product> productSet = productService.getProducts();

        assertEquals(productSet.size(), 1);
        verify(productRepository, times(1)).findAll();
        verify(productRepository, never()).findById(anyLong());
    }

    @Test
    public void findByIdTest() {
        Product product = new Product();
        product.setId(1L);
        Optional<Product> productOptional = Optional.of(product);

        when(productRepository.findById(anyLong())).thenReturn(productOptional);

        Product productReturned = productService.findById(1L);

        assertNotNull("Null product returned.", productReturned);
        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void findByIdTestNotFound() {

        Optional<Product> productOptional = Optional.empty();

        when(productRepository.findById(anyLong())).thenReturn(productOptional);

        Product product = productService.findById(1L);
    }

    @Test
    public void testDeleteById() {

        Long idToDelete = Long.valueOf(2L);

        productService.deleteById(idToDelete);

        verify(productRepository, times(1)).deleteById(anyLong());
    }

}
