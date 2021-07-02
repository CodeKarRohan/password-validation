package com.example.validator.process;

import com.example.validator.exception.PasswordValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidationEngine {
    private static final Logger LOG = LogManager.getLogger();
    private String password;
    private static final int MIN_LENGTH = 8;
    private PasswordValidate passwordValidate;

    public ValidationEngine(String password) {
        this.password = password;
        passwordValidate = new PasswordValidate(password);
    }

    public boolean validatePassword() {

        if (password == null) {
            return false;
        }
        try {
            return passwordValidate.feature1Test() && passwordValidate.feature2Test();
        } catch (PasswordValidationException p1) {

            LOG.error("Exception while validating password "+p1.getMessage());

        } catch (Exception e) {
            LOG.error("Exception while validating password "+e.getMessage());
        }
        return false;
    }

}
