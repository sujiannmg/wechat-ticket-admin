package com.fuwenping.bysj.entity;

/**
 * 系统帐号的实体类，关联的表名为WCT_ACCOUNT_INFO。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */

public class AccountInfo extends BaseEntity {

  private static final long serialVersionUID = 1L;

  protected String accountId; // 系统帐号编号
  protected String account; // 系统帐号
  protected String password; // 系统密码
  protected String description; // 系统描述

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
  }

  @Override
  public boolean equals(Object object) {
    return org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals(this, object);
  }

  @Override
  public int hashCode() {
    return org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode(this);
  }
}
