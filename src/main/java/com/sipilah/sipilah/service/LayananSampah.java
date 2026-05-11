package com.sipilah.sipilah.service;

import com.sipilah.sipilah.model.JenisSampah;
import com.sipilah.sipilah.model.KategoriSampah;
import com.sipilah.sipilah.model.Pengguna;
import com.sipilah.sipilah.model.PenggunaAktif;
import com.sipilah.sipilah.model.Transaksi;

import java.util.List;

public interface LayananSampah {

    // Kategori Sampah
    KategoriSampah tambahKategori(KategoriSampah kategori);
    List<KategoriSampah> semuaKategori();
    KategoriSampah cariKategoriById(Long id);
    void hapusKategori(Long id);

    // Pengguna
    PenggunaAktif simpanPengguna(PenggunaAktif pengguna);
    List<Pengguna> getPengguna();
    void hapusPengguna(Long id);

    // Transaksi
    Transaksi simpanTransaksi(Transaksi transaksi);
    List<Transaksi> getTransaksi();
    void hapusTransaksi(Long id);

    // Jenis Sampah
    JenisSampah tambahJenis(JenisSampah jenis);
    List<JenisSampah> semuaJenis();
    JenisSampah cariJenisById(Long id);
    List<JenisSampah> jenisByKategori(Long kategoriId);
    void hapusJenis(Long id);

    List<JenisSampah> getJenisSampah();
    List<KategoriSampah> getKategoriSampah();
    double totalSampah();
}