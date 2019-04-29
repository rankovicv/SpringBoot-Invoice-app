package com.code.example.persistence.repositories;

import com.code.example.persistence.entities.Invoice;
import com.code.example.persistence.entities.Sale;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by veljko on 4.8.18.
 */
public interface SaleRepository extends CrudRepository<Sale, Long> {

    List<Sale> findSalesByInvoice(Invoice invoice);
}
