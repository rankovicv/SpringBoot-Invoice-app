package com.code.example.persistence.repositories;

import com.code.example.persistence.entities.Invoice;
import com.code.example.persistence.entities.Sale;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by veljko on 4.8.18.
 */
public interface SaleRepository extends CrudRepository<Sale, Long> {

    Iterable<Sale> findSalesByInvoice(Invoice invoice);

}
