package com.sipilah.sipilah.service;

import com.sipilah.sipilah.model.Transaksi;

import java.util.List;

public interface LayananTransaksi {

    // Petugas - input transaksi, pilih warga yang sudah terdaftar
    Transaksi inputTransaksi(Transaksi transaksi);

    // Petugas - update status/keterangan transaksi
    Transaksi updateTransaksi(Long id, Transaksi transaksi);

    // Petugas & Admin - hapus transaksi
    void hapusTransaksi(Long id);

    // Admin - lihat semua transaksi
    List<Transaksi> semuaTransaksi();

    // Warga - lihat riwayat transaksi miliknya sendiri
    List<Transaksi> transaksiByPengguna(Long penggunaId);

    // Warga - lihat total berat sampah yang sudah disetor
    Double totalBeratByPengguna(Long penggunaId);

    // Warga - lihat jumlah transaksi
    Integer totalTransaksiByPengguna(Long penggunaId);
}