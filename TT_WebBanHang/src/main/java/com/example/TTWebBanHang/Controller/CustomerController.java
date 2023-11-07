package com.example.TTWebBanHang.Controller;

import com.example.TTWebBanHang.Entity.Customer;
import com.example.TTWebBanHang.Service.Impl.CustomerImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

@CrossOrigin
@RestController
@RequestMapping("savis/customer")
public class CustomerController {
    @Autowired
    private CustomerImplService service;

//    @GetMapping()
//    public List<Customer> ListCustomer(){
//        return service.getAllCustomer();
//    }
    
    @PostMapping()
    public Customer AddCustomer(@RequestBody Customer customer){
        return service.create(customer);
    }
    
    @PutMapping("{id}")
    public Customer UpdateCustomer(@PathVariable("id") UUID id, @RequestBody Customer customer){
        return service.update(customer, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") UUID id){
        service.delete(id);
    }


    @GetMapping()
    public Page<Customer> CustomerPagination(@RequestParam(name = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 10, Sort.by("hoTen"));
        Page<Customer> customerPage = service.pagination(pageable);

        return customerPage;
    }
}
