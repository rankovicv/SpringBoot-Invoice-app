package com.code.example.services.impl;

import com.code.example.exceptions.NotFoundException;
import com.code.example.persistence.entities.Product;
import com.code.example.persistence.entities.User;
import com.code.example.persistence.repositories.ProductRepository;
import com.code.example.persistence.repositories.SaleRepository;
import com.code.example.services.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by veljko on 5.8.18.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private final @NonNull
    ProductRepository productRepository;

    private final @NonNull
    SaleRepository saleRepository;

    @Override
    public Set<Product> getProducts() {
        log.debug("I'm in the service");

        Set<Product> productSet = new HashSet<>();
        productRepository.findAll().iterator().forEachRemaining(productSet::add);
        return productSet;
    }

    @Override
    public Set<Product> getProductsByUser(Long userId) {

        Set<Product> productSet = new HashSet<>();
        productRepository.findByUser_Id(userId).iterator().forEachRemaining(productSet::add);

        return productSet;
    }

    @Override
    public Product findById(Long id) {

        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()) {
            throw new NotFoundException("Product Not Found. For ID value: " + id.toString() );
        }

        return product.get();
    }

    @Override
    public Product saveProduct(Product product) {
        Product saveProduct = productRepository.save(product);

        log.debug("Saved ProductId:" + saveProduct.getId());
        return saveProduct;
    }

    @Override
    public void deleteById(Long id) {

        productRepository.deleteById(id);
    }
}
