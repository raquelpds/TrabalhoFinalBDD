package model;

import java.time.LocalDate;

public class Cliente extends Usuario {
    private String senha;

    public Cliente(int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco, String senha) {
        super(id, nome, cpf, dataNascimento, telefone, endereco);
        this.senha = senha;
    }

    @Override
    public boolean login(String senha){
        if(this.senha.equals(senha)){
            return true;
        }
        return false;
    }

    //Métodos
    public double consultarSaldo(){
        return 0;
    }

    public void depositar(double valor){
    }

    public boolean sacar(double valor){
        return false;
    }

    public String consultarExtrato(){
        return null;
    }

    public double consultarLimite(){
        return 0;
    }







}
