package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.IlerlemeKaydi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IlerlemeKaydiRepo extends JpaRepository<IlerlemeKaydi,Integer> {
    List<IlerlemeKaydi> findIlerlemeKaydiByDanisan(Danisan danisan);

    IlerlemeKaydi findIlerlemeKaydiById(int kayitId);

    @Query("SELECT ik FROM IlerlemeKaydi ik WHERE ik.danisan = :danisan AND ik.kayitTarihi = :gunTarih")
    List<IlerlemeKaydi> findIlerlemeKaydiByDanisanByKayitTarihi(@Param("danisan") Danisan danisan, @Param("gunTarih") LocalDate gunTarih);

    @Query("SELECT ik FROM IlerlemeKaydi ik " +
            "WHERE ik.danisan = :danisan " +
            "AND ik.kayitTarihi BETWEEN :bitisTarihi AND :baslangicTarihi")
    List<IlerlemeKaydi> findIlerlemeKaydiByDanisanAndTarihAraligi(
            @Param("danisan") Danisan danisan,
            @Param("bitisTarihi") LocalDate bitisTarihi,
            @Param("baslangicTarihi") LocalDate baslangicTarihi
    );

}
