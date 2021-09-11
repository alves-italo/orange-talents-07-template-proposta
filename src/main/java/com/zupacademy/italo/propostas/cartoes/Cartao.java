package com.zupacademy.italo.propostas.cartoes;

import com.zupacademy.italo.propostas.cartoes.bloqueios.Bloqueio;
import com.zupacademy.italo.propostas.propostas.Proposta;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String numero;
    @NotBlank
    private String titular;
    private LocalDateTime emitidoEm;
    private BigDecimal limite;

    private Long idProposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private final List<Bloqueio> bloqueios = new ArrayList<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numero, String titular, LocalDateTime emitidoEm, BigDecimal limite) {
        this.numero = numero;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.limite = limite;
    }

    public void setProposta(Proposta proposta) {
        this.idProposta = proposta.getId();
    }

    public String getNumero() {
        return numero;
    }

    public boolean ativo() {
        return this.bloqueios.stream().noneMatch(Bloqueio::ativo);
    }

    public void configuraBloqueio(String ipCliente, String userAgentCliente) {
        Assert.isTrue(this.ativo(), "Cartão já está bloqueado");

        Bloqueio bloqueio = new Bloqueio(ipCliente, userAgentCliente, this);

        this.bloqueios.add(bloqueio);
    }
}
