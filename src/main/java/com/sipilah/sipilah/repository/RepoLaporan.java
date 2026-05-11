package com.sipilah.sipilah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sipilah.sipilah.model.Laporan;

import java.util.List;

public interface RepoLaporan extends JpaRepository<Laporan, Long> {

    List<Laporan> findByPeriode(String periode);

}