package com.sipilah.sipilah.service;

import com.sipilah.sipilah.model.Admin;
import com.sipilah.sipilah.model.Pengguna;
import com.sipilah.sipilah.model.PenggunaAktif;
import com.sipilah.sipilah.model.Petugas;

import java.util.List;

public interface LayananPengguna {

    // =====================================================
    // UMUM
    // =====================================================

    Pengguna cariById(Long id);

    Pengguna cariByEmail(String email);

    Pengguna updatePengguna(Pengguna pengguna);

    void hapusPengguna(Long id);

    // =====================================================
    // ADMIN
    // =====================================================

    Admin tambahAdmin(Admin admin);

    List<Admin> semuaAdmin();

    // =====================================================
    // WARGA
    // =====================================================

    PenggunaAktif tambahWarga(
            PenggunaAktif warga);

    List<PenggunaAktif> semuaWarga();

    List<PenggunaAktif> wargaByRtRw(
            String rtRw);

    // =====================================================
    // PETUGAS
    // =====================================================

    Petugas tambahPetugas(
            Petugas petugas);

    List<Petugas> semuaPetugas();

    List<Petugas> petugasByWilayah(
            String wilayah);
}