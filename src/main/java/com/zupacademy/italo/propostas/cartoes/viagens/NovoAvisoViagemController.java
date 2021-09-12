package com.zupacademy.italo.propostas.cartoes.viagens;

import com.zupacademy.italo.propostas.cartoes.Cartao;
import com.zupacademy.italo.propostas.cartoes.CartaoRepository;
import com.zupacademy.italo.propostas.outrossistemas.cartao.AvisoLegadoRequest;
import com.zupacademy.italo.propostas.outrossistemas.cartao.CartaoLegadoClient;
import com.zupacademy.italo.propostas.outrossistemas.cartao.RespostaApi;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/cartoes")
public class NovoAvisoViagemController {
    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoLegadoClient cartaoLegadoClient;

    @PostMapping(value = "/{numeroCartao}/avisosviagem")
    @Transactional
    public ResponseEntity<?> cadastrarAvisoViagem(@PathVariable("numeroCartao") String numeroCartao,
                                                  @RequestBody @Valid NovoAvisoViagemRequest request,
                                                  @RequestHeader("X-Forward-For") String ipCliente,
                                                  @RequestHeader("User-Agent") String userAgentCliente) {
        Cartao cartao = cartaoRepository.findByNumero(numeroCartao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        AvisoViagem avisoViagem = request.toModel(ipCliente, userAgentCliente);

        try {
            RespostaApi response = cartaoLegadoClient.notificaAvisoViagem(cartao.getNumero(), new AvisoLegadoRequest(avisoViagem.getDestino(), avisoViagem.getValidoAte()));
            cartao.adicionaAvisoViagem(avisoViagem);
            cartaoRepository.save(cartao);
        } catch (FeignException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok().build();
    }
}
