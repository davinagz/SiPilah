package com.sipilah.sipilah.service.impl;

import com.sipilah.sipilah.exception.CustomException;
import com.sipilah.sipilah.model.Transaksi;
import com.sipilah.sipilah.repository.RepoPengguna;
import com.sipilah.sipilah.repository.RepoTransaksi;
import com.sipilah.sipilah.service.LayananTransaksi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LayananTransaksiImpl implements LayananTransaksi {

    private final RepoTransaksi repoTransaksi;
    private final RepoPengguna repoPengguna;

    @Override
    public Transaksi inputTransaksi(Transaksi transaksi) {
        // Pastikan warga yang dipilih petugas sudah terdaftar
        Long penggunaId = transaksi.getPengguna().getId();
        if (!repoPengguna.existsById(penggunaId)) {
            throw new CustomException("Warga dengan ID " + penggunaId + " tidak ditemukan");
        }
        return repoTransaksi.save(transaksi);
    }

    @Override
    public Transaksi updateTransaksi(Long id, Transaksi transaksi) {
        Transaksi existing = repoTransaksi.findById(id)
                .orElseThrow(() -> new CustomException("Transaksi dengan ID " + id + " tidak ditemukan"));
        existing.setBerat(transaksi.getBerat());
        existing.setKeterangan(transaksi.getKeterangan());
        existing.setJenisSampah(transaksi.getJenisSampah());
        existing.setKategoriSampah(transaksi.getKategoriSampah());
        return repoTransaksi.save(existing);
    }

    @Override
    public void hapusTransaksi(Long id) {
        if (!repoTransaksi.existsById(id)) {
            throw new CustomException("Transaksi dengan ID " + id + " tidak ditemukan");
        }
        repoTransaksi.deleteById(id);
    }

    @Override
    public List<Transaksi> semuaTransaksi() {
        return repoTransaksi.findAll();
    }

    @Override
    public List<Transaksi> transaksiByPengguna(Long penggunaId) {
        return repoTransaksi.findByPenggunaId(penggunaId);
    }

    @Override
    public Double totalBeratByPengguna(Long penggunaId) {
        return repoTransaksi.findByPenggunaId(penggunaId)
                .stream()
                .mapToDouble(t -> t.getBerat() != null ? t.getBerat() : 0)
                .sum();
    }

    @Override
    public Integer totalTransaksiByPengguna(Long penggunaId) {
        return repoTransaksi.findByPenggunaId(penggunaId).size();
    }
}