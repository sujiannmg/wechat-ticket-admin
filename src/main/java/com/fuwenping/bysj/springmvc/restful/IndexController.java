package com.fuwenping.bysj.springmvc.restful;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.AccountInfo;
import com.fuwenping.bysj.service.spring.IWechatTicketAuthorizeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 该类是系统帐号主页控制器。
 *
 * @author 付文萍
 * @version 0.0.-RELEASE
 */
@org.springframework.stereotype.Controller
public class IndexController extends BaseController {

  private static final Log log = LogFactory.getLog(AccountInfoController.class);

  @javax.annotation.Resource(name = "WechatTicketAuthorizeService")
  private IWechatTicketAuthorizeService wechatTicketAuthorizeService;

  // 返回主页面
  @RequestMapping(value = { "", "/", "starter" })
  public ModelAndView index() {
    return super.getView("starter");
  }

  // 返回登录页面
  @RequestMapping(value = { "login" }, method = RequestMethod.GET )
  public ModelAndView login() {
    return super.getView("login");
  }

  // 进行登录操作
  @RequestMapping(value = { "login" }, method = RequestMethod.POST )
  public ModelAndView login(String account, String password) {
    try {
      AccountInfo loginAccountInfo = wechatTicketAuthorizeService.login(account, password);
      super.getSession().setAttribute(LOGIN_ACCOUNT_KEY, loginAccountInfo);
      return index();
    } catch (WechatTicketException e) {
      return super.getView("login", e);
    }
  }

  // 登出
  @RequestMapping(value = { "logout" })
  public ModelAndView logout() {
    super.getSession().removeAttribute(LOGIN_ACCOUNT_KEY);
    super.getSession().invalidate();
    return super.getView("login");
  }
}
