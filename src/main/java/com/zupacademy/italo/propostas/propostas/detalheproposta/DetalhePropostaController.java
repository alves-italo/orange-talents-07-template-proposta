package com.zupacademy.italo.propostas.propostas.detalheproposta;

import com.zupacademy.italo.propostas.propostas.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/propostas")
public class DetalhePropostaController {
    @Autowired
    private PropostaRepository propostaRepository;

    @GetMapping("/{id}")
    public PropostaResponse consultarProposta(@PathVariable("id") Long id) {
        return propostaRepository.findById(id).map(PropostaResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
