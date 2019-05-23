package com.fuwenping.bysj.springmvc.restful;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.CinemaInterfaceInfo;
import com.fuwenping.bysj.entity.MovieInterfaceInfo;
import com.fuwenping.bysj.service.spring.IWechatTicketService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * 该类是影院接口后台信息控制器。
 *
 * @author 付文萍
 * @version 0.0.-RELEASE
 */
@org.springframework.stereotype.Controller
@RequestMapping("/cinemainterface")
public class CinemaInterfaceAdminController extends BaseController {

  private static final Log log = LogFactory.getLog(CinemaInterfaceAdminController.class);

  @javax.annotation.Resource(name = "WechatTicketService")
  private IWechatTicketService wechatTicketService;

  // 主页面
  @RequestMapping(value = { "", "/", "starter", "CinemaInterfaceGrid" })
  public ModelAndView grid(CinemaInterfaceInfo cinemaInterfaceInfo) {
    ModelAndView view = this.getView("cinemaInterfaceInfo/CinemaInterfaceGrid", null);
    try {
      Collection<CinemaInterfaceInfo> cinemaInterfaceInfoList = wechatTicketService.getCinemaInterfaceInfoByObject(cinemaInterfaceInfo == null ? new CinemaInterfaceInfo() : cinemaInterfaceInfo);
      view.addObject("cinemaInterfaceInfoList", cinemaInterfaceInfoList);
    } catch (WechatTicketException e) {
      view = this.getView("cinemaInterfaceInfo/CinemaInterfaceGrid", e);
    }
    return view;
  }

  // 显示详情
  @RequestMapping(value = { "view/{cinemaInterfaceId}" }, method = RequestMethod.GET)
  public ModelAndView view(@PathVariable String cinemaInterfaceId) {
    ModelAndView view = this.getView("cinemaInterfaceInfo/CinemaInterfaceDisplay", null);
    try {
      if (cinemaInterfaceId != null) {
        CinemaInterfaceInfo cinemaInterfaceInfo = wechatTicketService.getCinemaInterfaceInfoByPrimaryKey(cinemaInterfaceId);
        view.addObject("cinemaInterfaceInfo", cinemaInterfaceInfo);
      }
    } catch (WechatTicketException e) {
      view = this.getView("cinemaInterfaceInfo/CinemaInterfaceDisplay", e);
    }
    return view;
  }

  // 删除单个数据
  @RequestMapping(value = "delete/{cinemaInterfaceId}", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
  public ModelAndView delete(@PathVariable String cinemaInterfaceId) {
    try {
      CinemaInterfaceInfo cinemaInterfaceInfo = new CinemaInterfaceInfo();
      cinemaInterfaceInfo.setCinemaInterfaceId(cinemaInterfaceId);
      wechatTicketService.removeCinemaInterfaceInfo(cinemaInterfaceInfo);
      return grid(null);
    } catch (WechatTicketException e) {
      return this.getView("cinemaInterfaceInfo/CinemaInterfaceGrid", e);
    }
  }

  // 设置页面路径（用于激活页面菜单）
  @Override
  protected ModelAndView getView(String viewName, WechatTicketException wechatTicketException) {
    ModelAndView view = super.getView(viewName, wechatTicketException);
    view.addObject(ACTIVE_MENU_KEY, "cinemainterface");
    return view;
  }
}

