package com.example.TTWebBanHang.Repository;

import com.example.TTWebBanHang.Entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartDetailRepo extends JpaRepository<CartDetail, UUID> {
}
