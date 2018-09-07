package com.code.example.controllers;

import com.code.example.persistence.entities.Customer;
import com.code.example.services.CustomerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * Created by veljko on 3.9.18.
 */
@Slf4j
@Controller
@RequestMapping(path = "/customer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final @NonNull
    CustomerService customerService;

    @GetMapping("/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());

        return "customer/customerform";
    }

    @GetMapping("/{id}/update")
    public String updateCustomer(@PathVariable String id, Model model) {
        model.addAttribute("customer", customerService.findById(Long.valueOf(id)));

        return "customer/customerform";
    }

    @PostMapping()
    public @ResponseBody Customer saveOrUpdate(@ModelAttribute Customer customer, HttpServletRequest request) {
        Customer saveCustomer = customerService.saveCustomer(customer);

        String referer = request.getHeader("Referer");

        return saveCustomer;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody  boolean deleteById(@PathVariable String id) {

        log.debug("Deleting id: " + id);

        customerService.deleteById(Long.valueOf(id));

        return true;
    }



}
