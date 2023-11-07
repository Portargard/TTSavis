package com.example.TTWebBanHang.Controller;

import com.example.TTWebBanHang.Entity.Category;
import com.example.TTWebBanHang.Entity.OperationStatusModel;
import com.example.TTWebBanHang.Entity.Realm;
import com.example.TTWebBanHang.Entity.RequestOperationStatus;
import com.example.TTWebBanHang.Service.Impl.CategoryImplService;
import com.example.TTWebBanHang.Service.Impl.RealmImplService;
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
@RequestMapping("savis/realm")
public class RealmController {

    @Autowired
    RealmImplService realmImplService;

    @Autowired
    CategoryImplService categoryImplService;

    @GetMapping()
    private ResponseEntity<Page<Realm>> getall(@RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Realm> realms = realmImplService.findAll(pageable);
        return ResponseEntity.ok(realms);
    }

    @PostMapping()
    private ResponseEntity<Realm> add(@RequestBody Realm realm) {
        UUID maCategory = realm.getCategory().getId();
        Category category = categoryImplService.getOne(maCategory);
        realm.setCategory(category);

        Realm realmsave = realmImplService.add(realm);

        return ResponseEntity.ok(realmsave);

    }

    @PutMapping()
    private ResponseEntity<Realm> update(@RequestBody Realm realm,
                                                @PathVariable("id") UUID maht) {

        UUID idCategory = realm.getCategory().getId();
        Category categoryDCsave = categoryImplService.getOne(idCategory);

        realm.setCategory(categoryDCsave);

        Realm realmcheck = realmImplService.getOne(maht);
        if (realmcheck == null) {
            return ResponseEntity.notFound().build();
        }
        realmcheck.setId(maht);
        Realm realmupdate = realmImplService.update(realm);

        return ResponseEntity.ok(realmupdate);
    }

    @DeleteMapping("/{id}")
    public OperationStatusModel delete(@PathVariable("id") UUID id){

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        realmImplService.delete(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());


        return returnValue;
    }

}
