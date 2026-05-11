package com.sipilah.sipilah.service.impl;

import com.sipilah.sipilah.exception.CustomException;
import com.sipilah.sipilah.model.JenisSampah;
import com.sipilah.sipilah.model.KategoriSampah;
import com.sipilah.sipilah.model.Pengguna;
import com.sipilah.sipilah.model.PenggunaAktif;
import com.sipilah.sipilah.model.Transaksi;
import com.sipilah.sipilah.repository.RepoJenisSampah;
import com.sipilah.sipilah.repository.RepoKategori;
import com.sipilah.sipilah.repository.RepoPengguna;
import com.sipilah.sipilah.repository.RepoTransaksi;
import com.sipilah.sipilah.service.LayananSampah;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LayananSampahImpl implements LayananSampah {

    private final RepoKategori repoKategori;
    private final RepoJenisSampah repoJenisSampah;
    private final RepoPengguna repoPengguna;
    private final RepoTransaksi repoTransaksi;

    // ===================== KATEGORI =====================

    @Override
    public KategoriSampah tambahKategori(KategoriSampah kategori) {
        return repoKategori.save(kategori);
    }

    @Override
    public List<KategoriSampah> semuaKategori() {
        return repoKategori.findAll();
    }

    @Override
    public KategoriSampah cariKategoriById(Long id) {
        return repoKategori.findById(id)
                .orElseThrow(() -> new CustomException("Kategori dengan ID " + id + " tidak ditemukan"));
    }

    @Override
    public void hapusKategori(Long id) {
        if (!repoKategori.existsById(id)) {
            throw new CustomException("Kategori dengan ID " + id + " tidak ditemukan");
        }
        repoKategori.deleteById(id);
    }

    // ===================== JENIS SAMPAH =====================

    @Override
    public JenisSampah tambahJenis(JenisSampah jenis) {
        return repoJenisSampah.save(jenis);
    }

    @Override
    public List<JenisSampah> semuaJenis() {
        return repoJenisSampah.findAll();
    }

    @Override
    public JenisSampah cariJenisById(Long id) {
        return repoJenisSampah.findById(id)
                .orElseThrow(() -> new CustomException("Jenis sampah dengan ID " + id + " tidak ditemukan"));
    }

    @Override
    public List<JenisSampah> jenisByKategori(Long kategoriId) {
        return repoJenisSampah.findByKategoriId(kategoriId);
    }

    @Override
    public void hapusJenis(Long id) {
        if (!repoJenisSampah.existsById(id)) {
            throw new CustomException("Jenis sampah dengan ID " + id + " tidak ditemukan");
        }
        repoJenisSampah.deleteById(id);
    }

    @Override
    public PenggunaAktif simpanPengguna(PenggunaAktif pengguna) {
        if (repoPengguna.existsByEmail(pengguna.getEmail())) {
            throw new CustomException("Email " + pengguna.getEmail() + " sudah terdaftar");
        }
        return (PenggunaAktif) repoPengguna.save(pengguna);
    }

    @Override
    public List<Pengguna> getPengguna() {
        return repoPengguna.findAll();
    }

    @Override
    public void hapusPengguna(Long id) {
        if (!repoPengguna.existsById(id)) {
            throw new CustomException("Pengguna dengan ID " + id + " tidak ditemukan");
        }
        repoPengguna.deleteById(id);
    }

    @Override
    public Transaksi simpanTransaksi(Transaksi transaksi) {
        return repoTransaksi.save(transaksi);
    }

    @Override
    public List<Transaksi> getTransaksi() {
        return repoTransaksi.findAll();
    }

    @Override
    public void hapusTransaksi(Long id) {
        if (!repoTransaksi.existsById(id)) {
            throw new CustomException("Transaksi dengan ID " + id + " tidak ditemukan");
        }
        repoTransaksi.deleteById(id);
    }

    @Override
    public List<JenisSampah> getJenisSampah() {
        return semuaJenis();
    }

    @Override
    public List<KategoriSampah> getKategoriSampah() {
        return semuaKategori();
    }

    @Override
    public double totalSampah() {
        return getTransaksi().stream()
                .mapToDouble(t -> t.getBerat() != null ? t.getBerat() : 0.0)
                .sum();
    }
}
