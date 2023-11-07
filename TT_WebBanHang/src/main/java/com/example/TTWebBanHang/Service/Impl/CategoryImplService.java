package com.example.TTWebBanHang.Service.Impl;

import com.example.TTWebBanHang.Entity.Book;
import com.example.TTWebBanHang.Entity.Category;
import com.example.TTWebBanHang.Entity.ErrorMessages;
import com.example.TTWebBanHang.Exceptions.AllServiceException;
import com.example.TTWebBanHang.Repository.CategoryRepository;
import com.example.TTWebBanHang.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryImplService implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category getOne(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(UUID id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if(category == null){
            throw new AllServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        categoryRepository.deleteById(id);
    }
}
