package com.zupacademy.italo.propostas.cartoes.carteiras;

import com.zupacademy.italo.propostas.cartoes.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String email;

    private String servico;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String email, String servico) {
        this.email = email;
        this.servico = servico;
    }

    public Long getId() {
        return id;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public String getEmail() {
        return email;
    }

    public String getServico() {
        return servico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carteira carteira = (Carteira) o;
        return email.equals(carteira.email) && servico.equals(carteira.servico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, servico);
    }
}
