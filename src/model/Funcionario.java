package model;

import java.time.LocalDate;

public class Funcionario extends Usuario {
    //atributos
    private String codigoFuncionario;
    private String cargo;
    private String senha;
    private  int id_usuario; //relacionado a tabela usuario

    //Construtor
    public Funcionario (int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco, String senha, String codigoFuncionario, String cargo){
        super(id, nome, cpf, dataNascimento, telefone, endereco); //chama o construtor de usuário
        this.codigoFuncionario = codigoFuncionario;
        this.cargo = cargo;
        this.senha = senha;
    }

    @Override
    public boolean login(String senha){
        //validação específica para funcionário
        if(this.senha.equals(senha) && this.codigoFuncionario != null){
            return true;
        }
        return false;
    }

    //métodos
    void abrirConta( Conta conta){
        //implementação
    }

    void encerrarConta( Conta conta){
        //implementação
    }

    public Conta consultarDadosConta(int numeroConta){
        //implementação
        return null;
    }

    public Cliente consultarDadosCliente(int idCliente){
        //implementação
        return null;
    }

    public void alterarDadosConta(Conta conta){
        //implementação
    }

    public void alterarDadosCliente(Cliente cliente){
        //implementação
    }

    public void cadastrarFuncionario(Funcionario funcionario){

    }

    public void gerarRelatorioMovimentacao(){
        //implementação
    }

    //METODOS GETTRS AND SETTERS

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(String codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
