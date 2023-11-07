package com.example.TTWebBanHang.Service;

import com.example.TTWebBanHang.Entity.Realm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RealmService {

    Page<Realm> findAll(Pageable peageable);

    Realm getOne(UUID id);

    Realm add(Realm realm);

    Realm update(Realm realm);

    void delete(UUID id);
}
