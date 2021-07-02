package com.example.validator.process;

import com.example.validator.entity.PasswordValidator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@Data
public class PasswordValidate implements PasswordValidator {

    private  static final int MIN_LENGTH = 8;
    private String password;

    public  PasswordValidate(String password){
        this.password = password;
    }

    @Override
    public boolean feature1Test() {
        char[] c = password.toCharArray();
        for (int i = 0; i < c.length; i++) {

            if (Character.isLowerCase(c[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean feature2Test() throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        Callable<Boolean> lengthCheckTask = () -> {
            return password.length() > MIN_LENGTH;
        };
        Future<Boolean> lenCheck = exec.submit(lengthCheckTask);
        // boolean isNotNull = nullCheck.get();
        boolean hasValidLength = lenCheck.get();
        Future<List<Integer>> upperCaseAndDigCheck;
        Callable<List<Integer>> upperCaseAndDigTask = () -> {
            return this.validateUcaseAndDigit();
        };
        upperCaseAndDigCheck = exec.submit(upperCaseAndDigTask);

        int validScenario = 1;
        if (hasValidLength) validScenario++;
        List<Integer> res = upperCaseAndDigCheck.get();
        exec.shutdown();
        while (!exec.isTerminated()) ;
        if (res == null || res.size() == 0) {
            return false;
        }

        if (validScenario + res.size() >= 3) {
            return true;
        } else {
            return false;
        }
    }

    public List<Integer> validateUcaseAndDigit() {
        int uCase = 0;
        int digit = 0;
        List<Integer> lis = new ArrayList<>();
        char[] c = password.toCharArray();

        for (int i = 0; i < c.length; i++) {

            if (Character.isUpperCase(c[i])) {
                uCase++;
                if (uCase == 1)
                    lis.add(uCase);
            }
            if (Character.isDigit(c[i])) {
                digit++;
                if (digit == 1)
                    lis.add(digit);
            }
            if (uCase > 0 && digit > 0) {
                break;
            }

        }
        return lis;
    }
}
