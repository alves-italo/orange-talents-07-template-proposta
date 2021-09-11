package com.zupacademy.italo.propostas.cartoes;

import com.zupacademy.italo.propostas.outrossistemas.cartao.CartaoResponse;
import com.zupacademy.italo.propostas.outrossistemas.cartao.ConsultaCartaoClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificaCartaoService {
    @Autowired
    ConsultaCartaoClient consultaCartaoClient;

    public boolean existe(String numeroCartao) {
        try {
            CartaoResponse cartaoResponse = consultaCartaoClient.consultaNumero(numeroCartao);
            return true;
        } catch (FeignException exception) {
            return false;
        }
    }
}
