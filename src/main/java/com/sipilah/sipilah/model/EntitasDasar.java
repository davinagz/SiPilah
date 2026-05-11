package com.sipilah.sipilah.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
public abstract class EntitasDasar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dibuatPada = LocalDateTime.now();
}