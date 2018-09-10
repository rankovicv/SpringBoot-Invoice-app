package com.code.example.controllers;

import com.code.example.mail.EmailService;
import com.code.example.persistence.entities.Customer;
import com.code.example.persistence.entities.Invoice;
import com.code.example.persistence.entities.User;
import com.code.example.services.CustomerService;
import com.code.example.services.InvoiceService;
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

    @GetMapping({"/index"})
    public String getIndexPage(Model model) {

        log.debug("Getting Index page");

        Set<Invoice> invoices = invoiceService.getInvoices();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

        model.addAttribute("invoices", invoices);
        model.addAttribute("loggedUser", user);
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

        Set<Customer> customerSet = customerService.getCustomers();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

        model.addAttribute("customers", customerSet);
        model.addAttribute("loggedUser", user);

        return "customers";
    }

}