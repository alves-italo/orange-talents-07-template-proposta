package com.zupacademy.italo.propostas.cartoes.biometrias;

import com.zupacademy.italo.propostas.cartoes.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String fingerprint;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Long getId() {
        return id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public String getNumeroCartao() {
        return this.cartao.getNumero();
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biometria biometria = (Biometria) o;
        return Objects.equals(id, biometria.id) && Objects.equals(fingerprint, biometria.fingerprint) && Objects.equals(cartao, biometria.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fingerprint, cartao);
    }
}
