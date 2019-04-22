package com.fuwenping.bysj.springmvc.restful;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.AccountInfo;
import com.fuwenping.bysj.service.spring.IWechatTicketService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * 该类是系统帐号控制器。
 *
 * @author 付文萍
 * @version 0.0.-RELEASE
 */
@org.springframework.stereotype.Controller
@RequestMapping("/account")
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

  // 编辑页面
  @RequestMapping(value = { "edit/{accountId}" }, method = RequestMethod.GET)
  public ModelAndView edit(@PathVariable String accountId) {
    ModelAndView view = this.getView("accountInfo/AccountInfoEdit", null);
    try {
      if (accountId != null) {
        AccountInfo accountInfo = wechatTicketService.getAccountInfoByPrimaryKey(accountId);
        view.addObject("accountInfo", accountInfo);
      }
    } catch (WechatTicketException e) {
      view = this.getView("accountInfo/AccountInfoGrid", e);
    }
    return view;
  }

  @RequestMapping(value = { "edit", "create" }, method = RequestMethod.GET)
  public ModelAndView edit() {
    return edit(null);
  }

  // 新增账号页面
  @RequestMapping(value = { "edit" }, method = RequestMethod.POST)
  public ModelAndView save(AccountInfo accountInfo) {
    try {
      if (accountInfo.getAccountId() != null && accountInfo.getAccountId().trim().length() > 0) {
        wechatTicketService.updateAccountInfo(accountInfo);
      } else {
        wechatTicketService.saveAccountInfo(accountInfo);
      }
      return grid(null);
    } catch (WechatTicketException e) {
      ModelAndView view = this.getView("accountInfo/AccountInfoEdit", e);
      view.addObject("accountInfo", accountInfo);
      return view;
    }
  }

  // 显示详情
  @RequestMapping(value = { "view/{accountId}" }, method = RequestMethod.GET)
  public ModelAndView view(@PathVariable String accountId) {
    ModelAndView view = this.getView("accountInfo/AccountDisplay", null);
    try {
      if (accountId != null) {
        AccountInfo accountInfo = wechatTicketService.getAccountInfoByPrimaryKey(accountId);
        view.addObject("accountInfo", accountInfo);
      }
    } catch (WechatTicketException e) {
      view = this.getView("accountInfo/AccountDisplay", e);
    }
    return view;
  }

  // 删除单个数据
  @RequestMapping(value = "delete/{accountId}", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
  public ModelAndView delete(@PathVariable String accountId) {
    try {
      AccountInfo accountInfo = new AccountInfo();
      accountInfo.setAccountId(accountId);
      wechatTicketService.removeAccountInfo(accountInfo);
      return grid(null);
    } catch (WechatTicketException e) {
      return this.getView("accountInfo/AccountInfoGrid", e);
    }
  }

  // 设置页面路径
  @Override
  protected ModelAndView getView(String viewName, WechatTicketException wechatTicketException) {
    ModelAndView view = super.getView(viewName, wechatTicketException);
    view.addObject(ACTIVE_MENU_KEY, "accountInfo");
    return view;
  }


}
