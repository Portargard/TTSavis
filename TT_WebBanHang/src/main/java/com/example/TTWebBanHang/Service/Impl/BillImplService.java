package com.example.TTWebBanHang.Service.Impl;

import com.example.TTWebBanHang.Entity.Bill;
import com.example.TTWebBanHang.Entity.ErrorMessages;
import com.example.TTWebBanHang.Exceptions.AllServiceException;
import com.example.TTWebBanHang.Repository.BillRepository;
import com.example.TTWebBanHang.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BillImplService implements BillService {

    @Autowired
    BillRepository billRepository;

    @Override
    public Page<Bill> findAll(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    @Override
    public Bill getOne(UUID id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public Bill add(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill update(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void delete(UUID id) {
        Bill bill = billRepository.findById(id).orElse(null);

        if (bill == null){
            throw new AllServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        billRepository.deleteById(id);
    }
}
