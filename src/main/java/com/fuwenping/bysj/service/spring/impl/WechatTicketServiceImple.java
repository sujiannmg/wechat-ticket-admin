package com.fuwenping.bysj.service.spring.impl;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.AccountInfo;
import com.fuwenping.bysj.entity.MovieInterfaceInfo;
import com.fuwenping.bysj.persistent.jdbc.IAccountInfoPersistent;
import com.fuwenping.bysj.persistent.jdbc.IMovieInterfaceInfoPersistent;
import com.fuwenping.bysj.service.spring.IWechatTicketService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;

/**
 * 该类是以下对象操作的业务具休实现。
 * 系统帐号
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
@org.springframework.stereotype.Service("WechatTicketService")
@org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, readOnly = true, rollbackFor = java.lang.Exception.class)
public class WechatTicketServiceImple implements IWechatTicketService {

  private static final Log log = LogFactory.getLog(WechatTicketServiceImple.class);

  // 系统账号持久化层
  @javax.annotation.Resource(name = "AccountInfoPersistent")
  private IAccountInfoPersistent accountInfoPersistent;

  // 电影接口信息持久化层
  @javax.annotation.Resource(name = "MovieInterfaceInfoPersistent")
  private IMovieInterfaceInfoPersistent movieInterfaceInfoPersistent;

  // 系统账号实现
  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void saveAccountInfo(AccountInfo accountInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.saveAccountInfo ,parameters : [ accountInfo  =  " + accountInfo + "]");
    }
    try {
      AccountInfo queryAccountInfo = new AccountInfo();
      queryAccountInfo.setAccount(accountInfo.getAccount());
      Collection<AccountInfo> accountInfoList = accountInfoPersistent.getAccountInfoByObject(queryAccountInfo);
      if (!accountInfoList.isEmpty()) {
        throw new WechatTicketException("创建账号失败：帐号[" + accountInfo.getAccount() + "]已经存在");
      }
      accountInfoPersistent.saveAccountInfo(accountInfo);
    } catch (WechatTicketException e) {
      accountInfo.setAccountId(null);
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "创建账号失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void updateAccountInfo(AccountInfo accountInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.updateAccountInfo ,parameters : [ accountInfo  =  " + accountInfo + "]");
    }
    try {
      AccountInfo oldAccountInfo = accountInfoPersistent.getAccountInfoByPrimaryKey(accountInfo.getAccountId());
      if (oldAccountInfo == null || (!oldAccountInfo.getVersion().equals(oldAccountInfo.getVersion()))) {
        throw new WechatTicketException("修改账号失败：原帐号不存在，或者数据版本不一致。");
      }
      boolean isHaveData = false;
      AccountInfo queryAccountInfo = new AccountInfo();
      queryAccountInfo.setAccount(accountInfo.getAccount());
      Collection<AccountInfo> accountInfoList = accountInfoPersistent.getAccountInfoByObject(queryAccountInfo);
      if (!accountInfoList.isEmpty() && accountInfoList.size() > 1) {
        isHaveData = true;
      } else {
        if (!accountInfoList.isEmpty() && !oldAccountInfo.getAccountId().equals(accountInfoList.iterator().next().getAccountId())) {
          isHaveData = true;
        }
      }
      if (isHaveData) {
        throw new WechatTicketException("修改账号失败：帐号[" + accountInfo.getAccount() + "]已经存在");
      }
      accountInfo.setVersion(accountInfo.getVersion() + 1);
      accountInfoPersistent.updateAccountInfo(accountInfo);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "修改账号失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void removeAccountInfo(AccountInfo accountInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.removeAccountInfo ,parameters : [ accountInfo  =  " + accountInfo + "]");
    }
    try {
      accountInfoPersistent.removeAccountInfo(accountInfo);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "删除账号失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public AccountInfo getAccountInfoByPrimaryKey(String accountId) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.getAccountInfoByPrimaryKey ,parameters : [ accountInfo  =  " + accountId + "]");
    }
    try {
      return accountInfoPersistent.getAccountInfoByPrimaryKey(accountId);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "通过主键查询帐号失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public Collection<AccountInfo> getAccountInfoByObject(AccountInfo accountInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.getAccountInfoByObject ,parameters : [ accountInfo  =  " + accountInfo + "]");
    }
    try {
      return accountInfoPersistent.getAccountInfoByObject(accountInfo);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "通过对象查询帐号失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  // 电影接口信息实现
  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void saveMovieInterfaceInfo(MovieInterfaceInfo movieInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.saveMovieInterfaceInfo ,parameters : [ movieInterfaceInfo  =  " + movieInterfaceInfo + "]");
    }
    try {
      MovieInterfaceInfo queryMovieInterfaceInfo = new MovieInterfaceInfo();
      queryMovieInterfaceInfo.setMovieInterfaceName(movieInterfaceInfo.getMovieInterfaceName());
      Collection<MovieInterfaceInfo> movieInterfaceInfoList = movieInterfaceInfoPersistent.getMovieInterfaceInfoByObject(queryMovieInterfaceInfo);
      if (!movieInterfaceInfoList.isEmpty()) {
        throw new WechatTicketException("创建账号失败：帐号[" + movieInterfaceInfo.getMovieInterfaceName() + "]已经存在");
      }
      movieInterfaceInfoPersistent.saveMovieInterfaceInfo(movieInterfaceInfo);
    } catch (WechatTicketException e) {
      movieInterfaceInfo.setMovieInterfaceId(null);
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "创建电影接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void updateMovieInterfaceInfo(MovieInterfaceInfo movieInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.updateMovieInterfaceInfo ,parameters : [ movieInterfaceInfo  =  " + movieInterfaceInfo + "]");
    }
    try {
      MovieInterfaceInfo oldMovieInterfaceInfo = movieInterfaceInfoPersistent.getMovieInterfaceInfoByPrimaryKey(movieInterfaceInfo.getMovieInterfaceId());
      if (oldMovieInterfaceInfo == null || (!oldMovieInterfaceInfo.getVersion().equals(oldMovieInterfaceInfo.getVersion()))) {
        throw new WechatTicketException("修改电影接口信息失败：原电影接口信息不存在，或者数据版本不一致。");
      }
      boolean isHaveData = false;
      MovieInterfaceInfo queryMovieInterfaceInfo = new MovieInterfaceInfo();
      queryMovieInterfaceInfo.setMovieInterfaceName(movieInterfaceInfo.getMovieInterfaceName());
      Collection<MovieInterfaceInfo> movieInterfaceInfoList = movieInterfaceInfoPersistent.getMovieInterfaceInfoByObject(queryMovieInterfaceInfo);
      if (!movieInterfaceInfoList.isEmpty() && movieInterfaceInfoList.size() > 1) {
        isHaveData = true;
      } else {
        if (!movieInterfaceInfoList.isEmpty() && !oldMovieInterfaceInfo.getMovieInterfaceId().equals(movieInterfaceInfoList.iterator().next().getMovieInterfaceId())) {
          isHaveData = true;
        }
      }
      if (isHaveData) {
        throw new WechatTicketException("修改电影接口信息失败：电影接口信息[" + movieInterfaceInfo.getMovieInterfaceName() + "]已经存在");
      }
      movieInterfaceInfo.setVersion(movieInterfaceInfo.getVersion() + 1);
      movieInterfaceInfoPersistent.updateMovieInterfaceInfo(movieInterfaceInfo);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "修改电影接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void removeMovieInterfaceInfo(MovieInterfaceInfo movieInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.removeMovieInterfaceInfo ,parameters : [ movieInterfaceInfo  =  " + movieInterfaceInfo + "]");
    }
    try {
      movieInterfaceInfoPersistent.removeMovieInterfaceInfo(movieInterfaceInfo);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "删除电影接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public MovieInterfaceInfo getMovieInterfaceInfoByPrimaryKey(String movieInterfaceId) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.getMovieInterfaceInfoByPrimaryKey ,parameters : [ movieInterfaceId  =  " + movieInterfaceId + "]");
    }
    try {
      return movieInterfaceInfoPersistent.getMovieInterfaceInfoByPrimaryKey(movieInterfaceId);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "通过主键查询电影接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public Collection<MovieInterfaceInfo> getMovieInterfaceInfoByObject(MovieInterfaceInfo movieInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.getMovieInterfaceInfoByObject ,parameters : [ movieInterfaceInfo  =  " + movieInterfaceInfo + "]");
    }
    try {
      System.out.println("------" + movieInterfaceInfo);
      return movieInterfaceInfoPersistent.getMovieInterfaceInfoByObject(movieInterfaceInfo);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "通过对象查询电影接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }
}
