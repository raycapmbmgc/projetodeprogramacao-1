package app;

import banco.Banco;
import contas.*;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Banco banco = new Banco();

    public static void main(String[] args) {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== BANCO JAVA =====");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Listar contas");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Transferir");
            System.out.println("6 - Consultar saldo");
            System.out.println("7 - Histórico");
            System.out.println("8 - Excluir conta");
            System.out.println("0 - Sair");

            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1 -> criarConta();
                case 2 -> banco.listarContas();
                case 3 -> depositar();
                case 4 -> sacar();
                case 5 -> transferir();
                case 6 -> consultarSaldo();
                case 7 -> mostrarHistorico();
                case 8 -> excluirConta();
                case 0 -> System.out.println("Sistema encerrado.");
                default -> System.out.println("Opção inválida.");

            }
        }
    }

    static void criarConta() {

        System.out.print("Titular: ");
        String titular = scanner.nextLine();

        System.out.println("1 - Corrente");
        System.out.println("2 - Poupança");

        int tipo = scanner.nextInt();
        scanner.nextLine();

        Conta conta;

        if (tipo == 1) {
            conta = new ContaCorrente(titular, 0);
        } else {
            conta = new ContaPoupanca(titular, 0);
        }

        banco.criarConta(conta);

        System.out.println("Conta criada com sucesso!");
    }

    static void depositar() {

        System.out.print("Titular: ");
        String nome = scanner.nextLine();

        Conta conta = banco.buscarConta(nome);

        if (conta != null) {

            System.out.print("Valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            conta.depositar(valor);

        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    static void sacar() {

        System.out.print("Titular: ");
        String nome = scanner.nextLine();

        Conta conta = banco.buscarConta(nome);

        if (conta != null) {

            System.out.print("Valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            conta.sacar(valor);

        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    static void transferir() {

        System.out.print("Conta origem: ");
        String origem = scanner.nextLine();

        System.out.print("Conta destino: ");
        String destino = scanner.nextLine();

        Conta contaOrigem = banco.buscarConta(origem);
        Conta contaDestino = banco.buscarConta(destino);

        if (contaOrigem != null && contaDestino != null) {

            System.out.print("Valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            contaOrigem.transferir(contaDestino, valor);

        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    static void consultarSaldo() {

        System.out.print("Titular: ");
        String nome = scanner.nextLine();

        Conta conta = banco.buscarConta(nome);

        if (conta != null) {

            System.out.println("Saldo: R$ " + conta.getSaldo());

        } else {

            System.out.println("Conta não encontrada.");
        }
    }

    static void mostrarHistorico() {

        System.out.print("Titular: ");
        String nome = scanner.nextLine();

        Conta conta = banco.buscarConta(nome);

        if (conta != null) {

            conta.mostrarHistorico();

        } else {

            System.out.println("Conta não encontrada.");
        }
    }

    static void excluirConta() {

        System.out.print("Titular: ");
        String nome = scanner.nextLine();

        banco.removerConta(nome);
    }
}