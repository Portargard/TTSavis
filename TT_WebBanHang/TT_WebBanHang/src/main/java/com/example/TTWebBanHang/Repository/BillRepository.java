package com.example.TTWebBanHang.Repository;

import com.example.TTWebBanHang.Entity.Bill;
import com.example.TTWebBanHang.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, UUID> {
    Bill findByCustomerAndTrangThai(Customer customer, int trangThai);
}
