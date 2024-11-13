package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.AntrenorGelenKutusu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AntrenorGelenKutusuRepo extends JpaRepository<AntrenorGelenKutusu,Integer> {
    List<AntrenorGelenKutusu> findAntrenorGelenKutusuByAntrenor(Antrenor antrenor);
}
