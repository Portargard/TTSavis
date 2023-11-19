package com.example.TTWebBanHang.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

     @Column(name = "ma")
    private String ma;

     @Column(name = "ten")
    private String ten;

     @ManyToOne
    @JoinColumn(name = "realmid")
    private Realm realm;

     @ManyToOne
    @JoinColumn(name = "authorid")
    private Author author;

     @ManyToOne
    @JoinColumn(name = "pulisherid")
    private Pulisher pulisher;

     @ManyToOne
    @JoinColumn(name = "saleid")
    private Sale sale;

     @Column(name = "trongluong")
    private Double trongLuong;

     @Column(name = "soluong")
    private Integer soLuong;

     @Column(name = "soluongdaban")
    private Integer soLuongDaBan;

     @Column(name = "gianhap")
    private Double giaNhap;

     @Column(name = "giaban")
    private Double giaBan;

     @Lob
    @Column(name = "hinhanh")
    private byte[] hinhAnh;

     @Lob
    @Column(name = "mota")
    private String moTa;

     @Column(name = "trangthai")
    private Integer trangThai;


}
