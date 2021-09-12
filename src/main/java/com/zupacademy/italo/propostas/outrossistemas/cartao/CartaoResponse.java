package com.zupacademy.italo.propostas.outrossistemas.cartao;

import com.zupacademy.italo.propostas.cartoes.Cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartaoResponse {
    @NotBlank
    private final String id;
    @NotBlank
    private final String titular;
    private final LocalDateTime emitidoEm;
    @NotNull
    private final BigDecimal limite;

    public CartaoResponse(String id, String titular, LocalDateTime emitidoEm, BigDecimal limite) {
        this.id = id;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.limite = limite;
    }

    public Cartao toModel() {
        return new Cartao(this.id, this.titular, this.emitidoEm, this.limite);
    }
}
