package com.example.TTWebBanHang.Service;

import com.example.TTWebBanHang.Entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BookService {

    Page<Book> findAll(Pageable pageable);

    Book getOne(UUID id);

    Book add(Book book);

    Book update(Book book, UUID id);

    void delete(UUID id);

    List<Book> findAll();

}
