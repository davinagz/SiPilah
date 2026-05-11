package com.sipilah.sipilah.service;

import com.sipilah.sipilah.model.RequestPenjemputan;

import java.util.List;

public interface LayananPenjemputan {
    RequestPenjemputan buatRequest(Long wargaId, Double latitude, Double longitude, String alamat, String catatan);
    RequestPenjemputan terimaRequest(Long requestId, Long petugasId);
    RequestPenjemputan selesaikanRequest(Long requestId);
    RequestPenjemputan tolakRequest(Long requestId);
    List<RequestPenjemputan> requestMenunggu();
    List<RequestPenjemputan> requestByWarga(Long wargaId);
    List<RequestPenjemputan> semuaRequest();
}