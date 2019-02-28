package com.cenyol.example.controller;

import com.cenyol.example.utils.ShortUrlGenerator;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/api")
public class ApiController {

    //转化
    @RequestMapping(value = "/urlLongToShort", method = RequestMethod.GET)
    @ResponseBody
    public String urlLongToShort(String url, HttpServletResponse response) throws IOException {
        //得到url
        String[] shortUrl = ShortUrlGenerator.getShortUrl(url);
        //转化url，存储
        insertDb(shortUrl);
        return "success";
    }

    private void insertDb(String[] shortUrl) {

    }

    //用户自定义转化
    @RequestMapping(value = "/userDefineUrlLongToShort", method = RequestMethod.GET)
    @ResponseBody
    public String userDefineUrlLongToShort(String originUrl, String targetUrl, HttpServletResponse response) throws IOException {
        //得到url
        String[] strings = ShortUrlGenerator.getuserDefineShortUrl(originUrl, targetUrl);
        //转化url，存储

        return "success";
    }

}
