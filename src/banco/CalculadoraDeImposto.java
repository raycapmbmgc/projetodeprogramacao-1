package banco;

import contas.Tributavel;

public class CalculadoraDeImposto {
    private double total = 0;

    public void registrar(Tributavel t) {
        double valor = t.calcularTributo();
        total += valor;
        System.out.println("💰 Tributo registrado: R$ " + valor);
    }

    public double getTotal() {
        return total;
    }
}