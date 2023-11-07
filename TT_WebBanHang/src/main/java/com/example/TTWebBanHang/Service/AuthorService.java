package com.example.TTWebBanHang.Service;

import com.example.TTWebBanHang.Entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AuthorService {

    Page<Author> findAll(Pageable pageable);

    Author getOne(UUID id);

    Author add(Author author);

    Author update(Author author);

    void delete(UUID id);
}
