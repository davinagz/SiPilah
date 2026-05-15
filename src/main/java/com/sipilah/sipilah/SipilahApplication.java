package com.sipilah.sipilah;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import com.sipilah.sipilah.model.Admin;
import com.sipilah.sipilah.model.KategoriSampah;

import com.sipilah.sipilah.repository.RepoAdmin;
import com.sipilah.sipilah.repository.RepoKategori;

@SpringBootApplication
public class SipilahApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                SipilahApplication.class,
                args
        );
    }

    // =====================================================
    // AUTO INSERT DATA
    // =====================================================

    @Bean
    CommandLineRunner initData(

            RepoKategori kategoriRepo,

            RepoAdmin repoAdmin

    ) {

        return args -> {

            // =====================================================
            // KATEGORI SAMPAH
            // =====================================================

            if (kategoriRepo.count() == 0) {

                // =====================
                // ORGANIK
                // =====================

                KategoriSampah organik =
                        new KategoriSampah();

                organik.setNamaKategori(
                        "Organik"
                );

                organik.setDeskripsi(
                        "Sampah organik seperti sisa makanan"
                );

                kategoriRepo.save(organik);

                // =====================
                // ANORGANIK
                // =====================

                KategoriSampah anorganik =
                        new KategoriSampah();

                anorganik.setNamaKategori(
                        "Anorganik"
                );

                anorganik.setDeskripsi(
                        "Sampah anorganik seperti plastik"
                );

                kategoriRepo.save(anorganik);

                // =====================
                // B3
                // =====================

                KategoriSampah b3 =
                        new KategoriSampah();

                b3.setNamaKategori("B3");

                b3.setDeskripsi(
                        "Bahan Berbahaya dan Beracun"
                );

                kategoriRepo.save(b3);

                System.out.println(
                        "KATEGORI SAMPAH BERHASIL DIBUAT"
                );
            }

            // =====================================================
            // ADMIN DEFAULT
            // =====================================================

            if (repoAdmin.count() == 0) {

                Admin admin = new Admin();

                admin.setNama(
                        "Administrator"
                );

                admin.setAlamat(
                        "Kantor SIPILAH"
                );

                admin.setEmail(
                        "admin@sipilah.com"
                );

                admin.setPassword(
                        "admin123"
                );

                admin.setNoTelepon(
                        "082118713616"
                );

                admin.setStatus(
                        "APPROVED"
                );

                admin.setJabatan(
                        "Super Admin"
                );

                // =====================
                // SIMPAN DATABASE
                // =====================

                repoAdmin.save(admin);

                System.out.println(
                        "ADMIN DEFAULT BERHASIL DIBUAT"
                );
            }
        };
    }
}