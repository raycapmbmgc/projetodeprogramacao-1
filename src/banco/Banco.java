package banco;

import contas.Conta;
import java.util.ArrayList;

public class Banco {

    private ArrayList<Conta> contas = new ArrayList<>();

    public void criarConta(Conta conta) {
        contas.add(conta);
    }

    public void listarContas() {

        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        for (Conta c : contas) {

            System.out.println("-------------------");
            c.exibirDados();

        }
    }

    public Conta buscarConta(String titular) {

        for (Conta c : contas) {

            if (c.getTitular().equalsIgnoreCase(titular)) {
                return c;
            }

        }

        return null;
    }

    public void removerConta(String titular) {

        Conta conta = buscarConta(titular);

        if (conta != null) {

            contas.remove(conta);
            System.out.println("Conta removida com sucesso.");

        } else {

            System.out.println("Conta não encontrada.");

        }
    }
}