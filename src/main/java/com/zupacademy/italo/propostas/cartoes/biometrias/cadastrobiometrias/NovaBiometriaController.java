package com.zupacademy.italo.propostas.cartoes.biometrias.cadastrobiometrias;

import com.zupacademy.italo.propostas.cartoes.Cartao;
import com.zupacademy.italo.propostas.cartoes.CartaoRepository;
import com.zupacademy.italo.propostas.cartoes.biometrias.Biometria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class NovaBiometriaController {
    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/cartoes/{numeroCartao}/biometrias")
    @Transactional
    public ResponseEntity<?> cadastraBiometria(@PathVariable("numeroCartao") String numeroCartao, @RequestBody @Valid NovaBiometriaRequest request, UriComponentsBuilder uriBuilder) {
        Cartao cartao = cartaoRepository.findByNumero(numeroCartao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Biometria biometria = request.toModel();
        cartao.associaBiometria(biometria);
        cartaoRepository.save(cartao);

        Map<String, Object> pathVariableMap = new HashMap<>();
        pathVariableMap.put("numeroCartao", numeroCartao);
        pathVariableMap.put("id", biometria.getId());
        URI location = uriBuilder.path("/cartoes/{numeroCartao}/biometrias/{id}")
                .buildAndExpand(pathVariableMap)
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
