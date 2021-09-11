package com.zupacademy.italo.propostas.propostas.associacartao;

import com.zupacademy.italo.propostas.propostas.PropostaRepository;
import com.zupacademy.italo.propostas.propostas.StatusProposta;
import com.zupacademy.italo.propostas.outrossistemas.analise.AnalisePropostaRequest;
import com.zupacademy.italo.propostas.outrossistemas.cartao.CartaoResponse;
import com.zupacademy.italo.propostas.outrossistemas.cartao.ConsultaCartaoClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@EnableScheduling
public class ConsultaCartoesJob {
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private ConsultaCartaoClient consultaCartaoClient;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void consultaCartoes() {
        propostaRepository.findAllByStatus(StatusProposta.ELEGIVEL).forEach(proposta -> {
            try {
                CartaoResponse cartaoResponse = consultaCartaoClient.consultaNumero(new AnalisePropostaRequest(proposta));
                proposta.associaCartao(cartaoResponse.toModel());
                propostaRepository.save(proposta);
            } catch (FeignException exception) {
                // Logger
            }
        });
    }
}
