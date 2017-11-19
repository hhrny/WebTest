package com.hhr.test.filter.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;

public class WrapperServletOutputStream extends ServletOutputStream {

    private ResponseWrapper response;

    public WrapperServletOutputStream(ResponseWrapper response)
    {
        this.response = response;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    @Override
    public void write(int b) throws IOException {
        response.getBodyOutputStream().write(b);
        response.getResponse().getOutputStream().write(b);
    }
}
