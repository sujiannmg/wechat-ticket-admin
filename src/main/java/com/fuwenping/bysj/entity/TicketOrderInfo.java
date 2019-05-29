package com.fuwenping.bysj.entity;

/**
 * 系统帐号的实体类，关联的表名为WCT_TICKET_ORDER_INFO。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public class TicketOrderInfo extends BaseEntity {

  private static final long serialVersionUID = 1L;

  protected String ticketOrderInfoId; // 影票订单编号
  protected String cinemaName; // 影院名称
  protected String cinemaAddress; // 影院位置
  protected String movieName; // 影片名称
  protected String movieTime; // 影片开始时间
  protected String movieLabel; // 影片标签
  protected String cinemaSpecificAddress; // 观影具体位置
  protected String movieOrderNum; // 影片订单号
  protected String userPhoneNum; // 用户手机号
  protected String movieSerialNum; // 影片流水号
  protected String orderVerificationNum; // 订单验证码
  protected String orderSumPrice; // 订单总价

  public String getTicketOrderInfoId() {
    return ticketOrderInfoId;
  }

  public void setTicketOrderInfoId(String ticketOrderInfoId) {
    this.ticketOrderInfoId = ticketOrderInfoId;
  }

  public String getCinemaName() {
    return cinemaName;
  }

  public void setCinemaName(String cinemaName) {
    this.cinemaName = cinemaName;
  }

  public String getCinemaAddress() {
    return cinemaAddress;
  }

  public void setCinemaAddress(String cinemaAddress) {
    this.cinemaAddress = cinemaAddress;
  }

  public String getMovieName() {
    return movieName;
  }

  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }

  public String getMovieTime() {
    return movieTime;
  }

  public void setMovieTime(String movieTime) {
    this.movieTime = movieTime;
  }

  public String getMovieLabel() {
    return movieLabel;
  }

  public void setMovieLabel(String movieLabel) {
    this.movieLabel = movieLabel;
  }

  public String getCinemaSpecificAddress() {
    return cinemaSpecificAddress;
  }

  public void setCinemaSpecificAddress(String cinemaSpecificAddress) {
    this.cinemaSpecificAddress = cinemaSpecificAddress;
  }

  public String getMovieOrderNum() {
    return movieOrderNum;
  }

  public void setMovieOrderNum(String movieOrderNum) {
    this.movieOrderNum = movieOrderNum;
  }

  public String getUserPhoneNum() {
    return userPhoneNum;
  }

  public void setUserPhoneNum(String userPhoneNum) {
    this.userPhoneNum = userPhoneNum;
  }

  public String getMovieSerialNum() {
    return movieSerialNum;
  }

  public void setMovieSerialNum(String movieSerialNum) {
    this.movieSerialNum = movieSerialNum;
  }

  public String getOrderVerificationNum() {
    return orderVerificationNum;
  }

  public void setOrderVerificationNum(String orderVerificationNum) {
    this.orderVerificationNum = orderVerificationNum;
  }

  public String getOrderSumPrice() {
    return orderSumPrice;
  }

  public void setOrderSumPrice(String orderSumPrice) {
    this.orderSumPrice = orderSumPrice;
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
