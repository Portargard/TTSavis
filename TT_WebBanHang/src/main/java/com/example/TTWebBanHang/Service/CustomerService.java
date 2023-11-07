package com.example.TTWebBanHang.Service;

import com.example.TTWebBanHang.Entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    public Customer create(Customer customer);
    public Customer update(Customer customer, UUID id);
    public void delete(UUID id);
    public List<Customer> getAllCustomer();
    public Page<Customer> pagination(Pageable pageable);
}
