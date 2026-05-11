package com.sipilah.sipilah.controller;

import com.sipilah.sipilah.model.Admin;
import com.sipilah.sipilah.model.PenggunaAktif;
import com.sipilah.sipilah.model.Petugas;
import com.sipilah.sipilah.service.LayananPengguna;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class PenggunaController {

    private final LayananPengguna layananPengguna;

    // ===================== KELOLA WARGA =====================

    @GetMapping("/warga")
    public ResponseEntity<List<PenggunaAktif>> semuaWarga() {
        return ResponseEntity.ok(layananPengguna.semuaWarga());
    }

    @GetMapping("/warga/{id}")
    public ResponseEntity<?> wargaById(@PathVariable Long id) {
        return ResponseEntity.ok(layananPengguna.cariById(id));
    }

    @GetMapping("/warga/rtrw/{rtRw}")
    public ResponseEntity<List<PenggunaAktif>> wargaByRtRw(@PathVariable String rtRw) {
        return ResponseEntity.ok(layananPengguna.wargaByRtRw(rtRw));
    }

    @PostMapping("/warga")
    public ResponseEntity<PenggunaAktif> tambahWarga(@RequestBody PenggunaAktif warga) {
        return ResponseEntity.ok(layananPengguna.tambahWarga(warga));
    }

    @DeleteMapping("/warga/{id}")
    public ResponseEntity<?> hapusWarga(@PathVariable Long id) {
        layananPengguna.hapusPengguna(id);
        return ResponseEntity.ok("Warga berhasil dihapus");
    }

    // ===================== KELOLA PETUGAS =====================

    @GetMapping("/petugas")
    public ResponseEntity<List<Petugas>> semuaPetugas() {
        return ResponseEntity.ok(layananPengguna.semuaPetugas());
    }

    @PostMapping("/petugas")
    public ResponseEntity<Petugas> tambahPetugas(@RequestBody Petugas petugas) {
        return ResponseEntity.ok(layananPengguna.tambahPetugas(petugas));
    }

    @GetMapping("/petugas/wilayah/{wilayah}")
    public ResponseEntity<List<Petugas>> petugasByWilayah(@PathVariable String wilayah) {
        return ResponseEntity.ok(layananPengguna.petugasByWilayah(wilayah));
    }

    @DeleteMapping("/petugas/{id}")
    public ResponseEntity<?> hapusPetugas(@PathVariable Long id) {
        layananPengguna.hapusPengguna(id);
        return ResponseEntity.ok("Petugas berhasil dihapus");
    }

    // ===================== KELOLA ADMIN =====================

    @GetMapping("/list")
    public ResponseEntity<List<Admin>> semuaAdmin() {
        return ResponseEntity.ok(layananPengguna.semuaAdmin());
    }

    @PostMapping("/tambah")
    public ResponseEntity<Admin> tambahAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(layananPengguna.tambahAdmin(admin));
    }
}