package com.zupacademy.italo.propostas.cartoes.biometrias.detalhebiometrias;

import com.zupacademy.italo.propostas.cartoes.Cartao;
import com.zupacademy.italo.propostas.cartoes.biometrias.Biometria;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/cartoes")
public class DetalheBiometriaController {
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping(value = "/{numeroCartao}/biometrias/{id}")
    public BiometriaResponse mostrarBiometria(@PathVariable("numeroCartao") String numeroCartao, @PathVariable("id") Long id) {
        if (entityManager.createQuery("SELECT c FROM Cartao c WHERE c.numero = :numeroCartao", Cartao.class)
                .setParameter("numeroCartao", numeroCartao)
                .getResultList()
                .isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return new BiometriaResponse(entityManager.find(Biometria.class, id));
    }
}
