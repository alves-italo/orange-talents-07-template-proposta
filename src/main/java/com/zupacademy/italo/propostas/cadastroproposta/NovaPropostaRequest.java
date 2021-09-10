package com.zupacademy.italo.propostas.cadastroproposta;

import com.zupacademy.italo.propostas.utilidades.CPFouCNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class NovaPropostaRequest {
    @NotBlank
    @CPFouCNPJ
    private final String documento;
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    private final String nome;
    @NotBlank
    private final String endereco;
    @NotNull
    @PositiveOrZero
    private final BigDecimal salario;

    public NovaPropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel() {
        return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);
    }

    public String getDocumento() {
        return this.documento;
    }
}
