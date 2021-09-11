package com.zupacademy.italo.propostas.outrossistemas.cartao;

public class BloqueioRequest {
    private String sistemaResponsavel;

    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
