package com.fuwenping.bysj.persistent.jdbc.impl;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.CinemaInterfaceInfo;
import com.fuwenping.bysj.entity.CommentInterfaceInfo;
import com.fuwenping.bysj.persistent.jdbc.ICommentInterfaceInfoPersistent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 该类是完成对数据库表WCT_COMMENT_INTERFACE_INFO的持久化实现，包括对该表的增、删、改、查等基本操作的的具休实现。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */

@org.springframework.stereotype.Repository("CommentInterfaceInfoPersisent")
public class CommentInterfaceInfoPersisentImpl extends BasePersistentImpl implements ICommentInterfaceInfoPersistent {

  private static final Log log = LogFactory.getLog(CommentInterfaceInfoPersisentImpl.class);
  private static final String TABLE_NAME = "WCT_COMMENT_INTERFACE_INFO";

  private static final String COLUMN_COMMENT_INTERFACE_ID = "COMMENT_INTERFACE_ID";
  private static final String COLUMN_COMMENT_INTERFACE_NAME = "COMMENT_INTERFACE_NAME";
  private static final String COLUMN_COMMENT_INTERFACE_TYPE = "COMMENT_INTERFACE_TYPE";
  private static final String COLUMN_COMMENT_INTERFACE_URL = "COMMENT_INTERFACE_URL";
  private static final String COLUMN_COMMENT_INTERFACE_PAPRM = "COMMENT_INTERFACE_PAPRM";
  private static final String COLUMN_COMMENT_INTERFACE_ASCII = "COMMENT_INTERFACE_ASCII";
  private static final String COLUMN_COMMENT_INTERFACE_CODE = "COMMENT_INTERFACE_CODE";
  private static final String COLUMN_COMMENT_REPONSE_LENGTH = "COMMENT_REPONSE_LENGTH";
  private static final String COLUMN_COMMENT_INTERFACE_CONTENT = "COMMENT_INTERFACE_CONTENT";
  private static final String COLUMN_COMMENT_REPONSE_NUM = "COMMENT_REPONSE_NUM";
  private static final String COLUMN_COMMENT_REPONSE_TIME = "COMMENT_REPONSE_TIME";

  private static final LinkedHashSet<String> COLUMNS = new LinkedHashSet<String>();
  private static final LinkedHashSet<String> PRIMARY_KEY = new LinkedHashSet<String>();
  private static final LinkedHashMap<String, String> COLUMNS_PARAMETER = new LinkedHashMap<String, String>();

  private static final LinkedHashSet<String> NOT_INSERTABLE_COLUMNS = new LinkedHashSet<String>();
  private static final LinkedHashSet<String> NOT_UPDATABLE_COLUMNS = new LinkedHashSet<String>();

  private static final StringBuilder INSERT_SQL;
  private static final StringBuilder UPDATE_SQL;
  private static final StringBuilder DELETE_SQL;
  private static final StringBuilder SELECT_BASE_SQL;

  static {
    COLUMNS.add(COLUMN_COMMENT_INTERFACE_ID);
    COLUMNS.add(COLUMN_COMMENT_INTERFACE_NAME);
    COLUMNS.add(COLUMN_COMMENT_INTERFACE_TYPE);
    COLUMNS.add(COLUMN_COMMENT_INTERFACE_URL);
    COLUMNS.add(COLUMN_COMMENT_INTERFACE_PAPRM);
    COLUMNS.add(COLUMN_COMMENT_INTERFACE_ASCII);
    COLUMNS.add(COLUMN_COMMENT_INTERFACE_CODE);
    COLUMNS.add(COLUMN_COMMENT_REPONSE_LENGTH);
    COLUMNS.add(COLUMN_COMMENT_INTERFACE_CONTENT);
    COLUMNS.add(COLUMN_COMMENT_REPONSE_NUM);
    COLUMNS.add(COLUMN_COMMENT_REPONSE_TIME);
    COLUMNS.addAll(BASE_COLUMNS);

    PRIMARY_KEY.add(COLUMN_COMMENT_INTERFACE_ID);

    COLUMNS_PARAMETER.put(COLUMN_COMMENT_INTERFACE_ID, "commentInterfaceId");
    COLUMNS_PARAMETER.put(COLUMN_COMMENT_INTERFACE_NAME, "commentInterfaceName");
    COLUMNS_PARAMETER.put(COLUMN_COMMENT_INTERFACE_TYPE, "commentInterfaceType");
    COLUMNS_PARAMETER.put(COLUMN_COMMENT_INTERFACE_URL, "commentInterfaceUrl");
    COLUMNS_PARAMETER.put(COLUMN_COMMENT_INTERFACE_PAPRM, "commentInterfacePaprm");
    COLUMNS_PARAMETER.put(COLUMN_COMMENT_INTERFACE_ASCII, "commentInterfaceAscii");
    COLUMNS_PARAMETER.put(COLUMN_COMMENT_INTERFACE_CODE, "commentInterfaceCode");
    COLUMNS_PARAMETER.put(COLUMN_COMMENT_REPONSE_LENGTH, "commentReponseLength");
    COLUMNS_PARAMETER.put(COLUMN_COMMENT_INTERFACE_CONTENT, "commentInterfaceContent");
    COLUMNS_PARAMETER.put(COLUMN_COMMENT_REPONSE_NUM, "commentReponseNum");
    COLUMNS_PARAMETER.put(COLUMN_COMMENT_REPONSE_TIME, "commentReponseTime");
    COLUMNS_PARAMETER.putAll(BASE_COLUMNS_PARAMETER);

    NOT_INSERTABLE_COLUMNS.addAll(BASE_NOT_INSERTABLE_COLUMNS);
    NOT_UPDATABLE_COLUMNS.addAll(BASE_NOT_UPDATABLE_COLUMNS);

    INSERT_SQL = generateInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, NOT_INSERTABLE_COLUMNS);
    UPDATE_SQL = generateUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, NOT_UPDATABLE_COLUMNS);
    DELETE_SQL = generateDeleteSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_BASE_SQL = generateBaseSelectSql(TABLE_NAME, COLUMNS, PRIMARY_KEY);
  }

  @Override
  public void saveCommentInterfaceInfo(CommentInterfaceInfo commentInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CommentInterfaceInfoPersisentImpl.saveCommentInterfaceInfo ,parameters : [ commentInterfaceInfo  =  \" + commentInterfaceInfo + \"]");
    }
    try {
      // 得到随机的UUID
      commentInterfaceInfo.setCommentInterfaceId(UUID.randomUUID().toString());
      commentInterfaceInfo.setVersion(1l);
      commentInterfaceInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      this.namedParameterJdbcTemplate.update(INSERT_SQL.toString(), new BeanPropertySqlParameterSource(commentInterfaceInfo));
      System.out.println("-------------" + INSERT_SQL.toString() + "--------------");
    } catch (Exception e) {
      String errorMessage = "创建影评接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public void updateCommentInterfaceInfo(CommentInterfaceInfo commentInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CommentInterfaceInfoPersisentImpl.updateCommentInterfaceInfo ,parameters : [ commentInterfaceInfo  =  \" + commentInterfaceInfo + \"]");
    }
    try {
      commentInterfaceInfo.setModifyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      this.namedParameterJdbcTemplate.update(UPDATE_SQL.toString(), new BeanPropertySqlParameterSource(commentInterfaceInfo));
    } catch (Exception e) {
      String errorMessage = "修改影评接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public void removeCommentInterfaceInfo(CommentInterfaceInfo commentInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CommentInterfaceInfoPersisentImpl.removeCommentInterfaceInfo ,parameters : [ commentInterfaceInfo  =  \" + commentInterfaceInfo + \"]");
    }
    try {
      this.namedParameterJdbcTemplate.update(DELETE_SQL.toString(), new BeanPropertySqlParameterSource(commentInterfaceInfo));
    } catch (Exception e) {
      String errorMessage = "删除影评接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public CommentInterfaceInfo getCommentInterfaceInfoByPrimaryKey(String commentInterfaceId) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CommentInterfaceInfoPersisentImpl.getCommentInterfaceInfoByPrimaryKey ,parameters : [ commentInterfaceId  =  \" + commentInterfaceId + \"]");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      sql.append("AND ").append(COLUMN_COMMENT_INTERFACE_ID).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_COMMENT_INTERFACE_ID));
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("commentInterfaceId", commentInterfaceId);
      System.out.println("***********" + sql.toString() + "*********");
      Collection<CommentInterfaceInfo> commentInterfaceInfoList = this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(CommentInterfaceInfo.class));
      return (commentInterfaceInfoList != null && !commentInterfaceInfoList.isEmpty() && commentInterfaceInfoList.size() > 0) ? commentInterfaceInfoList.iterator().next() : null;
    } catch (Exception e) {
      String errorMessage = "通过主键查询影评接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public Collection<CommentInterfaceInfo> getCommentInterfaceInfoByObject(CommentInterfaceInfo commentInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CommentInterfaceInfoPersisentImpl.getCommentInterfaceInfoByObject ,parameters : [ commentInterfaceInfo  =  \" + commentInterfaceInfo + \"]");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (commentInterfaceInfo.getCommentInterfaceName() != null) {
        sql.append(" AND ").append(COLUMN_COMMENT_INTERFACE_NAME).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_COMMENT_INTERFACE_NAME));
      }
      if (commentInterfaceInfo.getCommentInterfaceType() != null) {
        sql.append(" AND ").append(COLUMN_COMMENT_INTERFACE_TYPE).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_COMMENT_INTERFACE_TYPE));
      }
      if (commentInterfaceInfo.getCommentInterfaceUrl() != null) {
        sql.append(" AND ").append(COLUMN_COMMENT_INTERFACE_URL).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_COMMENT_INTERFACE_URL));
      }
      return this.namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertySqlParameterSource(commentInterfaceInfo), BeanPropertyRowMapper.newInstance(CommentInterfaceInfo.class));
    } catch (Exception e) {
      String errorMessage = "通过对象查询影评接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }
}
