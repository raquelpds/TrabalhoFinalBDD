package controller;

import dao.ContaDAO;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

import java.util.List;

public class ContaController {

    //CONSULTAR POR NUMERO DA CONTA
    public Conta consultarConta(int numeroConta) {
        try {
            return ContaDAO.consultarConta(numeroConta);
        } catch (Exception e) {
            System.err.println("Erro ao consultar a conta: " + e.getMessage());
            return null;
        }
    }

    //CONSULTAR TODAS AS CONTAS CRIADAS
    public List<Conta> consultarTodasContas() {
        try {
            return ContaDAO.consultarTodasContas();
        } catch (Exception e) {
            System.err.println("Erro ao consultar todas as contas: " + e.getMessage());
            return null;
        }
    }

    //DELETE
    public boolean removerConta(Conta conta) {
        try {
            return ContaDAO.remover(conta);
        } catch (Exception e) {
            System.err.println("Erro ao remover a conta: " + e.getMessage());
            return false;
        }
    }

    //INSERT
    public boolean inserirConta(Conta conta) {
        try {
            if (conta instanceof ContaCorrente) {
                // Validar dados específicos para ContaCorrente
                ContaCorrente cc = (ContaCorrente) conta;
                if (cc.consultarLimite() <= 0) {
                    System.err.println("Erro: O limite da conta corrente deve ser maior que zero.");
                    return false;
                }
            } else if (conta instanceof ContaPoupanca) {
                // Validar dados específicos para ContaPoupanca
                ContaPoupanca cp = (ContaPoupanca) conta;
                if (cp.calcularRendimento() <= 0) {
                    System.err.println("Erro: A taxa de rendimento da conta poupança deve ser maior que zero.");
                    return false;
                }
            }

            return ContaDAO.inserir(conta);
        } catch (Exception e) {
            System.err.println("Erro ao inserir a conta: " + e.getMessage());
            return false;
        }
    }
}
