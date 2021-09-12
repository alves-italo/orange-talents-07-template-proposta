package com.zupacademy.italo.propostas.outrossistemas.cartao;

import java.time.LocalDate;

public class AvisoLegadoRequest {
    private String destino;
    private LocalDate validoAte;

    @Deprecated
    public AvisoLegadoRequest() {
    }

    public AvisoLegadoRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
