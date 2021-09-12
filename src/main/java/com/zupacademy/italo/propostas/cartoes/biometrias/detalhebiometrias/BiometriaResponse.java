package com.zupacademy.italo.propostas.cartoes.biometrias.detalhebiometrias;

import com.zupacademy.italo.propostas.cartoes.biometrias.Biometria;

public class BiometriaResponse {
    private final String fingerprint;
    private final String numeroCartao;

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
