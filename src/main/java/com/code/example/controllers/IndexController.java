package com.code.example.controllers;

import com.code.example.persistence.entities.Customer;
import com.code.example.persistence.entities.Invoice;
import com.code.example.persistence.entities.Product;
import com.code.example.persistence.entities.UserCompany;
import com.code.example.security.CurrentUser;
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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
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
    UserService userService;

    private final @NonNull
    ProductService productService;

    private final @NonNull
    SaleService saleService;

    @GetMapping({"/", "/index"})
    public String getIndexPage(Model model) {

        log.debug("Getting Index page");

        CurrentUser authUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserCompany userCompany = userService.getUserCompany(authUser.getUserId());

        if(userCompany == null) {
            return "redirect:user/company/edit";
        }

        Set<Invoice> invoices = invoiceService.getUserInvoices(authUser.getUserId());

        model.addAttribute("invoices", invoices);
        model.addAttribute("loggedUserName", authUser.getName());
        model.addAttribute("loggedUserLastName", authUser.getLastName());

        return "index";
    }

    @GetMapping("/customers")
    public String getCustomersPage(Model model) {

        log.debug("Customers page open");

        CurrentUser myUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Customer> customers = customerService.getCustomersByUser(myUserDetails.getUserId());

        model.addAttribute("customers", customers);

        return "customer/customers";
    }

    @GetMapping("/products")
    public String getProductsPage(Model model) {
        log.debug("Products page open");

        CurrentUser authUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Product> productSet = productService.getProductsByUser(authUser.getUserId());

        model.addAttribute("products", productSet);

        return "product/products";
    }

    @GetMapping("/invoice/add")
    public String addNewInvoice() {

        CurrentUser authUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Invoice newInvoice = invoiceService.addNewInvoice(authUser.getUserId());

        return "redirect:show/" + newInvoice.getId();
    }

    @GetMapping("/invoice/show/{id}")
    public String showById(@PathVariable String id, Model model) {

        CurrentUser authUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserCompany userCompany = userService.getUserCompany(authUser.getUserId());
        Invoice invoice = invoiceService.findById(new Long(id));
        List<Customer> customers = customerService.getCustomersByUser(authUser.getUserId());

        model.addAttribute("invoice", invoice);
        model.addAttribute("userCompany", userCompany);
        model.addAttribute("customers", customers);
        model.addAttribute("sales", saleService.getSalesByInvoice(invoice));
        model.addAttribute("products", productService.getProductsByUser(authUser.getUserId()));

        return "invoice/show";
    }

}