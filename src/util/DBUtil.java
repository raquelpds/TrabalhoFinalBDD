package util;

import dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    // Configuração do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/db_malveder";
    private static final String USER = "root";
    private static final String PASSWORD = "GH38xcjj#";

    static {
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver JDBC do MySQL: " + e.getMessage());
        }
    }

    //CONEXÃO COM O BANCO
    public static Connection getConnection() throws SQLException {
        return ConnectionFactory.getConnection();
    }

    // Método para fechar uma conexão
    public static void fecharConexao(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
