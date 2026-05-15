package com.sipilah.sipilah.controller;

import com.sipilah.sipilah.model.Admin;
import com.sipilah.sipilah.model.Pengguna;
import com.sipilah.sipilah.model.PenggunaAktif;
import com.sipilah.sipilah.model.Petugas;

import com.sipilah.sipilah.service.LayananPengguna;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class PenggunaController {

    private final LayananPengguna layananPengguna;

    // =====================================================
    // REGISTER WARGA
    // =====================================================

    @PostMapping("/register")
    public ResponseEntity<?> registerWarga(
            @RequestBody PenggunaAktif warga) {

        try {

            // =====================
            // VALIDASI EMAIL
            // =====================

            Pengguna existing =
                    layananPengguna
                            .cariByEmail(
                                    warga.getEmail()
                            );

            if (existing != null) {

                Map<String, String> error =
                        new HashMap<>();

                error.put(
                        "pesan",
                        "Email sudah digunakan"
                );

                return ResponseEntity
                        .badRequest()
                        .body(error);
            }

        } catch (Exception e) {

            // EMAIL BELUM ADA
        }

        // =====================
        // STATUS DEFAULT
        // =====================

        warga.setStatus("PENDING");

        // =====================
        // SIMPAN WARGA
        // =====================

        layananPengguna
                .tambahWarga(warga);

        // =====================
        // RESPONSE
        // =====================

        Map<String, String> response =
                new HashMap<>();

        response.put(
                "pesan",
                "Pendaftaran berhasil. Tunggu konfirmasi admin."
        );

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // LIST WARGA PENDING
    // =====================================================

    @GetMapping("/warga/pending")
    public ResponseEntity<List<PenggunaAktif>>
    wargaPending() {

        List<PenggunaAktif> pending =
                layananPengguna
                        .semuaWarga()
                        .stream()
                        .filter(w ->
                                w.getStatus()
                                        .equalsIgnoreCase(
                                                "PENDING"
                                        )
                        )
                        .collect(Collectors.toList());

        return ResponseEntity.ok(pending);
    }

    // =====================================================
    // APPROVE WARGA
    // =====================================================

    @PutMapping("/warga/approve/{id}")
    public ResponseEntity<?> approveWarga(
            @PathVariable Long id) {

        try {

            Pengguna pengguna =
                    layananPengguna
                            .cariById(id);

            pengguna.setStatus(
                    "APPROVED"
            );

            layananPengguna
                    .updatePengguna(
                            pengguna
                    );

            return ResponseEntity.ok(
                    "Warga berhasil dikonfirmasi"
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Warga tidak ditemukan"
                    );
        }
    }

    // =====================================================
    // REJECT WARGA
    // =====================================================

    @DeleteMapping("/warga/reject/{id}")
    public ResponseEntity<?> rejectWarga(
            @PathVariable Long id) {

        try {

            layananPengguna
                    .hapusPengguna(id);

            return ResponseEntity.ok(
                    "Pendaftaran warga ditolak"
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Gagal menolak warga"
                    );
        }
    }

    // =====================================================
    // SEMUA WARGA
    // =====================================================

    @GetMapping("/warga")
    public ResponseEntity<List<PenggunaAktif>>
    semuaWarga() {

        return ResponseEntity.ok(
                layananPengguna
                        .semuaWarga()
        );
    }

    // =====================================================
    // WARGA BY ID
    // =====================================================

    @GetMapping("/warga/{id}")
    public ResponseEntity<?> wargaById(
            @PathVariable Long id) {

        try {

            Pengguna pengguna =
                    layananPengguna
                            .cariById(id);

            return ResponseEntity.ok(
                    pengguna
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Warga tidak ditemukan"
                    );
        }
    }

    // =====================================================
    // WARGA BY RTRW
    // =====================================================

    @GetMapping("/warga/rtrw/{rtRw}")
    public ResponseEntity<List<PenggunaAktif>>
    wargaByRtRw(
            @PathVariable String rtRw) {

        return ResponseEntity.ok(
                layananPengguna
                        .wargaByRtRw(rtRw)
        );
    }

    // =====================================================
    // TAMBAH WARGA ADMIN
    // =====================================================

    @PostMapping("/warga")
    public ResponseEntity<?> tambahWarga(
            @RequestBody PenggunaAktif warga) {

        try {

            warga.setStatus(
                    "APPROVED"
            );

            PenggunaAktif saved =
                    layananPengguna
                            .tambahWarga(
                                    warga
                            );

            return ResponseEntity.ok(
                    saved
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            e.getMessage()
                    );
        }
    }

    // =====================================================
    // HAPUS WARGA
    // =====================================================

    @DeleteMapping("/warga/{id}")
    public ResponseEntity<?> hapusWarga(
            @PathVariable Long id) {

        try {

            layananPengguna
                    .hapusPengguna(id);

            return ResponseEntity.ok(
                    "Warga berhasil dihapus"
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Gagal menghapus warga"
                    );
        }
    }

    // =====================================================
    // SEMUA PETUGAS
    // =====================================================

    @GetMapping("/petugas")
    public ResponseEntity<List<Petugas>>
    semuaPetugas() {

        return ResponseEntity.ok(
                layananPengguna
                        .semuaPetugas()
        );
    }

    // =====================================================
    // TAMBAH PETUGAS
    // =====================================================

    @PostMapping("/petugas")
    public ResponseEntity<?> tambahPetugas(
            @RequestBody Petugas petugas) {

        try {

            petugas.setStatus(
                    "APPROVED"
            );

            Petugas saved =
                    layananPengguna
                            .tambahPetugas(
                                    petugas
                            );

            return ResponseEntity.ok(
                    saved
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            e.getMessage()
                    );
        }
    }

    // =====================================================
    // PETUGAS BY WILAYAH
    // =====================================================

    @GetMapping("/petugas/wilayah/{wilayah}")
    public ResponseEntity<List<Petugas>>
    petugasByWilayah(
            @PathVariable String wilayah) {

        return ResponseEntity.ok(
                layananPengguna
                        .petugasByWilayah(
                                wilayah
                        )
        );
    }

    // =====================================================
    // HAPUS PETUGAS
    // =====================================================

    @DeleteMapping("/petugas/{id}")
    public ResponseEntity<?> hapusPetugas(
            @PathVariable Long id) {

        try {

            layananPengguna
                    .hapusPengguna(id);

            return ResponseEntity.ok(
                    "Petugas berhasil dihapus"
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Gagal menghapus petugas"
                    );
        }
    }

    // =====================================================
    // LIST ADMIN
    // =====================================================

    @GetMapping("/list")
    public ResponseEntity<List<Admin>>
    semuaAdmin() {

        return ResponseEntity.ok(
                layananPengguna
                        .semuaAdmin()
        );
    }

    // =====================================================
    // TAMBAH ADMIN
    // =====================================================

    @PostMapping("/tambah")
    public ResponseEntity<?> tambahAdmin(
            @RequestBody Admin admin) {

        try {

            admin.setStatus(
                    "APPROVED"
            );

            Admin saved =
                    layananPengguna
                            .tambahAdmin(admin);

            return ResponseEntity.ok(
                    saved
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            e.getMessage()
                    );
        }
    }
}