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
        botaoFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = campoCPF.getText();
                String senha = new String(campoSenha.getPassword());

                if (verificarCredenciais(cpf, senha, "funcionario")) {
                    JOptionPane.showMessageDialog(frame, "Login como Funcionário bem-sucedido!");
                    frame.dispose();  // Fecha a tela de login
                    abrirMenuPrincipal(); // Abre o Menu Principal
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário ou senha inválidos para Funcionário.");
                }

            }
        });

        // Botão para login como Cliente
        JButton botaoCliente = new JButton("Login como Cliente");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(botaoCliente, gbc);

        // Ações dos botões
        botaoFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = campoCPF.getText();
                String senha = new String(campoSenha.getPassword());
                if (verificarCredenciais(cpf, senha, "funcionario")) {
                    JOptionPane.showMessageDialog(frame, "Login como Funcionário bem-sucedido!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário ou senha inválidos para Funcionário.");
                }
            }
        });

        botaoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = campoCPF.getText();
                String senha = new String(campoSenha.getPassword());
                if (verificarCredenciais(cpf, senha, "cliente")) {
                    JOptionPane.showMessageDialog(frame, "Login como Cliente bem-sucedido!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário ou senha inválidos para Cliente.");
                }
            }
        });

        // Exibir a janela
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    //Verificar credenciais
    private static boolean verificarCredenciais(String cpf, String senha, String tipo_usuario) {
        String query = "SELECT * FROM usuario WHERE cpf = ? AND senha = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // Retorna true se encontrou o usuário
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return false;
    }

    private static void abrirMenuPrincipal() {
        // Cria uma nova instância do MenuPrincipal e chama o método para exibir o menu
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.exibirMenu(); // Exibe o MenuPrincipal
    }






}
