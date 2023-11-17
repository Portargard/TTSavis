package com.example.TTWebBanHang.Controller;

import com.example.TTWebBanHang.Entity.Customer;
import com.example.TTWebBanHang.Service.Impl.CustomerImplService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("savis/customer")
public class CustomerController {
    @Autowired
    private CustomerImplService service;

    

    @PostMapping()
    public Customer AddCustomer(@RequestBody Customer customer) {
        
        return service.create(customer);
    }

    @PutMapping("/{id}")
    public Customer UpdateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {
        UUID customerUuid = UUID.fromString(id);
        return service.update(customer, customerUuid);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") UUID id) {
        service.delete(id);
    }

    @GetMapping()
    public List<Customer> CustomerPagination() {
        List<Customer> customerPage = service.getAllCustomer();

        return customerPage;
    }

    @GetMapping("/{id}")
    public Customer GetOne(@PathVariable("id") String id) {
        UUID customerId = UUID.fromString(id);
        return service.getOne(customerId);
    }

}
