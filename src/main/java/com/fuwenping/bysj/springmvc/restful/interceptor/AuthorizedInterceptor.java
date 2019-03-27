package com.fuwenping.bysj.springmvc.restful.interceptor;

import com.fuwenping.bysj.commons.security.AuthorizedContext;
import com.fuwenping.bysj.entity.AccountInfo;
import com.fuwenping.bysj.springmvc.restful.BaseController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 该类是权限拦截器。
 *
 * @author 付文萍
 * @version 0.0.-RELEASE
 */
public class AuthorizedInterceptor implements HandlerInterceptor {

  private static final Log log = LogFactory.getLog(AuthorizedInterceptor.class);

  // 执行时机 Handler执行之前去执行
  // 使用场景 用于用户权限拦截校验
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (log.isInfoEnabled()) {
      log.info("+============================== AuthorizedInterceptor start ==============================+");
    }
    String url = request.getPathInfo();
    String method = request.getMethod();
    if (log.isInfoEnabled()) {
      log.info("Authorized Url : " + url + " , http method : " + method);
    }
    AuthorizedContext.getAccountInfo();
    if (request.getSession().getAttribute(BaseController.LOGIN_ACCOUNT_KEY) != null) {
      AuthorizedContext.setAccountInfo((AccountInfo) request.getSession().getAttribute(BaseController.LOGIN_ACCOUNT_KEY));
      return true;
    } else {
      if (url.indexOf("/login") > -1) {
        return true;
      } else {
        request.getRequestDispatcher("/login").forward(request, response);
      }
    }
    // 返回 true表示不拦截即放行
    // 返回 false表示拦截，不在向后执行
    return false;
  }

  // 执行时机 Handler执行未返回modelAndView之前 去执行
  // 使用场景 将页面需要调用的信息通过modelAndView传到页面，比如：菜单导航通用在此方法取出来传到页面
  // 比如：统一定义视图决定视图转向
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    if (log.isInfoEnabled()) {
      log.info("正常执行完成");
    }
  }

  // 运行时机 Handler执行完成并且将modelAndView返回
  // 使用场景 统一进行异常处理，统一记录操作日志
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    if (log.isInfoEnabled()) {
      log.info("异常执行完成");
    }
  }
}
