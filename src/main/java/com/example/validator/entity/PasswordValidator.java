package com.example.validator.entity;

import java.util.concurrent.ExecutionException;

public interface PasswordValidator {

   boolean feature1Test();
   boolean feature2Test() throws ExecutionException, InterruptedException;
}
