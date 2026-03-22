package banco;

import contas.Conta;
import java.util.ArrayList;

public class Banco {
    private ArrayList<Conta> contas = new ArrayList<>();

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public Conta buscarConta(int numero) {
        for (Conta c : contas) {
            if (c.getNumero() == numero) return c;
        }
        return null;
    }

    public boolean removerConta(int numero) {
        Conta c = buscarConta(numero);
        if (c != null) {
            contas.remove(c);
            return true;
        }
        return false;
    }
}