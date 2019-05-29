package com.fuwenping.bysj.persistent.jdbc;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.CommentInterfaceInfo;

import java.util.Collection;

/**
 * 该接口是完成对数据库表WCT_COMMENT_INTERFACE_INFO的持久化申明，包括对该表的增、删、改、查等基本操作的接口定义。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public interface ICommentInterfaceInfoPersistent {

  public void saveCommentInterfaceInfo(CommentInterfaceInfo commentInterfaceInfo) throws WechatTicketException;

  public void updateCommentInterfaceInfo(CommentInterfaceInfo commentInterfaceInfo) throws WechatTicketException;

  public void removeCommentInterfaceInfo(CommentInterfaceInfo commentInterfaceInfo) throws WechatTicketException;

  public CommentInterfaceInfo getCommentInterfaceInfoByPrimaryKey(String commentInterfaceId) throws WechatTicketException;

  public Collection<CommentInterfaceInfo> getCommentInterfaceInfoByObject(CommentInterfaceInfo commentInterfaceInfo) throws WechatTicketException;
}
