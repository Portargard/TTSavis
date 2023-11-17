package com.example.TTWebBanHang.Controller;

import com.example.TTWebBanHang.Entity.Author;
import com.example.TTWebBanHang.Entity.Book;
import com.example.TTWebBanHang.Entity.OperationStatusModel;
import com.example.TTWebBanHang.Entity.Pulisher;
import com.example.TTWebBanHang.Entity.Realm;
import com.example.TTWebBanHang.Entity.RequestOperationStatus;
import com.example.TTWebBanHang.Entity.Sale;
import com.example.TTWebBanHang.Service.Impl.AuthorImplService;
import com.example.TTWebBanHang.Service.Impl.BookImplService;
import com.example.TTWebBanHang.Service.Impl.PulisherImplService;
import com.example.TTWebBanHang.Service.Impl.RealmImplService;
import com.example.TTWebBanHang.Service.Impl.SaleImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("savis/book")
@CrossOrigin
public class BookController {

    @Autowired
    BookImplService bookImplService;

    @Autowired
    RealmImplService realmImplService;

    @Autowired
    PulisherImplService pulisherImplService;

    @Autowired
    AuthorImplService authorImplService;

    @Autowired
    SaleImplService saleImplService;

    @GetMapping()
    private ResponseEntity<Page<Book>> getall(@RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("ma").descending());
        Page<Book> books = bookImplService.findAll(pageable);
        return ResponseEntity.ok(books);
    }


    @PostMapping()
    private ResponseEntity<Book> add(@RequestBody Book book) {
        UUID maRealm = book.getRealm().getId();
        UUID maPulisher = book.getPulisher().getId();
        UUID maAuthor = book.getAuthor().getId();
        UUID maSale = book.getSale().getId();

        Realm realm = realmImplService.getOne(maRealm);
        Pulisher pulisher = pulisherImplService.getOne(maPulisher);
        Author author = authorImplService.getOne(maAuthor);
        Sale sale = saleImplService.getOne(maSale);

        book.setRealm(realm);
        book.setPulisher(pulisher);
        book.setAuthor(author);
        book.setSale(sale);
        System.out.println(book.getMoTa());
        Book booksave = bookImplService.add(book);

        return ResponseEntity.ok(booksave);

    }

    @PutMapping("/{id}")
    private ResponseEntity<Book> update(@RequestBody Book book,
                                         @PathVariable("id") UUID maht) {

        UUID maRealm = book.getRealm().getId();
        UUID maPulisher = book.getPulisher().getId();
        UUID maAuthor = book.getAuthor().getId();
        UUID maSale = book.getSale().getId();

        Realm realm = realmImplService.getOne(maRealm);
        Pulisher pulisher = pulisherImplService.getOne(maPulisher);
        Author author = authorImplService.getOne(maAuthor);
        Sale sale = saleImplService.getOne(maSale);

        book.setRealm(realm);
        book.setPulisher(pulisher);
        book.setAuthor(author);
        book.setSale(sale);

        Book bookcheck = bookImplService.getOne(maht);
        if (bookcheck == null) {
            return ResponseEntity.notFound().build();
        }
        bookcheck.setId(maht);
        Book bookupdate = bookImplService.update(book);

        return ResponseEntity.ok(bookupdate);
    }

    @DeleteMapping("/{id}")
    public OperationStatusModel delete(@PathVariable("id") UUID id) {

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        bookImplService.delete(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return returnValue;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getOne(@PathVariable("id") UUID id) {
        Book book = bookImplService.getOne(id);
        return ResponseEntity.ok(book);
    }
}
