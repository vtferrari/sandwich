package br.com.vtferrari.sandwich.usecase.exception;

public class CannotConvertObjectToMessageException extends RuntimeException {
    public CannotConvertObjectToMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
