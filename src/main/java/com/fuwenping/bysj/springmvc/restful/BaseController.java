package com.fuwenping.bysj.springmvc.restful;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 该类是基础控制器。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public class BaseController {

  @InitBinder // 表单多对象精准绑定接收
  protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
  }

  @org.springframework.beans.factory.annotation.Autowired(required = false)
  private HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired(required = false)
  private HttpServletResponse response;

  protected HttpServletRequest getRequest() {
    return request;
  }

  protected HttpServletResponse getResponse() {
    return response;
  }

  protected HttpSession getSession() {

    return getRequest().getSession();
  }

  protected ServletContext getServletContext() {

    return getSession().getServletContext();
  }

  public static final String LOGIN_ACCOUNT_KEY = "AUTHORIZE_ACCOUNT_SESSION_KEY";
  protected static final String ACTIVE_MENU_KEY = "activeMenu";

  protected ModelAndView getView(String viewName) {
    ModelAndView view = new ModelAndView(viewName);
    String requestUrl = request.getRequestURL().toString();
    if (requestUrl.indexOf(":", 8) > 0) {
      view.addObject("webContextPath", request.getContextPath());
    } else {
      view.addObject("webContextPath", ".");
    }
    view.addObject(ACTIVE_MENU_KEY, "starter");
    System.out.println("***********" + view);
    return view;
  }

  protected ModelAndView getView(String viewName, WechatTicketException wechatTicketException) {
    ModelAndView view = this.getView(viewName);
    if (wechatTicketException != null) {
      view.addObject("error", wechatTicketException.getMessage());
      System.out.println("-------------" + view);
    }
    System.out.println("--------------" + view);
    return view;
  }
}

