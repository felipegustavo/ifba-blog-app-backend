package br.edu.ifba.blogapp.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super(String.format("Recuro não encontrado com id: %d", id));
    }

}
