package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCliente {

    // Método para exibir o Menu do Cliente
    public void exibirMenuCliente() {
        JFrame frameCliente = new JFrame("Menu do Cliente");
        frameCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCliente.setSize(500, 400);
        frameCliente.setLayout(new GridLayout(7, 1, 10, 10));

        // Botões para operações do menu do cliente
        JButton botaoSaldo = new JButton("Saldo");
        JButton botaoDeposito = new JButton("Depósito");
        JButton botaoSaque = new JButton("Saque");
        JButton botaoExtrato = new JButton("Extrato");
        JButton botaoConsultarLimite = new JButton("Consultar Limite");
        JButton botaoSair = new JButton("Sair");

        // Adiciona os botões ao frame
        frameCliente.add(botaoSaldo);
        frameCliente.add(botaoDeposito);
        frameCliente.add(botaoSaque);
        frameCliente.add(botaoExtrato);
        frameCliente.add(botaoConsultarLimite);
        frameCliente.add(botaoSair);

        // Funcionalidade dos botões
        botaoSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // BancoController.consultarSaldo(); // Chama o método de consulta de saldo
            }
        });

        botaoDeposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //BancoController.depositar(); // Chama o método de depósito
            }
        });

        botaoSaque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //BancoController.sacar(); // Chama o método de saque
            }
        });

        botaoExtrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // BancoController.extrato(); // Chama o método de extrato
            }
        });

        botaoConsultarLimite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // BancoController.consultarLimite(); // Chama o método de consulta de limite
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameCliente.dispose(); // Fecha o Menu do Cliente
            }
        });

        // Exibir a janela
        frameCliente.setLocationRelativeTo(null); // Centraliza na tela
        frameCliente.setVisible(true);
    }
}
