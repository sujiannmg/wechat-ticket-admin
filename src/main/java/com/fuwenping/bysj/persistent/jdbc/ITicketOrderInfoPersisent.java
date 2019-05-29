package com.fuwenping.bysj.persistent.jdbc;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.TicketOrderInfo;

import java.util.Collection;

/**
 * 该接口是完成对数据库表WCT_TICKET_ORDER_INFO的持久化申明，包括对该表的增、删、改、查等基本操作的接口定义。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public interface ITicketOrderInfoPersisent {

  public void saveTicketOrderInfo(TicketOrderInfo ticketOrderInfo) throws WechatTicketException;

  public void updateTicketOrderInfo(TicketOrderInfo ticketOrderInfo) throws WechatTicketException;

  public void removeTicketOrderInfo(TicketOrderInfo ticketOrderInfo) throws WechatTicketException;

  public TicketOrderInfo getTicketOrderInfoByPrimaryKey(String ticketOrderInfoId) throws WechatTicketException;

  public Collection<TicketOrderInfo> getTicketOrderInfoByObject(TicketOrderInfo ticketOrderInfo) throws WechatTicketException;
}
