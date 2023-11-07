package com.example.TTWebBanHang.Controller;

import com.example.TTWebBanHang.Entity.Cart;
import com.example.TTWebBanHang.Service.Impl.CartImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("savis/cart")
public class CartController {
    @Autowired
    private CartImplService service;

    @PostMapping()
    public Cart Add(@RequestBody Cart cart){
        return service.create(cart);
    }

    @PutMapping()
    public Cart Update(@RequestParam("id") UUID id, @RequestBody Cart cart){
        return service.update(cart, id);
    }

    @GetMapping()
    public List<Cart> Pagination(@RequestParam(name = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Cart> customerPage = service.pagination(pageable);

        return customerPage.getContent();
    }
}
