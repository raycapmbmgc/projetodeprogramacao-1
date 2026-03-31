package com.banco.repository;

import com.banco.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Conta findByNumero(int numero);
}