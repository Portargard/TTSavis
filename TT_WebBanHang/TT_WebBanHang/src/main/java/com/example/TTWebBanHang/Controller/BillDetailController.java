package com.example.TTWebBanHang.Controller;

import com.example.TTWebBanHang.Entity.Bill;
import com.example.TTWebBanHang.Entity.BillDetail;
import com.example.TTWebBanHang.Entity.OperationStatusModel;
import com.example.TTWebBanHang.Entity.RequestOperationStatus;
import com.example.TTWebBanHang.Service.Impl.BillDetailImplService;
import com.example.TTWebBanHang.Service.Impl.BillImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("savis/bill-detail")
@CrossOrigin
public class BillDetailController {

    @Autowired
    private BillDetailImplService billDetailImplService;

    @Autowired
    private BillImplService billImplService;

    @GetMapping("")
    public ResponseEntity<List<BillDetail>> findall() {


        List<BillDetail> billDetails = billDetailImplService.findAll();
        return ResponseEntity.ok(billDetails);
    }

    @GetMapping("/bill/{id}")
    public ResponseEntity<List<BillDetail>> findallByBill(@PathVariable("id") UUID id) {
        Bill bill = billImplService.getOne(id);
        List<BillDetail> billDetails = billDetailImplService.getAllByBill(bill);
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
