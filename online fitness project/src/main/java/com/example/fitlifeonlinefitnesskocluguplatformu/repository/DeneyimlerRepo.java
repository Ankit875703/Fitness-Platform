package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Deneyimler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeneyimlerRepo extends JpaRepository<Deneyimler,Integer> {
    Deneyimler findDeneyimlerById(int deneyimId);

}

