package com.zupacademy.italo.propostas.cartoes;

import com.zupacademy.italo.propostas.outrossistemas.cartao.CartaoResponse;
import com.zupacademy.italo.propostas.outrossistemas.cartao.CartaoLegadoClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificaCartaoService {
    @Autowired
    CartaoLegadoClient cartaoLegadoClient;

    public boolean existe(String numeroCartao) {
        try {
            CartaoResponse cartaoResponse = cartaoLegadoClient.consultaNumero(numeroCartao);
            return true;
        } catch (FeignException exception) {
            return false;
        }
    }
}
