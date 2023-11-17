package com.example.TTWebBanHang.Repository;

import com.example.TTWebBanHang.Entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, UUID> {
}
