package model;

public class ContaPoupanca extends Conta {
    private double taxaRendimento;

    //construtor
    public ContaPoupanca(String agencia, Cliente cliente, int numero, double saldo, double taxaRendimento) {
        super(agencia, cliente, numero, saldo);
        this.taxaRendimento = taxaRendimento;
    }

    //métodos
    public double calcularRendimento(){
        double rendimento = this.saldo * this.taxaRendimento;
        return rendimento;
    }
}
