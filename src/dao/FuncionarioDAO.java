package dao;

import model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FuncionarioDAO {

    private Connection connection;

    //Construtor
    public FuncionarioDAO(Connection connection){
        this.connection = connection;
    }

    //metodo para inserir um usuário no banco de dados
    public void save(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (codigo_funcionario, cargo, id_usuario) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, funcionario.getCodigoFuncionario());
            stmt.setString(2, funcionario.getCargo());
            stmt.setInt(3, funcionario.getIdUsuario());
            stmt.executeUpdate();
    }


    //metodo para buscar um usuario por ID

    //metodo para listar todos os usuarios

    //metodo para atualizar um usuario no banco de dados
}
