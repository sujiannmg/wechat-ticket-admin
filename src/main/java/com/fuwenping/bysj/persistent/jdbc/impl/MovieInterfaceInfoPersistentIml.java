package com.fuwenping.bysj.persistent.jdbc.impl;


import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.AccountInfo;
import com.fuwenping.bysj.entity.MovieInterfaceInfo;
import com.fuwenping.bysj.persistent.jdbc.IMovieInterfaceInfoPersistent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 该类是完成对数据库表WCT_MOVIE_INTERFACE_INFO的持久化实现，包括对该表的增、删、改、查等基本操作的的具休实现。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */

@org.springframework.stereotype.Repository("MovieInterfaceInfoPersistent")
public class MovieInterfaceInfoPersistentIml extends BasePersistentImpl implements IMovieInterfaceInfoPersistent {

  private static final Log log = LogFactory.getLog(MovieInterfaceInfoPersistentIml.class);
  private static final String TABLE_NAME = "WCT_MOVIE_INTERFACE_INFO";

  private static final String COLUMN_MOVIE_INTERFACE_ID = "MOVIE_INTERFACE_ID";
  private static final String COLUMN_MOVIE_INTERFACE_NAME = "MOVIE_INTERFACE_NAME";
  private static final String COLUMN_MOVIE_INTERFACE_TYPE = "MOVIE_INTERFACE_TYPE";
  private static final String COLUMN_MOVIE_INTERFACE_URL = "MOVIE_INTERFACE_URL";
  private static final String COLUMN_MOVIE_INTERFACE_PAPRM = "MOVIE_INTERFACE_PAPRM";
  private static final String COLUMN_MOVIE_INTERFACE_ASCII = "MOVIE_INTERFACE_ASCII";
  private static final String COLUMN_MOVIE_INTERFACE_CODE = "MOVIE_INTERFACE_CODE";
  private static final String COLUMN_MOVIE_REPONSE_LENGTH = "MOVIE_REPONSE_LENGTH";
  private static final String COLUMN_MOVIE_INTERFACE_CONTENT = "MOVIE_INTERFACE_CONTENT";
  private static final String COLUMN_MOVIE_REPONSE_NUM = "MOVIE_REPONSE_NUM";
  private static final String COLUMN_MOVIE_REPONSE_TIME = "MOVIE_REPONSE_TIME";

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
    COLUMNS.add(COLUMN_MOVIE_INTERFACE_ID);
    COLUMNS.add(COLUMN_MOVIE_INTERFACE_NAME);
    COLUMNS.add(COLUMN_MOVIE_INTERFACE_TYPE);
    COLUMNS.add(COLUMN_MOVIE_INTERFACE_URL);
    COLUMNS.add(COLUMN_MOVIE_INTERFACE_PAPRM);
    COLUMNS.add(COLUMN_MOVIE_INTERFACE_ASCII);
    COLUMNS.add(COLUMN_MOVIE_INTERFACE_CODE);
    COLUMNS.add(COLUMN_MOVIE_REPONSE_LENGTH);
    COLUMNS.add(COLUMN_MOVIE_INTERFACE_CONTENT);
    COLUMNS.add(COLUMN_MOVIE_REPONSE_NUM);
    COLUMNS.add(COLUMN_MOVIE_REPONSE_TIME);
    COLUMNS.addAll(BASE_COLUMNS);

    PRIMARY_KEY.add(COLUMN_MOVIE_INTERFACE_ID);

    COLUMNS_PARAMETER.put(COLUMN_MOVIE_INTERFACE_ID, "movieInterfaceId");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_INTERFACE_NAME, "movieInterfaceName");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_INTERFACE_TYPE, "movieInterfaceType");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_INTERFACE_URL, "movieInterfaceUrl");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_INTERFACE_PAPRM, "movieInterfacePaprm");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_INTERFACE_ASCII, "movieInterfaceAscii");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_INTERFACE_CODE, "movieInterfaceCode");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_REPONSE_LENGTH, "movieReponseLength");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_INTERFACE_CONTENT, "movieinterfaceContent");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_REPONSE_NUM, "movieReponseNum");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_REPONSE_TIME, "movieReponseTime");
    COLUMNS_PARAMETER.putAll(BASE_COLUMNS_PARAMETER);

    NOT_INSERTABLE_COLUMNS.addAll(BASE_NOT_INSERTABLE_COLUMNS);
    NOT_UPDATABLE_COLUMNS.addAll(BASE_NOT_UPDATABLE_COLUMNS);

    INSERT_SQL = generateInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, NOT_INSERTABLE_COLUMNS);
    UPDATE_SQL = generateUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, NOT_UPDATABLE_COLUMNS);
    DELETE_SQL = generateDeleteSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_BASE_SQL = generateBaseSelectSql(TABLE_NAME, COLUMNS, PRIMARY_KEY);
  }

  @Override
  public void saveMovieInterfaceInfo(MovieInterfaceInfo movieInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call MovieInterfaceInfoPersistent.saveMovieInterfaceInfo ,parameters : [ movieInterfaceInfo  =  \" + movieInterfaceInfo + \"]");
    }
    try {
      // 得到随机的UUID
      // movieInterfaceInfo.setMovieInterfaceId(UUID.randomUUID().toString());
      movieInterfaceInfo.setVersion(1l);
      movieInterfaceInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      this.namedParameterJdbcTemplate.update(INSERT_SQL.toString(), new BeanPropertySqlParameterSource(movieInterfaceInfo));
      System.out.println("-------------" + INSERT_SQL.toString() + "--------------");
    } catch (Exception e) {
      String errorMessage = "创建电影接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public void updateMovieInterfaceInfo(MovieInterfaceInfo movieInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call MovieInterfaceInfoPersistent.updateMovieInterfaceInfo ,parameters : [ movieInterfaceInfo  =  \" + movieInterfaceInfo + \"]");
    }
    try {
      movieInterfaceInfo.setModifyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      this.namedParameterJdbcTemplate.update(UPDATE_SQL.toString(), new BeanPropertySqlParameterSource(movieInterfaceInfo));
    } catch (Exception e) {
      String errorMessage = "修改电影接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public void removeMovieInterfaceInfo(MovieInterfaceInfo movieInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call MovieInterfaceInfoPersistent.movieInterfaceInfo ,parameters : [ movieInterfaceInfo  =  \" + movieInterfaceInfo + \"]");
    }
    try {
      this.namedParameterJdbcTemplate.update(DELETE_SQL.toString(), new BeanPropertySqlParameterSource(movieInterfaceInfo));
    } catch (Exception e) {
      String errorMessage = "删除电影接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public MovieInterfaceInfo getMovieInterfaceInfoByPrimaryKey(String movieInterfaceId) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call MovieInterfaceInfoPersistent.getMovieInterfaceInfoByPrimaryKey ,parameters : [ movieInterfaceId  =  \" + movieInterfaceId + \"]");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      sql.append("AND ").append(COLUMN_MOVIE_INTERFACE_ID).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_MOVIE_INTERFACE_ID));
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("movieInterfaceId", movieInterfaceId);
      System.out.println("***********" + sql.toString() + "*********");
      Collection<MovieInterfaceInfo> movieInterfaceInfoList = this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(MovieInterfaceInfo.class));
      return (movieInterfaceInfoList != null && !movieInterfaceInfoList.isEmpty() && movieInterfaceInfoList.size() > 0) ? movieInterfaceInfoList.iterator().next() : null;
    } catch (Exception e) {
      String errorMessage = "通过主键查询电影接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public Collection<MovieInterfaceInfo> getMovieInterfaceInfoByObject(MovieInterfaceInfo movieInterfaceInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call MovieInterfaceInfoPersistent.getMovieInterfaceInfoByObject ,parameters : [ movieInterfaceInfo  =  \" + movieInterfaceInfo + \"]");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (movieInterfaceInfo.getMovieInterfaceName() != null) {
        sql.append(" AND ").append(COLUMN_MOVIE_INTERFACE_NAME).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_MOVIE_INTERFACE_NAME));
      }
      if (movieInterfaceInfo.getMovieInterfaceType() != null) {
        sql.append(" AND ").append(COLUMN_MOVIE_INTERFACE_TYPE).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_MOVIE_INTERFACE_TYPE));
      }
      if (movieInterfaceInfo.getMovieInterfaceUrl() != null) {
        sql.append(" AND ").append(COLUMN_MOVIE_INTERFACE_URL).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_MOVIE_INTERFACE_URL));
      }
      return this.namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertySqlParameterSource(movieInterfaceInfo), BeanPropertyRowMapper.newInstance(MovieInterfaceInfo.class));
    } catch (Exception e) {
      String errorMessage = "通过对象查询电影接口信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }
}