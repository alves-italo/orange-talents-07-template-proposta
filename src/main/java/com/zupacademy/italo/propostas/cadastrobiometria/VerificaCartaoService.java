package com.zupacademy.italo.propostas.cadastrobiometria;

import com.zupacademy.italo.propostas.outrossistemas.cartao.CartaoNumeroResponse;
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
            CartaoNumeroResponse cartaoNumeroResponse = consultaCartaoClient.consultaNumero(numeroCartao);
            System.out.println(cartaoNumeroResponse.getId());
            return true;
        } catch (FeignException exception) {
            System.out.println(exception.status());
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
