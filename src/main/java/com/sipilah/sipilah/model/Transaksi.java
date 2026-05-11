package com.sipilah.sipilah.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Transaksi extends EntitasDasar {

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"password", "dibuatPada"})
    private Pengguna pengguna;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"dibuatPada"})
    private JenisSampah jenisSampah;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"dibuatPada"})
    private KategoriSampah kategoriSampah;

    private Double berat;
    private String tanggalSetor;
    private String keterangan;
}