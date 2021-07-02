package com.example.validator.process;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ValidationEngine {

    private String password;
    private PasswordValidate passwordValidate;
    private static final int MIN_LENGTH = 8;

    public ValidationEngine(String password) {
        this.password = password;
        passwordValidate = new PasswordValidate(password);
    }

    public boolean validatePassword() throws ExecutionException, InterruptedException {

        if(null == password){
            return false;
        }

        if (!feature1Check()) {
            return false;
        }

        return featureTwoCheck();
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

    public boolean featureTwoCheck() throws ExecutionException, InterruptedException {

        ExecutorService exec = Executors.newFixedThreadPool(5);

        Callable<Boolean> nullCheckTask = () -> {
            return password != null;
        };

        Future<Boolean> nullCheck = exec.submit(nullCheckTask);

        Callable<Boolean> lengthCheckTask = () -> {
            return password.length() > MIN_LENGTH;
        };

        Future<Boolean> lenCheck = exec.submit(lengthCheckTask);

        boolean isNotNull = nullCheck.get();
        boolean hasValidLength = lenCheck.get();

        if (isNotNull && hasValidLength) {
            return true;
        }

        Future<List<Integer>> upperCaseAndDigCheck;
        Callable<List<Integer>> upperCaseAndDigTask = () -> {
            return this.validateUcaseAndDigit();
        };

        upperCaseAndDigCheck = exec.submit(upperCaseAndDigTask);

        int validScenario = 0;

        if (isNotNull) validScenario++;
        if (hasValidLength) validScenario++;

        List<Integer> res = upperCaseAndDigCheck.get();

        exec.shutdown();
        while (!exec.isTerminated()) ;

        if (res == null || res.size() == 0){
            return false;
        }

       if(validScenario + res.size() >=3){
           return true;
       }else{
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
                lis.add(uCase);
            }
            if (Character.isDigit(c[i])) {
                digit++;
                lis.add(digit);
            }
            if (uCase > 0 || digit > 0) {
                break;
            }

        }

        return lis;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ValidationEngine vengine = new ValidationEngine("We");
        System.out.println(vengine.validatePassword());

    }
}
