package com.code.example.persistence.repositories;

import com.code.example.persistence.entities.Customer;
import com.code.example.persistence.entities.Invoice;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by veljko on 4.8.18.
 */
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    Iterable<Invoice> findInvoicesByCustomer(Customer customer);

}
