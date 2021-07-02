package com.example.validator.process;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TU_ValidationEngine {

    ValidationEngine valEngne ;

    @Test
    public void testValidPassword(){
        //this test check for a valid password
        valEngne = new ValidationEngine("Wi12345345");
        boolean res = valEngne.validatePassword();
        Assert.assertTrue(res);
    }

    @Test
    public void testPasswordWithoutLowerCaseLetter(){
        //this test check for a valid password
        valEngne = new ValidationEngine("WWW111EEE");
        boolean res = valEngne.validatePassword();
        Assert.assertFalse(res);
    }

    @Test
    public void testPasswordNull(){
        //this test check for a valid password
        valEngne = new ValidationEngine(null);
        boolean res = valEngne.validatePassword();
        Assert.assertFalse(res);
    }

    @Test
    public void testPasswordWithOnlyLowerCaseLetter(){
        //this test check for a valid password
        valEngne = new ValidationEngine("wwwwwwwddsdwwwww");
        boolean res = valEngne.validatePassword();
        Assert.assertFalse(res);
    }

    @Test
    public void withThreeConditionSatisfied(){
        valEngne = new ValidationEngine("w1W");
        boolean res = valEngne.validatePassword();
        Assert.assertTrue(res);
    }


    @Test
    public void withLengthAndLowerCaseAndDigit(){
        valEngne = new ValidationEngine("wwewwwww1");
        boolean res = valEngne.validatePassword();
        Assert.assertTrue(res);
    }

    @Test
    public void withJustLengthCheckSatisfied(){
        valEngne = new ValidationEngine("wwwwwwwww");
        boolean res = valEngne.validatePassword();
        Assert.assertFalse(res);
    }

}
