package view;

import dao.ConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginView {

    public static void main(String[] args) {
        // Criação da janela principal
        JFrame frame = new JFrame("Tela de Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new BorderLayout());

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        frame.add(panel, BorderLayout.CENTER);

        // Configurações de layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label e campo de texto para CPF
        JLabel labelCPF = new JLabel("CPF: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelCPF, gbc);

        JTextField campoCPF = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(campoCPF, gbc);

        // Label e campo de texto para senha
        JLabel labelSenha = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelSenha, gbc);

        JPasswordField campoSenha = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(campoSenha, gbc);

        // Botão para login como Funcionário
        JButton botaoFuncionario = new JButton("Login como Funcionário");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(botaoFuncionario, gbc);

        // Botão para login como Cliente
        JButton botaoCliente = new JButton("Login como Cliente");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(botaoCliente, gbc);

        // Funcionalidade do botão Login como Funcionário
        botaoFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = campoCPF.getText();
                String senha = new String(campoSenha.getPassword());

                if (verificarCredenciais(cpf, senha, "funcionario")) {
                    JOptionPane.showMessageDialog(frame, "Login como Funcionário bem-sucedido!");
                    frame.dispose();  // Fecha a tela de login
                    abrirMenuFuncionario(); // Abre o Menu do Funcionário
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário ou senha inválidos para Funcionário.");
                }
            }
        });

        // Funcionalidade do botão Login como Cliente
        botaoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = campoCPF.getText();
                String senha = new String(campoSenha.getPassword());

                if (verificarCredenciais(cpf, senha, "cliente")) {
                    JOptionPane.showMessageDialog(frame, "Login como Cliente bem-sucedido!");
                    frame.dispose();  // Fecha a tela de login
                    exibirMenuCliente(); // Abre o Menu do Cliente
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário ou senha inválidos para Cliente.");
                }
            }
        });

        // Exibir a janela
        frame.setLocationRelativeTo(null); // Centraliza na tela
        frame.setVisible(true);
    }

    // Verificar credenciais
    private static boolean verificarCredenciais(String cpf, String senha, String tipo_usuario) {
        String query = "SELECT * FROM usuario WHERE cpf = ? AND senha = ? AND tipo_usuario = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, cpf);
            stmt.setString(2, senha);
            stmt.setString(3, tipo_usuario); // 'funcionario' ou 'cliente'

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // Retorna true se encontrou o usuário
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return false;
    }

    //abrir o Menu do Funcionário
    private static void abrirMenuFuncionario() {
        // Cria uma nova instância do MenuFuncionario e chama o método para exibir o menu
        MenuFuncionario menuFuncionario = new MenuFuncionario();
        menuFuncionario.exibirMenuFuncionario(); // Exibe o Menu do Funcionário
    }

    //abrir o Menu do Cliente
    private static void exibirMenuCliente() {
        // Cria uma nova instância do MenuCliente e chama o método para exibir o menu
        MenuCliente menuCliente = new MenuCliente();
        menuCliente.exibirMenuCliente(); // Exibe o Menu do Cliente
    }
}
