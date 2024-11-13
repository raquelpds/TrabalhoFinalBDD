package model;

import java.time.LocalDate;

abstract class Usuario {
    protected int id;
    protected String nome;
    protected String cpf;
    protected LocalDate dataNascimento;
    protected String telefone;
    protected Endereco endereco;

    // Construtor
    public Usuario(int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
    }


    //Métodos Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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
        String s = "ID: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "CPF: " + cpf + "\n" +
                " Data de Nascimento: " + dataNascimento + "\n" +
                " Telefone: " + telefone + "\n" +
                " Endereço: " + endereco;
        return s;
    }
}
