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
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private final List<Bloqueio> bloqueios = new ArrayList<>();

    private EstadoCartao estado;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numero, String titular, LocalDateTime emitidoEm, BigDecimal limite) {
        this.numero = numero;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.limite = limite;
        this.estado = EstadoCartao.ATIVO;
    }

    public void setProposta(Proposta proposta) {
        this.idProposta = proposta.getId();
    }

    public String getNumero() {
        return numero;
    }

    public EstadoCartao getEstado() {
        return this.estado;
    }

    public void configuraBloqueio(String ipCliente, String userAgentCliente) {
        Assert.isTrue(this.bloqueios.stream().noneMatch(Bloqueio::ativo), "[BUG] Há mais de um bloqueio ativo no cartão");
        Assert.isTrue(this.estado.equals(EstadoCartao.ATIVO), "Cartão já está bloqueado");

        Bloqueio bloqueio = new Bloqueio(ipCliente, userAgentCliente, this);

        this.bloqueios.add(bloqueio);
        this.estado = EstadoCartao.AGUARDANDO_BLOQUEIO;
    }

    public Bloqueio getBloqueioAtivo() {
        return this.bloqueios.stream().filter(Bloqueio::ativo).collect(Collectors.toList()).get(0);
    }

    public void confirmaBloqueio() {
        this.estado = EstadoCartao.BLOQUEADO;
    }
}
