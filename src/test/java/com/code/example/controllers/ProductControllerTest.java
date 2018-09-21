package com.code.example.controllers;

import com.code.example.security.CurrentUser;
import com.code.example.persistence.entities.Product;
import com.code.example.persistence.entities.User;
import com.code.example.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by veljko on 13.9.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class ProductControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Mock
    ProductService productService;

    ProductController productController;

    MockMvc mockMvc;

     List<SimpleGrantedAuthority> role_client = Collections.singletonList(
             new SimpleGrantedAuthority("ROLE_CLIENT")
     );
    private CurrentUser userDetails = new CurrentUser("veljko","veljko", true,true,true,true,
            role_client, 1L, "user", "user");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        productController = new ProductController(productService);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
//                .standaloneSetup(productController)
                .build();

    }

    @Test
    public void testAddProduct() throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setName("Novi produkt");
        User user = new User();
        user.setId(1L);
        product.setUser(user);

        when(productService.saveProduct(any())).thenReturn(product);

        RequestBuilder requestBuilder = post("/rest/product")
                .with(user(userDetails))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testDeleteProduct() {

        Product product = new Product();
        product.setId(1L);
        product.setName("Novi product");



    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
