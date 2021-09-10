package com.zupacademy.italo.propostas.cadastrobiometria;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zupacademy.italo.propostas.utilidades.Base64;

import javax.validation.constraints.NotBlank;

public class NovaBiometriaRequest {
    @NotBlank
    @Base64
    private String fingerprint;

    public NovaBiometriaRequest(@JsonProperty("fingerprint") String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometria toModel(String numeroCartao) {
        return new Biometria(this.fingerprint, numeroCartao);
    }
}
