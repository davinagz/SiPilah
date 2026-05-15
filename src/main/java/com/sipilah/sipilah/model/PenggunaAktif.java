package com.sipilah.sipilah.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity

@Table(name = "pengguna_aktif")

@DiscriminatorValue("PENGGUNA")

@Getter
@Setter

public class PenggunaAktif
        extends Pengguna {

    // KHUSUS WARGA

    @Column(nullable = false)
    private String rtRw;

    @Override
    public String getRole() {

        return "PENGGUNA";
    }
}