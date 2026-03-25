package br.edu.ifba.blogapp.exceptions;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("A senha atual informada nao confere");
    }
}
