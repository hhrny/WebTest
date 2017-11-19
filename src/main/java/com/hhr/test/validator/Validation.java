package com.hhr.test.validator;

import javax.servlet.http.HttpServletRequest;

public interface Validation {
    void validate(HttpServletRequest request) throws ValidationException;
}
