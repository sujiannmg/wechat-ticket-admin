package com.fuwenping.bysj.persistent.jdbc;


import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.AccountInfo;

import java.util.Collection;

/**
 * 该接口是完成对数据库表WCT_ACCOUNT_INFO的持久化申明，包括对该表的增、删、改、查等基本操作的接口定义。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public interface IAccountInfoPersistent {

  public void saveAccountInfo(AccountInfo accountInfo) throws WechatTicketException;

  public void updateAccountInfo(AccountInfo accountInfo) throws WechatTicketException;

  public void removeAccountInfo(AccountInfo accountInfo) throws WechatTicketException;

  public AccountInfo getAccountInfoByPrimaryKey(String accountId) throws WechatTicketException;

  public Collection<AccountInfo> getAccountInfoByObject(AccountInfo accountInfo) throws WechatTicketException;
}
