package com.zupacademy.italo.propostas.propostas.detalhepropostas;

import com.zupacademy.italo.propostas.propostas.Proposta;
import com.zupacademy.italo.propostas.propostas.PropostaRepository;
import com.zupacademy.italo.propostas.propostas.StatusProposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class DetalhePropostaController {
    @Autowired
    private PropostaRepository propostaRepository;

    @GetMapping("/{id}")
    public PropostaResponse consultarProposta(@PathVariable("id") Long id) {
        Optional<Proposta> propostaOptional = propostaRepository.findById(id);

        if (propostaOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Proposta proposta = propostaOptional.get();
        PropostaResponse propostaResponse = new PropostaResponse(proposta);

        if (proposta.getStatus().equals(StatusProposta.CARTAO_GERADO))
            propostaResponse.setNumeroCartao(proposta.getNumeroCartao());

        return propostaResponse;
    }
}
