package com.zupacademy.italo.propostas.cadastroproposta;

import com.zupacademy.italo.propostas.detalheproposta.PropostaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class NovaPropostaController {
    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AvaliaSolicitanteService avaliaSolicitanteService;

    @PostMapping
    public ResponseEntity<?> cadastrarProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uriBuilder) {
        if (propostaRepository.existsByDocumento(request.getDocumento()))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);

        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);

        avaliaSolicitanteService.processaPropostas();

        URI location = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
