package com.fuwenping.bysj.service.spring;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.AccountInfo;

/**
 * 该接口是对以下对象操作的接口。
 * <b>系统账号登录</b>
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public interface IWechatTicketAuthorizeService {

  public AccountInfo login(String account, String password) throws WechatTicketException;
}
