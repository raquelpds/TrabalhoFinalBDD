package view;

import dao.ClienteDAO;
import model.Cliente;
import util.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MenuCliente {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    //EXIBIR MENU
    public void exibirMenuCliente() {
        JFrame frameCliente = new JFrame("Menu do Cliente");
        frameCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCliente.setSize(500, 400);
        frameCliente.setLayout(new GridLayout(6, 1, 10, 10));

        // Botões para operações do menu do cliente
        JButton botaoCadastrar = new JButton("Cadastrar Cliente");
        JButton botaoConsultar = new JButton("Consultar Cliente");
        JButton botaoAtualizar = new JButton("Atualizar Cliente");
        JButton botaoDeletar = new JButton("Deletar Cliente");
        JButton botaoListarTodos = new JButton("Listar Todos os Clientes");
        JButton botaoSair = new JButton("Sair");

        // Adiciona os botões ao frame
        frameCliente.add(botaoCadastrar);
        frameCliente.add(botaoConsultar);
        frameCliente.add(botaoAtualizar);
        frameCliente.add(botaoDeletar);
        frameCliente.add(botaoListarTodos);
        frameCliente.add(botaoSair);

        // Funcionalidade dos botões
        botaoCadastrar.addActionListener(e -> cadastrarCliente());
        botaoConsultar.addActionListener(e -> consultarCliente());
        botaoAtualizar.addActionListener(e -> atualizarCliente());
        botaoDeletar.addActionListener(e -> deletarCliente());
        botaoListarTodos.addActionListener(e -> listarTodosClientes());
        botaoSair.addActionListener(e -> frameCliente.dispose());

        // Exibir a janela
        frameCliente.setLocationRelativeTo(null); // Centraliza na tela
        frameCliente.setVisible(true);
    }

    //INSERT
    private void cadastrarCliente() {
        try {
            String nome = JOptionPane.showInputDialog("Nome:");
            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome não pode ser vazio.");
                return;
            }

            String cpf = JOptionPane.showInputDialog("CPF:");
            if (cpf == null || cpf.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "CPF não pode ser vazio.");
                return;
            }

            String dataNascimentoStr = JOptionPane.showInputDialog("Data de Nascimento (AAAA-MM-DD):");
            LocalDate dataNascimento;
            try {
                if (dataNascimentoStr == null || dataNascimentoStr.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Data de nascimento não pode ser vazia.");
                    return;
                }
                dataNascimento = LocalDate.parse(dataNascimentoStr); // Converte para LocalDate
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato AAAA-MM-DD.");
                return;
            }

            String telefone = JOptionPane.showInputDialog("Telefone:");
            if (telefone == null || telefone.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Telefone não pode ser vazio.");
                return;
            }

            String senha = JOptionPane.showInputDialog("Senha:");
            if (senha == null || senha.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Senha não pode ser vazia.");
                return;
            }

            // Cria o objeto Cliente
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setCpf(cpf);
            cliente.setDataNascimento(dataNascimento);
            cliente.setTelefone(telefone);
            cliente.setSenha(senha);

            // Salva o cliente no banco de dados
            clienteDAO.save(cliente);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + e.getMessage());
        }
    }


    //CONSULTA
    private void consultarCliente() {
        try {
            String idStr = JOptionPane.showInputDialog("Digite o ID do cliente:");
            int id = Integer.parseInt(idStr);

            Cliente cliente = clienteDAO.findById(id);
            if (cliente != null) {
                JOptionPane.showMessageDialog(null, "Dados do Cliente:\n" +
                        "ID: " + id + "\n" +
                        "Nome: " + cliente.getNome() + "\n" +
                        "CPF: " + cliente.getCpf() + "\n" +
                        "Data de Nascimento: " + cliente.getDataNascimento() + "\n" +
                        "Telefone: " + cliente.getTelefone());
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar cliente: " + e.getMessage());
        }
    }

    //UPDATE
    private void atualizarCliente() {
        try {
            String idStr = JOptionPane.showInputDialog("Digite o ID do cliente para atualizar:");
            int id = Integer.parseInt(idStr);

            Cliente cliente = clienteDAO.findById(id);
            if (cliente != null) {
                String novoNome = JOptionPane.showInputDialog("Novo Nome:", cliente.getNome());
                String novoCpf = JOptionPane.showInputDialog("Novo CPF:", cliente.getCpf());
                String novaDataNascimentoStr = JOptionPane.showInputDialog("Nova Data de Nascimento (AAAA-MM-DD):", cliente.getDataNascimento());
                LocalDate novaDataNascimento = LocalDate.parse(novaDataNascimentoStr);
                String novoTelefone = JOptionPane.showInputDialog("Novo Telefone:", cliente.getTelefone());
                String novaSenha = JOptionPane.showInputDialog("Nova Senha:", cliente.getSenha());

                cliente.setNome(novoNome);
                cliente.setCpf(novoCpf);
                cliente.setDataNascimento(novaDataNascimento);
                cliente.setTelefone(novoTelefone);
                cliente.setSenha(novaSenha);

                clienteDAO.update(cliente);
                JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    //DELETE
    private void deletarCliente() {
        try {
            String idStr = JOptionPane.showInputDialog("Digite o ID do cliente para deletar:");
            int id = Integer.parseInt(idStr);

            clienteDAO.delete(id);
            JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar cliente: " + e.getMessage());
        }
    }

    //LIST
    private void listarTodosClientes() {
        try {
            List<Cliente> clientes = clienteDAO.findAll();
            if (clientes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
            } else {
                StringBuilder listaClientes = new StringBuilder("Lista de Clientes:\n");
                for (Cliente cliente : clientes) {
                    listaClientes.append("ID: ").append(cliente.getId())
                            .append(", Nome: ").append(cliente.getNome())
                            .append(", CPF: ").append(cliente.getCpf())
                            .append(", Telefone: ").append(cliente.getTelefone())
                            .append("\n");
                }
                JOptionPane.showMessageDialog(null, listaClientes.toString());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + e.getMessage());
        }
    }
}
