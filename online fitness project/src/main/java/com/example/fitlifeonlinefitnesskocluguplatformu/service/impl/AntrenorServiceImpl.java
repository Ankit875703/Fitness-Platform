package com.example.fitlifeonlinefitnesskocluguplatformu.service.impl;

import com.example.fitlifeonlinefitnesskocluguplatformu.EgzersizDurumu;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.BeslenmePlaniOlusturRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.DanisanaMesajGonderRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.DanisanaPlanAtaRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.EgzersizPlaniRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.*;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.*;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AntrenorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AntrenorServiceImpl implements AntrenorService {
    private AntrenorRepo antrenorRepo;
    private DanisanAntrenorEslesmesiRepo danisanAntrenorEslesmesiRepo;
    private DeneyimlerRepo deneyimlerRepo;
    private AntrenorDeneyimleriRepo antrenorDeneyimleriRepo;
    private AntrenorEgzersizProgramlariRepo antrenorEgzersizProgramlariRepo;
    private DanisanEgzersizProgramlariRepo danisanEgzersizProgramlariRepo;
    private DanisanRepo danisanRepo;
    private DanisanGelenKutusuRepo danisanGelenKutusuRepo;
    private AntrenorGelenKutusuRepo antrenorGelenKutusuRepo;
    private GunlukOgunlerRepo gunlukOgunlerRepo;
    private BeslenmePlaniRepo beslenmePlaniRepo;
    private DanisanBeslenmePlaniRepo danisanBeslenmePlaniRepo;
    private SifreSifirlamaMailiRepo sifreSifirlamaMailiRepo;

    @Override
    public boolean antrenorKaydiOlustur(String ad, String soyad, String cinsiyet, LocalDate dogumTarihi, String telefonNumarasi, String email, String sifre, String dosyaURL) {
            Antrenor yeniAntrenor=new Antrenor();
            yeniAntrenor.setAd(ad);
            yeniAntrenor.setSoyad(soyad);
            yeniAntrenor.setCinsiyet(cinsiyet);
            yeniAntrenor.setDogumTarihi(dogumTarihi);
            yeniAntrenor.setTelefonNumarasi(telefonNumarasi);
            yeniAntrenor.setEmail(email);
            yeniAntrenor.setSifre(sifre);
            yeniAntrenor.setProfilFotografi(dosyaURL);
            yeniAntrenor.setBaslangicKontenjani(5);
            yeniAntrenor.setKalanKontenjan(5);
            antrenorRepo.save(yeniAntrenor);
            System.out.println("Kayıt Başarılı!");
            return true;
    }

    @Override
    public Antrenor antrenorGirisi(String email, String sifre) {
        List<Antrenor> antrenorList=antrenorRepo.findAll();
        for (Antrenor antrenor:antrenorList) {
            if(antrenor.getEmail().equals(email) && antrenor.getSifre().equals(sifre)){
                return antrenor;
            }
        }
        return null;
    }

    @Override
    public boolean antrenorSifreDegistir(String email) {
        List<Antrenor> antrenorList=antrenorRepo.findAll();
        for (Antrenor antrenor:antrenorList) {
            if(antrenor.getEmail().equals(email)){
                String yeniSifre="antrenor"+antrenor.getId()+"yeniSifre";
                antrenor.setSifre(yeniSifre);
                antrenorRepo.save(antrenor);
                SifreSifirlamaMaili sifreSifirlamaMaili=new SifreSifirlamaMaili();
                sifreSifirlamaMaili.setEmail(email);
                sifreSifirlamaMaili.setYeniSifre(yeniSifre);
                sifreSifirlamaMailiRepo.save(sifreSifirlamaMaili);
                return true;
            }
        }
        return false;
    }

    @Override
    public Antrenor antrenorBul(String email) {
        List<Antrenor> antrenorList=antrenorRepo.findAll();
        for (Antrenor antrenor:antrenorList) {
            if(antrenor.getEmail().equals(email)){
                return antrenor;
            }
        }
        return null;
    }

    @Override
    public List<Antrenor> tumAntrenorleriGetir() {
        return antrenorRepo.findAll();
    }


    @Override
    public void profilimiGuncelle(Integer antrenorId, String ad, String soyad, String telefonNumarasi, String email, String sifre) {
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        antrenor.setAd(ad);
        antrenor.setSoyad(soyad);
        antrenor.setTelefonNumarasi(telefonNumarasi);
        antrenor.setEmail(email);
        antrenor.setSifre(sifre);
        antrenorRepo.save(antrenor);
    }

    @Override
    public List<Danisan> danisanlarimList(Integer antrenorId) {
        List<Danisan> danisanlarim=new ArrayList<>();
        List< DanisanAntrenorEslesmesi> danisanAntrenorEslesmesiList =danisanAntrenorEslesmesiRepo.findDanisanAntrenorEslesmesiByAntrenor_Id(antrenorId);
        for (DanisanAntrenorEslesmesi danisanlar:danisanAntrenorEslesmesiList) {
            danisanlarim.add(danisanlar.getDanisan());
        }
        return danisanlarim;
    }

    @Override
    public List<Deneyimler> deneyimList() {
        return deneyimlerRepo.findAll();
    }

    @Override
    public void deneyimEkle(int antrenorId, int deneyimId, boolean uzmanlikAlaniMi) {
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        Deneyimler deneyim=deneyimlerRepo.findDeneyimlerById(deneyimId);
        AntrenorDeneyimleri antrenorDeneyimleri=new AntrenorDeneyimleri();
        antrenorDeneyimleri.setAntrenor(antrenor);
        antrenorDeneyimleri.setDeneyim(deneyim);
        antrenorDeneyimleri.setUzmanlikAlaniMi(uzmanlikAlaniMi);
        antrenorDeneyimleriRepo.save(antrenorDeneyimleri);
    }

    @Override
    public void deneyimSil(int antrenorId, int deneyimId) {
        Deneyimler deneyim=deneyimlerRepo.findDeneyimlerById(deneyimId);
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        AntrenorDeneyimleri deneyimim= antrenorDeneyimleriRepo.findAntrenorDeneyimleriByAntrenorAndDeneyim(antrenor,deneyim);
        antrenorDeneyimleriRepo.delete(deneyimim);
    }

    @Override
    public void uzmanlikAlaniEkle(int antrenorId, int deneyimId) {
        Deneyimler deneyim=deneyimlerRepo.findDeneyimlerById(deneyimId);
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        AntrenorDeneyimleri deneyimim= antrenorDeneyimleriRepo.findAntrenorDeneyimleriByAntrenorAndDeneyim(antrenor,deneyim);
        deneyimim.setUzmanlikAlaniMi(true);
        antrenorDeneyimleriRepo.save(deneyimim);
    }

    @Override
    public List<AntrenorDeneyimleri> getAntrenorDeneyimler(int antrenorId) {
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        List<AntrenorDeneyimleri> deneyimlerimList= antrenorDeneyimleriRepo.findAntrenorDeneyimleriByAntrenor(antrenor);
        return deneyimlerimList;
    }

    @Override
    public List<Deneyimler> getAntrenorunSahipOlmadigiDeneyimler(int antrenorId) {
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        List<AntrenorDeneyimleri> deneyimlerimList= antrenorDeneyimleriRepo.findAntrenorDeneyimleriByAntrenor(antrenor);
        List<Integer> deneyimIdList=new ArrayList<>();
        List<Deneyimler> sahipOlunmayanDeneyimler=new ArrayList<>();
        for (AntrenorDeneyimleri deneyim:deneyimlerimList) {
            deneyimIdList.add(deneyim.getDeneyim().getId());
        }
        sahipOlunmayanDeneyimler=deneyimList().stream().filter(deneyimler -> !deneyimIdList.contains(deneyimler.getId())).collect(Collectors.toList());

        return sahipOlunmayanDeneyimler;
    }

    @Override
    public void uzmanlikAlaniKaldir(int antrenorId, int deneyimId) {
        Deneyimler deneyim=deneyimlerRepo.findDeneyimlerById(deneyimId);
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        AntrenorDeneyimleri deneyimim= antrenorDeneyimleriRepo.findAntrenorDeneyimleriByAntrenorAndDeneyim(antrenor,deneyim);
        deneyimim.setUzmanlikAlaniMi(false);
        antrenorDeneyimleriRepo.save(deneyimim);
    }

    @Override
    public void antrenorBilgileriGuncelle(Antrenor antrenor) {
        Antrenor antrenorunEskiBilgileri=antrenorRepo.findAntrenorById(antrenor.getId());
        antrenorunEskiBilgileri.setAd(antrenor.getAd());
        antrenorunEskiBilgileri.setSoyad(antrenor.getSoyad());
        antrenorunEskiBilgileri.setCinsiyet(antrenor.getCinsiyet());
        antrenorunEskiBilgileri.setDogumTarihi(antrenor.getDogumTarihi());
        antrenorunEskiBilgileri.setTelefonNumarasi(antrenor.getTelefonNumarasi());
        antrenorunEskiBilgileri.setEmail(antrenor.getEmail());
        antrenorunEskiBilgileri.setSifre(antrenor.getSifre());
        antrenorRepo.save(antrenorunEskiBilgileri);
    }

    @Override
    public void egzersizPlaniOlustur(EgzersizPlaniRequest request) {
        Antrenor antrenor=antrenorRepo.findAntrenorById(request.getAntrenorId());
        AntrenorEgzersizProgramlari antrenorEgzersizProgramlari=new AntrenorEgzersizProgramlari();
        antrenorEgzersizProgramlari.setAntrenor(antrenor);
        antrenorEgzersizProgramlari.setEgzersizAdi(request.getEgzersizAdi());
        antrenorEgzersizProgramlari.setEgzersizHedefi(request.getEgzersizHedefi());
        antrenorEgzersizProgramlari.setSetSayisi(request.getSetSayisi());
        antrenorEgzersizProgramlari.setTekrarSayisi(request.getTekrarSayisi());
        antrenorEgzersizProgramlari.setProgramBaslamaTarihi(request.getProgramBaslamaTarihi());
        antrenorEgzersizProgramlari.setProgramSuresi(request.getProgramSuresi());
        antrenorEgzersizProgramlariRepo.save(antrenorEgzersizProgramlari);
    }

    @Override
    public List<AntrenorEgzersizProgramlari> egzersizPlanlarim(int antrenorId) {
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        List<AntrenorEgzersizProgramlari> antrenorEgzersizProgramlari=antrenorEgzersizProgramlariRepo.findAntrenorEgzersizProgramlariByAntrenor(antrenor);
        return antrenorEgzersizProgramlari;
    }

    @Override
    public AntrenorEgzersizProgramlari egzersizPlanim(int egzersizId) {
        AntrenorEgzersizProgramlari egzersizPlanim=antrenorEgzersizProgramlariRepo.findAntrenorEgzersizProgramlariById(egzersizId);
        return egzersizPlanim;
    }

    @Override
    public void egzersizPlaniBilgileriGuncelle(AntrenorEgzersizProgramlari antrenorEgzersizProgrami) {
        AntrenorEgzersizProgramlari programim=antrenorEgzersizProgramlariRepo.findAntrenorEgzersizProgramlariById(antrenorEgzersizProgrami.getId());
        programim.setEgzersizAdi(antrenorEgzersizProgrami.getEgzersizAdi());
        programim.setEgzersizHedefi(antrenorEgzersizProgrami.getEgzersizHedefi());
        programim.setSetSayisi(antrenorEgzersizProgrami.getSetSayisi());
        programim.setTekrarSayisi(antrenorEgzersizProgrami.getTekrarSayisi());
        programim.setProgramBaslamaTarihi(antrenorEgzersizProgrami.getProgramBaslamaTarihi());
        programim.setProgramSuresi(antrenorEgzersizProgrami.getProgramSuresi());
        antrenorEgzersizProgramlariRepo.save(programim);
    }

    @Override
    public List<DanisanEgzersizProgramlari> getDanisaninEgzersizPlanlari(int danisanId) {
        Danisan danisan=danisanRepo.findDanisanById(danisanId);
        List<DanisanEgzersizProgramlari> danisaninProgramlari= danisanEgzersizProgramlariRepo.findDanisanEgzersizProgramlariByDanisan(danisan);
        return danisaninProgramlari;
    }

    @Override
    public DanisanEgzersizProgramlari getdanisaninEgzersizPlaniDetay(int planId) {
        DanisanEgzersizProgramlari danisanEgzersiziDetay=danisanEgzersizProgramlariRepo.findDanisanEgzersizProgramlariByAntrenorEgzersizProgramlari_Id(planId);
        return danisanEgzersiziDetay;
    }

    @Override
    public List<AntrenorEgzersizProgramlari> danisaninAlmadigiEgzersizPlanlari(int danisanId, int antrenorId) {
        Danisan danisan=danisanRepo.findDanisanById(danisanId);
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        List<AntrenorEgzersizProgramlari> antrenorProgramlari=antrenorEgzersizProgramlariRepo.findAntrenorEgzersizProgramlariByAntrenor(antrenor);
        List<DanisanEgzersizProgramlari> danisanEgzersizi=danisanEgzersizProgramlariRepo.findDanisanEgzersizProgramlariByDanisan(danisan);
        List<Integer> egzersizId=new ArrayList<>();
        for (DanisanEgzersizProgramlari program:danisanEgzersizi) {
            egzersizId.add(program.getAntrenorEgzersizProgramlari().getId());
        }
        antrenorProgramlari=antrenorProgramlari.stream().filter(id-> !egzersizId.contains(id.getId())).collect(Collectors.toList());
        return antrenorProgramlari;
    }

    @Override
    public void danisanaEgzersizPlaniAta(DanisanaPlanAtaRequest request) {
        Danisan danisan=danisanRepo.findDanisanById(request.getDanisanId());
        Antrenor antrenor=antrenorRepo.findAntrenorById(request.getAntrenorId());
        AntrenorEgzersizProgramlari program=antrenorEgzersizProgramlariRepo.findAntrenorEgzersizProgramlariById(request.getProgramId());
        DanisanEgzersizProgramlari programiAta=new DanisanEgzersizProgramlari();
        programiAta.setDanisan(danisan);
        programiAta.setAntrenor(antrenor);
        programiAta.setAntrenorEgzersizProgramlari(program);
        programiAta.setEgzersizDurumu(EgzersizDurumu.YAPILMADI);
        danisanEgzersizProgramlariRepo.save(programiAta);
    }

    @Override
    public void danisanaMesajGonder(DanisanaMesajGonderRequest request) {
        Danisan danisan=danisanRepo.findDanisanById(request.getDanisanId());
        Antrenor antrenor=antrenorRepo.findAntrenorById(request.getAntrenorId());
        DanisanGelenKutusu danisanGelenKutusu=new DanisanGelenKutusu();
        danisanGelenKutusu.setMesaj(request.getMesaj());
        danisanGelenKutusu.setDanisan(danisan);
        danisanGelenKutusu.setAntrenor(antrenor);
        danisanGelenKutusuRepo.save(danisanGelenKutusu);
    }

    @Override
    public List<AntrenorGelenKutusu> getGelenMesajlar(int antrenorId) {
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        List<AntrenorGelenKutusu> antrenorGelenKutusu= antrenorGelenKutusuRepo.findAntrenorGelenKutusuByAntrenor(antrenor);
        return antrenorGelenKutusu;
    }

    @Override
    public void beslenmePlaniOlustur(BeslenmePlaniOlusturRequest request) {
        Antrenor antrenor=antrenorRepo.findAntrenorById(request.getAntrenorId());
        boolean sabah=request.getGunlukOgunler().contains("sabah");
        boolean ogle = request.getGunlukOgunler().contains("ogle");
        boolean aksam = request.getGunlukOgunler().contains("aksam");
        GunlukOgunler gunlukOgunler=gunlukOgunlerRepo.findBySabahOgunuAndOgleOgunuAndAksamOgunu(sabah,ogle,aksam);
        BeslenmePlani beslenmePlani=new BeslenmePlani();
        beslenmePlani.setAntrenor(antrenor);
        beslenmePlani.setGunlukOgunler(gunlukOgunler);
        beslenmePlani.setKaloriHedefi(request.getKaloriHedefi());
        beslenmePlani.setEgzersizHedefi(request.getBeslenmePlaniHedef());
        beslenmePlaniRepo.save(beslenmePlani);
    }

    @Override
    public List<BeslenmePlani> antrenorBeslenmePlanlariList(int antrenorId) {
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        List<BeslenmePlani> antrenorunBeslenmePlanlari=beslenmePlaniRepo.findByAntrenor(antrenor);
        return antrenorunBeslenmePlanlari;
    }

    @Override
    public BeslenmePlani beslenmePlanim(int beslenmePlanId) {
        BeslenmePlani planim=beslenmePlaniRepo.findById(beslenmePlanId);
        return planim;
    }

    @Override
    public void beslenmePlaniniGuncelle(BeslenmePlani beslenmePlani) {
        int id=beslenmePlani.getId();
        BeslenmePlani plan=beslenmePlaniRepo.findById(id);
        plan.setEgzersizHedefi(beslenmePlani.getEgzersizHedefi());
        plan.setGunlukOgunler(beslenmePlani.getGunlukOgunler());
        plan.setKaloriHedefi(beslenmePlani.getKaloriHedefi());
        beslenmePlaniRepo.save(plan);
    }

    @Override
    public List<DanisanBeslenmePlani> getBeslenmePlani(int danisanId) {
        Danisan danisan=danisanRepo.findDanisanById(danisanId);
        List<DanisanBeslenmePlani> beslenmePlanlari=danisanBeslenmePlaniRepo.findDanisanBeslenmePlaniByDanisan(danisan);
        return beslenmePlanlari;
    }


    @Override
    public List<BeslenmePlani> danisaninAlmadigiBeslenmePlanlari(int danisanId, int antrenorId) {
        Danisan danisan = danisanRepo.findDanisanById(danisanId);
        Antrenor antrenor = antrenorRepo.findAntrenorById(antrenorId);
        List<BeslenmePlani> antrenorPlanlari = beslenmePlaniRepo.findByAntrenor(antrenor);
        List<DanisanBeslenmePlani> danisaninBeslenmePlanlari = danisanBeslenmePlaniRepo.findDanisanBeslenmePlaniByDanisan(danisan);

        List<Integer> planId = danisaninBeslenmePlanlari.stream().map(plan -> plan.getBeslenmePlani().getId()).collect(Collectors.toList());
        antrenorPlanlari = antrenorPlanlari.stream().filter(plan -> !planId.contains(plan.getId())).collect(Collectors.toList());
        return antrenorPlanlari;
    }

    @Override
    public void danisanaBeslenmePlaniAta(DanisanaPlanAtaRequest request) {
        Danisan danisan=danisanRepo.findDanisanById(request.getDanisanId());
        Antrenor antrenor=antrenorRepo.findAntrenorById(request.getAntrenorId());
        BeslenmePlani beslenmePlani=beslenmePlaniRepo.findById(request.getProgramId());
        DanisanBeslenmePlani danisanBeslenmePlani=new DanisanBeslenmePlani();
        danisanBeslenmePlani.setBeslenmePlani(beslenmePlani);
        danisanBeslenmePlani.setDanisan(danisan);
        danisanBeslenmePlani.setAntrenor(antrenor);
        danisanBeslenmePlaniRepo.save(danisanBeslenmePlani);
    }


}
