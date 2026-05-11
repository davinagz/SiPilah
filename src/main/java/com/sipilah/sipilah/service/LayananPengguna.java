package com.sipilah.sipilah.service;

import com.sipilah.sipilah.model.Admin;
import com.sipilah.sipilah.model.Pengguna;
import com.sipilah.sipilah.model.PenggunaAktif;
import com.sipilah.sipilah.model.Petugas;

import java.util.List;

public interface LayananPengguna {

    // Umum
    Pengguna cariById(Long id);
    Pengguna cariByEmail(String email);
    void hapusPengguna(Long id);

    // Admin
    Admin tambahAdmin(Admin admin);
    List<Admin> semuaAdmin();

    // Warga
    PenggunaAktif tambahWarga(PenggunaAktif warga);
    List<PenggunaAktif> semuaWarga();
    List<PenggunaAktif> wargaByRtRw(String rtRw);

    // Petugas
    Petugas tambahPetugas(Petugas petugas);
    List<Petugas> semuaPetugas();
    List<Petugas> petugasByWilayah(String wilayah);
}