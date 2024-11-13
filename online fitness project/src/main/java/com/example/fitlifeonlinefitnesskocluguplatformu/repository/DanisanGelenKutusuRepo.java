package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.DanisanGelenKutusu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DanisanGelenKutusuRepo extends JpaRepository<DanisanGelenKutusu,Integer> {
   List<DanisanGelenKutusu> findDanisanGelenKutusuByDanisan(Danisan danisan);
}
