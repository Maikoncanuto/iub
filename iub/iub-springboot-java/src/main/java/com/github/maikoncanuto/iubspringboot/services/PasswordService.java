package com.github.maikoncanuto.iubspringboot.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.FALSE;
import static java.util.Objects.isNull;

@Service
public class PasswordService {

    @Value("${regex-password}")
    private String regexPassword;

    public Boolean validatePassword(final String password) {

        if (isNull(password))
            return FALSE;

        return password.matches(regexPassword);
    }

}
