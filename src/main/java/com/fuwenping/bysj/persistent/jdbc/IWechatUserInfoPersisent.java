package com.fuwenping.bysj.persistent.jdbc;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.WechatUserInfo;

import java.util.Collection;

/**
 * 该接口是完成对数据库表WCT_USER_INFO的持久化申明，包括对该表的增、删、改、查等基本操作的接口定义。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public interface IWechatUserInfoPersisent {

  public void saveWechatUserInfo(WechatUserInfo wechatUserInfo) throws WechatTicketException;

  public void updateWechatUserInfo(WechatUserInfo wechatUserInfo) throws WechatTicketException;

  public void removeWechatUserInfo(WechatUserInfo wechatUserInfo) throws WechatTicketException;

  public WechatUserInfo getWechatUserInfoByPrimaryKey(String openId) throws WechatTicketException;

  public Collection<WechatUserInfo> getWechatUserInfoByObject(WechatUserInfo wechatUserInfo) throws WechatTicketException;
}
