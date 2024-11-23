package model;

import java.time.LocalDate;

public class Cliente extends Usuario {
    private String senha;
    private int id_cliente;
    private int usuario_id;
    private Endereco endereco;

    public Cliente(int id_usuario, String nome, String cpf, LocalDate data_nascimento, String telefone, Endereco endereco, String senha, int usuario_id) {
        super(id_usuario, nome, cpf, data_nascimento, telefone, endereco);
        this.senha = senha;
        this.usuario_id = usuario_id;
    }

    public Cliente() {
        super();
    }


    @Override
    public boolean login(String cpf, String senha){
        if(this.senha.equals(senha)){
            return true;
        }
        return false;
    }

    //Métodos


    @Override
    public Endereco getEndereco() {
        return endereco;
    }

    @Override
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

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
