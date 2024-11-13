package model;

public class Endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private String local;
    private String cep;
    private int numeroCasa;

    public Endereco(String estado, String cidade, String bairro, String local, String cep, int numeroCasa) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.local = local;
        this.cep = cep;
        this.numeroCasa = numeroCasa;
    }

    //métodos get n' set

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String toString(){
        return "CEP: " + cep + ", Local: " + local + ", Nº: " + numeroCasa + ", Bairro: " + bairro +
                ", Cidade: " + cidade + ", Estado: " + estado;
    }
}
