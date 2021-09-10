package com.zupacademy.italo.propostas.cadastroproposta;

import com.zupacademy.italo.propostas.outrossistemas.analise.ResultadoSolicitacao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String documento;
    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    private BigDecimal salario;

    private StatusProposta status;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.status = StatusProposta.AGUARDANDO_AVALIACAO;
    }

    public Long getId() {
        return id;
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

    public StatusProposta getStatus() {
        return status;
    }

    public void processaAnalise(ResultadoSolicitacao resultado) {
        if (resultado.equals(ResultadoSolicitacao.SEM_RESTRICAO)) {
            this.status = StatusProposta.ELEGIVEL;
        } else if (resultado.equals(ResultadoSolicitacao.COM_RESTRICAO)) {
            this.status = StatusProposta.NAO_ELEGIVEL;
        }
    }
}
