package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.DanisanBeslenmePlani;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DanisanBeslenmePlaniRepo extends JpaRepository<DanisanBeslenmePlani,Integer> {
    List<DanisanBeslenmePlani> findDanisanBeslenmePlaniByDanisan(Danisan danisan);
}
