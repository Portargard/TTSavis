package com.example.TTWebBanHang.Repository;

import com.example.TTWebBanHang.Entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
}
