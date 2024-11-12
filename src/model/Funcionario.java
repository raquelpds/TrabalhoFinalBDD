package model;

public class Funcionario extends Cliente {
    //atributos
    private String codigoFuncionario;
    private String cargo;
    private String senha;

    //Construtor
    public Funcionario (String codigoFuncionario, String cargo, String senha){
        this.codigoFuncionario = codigoFuncionario;
        this.cargo = cargo;
        this.senha = senha;
    }

    //métodos
    void abrirConta( Conta conta){

    }


    @Override
    boolean login(String senha) {
        return false;
    }

    @Override
    void logout() {

    }

    @Override
    String consultarDados() {
        return "";
    }
}
