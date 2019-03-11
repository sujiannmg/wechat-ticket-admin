package com.fuwenping.bysj.service.spring.impl;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.AccountInfo;
import com.fuwenping.bysj.persistent.jdbc.IAccountInfoPersistent;
import com.fuwenping.bysj.service.spring.IWechatTicketAuthorizeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;

/**
 * 该类是以下对象操作的业务具休实现。
 * 系统帐号权限登录
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
@org.springframework.stereotype.Service("WechatTicketAuthorizeService")
@org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, readOnly = true, rollbackFor = java.lang.Exception.class)
public class WechatTicketAuthorizeServiceImpl implements IWechatTicketAuthorizeService {

  private static final Log log = LogFactory.getLog(WechatTicketServiceImple.class);

  @javax.annotation.Resource(name = "AccountInfoPersistent")
  private IAccountInfoPersistent accountInfoPersistent;

  @Override
  public AccountInfo login(String account, String password) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketAuthorizeService.login ,parameters : [ account  =  " + account + " + password  =  " + password + "]");
    }
    try {
      AccountInfo queryAccount = new AccountInfo();
      queryAccount.setAccount(account);
      Collection<AccountInfo> accountList = accountInfoPersistent.getAccountInfoByObject(queryAccount);
      if (accountList.isEmpty()) {
        throw new WechatTicketException("登录失败：帐号不存在");
      }
      if (!password.equals(accountList.iterator().next().getPassword())) {
        throw new WechatTicketException("登录失败：密码不正确");
      }
      return accountList.iterator().next();
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "登录失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }
}
