package com.zupacademy.italo.propostas.cadastrobiometria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String fingerprint;
    @NotBlank
    private String numeroCartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String fingerprint, String numeroCartao) {
        this.fingerprint = fingerprint;
        this.numeroCartao = numeroCartao;
    }

    public Long getId() {
        return id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
