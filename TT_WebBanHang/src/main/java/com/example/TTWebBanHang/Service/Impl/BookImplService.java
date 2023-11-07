package com.example.TTWebBanHang.Service.Impl;

import com.example.TTWebBanHang.Entity.Author;
import com.example.TTWebBanHang.Entity.Book;
import com.example.TTWebBanHang.Entity.ErrorMessages;
import com.example.TTWebBanHang.Exceptions.AllServiceException;
import com.example.TTWebBanHang.Repository.BookRepository;
import com.example.TTWebBanHang.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookImplService implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book getOne(UUID id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book add(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(UUID id) {
        Book book = bookRepository.findById(id).orElse(null);

        if(book == null){
            throw new AllServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        bookRepository.deleteById(id);
    }
}
