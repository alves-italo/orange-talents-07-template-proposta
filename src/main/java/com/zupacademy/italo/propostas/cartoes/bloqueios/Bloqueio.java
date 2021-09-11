package com.zupacademy.italo.propostas.cartoes.bloqueios;

import com.zupacademy.italo.propostas.cartoes.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String ipCliente;
    @NotBlank
    private String userAgentCliente;

    @CreationTimestamp
    private LocalDateTime bloqueadoEm;

    private boolean ativo;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String ipCliente, String userAgentCliente, Cartao cartao) {
        this.ipCliente = ipCliente;
        this.userAgentCliente = userAgentCliente;
        this.ativo = true;
        this.cartao = cartao;
    }

    public boolean ativo() {
        return this.ativo;
    }
}
