package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntrenorRepo extends JpaRepository<Antrenor,Integer> {
    Antrenor findAntrenorById(Integer id);
}
