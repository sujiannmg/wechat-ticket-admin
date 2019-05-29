package com.fuwenping.bysj.entity;

/**
 * 系统帐号的实体类，关联的表名为WCT_COMMENT_INTERFACE_INFO。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public class CommentInterfaceInfo extends BaseEntity {

  private static final long serialVersionUID = 1L;

  protected String commentInterfaceId; // 影评接口编号
  protected String commentInterfaceName; // 影评接口名称
  protected String commentInterfaceType; // 影评接口类型
  protected String commentInterfaceUrl; // 影评接口地址
  protected String commentInterfacePaprm; // 影评接口参数
  protected String commentInterfaceAscii; // 影评接口编码
  protected int commentInterfaceCode; // 接口响应状态
  protected int commentReponseLength; // 响应内容长度
  protected String commentInterfaceContent; // 接口响应内容
  protected int commentReponseNum; // 调用接口次数
  protected String commentReponseTime; // 调用接口时间

  public String getCommentInterfaceId() {
    return commentInterfaceId;
  }

  public void setCommentInterfaceId(String commentInterfaceId) {
    this.commentInterfaceId = commentInterfaceId;
  }

  public String getCommentInterfaceName() {
    return commentInterfaceName;
  }

  public void setCommentInterfaceName(String commentInterfaceName) {
    this.commentInterfaceName = commentInterfaceName;
  }

  public String getCommentInterfaceType() {
    return commentInterfaceType;
  }

  public void setCommentInterfaceType(String commentInterfaceType) {
    this.commentInterfaceType = commentInterfaceType;
  }

  public String getCommentInterfaceUrl() {
    return commentInterfaceUrl;
  }

  public void setCommentInterfaceUrl(String commentInterfaceUrl) {
    this.commentInterfaceUrl = commentInterfaceUrl;
  }

  public String getCommentInterfacePaprm() {
    return commentInterfacePaprm;
  }

  public void setCommentInterfacePaprm(String commentInterfacePaprm) {
    this.commentInterfacePaprm = commentInterfacePaprm;
  }

  public String getCommentInterfaceAscii() {
    return commentInterfaceAscii;
  }

  public void setCommentInterfaceAscii(String commentInterfaceAscii) {
    this.commentInterfaceAscii = commentInterfaceAscii;
  }

  public int getCommentInterfaceCode() {
    return commentInterfaceCode;
  }

  public void setCommentInterfaceCode(int commentInterfaceCode) {
    this.commentInterfaceCode = commentInterfaceCode;
  }

  public int getCommentReponseLength() {
    return commentReponseLength;
  }

  public void setCommentReponseLength(int commentReponseLength) {
    this.commentReponseLength = commentReponseLength;
  }

  public String getCommentInterfaceContent() {
    return commentInterfaceContent;
  }

  public void setCommentInterfaceContent(String commentInterfaceContent) {
    this.commentInterfaceContent = commentInterfaceContent;
  }

  public int getCommentReponseNum() {
    return commentReponseNum;
  }

  public void setCommentReponseNum(int commentReponseNum) {
    this.commentReponseNum = commentReponseNum;
  }

  public String getCommentReponseTime() {
    return commentReponseTime;
  }

  public void setCommentReponseTime(String commentReponseTime) {
    this.commentReponseTime = commentReponseTime;
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
