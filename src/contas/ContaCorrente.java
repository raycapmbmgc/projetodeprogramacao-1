package contas;

public class ContaCorrente extends Conta {

    private double taxaManutencao = 20;

    public ContaCorrente(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    @Override
    public void calcularRendimento() {

        saldo -= taxaManutencao;

        System.out.println("Taxa de manutenção descontada: R$ " + taxaManutencao);

        historico.add("Taxa de manutenção: R$ " + taxaManutencao);
    }
}