package contas;

import java.util.ArrayList;

public abstract class Conta {
    private int numero;
    private double saldo;
    private ArrayList<String> historico = new ArrayList<>();

    public Conta(int numero) {
        this.numero = numero;
        this.saldo = 0;
        historico.add("Conta criada com saldo inicial: R$ 0.0");
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<String> getHistorico() {
        return historico;
    }

    public void depositar(double valor) {
        saldo += valor;
        historico.add("Depósito: R$ " + valor + " | Saldo: R$ " + saldo);
    }

    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            historico.add("Saque: R$ " + valor + " | Saldo: R$ " + saldo);
            return true;
        }
        historico.add("Saque negado (saldo insuficiente): R$ " + valor);
        return false;
    }
}