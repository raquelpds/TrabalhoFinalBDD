package dao;

import java.sql.*;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/db_malveder";
    private static final String USER = "root";
    private static final String PASSWORD = "GH38xcjj#";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }

    public static void desconectar(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            System.out.println("Conexão estabelecida com sucesso!");

            // Criar um Statement e fazer uma consulta simples
            String sql = "SHOW TABLES"; // Exemplo: mostra todas as tabelas do banco
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    System.out.println("Tabela: " + rs.getString(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Falha na conexão ou consulta: " + e.getMessage());
        }
        String sql = "INSERT INTO Usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Configurando os parâmetros do comando SQL
            stmt.setString(1, "João Silva");
            stmt.setString(2, "12345678901");
            stmt.setDate(3, java.sql.Date.valueOf("1985-03-25"));
            stmt.setString(4, "123456789");
            stmt.setString(5, "CLIENTE");
            stmt.setString(6, "senha123");

            // Executa a inserção no banco de dados
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso! Linhas afetadas: " + linhasAfetadas);

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        }
    }
}
