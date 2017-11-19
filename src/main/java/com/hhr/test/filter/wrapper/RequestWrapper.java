package com.hhr.test.filter.wrapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class RequestWrapper extends HttpServletRequestWrapper{

    private static final Logger logger = LoggerFactory.getLogger(RequestWrapper.class);

    private String body;

    private String uri;

    /**
     * construct RequestWrapper, init uri and body
     * uri = uri + ? + query string
     * @param request
     */
    public RequestWrapper(HttpServletRequest request) {
        super(request);
        uri = request.getRequestURI();
        String queryStr = request.getQueryString();
        if (StringUtils.isNotBlank(queryStr))
        {
            uri = uri + "?" + queryStr;
        }
        try {
            InputStream inputStream = request.getInputStream();
            byte[] buf = new byte[128];
            StringBuffer sb = new StringBuffer();
            while(inputStream.read(buf,0,128) > 0)
            {
                sb.append(new String(buf, Charset.forName("UTF-8")));
            }
            body = sb.toString();
        } catch (IOException e) {
            logger.error("read http servlet request body failed, "+e.toString());
            body="";
        }
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public ServletInputStream getInputStream()
    {
        return new WrapperServletInputStream(body.getBytes(Charset.forName("UTF-8")));
    }
}
