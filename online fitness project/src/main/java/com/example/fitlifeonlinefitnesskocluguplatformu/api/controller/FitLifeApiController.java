package com.example.fitlifeonlinefitnesskocluguplatformu.api.controller;

import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.GirisRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AdminService;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AntrenorService;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.DanisanService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class FitLifeApiController {
    private final DanisanService danisanService;
    private final AntrenorService antrenorService;
    private final AdminService adminService;

    public FitLifeApiController(DanisanService danisanService, AntrenorService antrenorService, AdminService adminService) {
        this.danisanService = danisanService;
        this.antrenorService = antrenorService;
        this.adminService= adminService;
    }


    @PostMapping("/kayitOl")
    public ResponseEntity<?> kayitOl(@RequestPart("kullaniciTuru") String kullaniciTuru, @RequestPart("ad") String ad, @RequestPart("soyad") String soyad,
            @RequestPart("cinsiyet") String cinsiyet, @RequestPart("dogumTarihi") String dogumTarihi, @RequestPart("telefonNumarasi") String telefonNumarasi,
            @RequestPart("email") String email, @RequestPart("sifre") String sifre, @RequestPart("profilFotografi") MultipartFile profilFotografi) {
        boolean kayitOlusturuldu=false;
        boolean mailKullaniliyor=adminService.mailKullaniliyorMu(email);
        String dosyaAdi=null;

        if(!mailKullaniliyor){
            ResponseEntity<?> imageResponse =uploadImage(profilFotografi, email);
            if (imageResponse.getStatusCode() != HttpStatus.CREATED) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Profil fotoğrafı yüklenemedi!");
            }
             dosyaAdi = (String) imageResponse.getBody();
        }

        if (kullaniciTuru.equals("antrenor") && (!mailKullaniliyor)) {
            kayitOlusturuldu=antrenorService.antrenorKaydiOlustur(ad,soyad,cinsiyet, LocalDate.parse(dogumTarihi),telefonNumarasi,email,sifre,dosyaAdi);
        } else if (kullaniciTuru.equals("danisan") && (!mailKullaniliyor)) {
            kayitOlusturuldu=danisanService.danisanKaydiOlustur(ad,soyad,cinsiyet, LocalDate.parse(dogumTarihi),telefonNumarasi,email,sifre,dosyaAdi);
        }

        String sonuc = "Kullanıcı Türü: " + kullaniciTuru + ", Ad: " + ad + ", Soyad: " + soyad;
        if (kayitOlusturuldu) {
            return ResponseEntity.ok("Başarılı! " + sonuc);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hata: Kayıt başarısız!");
        }
    }


    @PostMapping("/girisYap")
    public ResponseEntity<String> girisYap(@RequestBody GirisRequest kullaniciVerileri) {
        if (Objects.nonNull(adminService.adminGirisi(kullaniciVerileri.getEmail(),kullaniciVerileri.getSifre()))) {
            return ResponseEntity.ok("Admin girişi başarılı");
        }else if(Objects.nonNull(antrenorService.antrenorGirisi(kullaniciVerileri.getEmail(),kullaniciVerileri.getSifre()))){
            if((antrenorService.antrenorGirisi(kullaniciVerileri.getEmail(),kullaniciVerileri.getSifre()).isAktifMi())){
                return ResponseEntity.ok("Antrenör girişi başarılı");
            }else {
                return ResponseEntity.status(403).body("Hesap devre dışı bırakılmış!");
            }
        }else if(Objects.nonNull(danisanService.danisanGirisi(kullaniciVerileri.getEmail(),kullaniciVerileri.getSifre()))){
            if((danisanService.danisanGirisi(kullaniciVerileri.getEmail(),kullaniciVerileri.getSifre()).isAktifMi())){
                return ResponseEntity.ok("Danışan girişi başarılı");
            }else {
                return ResponseEntity.status(403).body("Hesap devre dışı bırakılmış!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Giriş başarısız");
        }
    }

    @PostMapping("/sifreSifirla")
    public ResponseEntity<?> sifreSifirla(@RequestBody GirisRequest kullaniciVerileri) {
        if(adminService.adminSifreDegistir(kullaniciVerileri.getEmail())){
            return ResponseEntity.ok("Admin şifre sıfırlama başarılı");
        }else if(antrenorService.antrenorSifreDegistir(kullaniciVerileri.getEmail())){
            return ResponseEntity.ok("Antrenör şifre sıfırlama başarılı");
        }else if(danisanService.danisanSifreDegistir(kullaniciVerileri.getEmail())){
            return ResponseEntity.ok("Danışan şifre sıfırlama başarılı");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Şifre sıfırlama başarısız");
        }
    }


        private static String imageDirectory = System.getProperty("user.dir") + "/images/";

        public static ResponseEntity<?> uploadImage(@RequestParam("imageFile") MultipartFile file,
                                                    @RequestParam("imageName") String name) {
            makeDirectoryIfNotExist(imageDirectory);
            Path fileNamePath = Paths.get(imageDirectory,
                    name.concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename())));
            try {
                Files.write(fileNamePath, file.getBytes());
                return new ResponseEntity<>(name, HttpStatus.CREATED);
            } catch (IOException ex) {
                return new ResponseEntity<>("Image is not uploaded", HttpStatus.BAD_REQUEST);
            }
        }

        private static void makeDirectoryIfNotExist(String imageDirectory) {
            File directory = new File(imageDirectory);
            if (!directory.exists()) {
                directory.mkdir();
            }
        }


}
