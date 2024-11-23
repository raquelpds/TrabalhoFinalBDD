package model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Relatorio {
    private String tipo;
    private LocalDateTime dataGeracao;
    private List<String> dados;

    // Construtor
    public Relatorio(List<String> dados, LocalDateTime dataGeracao, String tipo) {
        this.dados = dados;
        this.dataGeracao = dataGeracao;
        this.tipo = tipo;
    }

    // Métodos getters e setters
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

    //RELATORIO GERAL
    public void gerarRelatorioGeral() {
        System.out.println("Gerando relatório geral...");
        System.out.println("Tipo: " + tipo);
        System.out.println("Data de Geração: " + dataGeracao);
        System.out.println("Dados do Relatório:");

        if (dados != null && !dados.isEmpty()) {
            for (String linha : dados) {
                System.out.println(linha);
            }
        } else {
            System.out.println("Nenhum dado disponível para o relatório.");
        }
    }

    //EXPORTAR PARA EXCEL
    public void exportarParaExcel() {
        String nomeArquivo = "relatorio_" + tipo.toLowerCase() + "_" + dataGeracao.toString().replace(":", "-") + ".csv";

        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            // Adicionando cabeçalho
            writer.append("Relatório: ").append(tipo).append("\n");
            writer.append("Data de Geração: ").append(dataGeracao.toString()).append("\n\n");

            // Adicionando os dados do relatório
            if (dados != null && !dados.isEmpty()) {
                writer.append("Dados:\n");
                for (String linha : dados) {
                    writer.append(linha).append("\n");
                }
            } else {
                writer.append("Nenhum dado disponível para exportar.\n");
            }

            System.out.println("Relatório exportado com sucesso para o arquivo: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao exportar o relatório para o arquivo: " + e.getMessage());
        }
    }
}
