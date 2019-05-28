package com.fuwenping.bysj.service.spring;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.AccountInfo;
import com.fuwenping.bysj.entity.CinemaInterfaceInfo;
import com.fuwenping.bysj.entity.MovieInterfaceInfo;
import com.fuwenping.bysj.entity.WechatUserInfo;

import java.util.Collection;

/**
 * 该接口是对以下对象操作的接口。
 * <b>系统账号</b>
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public interface IWechatTicketService {

  // AccountInfo
  public void saveAccountInfo(AccountInfo accountInfo) throws WechatTicketException;

  public void updateAccountInfo(AccountInfo accountInfo) throws WechatTicketException;

  public void removeAccountInfo(AccountInfo accountInfo) throws WechatTicketException;

  public AccountInfo getAccountInfoByPrimaryKey(String accountId) throws WechatTicketException;

  public Collection<AccountInfo> getAccountInfoByObject(AccountInfo accountInfo) throws WechatTicketException;

  // MovieInterfaceInfo
  public void saveMovieInterfaceInfo(MovieInterfaceInfo movieInterfaceInfo) throws WechatTicketException;

  public void updateMovieInterfaceInfo(MovieInterfaceInfo movieInterfaceInfo) throws WechatTicketException;

  public void removeMovieInterfaceInfo(MovieInterfaceInfo movieInterfaceInfo) throws WechatTicketException;

  public MovieInterfaceInfo getMovieInterfaceInfoByPrimaryKey(String movieInterfaceId) throws WechatTicketException;

  public Collection<MovieInterfaceInfo> getMovieInterfaceInfoByObject(MovieInterfaceInfo movieInterfaceInfo) throws WechatTicketException;

  // CinemaInterfaceInfo
  public void saveCinemaInterfaceInfo(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException;

  public void updateCinemaInterfaceInfo(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException;

  public void removeCinemaInterfaceInfo(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException;

  public CinemaInterfaceInfo getCinemaInterfaceInfoByPrimaryKey(String cinemaInterfaceId) throws WechatTicketException;

  public Collection<CinemaInterfaceInfo> getCinemaInterfaceInfoByObject(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException;

  // WechatUserInfo
  public void saveWechatUserInfo(WechatUserInfo wechatUserInfo) throws WechatTicketException;

  public void updateWechatUserInfo(WechatUserInfo wechatUserInfo) throws WechatTicketException;

  public void removeWechatUserInfo(WechatUserInfo wechatUserInfo) throws WechatTicketException;

  public WechatUserInfo getWechatUserInfoByPrimaryKey(String openId) throws WechatTicketException;

  public Collection<WechatUserInfo> getWechatUserInfoByObject(WechatUserInfo wechatUserInfo) throws WechatTicketException;
}
