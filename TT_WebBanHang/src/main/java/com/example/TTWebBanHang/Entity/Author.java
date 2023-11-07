package com.example.TTWebBanHang.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "tentacgia")
    private String tenTacGia;

    @Column(name = "ngaysinh")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaySinh;

    @Column(name = "ngaymat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayMat;

    @Lob
    @Column(name = "tieusu")
    private String tieuSu;
}
