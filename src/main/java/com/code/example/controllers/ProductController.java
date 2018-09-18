package com.code.example.controllers;

import com.code.example.security.CurrentUser;
import com.code.example.persistence.entities.Product;
import com.code.example.persistence.entities.User;
import com.code.example.services.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by veljko on 13.9.18.
 */
@RestController
@RequestMapping(path = "rest/product")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final @NonNull
    ProductService productService;

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product) {

        CurrentUser myUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        user.setId(myUserDetails.getUserId());
        product.setUser(user);

        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable String id) {

        log.debug("Deleting product id: " + id);

        productService.deleteById(Long.valueOf(id));

        return true;
    }
}
