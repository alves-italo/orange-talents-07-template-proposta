package com.zupacademy.italo.propostas.cartoes.bloqueios;

import com.zupacademy.italo.propostas.cartoes.Cartao;
import com.zupacademy.italo.propostas.cartoes.CartaoRepository;
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

    @PostMapping(value = "/{numeroCartao}/bloqueios")
    @Transactional
    public ResponseEntity<?> cadastrarNovoBloqueio(@PathVariable("numeroCartao") @Valid @NotBlank String numeroCartao,
                                                   @RequestHeader("X-Forward-For") String ipCliente,
                                                   @RequestHeader("User-Agent") String userAgentCliente) {
        Cartao cartao = cartaoRepository.findByNumero(numeroCartao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!cartao.ativo())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já está bloqueado.");

        cartao.configuraBloqueio(ipCliente, userAgentCliente);
        cartaoRepository.save(cartao);

        return ResponseEntity.ok(cartao);
    }
}
