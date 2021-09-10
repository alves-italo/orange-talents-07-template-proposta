package com.zupacademy.italo.propostas.outrossistemas.cartao;

import com.zupacademy.italo.propostas.outrossistemas.analise.AnalisePropostaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cartao-resource", url = "localhost:8888/api")
public interface ConsultaCartaoClient {
    @RequestMapping(method = RequestMethod.POST, value = "/cartoes", produces = "application/json")
    CartaoNumeroResponse consultaNumero(@RequestBody AnalisePropostaRequest request);
}
