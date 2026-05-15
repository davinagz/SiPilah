package com.sipilah.sipilah.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import lombok.Getter;
import lombok.Setter;

@Entity

// =====================
// INHERITANCE OOP
// =====================

@Inheritance(strategy = InheritanceType.JOINED)

@DiscriminatorColumn(
        name = "role",
        discriminatorType = DiscriminatorType.STRING
)

// =====================
// JSON ROLE MAPPING
// =====================

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "role"
)

@JsonSubTypes({

        @JsonSubTypes.Type(
                value = Admin.class,
                name = "ADMIN"
        ),

        @JsonSubTypes.Type(
                value = PenggunaAktif.class,
                name = "PENGGUNA"
        ),

        @JsonSubTypes.Type(
                value = Petugas.class,
                name = "PETUGAS"
        )

})

@Getter
@Setter

public abstract class Pengguna extends EntitasDasar {

    // =====================
    // DATA PENGGUNA
    // =====================

    @Column(nullable = false)
    private String nama;

    @Column(nullable = false)
    private String alamat;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String noTelepon;

    // =====================
    // STATUS AKUN
    // =====================

    /*
     * PENDING
     * APPROVED
     */

    @Column(nullable = false)
    private String status = "PENDING";

    // =====================
    // ROLE ABSTRACT
    // =====================

    public abstract String getRole();
}