package br.edu.ifba.blogapp.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super("Recuro não encontrado com id: " + id);
    }

}
