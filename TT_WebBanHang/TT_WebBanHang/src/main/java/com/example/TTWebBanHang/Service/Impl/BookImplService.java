package com.example.TTWebBanHang.Service.Impl;


import com.example.TTWebBanHang.Entity.Book;
import com.example.TTWebBanHang.Entity.ErrorMessages;
import com.example.TTWebBanHang.Repository.BookRepository;
import com.example.TTWebBanHang.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.TTWebBanHang.Exceptions.AllServiceException;

import java.util.List;
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
    public List<Book> findAll() {
        
        return bookRepository.findAll();
    }

    @Override
    public Book getOne(UUID id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book add(Book book) {
        String ma = "book_" + (bookRepository.count() + 1);
        book.setMa(ma);
        book.setTrangThai(1);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book, UUID id) {
        Book b = bookRepository.findById(id).get();
        book.setId(id);
        book.setMa(b.getMa());
        book.setSoLuongDaBan(b.getSoLuongDaBan());
        return bookRepository.save(book);
    }

    @Override
    public void delete(UUID id) {
        Book book = bookRepository.findById(id).orElse(null);

        if (book == null) {
            throw new AllServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        bookRepository.deleteById(id);
    }

}
