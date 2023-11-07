package com.example.TTWebBanHang.Repository;

import com.example.TTWebBanHang.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID>{
}
