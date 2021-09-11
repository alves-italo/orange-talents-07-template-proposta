package com.zupacademy.italo.propostas.outrossistemas.cartao;

import com.zupacademy.italo.propostas.cartoes.CartaoRepository;
import com.zupacademy.italo.propostas.cartoes.EstadoCartao;
import com.zupacademy.italo.propostas.outrossistemas.analise.AnalisePropostaRequest;
import com.zupacademy.italo.propostas.propostas.PropostaRepository;
import com.zupacademy.italo.propostas.propostas.StatusProposta;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartaoLegadoService {
    private CartaoRepository cartaoRepository;
    private PropostaRepository propostaRepository;
    private ConsultaCartaoClient consultaCartaoClient;

    public CartaoLegadoService(CartaoRepository cartaoRepository, PropostaRepository propostaRepository, ConsultaCartaoClient consultaCartaoClient) {
        this.cartaoRepository = cartaoRepository;
        this.propostaRepository = propostaRepository;
        this.consultaCartaoClient = consultaCartaoClient;
    }

    @Transactional
    public void notificaBloqueios() {
        cartaoRepository.findAllByEstado(EstadoCartao.AGUARDANDO_BLOQUEIO).forEach(cartao -> {
            try {
                BloqueioResponse response = consultaCartaoClient.notificaBloqueio(cartao.getNumero(), new BloqueioRequest(cartao.getBloqueioAtivo().getUserAgentCliente()));
                cartao.confirmaBloqueio();
                cartaoRepository.save(cartao);
                // Logger do bloqueio
            } catch (FeignException exception) {
                // Logger com o erro de comunicação
            }
        });
    }

    @Transactional
    public void associaCartoes() {
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
