package model;

import java.time.LocalDate;

abstract class Usuario {
    protected int id_usuario;
    protected String nome;
    protected String cpf;
    protected LocalDate data_nascimento;
    protected String telefone;
    protected Endereco endereco;

    // Construtor
    public Usuario(int id_usuario, String nome, String cpf, LocalDate data_nascimento, String telefone, Endereco endereco){
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
        this.endereco = endereco;
    }



    //Métodos Getters and Setters
    public int getId() {
        return id_usuario;
    }

    public void setId(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    //Métodos comuns e abstratos
    abstract boolean login(String senha);

    public void logout(){
        //implementação
        System.out.println("Usuário " + nome + " foi desconectado");
    }

    public String consultarDados(){
        String s = "ID: " + id_usuario + "\n" +
                "Nome: " + nome + "\n" +
                "CPF: " + cpf + "\n" +
                " Data de Nascimento: " + data_nascimento + "\n" +
                " Telefone: " + telefone + "\n" +
                " Endereço: " + endereco;
        return s;
    }
}
