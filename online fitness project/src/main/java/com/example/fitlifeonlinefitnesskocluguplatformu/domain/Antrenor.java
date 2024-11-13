package com.example.fitlifeonlinefitnesskocluguplatformu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "antrenor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Antrenor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ad")
    private String ad;

    @Column(name = "soyad")
    private String soyad;

    @Column(name = "cinsiyet")
    private String cinsiyet;

    @Column(name = "dogum_tarihi")
    private LocalDate dogumTarihi;

    @Column(name = "telefon_numarasi")
    private String telefonNumarasi;

    @Column(name = "email")
    private String email;

    @Column(name = "sifre")
    private String sifre;

    @Column(name = "profil_fotografi")
    private String profilFotografi;

    @Column(name = "aktif_mi")
    private boolean aktifMi = true;

    @Column(name = "baslangicKontenjani")
    private int baslangicKontenjani;

    @Column(name = "kalanKontenjan")
    private int kalanKontenjan;


}
