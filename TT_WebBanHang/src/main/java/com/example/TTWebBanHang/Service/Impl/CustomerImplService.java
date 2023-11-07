package com.example.TTWebBanHang.Service.Impl;

import com.example.TTWebBanHang.Entity.Customer;
import com.example.TTWebBanHang.Repository.CustomerRepo;
import com.example.TTWebBanHang.Service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class CustomerImplService implements CustomerService {
    @Autowired
    private CustomerRepo repo;

    @Override
    public Customer create(Customer customer) {
        this.repo.save(customer);
        return customer;
    }

    @Override
    public Customer update(Customer newCustomer, UUID id) {
        Customer customer = this.repo.findById(id).get();
        BeanUtils.copyProperties(newCustomer, customer);
        customer.setId(id);
        this.repo.save(customer);
        return customer;
    }

    @Override
    public void delete(UUID id) {
        Customer customer = this.repo.findById(id).get();
        this.repo.delete(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return repo.findAll();
    }

    @Override
    public Page<Customer> pagination(Pageable pageable) {
        return repo.findAll(pageable);
    }

}
