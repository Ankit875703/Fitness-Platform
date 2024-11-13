package com.example.fitlifeonlinefitnesskocluguplatformu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "danisanHedefleri")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DanisanHedefleri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "danisan_id", referencedColumnName = "id")
    private Danisan danisan;

    @ManyToOne
    @JoinColumn(name = "hedef_id", referencedColumnName = "id")
    private Deneyimler deneyim;

}
