package com.example.TTWebBanHang.Service;

import com.example.TTWebBanHang.Entity.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BillDetailService {

    Page<BillDetail> findAll(Pageable pageable);

    BillDetail getOne(UUID id);

    BillDetail add(BillDetail billDetail);

    BillDetail update(BillDetail billDetail);

    void delete(UUID id);
}
