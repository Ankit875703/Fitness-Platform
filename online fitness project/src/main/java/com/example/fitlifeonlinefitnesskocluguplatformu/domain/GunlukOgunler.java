package com.example.fitlifeonlinefitnesskocluguplatformu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "gunlukOgunler")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GunlukOgunler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id otamatik arttırma
    @Column(name = "id")
    private Integer id;

    @Column(name = "sabahOgunu")
    private boolean sabahOgunu;

    @Column(name = "ogleOgunu")
    private boolean ogleOgunu;

    @Column(name = "aksamOgunu")
    private boolean aksamOgunu;
}
