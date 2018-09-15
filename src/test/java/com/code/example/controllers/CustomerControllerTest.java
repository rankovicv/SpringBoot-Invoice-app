package com.code.example.controllers;

import com.code.example.persistence.entities.Customer;
import com.code.example.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.*;

/**
 * Created by veljko on 5.9.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        customerController = new CustomerController(customerService);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
//
//    @Test
//    public void testGetNewCustomerForm() throws Exception {
//
//        mockMvc.perform(get("/customer/new"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("customer/customerform"))
//                .andExpect(model().attributeExists("customer"));
//    }
//
//    @Test
//    public void testGetUpdateView() throws Exception {
//        Customer customer = new Customer();
//        customer.setId(2L);
//
//        when(customerService.findById(anyLong())).thenReturn(customer);
//
//        mockMvc.perform(get("/customer/1/update"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("customer/customerform"))
//                .andExpect(model().attributeExists("customer"));
//    }



//    @Test
//    public void testGetNewCustomerForm() throws Exception {
//
//        Customer customer = new Customer();
//        customer.setId(1L);
//        customer.setName("Customer doo");
//        customer.setAddress("Customers address");
//        customer.setPib(11223366);
//        customer.setPhone("006662255");
//        customer.setEmail("test@test");
//
//        when(customerService.saveCustomer(any())).thenReturn(customer);
//
//        RequestBuilder requestBuilder = post("/customer")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(customer));
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            final ObjectMapper mapper = new ObjectMapper();
//            final String jsonContent = mapper.writeValueAsString(obj);
//            return jsonContent;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
