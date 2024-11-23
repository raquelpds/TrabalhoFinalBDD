package controller;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        clienteDAO = new ClienteDAO();
    }

    // Método para cadastrar um novo cliente
    public boolean cadastrarCliente(Cliente cliente) {
        try {
            clienteDAO.save(cliente);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
            return false;
        }
    }

    // Método para buscar cliente por ID
    public Cliente buscarClientePorId(int id) {
        try {
            return clienteDAO.findById(id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
            return null;
        }
    }

    // Método para atualizar informações do cliente
    public boolean atualizarCliente(Cliente cliente) {
        try {
            clienteDAO.update(cliente);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // Método para deletar cliente por ID
    public boolean deletarCliente(int id) {
        try {
            clienteDAO.delete(id);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao deletar cliente: " + e.getMessage());
            return false;
        }
    }
}
