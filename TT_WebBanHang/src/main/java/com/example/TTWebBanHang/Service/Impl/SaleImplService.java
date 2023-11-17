package com.example.TTWebBanHang.Service.Impl;


import com.example.TTWebBanHang.Entity.ErrorMessages;
import com.example.TTWebBanHang.Entity.Sale;
import com.example.TTWebBanHang.Repository.SaleRepository;
import com.example.TTWebBanHang.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.TTWebBanHang.Exceptions.AllServiceException;

import java.util.UUID;

@Service
public class SaleImplService implements SaleService {

    @Autowired
    SaleRepository saleRepository;

    @Override
    public Page<Sale> findAll(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }

    @Override
    public Sale getOne(UUID id) {
        return saleRepository.findById(id).orElse(null);
    }

    @Override
    public Sale add(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Sale update(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public void delete(UUID id) {
        Sale sale = saleRepository.findById(id).orElse(null);

        if(sale == null){
            throw new AllServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        saleRepository.deleteById(id);
    }
}
