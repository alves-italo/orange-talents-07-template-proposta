package com.zupacademy.italo.propostas.detalheproposta;

import com.zupacademy.italo.propostas.cadastroproposta.Proposta;

import java.math.BigDecimal;

public class PropostaResponse {
    private final String documento;
    private final String email;
    private final String nome;
    private final String endereco;
    private final BigDecimal salario;
    private final String status;
    private final String numeroCartao;

    public PropostaResponse(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatus().toString();
        this.numeroCartao = proposta.getNumeroCartao();
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getStatus() {
        return status;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
