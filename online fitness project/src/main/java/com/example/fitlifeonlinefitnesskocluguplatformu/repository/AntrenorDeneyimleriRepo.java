package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.AntrenorDeneyimleri;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Deneyimler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AntrenorDeneyimleriRepo extends JpaRepository<AntrenorDeneyimleri,Integer> {
    AntrenorDeneyimleri findAntrenorDeneyimleriByAntrenorAndDeneyim(Antrenor antrenor, Deneyimler deneyim);

    List<AntrenorDeneyimleri> findAntrenorDeneyimleriByAntrenor(Antrenor antrenor);

    @Query("SELECT ad.antrenor FROM AntrenorDeneyimleri ad WHERE ad.deneyim.id = :deneyimId AND ad.uzmanlikAlaniMi = true AND ad.antrenor.kalanKontenjan>0 AND ad.antrenor.aktifMi= true")
    List<Antrenor> findAntrenorByDeneyim_Id(int deneyimId);


}
