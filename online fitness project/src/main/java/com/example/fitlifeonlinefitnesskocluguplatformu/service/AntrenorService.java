package com.example.fitlifeonlinefitnesskocluguplatformu.service;

import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.BeslenmePlaniOlusturRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.DanisanaMesajGonderRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.DanisanaPlanAtaRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.EgzersizPlaniRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface AntrenorService {
    boolean antrenorKaydiOlustur(String ad, String soyad,  String cinsiyet,LocalDate dogumTarihi, String telefonNumarasi, String email, String sifre,String dosyaURL);

    Antrenor antrenorGirisi(String email, String sifre);

    boolean antrenorSifreDegistir(String email);

    Antrenor antrenorBul(String email);

    List<Antrenor> tumAntrenorleriGetir();

    void profilimiGuncelle(Integer antrenorId,String ad, String soyad, String telefonNumarasi, String email, String sifre);

    List<Danisan> danisanlarimList(Integer antrenorId);

    List<Deneyimler> deneyimList();

    void deneyimEkle(int antrenorId,int deneyimId,boolean uzmanlikAlaniMi);

    void deneyimSil(int antrenorId, int deneyimId);

    void uzmanlikAlaniEkle(int antrenorId, int deneyimId);

    List<AntrenorDeneyimleri> getAntrenorDeneyimler(int antrenorId);

    List<Deneyimler> getAntrenorunSahipOlmadigiDeneyimler(int antrenorId);

    void uzmanlikAlaniKaldir(int antrenorId, int deneyimId);

    void antrenorBilgileriGuncelle(Antrenor antrenor);

    void egzersizPlaniOlustur(EgzersizPlaniRequest request);

    List<AntrenorEgzersizProgramlari> egzersizPlanlarim(int antrenorId);

    AntrenorEgzersizProgramlari egzersizPlanim(int egzersizId);

    void egzersizPlaniBilgileriGuncelle(AntrenorEgzersizProgramlari antrenorEgzersizProgrami);

    List<DanisanEgzersizProgramlari> getDanisaninEgzersizPlanlari(int danisanId);

    DanisanEgzersizProgramlari getdanisaninEgzersizPlaniDetay(int planId);

    List<AntrenorEgzersizProgramlari> danisaninAlmadigiEgzersizPlanlari(int danisanId,int antrenorId);

    void danisanaEgzersizPlaniAta(DanisanaPlanAtaRequest request);

    void danisanaMesajGonder(DanisanaMesajGonderRequest request);

    List<AntrenorGelenKutusu> getGelenMesajlar(int antrenorId);

    void beslenmePlaniOlustur(BeslenmePlaniOlusturRequest request);

    List<BeslenmePlani> antrenorBeslenmePlanlariList(int antrenorId);

    BeslenmePlani beslenmePlanim(int beslenmePlanId);

    void beslenmePlaniniGuncelle(BeslenmePlani beslenmePlani);

    List<DanisanBeslenmePlani> getBeslenmePlani(int danisanId);

    List<BeslenmePlani> danisaninAlmadigiBeslenmePlanlari(int danisanId,int antrenorId);

    void danisanaBeslenmePlaniAta(DanisanaPlanAtaRequest request);

}
