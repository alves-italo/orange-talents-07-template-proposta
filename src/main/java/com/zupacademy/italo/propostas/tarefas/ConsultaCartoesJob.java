package com.zupacademy.italo.propostas.tarefas;

import com.zupacademy.italo.propostas.cadastroproposta.PropostaRepository;
import com.zupacademy.italo.propostas.cadastroproposta.StatusProposta;
import com.zupacademy.italo.propostas.outrossistemas.analise.AnalisePropostaRequest;
import com.zupacademy.italo.propostas.outrossistemas.cartao.CartaoNumeroResponse;
import com.zupacademy.italo.propostas.outrossistemas.cartao.ConsultaCartaoClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ConsultaCartoesJob {
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private ConsultaCartaoClient consultaCartaoClient;

    @Scheduled(fixedDelay = 5000)
    public void consultaCartoes() {
        propostaRepository.findAllByStatus(StatusProposta.ELEGIVEL).forEach(proposta -> {
            try {
                CartaoNumeroResponse cartaoNumeroResponse = consultaCartaoClient.consultaNumero(new AnalisePropostaRequest(proposta));
                proposta.associaCartao(cartaoNumeroResponse.getId());
                propostaRepository.save(proposta);
            } catch (FeignException exception) {
                // Logger
            }
        });
    }
}
