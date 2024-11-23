package view;

import controller.BancoController;
import model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class MenuFuncionario {

    // Método para exibir o Menu do Funcionário
    public void exibirMenuFuncionario() {
        JFrame frameFuncionario = new JFrame("Menu do Funcionário");
        frameFuncionario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFuncionario.setSize(500, 400);
        frameFuncionario.setLayout(new GridLayout(8, 1, 10, 10));

        // Botões para operações do menu do funcionário
        JButton botaoAberturaConta = new JButton("Abertura de Conta");
        JButton botaoEncerramentoConta = new JButton("Encerramento de Conta");
        JButton botaoConsultaDados = new JButton("Consulta de Dados");
        JButton botaoAlteracaoDados = new JButton("Alteração de Dados");
        JButton botaoCadastroFuncionario = new JButton("Cadastro de Funcionário");
        JButton botaoGerarRelatorios = new JButton("Geração de Relatórios");
        JButton botaoSair = new JButton("Sair");

        // Adiciona os botões ao frame
        frameFuncionario.add(botaoAberturaConta);
        frameFuncionario.add(botaoEncerramentoConta);
        frameFuncionario.add(botaoConsultaDados);
        frameFuncionario.add(botaoAlteracaoDados);
        frameFuncionario.add(botaoCadastroFuncionario);
        frameFuncionario.add(botaoGerarRelatorios);
        frameFuncionario.add(botaoSair);

        // Funcionalidade dos botões
        botaoAberturaConta.addActionListener(e -> {
            try {
                // Coleta os dados do cliente e da conta
                String agencia = JOptionPane.showInputDialog("Digite a agência:");
                String clienteIdInput = JOptionPane.showInputDialog("Digite o ID do cliente:");
                String numeroInput = JOptionPane.showInputDialog("Digite o número da conta:");
                String saldoInput = JOptionPane.showInputDialog("Digite o saldo inicial:");
                String tipoConta = JOptionPane.showInputDialog("Digite o tipo de conta (corrente/poupanca):");
                String limite = JOptionPane.showInputDialog("Digite o limite: ");
                LocalDate dataVencimento = LocalDate.parse(JOptionPane.showInputDialog("Digite a data de vencimento: "));
                double taxaRendimento = Double.parseDouble(JOptionPane.showInputDialog("Digite a taxa de rendimento: "));

                // Valida os dados
                if (agencia.isBlank() || clienteIdInput.isBlank() || numeroInput.isBlank() || saldoInput.isBlank() || limite.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!");
                    return;
                }

                // Converte os dados para os tipos adequados
                int clienteId = Integer.parseInt(clienteIdInput);
                int numero = Integer.parseInt(numeroInput);
                double saldo = Double.parseDouble(saldoInput);

                // Busca o cliente pelo ID
                Cliente cliente = BancoController.buscarClientePorId(clienteId).orElseThrow(() -> new Exception("Cliente não encontrado!"));

                // Chama o método para abrir a conta e mostra o resultado
                String resultado = BancoController.abrirConta(agencia, cliente, numero, saldo, tipoConta, Double.parseDouble(limite), dataVencimento, taxaRendimento);
                JOptionPane.showMessageDialog(null, resultado);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        });

        // Ação para o botão "Sair"
        botaoSair.addActionListener(e -> frameFuncionario.dispose());

        // Tornar a janela visível
        frameFuncionario.setVisible(true);
    }

    // Ponto de entrada
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuFuncionario().exibirMenuFuncionario());
    }
}
