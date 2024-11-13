package model;

import java.time.LocalDate;

public class Funcionario extends Usuario {
    //atributos
    private String codigoFuncionario;
    private String cargo;
    private String senha;

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
}
