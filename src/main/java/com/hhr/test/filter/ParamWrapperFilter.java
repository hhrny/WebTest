package com.hhr.test.filter;

import com.hhr.test.filter.wrapper.RequestWrapper;
import com.hhr.test.filter.wrapper.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
@WebFilter(urlPatterns = "/*",filterName="ParamWrapperFilter")
public class ParamWrapperFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ParamWrapperFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestWrapper request = new RequestWrapper((HttpServletRequest) servletRequest);
        ResponseWrapper response = new ResponseWrapper((HttpServletResponse) servletResponse);

        // log interface information of request
        logger.info("request|uri:"+request.getUri()+"|body:"+request.getBody()+"|");

        // filter chain do filter
        filterChain.doFilter(request, response);

        // log interface information of response
        logger.info("response|status:"+response.getStatus()+"|body:"+response.getBody()+"|");
    }

    @Override
    public void destroy() {

    }
}
