package com.example.fitlifeonlinefitnesskocluguplatformu.api.controller;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Admin;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.DanisanAntrenorEslesmesi;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AdminService;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AntrenorService;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.DanisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AntrenorService antrenorService;
    @Autowired
    private DanisanService danisanService;


    @GetMapping("/info")
    public ResponseEntity<String> getUserData(@RequestParam String type, @RequestParam String email) {
        Admin adminInfo = adminService.adminBul(email);
        if (adminInfo != null) {
            return ResponseEntity.ok(adminInfo.getEmail());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping("/antrenorListesi")
    public ResponseEntity<?> getAntrenorListesi() {
        List<Antrenor> antrenorListesi = antrenorService.tumAntrenorleriGetir();
        return ResponseEntity.ok(antrenorListesi);
    }

    @GetMapping("/danisanListesi")
    public ResponseEntity<?> getDanisanListesi() {
        List<Danisan> danisanListesi = danisanService.tumDanisanlariGetir();
        return ResponseEntity.ok(danisanListesi);
    }

    @GetMapping("/antrenorBilgisi")
    public ResponseEntity<?> getAntrenorBilgisi(@RequestParam int index) {
        List<Antrenor> antrenorListesi = antrenorService.tumAntrenorleriGetir();
        return ResponseEntity.ok(antrenorListesi.get(index));
    }

    @GetMapping("/danisanBilgisi")
    public ResponseEntity<?> getDanisanBilgisi(@RequestParam int index) {
        List<Danisan> danisanListesi = danisanService.tumDanisanlariGetir();
        return ResponseEntity.ok(danisanListesi.get(index));
    }

    @PostMapping("/guncelleDanisan")
    public ResponseEntity<?> guncelleDanisan(@RequestBody Danisan danisan) {
        adminService.danisanBilgileriniGuncelle(danisan);
        return ResponseEntity.ok("Danışan bilgileri başarıyla güncellendi.");
    }

    @PostMapping("/guncelleAntrenor")
    public ResponseEntity<?> guncelleAntrenor(@RequestBody Antrenor antrenor) {
        adminService.antrenorBilgileriniGuncelle(antrenor);
        return ResponseEntity.ok("Danışan bilgileri başarıyla güncellendi.");
    }

    @PostMapping("/danisanDevreDisiBirak")
    public ResponseEntity<?> danisanDevreDisiBirak(@RequestBody Danisan danisan) {
        adminService.danisanDevreDisiBirak(danisan);
        return ResponseEntity.ok("Danışan devre dışı bırakıldı.");
    }

    @PostMapping("/antrenorDevreDisiBirak")
    public ResponseEntity<?> antrenorDevreDisiBirak(@RequestBody Antrenor antrenor) {
        adminService.antrenorDevreDisiBirak(antrenor);
        return ResponseEntity.ok("Antrenör devre dışı bırakıldı.");
    }

    @PostMapping("/danisanEtkinlestir")
    public ResponseEntity<?> danisanEtkinlestir(@RequestBody Danisan danisan) {
        adminService.danisanEtkinlestir(danisan);
        return ResponseEntity.ok("Danışan etkinleştirildi.");
    }

    @PostMapping("/antrenorEtkinlestir")
    public ResponseEntity<?> antrenorEtkinlestir(@RequestBody Antrenor antrenor) {
        adminService.antrenorEtkinlestir(antrenor);
        return ResponseEntity.ok("Antrenör etkinleştirildi.");
    }

    @PostMapping("/danisanAntrenorEslesmesiAtamaYap")
    public ResponseEntity<?> danisanAntrenorEslesmesiAtamaYap() {
        List<DanisanAntrenorEslesmesi> eslesmeList=adminService.danisanAntrenorEslesmeAtamasi();
        return ResponseEntity.ok(eslesmeList);
    }


}
