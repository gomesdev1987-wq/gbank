package tech.gmesdev87.gbank.exception;

public class EmailDuplicadoException extends RuntimeException {
    public EmailDuplicadoException(String email) {
        super("Já existe um usuário cadastrado com o e-mail: " + email);
    }
}
