package com.zupacademy.italo.propostas.cartoes.carteiras.cadastrocarteiras;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zupacademy.italo.propostas.cartoes.carteiras.Carteira;
import com.zupacademy.italo.propostas.cartoes.carteiras.ServicoCarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovaCarteiraRequest {
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    private final ServicoCarteira carteira;

    public NovaCarteiraRequest(String email, @JsonProperty("carteira") ServicoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public Carteira toModel() {
        return new Carteira(this.email, this.carteira);
    }
}
