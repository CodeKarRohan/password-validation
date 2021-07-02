package com.example.validator.process;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TU_ValidationEngine {

    ValidationEngine valEngne;

    @Test
    public void testValidPassword() {
        System.out.println("TEST 1. -------------TEST FOR VALID CONDITION----------------");
        //this test check for a valid password
        valEngne = new ValidationEngine("Wi12345345");
        boolean res = valEngne.validatePassword();
        Assert.assertTrue(res);
    }

    @Test
    public void testPasswordWithoutLowerCaseLetter() {
        System.out.println("TEST 2. -------------TEST FOR NO LOWER CASE----------------");
        //this test check for a valid password
        valEngne = new ValidationEngine("WWW111EEE");
        boolean res = valEngne.validatePassword();
        Assert.assertFalse(res);
    }

    @Test
    public void testPasswordNull() {
        System.out.println("TEST 3. -------------TEST FOR NULL CONDITION----------------");
        //this test check for a valid password
        valEngne = new ValidationEngine(null);
        boolean res = valEngne.validatePassword();
        Assert.assertFalse(res);
    }

    @Test
    public void testPasswordWithOnlyLowerCaseLetter() {
        System.out.println("TEST 4. -------------TEST FOR ONLY LOWER CASE LETTER----------------");
        //this test check for a valid password
        valEngne = new ValidationEngine("wwwwwwwddsdwwwww");
        boolean res = valEngne.validatePassword();
        Assert.assertFalse(res);
    }

    @Test
    public void withThreeConditionSatisfied() {
        System.out.println("TEST 5. -------------TEST FOR THREE CONDITION SATISFIED----------------");
        valEngne = new ValidationEngine("w1W");
        boolean res = valEngne.validatePassword();
        Assert.assertTrue(res);
    }


    @Test
    public void withLengthAndLowerCaseAndDigit() {
        System.out.println("TEST 6. -------------TEST FOR LENGTH, LOWER CASE, DIGIT----------------");
        valEngne = new ValidationEngine("wwewwwww1");
        boolean res = valEngne.validatePassword();
        Assert.assertTrue(res);
    }

    @Test
    public void withJustLengthCheckSatisfied() {
        System.out.println("TEST 7. -------------TEST FOR JUST LENGTH CHECK----------------");
        valEngne = new ValidationEngine("wwwwwwwww");
        boolean res = valEngne.validatePassword();
        Assert.assertFalse(res);
    }

    @Test
    public void withOnlyDigits() {
        System.out.println("TEST 8. -------------TEST FOR ONLY DIGIT ---------------");
        valEngne = new ValidationEngine("111111111111");
        boolean res = valEngne.validatePassword();
        Assert.assertFalse(res);
    }

    @Test
    public void withAllLowerCase() {
        System.out.println("TEST 9. -------------TEST FOR ALL LOWER CASE only----------------");
        valEngne = new ValidationEngine("eeeeeeeeeee");
        boolean res = valEngne.validatePassword();
        Assert.assertFalse(res);
    }

    @Test
    public void withAllUpperCase() {
        System.out.println("TEST 10 -------------TEST FOR ALL UPPER CASE ONLY ----------------");
        valEngne = new ValidationEngine("WWWWWWWWWWWW");
        boolean res = valEngne.validatePassword();
        Assert.assertFalse(res);
    }


}
