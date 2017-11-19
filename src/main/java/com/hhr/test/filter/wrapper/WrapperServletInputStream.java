package com.hhr.test.filter.wrapper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;

public class WrapperServletInputStream extends ServletInputStream {

    private byte[] body;
    private int pointer;


    public WrapperServletInputStream(byte[] body)
    {
        this.body = body;
        this.pointer = 0;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }

    @Override
    public int read() throws IOException {
        if (pointer < body.length)
        {
            return body[pointer++];
        }
        else
        {
            pointer = 0;
            return -1;
        }
    }
}
