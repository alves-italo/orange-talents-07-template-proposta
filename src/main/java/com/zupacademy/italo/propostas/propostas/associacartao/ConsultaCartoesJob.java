package com.zupacademy.italo.propostas.propostas.associacartao;

import com.zupacademy.italo.propostas.outrossistemas.cartao.CartaoLegadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ConsultaCartoesJob {
    @Autowired
    private CartaoLegadoService cartaoLegadoService;

    @Scheduled(fixedDelay = 5000)
    public void consultaCartoes() {
        cartaoLegadoService.associaCartoes();
    }
}
