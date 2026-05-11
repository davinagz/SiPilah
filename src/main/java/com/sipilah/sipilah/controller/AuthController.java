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

        Pengguna pengguna = layananPengguna.cariByEmail(email);

        if (!pengguna.getPassword().equals(password)) {
            Map<String, String> error = new HashMap<>();
            error.put("pesan", "Email atau password salah");
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