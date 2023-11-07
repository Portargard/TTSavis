package com.example.TTWebBanHang.Service.Impl;

import com.example.TTWebBanHang.Entity.Book;
import com.example.TTWebBanHang.Entity.ErrorMessages;
import com.example.TTWebBanHang.Entity.Realm;
import com.example.TTWebBanHang.Exceptions.AllServiceException;
import com.example.TTWebBanHang.Repository.RealmRepository;
import com.example.TTWebBanHang.Service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RealmImplService implements RealmService {

    @Autowired
    RealmRepository realmRepository;

    @Override
    public Page<Realm> findAll(Pageable peageable) {
        return realmRepository.findAll(peageable);
    }

    @Override
    public Realm getOne(UUID id) {
        return realmRepository.findById(id).orElse(null);
    }

    @Override
    public Realm add(Realm realm) {
        return realmRepository.save(realm);
    }

    @Override
    public Realm update(Realm realm) {
        return realmRepository.save(realm);
    }

    @Override
    public void delete(UUID id) {
        Realm realm = realmRepository.findById(id).orElse(null);

        if(realm == null){
            throw new AllServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        realmRepository.deleteById(id);
    }
}
