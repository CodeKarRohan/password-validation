package com.example.validator.entity;

public interface PasswordValidator {

    boolean lengthCheck();
    boolean nullCheck();
    boolean  uppercaseLetterCheck();
    boolean lowerCaseLetterCheck();
    boolean  numberCheck();
}
