package com.fuwenping.bysj.persistent.jdbc;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.CinemaInterfaceInfo;
import com.fuwenping.bysj.entity.MovieInterfaceInfo;

import java.util.Collection;

/**
 * 该接口是完成对数据库表WCT_CINEMA_INTERFACE_INFO的持久化申明，包括对该表的增、删、改、查等基本操作的接口定义。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public interface ICinemaInterfaceInfoPersistent {

  public void saveCinemaInterfaceInfo(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException;

  public void updateCinemaInterfaceInfo(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException;

  public void removeCinemaInterfaceInfo(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException;

  public CinemaInterfaceInfo getCinemaInterfaceInfoByPrimaryKey(String cinemaInterfaceId) throws WechatTicketException;

  public Collection<CinemaInterfaceInfo> getCinemaInterfaceInfoByObject(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException;
}
