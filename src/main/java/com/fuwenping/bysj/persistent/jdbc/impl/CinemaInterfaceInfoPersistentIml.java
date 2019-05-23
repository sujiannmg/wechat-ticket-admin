package com.fuwenping.bysj.persistent.jdbc.impl;


import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.CinemaInterfaceInfo;
import com.fuwenping.bysj.entity.MovieInterfaceInfo;
import com.fuwenping.bysj.persistent.jdbc.ICinemaInterfaceInfoPersistent;
import com.fuwenping.bysj.persistent.jdbc.IMovieInterfaceInfoPersistent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 该类是完成对数据库表WCT_CINEMA_INTERFACE_INFO的持久化实现，包括对该表的增、删、改、查等基本操作的的具休实现。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */

@org.springframework.stereotype.Repository("CinemaInterfaceInfoPersistent")
public class CinemaInterfaceInfoPersistentIml extends BasePersistentImpl implements ICinemaInterfaceInfoPersistent {

  private static final Log log = LogFactory.getLog(CinemaInterfaceInfoPersistentIml.class);
  private static final String TABLE_NAME = "WCT_CINEMA_INTERFACE_INFO";

  private static final String COLUMN_CINEMA_INTERFACE_ID = "CINEMA_INTERFACE_ID";
  private static final String COLUMN_CINEMA_INTERFACE_NAME = "CINEMA_INTERFACE_NAME";
  private static final String COLUMN_CINEMA_INTERFACE_TYPE = "CINEMA_INTERFACE_TYPE";
  private static final String COLUMN_CINEMA_INTERFACE_URL = "CINEMA_INTERFACE_URL";
  private static final String COLUMN_CINEMA_INTERFACE_PAPRM = "CINEMA_INTERFACE_PAPRM";
  private static final String COLUMN_CINEMA_INTERFACE_ASCII = "CINEMA_INTERFACE_ASCII";
  private static final String COLUMN_CINEMA_INTERFACE_CODE = "CINEMA_INTERFACE_CODE";
  private static final String COLUMN_CINEMA_REPONSE_LENGTH = "CINEMA_REPONSE_LENGTH";
  private static final String COLUMN_CINEMA_INTERFACE_CONTENT = "CINEMA_INTERFACE_CONTENT";
  private static final String COLUMN_CINEMA_REPONSE_NUM = "CINEMA_REPONSE_NUM";
  private static final String COLUMN_CINEMA_REPONSE_TIME = "CINEMA_REPONSE_TIME";

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
    COLUMNS.add(COLUMN_CINEMA_INTERFACE_ID);
    COLUMNS.add(COLUMN_CINEMA_INTERFACE_NAME);
    COLUMNS.add(COLUMN_CINEMA_INTERFACE_TYPE);
    COLUMNS.add(COLUMN_CINEMA_INTERFACE_URL);
    COLUMNS.add(COLUMN_CINEMA_INTERFACE_PAPRM);
    COLUMNS.add(COLUMN_CINEMA_INTERFACE_ASCII);
    COLUMNS.add(COLUMN_CINEMA_INTERFACE_CODE);
    COLUMNS.add(COLUMN_CINEMA_REPONSE_LENGTH);
    COLUMNS.add(COLUMN_CINEMA_INTERFACE_CONTENT);
    COLUMNS.add(COLUMN_CINEMA_REPONSE_NUM);
    COLUMNS.add(COLUMN_CINEMA_REPONSE_TIME);
    COLUMNS.addAll(BASE_COLUMNS);

    PRIMARY_KEY.add(COLUMN_CINEMA_INTERFACE_ID);

    COLUMNS_PARAMETER.put(COLUMN_CINEMA_INTERFACE_ID, "cinemaInterfaceId");
    COLUMNS_PARAMETER.put(COLUMN_CINEMA_INTERFACE_NAME, "cinemaInterfaceName");
    COLUMNS_PARAMETER.put(COLUMN_CINEMA_INTERFACE_TYPE, "cinemaInterfaceType");
    COLUMNS_PARAMETER.put(COLUMN_CINEMA_INTERFACE_URL, "cinemaInterfaceUrl");
    COLUMNS_PARAMETER.put(COLUMN_CINEMA_INTERFACE_PAPRM, "cinemaInterfacePaprm");
    COLUMNS_PARAMETER.put(COLUMN_CINEMA_INTERFACE_ASCII, "cinemaInterfaceAscii");
    COLUMNS_PARAMETER.put(COLUMN_CINEMA_INTERFACE_CODE, "cinemaInterfaceCode");
    COLUMNS_PARAMETER.put(COLUMN_CINEMA_REPONSE_LENGTH, "cinemaReponseLength");
    COLUMNS_PARAMETER.put(COLUMN_CINEMA_INTERFACE_CONTENT, "cinemaInterfaceContent");
    COLUMNS_PARAMETER.put(COLUMN_CINEMA_REPONSE_NUM, "cinemaReponseNum");
    COLUMNS_PARAMETER.put(COLUMN_CINEMA_REPONSE_TIME, "cinemaReponseTime");
    COLUMNS_PARAMETER.putAll(BASE_COLUMNS_PARAMETER);

    NOT_INSERTABLE_COLUMNS.addAll(BASE_NOT_INSERTABLE_COLUMNS);
    NOT_UPDATABLE_COLUMNS.addAll(BASE_NOT_UPDATABLE_COLUMNS);

    INSERT_SQL = generateInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, NOT_INSERTABLE_COLUMNS);
    UPDATE_SQL = generateUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, NOT_UPDATABLE_COLUMNS);
    DELETE_SQL = generateDeleteSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_BASE_SQL = generateBaseSelectSql(TABLE_NAME, COLUMNS, PRIMARY_KEY);
  }

  @Override
  public void saveCinemaInterfaceInfo(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CinemaInterfaceInfoPersistentIml.saveCinemaInterfaceInfo ,parameters : [ cinemaInterfaceInfo  =  \" + cinemaInterfaceInfo + \"]");
    }
    try {
      // 得到随机的UUID
      cinemaInterfaceInfo.setCinemaInterfaceId(UUID.randomUUID().toString());
      cinemaInterfaceInfo.setVersion(1l);
      cinemaInterfaceInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      this.namedParameterJdbcTemplate.update(INSERT_SQL.toString(), new BeanPropertySqlParameterSource(cinemaInterfaceInfo));
      System.out.println("-------------" + INSERT_SQL.toString() + "--------------");
    } catch (Exception e) {
      String errorMessage = "创建电影接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public void updateCinemaInterfaceInfo(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CinemaInterfaceInfoPersistentIml.updateCinemaInterfaceInfo ,parameters : [ cinemaInterfaceInfo  =  \" + cinemaInterfaceInfo + \"]");
    }
    try {
      cinemaInterfaceInfo.setModifyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      this.namedParameterJdbcTemplate.update(UPDATE_SQL.toString(), new BeanPropertySqlParameterSource(cinemaInterfaceInfo));
    } catch (Exception e) {
      String errorMessage = "修改电影接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public void removeCinemaInterfaceInfo(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CinemaInterfaceInfoPersistentIml.cinemaInterfaceInfo ,parameters : [ cinemaInterfaceInfo  =  \" + cinemaInterfaceInfo + \"]");
    }
    try {
      this.namedParameterJdbcTemplate.update(DELETE_SQL.toString(), new BeanPropertySqlParameterSource(cinemaInterfaceInfo));
    } catch (Exception e) {
      String errorMessage = "删除电影接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public CinemaInterfaceInfo getCinemaInterfaceInfoByPrimaryKey(String cinemaInterfaceId) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CinemaInterfaceInfoPersistentIml.getCinemaInterfaceInfoByPrimaryKey ,parameters : [ cinemaInterfaceId  =  \" + cinemaInterfaceId + \"]");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      sql.append("AND ").append(COLUMN_CINEMA_INTERFACE_ID).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_CINEMA_INTERFACE_ID));
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("cinemaInterfaceId", cinemaInterfaceId);
      System.out.println("***********" + sql.toString() + "*********");
      Collection<CinemaInterfaceInfo> cinemaInterfaceInfoList = this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(CinemaInterfaceInfo.class));
      return (cinemaInterfaceInfoList != null && !cinemaInterfaceInfoList.isEmpty() && cinemaInterfaceInfoList.size() > 0) ? cinemaInterfaceInfoList.iterator().next() : null;
    } catch (Exception e) {
      String errorMessage = "通过主键查询电影接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public Collection<CinemaInterfaceInfo> getCinemaInterfaceInfoByObject(CinemaInterfaceInfo cinemaInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CinemaInterfaceInfoPersistentIml.getCinemaInterfaceInfoByObject ,parameters : [ cinemaInterfaceInfo  =  \" + cinemaInterfaceInfo + \"]");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (cinemaInterfaceInfo.getCinemaInterfaceName() != null) {
        sql.append(" AND ").append(COLUMN_CINEMA_INTERFACE_NAME).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_CINEMA_INTERFACE_NAME));
      }
      if (cinemaInterfaceInfo.getCinemaInterfaceType() != null) {
        sql.append(" AND ").append(COLUMN_CINEMA_INTERFACE_TYPE).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_CINEMA_INTERFACE_TYPE));
      }
      if (cinemaInterfaceInfo.getCinemaInterfaceUrl() != null) {
        sql.append(" AND ").append(COLUMN_CINEMA_INTERFACE_URL).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_CINEMA_INTERFACE_URL));
      }
      return this.namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertySqlParameterSource(cinemaInterfaceInfo), BeanPropertyRowMapper.newInstance(CinemaInterfaceInfo.class));
    } catch (Exception e) {
      String errorMessage = "通过对象查询电影接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }
}