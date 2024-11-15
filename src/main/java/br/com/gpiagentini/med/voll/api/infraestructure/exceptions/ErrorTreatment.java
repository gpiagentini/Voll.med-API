package br.com.gpiagentini.med.voll.api.infraestructure.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorTreatment {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404Treatment() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400Treatment(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors().stream().map(ValidatioErrorData::new);
        return ResponseEntity.badRequest().body(errors);
    }

    private record ValidatioErrorData(String field, String message){
        public ValidatioErrorData(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

}
