import banco.Banco;
import contas.*;
import usuarios.*;
import banco.CalculadoraDeImposto;
import java.util.Scanner;

public class Main {

    static Banco banco = new Banco();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Gerente gerente = new Gerente("admin", "1234");
        int opcao;

        do {
            System.out.println("\n===================================");
            System.out.println("===== BANCO JAVA - MENU PRINCIPAL =====");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Listar contas");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Transferir");
            System.out.println("6 - Consultar saldo");
            System.out.println("7 - Calcular tributo");
            System.out.println("8 - Autenticar gerente");
            System.out.println("9 - Excluir conta");
            System.out.println("10 - Histórico de operações");
            System.out.println("0 - Sair");
            System.out.println("===================================");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> criarConta();
                case 2 -> listarContas();
                case 3 -> depositar();
                case 4 -> sacar();
                case 5 -> transferir();
                case 6 -> consultarSaldo();
                case 7 -> calcularTributo();
                case 8 -> autenticarGerente(gerente);
                case 9 -> excluirConta();
                case 10 -> mostrarHistorico();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);
    }

    static void criarConta() {
        System.out.println("1 - Corrente | 2 - Poupança");
        int tipo = sc.nextInt();
        System.out.print("Número da conta: ");
        int numero = sc.nextInt();

        if (banco.buscarConta(numero) != null) {
            System.out.println("❌ Conta com esse número já existe!");
            return;
        }

        if (tipo == 1) banco.adicionarConta(new ContaCorrente(numero));
        else banco.adicionarConta(new ContaPoupanca(numero));

        System.out.println("✅ Conta criada com sucesso! Número: " + numero);
    }

    static Conta selecionarConta() {
        System.out.print("Número da conta: ");
        int numero = sc.nextInt();
        Conta c = banco.buscarConta(numero);
        if (c == null) System.out.println("❌ Conta não encontrada!");
        return c;
    }

    static void listarContas() {
        System.out.println("\n--- LISTA DE CONTAS ---");
        if (banco.getContas().isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        for (Conta c : banco.getContas()) {
            System.out.println("Conta: " + c.getNumero() + " | Saldo: R$ " + c.getSaldo());
        }
    }

    static void depositar() {
        Conta c = selecionarConta();
        if (c != null) {
            System.out.print("Valor a depositar: ");
            double valor = sc.nextDouble();
            if (valor <= 0) System.out.println("❌ Valor inválido!");
            else {
                c.depositar(valor);
                System.out.println("✅ Depósito realizado com sucesso: R$ " + valor);
            }
        }
    }

    static void sacar() {
        Conta c = selecionarConta();
        if (c != null) {
            System.out.print("Valor a sacar: ");
            double valor = sc.nextDouble();
            if (valor <= 0) System.out.println("❌ Valor inválido!");
            else if (c.sacar(valor)) System.out.println("✅ Saque realizado: R$ " + valor);
            else System.out.println("❌ Saldo insuficiente! Operação não realizada.");
        }
    }

    static void transferir() {
        System.out.println("--- Conta de origem ---");
        Conta origem = selecionarConta();
        System.out.println("--- Conta de destino ---");
        Conta destino = selecionarConta();

        if (origem == null || destino == null) {
            System.out.println("❌ Transferência cancelada. Conta inexistente.");
            return;
        }

        System.out.print("Valor a transferir: ");
        double valor = sc.nextDouble();

        if (valor <= 0) {
            System.out.println("❌ Valor inválido! Transferência cancelada.");
            return;
        }

        if (origem.sacar(valor)) {
            destino.depositar(valor);
            System.out.println("✅ Transferência concluída! R$ " + valor + " de " + origem.getNumero() + " para " + destino.getNumero());
        } else {
            System.out.println("❌ Saldo insuficiente! Transferência não realizada.");
        }
    }

    static void consultarSaldo() {
        Conta c = selecionarConta();
        if (c != null) System.out.println("💰 Saldo atual: R$ " + c.getSaldo());
    }

    static void calcularTributo() {
        CalculadoraDeImposto calc = new CalculadoraDeImposto();
        for (Conta c : banco.getContas()) {
            if (c instanceof Tributavel) calc.registrar((Tributavel) c);
        }
        System.out.println("💵 Total de tributos calculado: R$ " + calc.getTotal());
    }

    static void autenticarGerente(Gerente gerente) {
        System.out.print("Digite a senha do gerente: ");
        String senha = sc.next();
        if (gerente.autenticar(senha)) System.out.println("✅ Acesso permitido!");
        else System.out.println("❌ Senha incorreta!");
    }

    static void excluirConta() {
        System.out.print("Número da conta a excluir: ");
        int numero = sc.nextInt();
        if (banco.removerConta(numero)) System.out.println("✅ Conta excluída com sucesso!");
        else System.out.println("❌ Conta não encontrada! Exclusão não realizada.");
    }

    static void mostrarHistorico() {
        Conta c = selecionarConta();
        if (c != null) {
            System.out.println("\n--- HISTÓRICO DE OPERAÇÕES ---");
            if (c.getHistorico().isEmpty()) System.out.println("Nenhuma operação realizada ainda.");
            for (String h : c.getHistorico()) System.out.println(h);
        }
    }
}