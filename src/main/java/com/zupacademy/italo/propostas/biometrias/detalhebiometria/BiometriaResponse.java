package com.zupacademy.italo.propostas.biometrias.detalhebiometria;

import com.zupacademy.italo.propostas.biometrias.Biometria;

public class BiometriaResponse {
    private String fingerprint;
    private String numeroCartao;

    public BiometriaResponse(Biometria biometria) {
        this.fingerprint = biometria.getFingerprint();
        this.numeroCartao = biometria.getNumeroCartao();
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
