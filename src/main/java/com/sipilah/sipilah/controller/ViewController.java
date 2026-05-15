package com.sipilah.sipilah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/petugas")
    public String petugas() {
        return "petugas";
    }

    @GetMapping("/pengguna")
    public String pengguna() {
        return "pengguna";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}