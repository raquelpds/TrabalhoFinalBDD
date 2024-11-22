package model;

import java.time.LocalDate;

public class Cliente extends Usuario {
    private String senha;
    private int usuario_id;

    public Cliente(int id_usuario, String nome, String cpf, LocalDate data_nascimento, String telefone, Endereco endereco, String senha, int usuario_id) {
        super(id_usuario, nome, cpf, data_nascimento, telefone, endereco);
        this.senha = senha;
        this.usuario_id = usuario_id;
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
