package com.sipilah.sipilah.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class RequestPenjemputan extends EntitasDasar {

    @ManyToOne(fetch = FetchType.EAGER)
    private PenggunaAktif warga;

    @ManyToOne(fetch = FetchType.EAGER)
    private Petugas petugas;

    private Double latitude;
    private Double longitude;
    private String alamatLengkap;
    private String catatan;

    @Enumerated(EnumType.STRING)
    private StatusRequest status = StatusRequest.MENUNGGU;

    public enum StatusRequest {
        MENUNGGU, DITERIMA, SELESAI, DITOLAK
    }
}