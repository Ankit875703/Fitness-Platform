package com.example.fitlifeonlinefitnesskocluguplatformu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "danisanGelenKutusu")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DanisanGelenKutusu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id otamatik arttırma
    @Column(name = "id")
    private Integer id;

    @Column(name = "mesaj")
    private String mesaj;

    @ManyToOne
    @JoinColumn(name = "antrenor_id", nullable = false)
    private Antrenor antrenor;

    @ManyToOne
    @JoinColumn(name = "danisan_id", nullable = false)
    private Danisan danisan;
}
