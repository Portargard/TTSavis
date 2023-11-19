package com.example.TTWebBanHang.Service.Impl;

import com.example.TTWebBanHang.Entity.Bill;
import com.example.TTWebBanHang.Entity.Customer;
import com.example.TTWebBanHang.Entity.ErrorMessages;
import com.example.TTWebBanHang.Exceptions.AllServiceException;
import com.example.TTWebBanHang.Repository.BillRepository;
import com.example.TTWebBanHang.Repository.CustomerRepo;
import com.example.TTWebBanHang.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class BillImplService implements BillService {

    @Autowired
    BillRepository billRepository;

    @Autowired
    CustomerRepo customerRepo;

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
        String ma = "bill_" + (billRepository.count() + 1);
        bill.setMa(ma);
        bill.setNgayTao(new Date());
        bill.setHinhThucThanhToan(1);
        bill.setSoTienThanhToan(0);
        bill.setTrangThai(0);
        return billRepository.save(bill);
    }

    @Override
    public Bill update(Bill bill, UUID id) {
        Bill billCheck = billRepository.findById(id).get();
        billCheck.setNgayThanhToan(new Date());
        billCheck.setSoTienThanhToan(bill.getSoTienThanhToan());
        billCheck.setTrangThai(1);
        return billRepository.save(billCheck);
    }

    @Override
    public void delete(UUID id) {
        Bill bill = billRepository.findById(id).orElse(null);

        if (bill == null){
            throw new AllServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        billRepository.deleteById(id);
    }

    @Override
    public Bill GetByIdCustomer(UUID id) {
        Customer customer = customerRepo.findById(id).get();
        return billRepository.findByCustomerAndTrangThai(customer, 0);
    }
}
