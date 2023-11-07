package com.example.TTWebBanHang.Service;

import com.example.TTWebBanHang.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Category getOne(UUID id);

    Category add(Category category);

    Category update(Category category);

    void delete(UUID id);
}
