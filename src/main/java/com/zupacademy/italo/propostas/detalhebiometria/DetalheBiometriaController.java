package com.zupacademy.italo.propostas.detalhebiometria;

import com.zupacademy.italo.propostas.cadastrobiometria.BiometriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/biometrias")
public class DetalheBiometriaController {
    @Autowired
    private BiometriaRepository biometriaRepository;

    @GetMapping(value = "{id}")
    public BiometriaResponse mostrarBiometria(@PathVariable Long id) {
        return biometriaRepository.findById(id).map(BiometriaResponse::new).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
