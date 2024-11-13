package com.example.fitlifeonlinefitnesskocluguplatformu.service.impl;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.*;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.*;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepo adminRepo;
    private DanisanRepo danisanRepo;
    private AntrenorRepo antrenorRepo;
    private SifreSifirlamaMailiRepo sifreSifirlamaMailiRepo;
    private DanisanHedefleriRepo danisanHedefleriRepo;
    private AntrenorDeneyimleriRepo antrenorDeneyimleriRepo;
    private DanisanAntrenorEslesmesiRepo danisanAntrenorEslesmesiRepo;
    private DeneyimlerRepo deneyimlerRepo;

    @Override
    public Admin adminGirisi(String email, String sifre) {
        List<Admin> adminList=adminRepo.findAll();
        for (Admin admin:adminList) {
            if(admin.getEmail().equals(email) && admin.getSifre().equals(sifre)){
                return admin;
            }
        }
        return null;
    }

    @Override
    public boolean adminSifreDegistir(String email) {
        List<Admin> adminList=adminRepo.findAll();
        for (Admin admin:adminList) {
            if(admin.getEmail().equals(email)){
                String yeniSifre="adminYeniSifre";
                admin.setSifre(yeniSifre);
                adminRepo.save(admin);
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
    public Admin adminBul(String email) {
        List<Admin> adminList = adminRepo.findAll();
        for (Admin admin : adminList) {
            if (admin.getEmail().equals(email)) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public void danisanBilgileriniGuncelle(Danisan danisan) {
        Danisan danisaninEskiBilgileri=danisanRepo.findDanisanById(danisan.getId());
        danisaninEskiBilgileri.setAd(danisan.getAd());
        danisaninEskiBilgileri.setSoyad(danisan.getSoyad());
        danisaninEskiBilgileri.setCinsiyet(danisan.getCinsiyet());
        danisaninEskiBilgileri.setDogumTarihi(danisan.getDogumTarihi());
        danisaninEskiBilgileri.setTelefonNumarasi(danisan.getTelefonNumarasi());
        danisaninEskiBilgileri.setEmail(danisan.getEmail());
        danisaninEskiBilgileri.setSifre(danisan.getSifre());
        danisanRepo.save(danisaninEskiBilgileri);
    }

    @Override
    public void antrenorBilgileriniGuncelle(Antrenor antrenor) {
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
    public void danisanDevreDisiBirak(Danisan danisan) {
        Danisan guncelDanisan=danisanRepo.findDanisanById(danisan.getId());
        guncelDanisan.setAktifMi(false);
        danisanRepo.save(guncelDanisan);
    }

    @Override
    public void antrenorDevreDisiBirak(Antrenor antrenor) {
        Antrenor guncelAntrenor=antrenorRepo.findAntrenorById(antrenor.getId());
        guncelAntrenor.setAktifMi(false);
        antrenorRepo.save(guncelAntrenor);
    }

    @Override
    public void danisanEtkinlestir(Danisan danisan) {
        Danisan guncelDanisan=danisanRepo.findDanisanById(danisan.getId());
        guncelDanisan.setAktifMi(true);
        danisanRepo.save(guncelDanisan);
    }

    @Override
    public void antrenorEtkinlestir(Antrenor antrenor) {
        Antrenor guncelAntrenor=antrenorRepo.findAntrenorById(antrenor.getId());
        guncelAntrenor.setAktifMi(true);
        antrenorRepo.save(guncelAntrenor);
    }

    @Override
    public boolean mailKullaniliyorMu(String email) {
        List<Danisan>danisanList=danisanRepo.findAll();
        List<Antrenor> antrenorList=antrenorRepo.findAll();
        for (Antrenor antrenor: antrenorList) {
            if(antrenor.getEmail().equals(email)){
                return true;
            }
        }
        for (Danisan danisan: danisanList) {
            if(danisan.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public List<DanisanAntrenorEslesmesi> danisanAntrenorEslesmeAtamasi() {
        List<Integer> deneyimIdList = Arrays.asList(1, 2, 3, 4);

        for (Integer deneyimId : deneyimIdList) {
            List<Danisan> danisanList = danisanHedefleriRepo.findDanisanByDeneyim_Id(deneyimId);
            List<Antrenor> antrenorList = antrenorDeneyimleriRepo.findAntrenorByDeneyim_Id(deneyimId);
            Deneyimler deneyim=deneyimlerRepo.findDeneyimlerById(deneyimId);

            for (Danisan danisan : danisanList) {
                Antrenor uygunAntrenor = antrenorList
                        .stream()
                        .filter(antrenor -> antrenor.getKalanKontenjan() > 0)
                        .findFirst()
                        .orElse(null);

                DanisanAntrenorEslesmesi danisanAntrenorHedefKaydi=danisanAntrenorEslesmesiRepo.findByDanisanAndDeneyim(danisan,deneyim);

                if(Objects.nonNull(danisanAntrenorHedefKaydi)){
                    uygunAntrenor=null;
                }

                if (uygunAntrenor != null) {
                    System.out.println("Danışan " + danisan.getId() + " ile Antrenör " + uygunAntrenor.getId() + " eşleştirildi.");
                    uygunAntrenor.setKalanKontenjan(uygunAntrenor.getKalanKontenjan() - 1);
                    antrenorRepo.save(uygunAntrenor);

                    DanisanAntrenorEslesmesi danisanAntrenorEslesmesi = new DanisanAntrenorEslesmesi();
                    danisanAntrenorEslesmesi.setAntrenor(uygunAntrenor);
                    danisanAntrenorEslesmesi.setDanisan(danisan);
                    danisanAntrenorEslesmesi.setDeneyim(deneyim);
                    danisanAntrenorEslesmesiRepo.save(danisanAntrenorEslesmesi);
                } else {
                    System.out.println("Danışan " + danisan.getId() + " için uygun antrenör bulunamadı.");
                }
            }
        }
        return danisanAntrenorEslesmesiRepo.findAll();
    }



}
