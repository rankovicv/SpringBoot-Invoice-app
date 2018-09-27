package com.code.example.persistence.repositories;

import com.code.example.persistence.entities.Customer;
import com.code.example.persistence.entities.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by veljko on 4.8.18.
 */
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    Iterable<Invoice> findInvoicesByCustomer(Customer customer);

    Iterable<Invoice> findByUser_Id(Long userId);

    @Query(value = "SELECT invoice_number FROM invoice WHERE user_id =:userId ORDER BY invoice_number DESC LIMIT 1", nativeQuery = true)
    String findLastInvoiceNumber(@Param("userId") Long userId);
}
