package com.example.TTWebBanHang.Service;

import com.example.TTWebBanHang.Entity.Pulisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PulisherService {

    Page<Pulisher> findAll(Pageable pageable);

    List<Pulisher> findAll();

    Pulisher getOne(UUID id);

    Pulisher add(Pulisher pulisher);

    Pulisher update(Pulisher pulisher);

    void delete(UUID id);
}
