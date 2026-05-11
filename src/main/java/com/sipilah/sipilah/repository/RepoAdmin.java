package com.sipilah.sipilah.repository;

import com.sipilah.sipilah.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoAdmin extends JpaRepository<Admin, Long> {
}