package com.example.TTWebBanHang.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "giatri")
    private Double giaTri;

    @Column(name = "ngaybatdau")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayBatDau;

    @Column(name = "ngayketthuc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayKetThuc;

    @Column(name = "trangthai")
    private Integer trangThai;



}
