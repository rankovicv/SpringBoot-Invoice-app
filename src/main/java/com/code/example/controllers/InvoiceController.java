package com.code.example.controllers;

import com.code.example.persistence.entities.Customer;
import com.code.example.persistence.entities.Invoice;
import com.code.example.persistence.entities.Sale;
import com.code.example.services.InvoiceService;
import com.code.example.services.SaleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by veljko on 27.8.18.
 */
@Slf4j
@RestController
@RequestMapping(path = "rest/invoice")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvoiceController {

    private final @NonNull
    InvoiceService invoiceService;

    private final @NonNull
    SaleService saleService;

    @PostMapping("/{id}/saveCustomer")
    public ResponseEntity<String> saveInvoiceCustomer(@PathVariable Long id, @RequestParam("customer") String customerId) {

        Customer customer = new Customer();
        customer.setId(Long.parseLong(customerId));
        Invoice invoice = invoiceService.findById(id);
        invoice.setCustomer(customer);

        invoiceService.saveInvoice(invoice);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/{id}/sale")
    public Sale addSale(@PathVariable Long id, @RequestBody Sale sale) {

        Invoice invoice = new Invoice();
        invoice.setId(id);
        sale.setInvoice(invoice);

        saleService.saveSale(sale);
        invoiceService.updateInvoiceTotal(id);

        return sale;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Long id) {

        invoiceService.deleteById(id);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("/sale/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable Long id) {

        saleService.deleteById(id);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
