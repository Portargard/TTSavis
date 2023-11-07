package com.example.TTWebBanHang.Repository;

import com.example.TTWebBanHang.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
