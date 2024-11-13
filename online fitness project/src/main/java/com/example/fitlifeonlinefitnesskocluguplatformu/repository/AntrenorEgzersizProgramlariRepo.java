package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.AntrenorEgzersizProgramlari;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AntrenorEgzersizProgramlariRepo extends JpaRepository<AntrenorEgzersizProgramlari,Integer> {
    List<AntrenorEgzersizProgramlari> findAntrenorEgzersizProgramlariByAntrenor(Antrenor antrenor);

    AntrenorEgzersizProgramlari findAntrenorEgzersizProgramlariById(int egzersizId);
}
