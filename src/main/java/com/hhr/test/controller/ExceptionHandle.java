package com.hhr.test.controller;

import com.hhr.test.utils.Messages;
import com.hhr.test.utils.MsgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    public void handler(HttpServletRequest request, HttpServletResponse response, Exception e){
        logger.error("Exception Handle: "+e.toString());
        MsgUtils.constructResponse(response, HttpServletResponse.SC_SERVICE_UNAVAILABLE, Messages.INTER_ERROR);
    }
}
