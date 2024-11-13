package com.example.fitlifeonlinefitnesskocluguplatformu.api.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class KayitRequest {
    private String kullaniciTuru;
    private String ad;
    private String soyad;
    private String cinsiyet;
    private LocalDate dogumTarihi;
    private String telefonNumarasi;
    private String email;
    private String sifre;
}
