package com.example.fitlifeonlinefitnesskocluguplatformu.api.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class EgzersizPlaniRequest {
    private String egzersizAdi;
    private String egzersizHedefi;
    private int setSayisi;
    private int tekrarSayisi;
    private LocalDate programBaslamaTarihi;
    private int programSuresi;
    private int antrenorId;
}
