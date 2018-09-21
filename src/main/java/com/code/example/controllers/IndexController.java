package com.code.example.controllers;

import com.code.example.persistence.entities.*;
import com.code.example.security.CurrentUser;
import com.code.example.mail.EmailService;
import com.code.example.services.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    private final @NonNull
    SaleService saleService;

    @GetMapping({"/", "/index"})
    public String getIndexPage(Model model) {

        log.debug("Getting Index page");

        CurrentUser myUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserCompany userCompany = userService.getUserCompany(myUserDetails.getUserId());

        if(userCompany == null) {
            return "redirect:user/company/edit";
        }
        Set<Invoice> invoices = invoiceService.getInvoices();


        model.addAttribute("invoices", invoices);
        model.addAttribute("loggedUser", myUserDetails.getUsername());

        return "index";
    }

    @GetMapping("/customers")
    public String getCustomersPage(Model model) {

        log.debug("Customers page open");

        CurrentUser myUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Customer> customerSet = customerService.getCustomersByUser(myUserDetails.getUserId());

        model.addAttribute("customers", customerSet);

        return "customer/customers";
    }

    @GetMapping("/products")
    public String getProductsPage(Model model) {
        log.debug("Products page open");

        CurrentUser myUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Product> productSet = productService.getProductsByUser(myUserDetails.getUserId());

        model.addAttribute("products", productSet);

        return "product/products";
    }


    @GetMapping("/invoice/show/{id}")
    public String showById(@PathVariable String id, Model model) {

        Invoice invoice = invoiceService.findById(new Long(id));

        model.addAttribute("invoice", invoice);
        model.addAttribute("sales", saleService.getSalesByInvoice(invoice));

        return "invoice/show";
    }

}