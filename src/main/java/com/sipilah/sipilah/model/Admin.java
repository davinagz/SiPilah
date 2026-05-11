package com.sipilah.sipilah.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "admin")
@DiscriminatorValue("ADMIN")
@Getter @Setter
public class Admin extends Pengguna {

    private String jabatan;

    @Override
    public String getRole() {
        return "ADMIN";
    }
}