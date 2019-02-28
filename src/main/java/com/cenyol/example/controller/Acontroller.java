package com.cenyol.example.controller;

import com.cenyol.example.utils.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuan.luo
 * @date 2019/2/28 0028
 */
@Controller
@RequestMapping("/a")
public class Acontroller {

    //跳转
    @RequestMapping(value = "/{url}", method = RequestMethod.GET)
    @ResponseBody
    public void url(@PathVariable String url, HttpServletResponse response) throws IOException {
        //得到url

        //统计次数,写redis
        RedisUtil redisUtil = new RedisUtil();
        //    SET page_view 20
        boolean url1 = redisUtil.exists("url");
        if (url1) {
            redisUtil.incr(url);
        } else {
            redisUtil.set(url, "0");
        }

        //重定向
        response.sendRedirect("/");
    }


}
