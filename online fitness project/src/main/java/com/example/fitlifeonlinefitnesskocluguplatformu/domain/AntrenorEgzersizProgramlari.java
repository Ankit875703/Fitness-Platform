package com.example.fitlifeonlinefitnesskocluguplatformu.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "antrenorEgzersizProgramlari")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AntrenorEgzersizProgramlari {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "egzersizAdi")
    private String egzersizAdi;

    @Column(name = "egzersizHedefi")
    private String egzersizHedefi;

    @Column(name = "setSayisi")
    private int setSayisi;

    @Column(name = "tekrarSayisi")
    private int tekrarSayisi;

    @Column(name = "programBaslamaTarihi")
    private LocalDate programBaslamaTarihi;

    @Column(name = "programSuresi")
    private int programSuresi;

    @ManyToOne
    @JoinColumn(name = "antrenor_id", referencedColumnName = "id")
    private Antrenor antrenor;
}
