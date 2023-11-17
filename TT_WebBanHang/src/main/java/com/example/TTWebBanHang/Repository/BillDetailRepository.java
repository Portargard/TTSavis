package com.example.TTWebBanHang.Repository;

import com.example.TTWebBanHang.Entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillDetailRepository extends JpaRepository<BillDetail, UUID> {
}
