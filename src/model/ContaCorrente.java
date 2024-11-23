package model;

import java.time.LocalDate;

public class ContaCorrente extends Conta {
    private double limite;
    private LocalDate dataVencimento;

    //construtor
    public ContaCorrente(String agencia, Cliente cliente, int numero, double saldo, double limite, LocalDate dataVencimento) {
        super(agencia, cliente, numero, saldo);
        this.limite = limite;
        this.dataVencimento = dataVencimento;
    }

    //métodos
    public double consultarLimite(){
        return this.limite;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public int getNumeroConta() {
        return getNumero(); // Retorna o número da conta
    }
}

