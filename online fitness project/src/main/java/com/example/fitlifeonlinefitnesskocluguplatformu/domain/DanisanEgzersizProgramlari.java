package com.example.fitlifeonlinefitnesskocluguplatformu.domain;

import com.example.fitlifeonlinefitnesskocluguplatformu.EgzersizDurumu;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "danisanEgzersizProgramlari")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DanisanEgzersizProgramlari {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "antrenor_id", referencedColumnName = "id")
    private Antrenor antrenor;

    @ManyToOne
    @JoinColumn(name = "danisan_id", referencedColumnName = "id")
    private Danisan danisan;

    @ManyToOne
    @JoinColumn(name = "egzersizId", referencedColumnName = "id")
    private AntrenorEgzersizProgramlari antrenorEgzersizProgramlari;

    @Enumerated(EnumType.STRING)
    @Column(name = "egzersiz_durumu")
    private EgzersizDurumu egzersizDurumu;

}
