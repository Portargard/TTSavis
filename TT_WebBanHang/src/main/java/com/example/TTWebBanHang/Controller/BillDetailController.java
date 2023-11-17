package com.example.TTWebBanHang.Controller;

import com.example.TTWebBanHang.Entity.BillDetail;
import com.example.TTWebBanHang.Entity.OperationStatusModel;
import com.example.TTWebBanHang.Entity.RequestOperationStatus;
import com.example.TTWebBanHang.Service.Impl.BillDetailImplService;
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
@RequestMapping("savis/bill-detail")
public class BillDetailController {

    @Autowired
    private BillDetailImplService billDetailImplService;

    @GetMapping("")
    public ResponseEntity<Page<BillDetail>> findall(@RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<BillDetail> billDetails = billDetailImplService.findAll(pageable);
        return ResponseEntity.ok(billDetails);
    }

    @PostMapping("")
    public  ResponseEntity<BillDetail> add(@RequestBody BillDetail billDetail){
        BillDetail billDetailSave = billDetailImplService.add(billDetail);
        return ResponseEntity.ok(billDetailSave);

    }

    @PutMapping("/{id}")
    private ResponseEntity<BillDetail> update(@RequestBody BillDetail billDetail,
                                          @PathVariable("id") UUID maht) {

        BillDetail billDetailCheck = billDetailImplService.getOne(maht);
        if (billDetailCheck == null) {
            return ResponseEntity.notFound().build();
        }
        billDetail.setId(maht);
        BillDetail billDetailUpdate = billDetailImplService.update(billDetail);

        return ResponseEntity.ok(billDetailUpdate);


    }

    @DeleteMapping("/{id}")
    public OperationStatusModel delete(@PathVariable("id") UUID id){

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        billDetailImplService.delete(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());


        return returnValue;
    }
}
