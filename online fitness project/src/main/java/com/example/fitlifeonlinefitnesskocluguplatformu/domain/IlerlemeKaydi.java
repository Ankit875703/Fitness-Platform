package com.example.fitlifeonlinefitnesskocluguplatformu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "ilerlemeKaydi")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IlerlemeKaydi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "kilo")
    private double kilo;

    @Column(name = "boy")
    private double boy;

    @Column(name = "vucutYagOrani")
    private double vucutYagOrani;

    @Column(name = "kasKutlesi")
    private double kasKutlesi;

    @Column(name = "vki")
    private double vki;

    @ManyToOne
    @JoinColumn(name = "danisan_id", referencedColumnName = "id")
    private Danisan danisan;

    @Column(name = "kayitTarihi")
    private LocalDate kayitTarihi;
}
