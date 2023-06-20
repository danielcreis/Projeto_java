package main.java.app.model;

public class ContaPoupanca extends Conta {
    private double taxaJuros = 0.005;

    public ContaPoupanca(int numero, String titular, double saldo, double taxaJuros) {
        super(numero, titular, saldo);
        this.taxaJuros = taxaJuros;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public void atualizarJuros() {
        if (getSaldo() > 0) {
            double juros = getSaldo() * taxaJuros;
            depositar(juros);
        }
    }
}
