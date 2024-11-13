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

    //métodos
    public void gerarRelatorioGeral(){
        //implementação
    }

    public void exportarParaExcel(){
        //implementação
    }
}
