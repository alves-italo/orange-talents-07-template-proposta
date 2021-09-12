package com.zupacademy.italo.propostas.outrossistemas.cartao;

import com.zupacademy.italo.propostas.outrossistemas.analise.AnalisePropostaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cartao-resource", url = "${servicos.cartao.host}")
public interface CartaoLegadoClient {
    @RequestMapping(method = RequestMethod.POST, value = "/cartoes", produces = "application/json")
    CartaoResponse consultaNumero(@RequestBody AnalisePropostaRequest request);

    @RequestMapping(method = RequestMethod.GET, value = "/cartoes/{numeroCartao}", produces = "application/json")
    CartaoResponse consultaNumero(@PathVariable("numeroCartao") String numeroCartao);

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{numeroCartao}/bloqueios", produces = "application/json")
    RespostaApi notificaBloqueio(@PathVariable("numeroCartao") String numeroCartao, @RequestBody BloqueioLegadoRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{numeroCartao}/avisos", produces = "application/json")
    RespostaApi notificaAvisoViagem(@PathVariable("numeroCartao") String numeroCartao, @RequestBody AvisoLegadoRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{numeroCartao}/carteiras", produces = "application/json")
    RespostaApi associaCarteira(@PathVariable("numeroCartao") String numeroCartao, @RequestBody CarteiraLegadoRequest carteiraLegadoRequest);
}
