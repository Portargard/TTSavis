package com.example.TTWebBanHang.Repository;

import com.example.TTWebBanHang.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepo extends JpaRepository<Customer, UUID> {
}
