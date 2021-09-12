package com.zupacademy.italo.propostas.outrossistemas.analise;

import com.zupacademy.italo.propostas.propostas.Proposta;

import javax.validation.constraints.NotBlank;

public class AnalisePropostaRequest {
    @NotBlank
    private final String documento;
    @NotBlank
    private final String nome;
    @NotBlank
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
