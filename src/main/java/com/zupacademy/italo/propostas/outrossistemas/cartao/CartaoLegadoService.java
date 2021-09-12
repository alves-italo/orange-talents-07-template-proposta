package com.zupacademy.italo.propostas.outrossistemas.cartao;

import com.zupacademy.italo.propostas.cartoes.CartaoRepository;
import com.zupacademy.italo.propostas.cartoes.EstadoCartao;
import com.zupacademy.italo.propostas.outrossistemas.analise.AnalisePropostaRequest;
import com.zupacademy.italo.propostas.propostas.PropostaRepository;
import com.zupacademy.italo.propostas.propostas.StatusProposta;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartaoLegadoService {
    private final CartaoRepository cartaoRepository;
    private final PropostaRepository propostaRepository;
    private final CartaoLegadoClient cartaoLegadoClient;

    public CartaoLegadoService(CartaoRepository cartaoRepository, PropostaRepository propostaRepository, CartaoLegadoClient cartaoLegadoClient) {
        this.cartaoRepository = cartaoRepository;
        this.propostaRepository = propostaRepository;
        this.cartaoLegadoClient = cartaoLegadoClient;
    }

    @Transactional
    public void notificaBloqueios() {
        cartaoRepository.findAllByEstado(EstadoCartao.AGUARDANDO_BLOQUEIO).forEach(cartao -> {
            try {
                RespostaApi response = cartaoLegadoClient.notificaBloqueio(cartao.getNumero(), new BloqueioLegadoRequest(cartao.getBloqueioAtivo().getUserAgentCliente()));
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
                CartaoResponse cartaoResponse = cartaoLegadoClient.consultaNumero(new AnalisePropostaRequest(proposta));
                proposta.associaCartao(cartaoResponse.toModel());
                propostaRepository.save(proposta);
            } catch (FeignException exception) {
                // Logger
            }
        });
    }
}
