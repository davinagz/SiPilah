package com.sipilah.sipilah.controller;

import com.sipilah.sipilah.model.RequestPenjemputan;
import com.sipilah.sipilah.service.LayananPenjemputan;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PenjemputanController {

    private final LayananPenjemputan layananPenjemputan;

    // Warga buat request penjemputan
    @PostMapping("/api/pengguna/{id}/request-penjemputan")
    public ResponseEntity<RequestPenjemputan> buatRequest(
            @PathVariable Long id,
            @RequestBody Map<String, Object> body) {
        Double lat = Double.valueOf(body.get("latitude").toString());
        Double lng = Double.valueOf(body.get("longitude").toString());
        String alamat = body.getOrDefault("alamat", "").toString();
        String catatan = body.getOrDefault("catatan", "").toString();
        return ResponseEntity.ok(layananPenjemputan.buatRequest(id, lat, lng, alamat, catatan));
    }

    // Warga lihat status requestnya
    @GetMapping("/api/pengguna/{id}/request-penjemputan")
    public ResponseEntity<List<RequestPenjemputan>> requestWarga(@PathVariable Long id) {
        return ResponseEntity.ok(layananPenjemputan.requestByWarga(id));
    }

    // Petugas lihat request yang menunggu
    @GetMapping("/api/petugas/request-penjemputan")
    public ResponseEntity<List<RequestPenjemputan>> requestMenunggu() {
        return ResponseEntity.ok(layananPenjemputan.requestMenunggu());
    }

    // Petugas terima request
    @PutMapping("/api/petugas/request-penjemputan/{requestId}/terima")
    public ResponseEntity<RequestPenjemputan> terimaRequest(
            @PathVariable Long requestId,
            @RequestBody Map<String, Object> body) {
        Long petugasId = Long.valueOf(body.get("petugasId").toString());
        return ResponseEntity.ok(layananPenjemputan.terimaRequest(requestId, petugasId));
    }

    // Petugas selesaikan request
    @PutMapping("/api/petugas/request-penjemputan/{requestId}/selesai")
    public ResponseEntity<RequestPenjemputan> selesaikanRequest(@PathVariable Long requestId) {
        return ResponseEntity.ok(layananPenjemputan.selesaikanRequest(requestId));
    }

    // Admin lihat semua request
    @GetMapping("/api/admin/request-penjemputan")
    public ResponseEntity<List<RequestPenjemputan>> semuaRequest() {
        return ResponseEntity.ok(layananPenjemputan.semuaRequest());
    }
}