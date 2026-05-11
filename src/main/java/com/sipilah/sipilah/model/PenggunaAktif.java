package com.sipilah.sipilah.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pengguna_aktif")
@DiscriminatorValue("PENGGUNA")
@Getter @Setter
public class PenggunaAktif extends Pengguna {

    private String alamat;
    private String rtRw;

    @Override
    public String getRole() {
        return "PENGGUNA";
    }
}