package contas;

import java.util.ArrayList;

public abstract class Conta {

    protected String titular;
    protected double saldo;

    protected ArrayList<String> historico = new ArrayList<>();

    public Conta(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;

        historico.add("Conta criada com saldo inicial: R$ " + saldoInicial);
    }

    public void depositar(double valor) {

        if (valor <= 0) {
            System.out.println("Valor inválido.");
            return;
        }

        saldo += valor;
        historico.add("Depósito de R$ " + valor);
    }

    public boolean sacar(double valor) {

        if (valor > saldo) {
            System.out.println("Saldo insuficiente.");
            historico.add("Tentativa de saque sem saldo.");
            return false;
        }

        saldo -= valor;
        historico.add("Saque de R$ " + valor);

        return true;
    }

    public boolean transferir(Conta destino, double valor) {

        if (this.sacar(valor)) {

            destino.depositar(valor);

            historico.add("Transferência enviada de R$ " + valor + " para " + destino.titular);
            destino.historico.add("Transferência recebida de R$ " + valor + " de " + titular);

            return true;
        }

        return false;
    }

    public void mostrarHistorico() {

        System.out.println("\nHistórico da conta:");

        for (String op : historico) {
            System.out.println("- " + op);
        }
    }

    public void exibirDados() {

        System.out.println("Titular: " + titular);
        System.out.println("Saldo: R$ " + saldo);
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public abstract void calcularRendimento();
}