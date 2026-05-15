package com.sipilah.sipilah.controller;

import com.sipilah.sipilah.model.Pengguna;
import com.sipilah.sipilah.service.LayananPengguna;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LayananPengguna layananPengguna;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        // Cek email terdaftar
        Pengguna pengguna = null;
        try {
            pengguna = layananPengguna.cariByEmail(email);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("pesan", "EMAIL_TIDAK_TERDAFTAR");
            return ResponseEntity.status(404).body(error);
        }

        // Cek password
        if (!pengguna.getPassword().equals(password)) {
            Map<String, String> error = new HashMap<>();
            error.put("pesan", "PASSWORD_SALAH");
            return ResponseEntity.badRequest().body(error);
        }

        Map<String, String> response = new HashMap<>();
        response.put("nama", pengguna.getNama());
        response.put("email", pengguna.getEmail());
        response.put("role", pengguna.getRole());
        response.put("pesan", "Login berhasil sebagai " + pengguna.getRole());

        return ResponseEntity.ok(response);
    }
}