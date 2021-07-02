package com.example.validator.process;

import java.util.concurrent.ExecutionException;

public class ValidationEngine {

    private String password;
    private static final int MIN_LENGTH = 8;
    private PasswordValidate passwordValidate;

    public ValidationEngine(String password) {
        this.password = password;
        passwordValidate = new PasswordValidate(password);
    }

    public boolean validatePassword() throws ExecutionException, InterruptedException {
        return  passwordValidate.feature1Test() && passwordValidate.feature2Test();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ValidationEngine vengine = new ValidationEngine("W1WWeWW");
        System.out.println(vengine.validatePassword());

    }
}
