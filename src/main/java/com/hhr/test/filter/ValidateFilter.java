package com.hhr.test.filter;

import com.hhr.test.utils.MsgUtils;
import com.hhr.test.validator.URLValidator;
import com.hhr.test.validator.Validation;
import com.hhr.test.validator.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Order(2)
@WebFilter(urlPatterns = "/*",filterName="ValidateFilter")
public class ValidateFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ValidateFilter.class);

    Validation validation = new URLValidator();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        try {
            validation.validate(request);
        } catch (ValidationException e) {
            logger.error("validation failed!");
            MsgUtils.constructResponse(response, e.getStatus(), e.getMsg());
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
