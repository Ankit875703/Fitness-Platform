package com.example.fitlifeonlinefitnesskocluguplatformu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "danisanBeslenmePlani")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DanisanBeslenmePlani {
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
    @JoinColumn(name = "beslenmePLaniId", referencedColumnName = "id")
    private BeslenmePlani beslenmePlani;
}

