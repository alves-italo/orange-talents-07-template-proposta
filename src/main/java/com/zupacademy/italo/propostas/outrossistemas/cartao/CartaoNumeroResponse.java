package com.zupacademy.italo.propostas.outrossistemas.cartao;

public class CartaoNumeroResponse {
    private String id;

    @Deprecated
    public CartaoNumeroResponse() {
    }

    public CartaoNumeroResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
