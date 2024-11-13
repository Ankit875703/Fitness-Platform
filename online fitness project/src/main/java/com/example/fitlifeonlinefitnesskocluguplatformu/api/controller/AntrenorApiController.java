package com.example.fitlifeonlinefitnesskocluguplatformu.api.controller;

import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.*;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.*;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AntrenorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/antrenor")
public class AntrenorApiController {

    @Autowired
    private AntrenorService antrenorService;

    private static String imageDirectory = System.getProperty("user.dir") + "/images/";

    @GetMapping("/info")
    public ResponseEntity<?> getAntrenorInfo(@RequestParam String type, @RequestParam String email) {
        Antrenor antrenor=antrenorService.antrenorBul(email);
        return ResponseEntity.ok(antrenor);
    }

    @GetMapping("/getImage/{imageName}")
    public ResponseEntity<?> getImage(@PathVariable String imageName) throws IOException {
        String imageUzanti=imageName+".png";
        Path imagePath = Paths.get(imageDirectory, imageUzanti);
        try {
            Resource resource=new UrlResource(imagePath.toUri());
         return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/antrenorGuncelle")
    public ResponseEntity<?> antrenorGuncelle(@RequestPart("id") Integer id,
                                              @RequestPart("ad") String ad,
                                              @RequestPart("soyad") String soyad,
                                              @RequestPart("telefonNumarasi") String telefonNumarasi,
                                              @RequestPart("email") String email,
                                              @RequestPart("sifre") String sifre) {
        antrenorService.profilimiGuncelle(id, ad, soyad, telefonNumarasi, email, sifre);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/danisanlarim")
    public ResponseEntity<?> getDanisanlarim(@RequestParam Integer  antrenorId) {
        List<Danisan> danisanList=antrenorService.danisanlarimList(antrenorId);
        return ResponseEntity.ok(danisanList);
    }

    @GetMapping("/deneyimler")
    public ResponseEntity<List<Deneyimler>> getDeneyimler() {
        List<Deneyimler> deneyimler =antrenorService.deneyimList();
        return ResponseEntity.ok(deneyimler);
    }

    @GetMapping("/antrenorDeneyimleri")
    public ResponseEntity<List<AntrenorDeneyimleri>> getAntrenorDeneyimleri(@RequestParam Integer  antrenorId) {
        List<AntrenorDeneyimleri> antrenorDeneyimleri = antrenorService.getAntrenorDeneyimler(antrenorId);
        return ResponseEntity.ok(antrenorDeneyimleri);
    }

    @GetMapping("/antrenorunSahipOlmadigiDeneyimler")
    public ResponseEntity<List<Deneyimler>> getAntrenorunSahipOlmadigiDeneyimler(@RequestParam Integer  antrenorId) {
        List<Deneyimler> antrenorunSahipOlmadigiDeneyimler= antrenorService.getAntrenorunSahipOlmadigiDeneyimler(antrenorId);
        return ResponseEntity.ok(antrenorunSahipOlmadigiDeneyimler);
    }


    @PostMapping("/deneyimEkle")
    public ResponseEntity<String> deneyimEkle(@RequestBody DeneyimEkleRequest request) {
        try {
            antrenorService.deneyimEkle(request.getAntrenorId(), request.getDeneyimId(), request.isUzmanlikAlaniMi());
            return ResponseEntity.ok("Deneyim eklendi.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deneyim eklenirken bir hata oluştu.");
        }
    }

    @DeleteMapping("/deneyimSil/{antrenorId}/{deneyimId}")
    public ResponseEntity<String> deneyimSil(@PathVariable int antrenorId, @PathVariable int deneyimId) {
        try {
            antrenorService.deneyimSil(antrenorId,deneyimId);
            return ResponseEntity.ok("Deneyim silindi.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deneyim silinirken bir hata oluştu.");
        }
    }

    @PutMapping("/uzmanlikAlaniOlarakIsaretle/{antrenorId}/{deneyimId}")
    public ResponseEntity<String> uzmanlikAlaniEkle(@PathVariable int antrenorId, @PathVariable int deneyimId) {
        try {
            antrenorService.uzmanlikAlaniEkle(antrenorId,deneyimId);
            return ResponseEntity.ok("Uzmanlık alanı olarak işaretlendi.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Uzmanlık alanı eklenirken bir hata oluştu.");
        }
    }

    @PutMapping("/uzmanlikAlaniniKaldir/{antrenorId}/{deneyimId}")
    public ResponseEntity<String> uzmanlikAlaniniKaldir(@PathVariable int antrenorId, @PathVariable int deneyimId) {
        try {
            antrenorService.uzmanlikAlaniKaldir(antrenorId,deneyimId);
            return ResponseEntity.ok("Uzmanlık alanı kaldırıldı.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Uzmanlık alanı kaldırılırken bir hata oluştu.");
        }
    }


    @PostMapping("/guncelleAntrenor")
    public ResponseEntity<?> guncelleAntrenor(@RequestBody Antrenor antrenor) {
        antrenorService.antrenorBilgileriGuncelle(antrenor);
        return ResponseEntity.ok("Danışan bilgileri başarıyla güncellendi.");
    }

    @PostMapping("/egzersizPlaniOlustur")
    public ResponseEntity<String> egzersizPlaniOlustur(@RequestBody EgzersizPlaniRequest egzersizPlaniRequest) {
        try {
            // request nesnesinden gerekli bilgileri alarak egzersiz planını oluşturun
            antrenorService.egzersizPlaniOlustur(egzersizPlaniRequest);
            return ResponseEntity.ok("Egzersiz planı başarıyla oluşturuldu.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Egzersiz planı oluşturulurken bir hata oluştu.");
        }
    }

    @GetMapping("/egzersizPlanlarim")
    public ResponseEntity<?> egzersizPlanlarim(@RequestParam Integer  antrenorId){
        List<AntrenorEgzersizProgramlari> planlarim=antrenorService.egzersizPlanlarim(antrenorId);
        return ResponseEntity.ok(planlarim);
    }

    @GetMapping("/egzersizPlanDetaylari")
    public ResponseEntity<?> egzersizPlanim(@RequestParam Integer egzersizPlanId){
        AntrenorEgzersizProgramlari egzersizPlanim=antrenorService.egzersizPlanim(egzersizPlanId);
        return ResponseEntity.ok(egzersizPlanim);
    }


    @PostMapping("/guncelleEgzersizPlani")
    public ResponseEntity<?> guncelleEgzersizPlani(@RequestBody AntrenorEgzersizProgramlari antrenorEgzersizProgramlari) {
        antrenorService.egzersizPlaniBilgileriGuncelle(antrenorEgzersizProgramlari);
        return ResponseEntity.ok("Egzersiz plan bilgileri başarıyla güncellendi.");
    }

    @GetMapping("/danisaninEgzersizPlanlari")
    public ResponseEntity<?> getDanisaninEgzersizPlanlari(@RequestParam int danisanId) {
        try {
            // AntrenorService'de danışanın egzersiz planlarını getiren bir metodunuzu çağırın
            List<DanisanEgzersizProgramlari> egzersizPlanlari = antrenorService.getDanisaninEgzersizPlanlari(danisanId);
            return ResponseEntity.ok(egzersizPlanlari);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Danışanın egzersiz planlarını getirirken bir hata oluştu.");
        }
    }

    @GetMapping("/danisaninAlmadigiEgzersizPlanlari")
    public ResponseEntity<?> getDanisaninAlmadigiEgzersizPlanlari(@RequestParam int danisanId,@RequestParam int antrenorId) {
        try {
            List<AntrenorEgzersizProgramlari> egzersizPlanlari = antrenorService.danisaninAlmadigiEgzersizPlanlari(danisanId,antrenorId);
            return ResponseEntity.ok(egzersizPlanlari);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Danışanın egzersiz planlarını getirirken bir hata oluştu.");
        }
    }



    @PostMapping("/danisanaEgzersizPlaniAta")
    public ResponseEntity<String> danisanaEgzersizPlaniAta(@RequestBody DanisanaPlanAtaRequest danisanaPlanAtaRequest) {
        try {
            antrenorService.danisanaEgzersizPlaniAta(danisanaPlanAtaRequest);
            return ResponseEntity.ok("Danışana egzersiz planı atandı.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Danışana egzersiz planı atanırken bir hata oluştu.");
        }
    }

    @PostMapping("/danisanaMesajGonder")
    public ResponseEntity<String> mesajGonder(@RequestBody DanisanaMesajGonderRequest danisanaMesajGonderRequest) {
        try {
            antrenorService.danisanaMesajGonder(danisanaMesajGonderRequest);
            return ResponseEntity.ok("Mesaj başarıyla gönderildi.");
        } catch (Exception e) {
            // Hata durumunda iç hata mesajını döndür
            return ResponseEntity.status(500).body("Mesaj gönderilirken bir hata oluştu.");
        }
    }

    @GetMapping("/gelenMesajlar")
    public ResponseEntity<?> getGelenMesajlar(@RequestParam Integer antrenorId) {
        try {
            List<AntrenorGelenKutusu> gelenMesajlar = antrenorService.getGelenMesajlar(antrenorId);
            return ResponseEntity.ok(gelenMesajlar);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Hata durumunda uygun bir cevap döndürülebilir.
        }
    }

    @PostMapping("/beslenmePlaniOlustur")
    public ResponseEntity<String> beslenmePlaniOlustur(@RequestBody BeslenmePlaniOlusturRequest beslenmePlaniOlusturRequest) {
        try {
            antrenorService.beslenmePlaniOlustur(beslenmePlaniOlusturRequest);
            return ResponseEntity.ok("Beslenme planı başarıyla oluşturuldu.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Beslenme planı oluşturulurken bir hata oluştu.");
        }
    }


    @GetMapping("/beslenmePlanlarim")
    public ResponseEntity<?> getBeslenmePlanlariByAntrenorId(@RequestParam Integer antrenorId) {
        try {
            List<BeslenmePlani> beslenmePlanlari = antrenorService.antrenorBeslenmePlanlariList(antrenorId);
            return ResponseEntity.ok(beslenmePlanlari);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Beslenme planları getirilirken bir hata oluştu. Hata Detayı: " + e.getMessage());
        }
    }

    @GetMapping("/beslenmePlanDetaylari")
    public ResponseEntity<?> beslenmePlanim(@RequestParam Integer beslenmePlanId){
        BeslenmePlani beslenmePlanim=antrenorService.beslenmePlanim(beslenmePlanId);
        return ResponseEntity.ok(beslenmePlanim);
    }

    @PostMapping("/beslenmePlaniGuncelle")
    public ResponseEntity<?> beslenmePlaniGuncelle(@RequestBody BeslenmePlani beslenmePlani) {
        try {
            antrenorService.beslenmePlaniniGuncelle(beslenmePlani);
            return ResponseEntity.ok(beslenmePlani);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Beslenme planları güncellenirken bir hata oluştu. Hata Detayı: " + e.getMessage());
        }
    }

    @GetMapping("/danisaninBeslenmePlanlari")
    public ResponseEntity<?> getDanisaninBeslenmePlanlari(@RequestParam int danisanId) {
        try {
            List<DanisanBeslenmePlani> beslenmePlanlari = antrenorService.getBeslenmePlani(danisanId);
            return ResponseEntity.ok(beslenmePlanlari);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Danışanın beslenme planlarını getirirken bir hata oluştu.");
        }
    }

    @GetMapping("/danisaninAlmadigiBeslenmePlanlari")
    public ResponseEntity<?> getDanisaninAlmadigiBeslenmePlanlari(@RequestParam int danisanId,@RequestParam int antrenorId) {
        try {
            List<BeslenmePlani> antrenorBeslenmePlanlari = antrenorService.danisaninAlmadigiBeslenmePlanlari(danisanId,antrenorId);
            return ResponseEntity.ok(antrenorBeslenmePlanlari);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Beslenme planlarını getirirken bir hata oluştu.");
        }
    }

    @PostMapping("/danisanaBeslenmePlaniAta")
    public ResponseEntity<?> danisanaBeslenmePlaniAta(@RequestBody DanisanaPlanAtaRequest danisanaPlanAtaRequest) {
        try {
            antrenorService.danisanaBeslenmePlaniAta(danisanaPlanAtaRequest);
            return ResponseEntity.ok("Danışana egzersiz planı atandı.");
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Danışana beslenme planı atanırken bir hata oluştu.");
        }
    }



}
