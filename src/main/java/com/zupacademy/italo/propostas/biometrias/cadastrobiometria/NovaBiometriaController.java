package com.zupacademy.italo.propostas.biometrias.cadastrobiometria;

import com.zupacademy.italo.propostas.biometrias.Biometria;
import com.zupacademy.italo.propostas.biometrias.BiometriaRepository;
import com.zupacademy.italo.propostas.cartoes.VerificaCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class NovaBiometriaController {
    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private VerificaCartaoService verificaCartaoService;

    @PostMapping("/cartoes/{numeroCartao}/biometrias")
    public ResponseEntity<?> cadastraBiometria(@PathVariable("numeroCartao") String numeroCartao, @RequestBody @Valid NovaBiometriaRequest request, UriComponentsBuilder uriBuilder) {
        if(!verificaCartaoService.existe(numeroCartao)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Biometria biometria = request.toModel(numeroCartao);
        biometriaRepository.save(biometria);

        URI location = uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
