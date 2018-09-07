package com.code.example.controllers;

import com.code.example.persistence.entities.Invoice;
import com.code.example.services.InvoiceService;
import com.code.example.services.SaleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by veljko on 27.8.18.
 */
public class InvoiceConrollerTest {

    @Mock
    InvoiceService invoiceService;

    @Mock
    SaleService saleService;

    InvoiceController invoiceController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        invoiceController = new InvoiceController(invoiceService, saleService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
    }

    @Test
    public void testGetInvoice() throws Exception {

        Invoice invoice = new Invoice();
        invoice.setId(1L);

        when(invoiceService.findById(anyLong())).thenReturn(invoice);

        mockMvc.perform(get("/invoice/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("invoice/show"));
    }

}
