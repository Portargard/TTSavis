package com.example.TTWebBanHang.Service;

import com.example.TTWebBanHang.Entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SaleService {

    Page<Sale> findAll(Pageable pageable);

    Sale getOne(UUID id);

    Sale add(Sale sale);

    Sale update(Sale sale);

    void delete(UUID id);
}
