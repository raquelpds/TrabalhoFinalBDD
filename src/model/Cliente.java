package model;

import java.time.LocalDate;
import java.util.Locale;

abstract class Cliente {
    //atributos:
    int id;
    String nome, cpf, telefone, endereco;
    LocalDate dataNascimento;
    //métodos
     abstract boolean login(String senha);
     abstract void logout();
     abstract String consultarDados();

}
