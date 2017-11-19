package com.hhr.test.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class MsgUtils {

    private static final Logger logger = LoggerFactory.getLogger(MsgUtils.class);

    public static HttpServletResponse constructResponse(HttpServletResponse response, int status, String msg)
    {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (StringUtils.isNotBlank(msg))
        {
            try {
                response.getOutputStream().write(msg.getBytes(Charset.forName("UTF-8")));
            } catch (IOException e) {
                logger.error("output stream to response body failed, " + e.toString());
            }
        }
        return response;
    }

    public static String constructErrorString(String msg, List<String> params)
    {
        String body = msg;
        if (params != null) {
            for (String param : params) {
                body = body.replaceFirst("#", param);
            }
        }
        return body;
    }
}
