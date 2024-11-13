package com.example.fitlifeonlinefitnesskocluguplatformu.api.controller;

import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.*;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.*;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.DanisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/danisan")
public class DanisanApiController {

    @Autowired
    DanisanService danisanService;

    private static String imageDirectory = System.getProperty("user.dir") + "/images/";

    @GetMapping("/info")
    public ResponseEntity<?> getDanisanInfo(@RequestParam String type, @RequestParam String email) {
        Danisan danisan=danisanService.danisanBul(email);
        return ResponseEntity.ok(danisan);
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

    @PostMapping("/guncelleDanisan")
    public ResponseEntity<?> danisanGuncelle(@RequestBody DanisanGuncellemeRequest request) {
        danisanService.danisanGuncelle(request);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/egzersizPlanlarimiGoster")
    public ResponseEntity<?> egzersizPlanlarimiGoster(@RequestParam Integer danisanId) {
        try {
            List<DanisanEgzersizProgramlari> egzersizList=danisanService.danisanProgramlist(danisanId);
            return ResponseEntity.ok(egzersizList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Danışanın egzersiz planlarını getirirken bir hata oluştu. Hata Detayı: " + e.getMessage());
        }
    }

    @GetMapping("/egzersizPlanDetaylari")
    public ResponseEntity<?> getEgzersizPlanDetaylari(@RequestParam Integer egzersizPlanId) {
        try {
            AntrenorEgzersizProgramlari egzersizPlanDetaylari = danisanService.getEgzersizPlanDetaylari(egzersizPlanId);
                return ResponseEntity.ok(egzersizPlanDetaylari);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Egzersiz plan detayları getirirken bir hata oluştu. Hata Detayı: " + e.getMessage());
        }
    }


    @PostMapping("/egzersizPlanTamamla")
    public ResponseEntity<String> egzersizPlanTamamla(@RequestParam Integer egzersizPlanId) {
        try {
            danisanService.egzersizPlaniniTamamla(egzersizPlanId);
            return ResponseEntity.ok("Egzersiz planı başarıyla tamamlandı.");
        } catch (Exception e) {
            // Hata durumunda ise uygun bir hata mesajı ile 500 Internal Server Error dönebilirsiniz
            return ResponseEntity.status(500).body("Egzersiz planını tamamlama sırasında bir hata oluştu. Hata Detayı: " + e.getMessage());
        }
    }


    @PostMapping("/antrenoreMesajGonder")
    public ResponseEntity<String> mesajGonder(@RequestBody AntrenoreMesajGonderRequest antrenoreMesajGonderRequest) {
        try {
            danisanService.antrenoreMesajGonder(antrenoreMesajGonderRequest);
            return ResponseEntity.ok("Mesaj başarıyla gönderildi.");
        } catch (Exception e) {
            // Hata durumunda iç hata mesajını döndür
            return ResponseEntity.status(500).body("Mesaj gönderilirken bir hata oluştu.");
        }
    }

    @GetMapping("/gelenMesajlar")
    public ResponseEntity<?> getGelenMesajlar(@RequestParam Integer danisanId) {
        try {
            List<DanisanGelenKutusu> gelenKutusu = danisanService.danisanGelenKutusu(danisanId);
            return ResponseEntity.ok(gelenKutusu);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Mesajları getirirken bir hata oluştu.");
        }
    }

    @GetMapping("/beslenmePlanlarimiGoster")
    public ResponseEntity<?> getBeslenmePlanlarim(@RequestParam Integer danisanId) {
        try {
            List<DanisanBeslenmePlani> planlar = danisanService.beslenmePlanlarimiBul(danisanId);
            return ResponseEntity.ok(planlar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Beslenme planlarını getirirken bir hata oluştu.");
        }
    }

    @GetMapping("/beslenmePlanDetaylari")
    public ResponseEntity<?> getBeslenmePlanDetaylari(@RequestParam Integer beslenmePlanId) {
        try {
            BeslenmePlani beslenmeDetaylari = danisanService.getBeslenmePlaniminDetayi(beslenmePlanId);
            return ResponseEntity.ok(beslenmeDetaylari);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Beslenme plan detayları getirirken bir hata oluştu. Hata Detayı: " + e.getMessage());
        }
    }

    @PostMapping("/ilerlemeKaydiEkle")
    public ResponseEntity<String> ilerlemeKaydiEkle(@RequestBody IlerlemeKaydiRequest ilerlemeKaydiRequest) {
        try {
            danisanService.danisanIlerlemeKaydiEkleme(ilerlemeKaydiRequest);
            return ResponseEntity.ok("İlerleme kaydı eklendi.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("İlerleme kaydı eklenirken bir hata oluştu.");
        }
    }


    @GetMapping("/ilerlemeKayitlarim")
    public ResponseEntity<?> getilerlemeKayitlarim(@RequestParam Integer danisanId) {
        try {
            List<IlerlemeKaydi> ilerlemeKaydiLists= danisanService.getIlerlemeKayitlarim(danisanId);
            return ResponseEntity.ok(ilerlemeKaydiLists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("İlerleme kayıtlarını  getirirken bir hata oluştu.");
        }
    }

    @GetMapping("/ilerlemeKaydiDetaylari")
    public ResponseEntity<?> getilerlemeKaydiDetay(@RequestParam Integer kayitId) {
        try {
            IlerlemeKaydi ilerlemeKaydi= danisanService.getIlerlemeKaydiDetay(kayitId);
            return ResponseEntity.ok(ilerlemeKaydi);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("İlerleme kaydını  getirirken bir hata oluştu.");
        }
    }


    @PostMapping("/ilerlemeKaydiGuncelle")
    public ResponseEntity<String> ilerlemeKaydiGuncelle(@RequestBody IlerlemeKaydiGuncelleRequest ilerlemeKaydiGuncelleRequest) {
        try {
            danisanService.ilerlemeKaydiGuncelle(ilerlemeKaydiGuncelleRequest);
            return ResponseEntity.ok("İlerleme kaydı güncellendi.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("İlerleme kaydı güncellenirken bir hata oluştu.");
        }
    }

    @GetMapping("/gunlukIlerlemeKaydiRaporlama")
    public ResponseEntity<List<IlerlemeKaydi>> gunlukIlerlemeKaydiRaporlama(@RequestParam int danisanId, @RequestParam LocalDate gunTarih) {
        List<IlerlemeKaydi> danisaninIlerlemeKayitlari = danisanService.danisanGunlukIlerlemeKaydiRaporu(danisanId,gunTarih);
        return ResponseEntity.ok(danisaninIlerlemeKayitlari);
    }


    @GetMapping("/haftalikIlerlemeKaydiRaporlama")
    public ResponseEntity<List<IlerlemeKaydi>> haftalikIlerlemeKaydiRaporlama(@RequestParam int danisanId, @RequestParam LocalDate gunTarih) {
        List<IlerlemeKaydi> danisaninIlerlemeKayitlari = danisanService.danisanHaftalikIlerlemeKaydiRaporu(danisanId,gunTarih);
        return ResponseEntity.ok(danisaninIlerlemeKayitlari);
    }


    @GetMapping("/danisanHedefleri")
    public ResponseEntity<List<DanisanHedefleri>> getDanisanHedefleri(@RequestParam Integer  danisanId) {
        List<DanisanHedefleri> danisanHedefleri = danisanService.getDanisanHedefleri(danisanId);
        return ResponseEntity.ok(danisanHedefleri);
    }

    @GetMapping("/danisaninSahipOlmadigiHedefler")
    public ResponseEntity<List<Deneyimler>> getDanisaninSahipOlmadigiHedefler(@RequestParam Integer  danisanId) {
        List<Deneyimler> danisaninSahipOlmadigiHedefler= danisanService.getDanisaninSahipOlmadigiHedefler(danisanId);
        return ResponseEntity.ok(danisaninSahipOlmadigiHedefler);
    }


    @PostMapping("/hedefEkle/{danisanId}/{hedefId}")
    public ResponseEntity<String> hedefEkle(@PathVariable int danisanId, @PathVariable int hedefId) {
        try {
            danisanService.hedefEkle(danisanId, hedefId);
            return ResponseEntity.ok("Hedef eklendi.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hedef eklenirken bir hata oluştu.");
        }
    }

    @DeleteMapping("/hedefSil/{danisanId}/{hedefId}")
    public ResponseEntity<String> hedefSil(@PathVariable int danisanId, @PathVariable int hedefId) {
        try {
            danisanService.hedefSil(danisanId,hedefId);
            return ResponseEntity.ok("Hedef silindi.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hedef silinirken bir hata oluştu.");
        }
    }


}
