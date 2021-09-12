package com.zupacademy.italo.propostas.cartoes.carteiras.detalhecarteiras;

import com.zupacademy.italo.propostas.cartoes.carteiras.Carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraResponse {
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    private final String carteira;

    public CarteiraResponse(Carteira carteira) {
        this.email = carteira.getEmail();
        this.carteira = carteira.getServico();
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
