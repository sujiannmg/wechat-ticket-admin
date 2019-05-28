package com.fuwenping.bysj.persistent.jdbc.impl;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.AccountInfo;
import com.fuwenping.bysj.entity.WechatUserInfo;
import com.fuwenping.bysj.persistent.jdbc.IWechatUserInfoPersisent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 该类是完成对数据库表WCT_USER_INFO的持久化实现，包括对该表的增、删、改、查等基本操作的的具休实现。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
@org.springframework.stereotype.Repository("WechatUserInfoPersisent")
public class WechatUserInfoPersisentImpl extends BasePersistentImpl implements IWechatUserInfoPersisent {

  private static final Log log = LogFactory.getLog(WechatUserInfoPersisentImpl.class);
  private static final String TABLE_NAME = "WCT_USER_INFO";

  private static final String COLUMN_OPEN_ID = "OPEN_ID";
  private static final String COLUMN_NICK_NAME = "NICK_NAME";
  private static final String COLUMN_GENDER = "GENDER";
  private static final String COLUMN_CITY = "CITY";
  private static final String COLUMN_PROVINCE = "PROVINCE";
  private static final String COLUMN_AVATAR_URL = "AVATAR_URL";
  private static final String COLUMN_UNION_ID = "UNION_ID";

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
    COLUMNS.add(COLUMN_OPEN_ID);
    COLUMNS.add(COLUMN_NICK_NAME);
    COLUMNS.add(COLUMN_GENDER);
    COLUMNS.add(COLUMN_CITY);
    COLUMNS.add(COLUMN_PROVINCE);
    COLUMNS.add(COLUMN_AVATAR_URL);
    COLUMNS.add(COLUMN_UNION_ID);
    COLUMNS.addAll(BASE_COLUMNS);

    PRIMARY_KEY.add(COLUMN_OPEN_ID);

    COLUMNS_PARAMETER.put(COLUMN_OPEN_ID, "openId");
    COLUMNS_PARAMETER.put(COLUMN_NICK_NAME, "nickName");
    COLUMNS_PARAMETER.put(COLUMN_GENDER, "gender");
    COLUMNS_PARAMETER.put(COLUMN_CITY, "city");
    COLUMNS_PARAMETER.put(COLUMN_PROVINCE, "province");
    COLUMNS_PARAMETER.put(COLUMN_AVATAR_URL, "avatarUrl");
    COLUMNS_PARAMETER.put(COLUMN_UNION_ID, "unionId");
    COLUMNS_PARAMETER.putAll(BASE_COLUMNS_PARAMETER);

    NOT_INSERTABLE_COLUMNS.addAll(BASE_NOT_INSERTABLE_COLUMNS);
    NOT_UPDATABLE_COLUMNS.addAll(BASE_NOT_UPDATABLE_COLUMNS);

    INSERT_SQL = generateInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, NOT_INSERTABLE_COLUMNS);
    UPDATE_SQL = generateUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, NOT_UPDATABLE_COLUMNS);
    DELETE_SQL = generateDeleteSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_BASE_SQL = generateBaseSelectSql(TABLE_NAME, COLUMNS, PRIMARY_KEY);
  }

  @Override
  public void saveWechatUserInfo(WechatUserInfo wechatUserInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatUserInfoPersisent.saveWechatUserInfo ,parameters : [ wechatUserInfo  =  \" + wechatUserInfo + \"]");
    }
    try {
      // 得到随机的UUID
      wechatUserInfo.setOpenId(UUID.randomUUID().toString());
      wechatUserInfo.setVersion(1l);
      wechatUserInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      this.namedParameterJdbcTemplate.update(INSERT_SQL.toString(), new BeanPropertySqlParameterSource(wechatUserInfo));
      System.out.println("-------------" + INSERT_SQL.toString() + "--------------");
    } catch (Exception e) {
      String errorMessage = "创建微信用户失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public void updateWechatUserInfo(WechatUserInfo wechatUserInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatUserInfoPersisent.updateWechatUserInfo ,parameters : [ wechatUserInfo  =  \" + wechatUserInfo + \"]");
    }
    try {
      wechatUserInfo.setModifyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      this.namedParameterJdbcTemplate.update(UPDATE_SQL.toString(), new BeanPropertySqlParameterSource(wechatUserInfo));
    } catch (Exception e) {
      String errorMessage = "修改微信用户失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public void removeWechatUserInfo(WechatUserInfo wechatUserInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatUserInfoPersisent.removeWechatUserInfo ,parameters : [ wechatUserInfo  =  \" + wechatUserInfo + \"]");
    }
    try {
      this.namedParameterJdbcTemplate.update(DELETE_SQL.toString(), new BeanPropertySqlParameterSource(wechatUserInfo));
    } catch (Exception e) {
      String errorMessage = "删除微信用户：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public WechatUserInfo getWechatUserInfoByPrimaryKey(String openId) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatUserInfoPersisent.getWechatUserInfoByPrimaryKey ,parameters : [ openId  =  \" + openId + \"]");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      sql.append("AND ").append(COLUMN_OPEN_ID).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_OPEN_ID));
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("openId", openId);
      System.out.println("***********" + sql.toString() + "*********");
      Collection<WechatUserInfo> wechatUserInfoList = this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(WechatUserInfo.class));
      return (wechatUserInfoList != null && !wechatUserInfoList.isEmpty() && wechatUserInfoList.size() > 0) ? wechatUserInfoList.iterator().next() : null;
    } catch (Exception e) {
      String errorMessage = "通过主键查询微信用户失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public Collection<WechatUserInfo> getWechatUserInfoByObject(WechatUserInfo wechatUserInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WechatUserInfoPersisent.getWechatUserInfoByObject ,parameters : [ wechatUserInfo  =  \" + wechatUserInfo + \"]");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (wechatUserInfo.getNickName() != null) {
        sql.append(" AND ").append(COLUMN_NICK_NAME).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_NICK_NAME));
      }
      if (wechatUserInfo.getGender() != null) {
        sql.append(" AND ").append(COLUMN_GENDER).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_GENDER));
      }
      return this.namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertySqlParameterSource(wechatUserInfo), BeanPropertyRowMapper.newInstance(WechatUserInfo.class));
    } catch (Exception e) {
      String errorMessage = "通过对象查询微信用户失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }
}
