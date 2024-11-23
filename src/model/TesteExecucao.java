package model;

import dao.ConnectionFactory;
import dao.FuncionarioDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class TesteExecucao {
    public static void main(String[] args) throws SQLException {

        //Conexão com o Banco de Dados
        try(Connection connection = ConnectionFactory.getConnection()) {
            System.out.println("Conexão estabelecida com sucesso!");

            //Instância do DAO
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);

            // Menu interativo
            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                System.out.println("=== Menu de Funcionários ===");
                System.out.println("1. Inserir Funcionário");
                System.out.println("2. Atualizar Funcionário");
                System.out.println("3. Excluir Funcionário");
                System.out.println("4. Consultar Funcionários");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir quebra de linha

                switch (opcao) {
                    case 1:
                        funcionarioDAO.inserirFuncionario();
                        break;
                    case 2:
                        funcionarioDAO.atualizarFuncionario();
                        break;
                    case 3:
                        funcionarioDAO.excluirFuncionario();
                        break;
                    case 4:
                        funcionarioDAO.consultarTodosFuncionarios();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
                System.out.println(); // Separador visual
            } while (opcao != 0);

            // Fechar recursos
            scanner.close();
            connection.close();

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }

    }

}
