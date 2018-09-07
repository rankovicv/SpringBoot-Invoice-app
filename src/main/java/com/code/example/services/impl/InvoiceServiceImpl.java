package com.code.example.services.impl;

import com.code.example.exceptions.NotFoundException;
import com.code.example.persistence.entities.Invoice;
import com.code.example.persistence.repositories.InvoiceRepository;
import com.code.example.services.InvoiceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by veljko on 25.8.18.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvoiceServiceImpl implements InvoiceService {

    private final @NonNull
    InvoiceRepository invoiceRepository;

    @Override
    public Set<Invoice> getInvoices() {
        log.debug("I'm in getInvoices-InvoiceService");

        Set<Invoice> invoices = new HashSet<>();
        invoiceRepository.findAll().iterator().forEachRemaining(invoices::add);

        return invoices;
    }

    @Override
    public Invoice findById(Long id) {

        Optional<Invoice> invoice = invoiceRepository.findById(id);

        if(!invoice.isPresent()) {
            throw new NotFoundException("Invoice Not Found. For ID value: " + id.toString() );
        }

        return invoice.get();
    }

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        Invoice saveInvoice = invoiceRepository.save(invoice);

        log.debug("Saved InvoiceId:" + saveInvoice.getId());
        return saveInvoice;
    }

    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
}
