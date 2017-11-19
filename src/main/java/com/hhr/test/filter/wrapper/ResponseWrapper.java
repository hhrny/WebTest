package com.hhr.test.filter.wrapper;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

public class ResponseWrapper extends HttpServletResponseWrapper {

    private static final Logger logger = LoggerFactory.getLogger(ResponseWrapper.class);

    private StringBuffer body = new StringBuffer();

    private ByteArrayOutputStream bodyByteOutputStream = new ByteArrayOutputStream();

    public ServletOutputStream outputStream = new WrapperServletOutputStream(this);

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public String getBody() {
        String body = "";
        byte[] tmp = bodyByteOutputStream.toByteArray();
        if (ArrayUtils.isNotEmpty(tmp))
        {
            body = new String(tmp, Charset.forName("UTF-8"));
        }
        return body;
    }

    public ByteArrayOutputStream getBodyOutputStream()
    {
        return bodyByteOutputStream;
    }

    @Override
    public ServletOutputStream getOutputStream()
    {
        return outputStream;
    }
}
