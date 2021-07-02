package com.example.validator.process;

import com.example.validator.entity.PasswordValidator;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ValidationEngine {

    private String password;
    private PasswordValidate passwordValidate;

    public ValidationEngine(String password) {
        this.password = password;
        passwordValidate = new PasswordValidate(password);
    }

    public boolean validatePassword() {

        if (!feature1Check()) {
            return false;
        }

        return feature2Check();
    }

    public boolean feature1Check() {

        return passwordValidate.lowerCaseLetterCheck();

    }

    public boolean feature2Check() {

        passwordValidate.nullCheck();
        passwordValidate.lengthCheck();

        if (passwordValidate.getCheckCount() >= 3) return true;
        passwordValidate.isLowerCaseConditionFlag();
        if (passwordValidate.getCheckCount() >= 3) return true;

        passwordValidate.isNumberCheckFlag();
        if (passwordValidate.getCheckCount() >= 3) return true;

        return false;
    }

    public static void main(String[] args) {

        PasswordValidate pswdValidate = new PasswordValidate();


    }
}
