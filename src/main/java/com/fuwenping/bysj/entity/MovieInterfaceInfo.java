package com.fuwenping.bysj.entity;

/**
 * 电影接口信息的实体类，关联的表名为WCT_MOVIE_INTERFACE_INFO。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public class MovieInterfaceInfo extends BaseEntity {

  private static final long serialVersionUID = 1L;

  protected String movieInterfaceId; // 电影接口编号
  protected String movieInterfaceName; // 电影接口名称
  protected String movieInterfaceType; // 电影接口类型
  protected String movieInterfaceUrl; // 电影接口地址
  protected String movieInterfacePaprm; // 电影接口参数
  protected String movieInterfaceAscii; // 电影接口编码
  protected int movieInterfaceCode; // 接口响应状态
  protected int movieReponseLength; // 响应内容长度
  protected String movieinterfaceContent; // 接口响应内容
  protected int movieReponseNum; // 调用接口次数
  protected String movieReponseTime; // 调用接口时间

  public String getMovieInterfaceId() {
    return movieInterfaceId;
  }

  public void setMovieInterfaceId(String movieInterfaceId) {
    this.movieInterfaceId = movieInterfaceId;
  }

  public String getMovieInterfaceName() {
    return movieInterfaceName;
  }

  public void setMovieInterfaceName(String movieInterfaceName) {
    this.movieInterfaceName = movieInterfaceName;
  }

  public String getMovieInterfaceType() {
    return movieInterfaceType;
  }

  public void setMovieInterfaceType(String movieInterfaceType) {
    this.movieInterfaceType = movieInterfaceType;
  }

  public String getMovieInterfaceUrl() {
    return movieInterfaceUrl;
  }

  public void setMovieInterfaceUrl(String movieInterfaceUrl) {
    this.movieInterfaceUrl = movieInterfaceUrl;
  }

  public String getMovieInterfacePaprm() {
    return movieInterfacePaprm;
  }

  public void setMovieInterfacePaprm(String movieInterfacePaprm) {
    this.movieInterfacePaprm = movieInterfacePaprm;
  }

  public String getMovieInterfaceAscii() {
    return movieInterfaceAscii;
  }

  public void setMovieInterfaceAscii(String movieInterfaceAscii) {
    this.movieInterfaceAscii = movieInterfaceAscii;
  }

  public int getMovieInterfaceCode() {
    return movieInterfaceCode;
  }

  public void setMovieInterfaceCode(int movieInterfaceCode) {
    this.movieInterfaceCode = movieInterfaceCode;
  }

  public int getMovieReponseLength() {
    return movieReponseLength;
  }

  public void setMovieReponseLength(int movieReponseLength) {
    this.movieReponseLength = movieReponseLength;
  }

  public String getMovieinterfaceContent() {
    return movieinterfaceContent;
  }

  public void setMovieinterfaceContent(String movieinterfaceContent) {
    this.movieinterfaceContent = movieinterfaceContent;
  }

  public int getMovieReponseNum() {
    return movieReponseNum;
  }

  public void setMovieReponseNum(int movieReponseNum) {
    this.movieReponseNum = movieReponseNum;
  }

  public String getMovieReponseTime() {
    return movieReponseTime;
  }

  public void setMovieReponseTime(String movieReponseTime) {
    this.movieReponseTime = movieReponseTime;
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

