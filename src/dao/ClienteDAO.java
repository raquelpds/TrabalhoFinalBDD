package dao;

import model.Cliente;
import model.Endereco;
import util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    //SAVE
    public void save(Cliente cliente) throws SQLException {
        String sqlUsuario = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) VALUES (?, ?, ?, ?, 'CLIENTE', ?)";
        String sqlCliente = "INSERT INTO cliente (id_usuario) VALUES (?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement stmtCliente = connection.prepareStatement(sqlCliente)) {

            // Inserindo dados na tabela usuario
            stmtUsuario.setString(1, cliente.getNome());
            stmtUsuario.setString(2, cliente.getCpf());
            stmtUsuario.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            stmtUsuario.setString(4, cliente.getTelefone());
            stmtUsuario.setString(5, cliente.getSenha());

            stmtUsuario.executeUpdate();

            // Recuperando o ID gerado para o usuário
            ResultSet generatedKeys = stmtUsuario.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idUsuario = generatedKeys.getInt(1);

                // Inserindo dados na tabela cliente
                stmtCliente.setInt(1, idUsuario);
                stmtCliente.executeUpdate();
            } else {
                throw new SQLException("Falha ao obter o ID do usuário.");
            }
        }
    }

    //BUSCAR
    public Cliente findById(int id) throws SQLException {
        String sql = "SELECT u.nome, u.cpf, u.data_nascimento, u.telefone, u.senha " +
                "FROM cliente c " +
                "JOIN usuario u ON c.id_usuario = u.id_usuario " +
                "WHERE c.id_cliente = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(id);
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setSenha(rs.getString("senha"));
                return cliente;
            }
        }
        return null;
    }

    //UPDATE
    public void update(Cliente cliente) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, data_nascimento = ?, telefone = ?, senha = ? " +
                "WHERE id_usuario = (SELECT id_usuario FROM cliente WHERE id_cliente = ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getSenha());
            stmt.setInt(6, cliente.getId());

            stmt.executeUpdate();
        }
    }

    //DELETE
    public void delete(int id) throws SQLException {
        String sqlCliente = "DELETE FROM cliente WHERE id_cliente = ?";
        String sqlUsuario = "DELETE FROM usuario WHERE id_usuario = (SELECT id_usuario FROM cliente WHERE id_cliente = ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtCliente = connection.prepareStatement(sqlCliente);
             PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {

            // Deletando o cliente
            stmtCliente.setInt(1, id);
            stmtCliente.executeUpdate();

            // Deletando o usuário relacionado
            stmtUsuario.setInt(1, id);
            stmtUsuario.executeUpdate();
        }
    }

    //LISTAR TODOS
    public List<Cliente> findAll() throws SQLException {
        String sql = "SELECT c.id_cliente, u.nome, u.cpf, u.data_nascimento, u.telefone " +
                "FROM cliente c " +
                "JOIN usuario u ON c.id_usuario = u.id_usuario";

        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}
