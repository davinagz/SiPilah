package com.sipilah.sipilah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // 1. Halaman Utama
    @GetMapping("/")
    public String home() {
        return "index"; // Dia akan otomatis mencari templates/index.html
    }

    // 2. Halaman Pengguna
    @GetMapping("/pengguna")
    public String halamanPengguna() {
        return "pengguna"; // Dia akan mencari templates/pengguna.html
    }

    // 3. Halaman Petugas
    @GetMapping("/petugas")
    public String halamanPetugas() {
        return "petugas"; // Dia akan mencari templates/petugas.html
    }
}