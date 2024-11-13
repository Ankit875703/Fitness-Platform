package com.example.fitlifeonlinefitnesskocluguplatformu.api.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestPart;

@Getter
@Setter
public class DanisanGuncellemeRequest {
    private Integer id;
    private String ad;
    private String soyad;
    private String telefonNumarasi;
    private String email;
    private String sifre;
}
