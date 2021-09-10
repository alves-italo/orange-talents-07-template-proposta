package com.zupacademy.italo.propostas.outrossistemas.analise;

public class AnalisePropostaRequest {
    private final String documento;
    private final String nome;
    private final String idProposta;

    public AnalisePropostaRequest(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
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
