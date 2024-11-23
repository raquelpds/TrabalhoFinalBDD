package dao;

import model.Endereco;
import model.Funcionario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FuncionarioDAO {

    private Connection connection;

    //Construtor
    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }


    // METODO PARA CADASTRAR FUNCIONÁRIO
    public void inserirFuncionario() throws SQLException {
        connection = ConnectionFactory.getConnection();
        connection.setAutoCommit(false);
        try (Scanner scanner = new Scanner(System.in)) {

            // inserir dados do usuário
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Data de Nascimento (DIA-MES-ANO): ");
            LocalDate data_nascimento = LocalDate.parse(scanner.nextLine());

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            System.out.println("Tipo de Usuário: ");
            String tipo_usuario = scanner.nextLine().toUpperCase();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            // inserir no SQL - ordem correta dos campos
            String sqlUsuario = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) VALUES (?, ?, ?, ?, ?, ?)";
            int usuarioId = 0;
            try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                stmtUsuario.setString(1, nome);
                stmtUsuario.setString(2, cpf);
                stmtUsuario.setDate(3, Date.valueOf(data_nascimento));
                stmtUsuario.setString(4, telefone);
                stmtUsuario.setString(5, tipo_usuario);
                stmtUsuario.setString(6, senha);
                stmtUsuario.executeUpdate();

                // Pegar o id_usuario gerado
                try (ResultSet rs = stmtUsuario.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuarioId = rs.getInt(1);  // O ID gerado é o primeiro campo
                    } else {
                        throw new SQLException("Falha ao recuperar o ID gerado para o usuário.");
                    }
                }

                // inserir dados do endereço
                System.out.print("Estado: ");
                String estado = scanner.nextLine();

                System.out.print("Cidade: ");
                String cidade = scanner.nextLine();

                System.out.print("Bairro: ");
                String bairro = scanner.nextLine();

                System.out.print("Logradouro: ");
                String local = scanner.nextLine();

                System.out.print("CEP: ");
                String cep = scanner.nextLine();

                System.out.print("Número da Casa: ");
                int numeroCasa = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer

                // Criar objeto Endereco
                Endereco endereco = new Endereco(estado, cidade, bairro, local, cep, numeroCasa);
                String sqlEndereco = "INSERT INTO endereco (cep, numero_casa, bairro, cidade, estado, usuario_id, local) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmtEndereco = connection.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS)) {
                    stmtEndereco.setString(1, endereco.getCep());
                    stmtEndereco.setInt(2, endereco.getNumeroCasa());
                    stmtEndereco.setString(3, endereco.getBairro());
                    stmtEndereco.setString(4, endereco.getCidade());
                    stmtEndereco.setString(5, endereco.getEstado());
                    stmtEndereco.setInt(6, usuarioId);
                    stmtEndereco.setString(7, endereco.getLocal());
                    stmtEndereco.executeUpdate();

                    // Recupera o ID do endereco
                    int enderecoId = 0;
                    try (ResultSet generatedKeys = stmtEndereco.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            enderecoId = generatedKeys.getInt(1);
                            System.out.println("Endereco ID: " + enderecoId);
                        }

                        // Inserir dados do funcionário
                        System.out.print("Código Funcionário: ");
                        String codigo_funcionario = scanner.nextLine();

                        System.out.print("Cargo: ");
                        String cargo = scanner.nextLine();

                        // Inserir no SQL - tabela funcionário
                        String sqlFuncionario = "INSERT INTO funcionário (codigo_funcionario, cargo, usuario_id, senha, endereco_id) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement stmtFuncionario = connection.prepareStatement(sqlFuncionario)) {
                            stmtFuncionario.setString(1, codigo_funcionario);
                            stmtFuncionario.setString(2, cargo);
                            stmtFuncionario.setInt(3, usuarioId);
                            stmtFuncionario.setString(4, senha);
                            stmtFuncionario.setInt(5, enderecoId);

                            stmtFuncionario.executeUpdate();
                        }
                        connection.commit(); // Confirma a transação
                        System.out.println("Funcionário inserido com sucesso!");

                    } catch (Exception e) {
                        connection.rollback(); // Reverte a transação em caso de erro
                        System.err.println("Erro ao inserir funcionário: " + e.getMessage());
                    }

                } catch (SQLException e) {
                    connection.rollback();
                    System.err.println("Erro ao inserir endereço: " + e.getMessage());
                }

            } catch (SQLException e) {
                connection.rollback();
                System.err.println("Erro ao inserir usuário: " + e.getMessage());
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Erro ao habilitar o autocommit: " + e.getMessage());
                }
                // Fechar a conexão ao final
                if (connection != null) {
                    ConnectionFactory.desconectar(connection);
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    // Metodo para atualizar um funcionário
    public void atualizarFuncionario() throws SQLException {
        connection = ConnectionFactory.getConnection();
        connection.setAutoCommit(false);
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite o Código do funcionário a ser atualizado: ");
            int codigo_funcionario = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha após o inteiro

            System.out.print("Novo cargo: ");
            String novoCargo = scanner.nextLine();

            System.out.print("Nova senha: ");
            String novaSenha = scanner.nextLine();

            // Corrigir a ordem dos parâmetros na consulta SQL
            String sql = "UPDATE funcionário SET cargo = ?, senha = ? WHERE codigo_funcionario = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, novoCargo);
                stmt.setString(2, novaSenha);
                stmt.setInt(3, codigo_funcionario);

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Funcionário atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum funcionário encontrado com o código informado.");
                }

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                System.err.println("Erro ao atualizar funcionário: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar a atualização: " + e.getMessage());
        } finally {
            // Fechar a conexão ao final
            if (connection != null) {
                ConnectionFactory.desconectar(connection);
            }
        }
    }

    // Metodo para excluir um funcionário
    public void excluirFuncionario() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite o Código do funcionário a ser excluído: ");
            int codigo_funcionario = scanner.nextInt();

            //DELETAR NO SQL
            String sql = "DELETE FROM funcionário WHERE codigo_funcionario = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, codigo_funcionario);

                int rowsDeleted = stmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Funcionário excluído com sucesso!");
                } else {
                    System.out.println("Nenhum funcionário encontrado com o ID informado.");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }

    // Metodo para consultar todos os funcionários e seus dados relacionados
    public void consultarTodosFuncionarios() {

        // Consulta SQL para pegar todos os funcionários, incluindo dados de usuário e endereço
        String sql = "SELECT f.id_funcionario, f.codigo_funcionario, f.cargo, f.usuario_id, f.endereco_id, " +
                "u.nome, u.cpf, u.data_nascimento, u.telefone, u.senha, " +
                "e.cep, e.local, e.numero_casa, e.bairro, e.cidade, e.estado " +
                "FROM funcionário f " +
                "JOIN usuario u ON u.id_usuario = f.usuario_id " + // Relacionando com a tabela usuario
                "JOIN endereco e ON e.usuario_id = u.id_usuario"; // Relacionando com a tabela endereco


        List<Funcionario> funcionarios = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            // Executar a consulta SQL
            ResultSet resultSet = stmt.executeQuery(sql);

            // Iterar sobre os resultados e preencher a lista de funcionários
            while (resultSet.next()) {
                // Criando o objeto Endereco a partir dos dados do ResultSet
                Endereco endereco = new Endereco(
                        resultSet.getString("estado"),
                        resultSet.getString("cidade"),
                        resultSet.getString("bairro"),
                        resultSet.getString("local"),
                        resultSet.getString("cep"),
                        resultSet.getInt("numero_casa")
                );

                // Criando o objeto Funcionario a partir dos dados do ResultSet
                Funcionario funcionario = new Funcionario(
                        resultSet.getInt("id_funcionario"), // id_funcionario da tabela funcionario
                        resultSet.getString("nome"), // nome vindo da tabela usuario
                        resultSet.getString("cpf"),
                        resultSet.getDate("data_nascimento").toLocalDate(),
                        resultSet.getString("telefone"),
                        endereco, // Endereço é mapeado a partir do SELECT
                        resultSet.getString("senha"),
                        resultSet.getString("codigo_funcionario"),
                        resultSet.getString("cargo"),
                        resultSet.getInt("usuario_id"),
                        resultSet.getInt("endereco_id")
                );

                // Adicionando o funcionário à lista
                funcionarios.add(funcionario);
            }

            //TESTE PARA VERIFICAR SE OS DADOS ESTÃO RETORNANDO OK
            /*
            // Exibindo os dados dos funcionários no console
            if (funcionarios.isEmpty()) {
                System.out.println("Não há funcionários cadastrados.");
            } else {
                for (Funcionario f : funcionarios) {
                    System.out.println("=========================================");
                    System.out.println("ID do Funcionário: " + f.getId_funcionario());
                    System.out.println("Código do Funcionário: " + f.getCodigo_funcionario());
                    System.out.println("Cargo: " + f.getCargo());
                    System.out.println("Usuário ID: " + f.getUsuario_id());
                    System.out.println("Nome: " + f.getNome());
                    System.out.println("CPF: " + f.getCpf());
                    System.out.println("Data de Nascimento: " + f.getData_nascimento());
                    System.out.println("Telefone: " + f.getTelefone());
                    System.out.println("Senha: " + f.getSenha());

                    // Exibindo o endereço do funcionário
                    if (f.getEndereco() != null) {
                        System.out.println("Endereço:");
                        System.out.println("CEP: " + f.getEndereco().getCep());
                        System.out.println("Logradouro: " + f.getEndereco().getLocal());
                        System.out.println("Número da Casa: " + f.getEndereco().getNumeroCasa());
                        System.out.println("Bairro: " + f.getEndereco().getBairro());
                        System.out.println("Cidade: " + f.getEndereco().getCidade());
                        System.out.println("Estado: " + f.getEndereco().getEstado());
                    } else {
                        System.out.println("Endereço não disponível.");
                    }
                    System.out.println("=========================================");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());

    */


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}







