package com.code.example.services;

import com.code.example.persistence.entities.Invoice;
import com.code.example.persistence.entities.Sale;

import java.util.Set;

/**
 * Created by veljko on 5.8.18.
 */
public interface SaleService {

    Set<Sale> getSales();

    Set<Sale> getSalesByInvoice(Invoice invoice);

    Sale findById(Long id);

    Sale saveSale(Sale sale);

    void deleteById(Long id);
}
