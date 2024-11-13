package com.example.fitlifeonlinefitnesskocluguplatformu.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "beslenmePlani")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BeslenmePlani {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id otamatik arttırma
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "antrenor_id", nullable = false)
    private Antrenor antrenor;

    @Column(name = "egzersizHedefi")
    private String egzersizHedefi;

    @ManyToOne
    @JoinColumn(name = "gunlukOgunler", referencedColumnName = "id")
    private GunlukOgunler gunlukOgunler;

    @Column(name = "kaloriHedefi")
    private int kaloriHedefi;

}
