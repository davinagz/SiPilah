package com.sipilah.sipilah.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class JenisSampah extends EntitasDasar {

    private String namaSampah;

    @ManyToOne
    private KategoriSampah kategori;

    private String satuan;
}