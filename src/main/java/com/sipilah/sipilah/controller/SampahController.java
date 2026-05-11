package com.sipilah.sipilah.controller;

import com.sipilah.sipilah.model.JenisSampah;
import com.sipilah.sipilah.model.KategoriSampah;
import com.sipilah.sipilah.service.LayananSampah;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sampah")
@RequiredArgsConstructor
public class SampahController {

    private final LayananSampah layananSampah;

    // ===================== KATEGORI =====================

    @GetMapping("/kategori")
    public ResponseEntity<List<KategoriSampah>> semuaKategori() {
        return ResponseEntity.ok(layananSampah.semuaKategori());
    }

    @GetMapping("/kategori/{id}")
    public ResponseEntity<KategoriSampah> kategoriById(@PathVariable Long id) {
        return ResponseEntity.ok(layananSampah.cariKategoriById(id));
    }

    @PostMapping("/kategori")
    public ResponseEntity<KategoriSampah> tambahKategori(@RequestBody KategoriSampah kategori) {
        return ResponseEntity.ok(layananSampah.tambahKategori(kategori));
    }

    @DeleteMapping("/kategori/{id}")
    public ResponseEntity<?> hapusKategori(@PathVariable Long id) {
        layananSampah.hapusKategori(id);
        return ResponseEntity.ok("Kategori berhasil dihapus");
    }

    // ===================== JENIS SAMPAH =====================

    @GetMapping("/jenis")
    public ResponseEntity<List<JenisSampah>> semuaJenis() {
        return ResponseEntity.ok(layananSampah.semuaJenis());
    }

    @GetMapping("/jenis/{id}")
    public ResponseEntity<JenisSampah> jenisById(@PathVariable Long id) {
        return ResponseEntity.ok(layananSampah.cariJenisById(id));
    }

    @GetMapping("/jenis/kategori/{kategoriId}")
    public ResponseEntity<List<JenisSampah>> jenisByKategori(@PathVariable Long kategoriId) {
        return ResponseEntity.ok(layananSampah.jenisByKategori(kategoriId));
    }

    @PostMapping("/jenis")
    public ResponseEntity<JenisSampah> tambahJenis(@RequestBody JenisSampah jenis) {
        return ResponseEntity.ok(layananSampah.tambahJenis(jenis));
    }

    @DeleteMapping("/jenis/{id}")
    public ResponseEntity<?> hapusJenis(@PathVariable Long id) {
        layananSampah.hapusJenis(id);
        return ResponseEntity.ok("Jenis sampah berhasil dihapus");
    }
}