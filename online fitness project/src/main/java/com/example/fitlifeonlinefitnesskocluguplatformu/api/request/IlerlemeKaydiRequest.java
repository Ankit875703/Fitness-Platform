package com.example.fitlifeonlinefitnesskocluguplatformu.api.request;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IlerlemeKaydiRequest {
    private double kilo;
    private double boy;
    private double vucutYagOrani;
    private double kasKutlesi;
    private double vki;
    private int danisanId;
}
