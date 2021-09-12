package com.zupacademy.italo.propostas.cartoes;

import com.zupacademy.italo.propostas.outrossistemas.cartao.CartaoLegadoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificaCartaoService {
    @Autowired
    CartaoLegadoClient cartaoLegadoClient;

}
