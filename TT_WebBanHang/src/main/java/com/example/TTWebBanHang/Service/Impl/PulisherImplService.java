package com.example.TTWebBanHang.Service.Impl;


import com.example.TTWebBanHang.Entity.ErrorMessages;
import com.example.TTWebBanHang.Entity.Pulisher;
import com.example.TTWebBanHang.Repository.PulisherRepository;
import com.example.TTWebBanHang.Service.PulisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.TTWebBanHang.Exceptions.AllServiceException;

import java.util.UUID;

@Service
public class PulisherImplService implements PulisherService {

    @Autowired
    PulisherRepository pulisherRepository;

    @Override
    public Page<Pulisher> findAll(Pageable pageable) {
        return pulisherRepository.findAll(pageable);
    }

    @Override
    public Pulisher getOne(UUID id) {
        return pulisherRepository.findById(id).orElse(null);
    }

    @Override
    public Pulisher add(Pulisher pulisher) {
        return pulisherRepository.save(pulisher);
    }

    @Override
    public Pulisher update(Pulisher pulisher) {
        return pulisherRepository.save(pulisher);
    }

    @Override
    public void delete(UUID id) {
        Pulisher pulisher = pulisherRepository.findById(id).orElse(null);

        if(pulisher == null){
            throw new AllServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        pulisherRepository.deleteById(id);
    }
}
