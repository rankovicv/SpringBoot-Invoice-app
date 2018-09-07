package com.code.example.controllers;

import com.code.example.persistence.entities.Invoice;
import com.code.example.services.InvoiceService;
import com.code.example.services.SaleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by veljko on 27.8.18.
 */
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvoiceController {

    private final @NonNull
    InvoiceService invoiceService;

    private final @NonNull
    SaleService saleService;

    @GetMapping("/invoice/show/{id}")
    public String showById(@PathVariable String id, Model model) {

        Invoice invoice = invoiceService.findById(new Long(id));

        model.addAttribute("invoice", invoice);
        model.addAttribute("sales", saleService.getSalesByInvoice(invoice));

        return "invoice/show";
    }

}
