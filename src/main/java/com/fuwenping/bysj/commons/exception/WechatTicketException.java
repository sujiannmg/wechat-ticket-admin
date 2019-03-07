package com.fuwenping.bysj.commons.exception;

/**
 * 异常类
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public class WechatTicketException extends Exception {

  public WechatTicketException() {
    super();
  }

  public WechatTicketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public WechatTicketException(String message, Throwable cause) {
    super(message, cause);
  }

  public WechatTicketException(String message) {
    super(message);
  }

  public WechatTicketException(Throwable cause) {
    super(cause);
  }
}
