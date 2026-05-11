package com.sipilah.sipilah.service.impl;

import com.sipilah.sipilah.exception.CustomException;
import com.sipilah.sipilah.model.PenggunaAktif;
import com.sipilah.sipilah.model.Petugas;
import com.sipilah.sipilah.model.RequestPenjemputan;
import com.sipilah.sipilah.repository.RepoPenggunaAktif;
import com.sipilah.sipilah.repository.RepoPetugas;
import com.sipilah.sipilah.repository.RepoRequestPenjemputan;
import com.sipilah.sipilah.service.LayananPenjemputan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LayananPenjemputanImpl implements LayananPenjemputan {

    private final RepoRequestPenjemputan repoRequest;
    private final RepoPenggunaAktif repoPenggunaAktif;
    private final RepoPetugas repoPetugas;

    @Override
    public RequestPenjemputan buatRequest(Long wargaId, Double latitude, Double longitude, String alamat, String catatan) {
        PenggunaAktif warga = repoPenggunaAktif.findById(wargaId)
                .orElseThrow(() -> new CustomException("Warga tidak ditemukan"));
        RequestPenjemputan req = new RequestPenjemputan();
        req.setWarga(warga);
        req.setLatitude(latitude);
        req.setLongitude(longitude);
        req.setAlamatLengkap(alamat);
        req.setCatatan(catatan);
        req.setStatus(RequestPenjemputan.StatusRequest.MENUNGGU);
        return repoRequest.save(req);
    }

    @Override
    public RequestPenjemputan terimaRequest(Long requestId, Long petugasId) {
        RequestPenjemputan req = repoRequest.findById(requestId)
                .orElseThrow(() -> new CustomException("Request tidak ditemukan"));
        Petugas petugas = repoPetugas.findById(petugasId)
                .orElseThrow(() -> new CustomException("Petugas tidak ditemukan"));
        req.setStatus(RequestPenjemputan.StatusRequest.DITERIMA);
        req.setPetugas(petugas);
        return repoRequest.save(req);
    }

    @Override
    public RequestPenjemputan selesaikanRequest(Long requestId) {
        RequestPenjemputan req = repoRequest.findById(requestId)
                .orElseThrow(() -> new CustomException("Request tidak ditemukan"));
        req.setStatus(RequestPenjemputan.StatusRequest.SELESAI);
        return repoRequest.save(req);
    }

    @Override
    public RequestPenjemputan tolakRequest(Long requestId) {
        RequestPenjemputan req = repoRequest.findById(requestId)
                .orElseThrow(() -> new CustomException("Request tidak ditemukan"));
        req.setStatus(RequestPenjemputan.StatusRequest.DITOLAK);
        return repoRequest.save(req);
    }

    @Override
    public List<RequestPenjemputan> requestMenunggu() {
        return repoRequest.findByStatus(RequestPenjemputan.StatusRequest.MENUNGGU);
    }

    @Override
    public List<RequestPenjemputan> requestByWarga(Long wargaId) {
        return repoRequest.findByWargaId(wargaId);
    }

    @Override
    public List<RequestPenjemputan> semuaRequest() {
        return repoRequest.findAll();
    }
}