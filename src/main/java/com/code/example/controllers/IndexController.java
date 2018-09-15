package com.code.example.controllers;

import com.code.example.configuration.CurrentUser;
import com.code.example.mail.EmailService;
import com.code.example.persistence.entities.Customer;
import com.code.example.persistence.entities.Invoice;
import com.code.example.persistence.entities.Product;
import com.code.example.persistence.entities.User;
import com.code.example.services.CustomerService;
import com.code.example.services.InvoiceService;
import com.code.example.services.ProductService;
import com.code.example.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by veljko on 29.7.18.
 */
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController {

    private final @NonNull
    InvoiceService invoiceService;

    private final @NonNull
    CustomerService customerService;

    private final @NonNull
    EmailService emailService;

    private final @NonNull
    UserService userService;

    private final @NonNull
    ProductService productService;

    @GetMapping({"/index"})
    public String getIndexPage(Model model) {

        log.debug("Getting Index page");

        Set<Invoice> invoices = invoiceService.getInvoices();

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CurrentUser myUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userService.findUserByEmail(auth.getName());

        model.addAttribute("invoices", invoices);
        model.addAttribute("loggedUser", myUserDetails.getUsername());
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText(
//                "This is the test email template for your email:" + invoices.iterator().next().getInvoiceNumber());
//
//        emailService.sendSimpleMessageUsingTemplate("veljko.rankovic9@gmail.com", "tema", message);

        return "index";
    }

    @GetMapping("/customers")
    public String getCustomersPage(Model model) {

        log.debug("Customers page open");

        CurrentUser myUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Customer> customerSet = customerService.getCustomersByUser(myUserDetails.getUserId());

        model.addAttribute("customers", customerSet);
//        model.addAttribute("loggedUser", user);


        return "customers";
    }

    @GetMapping("/products")
    public String getProductsPage(Model model) {
        log.debug("Products page open");

        CurrentUser myUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Product> productSet = productService.getProductsByUser(myUserDetails.getUserId());

        model.addAttribute("products", productSet);

        return "products";
    }

}