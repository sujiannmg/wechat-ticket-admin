package com.fuwenping.bysj.entity;

/**
 * 系统帐号的实体类，关联的表名为WCT_USER_INFO。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */

public class WechatUserInfo extends BaseEntity {

  private static final long serialVersionUID = 1L;

  protected String openId; // 用户编号
  protected String nickName; // 用户昵称
  protected String gender; // 用户性别
  protected String city; // 用户城市
  protected String province; // 用户省会
  protected String avatarUrl; // 头像路径
  protected String unionId; // 统一标识

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getUnionId() {
    return unionId;
  }

  public void setUnionId(String unionId) {
    this.unionId = unionId;
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
