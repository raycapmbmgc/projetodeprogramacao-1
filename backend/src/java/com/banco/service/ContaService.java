package com.banco.service;

import com.banco.model.Conta;
import com.banco.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository repo;

    public ContaService(ContaRepository repo) {
        this.repo = repo;
    }

    public Conta criar(Conta conta) {
        return repo.save(conta);
    }

    public List<Conta> listar() {
        return repo.findAll();
    }

    public Conta buscar(int numero) {
        return repo.findByNumero(numero);
    }

    public void depositar(int numero, double valor) {
        Conta c = buscar(numero);
        c.depositar(valor);
        repo.save(c);
    }

    public boolean sacar(int numero, double valor) {
        Conta c = buscar(numero);
        boolean ok = c.sacar(valor);
        repo.save(c);
        return ok;
    }

    public boolean transferir(int origem, int destino, double valor) {
        Conta o = buscar(origem);
        Conta d = buscar(destino);

        if (o != null && d != null && o.getSaldo() >= valor) {
            o.sacar(valor);
            d.depositar(valor);
            repo.save(o);
            repo.save(d);
            return true;
        }
        return false;
    }

    public double calcularTributo() {
        return repo.findAll().stream()
                .filter(c -> c instanceof com.banco.model.Tributavel)
                .mapToDouble(c -> ((com.banco.model.Tributavel) c).calcularTributo())
                .sum();
    }
}