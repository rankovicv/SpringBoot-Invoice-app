package com.code.example.controllers;

import com.code.example.configuration.CurrentUser;
import com.code.example.persistence.entities.Customer;
import com.code.example.persistence.entities.User;
import com.code.example.services.CustomerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping()
    public @ResponseBody Customer saveOrUpdate(@ModelAttribute Customer customer) {

        CurrentUser loggedUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        user.setId(loggedUserDetails.getUserId());
        customer.setUser(user);

        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody  boolean deleteById(@PathVariable String id) {

        log.debug("Deleting id: " + id);

        customerService.deleteById(Long.valueOf(id));

        return true;
    }
}
