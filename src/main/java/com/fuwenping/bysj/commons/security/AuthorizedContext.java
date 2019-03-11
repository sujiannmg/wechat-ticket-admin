package com.fuwenping.bysj.commons.security;

import com.fuwenping.bysj.entity.AccountInfo;

public class AuthorizedContext {

  // 使用ThreadLocal（线程本地变量）来避免线程需要竞争同一个共享变量
  private final static ThreadLocal<AccountInfo> accountThreadLocal = new ThreadLocal<AccountInfo>();

  public final static void setAccountInfo(AccountInfo accountInfo) {
    // set()用来设置当前线程中变量的副本
    accountThreadLocal.set(accountInfo);
  }

  public final static AccountInfo getAccountInfo() {
    // get()方法是用来获取ThreadLocal在当前线程中保存的变量副本
    return accountThreadLocal.get();
  }

  public final static void removeAccountInfo() {
    // remove()用来移除当前线程中变量的副本
    accountThreadLocal.remove();
  }

  public final static String getSignature() {
    return getAccountInfo().getAccountId();
  }
}
