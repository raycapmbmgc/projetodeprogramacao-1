package contas;

public class ContaPoupanca extends Conta {

    private double taxaRendimento = 0.05;

    public ContaPoupanca(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    @Override
    public void calcularRendimento() {

        double rendimento = saldo * taxaRendimento;

        saldo += rendimento;

        System.out.println("Rendimento aplicado: R$ " + rendimento);

        historico.add("Rendimento aplicado: R$ " + rendimento);
    }
}