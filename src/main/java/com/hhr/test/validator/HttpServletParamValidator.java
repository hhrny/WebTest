package com.hhr.test.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class HttpServletParamValidator implements Validation {

    private static final Logger logger = LoggerFactory.getLogger(HttpServletParamValidator.class);

    @Override
    public void validate(HttpServletRequest request) throws ValidationException {

    }
}
