package com.example.fitlifeonlinefitnesskocluguplatformu.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeslenmePlaniOlusturRequest {
    private int kaloriHedefi;
    private String gunlukOgunler;
    private String beslenmePlaniHedef;
    private int antrenorId;

}

