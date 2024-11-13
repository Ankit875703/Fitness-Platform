package com.example.fitlifeonlinefitnesskocluguplatformu.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DanisanaMesajGonderRequest {
    private String mesaj;
    private int danisanId;
    private int antrenorId;
}
