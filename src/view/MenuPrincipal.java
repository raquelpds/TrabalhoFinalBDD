package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal {

    // Método main para iniciar o Menu Principal diretamente
    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.exibirMenu();  // Exibe o menu principal
    }

    // Método para exibir o Menu Principal
    public void exibirMenu() {
        // Criação da janela principal
        JFrame frame = new JFrame("Menu Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridBagLayout());

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        frame.add(panel);

        // Configurações de layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Botão para o Menu do Funcionário
        JButton botaoFuncionario = new JButton("Funcionário");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(botaoFuncionario, gbc);

        // Botão para o Menu do Cliente
        JButton botaoCliente = new JButton("Cliente");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(botaoCliente, gbc);

        // Botão para Sair
        JButton botaoSair = new JButton("Sair");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(botaoSair, gbc);

        // Funcionalidade dos botões
        botaoFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Fecha o menu principal
                abrirMenuFuncionario(); // Abre o menu do funcionário
            }
        });

        botaoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Fecha o menu principal
                abrirMenuCliente(); // Abre o menu do cliente
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fecha o aplicativo
            }
        });

        // Exibir a janela
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Método para abrir o menu do Funcionário
    private static void abrirMenuFuncionario() {
        // Cria uma nova instância do MenuFuncionario e chama o método para exibir o menu
        MenuFuncionario menuFuncionario = new MenuFuncionario();
        menuFuncionario.exibirMenuFuncionario(); // Exibe o Menu do Funcionário
    }

    // Método para abrir o menu do Cliente
    private static void abrirMenuCliente() {
        // Cria uma instância do MenuCliente
        MenuCliente menuCliente = new MenuCliente();
        menuCliente.exibirMenuCliente(); // Chama o método para exibir o menu do cliente
    }
}
