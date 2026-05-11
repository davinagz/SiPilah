package com.sipilah.sipilah.service.impl;

import com.sipilah.sipilah.exception.CustomException;
import com.sipilah.sipilah.model.Admin;
import com.sipilah.sipilah.model.Pengguna;
import com.sipilah.sipilah.model.PenggunaAktif;
import com.sipilah.sipilah.model.Petugas;
import com.sipilah.sipilah.repository.RepoAdmin;
import com.sipilah.sipilah.repository.RepoPengguna;
import com.sipilah.sipilah.repository.RepoPenggunaAktif;
import com.sipilah.sipilah.repository.RepoPetugas;
import com.sipilah.sipilah.service.LayananPengguna;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayananPenggunaImpl implements LayananPengguna {

    private final RepoPengguna repoPengguna;
    private final RepoAdmin repoAdmin;
    private final RepoPenggunaAktif repoPenggunaAktif;
    private final RepoPetugas repoPetugas;

    public LayananPenggunaImpl(RepoPengguna repoPengguna,
                                RepoAdmin repoAdmin,
                                RepoPenggunaAktif repoPenggunaAktif,
                                RepoPetugas repoPetugas) {
        this.repoPengguna = repoPengguna;
        this.repoAdmin = repoAdmin;
        this.repoPenggunaAktif = repoPenggunaAktif;
        this.repoPetugas = repoPetugas;
    }

    // ===================== UMUM =====================

    @Override
    public Pengguna cariById(Long id) {
        return repoPengguna.findById(id)
                .orElseThrow(() -> new CustomException("Pengguna dengan ID " + id + " tidak ditemukan"));
    }

    @Override
    public Pengguna cariByEmail(String email) {
        return repoPengguna.findByEmail(email)
                .orElseThrow(() -> new CustomException("Pengguna dengan email " + email + " tidak ditemukan"));
    }

    @Override
    public void hapusPengguna(Long id) {
        if (!repoPengguna.existsById(id)) {
            throw new CustomException("Pengguna dengan ID " + id + " tidak ditemukan");
        }
        repoPengguna.deleteById(id);
    }

    // ===================== ADMIN =====================

    @Override
    public Admin tambahAdmin(Admin admin) {
        if (repoPengguna.existsByEmail(admin.getEmail())) {
            throw new CustomException("Email " + admin.getEmail() + " sudah terdaftar");
        }
        return repoAdmin.save(admin);
    }

    @Override
    public List<Admin> semuaAdmin() {
        return repoAdmin.findAll();
    }

    // ===================== WARGA =====================

    @Override
    public PenggunaAktif tambahWarga(PenggunaAktif warga) {
        if (repoPengguna.existsByEmail(warga.getEmail())) {
            throw new CustomException("Email " + warga.getEmail() + " sudah terdaftar");
        }
        return repoPenggunaAktif.save(warga);
    }

    @Override
    public List<PenggunaAktif> semuaWarga() {
        return repoPenggunaAktif.findAll();
    }

    @Override
    public List<PenggunaAktif> wargaByRtRw(String rtRw) {
        return repoPenggunaAktif.findByRtRw(rtRw);
    }

    // ===================== PETUGAS =====================

    @Override
    public Petugas tambahPetugas(Petugas petugas) {
        if (repoPengguna.existsByEmail(petugas.getEmail())) {
            throw new CustomException("Email " + petugas.getEmail() + " sudah terdaftar");
        }
        return repoPetugas.save(petugas);
    }

    @Override
    public List<Petugas> semuaPetugas() {
        return repoPetugas.findAll();
    }

    @Override
    public List<Petugas> petugasByWilayah(String wilayah) {
        return repoPetugas.findByWilayahTugas(wilayah);
    }
}