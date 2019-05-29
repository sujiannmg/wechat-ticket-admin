package com.fuwenping.bysj.service.spring.impl;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.*;
import com.fuwenping.bysj.persistent.jdbc.*;
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

  // 微信用户持久化层
  @javax.annotation.Resource(name = "WechatUserInfoPersisent")
  private IWechatUserInfoPersisent wechatUserInfoPersisent;

  // 电影接口信息持久化层
  @javax.annotation.Resource(name = "MovieInterfaceInfoPersistent")
  private IMovieInterfaceInfoPersistent movieInterfaceInfoPersistent;

  // 影院接口信息持久化层
  @javax.annotation.Resource(name = "CinemaInterfaceInfoPersistent")
  private ICinemaInterfaceInfoPersistent cinemaInterfaceInfoPersistent;

  // 影评接口信息持久化层
  @javax.annotation.Resource(name = "CommentInterfaceInfoPersisent")
  private ICommentInterfaceInfoPersistent commentInterfaceInfoPersisent;

  // 影票订单信息持久化层
  @javax.annotation.Resource(name = "TicketOrderInfoPersisent")
  private ITicketOrderInfoPersisent ticketOrderInfoPersisent;

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
        throw new WechatTicketException("创建电影接口信息失败：电影[" + movieInterfaceInfo.getMovieInterfaceName() + "]已经存在");
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
      // e.printStackTrace();
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

  // 影院接口信息实现
  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void saveCinemaInterfaceInfo(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.saveCinemaInterfaceInfo ,parameters : [ cinemaInterfaceInfo  =  " + cinemaInterfaceInfo + "]");
    }
    try {
      CinemaInterfaceInfo queryCinemaInterfaceInfo = new CinemaInterfaceInfo();
      queryCinemaInterfaceInfo.setCinemaInterfaceName(cinemaInterfaceInfo.getCinemaInterfaceName());
      Collection<CinemaInterfaceInfo> cinemaInterfaceInfoList = cinemaInterfaceInfoPersistent.getCinemaInterfaceInfoByObject(queryCinemaInterfaceInfo);
      if (!cinemaInterfaceInfoList.isEmpty()) {
        throw new WechatTicketException("创建影院接口信息失败：影院[" + cinemaInterfaceInfo.getCinemaInterfaceName() + "]已经存在");
      }
     cinemaInterfaceInfoPersistent.saveCinemaInterfaceInfo(cinemaInterfaceInfo);
    } catch (WechatTicketException e) {
      cinemaInterfaceInfo.setCinemaInterfaceId(null);
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "创建影院接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void updateCinemaInterfaceInfo(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.updateCinemaInterfaceInfo ,parameters : [ cinemaInterfaceInfo  =  " + cinemaInterfaceInfo + "]");
    }
    try {
      CinemaInterfaceInfo oldCinemaInterfaceInfo = cinemaInterfaceInfoPersistent.getCinemaInterfaceInfoByPrimaryKey(cinemaInterfaceInfo.getCinemaInterfaceId());
      if (oldCinemaInterfaceInfo == null || (!oldCinemaInterfaceInfo.getVersion().equals(oldCinemaInterfaceInfo.getVersion()))) {
        throw new WechatTicketException("修改影院接口信息失败：原影院接口信息不存在，或者数据版本不一致。");
      }
      boolean isHaveData = false;
      CinemaInterfaceInfo queryCinemaInterfaceInfo = new CinemaInterfaceInfo();
      queryCinemaInterfaceInfo.setCinemaInterfaceName(cinemaInterfaceInfo.getCinemaInterfaceName());
      Collection<CinemaInterfaceInfo> cinemaInterfaceInfoList = cinemaInterfaceInfoPersistent.getCinemaInterfaceInfoByObject(queryCinemaInterfaceInfo);
      if (!cinemaInterfaceInfoList.isEmpty() && cinemaInterfaceInfoList.size() > 1) {
        isHaveData = true;
      } else {
        if (!cinemaInterfaceInfoList.isEmpty() && !oldCinemaInterfaceInfo.getCinemaInterfaceId().equals(cinemaInterfaceInfoList.iterator().next().getCinemaInterfaceId())) {
          isHaveData = true;
        }
      }
      if (isHaveData) {
        throw new WechatTicketException("修改影院接口信息失败：影院接口信息[" + cinemaInterfaceInfo.getCinemaInterfaceId() + "]已经存在");
      }
      cinemaInterfaceInfo.setVersion(cinemaInterfaceInfo.getVersion() + 1);
      cinemaInterfaceInfoPersistent.updateCinemaInterfaceInfo(cinemaInterfaceInfo);
    } catch (WechatTicketException e) {
      // e.printStackTrace();
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
  public void removeCinemaInterfaceInfo(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.removeCinemaInterfaceInfo ,parameters : [ cinemaInterfaceInfo  =  " + cinemaInterfaceInfo + "]");
    }
    try {
      cinemaInterfaceInfoPersistent.removeCinemaInterfaceInfo(cinemaInterfaceInfo);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "删除影院接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public CinemaInterfaceInfo getCinemaInterfaceInfoByPrimaryKey(String cinemaInterfaceId) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.getCinemaInterfaceInfoByPrimaryKey ,parameters : [ cinemaInterfaceId  =  " + cinemaInterfaceId + "]");
    }
    try {
      return cinemaInterfaceInfoPersistent.getCinemaInterfaceInfoByPrimaryKey(cinemaInterfaceId);
    } catch (WechatTicketException e) {
      e.printStackTrace();
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "通过主键查询影院接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public Collection<CinemaInterfaceInfo> getCinemaInterfaceInfoByObject(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.getCinemaInterfaceInfoByObject ,parameters : [ cinemaInterfaceInfo  =  " + cinemaInterfaceInfo + "]");
    }
    try {
      System.out.println("------" + cinemaInterfaceInfo);
      return cinemaInterfaceInfoPersistent.getCinemaInterfaceInfoByObject(cinemaInterfaceInfo);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "通过对象查询影院接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  // 微信用户实现
  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void saveWechatUserInfo(WechatUserInfo wechatUserInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.saveWechatUserInfo ,parameters : [ wechatUserInfo  =  " + wechatUserInfo + "]");
    }
    try {
      WechatUserInfo queryWechatUserInfo = new WechatUserInfo();
      queryWechatUserInfo.setNickName(wechatUserInfo.getNickName());
      Collection<WechatUserInfo> wechatUserInfoList = wechatUserInfoPersisent.getWechatUserInfoByObject(queryWechatUserInfo);
      if (!wechatUserInfoList.isEmpty()) {
        throw new WechatTicketException("创建微信用户信息失败：微信用户[" + wechatUserInfo.getNickName() + "]已经存在");
      }
      wechatUserInfoPersisent.saveWechatUserInfo(wechatUserInfo);
    } catch (WechatTicketException e) {
      wechatUserInfo.setOpenId(null);
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "创建微信用户信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void updateWechatUserInfo(WechatUserInfo wechatUserInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.updateWechatUserInfo ,parameters : [ wechatUserInfo  =  " + wechatUserInfo + "]");
    }
    try {
      WechatUserInfo oldWechatUserInfo = wechatUserInfoPersisent.getWechatUserInfoByPrimaryKey(wechatUserInfo.getOpenId());
      if (oldWechatUserInfo == null || (!oldWechatUserInfo.getVersion().equals(oldWechatUserInfo.getVersion()))) {
        throw new WechatTicketException("修改微信用户信息失败：原影微信用户信息不存在，或者数据版本不一致。");
      }
      boolean isHaveData = false;
      WechatUserInfo queryWechatUserInfo = new WechatUserInfo();
      queryWechatUserInfo.setNickName(wechatUserInfo.getNickName());
      Collection<WechatUserInfo> wechatUserInfoList = wechatUserInfoPersisent.getWechatUserInfoByObject(queryWechatUserInfo);
      if (!wechatUserInfoList.isEmpty() && wechatUserInfoList.size() > 1) {
        isHaveData = true;
      } else {
        if (!wechatUserInfoList.isEmpty() && !oldWechatUserInfo.getOpenId().equals(wechatUserInfoList.iterator().next().getOpenId())) {
          isHaveData = true;
        }
      }
      if (isHaveData) {
        throw new WechatTicketException("修改微信用户信息失败：微信用户信息[" + wechatUserInfo.getOpenId() + "]已经存在");
      }
      wechatUserInfo.setVersion(wechatUserInfo.getVersion() + 1);
      wechatUserInfoPersisent.updateWechatUserInfo(wechatUserInfo);
    } catch (WechatTicketException e) {
      // e.printStackTrace();
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "修改微信用户信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void removeWechatUserInfo(WechatUserInfo wechatUserInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.removeWechatUserInfo ,parameters : [ wechatUserInfo  =  " + wechatUserInfo + "]");
    }
    try {
      wechatUserInfoPersisent.removeWechatUserInfo(wechatUserInfo);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "删除微信用户信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public WechatUserInfo getWechatUserInfoByPrimaryKey(String openId) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.getWechatUserInfoByPrimaryKey ,parameters : [ openId  =  " + openId + "]");
    }
    try {
      return wechatUserInfoPersisent.getWechatUserInfoByPrimaryKey(openId);
    } catch (WechatTicketException e) {
      e.printStackTrace();
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "通过主键查询微信用户信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public Collection<WechatUserInfo> getWechatUserInfoByObject(WechatUserInfo wechatUserInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.getWechatUserInfoByObject ,parameters : [ wechatUserInfo  =  " + wechatUserInfo + "]");
    }
    try {
      System.out.println("------" + wechatUserInfo);
      return wechatUserInfoPersisent.getWechatUserInfoByObject(wechatUserInfo);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "通过对象查询微信用户信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  // 影评接口实现
  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void saveCommentInterfaceInfo(CommentInterfaceInfo commentInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.saveCommentInterfaceInfo ,parameters : [ commentInterfaceInfo  =  " + commentInterfaceInfo + "]");
    }
    try {
      CommentInterfaceInfo queryCommentInterfaceInfo = new CommentInterfaceInfo();
      queryCommentInterfaceInfo.setCommentInterfaceName(commentInterfaceInfo.getCommentInterfaceName());
      Collection<CommentInterfaceInfo> commentInterfaceInfoList = commentInterfaceInfoPersisent.getCommentInterfaceInfoByObject(queryCommentInterfaceInfo);
      if (!commentInterfaceInfoList.isEmpty()) {
        throw new WechatTicketException("创建影评接口信息失败：影评[" + commentInterfaceInfo.getCommentInterfaceName() + "]已经存在");
      }
      commentInterfaceInfoPersisent.saveCommentInterfaceInfo(commentInterfaceInfo);
    } catch (WechatTicketException e) {
      commentInterfaceInfo.setCommentInterfaceId(null);
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "创建影评接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void updateCommentInterfaceInfo(CommentInterfaceInfo commentInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.updateCommentInterfaceInfo ,parameters : [ commentInterfaceInfo  =  " + commentInterfaceInfo + "]");
    }
    try {
      CommentInterfaceInfo oldCommentInterfaceInfo = commentInterfaceInfoPersisent.getCommentInterfaceInfoByPrimaryKey(commentInterfaceInfo.getCommentInterfaceId());
      if (oldCommentInterfaceInfo == null || (!oldCommentInterfaceInfo.getVersion().equals(oldCommentInterfaceInfo.getVersion()))) {
        throw new WechatTicketException("修改影评接口信息失败：原影评接口信息不存在，或者数据版本不一致。");
      }
      boolean isHaveData = false;
      CommentInterfaceInfo queryCommentInterfaceInfo = new CommentInterfaceInfo();
      queryCommentInterfaceInfo.setCommentInterfaceName(commentInterfaceInfo.getCommentInterfaceName());
      Collection<CommentInterfaceInfo> commentInterfaceInfoList = commentInterfaceInfoPersisent.getCommentInterfaceInfoByObject(queryCommentInterfaceInfo);
      if (!commentInterfaceInfoList.isEmpty() && commentInterfaceInfoList.size() > 1) {
        isHaveData = true;
      } else {
        if (!commentInterfaceInfoList.isEmpty() && !oldCommentInterfaceInfo.getCommentInterfaceId().equals(commentInterfaceInfoList.iterator().next().getCommentInterfaceId())) {
          isHaveData = true;
        }
      }
      if (isHaveData) {
        throw new WechatTicketException("修改影评接口信息失败：影评接口信息[" + commentInterfaceInfo.getCommentInterfaceId() + "]已经存在");
      }
      commentInterfaceInfo.setVersion(commentInterfaceInfo.getVersion() + 1);
      commentInterfaceInfoPersisent.updateCommentInterfaceInfo(commentInterfaceInfo);
    } catch (WechatTicketException e) {
      // e.printStackTrace();
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "修改影评接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void removeCommentInterfaceInfo(CommentInterfaceInfo commentInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.removeCommentInterfaceInfo ,parameters : [ commentInterfaceInfo  =  " + commentInterfaceInfo + "]");
    }
    try {
      commentInterfaceInfoPersisent.removeCommentInterfaceInfo(commentInterfaceInfo);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "删除影评接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public CommentInterfaceInfo getCommentInterfaceInfoByPrimaryKey(String commentInterfaceId) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.getCommentInterfaceInfoByPrimaryKey ,parameters : [ commentInterfaceId  =  " + commentInterfaceId + "]");
    }
    try {
      return commentInterfaceInfoPersisent.getCommentInterfaceInfoByPrimaryKey(commentInterfaceId);
    } catch (WechatTicketException e) {
      e.printStackTrace();
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "通过主键查询影评接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public Collection<CommentInterfaceInfo> getCommentInterfaceInfoByObject(CommentInterfaceInfo commentInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.getCommentInterfaceInfoByObject ,parameters : [ commentInterfaceInfo  =  " + commentInterfaceInfo + "]");
    }
    try {
      System.out.println("------" + commentInterfaceInfo);
      return commentInterfaceInfoPersisent.getCommentInterfaceInfoByObject(commentInterfaceInfo);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "通过对象查询影票订单信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  // 影票订单信息

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void saveTicketOrderInfo(TicketOrderInfo ticketOrderInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.saveTicketOrderInfo ,parameters : [ ticketOrderInfo  =  " + ticketOrderInfo + "]");
    }
    try {
      /*
      TicketOrderInfo queryTicketOrderInfo = new TicketOrderInfo();
      queryTicketOrderInfo.setCinemaName(ticketOrderInfo.getCinemaName());
      Collection<TicketOrderInfo> ticketOrderInfoList = ticketOrderInfoPersisent.getTicketOrderInfoByObject(queryTicketOrderInfo);
      if (!ticketOrderInfoList.isEmpty()) {
        throw new WechatTicketException("创建影评接口信息失败：影评[" + ticketOrderInfo.getMovieName() + "]已经存在");
      }
      */
      ticketOrderInfoPersisent.saveTicketOrderInfo(ticketOrderInfo);
    } catch (WechatTicketException e) {
      ticketOrderInfo.setTicketOrderInfoId(null);
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "创建影票订单信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void updateTicketOrderInfo(TicketOrderInfo ticketOrderInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.updateTicketOrderInfo ,parameters : [ ticketOrderInfo  =  " + ticketOrderInfo + "]");
    }
    try {
      TicketOrderInfo oldTicketOrderInfo = ticketOrderInfoPersisent.getTicketOrderInfoByPrimaryKey(ticketOrderInfo.getTicketOrderInfoId());
      if (oldTicketOrderInfo == null || (!oldTicketOrderInfo.getVersion().equals(oldTicketOrderInfo.getVersion()))) {
        throw new WechatTicketException("修改影票订单信息失败：原影票订单信息不存在，或者数据版本不一致。");
      }
      boolean isHaveData = false;
      TicketOrderInfo queryTicketOrderInfo = new TicketOrderInfo();
      queryTicketOrderInfo.setCinemaName(ticketOrderInfo.getCinemaName());
      Collection<TicketOrderInfo> ticketOrderInfoList = ticketOrderInfoPersisent.getTicketOrderInfoByObject(queryTicketOrderInfo);
      if (!ticketOrderInfoList.isEmpty() && ticketOrderInfoList.size() > 1) {
        isHaveData = true;
      } else {
        if (!ticketOrderInfoList.isEmpty() && !oldTicketOrderInfo.getTicketOrderInfoId().equals(ticketOrderInfoList.iterator().next().getTicketOrderInfoId())) {
          isHaveData = true;
        }
      }
      if (isHaveData) {
        throw new WechatTicketException("修改影票订单信息失败：影评接口信息[" + ticketOrderInfo.getTicketOrderInfoId() + "]已经存在");
      }
      ticketOrderInfo.setVersion(ticketOrderInfo.getVersion() + 1);
      ticketOrderInfoPersisent.updateTicketOrderInfo(ticketOrderInfo);
    } catch (WechatTicketException e) {
      // e.printStackTrace();
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "修改影票订单信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public void removeTicketOrderInfo(TicketOrderInfo ticketOrderInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.removeTicketOrderInfo ,parameters : [ ticketOrderInfo  =  " + ticketOrderInfo + "]");
    }
    try {
      ticketOrderInfoPersisent.removeTicketOrderInfo(ticketOrderInfo);

    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "删除应票订单信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public TicketOrderInfo getTicketOrderInfoByPrimaryKey(String ticketOrderInfoId) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.getTicketOrderInfoByPrimaryKey ,parameters : [ ticketOrderInfoId  =  " + ticketOrderInfoId + "]");
    }
    try {
      return ticketOrderInfoPersisent.getTicketOrderInfoByPrimaryKey(ticketOrderInfoId);
    } catch (WechatTicketException e) {
      e.printStackTrace();
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "通过主键查询影评接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  public Collection<TicketOrderInfo> getTicketOrderInfoByObject(TicketOrderInfo ticketOrderInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatTicketServic.getTicketOrderInfoByObject ,parameters : [ ticketOrderInfo  =  " + ticketOrderInfo + "]");
    }
    try {
      System.out.println("------" + ticketOrderInfo);
      return ticketOrderInfoPersisent.getTicketOrderInfoByObject(ticketOrderInfo);
    } catch (WechatTicketException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      String errorMessage = "通过对象查询影评接口信息失败：业务逻辑错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }
}
