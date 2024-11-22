package model;

import java.time.LocalDate;

public class Funcionario extends Usuario {
    //atributos
    private int id_funcionario;
    private String codigo_funcionario;
    private String cargo;
    private String senha;
    private int usuario_id;
    private int endereco_id;//relacionado a tabela endereco

    //Construtor
    public Funcionario (int id_usuario, String nome, String cpf, LocalDate data_nascimento, String telefone, Endereco endereco, String senha, String codigo_funcionario, String cargo, int usuario_id, int endereco_id){
        super(id_usuario, nome, cpf, data_nascimento, telefone, endereco); //chama o construtor de usuário
        this.codigo_funcionario = codigo_funcionario;
        this.cargo = cargo;
        this.senha = senha;
        this.usuario_id = usuario_id;
        this.endereco_id = endereco_id;
    }



    @Override
    public boolean login(String senha){
        //validação específica para funcionário
        if(this.senha.equals(senha) && this.codigo_funcionario != null){
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

    public String getCodigo_funcionario() {
        return codigo_funcionario;
    }

    public void setCodigo_funcionario(String codigo_funcionario) {
        this.codigo_funcionario = codigo_funcionario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId_usuario() {
        return usuario_id;
    }

    public void setId_usuario(int id_usuario) {
        this.usuario_id = id_usuario;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(int endereco_id) {
        this.endereco_id = endereco_id;
    }
}
