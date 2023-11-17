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
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "Bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "Ma")
    private String ma;

    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "HinhThucThanhToan")
    private int hinhThucThanhToan;

    @Column(name = "NgayXacNhan")
    private Date ngayXacNhan;

    @Column(name = "NgayThanhToan")
    private Date ngayThanhToan;

    @Column(name = "NgayGuiHang")
    private Date ngayGuiHang;

    @Column(name = "NgayNhanHang")
    private Date ngayNhanHang;

    @Column(name = "SoTienThanhToan")
    private float soTienThanhToan;

    @Column(name = "TrangThai")
    private int trangThai;

}
