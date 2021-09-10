package com.zupacademy.italo.propostas.outrossistemas.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "solicitacao-analise", url = "localhost:9999/api")
public interface AnaliseSolicitacaoClient {
    @RequestMapping(method = RequestMethod.POST, value = "/solicitacao", produces = "application/json")
    AnaliseSolicitacaoResponse solicitaAnalise(@RequestBody AnalisePropostaRequest request);
}
