package com.zupacademy.italo.propostas.outrossistemas.cartao;

import javax.validation.constraints.NotBlank;

public class BloqueioLegadoRequest {
    @NotBlank
    private final String sistemaResponsavel;

    public BloqueioLegadoRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
