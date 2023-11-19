package com.example.TTWebBanHang.Controller;

import com.example.TTWebBanHang.Entity.Author;
import com.example.TTWebBanHang.Entity.OperationStatusModel;
import com.example.TTWebBanHang.Entity.RequestOperationStatus;
import com.example.TTWebBanHang.Service.Impl.AuthorImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("savis/author")
@CrossOrigin
public class AuthorController {
    @Autowired
    private AuthorImplService authorImplService;


    @GetMapping("")
    public ResponseEntity<Page<Author>> findall(@RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<Author> authors = authorImplService.findAll(pageable);
        return ResponseEntity.ok(authors);
    }

    @GetMapping("list")
    public ResponseEntity<List<Author>> getAllAuthor() {
        
        return ResponseEntity.ok(authorImplService.findAll());
    }

    @PostMapping("")
    public  ResponseEntity<Author> add(@RequestBody Author author){
        Author authorSave = authorImplService.add(author);
        return ResponseEntity.ok(authorSave);

    }

    @PutMapping("/{id}")
    private ResponseEntity<Author> update(@RequestBody Author author,
                                                @PathVariable("id") UUID maht) {

        Author authorCheck = authorImplService.getOne(maht);
        if (authorCheck == null) {
            return ResponseEntity.notFound().build();
        }
        author.setId(maht);
        Author authorUpdate = authorImplService.update(author);

        return ResponseEntity.ok(authorUpdate);


    }

    @DeleteMapping("/{id}")
    public OperationStatusModel delete(@PathVariable("id") UUID id){

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        authorImplService.delete(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());


        return returnValue;
    }

}
