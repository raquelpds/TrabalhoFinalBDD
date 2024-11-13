package model;

import java.time.LocalDateTime;
import java.util.List;

public class Relatorio {
    private String tipo;
    private LocalDateTime dataGeracao;
    private List<String> dados;

    //construtor
    public Relatorio(List<String> dados, LocalDateTime dataGeracao, String tipo) {
        this.dados = dados;
        this.dataGeracao = dataGeracao;
        this.tipo = tipo;
    }

    //métodos getters and setters
    public List<String> getDados() {
        return dados;
    }

    public void setDados(List<String> dados) {
        this.dados = dados;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //métodos
    public void gerarRelatorioGeral(){
        //implementação
    }

    public void exportarParaExcel(){
        //implementação
    }
}
