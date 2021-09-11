package com.zupacademy.italo.propostas.cartoes.bloqueios;

import com.zupacademy.italo.propostas.cartoes.Cartao;
import com.zupacademy.italo.propostas.cartoes.CartaoRepository;
import com.zupacademy.italo.propostas.cartoes.EstadoCartao;
import com.zupacademy.italo.propostas.outrossistemas.cartao.CartaoLegadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/cartoes")
public class NovoBloqueioController {
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private CartaoLegadoService cartaoLegadoService;

    @PostMapping(value = "/{numeroCartao}/bloqueios")
    @Transactional
    public ResponseEntity<?> cadastrarNovoBloqueio(@PathVariable("numeroCartao") @Valid @NotBlank String numeroCartao,
                                                   @RequestHeader("X-Forward-For") String ipCliente,
                                                   @RequestHeader("User-Agent") String userAgentCliente) {
        Cartao cartao = cartaoRepository.findByNumero(numeroCartao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (cartao.getEstado().equals(EstadoCartao.BLOQUEADO))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já está bloqueado.");

        if (cartao.getEstado().equals(EstadoCartao.AGUARDANDO_BLOQUEIO))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe um bloqueio do cartão em processamento.");

        cartao.configuraBloqueio(ipCliente, userAgentCliente);
        cartaoRepository.save(cartao);
        cartaoLegadoService.notificaBloqueios();

        return ResponseEntity.ok(cartao);
    }
}
