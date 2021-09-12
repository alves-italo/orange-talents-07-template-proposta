package com.zupacademy.italo.propostas.cartoes.viagens;

import com.zupacademy.italo.propostas.cartoes.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destino;

    private LocalDate validoAte;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @ManyToOne
    private Cartao cartao;

    private String ipCliente;

    private String userAgentCliente;

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(String destino, LocalDate validoAte, String ipCliente, String userAgentCliente) {
        this.destino = destino;
        this.validoAte = validoAte;
        this.ipCliente = ipCliente;
        this.userAgentCliente = userAgentCliente;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
