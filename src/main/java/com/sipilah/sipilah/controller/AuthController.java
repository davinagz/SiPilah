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

    // =====================================================
    // LOGIN
    // =====================================================

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> request) {

        // =====================
        // AMBIL DATA REQUEST
        // =====================

        String email = request.get("email");
        String password = request.get("password");

        // =====================
        // VALIDASI INPUT
        // =====================

        if (email == null || password == null
                || email.isEmpty()
                || password.isEmpty()) {

            Map<String, String> error =
                    new HashMap<>();

            error.put(
                    "pesan",
                    "Email dan password wajib diisi"
            );

            return ResponseEntity
                    .badRequest()
                    .body(error);
        }

        // =====================
        // CEK EMAIL
        // =====================

        Pengguna pengguna =
                layananPengguna.cariByEmail(email);

        if (pengguna == null) {

            Map<String, String> error =
                    new HashMap<>();

            error.put(
                    "pesan",
                    "Email tidak ditemukan"
            );

            return ResponseEntity
                    .badRequest()
                    .body(error);
        }

        // =====================
        // CEK STATUS APPROVAL
        // =====================

        if (!pengguna.getStatus()
                .equalsIgnoreCase("APPROVED")) {

            Map<String, String> error =
                    new HashMap<>();

            error.put(
                    "pesan",
                    "Akun belum dikonfirmasi admin"
            );

            return ResponseEntity
                    .badRequest()
                    .body(error);
        }

        // =====================
        // CEK PASSWORD
        // =====================

        if (!pengguna.getPassword()
                .equals(password)) {

            Map<String, String> error =
                    new HashMap<>();

            error.put(
                    "pesan",
                    "Password salah"
            );

            return ResponseEntity
                    .badRequest()
                    .body(error);
        }

        // =====================
        // RESPONSE BERHASIL
        // =====================

        Map<String, String> response =
                new HashMap<>();

        response.put(
                "nama",
                pengguna.getNama()
        );

        response.put(
                "email",
                pengguna.getEmail()
        );

        response.put(
                "role",
                pengguna.getRole()
        );

        response.put(
                "status",
                pengguna.getStatus()
        );

        response.put(
                "pesan",
                "Login berhasil sebagai "
                        + pengguna.getRole()
        );

        return ResponseEntity.ok(response);
    }
}