package com.sipilah.sipilah.repository;

import com.sipilah.sipilah.model.PenggunaAktif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoPenggunaAktif extends JpaRepository<PenggunaAktif, Long> {
    List<PenggunaAktif> findByRtRw(String rtRw);
}