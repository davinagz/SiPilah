package com.sipilah.sipilah.repository;

import com.sipilah.sipilah.model.RequestPenjemputan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoRequestPenjemputan extends JpaRepository<RequestPenjemputan, Long> {
    List<RequestPenjemputan> findByWargaId(Long wargaId);
    List<RequestPenjemputan> findByStatus(RequestPenjemputan.StatusRequest status);
    List<RequestPenjemputan> findByPetugasId(Long petugasId);
}