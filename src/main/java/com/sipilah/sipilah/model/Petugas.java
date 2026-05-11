package com.sipilah.sipilah.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "petugas")
@DiscriminatorValue("PETUGAS")
@Getter @Setter
public class Petugas extends Pengguna {

    private String wilayahTugas;
    private String nomorInduk;

    @Override
    public String getRole() {
        return "PETUGAS";
    }
}