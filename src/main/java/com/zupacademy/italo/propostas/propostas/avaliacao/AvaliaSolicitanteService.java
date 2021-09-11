package com.zupacademy.italo.propostas.propostas.avaliacao;

import com.zupacademy.italo.propostas.outrossistemas.analise.AnaliseSolicitacaoClient;
import com.zupacademy.italo.propostas.outrossistemas.analise.AnalisePropostaRequest;
import com.zupacademy.italo.propostas.outrossistemas.analise.ResultadoSolicitacao;
import com.zupacademy.italo.propostas.propostas.PropostaRepository;
import com.zupacademy.italo.propostas.propostas.StatusProposta;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvaliaSolicitanteService {
    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AnaliseSolicitacaoClient analiseSolicitacaoClient;

    @Transactional
    public void processaPropostas() {
        propostaRepository.findAllByStatus(StatusProposta.AGUARDANDO_AVALIACAO).forEach(proposta -> {
            try {
                AnalisePropostaRequest request = new AnalisePropostaRequest(proposta);
                proposta.processaAnalise(analiseSolicitacaoClient.solicitaAnalise(request).getResultadoSolicitacao());
            } catch (FeignException exception) {
                if (exception.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                    proposta.processaAnalise(ResultadoSolicitacao.COM_RESTRICAO);
                }
            }

            propostaRepository.save(proposta);
        });
    }
}
