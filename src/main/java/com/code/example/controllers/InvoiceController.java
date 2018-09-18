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
import org.springframework.web.bind.annotation.RestController;

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



}
