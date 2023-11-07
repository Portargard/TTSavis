package com.example.TTWebBanHang.Controller;

import com.example.TTWebBanHang.Entity.Book;
import com.example.TTWebBanHang.Entity.BookSize;
import com.example.TTWebBanHang.Entity.OperationStatusModel;
import com.example.TTWebBanHang.Entity.RequestOperationStatus;
import com.example.TTWebBanHang.Service.Impl.BookImplService;
import com.example.TTWebBanHang.Service.Impl.BookSizeImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("savis/bookSize")
public class BookSizeController {
    @Autowired
    BookSizeImplService bookSizeImplService;

    @Autowired
    BookImplService bookImplService;

    @GetMapping()
    private ResponseEntity<Page<BookSize>> getall(@RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<BookSize> bookSizes = bookSizeImplService.findAll(pageable);
        return ResponseEntity.ok(bookSizes);
    }

    @PostMapping()
    private ResponseEntity<BookSize> add(@RequestBody BookSize bookSize) {
        UUID maBook = bookSize.getBook().getId();
        Book book = bookImplService.getOne(maBook);
        bookSize.setBook(book);

        BookSize bookSizesave = bookSizeImplService.add(bookSize);

        return ResponseEntity.ok(bookSizesave);

    }

    @PutMapping()
    private ResponseEntity<BookSize> update(@RequestBody BookSize bookSize,
                                         @PathVariable("id") UUID maht) {

        UUID idBook = bookSize.getBook().getId();
        Book bookDCsave = bookImplService.getOne(idBook);

        bookSize.setBook(bookDCsave);

        BookSize bookSizecheck = bookSizeImplService.getOne(maht);
        if (bookSizecheck == null) {
            return ResponseEntity.notFound().build();
        }
        bookSizecheck.setId(maht);
        BookSize bookSizeupdate = bookSizeImplService.update(bookSize);

        return ResponseEntity.ok(bookSizeupdate);
    }

    @DeleteMapping("/{id}")
    public OperationStatusModel delete(@PathVariable("id") UUID id){

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        bookSizeImplService.delete(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());


        return returnValue;
    }
}
