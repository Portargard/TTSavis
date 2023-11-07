package com.example.TTWebBanHang.Repository;

import com.example.TTWebBanHang.Entity.Pulisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PulisherRepository extends JpaRepository<Pulisher, UUID> {
}
