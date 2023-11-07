package com.example.TTWebBanHang.Service;

import com.example.TTWebBanHang.Entity.CartDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

public interface CartDetailService {
    public CartDetail create(CartDetail cart);
    public CartDetail update(CartDetail cart, UUID id);
    public List<CartDetail> getAllCart();
    public Page<CartDetail> pagination(Pageable pageable);
}
