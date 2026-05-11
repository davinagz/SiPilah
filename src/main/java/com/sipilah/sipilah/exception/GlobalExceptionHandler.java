package com.sipilah.sipilah.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sipilah.sipilah.model.PenggunaAktif;
import com.sipilah.sipilah.model.Transaksi;
import com.sipilah.sipilah.service.LayananSampah;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final LayananSampah service;

    public GlobalExceptionHandler(LayananSampah service) {
        this.service = service;
    }

    @ExceptionHandler(CustomException.class)
    public String handleCustomException(CustomException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("transaksiList", service.getTransaksi());
        model.addAttribute("penggunaList", service.getPengguna());
        model.addAttribute("jenisSampahList", service.getJenisSampah());
        model.addAttribute("transaksi", new Transaksi());
        model.addAttribute("pengguna", new PenggunaAktif());
        model.addAttribute("total", service.totalSampah());
        return "index";
    }
}
