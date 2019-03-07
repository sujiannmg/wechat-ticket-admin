package com.fuwenping.bysj.service.spring;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.AccountInfo;

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
}
