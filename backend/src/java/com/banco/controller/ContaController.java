package com.banco.controller;

import com.banco.dto.TransferenciaDTO;
import com.banco.model.Conta;
import com.banco.service.ContaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/contas")
public class ContaController {

    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    @PostMapping
    public Conta criar(@RequestBody Conta conta) {
        return service.criar(conta);
    }

    @GetMapping
    public List<Conta> listar() {
        return service.listar();
    }

    @PostMapping("/depositar")
    public String depositar(@RequestBody Conta c) {
        service.depositar(c.getNumero(), c.getSaldo());
        return "Depósito realizado!";
    }

    @PostMapping("/sacar")
    public String sacar(@RequestBody Conta c) {
        return service.sacar(c.getNumero(), c.getSaldo())
                ? "Saque realizado"
                : "Saldo insuficiente";
    }

    @PostMapping("/transferir")
    public String transferir(@RequestBody TransferenciaDTO dto) {
        return service.transferir(dto.origem, dto.destino, dto.valor)
                ? "Transferência OK"
                : "Erro na transferência";
    }

    @GetMapping("/tributos")
    public double tributo() {
        return service.calcularTributo();
    }
}