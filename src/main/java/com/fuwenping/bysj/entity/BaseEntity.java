package com.fuwenping.bysj.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基础实体类
 *
 *  @author 付文萍
 *  @version 0.0.1-RELEASE
 */
public abstract class BaseEntity implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected Long version; // 业务版本
  protected String createTime; // 创建时间
  protected String createUsername; // 创建账号
  protected String modifyTime; // 修改时间
  protected String modifyUsername; // 修改账号

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) { this.createTime = createTime; }

  public String getCreateUsername() {
    return createUsername;
  }

  public void setCreateUsername(String createUsername) {
    this.createUsername = createUsername;
  }

  public String getModifyTime() {
    return modifyTime;
  }

  public void setModifyTime(String modifyTime) { this.modifyTime = modifyTime; }

  public String getModifyUsername() {
    return modifyUsername;
  }

  public void setModifyUsername(String modifyUsername) {
    this.modifyUsername = modifyUsername;
  }
}
