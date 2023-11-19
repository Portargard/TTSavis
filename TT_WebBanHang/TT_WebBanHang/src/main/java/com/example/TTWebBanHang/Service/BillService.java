package com.example.TTWebBanHang.Service;

import com.example.TTWebBanHang.Entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BillService {

    Page<Bill> findAll(Pageable pageable);

    Bill getOne(UUID id);

    Bill add(Bill bill);

    Bill update(Bill bill, UUID id);

    void delete(UUID id);

    Bill GetByIdCustomer(UUID id);

}
