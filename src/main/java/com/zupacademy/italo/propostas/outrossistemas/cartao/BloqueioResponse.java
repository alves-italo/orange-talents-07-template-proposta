package com.zupacademy.italo.propostas.outrossistemas.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class BloqueioResponse {
    @NotBlank
    private String resultado;

    public BloqueioResponse(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
