package com.zupacademy.italo.propostas.propostas.cadastropropostas;

import com.zupacademy.italo.propostas.propostas.Proposta;
import com.zupacademy.italo.propostas.propostas.PropostaRepository;
import com.zupacademy.italo.propostas.propostas.analises.AnaliseSolicitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    private AnaliseSolicitanteService analiseSolicitanteService;

    @PostMapping
    public ResponseEntity<?> cadastrarProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uriBuilder) {
        if (propostaRepository.existsByDocumento(request.getDocumento()))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);

        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);

        analiseSolicitanteService.processaPropostas();

        URI location = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
