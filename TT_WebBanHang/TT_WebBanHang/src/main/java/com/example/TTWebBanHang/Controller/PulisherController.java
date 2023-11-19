package com.example.TTWebBanHang.Controller;

import com.example.TTWebBanHang.Entity.Pulisher;
import com.example.TTWebBanHang.Entity.OperationStatusModel;
import com.example.TTWebBanHang.Entity.RequestOperationStatus;
import com.example.TTWebBanHang.Service.Impl.PulisherImplService;
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
@RequestMapping("savis/pulisher")
@CrossOrigin
public class PulisherController {

    @Autowired
    private PulisherImplService pulisherImplService;


    @GetMapping("")
    public ResponseEntity<Page<Pulisher>> findall(@RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<Pulisher> pulishers = pulisherImplService.findAll(pageable);
        return ResponseEntity.ok(pulishers);
    }

@GetMapping("list")
    private ResponseEntity<List<Pulisher>> getallPulisher() {
        List<Pulisher> pulisher =pulisherImplService.findAll();
        return ResponseEntity.ok(pulisher);
    }

    @PostMapping("")
    public  ResponseEntity<Pulisher> add(@RequestBody Pulisher pulisher){
        Pulisher pulisherSave = pulisherImplService.add(pulisher);
        return ResponseEntity.ok(pulisherSave);

    }

    @PutMapping("/{id}")
    private ResponseEntity<Pulisher> update(@RequestBody Pulisher pulisher,
                                          @PathVariable("id") UUID maht) {

        Pulisher pulisherCheck = pulisherImplService.getOne(maht);
        if (pulisherCheck == null) {
            return ResponseEntity.notFound().build();
        }
        pulisher.setId(maht);
        Pulisher pulisherUpdate = pulisherImplService.update(pulisher);

        return ResponseEntity.ok(pulisherUpdate);


    }

    @DeleteMapping("/{id}")
    public OperationStatusModel delete(@PathVariable("id") UUID id){

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        pulisherImplService.delete(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());


        return returnValue;
    }
    
}
