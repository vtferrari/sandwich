package br.com.vtferrari.sandwich.handler.exception;

public class CannotConvertMessageToObjectException extends RuntimeException {
    public CannotConvertMessageToObjectException(String message, Throwable e) {
        super(message, e);
    }
}
