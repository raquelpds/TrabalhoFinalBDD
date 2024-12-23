package model;

public abstract class Conta {
    private int numero;
    private String agencia;
    protected double saldo;
    private Cliente cliente;
    private String tipo_conta;

    //construtor
    public Conta(String agencia, Cliente cliente, int numero, double saldo) {
        this.agencia = agencia;
        this.cliente = cliente;
        this.numero = numero;
        this.saldo = saldo;
    }


    //métodos getters e setters
    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    //métodos
    public void depositar(double valor){
        this.saldo += valor;
    }

    public boolean sacar(double valor) {
        return false;
    }

    public double consultarSaldo(){
        return this.saldo;
    }

    public int getNumeroConta() {
        return 0;
    }
}
