package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.BeslenmePlani;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeslenmePlaniRepo extends JpaRepository<BeslenmePlani,Integer> {
    List<BeslenmePlani> findByAntrenor(Antrenor antrenor);

    BeslenmePlani findById(int beslenmePlaniId);
}
