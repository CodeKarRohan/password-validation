package com.example.validator.entity;

import com.example.validator.exception.PasswordValidationException;

import java.util.concurrent.ExecutionException;

public interface PasswordValidator {

    boolean feature1Test() throws PasswordValidationException;

    boolean feature2Test() throws ExecutionException, InterruptedException, PasswordValidationException;
}
