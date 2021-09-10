package com.zupacademy.italo.propostas.cadastroproposta;

import com.zupacademy.italo.propostas.outrossistemas.analise.AnalisePropostaClient;
import com.zupacademy.italo.propostas.outrossistemas.analise.AnalisePropostaRequest;
import com.zupacademy.italo.propostas.outrossistemas.analise.ResultadoSolicitacao;
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
    private AnalisePropostaClient analisePropostaClient;

    @Transactional
    public void processaPropostas() {
        propostaRepository.findAllByStatus(StatusProposta.AGUARDANDO_AVALIACAO).forEach(proposta -> {
            try {
                AnalisePropostaRequest request = new AnalisePropostaRequest(proposta.getDocumento(), proposta.getNome(), proposta.getId().toString());
                proposta.processaAnalise(analisePropostaClient.solicitaAnalise(request).getResultadoSolicitacao());
                propostaRepository.save(proposta);
            } catch (FeignException exception) {
                if (exception.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                    proposta.processaAnalise(ResultadoSolicitacao.COM_RESTRICAO);
                }
            }
        });
    }
}
