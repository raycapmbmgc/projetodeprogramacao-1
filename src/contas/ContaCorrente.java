package contas;

public class ContaCorrente extends Conta implements Tributavel {

    public ContaCorrente(int numero) {
        super(numero);
    }

    @Override
    public double calcularTributo() {
        double tributo = getSaldo() * 0.01; // 1% de tributo
        return tributo;
    }
}