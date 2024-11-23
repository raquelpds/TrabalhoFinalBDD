package controller;

import dao.ConnectionFactory;
import dao.ContaDAO;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.Endereco;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class BancoController {

    // Método para abrir conta
    public static String abrirConta(String agencia, Cliente id_cliente, int numero, double saldo, String tipoConta, double limite, LocalDate dataVencimento, double taxaRendimento) {
        Conta novaConta;

        // Criando conta corrente ou poupança com base no tipo de conta
        if ("corrente".equalsIgnoreCase(tipoConta)) {
            novaConta = new ContaCorrente(agencia, id_cliente, numero, saldo, limite, dataVencimento);
        } else if ("poupanca".equalsIgnoreCase(tipoConta)) {
            novaConta = new ContaPoupanca(agencia, id_cliente, numero, saldo, taxaRendimento);
        } else {
            return "Tipo de conta inválido!";
        }

        // Inserindo a conta no banco de dados
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO conta (numero_conta, agencia, saldo, cliente_id) VALUES (?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, numero);
                stmt.setString(2, agencia);
                stmt.setDouble(3, saldo);
                stmt.setInt(4, id_cliente.getId_cliente());

                stmt.executeUpdate();
                return "Conta " + tipoConta + " criada com sucesso!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao cadastrar a conta: " + e.getMessage();
        }
    }

    // Método para encerrar conta
    public static String encerrarConta(int numeroConta) {
        Conta conta = ContaDAO.consultarConta(numeroConta);

        if (conta == null) {
            return "Conta não encontrada!";
        }

        if (conta.getSaldo() != 0) {
            return "Conta não pode ser encerrada. Saldo não está zerado!";
        }

        boolean sucesso = ContaDAO.remover(conta);
        return sucesso ? "Conta encerrada com sucesso!" : "Erro ao tentar encerrar a conta!";
    }

    // Método para cadastrar funcionário
    public static String cadastrarFuncionario(String nome, String cpf, String cargo, double salario) {
        String sql = "INSERT INTO funcionario (nome, cpf, cargo, salario) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, cargo);
            stmt.setDouble(4, salario);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0 ? "Funcionário cadastrado com sucesso!" : "Falha ao cadastrar o funcionário!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao cadastrar o funcionário: " + e.getMessage();
        }
    }

    // Método para buscar cliente por ID
    public static Optional<Cliente> buscarClientePorId(int clienteId) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt("id_usuario");
                    String nome = rs.getString("nome");
                    String cpf = rs.getString("cpf");
                    LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                    String telefone = rs.getString("telefone");
                    String senha = rs.getString("senha");
                    int usuarioId = rs.getInt("usuario_id");

                    Endereco endereco = buscarEnderecoPorClienteId(clienteId);

                    Cliente cliente = new Cliente(idUsuario, nome, cpf, dataNascimento, telefone, endereco, senha, usuarioId);
                    return Optional.of(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    // Método para buscar endereço por ID do cliente
    public static Endereco buscarEnderecoPorClienteId(int clienteId) {
        String sql = "SELECT * FROM endereco WHERE cliente_id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Endereco(
                            rs.getString("estado"),
                            rs.getString("cidade"),
                            rs.getString("bairro"),
                            rs.getString("local"),
                            rs.getString("cep"),
                            rs.getInt("numero_casa")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
