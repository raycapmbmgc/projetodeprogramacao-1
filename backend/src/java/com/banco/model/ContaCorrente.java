package com.banco.model;

import jakarta.persistence.Entity;

@Entity
public class ContaCorrente extends Conta implements Tributavel {

    @Override
    public double calcularTributo() {
        return getSaldo() * 0.01;
    }
}