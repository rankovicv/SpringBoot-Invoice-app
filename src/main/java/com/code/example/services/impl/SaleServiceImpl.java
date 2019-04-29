package com.code.example.services.impl;

import com.code.example.exceptions.NotFoundException;
import com.code.example.persistence.entities.Invoice;
import com.code.example.persistence.entities.Sale;
import com.code.example.persistence.repositories.SaleRepository;
import com.code.example.services.SaleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by veljko on 23.8.18.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleServiceImpl implements SaleService {

    private final @NonNull
    SaleRepository saleRepository;

    @Override
    public Set<Sale> getSales() {
        log.debug("I'm in the SaleService");

        Set<Sale> saleSet = new HashSet<>();
        saleRepository.findAll().iterator().forEachRemaining(saleSet::add);
        return saleSet;

    }

    @Override
    public List<Sale> getSalesByInvoice(Invoice invoice) {

        List<Sale> saleSet = new ArrayList<>();
        saleRepository.findSalesByInvoice(invoice).iterator().forEachRemaining(saleSet::add);

        return saleSet;
    }

    @Override
    public Sale findById(Long id) {

        Optional<Sale> sale = saleRepository.findById(id);
        if(!sale.isPresent()) {
            throw new NotFoundException("Sale Not Found. For ID value: " + id.toString() );
        }
        return sale.get();
    }

    @Override
    public Sale saveSale(Sale sale) {

        Sale saveSale = saleRepository.save(sale);
        log.debug("Saved SaleId:" + saveSale.getId());
        return saveSale;
    }

    @Override
    public void deleteById(Long id) {
        saleRepository.deleteById(id);
    }
}
