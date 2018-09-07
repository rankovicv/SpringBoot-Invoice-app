package com.code.example.controllers;

import com.code.example.mail.EmailService;
import com.code.example.persistence.entities.Customer;
import com.code.example.persistence.entities.Invoice;
import com.code.example.services.CustomerService;
import com.code.example.services.InvoiceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

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

    @GetMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        log.debug("Getting Index page");

        Set<Invoice> invoices = invoiceService.getInvoices();
        invoices.stream()
                .sorted(Comparator.comparing(Invoice::getId))
                .collect(Collectors.toList());

        model.addAttribute("invoices", invoices);
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

        model.addAttribute("customers", customerSet);

        return "customers";
    }

}