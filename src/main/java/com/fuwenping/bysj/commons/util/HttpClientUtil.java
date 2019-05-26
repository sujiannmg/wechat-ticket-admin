package com.fuwenping.bysj.commons.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

  public static String doGet(String url, Map<String, String> param) {

    // 创建Httpclient对象
    CloseableHttpClient httpclient = HttpClients.createDefault();

    String resultString = "";
    CloseableHttpResponse response = null;
    try {
      // 创建uri
      URIBuilder builder = new URIBuilder(url);
      if (param != null) {
        for (String key : param.keySet()) {
          builder.addParameter(key, param.get(key));
        }
      }
      URI uri = builder.build();

      // 创建http GET请求
      HttpGet httpGet = new HttpGet(uri);

      // 执行请求
      response = httpclient.execute(httpGet);
      // 判断返回状态是否为200
      if (response.getStatusLine().getStatusCode() == 200) {
        resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (response != null) {
          response.close();
        }
        httpclient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return resultString;
  }

  public static String doGet(String url) {
    return doGet(url, null);
  }

  public static String doPost(String url, Map<String, String> param) {
    // 创建Httpclient对象
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    String resultString = "";
    try {
      // 创建Http Post请求
      HttpPost httpPost = new HttpPost(url);
      // 创建参数列表
      if (param != null) {
        List<NameValuePair> paramList = new ArrayList<>();
        for (String key : param.keySet()) {
          paramList.add(new BasicNameValuePair(key, param.get(key)));
        }
        // 模拟表单
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
        httpPost.setEntity(entity);
      }
      // 执行http请求
      response = httpClient.execute(httpPost);
      resultString = EntityUtils.toString(response.getEntity(), "utf-8");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        response.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return resultString;
  }

  public static String doPost(String url) {
    return doPost(url, null);
  }

  public static String doPostJson(String url, String json) {
    // 创建Httpclient对象
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    String resultString = "";
    try {
      // 创建Http Post请求
      HttpPost httpPost = new HttpPost(url);
      // 创建请求内容
      StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
      httpPost.setEntity(entity);
      // 执行http请求
      response = httpClient.execute(httpPost);
      resultString = EntityUtils.toString(response.getEntity(), "utf-8");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        response.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return resultString;
  }
}
