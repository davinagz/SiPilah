package com.sipilah.sipilah.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class KategoriSampah extends EntitasDasar {
    private String namaKategori;
    private String deskripsi;
}