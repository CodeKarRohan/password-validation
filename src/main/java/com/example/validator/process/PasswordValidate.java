package com.example.validator.process;

import com.example.validator.entity.PasswordValidator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordValidate implements PasswordValidator {

    private  static final int MIN_LENGTH = 8;
    private boolean lenCheckFlag;
    private boolean nullFlag;
    private boolean upperCaseConditionFlag;
    private boolean lowerCaseConditionFlag;
    private boolean numberCheckFlag;
    private String password;
    private int checkCount =0;



    public  PasswordValidate(String password){
        this.password = password;
    }

    public void incrementCount(){
        this.checkCount +=1;
    }

    @Override
    public boolean lengthCheck() {

        lenCheckFlag = password.length() > MIN_LENGTH;

        if(lenCheckFlag) checkCount += 1;
        return  lenCheckFlag;
    }

    @Override
    public boolean nullCheck() {
        nullFlag = password != null;
        if (nullFlag) checkCount += 1;
        return nullFlag;
    }

    @Override
    public boolean uppercaseLetterCheck() {
        upperCaseConditionFlag = false;
        char[] c = password.toCharArray();

        for(int i =0; i <c.length; i++) {

            if (Character.isUpperCase(c[i])) {
                upperCaseConditionFlag =  true;
                checkCount += 1;
                break;
            }
        }
        return  upperCaseConditionFlag;
    }

    @Override
    public boolean lowerCaseLetterCheck() {
        lowerCaseConditionFlag = false;
        char[] c = password.toCharArray();

       for(int i =0; i <c.length; i++) {

            if (Character.isUpperCase(c[i])) {
                lowerCaseConditionFlag =  true;
                checkCount += 1;
                break;
             }
        }
        return  lowerCaseConditionFlag;
    }

    @Override
    public boolean numberCheck() {
        numberCheckFlag = false;
        char[] c = password.toCharArray();

        for(int i =0; i <c.length; i++) {

            if (Character.isDigit(c[i])) {
                numberCheckFlag =  true;
                checkCount += 1;
                break;
            }
        }
        return  numberCheckFlag;
    }
}
