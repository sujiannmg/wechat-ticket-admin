package com.fuwenping.bysj.springmvc.restful;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.AccountInfo;
import com.fuwenping.bysj.service.spring.IWechatTicketService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * 该类是系统帐号控制器。
 *
 * @author 付文萍
 * @version 0.0.-RELEASE
 */
@org.springframework.stereotype.Controller
@RequestMapping("/base/account")
public class AccountInfoController extends BaseController {

  private static final Log log = LogFactory.getLog(AccountInfoController.class);

  @javax.annotation.Resource(name = "WechatTicketService")
  private IWechatTicketService wechatTicketService;

  // 主页面
  @RequestMapping(value = { "", "/", "starter", "AccountInfoGrid" })
  public ModelAndView grid(AccountInfo accountInfo) {
    ModelAndView view = this.getView("accountInfo/AccountInfoGrid", null);
    try {
      Collection<AccountInfo> accountInfoList = wechatTicketService.getAccountInfoByObject(accountInfo == null ? new AccountInfo() : accountInfo);
      view.addObject("accountInfoList", accountInfoList);
    } catch (WechatTicketException e) {
      view = this.getView("accountInfo/AccountInfoGrid", e);
    }
    return view;
  }

  // 设置页面路径
  @Override
  protected ModelAndView getView(String viewName, WechatTicketException wechatTicketException) {
    ModelAndView view = super.getView(viewName, wechatTicketException);
    view.addObject(ACTIVE_MENU_KEY, "base/account");
    return view;
  }


}
