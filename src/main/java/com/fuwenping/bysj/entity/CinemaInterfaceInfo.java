package com.fuwenping.bysj.entity;

/**
 * 电影接口信息的实体类，关联的表名为WCT_CINEMA_INTERFACE_INFO。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public class CinemaInterfaceInfo extends BaseEntity {

  private static final long serialVersionUID = 1L;

  protected String cinemaInterfaceId; // 影院接口编号
  protected String cinemaInterfaceName; // 影院接口名称
  protected String cinemaInterfaceType; // 影院接口类型
  protected String cinemaInterfaceUrl; // 影院接口地址
  protected String cinemaInterfacePaprm; // 影院接口参数
  protected String cinemaInterfaceAscii; // 影院接口编码
  protected int cinemaInterfaceCode; // 接口响应状态
  protected int cinemaReponseLength; // 响应内容长度
  protected String cinemaInterfaceContent; // 接口响应内容
  protected int cinemaReponseNum; // 调用接口次数
  protected String cinemaReponseTime; // 调用接口时间

  public String getCinemaInterfaceId() {
    return cinemaInterfaceId;
  }

  public void setCinemaInterfaceId(String cinemaInterfaceId) {
    this.cinemaInterfaceId = cinemaInterfaceId;
  }

  public String getCinemaInterfaceName() {
    return cinemaInterfaceName;
  }

  public void setCinemaInterfaceName(String cinemaInterfaceName) {
    this.cinemaInterfaceName = cinemaInterfaceName;
  }

  public String getCinemaInterfaceType() {
    return cinemaInterfaceType;
  }

  public void setCinemaInterfaceType(String cinemaInterfaceType) {
    this.cinemaInterfaceType = cinemaInterfaceType;
  }

  public String getCinemaInterfaceUrl() {
    return cinemaInterfaceUrl;
  }

  public void setCinemaInterfaceUrl(String cinemaInterfaceUrl) {
    this.cinemaInterfaceUrl = cinemaInterfaceUrl;
  }

  public String getCinemaInterfacePaprm() {
    return cinemaInterfacePaprm;
  }

  public void setCinemaInterfacePaprm(String cinemaInterfacePaprm) {
    this.cinemaInterfacePaprm = cinemaInterfacePaprm;
  }

  public String getCinemaInterfaceAscii() {
    return cinemaInterfaceAscii;
  }

  public void setCinemaInterfaceAscii(String cinemaInterfaceAscii) {
    this.cinemaInterfaceAscii = cinemaInterfaceAscii;
  }

  public int getCinemaInterfaceCode() {
    return cinemaInterfaceCode;
  }

  public void setCinemaInterfaceCode(int cinemaInterfaceCode) {
    this.cinemaInterfaceCode = cinemaInterfaceCode;
  }

  public int getCinemaReponseLength() {
    return cinemaReponseLength;
  }

  public void setCinemaReponseLength(int cinemaReponseLength) {
    this.cinemaReponseLength = cinemaReponseLength;
  }

  public String getCinemaInterfaceContent() {
    return cinemaInterfaceContent;
  }

  public void setCinemaInterfaceContent(String cinemaInterfaceContent) {
    this.cinemaInterfaceContent = cinemaInterfaceContent;
  }

  public int getCinemaReponseNum() {
    return cinemaReponseNum;
  }

  public void setCinemaReponseNum(int cinemaReponseNum) {
    this.cinemaReponseNum = cinemaReponseNum;
  }

  public String getCinemaReponseTime() {
    return cinemaReponseTime;
  }

  public void setCinemaReponseTime(String cinemaReponseTime) {
    this.cinemaReponseTime = cinemaReponseTime;
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

