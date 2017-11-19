package com.hhr.test.controller;

import com.alibaba.fastjson.JSON;
import com.hhr.test.controller.bean.User;
import com.hhr.test.controller.request.LoginRequest;
import com.hhr.test.utils.Messages;
import com.hhr.test.utils.MsgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class Login {

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    private Map<String, User> userMap = new HashMap<String, User>();

    @RequestMapping(path="/login/{userId}", method=RequestMethod.POST, headers="Content-type=application/json")
    public void login(HttpServletRequest request,
                      HttpServletResponse response,
                      @PathVariable("userId") String userId,
                      @RequestBody LoginRequest loginRequest) {
        logger.info("user login|userId:"+userId+"|passwd:"+loginRequest.getPasswd()+"|");
        if (userMap.containsKey(userId))
        {
            User user = userMap.get(userId);
            if (user.getUserId().equals(userId) && user.getPasswd().equals(loginRequest.getPasswd()))
            {
                MsgUtils.constructResponse(response, HttpServletResponse.SC_OK, Messages.SUCCESS);
            }
            else
            {
                MsgUtils.constructResponse(response, HttpServletResponse.SC_FORBIDDEN, Messages.LOGIN_FAILED);
            }
        }
        else
        {
            List<String> paramList = new ArrayList<String>();
            paramList.add(userId);
            MsgUtils.constructResponse(response, HttpServletResponse.SC_FORBIDDEN, MsgUtils.constructErrorString(Messages.OBJECT_NOT_EXIST, paramList));
        }

    }

    @RequestMapping(path="/register/{userId}", method=RequestMethod.POST)
    public void register(HttpServletRequest request,
                         HttpServletResponse response,
                         @PathVariable("userId") String userId,
                         @RequestBody LoginRequest loginRequest)
    {
        logger.info("user login|userId:"+userId+"|passwd:"+loginRequest.getPasswd()+"|");
        if (userMap.containsKey(userId))
        {
            List<String> paramList = new ArrayList<String>();
            paramList.add(userId);
            MsgUtils.constructResponse(response, 400, MsgUtils.constructErrorString(Messages.OBJECT_EXIST, paramList));
        }
        else
        {
            User user = new User();
            user.setUserId(userId);
            user.setPasswd(loginRequest.getPasswd());
            userMap.put(userId, user);
            MsgUtils.constructResponse(response, HttpServletResponse.SC_CREATED, Messages.SUCCESS);
        }
    }

    @RequestMapping(path="/users", method = RequestMethod.GET)
    public void queryUsers(HttpServletRequest request, HttpServletResponse response)
    {
        List<User> userList = new ArrayList<User>();
        for (User user : userMap.values())
        {
            userList.add(user);
        }
        MsgUtils.constructResponse(response, HttpServletResponse.SC_OK, JSON.toJSONString(userList));
    }
}
