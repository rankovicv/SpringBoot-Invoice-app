package com.code.example.services.impl;

import com.code.example.exceptions.NotFoundException;
import com.code.example.persistence.entities.Invoice;
import com.code.example.persistence.repositories.InvoiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by veljko on 27.8.18.
 */
public class InvoiceServiceImplTest {

    InvoiceServiceImpl invoiceService;

    @Mock
    InvoiceRepository invoiceRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        invoiceService = new InvoiceServiceImpl(invoiceRepository);
    }

    @Test
    public void getInvoicesTest() {

        Invoice invoice = new Invoice();
        HashSet<Invoice> invoices = new HashSet<>();
        invoices.add(invoice);

        when(invoiceService.getInvoices()).thenReturn(invoices);

        Set<Invoice> invoiceSet = invoiceService.getInvoices();

        assertEquals(invoiceSet.size(), 1);
        verify(invoiceRepository, times(1)).findAll();
        verify(invoiceRepository, never()).findById(anyLong());
    }

    @Test
    public void findByIdTest() {

        Invoice invoice = new Invoice();
        invoice.setId(1L);
        Optional<Invoice> invoiceOptional = Optional.of(invoice);

        when(invoiceRepository.findById(anyLong())).thenReturn(invoiceOptional);

        Invoice invoiceReturned = invoiceService.findById(1L);

        assertNotNull("Null invoice retuned", invoiceReturned);
        verify(invoiceRepository, times(1)).findById(anyLong());
        verify(invoiceRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void findByIdTestNotFound() {

        Optional<Invoice> invoiceOptional = Optional.empty();

        when(invoiceRepository.findById(anyLong())).thenReturn(invoiceOptional);

        Invoice invoice = invoiceService.findById(1L);
    }

    @Test
    public void saveInvoiceTest() {

        Invoice invoice = new Invoice();
        invoice.setId(1L);

        when(invoiceRepository.save(any())).thenReturn(invoice);

        Invoice savedInvoice = invoiceService.saveInvoice(invoice);

        assertEquals(Long.valueOf(1L), savedInvoice.getId());
        verify(invoiceRepository, times(1)).save(any(Invoice.class));
    }

    @Test
    public void deleteByIdTest() {

        Long idToDelete = Long.valueOf(2L);

        invoiceService.deleteById(idToDelete);

        verify(invoiceRepository, times(1)).deleteById(anyLong());
    }
}