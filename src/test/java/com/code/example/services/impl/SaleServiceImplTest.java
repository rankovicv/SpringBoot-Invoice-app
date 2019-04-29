package com.code.example.services.impl;

import com.code.example.exceptions.NotFoundException;
import com.code.example.persistence.entities.Invoice;
import com.code.example.persistence.entities.Product;
import com.code.example.persistence.entities.Sale;
import com.code.example.persistence.repositories.InvoiceRepository;
import com.code.example.persistence.repositories.ProductRepository;
import com.code.example.persistence.repositories.SaleRepository;
import com.code.example.services.SaleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by veljko on 24.8.18.
 */
public class SaleServiceImplTest {

    SaleServiceImpl saleService;

    @Mock
    SaleRepository saleRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    InvoiceRepository invoiceRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        saleService = new SaleServiceImpl(saleRepository);
    }

    @Test
    public void getSalesTest() {

        Sale sale = new Sale();
        HashSet<Sale> sales = new HashSet<>();
        sales.add(sale);

        when(saleService.getSales()).thenReturn(sales);

        Set<Sale> saleSet = saleService.getSales();

        assertEquals(saleSet.size(), 1);
        verify(saleRepository, times(1)).findAll();
        verify(saleRepository, never()).findById(anyLong());
    }

    @Test
    public void getSalesByInvoiceTest() {

        Invoice invoice = new Invoice();
        invoice.setId(1L);

        Sale sale = new Sale();
        sale.setId(1L);
        sale.setInvoice(invoice);

        Sale sale1 = new Sale();
        sale.setId(2L);
        sale1.setInvoice(invoice);

        List<Sale> sales = new ArrayList<>();
        sales.add(sale);
        sales.add(sale1);

        when(saleService.getSalesByInvoice(invoice)).thenReturn(sales);

        List<Sale> returnSales = saleService.getSalesByInvoice(invoice);

        assertEquals(Long.valueOf(1L), returnSales.iterator().next().getInvoice().getId());
        assertEquals(2, returnSales.size());
        verify(saleRepository, times(1)).findSalesByInvoice(invoice);
    }

    @Test
    public void findByIdTest() {

        Sale sale = new Sale();
        sale.setId(1L);
        Optional<Sale> saleOptional = Optional.of(sale);

        when(saleRepository.findById(anyLong())).thenReturn(saleOptional);

        Sale returnSale = saleService.findById(1L);

        assertNotNull("Null sale returned. ", returnSale);
        verify(saleRepository, times(1)).findById(anyLong());
        verify(saleRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void findByIdTestNotFound() {

        Optional<Sale> saleOptional = Optional.empty();

        when(saleRepository.findById(anyLong())).thenReturn(saleOptional);

        Sale sale = saleService.findById(1L);
    }

    @Test
    public void testSaveSale() {

        Invoice invoice = new Invoice();
        invoice.setId(1L);

        Product product = new Product();
        product.setId(4L);

        Sale sale = new Sale();
        sale.setId(3L);
        sale.setInvoice(invoice);
        sale.setProduct(product);

        when(saleRepository.save(any())).thenReturn(sale);

        Sale savedSale = saleService.saveSale(sale);

        assertEquals(Long.valueOf(3L), savedSale.getId());
        verify(saleRepository, times(1)).save(any(Sale.class));

    }

    @Test
    public void deleteByIdTest() {

        Long idToDelete = Long.valueOf(2L);

        saleService.deleteById(idToDelete);

        verify(saleRepository, times(1)).deleteById(anyLong());
    }
}