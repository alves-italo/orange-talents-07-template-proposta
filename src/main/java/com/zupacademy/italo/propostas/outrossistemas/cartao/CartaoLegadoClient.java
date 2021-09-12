package com.zupacademy.italo.propostas.outrossistemas.cartao;

import com.zupacademy.italo.propostas.outrossistemas.analise.AnalisePropostaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cartaoLegadoClient", url = "${servicos.cartao.host}")
public interface CartaoLegadoClient {
    @GetMapping(value = "/cartoes")
    CartaoResponse consultaNumero(@RequestBody AnalisePropostaRequest request);

    @GetMapping(value = "/cartoes/{numeroCartao}")
    CartaoResponse consultaNumero(@PathVariable("numeroCartao") String numeroCartao);

    @PostMapping(value = "/cartoes/{numeroCartao}/bloqueios")
    RespostaApi notificaBloqueio(@PathVariable("numeroCartao") String numeroCartao, @RequestBody BloqueioLegadoRequest request);

    @PostMapping(value = "/cartoes/{numeroCartao}/avisos")
    RespostaApi notificaAvisoViagem(@PathVariable("numeroCartao") String numeroCartao, @RequestBody AvisoLegadoRequest request);

    @PostMapping(value = "/cartoes/{numeroCartao}/carteiras")
    RespostaApi associaCarteira(@PathVariable("numeroCartao") String numeroCartao, @RequestBody CarteiraLegadoRequest carteiraLegadoRequest);
}
