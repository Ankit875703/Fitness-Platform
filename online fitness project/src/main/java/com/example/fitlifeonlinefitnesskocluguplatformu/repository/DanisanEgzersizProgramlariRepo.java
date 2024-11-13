package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.DanisanEgzersizProgramlari;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DanisanEgzersizProgramlariRepo extends JpaRepository<DanisanEgzersizProgramlari,Integer> {
    List<DanisanEgzersizProgramlari> findDanisanEgzersizProgramlariByDanisan(Danisan danisan);

    DanisanEgzersizProgramlari findDanisanEgzersizProgramlariByAntrenorEgzersizProgramlari_Id(int programId);

    DanisanEgzersizProgramlari findDanisanEgzersizProgramlariById(int id);
}
