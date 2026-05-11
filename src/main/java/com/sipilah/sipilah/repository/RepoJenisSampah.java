package com.sipilah.sipilah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sipilah.sipilah.model.JenisSampah;
import java.util.List;

public interface RepoJenisSampah extends JpaRepository<JenisSampah, Long> {

    List<JenisSampah> findByKategoriId(Long kategoriId);

}