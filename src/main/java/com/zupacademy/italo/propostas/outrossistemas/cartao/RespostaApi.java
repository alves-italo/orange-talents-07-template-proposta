package com.zupacademy.italo.propostas.outrossistemas.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class RespostaApi {
    @NotBlank
    private final String resultado;

    public RespostaApi(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
