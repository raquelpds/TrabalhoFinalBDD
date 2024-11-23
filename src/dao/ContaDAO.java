package dao;

import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    // Método para consultar uma conta pelo número da conta
    public static Conta consultarConta(int numeroConta) {
        String sql = "SELECT * FROM conta WHERE numero_conta = ?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                // Definir o número da conta a ser consultada
                stmt.setInt(1, numeroConta);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    // Recuperar os dados da conta
                    String agencia = rs.getString("agencia");
                    double saldo = rs.getDouble("saldo");
                    String tipoConta = rs.getString("tipo_conta");
                    int numero = rs.getInt("numero_conta");
                    // Criar e retornar o objeto Conta dependendo do tipo
                    if ("corrente".equalsIgnoreCase(tipoConta)) {
                        double limite = rs.getDouble("limite");
                        Date dataVencimento = rs.getDate("data_vencimento");
                        return new ContaCorrente(agencia, null, numero, saldo, limite, dataVencimento.toLocalDate());
                    } else if ("poupanca".equalsIgnoreCase(tipoConta)) {
                        double taxaRendimento = rs.getDouble("taxa_rendimento");
                        return new ContaPoupanca(agencia, null, numero, saldo, taxaRendimento);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Retorna null se a conta não for encontrada
    }

    // Método para consultar todas as contas
    public static List<Conta> consultarTodasContas() {
        String sql = "SELECT * FROM conta";
        List<Conta> contas = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String agencia = rs.getString("agencia");
                    double saldo = rs.getDouble("saldo");
                    String tipoConta = rs.getString("tipo_conta");
                    int numero = rs.getInt("numero_conta");

                    if ("corrente".equalsIgnoreCase(tipoConta)) {
                        double limite = rs.getDouble("limite");
                        Date dataVencimento = rs.getDate("data_vencimento");
                        ContaCorrente conta = new ContaCorrente(agencia, null, numero, saldo, limite, dataVencimento.toLocalDate());
                        contas.add(conta);
                    } else if ("poupanca".equalsIgnoreCase(tipoConta)) {
                        double taxaRendimento = rs.getDouble("taxa_rendimento");
                        ContaPoupanca conta = new ContaPoupanca(agencia, null, numero, saldo, taxaRendimento);
                        contas.add(conta);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contas;
    }

    // Método para remover uma conta do banco de dados
    public static boolean remover(Conta conta) {
        String sql = "DELETE FROM conta WHERE numero_conta = ?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                // Definir o número da conta a ser removida
                stmt.setInt(1, conta.getNumero());

                // Executar o comando de remoção
                int rowsAffected = stmt.executeUpdate();

                return rowsAffected > 0;  // Se pelo menos uma linha foi afetada, a remoção foi bem-sucedida
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Retorna false se não conseguiu remover a conta
    }

    // Método para inserir uma nova conta no banco de dados
    public static boolean inserir(Conta conta) {
        String sql = "INSERT INTO conta (numero_conta, agencia, saldo, tipo_conta, limite, taxa_rendimento) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                // Preencher os parâmetros da consulta
                stmt.setInt(1, conta.getNumero());  // Número da conta
                stmt.setString(2, conta.getAgencia());  // Agência da conta
                stmt.setDouble(3, conta.getSaldo());  // Saldo da conta
                stmt.setString(4, conta instanceof ContaCorrente ? "corrente" : "poupanca");  // Tipo de conta
                if (conta instanceof ContaCorrente) {
                    // Para ContaCorrente, adiciona limite e data de vencimento
                    stmt.setDouble(5, ((ContaCorrente) conta).consultarLimite());
                    stmt.setNull(6, Types.DATE);  // Se necessário, pode adicionar a data de vencimento para ContaCorrente
                } else {
                    // Para ContaPoupanca, adiciona a taxa de rendimento
                    stmt.setNull(5, Types.DOUBLE);  // Limite não é aplicável para ContaPoupanca
                    stmt.setDouble(6, ((ContaPoupanca) conta).calcularRendimento());
                }

                // Executar a inserção
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;  // Se pelo menos uma linha foi afetada, a inserção foi bem-sucedida
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Retorna false se não conseguiu inserir a conta
    }
}
