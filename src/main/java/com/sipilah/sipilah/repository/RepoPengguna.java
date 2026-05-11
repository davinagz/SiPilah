package com.sipilah.sipilah.repository;

import com.sipilah.sipilah.model.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepoPengguna extends JpaRepository<Pengguna, Long> {
    Optional<Pengguna> findByEmail(String email);
    boolean existsByEmail(String email);
}