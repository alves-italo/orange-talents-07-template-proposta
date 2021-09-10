package com.zupacademy.italo.propostas.outrossistemas.analise;

public class AnalisePropostaResponse {
    private final String documento;
    private final String nome;
    private final String idProposta;
    private final ResultadoSolicitacao resultadoSolicitacao;

    public AnalisePropostaResponse(String documento, String nome, String idProposta, ResultadoSolicitacao resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    public ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
