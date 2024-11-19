package br.com.gpiagentini.med.voll.api.domain.appointment;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
