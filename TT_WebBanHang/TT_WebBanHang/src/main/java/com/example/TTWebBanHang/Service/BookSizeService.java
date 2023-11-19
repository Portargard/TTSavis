package com.example.TTWebBanHang.Service;

import com.example.TTWebBanHang.Entity.BookSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookSizeService {

    Page<BookSize> findAll(Pageable pageable);

    BookSize getOne(UUID id);

    BookSize add(BookSize bookSize);

    BookSize update(BookSize bookSize);

    void delete(UUID id);
}
