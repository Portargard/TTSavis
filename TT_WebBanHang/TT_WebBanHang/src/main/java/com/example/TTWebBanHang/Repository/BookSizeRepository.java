package com.example.TTWebBanHang.Repository;

import com.example.TTWebBanHang.Entity.BookSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookSizeRepository extends JpaRepository<BookSize, UUID> {
}
