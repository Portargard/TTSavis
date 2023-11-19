package com.example.TTWebBanHang.Controller;

import com.example.TTWebBanHang.Entity.Category;
import com.example.TTWebBanHang.Entity.OperationStatusModel;
import com.example.TTWebBanHang.Entity.RequestOperationStatus;
import com.example.TTWebBanHang.Service.Impl.CategoryImplService;
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
@RequestMapping("savis/category")
public class CategoryController {
    @Autowired
    private CategoryImplService categoryImplService;


    @GetMapping("")
    public ResponseEntity<Page<Category>> findall(@RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<Category> categorys = categoryImplService.findAll(pageable);
        return ResponseEntity.ok(categorys);
    }

    @PostMapping("")
    public  ResponseEntity<Category> add(@RequestBody Category category){
        Category categorySave = categoryImplService.add(category);
        return ResponseEntity.ok(categorySave);

    }

    @PutMapping("/{id}")
    private ResponseEntity<Category> update(@RequestBody Category category,
                                          @PathVariable("id") UUID maht) {

        Category categoryCheck = categoryImplService.getOne(maht);
        if (categoryCheck == null) {
            return ResponseEntity.notFound().build();
        }
        category.setId(maht);
        Category categoryUpdate = categoryImplService.update(category);

        return ResponseEntity.ok(categoryUpdate);


    }

    @DeleteMapping("/{id}")
    public OperationStatusModel delete(@PathVariable("id") UUID id){

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        categoryImplService.delete(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());


        return returnValue;
    }
    
}
