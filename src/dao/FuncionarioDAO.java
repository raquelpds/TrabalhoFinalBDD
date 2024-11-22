package dao;

import model.Endereco;
import model.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    private Connection connection;

    //Construtor
    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }

    //INSERÇÃO: metodo para inserir um usuário no banco de dados
    public void save(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionário (codigo_funcionario, cargo, usuario_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, funcionario.getCodigo_funcionario());
            stmt.setString(2, funcionario.getCargo());
            stmt.setInt(3, funcionario.getId_usuario());
            stmt.executeUpdate();

            // Obter o ID gerado, se necessário
            ResultSet resultS = stmt.getGeneratedKeys();
            if (resultS.next()) {
                funcionario.setId_funcionario(resultS.getInt(1));
            }
        }
    }

    //EXCLUSÃO: metodo para remover um funcionario pelo ID
    public void delete(int idFuncionario) throws SQLException {
        String sql = "DELETE FROM funcionário WHERE id_funcionario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idFuncionario);
            stmt.executeUpdate();
        }
    }

    /*//Metodo para ler um funcionario pelo id
    public Funcionario read (int idFuncionario) throws SQLException{
        String sql = "SELECT * FROM funcionário WHERE id_funcionario = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1,idFuncionario);
            ResultSet resul = stmt.executeQuery();
            if(resul.next()){
                Endereco endereco = new Endereco(
                        resul.getString("estado"),
                        resul.getString("cidade"),
                        resul.getString("bairro"),
                        resul.getString("local"),
                        resul.getString("cep"),
                        resul.getInt("numeroCasa")
                );
                return new Funcionario(
                        resul.getInt("id_usuario"),
                        resul.getString("nome"),
                        resul.getString("cpf"),
                        resul.getDate("data_nascimento").toLocalDate(),
                        resul.getString("telefone"),
                        endereco,
                        resul.getString("senha"),
                        resul.getString("codigo_funcionario"),
                        resul.getString("cargo"),
                        resul.getInt("usuario_id")
                );
            }
        }
        return null;
    } */

    // READ LIST: metodo para listar todos os funcionários
    public List<Funcionario> findAll() throws SQLException{
        String sql = "SELECT * FROM funcionário";
        List<Funcionario> funcionarios = new ArrayList<>();

        try(Statement stmt = connection.createStatement()){
            ResultSet resul = stmt.executeQuery(sql);

            while (resul.next()){
                Endereco endereco = new Endereco(
                        resul.getString("estado"),
                        resul.getString("cidade"),
                        resul.getString("bairro"),
                        resul.getString("local"),
                        resul.getString("cep"),
                        resul.getInt("numeroCasa")
                );
                Funcionario funcionario = new Funcionario(
                        resul.getInt("id_usuario"),
                        resul.getString("nome"),
                        resul.getString("cpf"),
                        resul.getDate("data_nascimento").toLocalDate(),
                        resul.getString("telefone"),
                        endereco,
                        resul.getString("senha"),
                        resul.getString("codigo_funcionario"),
                        resul.getString("cargo"),
                        resul.getInt("usuario_id")
                );
                funcionarios.add(funcionario);
            }
            return funcionarios;
        }
    }

    //ATUALIZAÇÃO: metodo para atualizar os dados de um funcionário
    public void update(Funcionario funcionario) throws SQLException{
        String sql = "UPDATE funcionário SET codigo_funcionario = ?, cargo = ?, usuario_id = ? WHERE id_funcionario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, funcionario.getId_funcionario());
            stmt.setString(2, funcionario.getCodigo_funcionario());
            stmt.setString(3, funcionario.getCargo());
            stmt.setString(4, funcionario.getSenha());
            stmt.setInt(5, funcionario.getUsuario_id());
            stmt.executeUpdate();
        }
    }






    }






    //metodo para buscar um usuario por ID



    //metodo para atualizar um usuario no banco de dados
