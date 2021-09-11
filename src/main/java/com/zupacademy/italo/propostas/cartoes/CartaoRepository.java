package com.zupacademy.italo.propostas.cartoes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findByNumero(String numero);
}
