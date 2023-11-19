package com.example.TTWebBanHang.Repository;

import com.example.TTWebBanHang.Entity.Bill;
import com.example.TTWebBanHang.Entity.BillDetail;
import com.example.TTWebBanHang.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BillDetailRepository extends JpaRepository<BillDetail, UUID> {
    int countByBillAndBook(Bill bill, Book book);
    BillDetail findByBillAndBook(Bill bill, Book book);
    List<BillDetail> findAllByBill(Bill bill);
}
