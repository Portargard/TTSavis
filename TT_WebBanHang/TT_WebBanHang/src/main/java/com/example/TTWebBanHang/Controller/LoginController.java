package com.example.TTWebBanHang.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TTWebBanHang.Entity.Account;
import com.example.TTWebBanHang.Entity.Customer;
import com.example.TTWebBanHang.Service.Impl.CustomerImplService;

@RestController
@RequestMapping("savis/login")
@CrossOrigin
public class LoginController {
    @Autowired
    private CustomerImplService service;

    
    @PostMapping()
    public Customer login(@RequestBody Account account) {
        System.out.println("customer is "+service.getOneByAccount(account));
        if (service.getOneByAccount(account)!=null) {
            return service.getOneByAccount(account);
        }
        return null;
    }
}
