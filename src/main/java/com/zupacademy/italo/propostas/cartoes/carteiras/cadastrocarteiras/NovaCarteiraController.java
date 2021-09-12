package com.zupacademy.italo.propostas.cartoes.carteiras.cadastrocarteiras;

import com.zupacademy.italo.propostas.cartoes.Cartao;
import com.zupacademy.italo.propostas.cartoes.CartaoRepository;
import com.zupacademy.italo.propostas.cartoes.carteiras.Carteira;
import com.zupacademy.italo.propostas.outrossistemas.cartao.CartaoLegadoClient;
import com.zupacademy.italo.propostas.outrossistemas.cartao.CarteiraLegadoRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cartoes")
public class NovaCarteiraController {
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private CartaoLegadoClient cartaoLegadoClient;

    @PostMapping(value = "/{numeroCartao}/carteiras")
    @Transactional
    public ResponseEntity<?> cadastrarNovaCarteira(@PathVariable("numeroCartao") String numeroCartao, @RequestBody NovaCarteiraRequest request, UriComponentsBuilder uriBuilder) {
        Cartao cartao = cartaoRepository.findByNumero(numeroCartao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Carteira carteira = request.toModel();

        if (cartao.possuiCarteira(carteira)) throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);

        try {
            CarteiraLegadoRequest carteiraLegadoRequest = new CarteiraLegadoRequest(carteira);
            cartaoLegadoClient.associaCarteira(cartao.getNumero(), carteiraLegadoRequest);
            cartao.associaCarteira(carteira);
            cartaoRepository.save(cartao);
        } catch (FeignException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Map<String, Object> pathVariableMap = new HashMap<>();
        pathVariableMap.put("numeroCartao", numeroCartao);
        pathVariableMap.put("id", carteira.getId());
        URI location = uriBuilder.path("/cartoes/{numeroCartao}/carteiras/{id}")
                .buildAndExpand(pathVariableMap)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
