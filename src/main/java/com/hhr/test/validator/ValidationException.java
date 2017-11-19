package com.hhr.test.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends Throwable {

    private static final Logger logger = LoggerFactory.getLogger(ValidationException.class);

    private int status;

    private String msg;

    private List<String> params = new ArrayList<String>();

    public ValidationException() {
    }

    public ValidationException(String name) {
        super(name);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void addParam(String param)
    {
        this.params.add(param);
    }

    public List<String> getParams()
    {
        return this.params;
    }
}