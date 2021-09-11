package com.zupacademy.italo.propostas.outrossistemas.analise;

import com.zupacademy.italo.propostas.propostas.Proposta;

public class AnalisePropostaRequest {
    private final String documento;
    private final String nome;
    private final String idProposta;

    public AnalisePropostaRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
