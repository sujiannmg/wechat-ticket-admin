package com.fuwenping.bysj.springmvc.restful;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.WechatUserInfo;
import com.fuwenping.bysj.service.spring.IWechatTicketService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * 该类是微信用户后台信息控制器。
 *
 * @author 付文萍
 * @version 0.0.-RELEASE
 */
@org.springframework.stereotype.Controller
@RequestMapping("/wechatuserinfoadmin")
public class WechatUserInfoAdminController extends BaseController {

  private static final Log log = LogFactory.getLog(WechatUserInfoAdminController.class);

  @javax.annotation.Resource(name = "WechatTicketService")
  private IWechatTicketService wechatTicketService;

  // 主页面
  @RequestMapping(value = { "", "/", "starter", "WechatUserInfoGrid" })
  public ModelAndView grid(WechatUserInfo wechatUserInfo) {
    ModelAndView view = this.getView("wechatInfo/WechatUserInfoGrid", null);
    try {
      Collection<WechatUserInfo> wechatUserInfoList = wechatTicketService.getWechatUserInfoByObject(wechatUserInfo == null ? new WechatUserInfo() : wechatUserInfo);
      view.addObject("wechatUserInfoList", wechatUserInfoList);
    } catch (WechatTicketException e) {
      view = this.getView("wechatInfo/WechatUserInfoGrid", e);
    }
    return view;
  }

  // 显示详情
  @RequestMapping(value = { "view/{openId}" }, method = RequestMethod.GET)
  public ModelAndView view(@PathVariable String openId) {
    ModelAndView view = this.getView("wechatInfo/WechatUserInfoDisplay", null);
    try {
      if (openId != null) {
        WechatUserInfo wechatUserInfo = wechatTicketService.getWechatUserInfoByPrimaryKey(openId);
        view.addObject("wechatUserInfo", wechatUserInfo);
      }
    } catch (WechatTicketException e) {
      view = this.getView("wechatInfo/WechatUserInfoDisplay", e);
    }
    return view;
  }

  // 删除单个数据
  @RequestMapping(value = "delete/{openId}", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
  public ModelAndView delete(@PathVariable String openId) {
    try {
      WechatUserInfo wechatUserInfo = new WechatUserInfo();
      wechatUserInfo.setOpenId(openId);
      wechatTicketService.removeWechatUserInfo(wechatUserInfo);
      return grid(null);
    } catch (WechatTicketException e) {
      return this.getView("wechatInfo/WechatUserInfoGrid", e);
    }
  }

  // 设置页面路径（用于激活页面菜单）
  @Override
  protected ModelAndView getView(String viewName, WechatTicketException wechatTicketException) {
    ModelAndView view = super.getView(viewName, wechatTicketException);
    view.addObject(ACTIVE_MENU_KEY, "wechat");
    return view;
  }
}
