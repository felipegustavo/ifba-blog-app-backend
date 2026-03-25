package br.edu.ifba.blogapp.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.ifba.blogapp.domain.dto.ErrorResponse;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler({ ResourceNotFoundException.class, UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        var response = new ErrorResponse("RECURSO NÃO ENCONTRADO", ex.getMessage(), null);
        
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException ex) {
        var response = new ErrorResponse("EMAIL DUPLICADO", ex.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException ex) {
        var response = new ErrorResponse("SENHA INVALIDA", ex.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handleObjectOptimisticLockingFailureException(ObjectOptimisticLockingFailureException ex) {
        var id = ex.getIdentifier();
        var response = new ErrorResponse("RECURSO NÃO ENCONTRADO", "Não é possível excluir ou editar recurso com id: " + id, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->  {
            List<String> errorMessages = errors.get(error.getField());
            if (errorMessages == null) {
                errorMessages = new ArrayList<>();
                errors.put(error.getField(), errorMessages);
            }
            errorMessages.add(error.getDefaultMessage());
        });

        var response = new ErrorResponse("DADOS INVÁLIDOS", "Dados enviados contém erros", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
