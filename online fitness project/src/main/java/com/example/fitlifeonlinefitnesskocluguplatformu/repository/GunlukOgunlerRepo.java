package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.GunlukOgunler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GunlukOgunlerRepo extends JpaRepository<GunlukOgunler,Integer> {

    GunlukOgunler findBySabahOgunuAndOgleOgunuAndAksamOgunu(boolean sabahOgunu, boolean ogleOgunu, boolean aksamOgunu);
}
