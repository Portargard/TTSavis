package com.example.TTWebBanHang.Service;

import com.example.TTWebBanHang.Entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

public interface CartService {
    public Cart create(Cart cart);
    public Cart update(Cart cart, UUID id);
    public List<Cart> getAllCart();
    public Page<Cart> pagination(Pageable pageable);
}
