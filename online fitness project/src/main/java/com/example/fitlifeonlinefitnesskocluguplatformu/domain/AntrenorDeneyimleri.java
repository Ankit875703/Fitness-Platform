package com.example.fitlifeonlinefitnesskocluguplatformu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "antrenorDeneyimleri")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AntrenorDeneyimleri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "antrenor_id", referencedColumnName = "id")
    private Antrenor antrenor;

    @ManyToOne
    @JoinColumn(name = "deneyim_id", referencedColumnName = "id")
    private Deneyimler deneyim;

    @Column(name = "uzmanlik_alani_mi")
    private boolean uzmanlikAlaniMi; // true ise uzmanlık alanı, false ise deneyim
}
