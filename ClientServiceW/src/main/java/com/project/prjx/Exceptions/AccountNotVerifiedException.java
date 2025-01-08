package com.project.prjx.Exceptions;

public class AccountNotVerifiedException extends RuntimeException {
    public AccountNotVerifiedException(String message) {
        super(message);
    }

    public AccountNotVerifiedException() {
        super("Account is not verified, please verify at least one email or phone number");
    }
}
