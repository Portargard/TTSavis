package com.example.TTWebBanHang.Service.Impl;

import com.example.TTWebBanHang.Entity.Cart;
import com.example.TTWebBanHang.Repository.CartRepo;
import com.example.TTWebBanHang.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartImplService implements CartService {
    @Autowired
    private CartRepo repo;

    @Override
    public Cart create(Cart cart) {
        this.repo.save(cart);
        return cart;
    }

    @Override
    public Cart update(Cart newCart, UUID id) {
        Cart cart = this.repo.findById(id).get();
        cart.setCustomer(newCart.getCustomer());
        this.repo.save(cart);
        return cart;
    }

    @Override
    public List<Cart> getAllCart() {
        return this.repo.findAll();
    }

    @Override
    public Page<Cart> pagination(Pageable pageable) {
        return this.repo.findAll(pageable);
    }

  
}
