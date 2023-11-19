package com.example.TTWebBanHang.Service;

import com.example.TTWebBanHang.Entity.Bill;
import com.example.TTWebBanHang.Entity.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BillDetailService {

    List<BillDetail> findAll();

    BillDetail getOne(UUID id);

    BillDetail add(BillDetail billDetail);

    BillDetail update(BillDetail billDetail);

    void delete(UUID id);

    List<BillDetail> getAllByBill(Bill bill);

}
