package com.fuwenping.bysj.commons.util;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 该类HttpClients发送请求的工具类
 *
 * @author 付文萍
 * @version 0.0.-RELEASE
 */
public class HttpClientUtil {

  /**
   * get请求，参数拼接在地址上
   *
   * @param url 请求地址加参数
   * @return 响应
   */
  public String httpGet(String url) {
    // 声明get请求返回结果
    String result = null;
    // 声明get请求状态
    int responseCode = 0;
    // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    RequestConfig config = RequestConfig.custom()
      .setConnectTimeout(60000)
      .setConnectionRequestTimeout(60000)
      .setSocketTimeout(60000)
      .build();
    // 创建Get请求
    HttpGet get = new HttpGet(url);
    get.setConfig(config);
    // 响应模型
    CloseableHttpResponse response = null;
    try {
      // 由客户端执行(发送)Get请求
      response = httpClient.execute(get);
      responseCode = response.getStatusLine().getStatusCode();
      System.out.println("响应状态为:" + responseCode);
      if (response != null && response.getStatusLine().getStatusCode() == 200) {
        // 从响应模型中获取响应实体
        HttpEntity entity = response.getEntity();
        System.out.println("响应内容长度为:" + entity.getContentLength());
        // System.out.println("响应内容为:" + EntityUtils.toString(entity));
        result = EntityUtils.toString(entity);
      }
      return result;
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        // 释放资源
        if (httpClient != null) {
          httpClient.close();
        }
        if (response != null) {
          response.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
