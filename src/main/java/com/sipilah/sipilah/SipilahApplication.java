package com.sipilah.sipilah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.sipilah.sipilah.model.KategoriSampah;
import com.sipilah.sipilah.model.Admin;
import com.sipilah.sipilah.model.Petugas;

import com.sipilah.sipilah.repository.RepoKategori;
import com.sipilah.sipilah.repository.RepoAdmin;
import com.sipilah.sipilah.repository.RepoPetugas;

@SpringBootApplication
public class SipilahApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                SipilahApplication.class,
                args
        );
    }

    @Bean
    CommandLineRunner initData(

            RepoKategori kategoriRepo,
            RepoAdmin repoAdmin,
            RepoPetugas repoPetugas

    ) {

        return args -> {

            // ==============================
            // KATEGORI SAMPAH
            // ==============================

            if (kategoriRepo.count() == 0) {

                KategoriSampah organik =
                        new KategoriSampah();

                organik.setNamaKategori(
                        "Organik"
                );

                organik.setDeskripsi(
                        "Sampah organik seperti sisa makanan"
                );

                kategoriRepo.save(organik);

                KategoriSampah anorganik =
                        new KategoriSampah();

                anorganik.setNamaKategori(
                        "Anorganik"
                );

                anorganik.setDeskripsi(
                        "Sampah anorganik seperti plastik"
                );

                kategoriRepo.save(anorganik);

                KategoriSampah b3 =
                        new KategoriSampah();

                b3.setNamaKategori(
                        "B3"
                );

                b3.setDeskripsi(
                        "Bahan Berbahaya dan Beracun"
                );

                kategoriRepo.save(b3);

                System.out.println(
                        "✅ Kategori sampah berhasil dibuat!"
                );
            }

            // ==============================
            // ADMIN DEFAULT
            // ==============================

            if (repoAdmin.count() == 0) {

                Admin admin =
                        new Admin();

                admin.setNama(
                        "Admin SIPILAH"
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
                        "081234567890"
                );

                admin.setStatus(
                        "APPROVED"
                );

                repoAdmin.save(admin);

                System.out.println(
                        "✅ Admin berhasil dibuat!"
                );
            }

            // ==============================
            // PETUGAS DEFAULT
            // ==============================

            if (repoPetugas.count() == 0) {

                Petugas petugas =
                        new Petugas();

                petugas.setNama(
                        "Budi Budiman"
                );

                petugas.setAlamat(
                        "Bandung Barat"
                );

                petugas.setEmail(
                        "budi@sipilah.com"
                );

                petugas.setPassword(
                        "budi123"
                );

                petugas.setNoTelepon(
                        "082118713616"
                );

                petugas.setStatus(
                        "APPROVED"
                );

                petugas.setWilayahTugas(
                        "Bandung Barat"
                );

                repoPetugas.save(
                        petugas
                );

                System.out.println(
                        "✅ Petugas berhasil dibuat!"
                );
            }
        };
    }
}