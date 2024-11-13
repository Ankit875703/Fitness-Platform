package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DanisanHedefleriRepo extends JpaRepository<DanisanHedefleri,Integer> {

    List<DanisanHedefleri> findDanisanHedefleriByDanisan(Danisan danisan);

    DanisanHedefleri findDanisanHedefleriByDanisanAndDeneyim(Danisan danisan, Deneyimler deneyim);

    @Query("SELECT dh.danisan FROM DanisanHedefleri dh WHERE dh.deneyim.id = :deneyimId AND dh.danisan.aktifMi= true")
    List<Danisan> findDanisanByDeneyim_Id(int deneyimId);

}
