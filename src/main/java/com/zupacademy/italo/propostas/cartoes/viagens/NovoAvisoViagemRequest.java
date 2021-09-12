package com.zupacademy.italo.propostas.cartoes.viagens;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class NovoAvisoViagemRequest {
    @NotBlank
    private String destino;
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataRetorno;

    public NovoAvisoViagemRequest(String destino, @JsonProperty("dataRetorno") LocalDate dataRetorno) {
        this.destino = destino;
        this.dataRetorno = dataRetorno;
    }

    public AvisoViagem toModel(String ipCliente, String userAgentCliente) {
        return new AvisoViagem(this.destino, this.dataRetorno, ipCliente, userAgentCliente);
    }
}
