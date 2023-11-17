package com.example.TTWebBanHang.Service.Impl;

import com.example.TTWebBanHang.Entity.Customer;
import com.example.TTWebBanHang.Repository.CustomerRepo;
import com.example.TTWebBanHang.Service.CustomerService;
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
        String ma = "KH" + (repo.count() + 1);
        customer.setMa(ma);
        repo.save(customer);
        return customer;
    }

    @Override
    public Customer update(Customer newCustomer, UUID id) {
        Customer customer = this.repo.findById(id).get();
        customer.setHoTen(newCustomer.getHoTen());
        customer.setSdt(newCustomer.getSdt());
        customer.setDiaChi(newCustomer.getDiaChi());
        customer.setGioiTinh(newCustomer.getGioiTinh());
        customer.setEmail(newCustomer.getEmail());
        customer.setUsername(newCustomer.getUsername());
        customer.setPassword(newCustomer.getPassword());
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

    @Override
    public Customer getOne(UUID id) {
        return repo.findById(id).get();
    }

}
