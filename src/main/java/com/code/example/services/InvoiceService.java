package com.code.example.services;

import com.code.example.persistence.entities.Invoice;

import java.util.Set;

/**
 * Created by veljko on 5.8.18.
 */
public interface InvoiceService {

    Set<Invoice> getInvoices();

    Invoice findById(Long id);

    Invoice saveInvoice(Invoice invoice);

    void deleteById(Long id);
}
