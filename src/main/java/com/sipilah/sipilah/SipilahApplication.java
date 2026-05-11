package com.sipilah.sipilah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.sipilah.sipilah.model.KategoriSampah;
import com.sipilah.sipilah.repository.RepoKategori;

@SpringBootApplication
public class SipilahApplication {

    public static void main(String[] args) {
        SpringApplication.run(SipilahApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(RepoKategori kategoriRepo) {
        return args -> {
            if (kategoriRepo.count() == 0) {
                KategoriSampah organik = new KategoriSampah();
                organik.setNamaKategori("Organik");
                organik.setDeskripsi("Sampah organik seperti sisa makanan");
                kategoriRepo.save(organik);

                KategoriSampah anorganik = new KategoriSampah();
                anorganik.setNamaKategori("Anorganik");
                anorganik.setDeskripsi("Sampah anorganik seperti plastik");
                kategoriRepo.save(anorganik);

                KategoriSampah b3 = new KategoriSampah();
                b3.setNamaKategori("B3");
                b3.setDeskripsi("Bahan Berbahaya dan Beracun");
                kategoriRepo.save(b3);
            }
        };
    }
}