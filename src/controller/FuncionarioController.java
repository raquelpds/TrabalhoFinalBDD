package controller;

import dao.FuncionarioDAO;
import model.Funcionario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO;

    // Construtor
    public FuncionarioController(Connection connection) {
        this.funcionarioDAO = new FuncionarioDAO(connection);
    }

    //INSERT
    public void inserirFuncionario() {
        try {
            funcionarioDAO.inserirFuncionario();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    //UPDATE
    public void atualizarFuncionario() {
        try {
            funcionarioDAO.atualizarFuncionario();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    //DELETE
    public void excluirFuncionario() {
        try {
            funcionarioDAO.excluirFuncionario();
        } catch (Exception e) {
            System.err.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }

    //CONNSULTA
    public void consultarTodosFuncionarios() {
        try {
            funcionarioDAO.consultarTodosFuncionarios();
        } catch (Exception e) {
            System.err.println("Erro ao consultar funcionários: " + e.getMessage());
        }
    }

}
