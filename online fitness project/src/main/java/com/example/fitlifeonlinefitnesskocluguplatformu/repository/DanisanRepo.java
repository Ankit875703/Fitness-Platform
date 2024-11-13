package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DanisanRepo extends JpaRepository<Danisan,Integer> {
    Danisan findDanisanById(Integer id);
}
