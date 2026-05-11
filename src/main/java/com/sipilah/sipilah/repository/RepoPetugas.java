package com.sipilah.sipilah.repository;

import com.sipilah.sipilah.model.Petugas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoPetugas extends JpaRepository<Petugas, Long> {
    List<Petugas> findByWilayahTugas(String wilayahTugas);
}