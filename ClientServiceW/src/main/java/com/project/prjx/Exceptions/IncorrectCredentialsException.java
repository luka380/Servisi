package com.project.prjx.Exceptions;

public class IncorrectCredentialsException extends RuntimeException {
    public IncorrectCredentialsException(String message) {
        super(message);
    }

    public IncorrectCredentialsException() {
        super("Incorrect credentials");
    }
}

