package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public static void main(String[] args) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Falha na conexão: " + e.getMessage());
        }
    }

}
