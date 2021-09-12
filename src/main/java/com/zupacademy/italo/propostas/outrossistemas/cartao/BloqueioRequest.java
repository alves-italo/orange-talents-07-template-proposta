package com.zupacademy.italo.propostas.outrossistemas.cartao;

import javax.validation.constraints.NotBlank;

public class BloqueioRequest {
    @NotBlank
    private String sistemaResponsavel;

    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
