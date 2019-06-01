package com.fuwenping.bysj.springmvc.restful;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.TicketOrderInfo;
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
@RequestMapping("/ticketorderinfoadmin")
public class TicketOrdernfoAdminController extends BaseController {

  private static final Log log = LogFactory.getLog(TicketOrdernfoAdminController.class);

  @javax.annotation.Resource(name = "WechatTicketService")
  private IWechatTicketService wechatTicketService;

  // 主页面
  @RequestMapping(value = { "", "/", "starter", "TicketOrdernfoGrid" })
  public ModelAndView grid(TicketOrderInfo tcketOrderInfo) {
    ModelAndView view = this.getView("ticketOrderInfo/TicketOrdernfoGrid", null);
    try {
      Collection<TicketOrderInfo> tcketOrderInfoList = wechatTicketService.getTicketOrderInfoByObject(tcketOrderInfo == null ? new TicketOrderInfo() : tcketOrderInfo);
      view.addObject("tcketOrderInfoList", tcketOrderInfoList);
    } catch (WechatTicketException e) {
      view = this.getView("ticketOrderInfo/TicketOrdernfoGrid", e);
    }
    return view;
  }

  // 显示详情
  @RequestMapping(value = { "view/{ticketOrderInfoId}" }, method = RequestMethod.GET)
  public ModelAndView view(@PathVariable String ticketOrderInfoId) {
    ModelAndView view = this.getView("ticketOrderInfo/TicketOrdernfoDisplay", null);
    try {
      if (ticketOrderInfoId != null) {
        TicketOrderInfo tcketOrderInfo = wechatTicketService.getTicketOrderInfoByPrimaryKey(ticketOrderInfoId);
        view.addObject("tcketOrderInfo", tcketOrderInfo);
      }
    } catch (WechatTicketException e) {
      view = this.getView("ticketOrderInfo/TicketOrdernfoDisplay", e);
    }
    return view;
  }

  // 删除单个数据
  @RequestMapping(value = "delete/{ticketOrderInfoId}", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
  public ModelAndView delete(@PathVariable String ticketOrderInfoId) {
    try {
      TicketOrderInfo tcketOrderInfo = new TicketOrderInfo();
      tcketOrderInfo.setTicketOrderInfoId(ticketOrderInfoId);
      wechatTicketService.removeTicketOrderInfo(tcketOrderInfo);
      return grid(null);
    } catch (WechatTicketException e) {
      return this.getView("ticketOrderInfo/TicketOrdernfoGrid", e);
    }
  }

  // 设置页面路径（用于激活页面菜单）
  @Override
  protected ModelAndView getView(String viewName, WechatTicketException wechatTicketException) {
    ModelAndView view = super.getView(viewName, wechatTicketException);
    view.addObject(ACTIVE_MENU_KEY, "order");
    return view;
  }
}
