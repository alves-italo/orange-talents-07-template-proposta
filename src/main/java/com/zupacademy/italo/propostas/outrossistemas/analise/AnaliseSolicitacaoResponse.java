package com.zupacademy.italo.propostas.outrossistemas.analise;

import javax.validation.constraints.NotBlank;

public class AnaliseSolicitacaoResponse {
    @NotBlank
    private final String documento;
    @NotBlank
    private final String nome;
    @NotBlank
    private final String idProposta;
    private final ResultadoSolicitacao resultadoSolicitacao;

    public AnaliseSolicitacaoResponse(String documento, String nome, String idProposta, ResultadoSolicitacao resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    public ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
