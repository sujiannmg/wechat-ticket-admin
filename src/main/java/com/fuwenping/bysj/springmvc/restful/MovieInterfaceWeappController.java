package com.fuwenping.bysj.springmvc.restful;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.commons.util.HttpClientUtil;
import com.fuwenping.bysj.entity.MovieInterfaceInfo;
import com.fuwenping.bysj.service.spring.IWechatTicketService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 该类是电影接口信息控制器。
 *
 * @author 付文萍
 * @version 0.0.-RELEASE
 */
@RestController // 该类注入有别于@Controller
@RequestMapping("/movieinterface")
public class MovieInterfaceWeappController extends BaseController {

  private static final Log log = LogFactory.getLog(MovieInterfaceWeappController.class);

  @javax.annotation.Resource(name = "WechatTicketService")
  private IWechatTicketService wechatTicketService;

  // 电影即将上映接口
  @RequestMapping(value = { "cominglist" }, method = RequestMethod.GET)
  public String getMovieCominglist() {
    // TODO 按照正常的controller进行传参，解决思路
    try {
      // 保存请求结果
      String saveResult = null;
      // 初始化请求
      HttpClientUtil httpClientUtil = new HttpClientUtil();
      MovieInterfaceInfo movieInterfaceInfo = new MovieInterfaceInfo();
      // 通过主键查询到第一次增加的数据对象
      MovieInterfaceInfo oldMovieInterfaceInfo = wechatTicketService.getMovieInterfaceInfoByPrimaryKey("ae795551-360e-4f6b-bfe5-5b09d4687be4");
      // 获取接口数据
      saveResult = httpClientUtil.httpGet("https://m.maoyan.com/ajax/comingList?token=&limit=10");
      if(!oldMovieInterfaceInfo.getMovieInterfaceId().equals(null)) {
        // 电影接口编号
        movieInterfaceInfo.setMovieInterfaceId("ae795551-360e-4f6b-bfe5-5b09d4687be4");
        // 电影接口名称
        movieInterfaceInfo.setMovieInterfaceName("即将上映电影列表");
        // 电影接口类型
        movieInterfaceInfo.setMovieInterfaceType("GET请求");
        // 电影接口地址
        movieInterfaceInfo.setMovieInterfaceUrl("https://m.maoyan.com/ajax/comingList?token=&limit=10");
        // 电影接口参数
        movieInterfaceInfo.setMovieInterfacePaprm("无参数");
        // 电影接口编码
        movieInterfaceInfo.setMovieInterfaceAscii("UTF-8");
        // 接口响应状态
        movieInterfaceInfo.setMovieInterfaceCode(200);
        // 响应内容长度
        movieInterfaceInfo.setMovieReponseLength(-1);
        // 业务版本
        movieInterfaceInfo.setVersion(oldMovieInterfaceInfo.getVersion());
        // 接口响应内容
        movieInterfaceInfo.setMovieinterfaceContent(saveResult);
        // 调用接口次数
        movieInterfaceInfo.setMovieReponseNum(oldMovieInterfaceInfo.getMovieReponseNum() + 1); // 查出原来数据+1操作。使用标志判断
        // 调用接口时间
        movieInterfaceInfo.setMovieReponseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        wechatTicketService.updateMovieInterfaceInfo(movieInterfaceInfo);
      } else {
        // 电影接口名称
        movieInterfaceInfo.setMovieInterfaceName("即将上映电影列表");
        // 电影接口类型
        movieInterfaceInfo.setMovieInterfaceType("GET请求");
        // 电影接口地址
        movieInterfaceInfo.setMovieInterfaceUrl("https://m.maoyan.com/ajax/comingList?token=&limit=10");
        // 电影接口参数
        movieInterfaceInfo.setMovieInterfacePaprm("无参数");
        // 电影接口编码
        movieInterfaceInfo.setMovieInterfaceAscii("UTF-8");
        // 接口响应状态
        movieInterfaceInfo.setMovieInterfaceCode(200);
        // 响应内容长度
        movieInterfaceInfo.setMovieReponseLength(-1);
        // 接口响应内容
        movieInterfaceInfo.setMovieinterfaceContent(saveResult);
        // 调用接口次数
        movieInterfaceInfo.setMovieReponseNum(1);
        // 调用接口时间
        movieInterfaceInfo.setMovieReponseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        wechatTicketService.saveMovieInterfaceInfo(movieInterfaceInfo);
      }
      return saveResult;
    } catch (WechatTicketException e) {
      e.printStackTrace();
      return null;
    }
  }

  // 电影最近最受欢迎接口
  @RequestMapping(value = { "mostexpected" }, method = RequestMethod.GET)
  public String getMovieMostExpected() {
    try {
      // 保存请求结果
      String saveResult = null;
      // 初始化请求
      HttpClientUtil httpClientUtil = new HttpClientUtil();
      MovieInterfaceInfo movieInterfaceInfo = new MovieInterfaceInfo();
      // 通过主键查询到第一次增加的数据对象
      MovieInterfaceInfo oldMovieInterfaceInfo = wechatTicketService.getMovieInterfaceInfoByPrimaryKey("977fba01-a2d1-4076-a37f-5f845594509d");
      // 获取接口数据
      saveResult = httpClientUtil.httpGet("https://m.maoyan.com/ajax/mostExpected?limit=10&offset=0&token=");
      if(!oldMovieInterfaceInfo.getMovieInterfaceId().equals(null)) {
        // 电影接口编号
        movieInterfaceInfo.setMovieInterfaceId("977fba01-a2d1-4076-a37f-5f845594509d");
        // 电影接口名称
        movieInterfaceInfo.setMovieInterfaceName("最近最受欢迎电影列表");
        // 电影接口类型
        movieInterfaceInfo.setMovieInterfaceType("GET请求");
        // 电影接口地址
        movieInterfaceInfo.setMovieInterfaceUrl("https://m.maoyan.com/ajax/mostExpected?limit=10&offset=0&token=");
        // 电影接口参数
        movieInterfaceInfo.setMovieInterfacePaprm("无参数");
        // 电影接口编码
        movieInterfaceInfo.setMovieInterfaceAscii("UTF-8");
        // 接口响应状态
        movieInterfaceInfo.setMovieInterfaceCode(200);
        // 响应内容长度
        movieInterfaceInfo.setMovieReponseLength(-1);
        // 业务版本
        movieInterfaceInfo.setVersion(oldMovieInterfaceInfo.getVersion());
        // 接口响应内容
        movieInterfaceInfo.setMovieinterfaceContent(saveResult);
        // 调用接口次数
        movieInterfaceInfo.setMovieReponseNum(oldMovieInterfaceInfo.getMovieReponseNum() + 1); // 查出原来数据+1操作。使用标志判断
        // 调用接口时间
        movieInterfaceInfo.setMovieReponseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        wechatTicketService.updateMovieInterfaceInfo(movieInterfaceInfo);
      } else {
        // 电影接口名称
        movieInterfaceInfo.setMovieInterfaceName("最近最受欢迎电影列表");
        // 电影接口类型
        movieInterfaceInfo.setMovieInterfaceType("GET请求");
        // 电影接口地址
        movieInterfaceInfo.setMovieInterfaceUrl("https://m.maoyan.com/ajax/mostExpected?limit=10&offset=0&token=");
        // 电影接口参数
        movieInterfaceInfo.setMovieInterfacePaprm("无参数");
        // 电影接口编码
        movieInterfaceInfo.setMovieInterfaceAscii("UTF-8");
        // 接口响应状态
        movieInterfaceInfo.setMovieInterfaceCode(200);
        // 响应内容长度
        movieInterfaceInfo.setMovieReponseLength(-1);
        // 接口响应内容
        movieInterfaceInfo.setMovieinterfaceContent(saveResult);
        // 调用接口次数
        movieInterfaceInfo.setMovieReponseNum(1);
        // 调用接口时间
        movieInterfaceInfo.setMovieReponseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        wechatTicketService.saveMovieInterfaceInfo(movieInterfaceInfo);
      }
      return saveResult;
    } catch (WechatTicketException e) {
      e.printStackTrace();
      return null;
    }
  }


  // 电影正在热映接口
  @RequestMapping(value = { "movieoninfolist" }, method = RequestMethod.GET)
  public String getMovieMovieOnInfoList() {
    try {
      // 保存请求结果
      String saveResult = null;
      // 初始化请求
      HttpClientUtil httpClientUtil = new HttpClientUtil();
      MovieInterfaceInfo movieInterfaceInfo = new MovieInterfaceInfo();
      // 通过主键查询到第一次增加的数据对象
      MovieInterfaceInfo oldMovieInterfaceInfo = wechatTicketService.getMovieInterfaceInfoByPrimaryKey("15aaa253-8b10-4554-8a6f-edc527869fd8");
      // 获取接口数据
      saveResult = httpClientUtil.httpGet("https://m.maoyan.com/ajax/movieOnInfoList?token=");
      if(!oldMovieInterfaceInfo.getMovieInterfaceId().equals(null)) {
        // 电影接口编号
        movieInterfaceInfo.setMovieInterfaceId("15aaa253-8b10-4554-8a6f-edc527869fd8");
        // 电影接口名称
        movieInterfaceInfo.setMovieInterfaceName("正在热映电影列表");
        // 电影接口类型
        movieInterfaceInfo.setMovieInterfaceType("GET请求");
        // 电影接口地址
        movieInterfaceInfo.setMovieInterfaceUrl("https://m.maoyan.com/ajax/movieOnInfoList?token=");
        // 电影接口参数
        movieInterfaceInfo.setMovieInterfacePaprm("无参数");
        // 电影接口编码
        movieInterfaceInfo.setMovieInterfaceAscii("UTF-8");
        // 接口响应状态
        movieInterfaceInfo.setMovieInterfaceCode(200);
        // 响应内容长度
        movieInterfaceInfo.setMovieReponseLength(-1);
        // 业务版本
        movieInterfaceInfo.setVersion(oldMovieInterfaceInfo.getVersion());
        // 接口响应内容
        movieInterfaceInfo.setMovieinterfaceContent(saveResult);
        // 调用接口次数
        movieInterfaceInfo.setMovieReponseNum(oldMovieInterfaceInfo.getMovieReponseNum() + 1); // 查出原来数据+1操作。使用标志判断
        // 调用接口时间
        movieInterfaceInfo.setMovieReponseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        wechatTicketService.updateMovieInterfaceInfo(movieInterfaceInfo);
      } else {
        // 电影接口名称
        movieInterfaceInfo.setMovieInterfaceName("正在热映电影列表");
        // 电影接口类型
        movieInterfaceInfo.setMovieInterfaceType("GET请求");
        // 电影接口地址
        movieInterfaceInfo.setMovieInterfaceUrl("https://m.maoyan.com/ajax/movieOnInfoList?token=");
        // 电影接口参数
        movieInterfaceInfo.setMovieInterfacePaprm("无参数");
        // 电影接口编码
        movieInterfaceInfo.setMovieInterfaceAscii("UTF-8");
        // 接口响应状态
        movieInterfaceInfo.setMovieInterfaceCode(200);
        // 响应内容长度
        movieInterfaceInfo.setMovieReponseLength(-1);
        // 接口响应内容
        movieInterfaceInfo.setMovieinterfaceContent(saveResult);
        // 调用接口次数
        movieInterfaceInfo.setMovieReponseNum(1);
        // 调用接口时间
        movieInterfaceInfo.setMovieReponseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        wechatTicketService.saveMovieInterfaceInfo(movieInterfaceInfo);
      }
      return saveResult;
    } catch (WechatTicketException e) {
      e.printStackTrace();
      return null;
    }
  }

  // 更多即将上映电影接口
  @RequestMapping(value = { "morecominglist/{movieIds}" }, method = RequestMethod.GET)
  public String getMovieMoreCominglist(@PathVariable String movieIds) {
    // TODO 按照正常的controller进行传参，解决思路
    try {
      // 保存请求结果
      String saveResult = null;
      // 初始化请求
      HttpClientUtil httpClientUtil = new HttpClientUtil();
      MovieInterfaceInfo movieInterfaceInfo = new MovieInterfaceInfo();
      // 通过主键查询到第一次增加的数据对象
      MovieInterfaceInfo oldMovieInterfaceInfo = wechatTicketService.getMovieInterfaceInfoByPrimaryKey("0d55892f-cb48-45c6-9b85-d6c32efedd8f");
      // 获取接口数据
      System.out.println(movieIds);
      saveResult = httpClientUtil.httpGet("https://m.maoyan.com/ajax/moreComingList?token=&movieIds=" + movieIds);
      if(!oldMovieInterfaceInfo.getMovieInterfaceId().equals(null)) {
        // 电影接口编号
        movieInterfaceInfo.setMovieInterfaceId("0d55892f-cb48-45c6-9b85-d6c32efedd8f");
        // 电影接口名称
        movieInterfaceInfo.setMovieInterfaceName("更多即将上映电影列表");
        // 电影接口类型
        movieInterfaceInfo.setMovieInterfaceType("GET请求");
        // 电影接口地址
        movieInterfaceInfo.setMovieInterfaceUrl("https://m.maoyan.com/ajax/moreComingList?token=&movieIds=" + movieIds);
        // 电影接口参数
        movieInterfaceInfo.setMovieInterfacePaprm("有参数-" + movieIds);
        // 电影接口编码
        movieInterfaceInfo.setMovieInterfaceAscii("UTF-8");
        // 接口响应状态
        movieInterfaceInfo.setMovieInterfaceCode(200);
        // 响应内容长度
        movieInterfaceInfo.setMovieReponseLength(-1);
        // 业务版本
        movieInterfaceInfo.setVersion(oldMovieInterfaceInfo.getVersion());
        // 接口响应内容
        movieInterfaceInfo.setMovieinterfaceContent(saveResult);
        // 调用接口次数
        movieInterfaceInfo.setMovieReponseNum(oldMovieInterfaceInfo.getMovieReponseNum() + 1); // 查出原来数据+1操作。使用标志判断
        // 调用接口时间
        movieInterfaceInfo.setMovieReponseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        wechatTicketService.updateMovieInterfaceInfo(movieInterfaceInfo);
      } else {
        // 电影接口名称
      movieInterfaceInfo.setMovieInterfaceName("更多即将上映电影列表");
      // 电影接口类型
      movieInterfaceInfo.setMovieInterfaceType("GET请求");
      // 电影接口地址
      movieInterfaceInfo.setMovieInterfaceUrl("https://m.maoyan.com/ajax/moreComingList?token=&movieIds=" + movieIds);
      // 电影接口参数
      movieInterfaceInfo.setMovieInterfacePaprm("有参数-" + movieIds);
      // 电影接口编码
      movieInterfaceInfo.setMovieInterfaceAscii("UTF-8");
      // 接口响应状态
      movieInterfaceInfo.setMovieInterfaceCode(200);
      // 响应内容长度
      movieInterfaceInfo.setMovieReponseLength(-1);
      // 接口响应内容
      movieInterfaceInfo.setMovieinterfaceContent(saveResult);
      // 调用接口次数
      movieInterfaceInfo.setMovieReponseNum(1);
      // 调用接口时间
      movieInterfaceInfo.setMovieReponseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      wechatTicketService.saveMovieInterfaceInfo(movieInterfaceInfo);
      }
      return saveResult;
    } catch (WechatTicketException e) {
      e.printStackTrace();
      return null;
    }
  }

  // 更多电影最近最受欢迎接口
  @RequestMapping(value = { "moremostexpected/{length}" }, method = RequestMethod.GET)
  public String getMovieMoreMostExpected(@PathVariable String length) {
    // TODO 按照正常的controller进行传参，解决思路
    try {
      // 保存请求结果
      String saveResult = null;
      // 初始化请求
      HttpClientUtil httpClientUtil = new HttpClientUtil();
      MovieInterfaceInfo movieInterfaceInfo = new MovieInterfaceInfo();
      // 通过主键查询到第一次增加的数据对象
      MovieInterfaceInfo oldMovieInterfaceInfo = wechatTicketService.getMovieInterfaceInfoByPrimaryKey("668e2bb6-817b-43ce-bd57-1534401f7b3f");
      // 获取接口数据
      System.out.println(length);
      saveResult = httpClientUtil.httpGet("https://m.maoyan.com/ajax/mostExpected?limit=10&offset=" + length + "&token=");
      if(!oldMovieInterfaceInfo.getMovieInterfaceId().equals(null)) {
        // 电影接口编号
        movieInterfaceInfo.setMovieInterfaceId("668e2bb6-817b-43ce-bd57-1534401f7b3f");
        // 电影接口名称
        movieInterfaceInfo.setMovieInterfaceName("更多最近最受欢迎电影列表");
        // 电影接口类型
        movieInterfaceInfo.setMovieInterfaceType("GET请求");
        // 电影接口地址
        movieInterfaceInfo.setMovieInterfaceUrl("https://m.maoyan.com/ajax/mostExpected?limit=10&offset=" + length + "&token=");
        // 电影接口参数
        movieInterfaceInfo.setMovieInterfacePaprm("有参数-" + length);
        // 电影接口编码
        movieInterfaceInfo.setMovieInterfaceAscii("UTF-8");
        // 接口响应状态
        movieInterfaceInfo.setMovieInterfaceCode(200);
        // 响应内容长度
        movieInterfaceInfo.setMovieReponseLength(-1);
        // 业务版本
        movieInterfaceInfo.setVersion(oldMovieInterfaceInfo.getVersion());
        // 接口响应内容
        movieInterfaceInfo.setMovieinterfaceContent(saveResult);
        // 调用接口次数
        movieInterfaceInfo.setMovieReponseNum(oldMovieInterfaceInfo.getMovieReponseNum() + 1); // 查出原来数据+1操作。使用标志判断
        // 调用接口时间
        movieInterfaceInfo.setMovieReponseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        wechatTicketService.updateMovieInterfaceInfo(movieInterfaceInfo);
      } else {
      // 电影接口名称
      movieInterfaceInfo.setMovieInterfaceName("更多最近最受欢迎电影列表");
      // 电影接口类型
      movieInterfaceInfo.setMovieInterfaceType("GET请求");
      // 电影接口地址
      movieInterfaceInfo.setMovieInterfaceUrl("https://m.maoyan.com/ajax/mostExpected?limit=10&offset=" + length + "&token=");
      // 电影接口参数
      movieInterfaceInfo.setMovieInterfacePaprm("有参数-" + length);
      // 电影接口编码
      movieInterfaceInfo.setMovieInterfaceAscii("UTF-8");
      // 接口响应状态
      movieInterfaceInfo.setMovieInterfaceCode(200);
      // 响应内容长度
      movieInterfaceInfo.setMovieReponseLength(-1);
      // 接口响应内容
      movieInterfaceInfo.setMovieinterfaceContent(saveResult);
      // 调用接口次数
      movieInterfaceInfo.setMovieReponseNum(1);
      // 调用接口时间
      movieInterfaceInfo.setMovieReponseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      wechatTicketService.saveMovieInterfaceInfo(movieInterfaceInfo);
      }
      return saveResult;
    } catch (WechatTicketException e) {
      e.printStackTrace();
      return null;
    }
  }

}
