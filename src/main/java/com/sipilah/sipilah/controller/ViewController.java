package com.sipilah.sipilah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home() {
        return "admin";
    }

    @GetMapping("/pengguna")
    public String pengguna() {
        return "pengguna";
    }

    @GetMapping("/petugas")
    public String petugas() {
        return "petugas";
    }
}