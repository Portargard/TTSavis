package com.example.TTWebBanHang.Service.Impl;

import com.example.TTWebBanHang.Entity.BillDetail;
import com.example.TTWebBanHang.Entity.ErrorMessages;
import com.example.TTWebBanHang.Exceptions.AllServiceException;
import com.example.TTWebBanHang.Repository.BillDetailRepository;
import com.example.TTWebBanHang.Service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BillDetailImplService implements BillDetailService {

    @Autowired
    BillDetailRepository billDetailRepository;

    @Override
    public Page<BillDetail> findAll(Pageable pageable) {
        return billDetailRepository.findAll(pageable);
    }

    @Override
    public BillDetail getOne(UUID id) {
        return billDetailRepository.findById(id).orElse(null);
    }

    @Override
    public BillDetail add(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }

    @Override
    public BillDetail update(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }

    @Override
    public void delete(UUID id) {
        BillDetail billDetail = billDetailRepository.findById(id).orElse(null);

    if(billDetail == null){
        throw new AllServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
    }

    billDetailRepository.deleteById(id);
    }
}
