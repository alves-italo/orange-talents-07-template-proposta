package com.zupacademy.italo.propostas.cartoes.carteiras.cadastrocarteiras;

import com.zupacademy.italo.propostas.cartoes.carteiras.Carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovaCarteiraRequest {
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    private final String carteira;

    public NovaCarteiraRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public Carteira toModel() {
        return new Carteira(this.email, this.carteira);
    }
}
