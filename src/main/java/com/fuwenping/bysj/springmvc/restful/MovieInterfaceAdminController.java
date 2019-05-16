package com.fuwenping.bysj.springmvc.restful;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.MovieInterfaceInfo;
import com.fuwenping.bysj.service.spring.IWechatTicketService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * 该类是电影接口后台信息控制器。
 *
 * @author 付文萍
 * @version 0.0.-RELEASE
 */
@org.springframework.stereotype.Controller
@RequestMapping("/movieinterface")
public class MovieInterfaceAdminController extends BaseController {

  private static final Log log = LogFactory.getLog(AccountInfoController.class);

  @javax.annotation.Resource(name = "WechatTicketService")
  private IWechatTicketService wechatTicketService;

  // 主页面
  @RequestMapping(value = { "", "/", "starter", "MovieInterfaceGrid" })
  public ModelAndView grid(MovieInterfaceInfo movieInterfaceInfo) {
    ModelAndView view = this.getView("movieInterfaceInfo/MovieInterfaceGrid", null);
    try {
      Collection<MovieInterfaceInfo> movieInterfaceInfoList = wechatTicketService.getMovieInterfaceInfoByObject(movieInterfaceInfo == null ? new MovieInterfaceInfo() : movieInterfaceInfo);
      view.addObject("movieInterfaceInfoList", movieInterfaceInfoList);
    } catch (WechatTicketException e) {
      view = this.getView("movieInterfaceInfo/MovieInterfaceGrid", e);
    }
    return view;
  }
}

