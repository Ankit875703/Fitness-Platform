package com.example.fitlifeonlinefitnesskocluguplatformu.service;

import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.AntrenoreMesajGonderRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.DanisanGuncellemeRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.IlerlemeKaydiGuncelleRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.IlerlemeKaydiRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface DanisanService {
    boolean danisanKaydiOlustur(String ad, String soyad,  String cinsiyet,LocalDate dogumTarihi, String telefonNumarasi, String email, String sifre,String dosyaURL);

    Danisan danisanGirisi(String email, String sifre);

    boolean danisanSifreDegistir(String email);

    Danisan danisanBul(String email);

    List<Danisan> tumDanisanlariGetir();

    void danisanGuncelle(DanisanGuncellemeRequest request);

    List<DanisanEgzersizProgramlari> danisanProgramlist(int danisanId);

   AntrenorEgzersizProgramlari getEgzersizPlanDetaylari(int egzersizId);

   void egzersizPlaniniTamamla(int danisanEgzersizProgramlariId);

   void antrenoreMesajGonder(AntrenoreMesajGonderRequest request);

   List<DanisanGelenKutusu> danisanGelenKutusu(int danisanId);

   List<DanisanBeslenmePlani> beslenmePlanlarimiBul(int danisanId);

   BeslenmePlani getBeslenmePlaniminDetayi(int beslenmePlanId);

   void danisanIlerlemeKaydiEkleme(IlerlemeKaydiRequest request);

   List<IlerlemeKaydi> getIlerlemeKayitlarim(int danisanId);

    IlerlemeKaydi getIlerlemeKaydiDetay(int kayitId);

    void ilerlemeKaydiGuncelle(IlerlemeKaydiGuncelleRequest request);

    List<IlerlemeKaydi> danisanGunlukIlerlemeKaydiRaporu(int danisanId, LocalDate gunTarih);

    List<IlerlemeKaydi> danisanHaftalikIlerlemeKaydiRaporu(int danisanId, LocalDate baslangicTarih);

    List<DanisanHedefleri> getDanisanHedefleri(int danisanId);

    List<Deneyimler> deneyimList();

    List<Deneyimler> getDanisaninSahipOlmadigiHedefler(int danisanId);

    void hedefEkle(int danisanId, int hedefId);

    void hedefSil(int danisanId, int hedefId);
}
