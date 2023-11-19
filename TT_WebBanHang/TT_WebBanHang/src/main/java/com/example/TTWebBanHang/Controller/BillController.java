package com.example.TTWebBanHang.Controller;

import com.example.TTWebBanHang.Entity.Bill;
import com.example.TTWebBanHang.Entity.OperationStatusModel;
import com.example.TTWebBanHang.Entity.RequestOperationStatus;
import com.example.TTWebBanHang.Service.Impl.BillImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("savis/bill")
@CrossOrigin
public class BillController {
    
    @Autowired
    private BillImplService billImplService;

    @GetMapping("")
    public ResponseEntity<Page<Bill>> findall(@RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<Bill> bills = billImplService.findAll(pageable);
        return ResponseEntity.ok(bills);
    }

    @PostMapping("")
    public  ResponseEntity<Bill> add(@RequestBody Bill bill){
        Bill billSave = billImplService.add(bill);
        return ResponseEntity.ok(billSave);

    }

    @PutMapping("/{id}")
    private ResponseEntity<Bill> update(@RequestBody Bill bill,
                                          @PathVariable("id") UUID maht) {



        Bill billUpdate = billImplService.update(bill, maht);

        return ResponseEntity.ok(billUpdate);


    }

    @DeleteMapping("/{id}")
    public OperationStatusModel delete(@PathVariable("id") UUID id){

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        billImplService.delete(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());


        return returnValue;
    }

    @GetMapping("/customer/{id}")
    public Bill getBill(@PathVariable("id") UUID id){

        return billImplService.GetByIdCustomer(id);
    }


}
