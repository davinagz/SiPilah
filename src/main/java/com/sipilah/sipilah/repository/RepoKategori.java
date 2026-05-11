package com.sipilah.sipilah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sipilah.sipilah.model.KategoriSampah;

public interface RepoKategori extends JpaRepository<KategoriSampah, Long> {

    KategoriSampah findByNamaKategori(String namaKategori);

}