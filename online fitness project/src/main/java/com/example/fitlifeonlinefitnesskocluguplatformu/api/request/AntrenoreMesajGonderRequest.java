package com.example.fitlifeonlinefitnesskocluguplatformu.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AntrenoreMesajGonderRequest {
    private String mesaj;
    private int antrenorId;
     private int danisanId;
}
