package com.example.fitlifeonlinefitnesskocluguplatformu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "deneyimler")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Deneyimler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "deneyim")
    private String deneyim; // kilo aldırma, kilo verdirme, kilo koruma, kas kazanma
}
