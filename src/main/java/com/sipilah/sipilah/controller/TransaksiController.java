package com.sipilah.sipilah.controller;

import com.sipilah.sipilah.model.Transaksi;
import com.sipilah.sipilah.model.PenggunaAktif;
import com.sipilah.sipilah.service.LayananTransaksi;
import com.sipilah.sipilah.service.LayananPengguna;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TransaksiController {

    private final LayananTransaksi layananTransaksi;
    private final LayananPengguna layananPengguna;

    // ===================== PETUGAS =====================

    // Petugas lihat daftar warga terdaftar (untuk pilih warga saat input transaksi)
    @GetMapping("/api/petugas/warga")
    public ResponseEntity<List<PenggunaAktif>> daftarWarga() {
        return ResponseEntity.ok(layananPengguna.semuaWarga());
    }

    // Petugas input transaksi — pilih warga yang sudah terdaftar
    @PostMapping("/api/petugas/transaksi")
    public ResponseEntity<Transaksi> inputTransaksi(@RequestBody Transaksi transaksi) {
        return ResponseEntity.ok(layananTransaksi.inputTransaksi(transaksi));
    }

    // Petugas update transaksi
    @PutMapping("/api/petugas/transaksi/{id}")
    public ResponseEntity<Transaksi> updateTransaksi(@PathVariable Long id, @RequestBody Transaksi transaksi) {
        return ResponseEntity.ok(layananTransaksi.updateTransaksi(id, transaksi));
    }

    // Petugas hapus transaksi
    @DeleteMapping("/api/petugas/transaksi/{id}")
    public ResponseEntity<?> hapusTransaksi(@PathVariable Long id) {
        layananTransaksi.hapusTransaksi(id);
        return ResponseEntity.ok("Transaksi berhasil dihapus");
    }

    // ===================== PENGGUNA (WARGA) =====================

    // Warga lihat riwayat transaksi miliknya
    @GetMapping("/api/pengguna/{id}/riwayat")
    public ResponseEntity<List<Transaksi>> riwayatTransaksi(@PathVariable Long id) {
        return ResponseEntity.ok(layananTransaksi.transaksiByPengguna(id));
    }

    // Warga lihat dashboard info sampahnya
    @GetMapping("/api/pengguna/{id}/dashboard")
    public ResponseEntity<Map<String, Object>> dashboard(@PathVariable Long id) {
        Map<String, Object> info = new HashMap<>();
        info.put("totalBerat", layananTransaksi.totalBeratByPengguna(id));
        info.put("totalTransaksi", layananTransaksi.totalTransaksiByPengguna(id));
        return ResponseEntity.ok(info);
    }

    // ===================== ADMIN =====================

    // Admin lihat semua transaksi
    @GetMapping("/api/admin/transaksi")
    public ResponseEntity<List<Transaksi>> semuaTransaksi() {
        return ResponseEntity.ok(layananTransaksi.semuaTransaksi());
    }

    // Admin hapus transaksi
    @DeleteMapping("/api/admin/transaksi/{id}")
    public ResponseEntity<?> hapusTransaksiAdmin(@PathVariable Long id) {
        layananTransaksi.hapusTransaksi(id);
        return ResponseEntity.ok("Transaksi berhasil dihapus oleh admin");
    }
}