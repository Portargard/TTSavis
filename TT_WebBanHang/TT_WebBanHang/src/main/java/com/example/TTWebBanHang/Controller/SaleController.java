package com.example.TTWebBanHang.Controller;

import com.example.TTWebBanHang.Entity.Sale;
import com.example.TTWebBanHang.Entity.OperationStatusModel;
import com.example.TTWebBanHang.Entity.RequestOperationStatus;
import com.example.TTWebBanHang.Service.Impl.SaleImplService;
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
@RequestMapping("savis/sale")
public class SaleController {

    @Autowired
    private SaleImplService saleImplService;


    @GetMapping("")
    public ResponseEntity<Page<Sale>> findall(@RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<Sale> sales = saleImplService.findAll(pageable);
        return ResponseEntity.ok(sales);
    }

    @PostMapping("")
    public  ResponseEntity<Sale> add(@RequestBody Sale sale){
        Sale saleSave = saleImplService.add(sale);
        return ResponseEntity.ok(saleSave);

    }

    @PutMapping("/{id}")
    private ResponseEntity<Sale> update(@RequestBody Sale sale,
                                          @PathVariable("id") UUID maht) {

        Sale saleCheck = saleImplService.getOne(maht);
        if (saleCheck == null) {
            return ResponseEntity.notFound().build();
        }
        sale.setId(maht);
        Sale saleUpdate = saleImplService.update(sale);

        return ResponseEntity.ok(saleUpdate);


    }

    @DeleteMapping("/{id}")
    public OperationStatusModel delete(@PathVariable("id") UUID id){

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        saleImplService.delete(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());


        return returnValue;
    }
}
