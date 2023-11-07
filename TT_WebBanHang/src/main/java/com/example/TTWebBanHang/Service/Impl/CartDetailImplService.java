package com.example.TTWebBanHang.Service.Impl;

import com.example.TTWebBanHang.Entity.CartDetail;
import com.example.TTWebBanHang.Repository.CartDetailRepo;
import com.example.TTWebBanHang.Service.CartDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class CartDetailImplService implements CartDetailService {
    @Autowired
    private CartDetailRepo repo;


    @Override
    public CartDetail create(CartDetail cart) {
        this.repo.save(cart);
        return cart;
    }

    @Override
    public CartDetail update(CartDetail cart, UUID id) {
        CartDetail cartDetail = this.repo.findById(id).get();
        BeanUtils.copyProperties(cart, cartDetail);
        cartDetail.setId(id);
        this.repo.save(cartDetail);
        return cartDetail;
    }

    @Override
    public List<CartDetail> getAllCart() {
        return this.repo.findAll();
    }

    @Override
    public Page<CartDetail> pagination(Pageable pageable) {
        return this.repo.findAll(pageable);
    }
}
