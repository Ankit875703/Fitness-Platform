package com.example.fitlifeonlinefitnesskocluguplatformu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "sifreSifirlamaMaili")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SifreSifirlamaMaili {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "yeniSifre")
    private String yeniSifre;
}
