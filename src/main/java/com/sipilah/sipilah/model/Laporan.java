package com.sipilah.sipilah.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Laporan extends EntitasDasar {

    @ManyToOne
    private Pengguna pengguna;

    private Double totalBerat;
    private Integer totalTransaksi;
    private String periode;
}