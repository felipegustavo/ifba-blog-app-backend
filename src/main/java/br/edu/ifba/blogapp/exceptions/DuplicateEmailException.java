package br.edu.ifba.blogapp.exceptions;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super(String.format("Ja existe um usuario cadastrado com o email: %s", email));
    }

}
