package com.example.TTWebBanHang.Service.Impl;

import com.example.TTWebBanHang.Entity.Book;
import com.example.TTWebBanHang.Entity.BookSize;
import com.example.TTWebBanHang.Entity.ErrorMessages;
import com.example.TTWebBanHang.Exceptions.AllServiceException;
import com.example.TTWebBanHang.Repository.BookSizeRepository;
import com.example.TTWebBanHang.Service.BookSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookSizeImplService implements BookSizeService {

    @Autowired
    BookSizeRepository bookSizeRepository;

    @Override
    public Page<BookSize> findAll(Pageable pageable) {
        return bookSizeRepository.findAll(pageable);
    }

    @Override
    public BookSize getOne(UUID id) {
        return bookSizeRepository.findById(id).orElse(null);
    }

    @Override
    public BookSize add(BookSize bookSize) {
        return bookSizeRepository.save(bookSize);
    }

    @Override
    public BookSize update(BookSize bookSize) {
        return bookSizeRepository.save(bookSize);
    }

    @Override
    public void delete(UUID id) {
        BookSize bookSize = bookSizeRepository.findById(id).orElse(null);

        if(bookSize == null){
            throw new AllServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        bookSizeRepository.deleteById(id);
    }
}
