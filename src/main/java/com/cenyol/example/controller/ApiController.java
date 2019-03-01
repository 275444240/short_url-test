package com.cenyol.example.controller;

import com.cenyol.example.model.UrlInfo;
import com.cenyol.example.repository.UrlDao;
import com.cenyol.example.utils.ShortUrlGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuan.luo
 * @date 2019/2/28 0028
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
    UrlDao urlDao;
    /**
     * 添加短链接的页面跳转
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //指定视图
        return "addUrl";
    }

    /**
     * 自定义或者自动生成短链接
     * @param request
     * @param url
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addurlPost", method = RequestMethod.POST)
    public String addurlPost(HttpServletRequest request, UrlInfo url, HttpServletResponse response) throws IOException {
        //指定视图
        String shortUrl = url.getShortUrl();
        boolean blank = StringUtils.isBlank(shortUrl);
        if (blank) {
            urlLongToShort(url.getLongUrl());
        } else {
            userDefineUrlLongToShort(url.getLongUrl(), url.getShortUrl());
        }
        return "addUrl";
    }


    public String urlLongToShort(String url) throws IOException {
        //得到url
        String[] shortUrl = ShortUrlGenerator.getShortUrl(url);
        //转化url，存储
        insertDb(shortUrl,url);
        return "success";
    }

    private void insertDb(String[] shortUrl,String url) {
        UrlInfo urlInfo=new UrlInfo();
        urlInfo.setLongUrl(url);
        urlInfo.setShortUrl(shortUrl[1]);
        urlDao.insert(urlInfo);
    }


    public String userDefineUrlLongToShort(String originUrl, String targetUrl) throws IOException {
        //得到url
        String[] strings = ShortUrlGenerator.getuserDefineShortUrl(originUrl, targetUrl);
        //转化url，存储
        insertDb(strings,originUrl);
        return "success";
    }

}
