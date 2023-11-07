package com.example.TTWebBanHang.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "CartDetail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "CartID", referencedColumnName = "ID")
    private Cart cart;

    @ManyToOne()
    @JoinColumn(name = "BookID", referencedColumnName = "ID")
    private Book book;

    @Column(name = "SoLuong")
    private int soLuong;
}
