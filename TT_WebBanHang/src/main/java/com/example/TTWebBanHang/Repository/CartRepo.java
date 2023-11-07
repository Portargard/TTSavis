package com.example.TTWebBanHang.Repository;

import com.example.TTWebBanHang.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepo extends JpaRepository<Cart, UUID> {
}
