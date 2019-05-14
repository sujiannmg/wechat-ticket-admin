package com.fuwenping.bysj.springmvc.restful;

import com.fuwenping.bysj.commons.util.HttpClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 该类是系统帐号控制器。
 *
 * @author 付文萍
 * @version 0.0.-RELEASE
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = { "one" }, method = RequestMethod.GET)
    public String get123() {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        System.out.println("返回结果" + httpClientUtil.get("https://m.maoyan.com/ajax/comingList?token=&limit=10"));
        return httpClientUtil.get("https://m.maoyan.com/ajax/comingList?token=&limit=10");
    }
}
