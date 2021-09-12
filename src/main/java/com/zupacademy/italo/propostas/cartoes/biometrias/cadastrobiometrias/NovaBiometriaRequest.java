package com.zupacademy.italo.propostas.cartoes.biometrias.cadastrobiometrias;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zupacademy.italo.propostas.cartoes.biometrias.Biometria;
import com.zupacademy.italo.propostas.utilidades.Base64;

import javax.validation.constraints.NotBlank;

public class NovaBiometriaRequest {
    @NotBlank
    @Base64
    private final String fingerprint;

    public NovaBiometriaRequest(@JsonProperty("fingerprint") String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometria toModel() {
        return new Biometria(this.fingerprint);
    }
}
