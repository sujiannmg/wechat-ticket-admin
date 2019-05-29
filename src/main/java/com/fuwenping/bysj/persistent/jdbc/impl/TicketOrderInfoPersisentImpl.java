package com.fuwenping.bysj.persistent.jdbc.impl;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.CommentInterfaceInfo;
import com.fuwenping.bysj.entity.TicketOrderInfo;
import com.fuwenping.bysj.persistent.jdbc.ITicketOrderInfoPersisent;
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

@org.springframework.stereotype.Repository("TicketOrderInfoPersisent")
public class TicketOrderInfoPersisentImpl extends BasePersistentImpl implements ITicketOrderInfoPersisent {

  private static final Log log = LogFactory.getLog(TicketOrderInfoPersisentImpl.class);
  private static final String TABLE_NAME = "WCT_TICKET_ORDER_INFO";

  private static final String COLUMN_TICKET_ORDER_INFO_ID = "TICKET_ORDER_INFO_ID";
  private static final String COLUMN_CINEMA_NAME = "CINEMA_NAME";
  private static final String COLUMN_CINEMA_ADDRESS = "CINEMA_ADDRESS";
  private static final String COLUMN_MOVIE_NAME = "MOVIE_NAME";
  private static final String COLUMN_MOVIE_TIME = "MOVIE_TIME";
  private static final String COLUMN_MOVIE_LABEL = "MOVIE_LABEL";
  private static final String COLUMN_CINEMA_SPECIFIC_ADDRESS = "CINEMA_SPECIFIC_ADDRESS";
  private static final String COLUMN_MOVIE_ORDER_NUM = "MOVIE_ORDER_NUM";
  private static final String COLUMN_USER_PHONE_NUM = "USER_PHONE_NUM";
  private static final String COLUMN_MOVIE_SERIAL_NUM = "MOVIE_SERIAL_NUM";
  private static final String COLUMN_ORDER_VERIFICATION_NUM = "ORDER_VERIFICATION_NUM";
  private static final String COLUMN_ORDER_SUM_PRICE = "ORDER_SUM_PRICE";

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
    COLUMNS.add(COLUMN_TICKET_ORDER_INFO_ID);
    COLUMNS.add(COLUMN_CINEMA_NAME);
    COLUMNS.add(COLUMN_CINEMA_ADDRESS);
    COLUMNS.add(COLUMN_MOVIE_NAME);
    COLUMNS.add(COLUMN_MOVIE_TIME);
    COLUMNS.add(COLUMN_MOVIE_LABEL);
    COLUMNS.add(COLUMN_CINEMA_SPECIFIC_ADDRESS);
    COLUMNS.add(COLUMN_MOVIE_ORDER_NUM);
    COLUMNS.add(COLUMN_USER_PHONE_NUM);
    COLUMNS.add(COLUMN_MOVIE_SERIAL_NUM);
    COLUMNS.add(COLUMN_ORDER_VERIFICATION_NUM);
    COLUMNS.add(COLUMN_ORDER_SUM_PRICE);
    COLUMNS.addAll(BASE_COLUMNS);

    PRIMARY_KEY.add(COLUMN_TICKET_ORDER_INFO_ID);

    COLUMNS_PARAMETER.put(COLUMN_TICKET_ORDER_INFO_ID, "ticketOrderInfoId");
    COLUMNS_PARAMETER.put(COLUMN_CINEMA_NAME, "cinemaName");
    COLUMNS_PARAMETER.put(COLUMN_CINEMA_ADDRESS, "cinemaAddress");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_NAME, "movieName");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_TIME, "movieTime");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_LABEL, "movieLabel");
    COLUMNS_PARAMETER.put(COLUMN_CINEMA_SPECIFIC_ADDRESS, "cinemaSpecificAddress");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_ORDER_NUM, "movieOrderNum");
    COLUMNS_PARAMETER.put(COLUMN_USER_PHONE_NUM, "userPhoneNum");
    COLUMNS_PARAMETER.put(COLUMN_MOVIE_SERIAL_NUM, "movieSerialNum");
    COLUMNS_PARAMETER.put(COLUMN_ORDER_VERIFICATION_NUM, "orderVerificationNum");
    COLUMNS_PARAMETER.put(COLUMN_ORDER_SUM_PRICE, "orderSumPrice");
    COLUMNS_PARAMETER.putAll(BASE_COLUMNS_PARAMETER);

    NOT_INSERTABLE_COLUMNS.addAll(BASE_NOT_INSERTABLE_COLUMNS);
    NOT_UPDATABLE_COLUMNS.addAll(BASE_NOT_UPDATABLE_COLUMNS);

    INSERT_SQL = generateInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, NOT_INSERTABLE_COLUMNS);
    UPDATE_SQL = generateUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, NOT_UPDATABLE_COLUMNS);
    DELETE_SQL = generateDeleteSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_BASE_SQL = generateBaseSelectSql(TABLE_NAME, COLUMNS, PRIMARY_KEY);
  }

  @Override
  public void saveTicketOrderInfo(TicketOrderInfo ticketOrderInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call TicketOrderInfoPersisentImpl.saveTicketOrderInfo ,parameters : [ ticketOrderInfo  =  \" + ticketOrderInfo + \"]");
    }
    try {
      // 得到随机的UUID
      ticketOrderInfo.setTicketOrderInfoId(UUID.randomUUID().toString());
      ticketOrderInfo.setVersion(1l);
      ticketOrderInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      this.namedParameterJdbcTemplate.update(INSERT_SQL.toString(), new BeanPropertySqlParameterSource(ticketOrderInfo));
      System.out.println("-------------" + INSERT_SQL.toString() + "--------------");
    } catch (Exception e) {
      String errorMessage = "创建影票信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public void updateTicketOrderInfo(TicketOrderInfo ticketOrderInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call TicketOrderInfoPersisentImpl.updateTicketOrderInfo ,parameters : [ ticketOrderInfo  =  \" + ticketOrderInfo + \"]");
    }
    try {
      ticketOrderInfo.setModifyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      this.namedParameterJdbcTemplate.update(UPDATE_SQL.toString(), new BeanPropertySqlParameterSource(ticketOrderInfo));
    } catch (Exception e) {
      String errorMessage = "修改影票信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public void removeTicketOrderInfo(TicketOrderInfo ticketOrderInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call TicketOrderInfoPersisentImpl.removeTicketOrderInfo ,parameters : [ ticketOrderInfo  =  \" + ticketOrderInfo + \"]");
    }
    try {
      this.namedParameterJdbcTemplate.update(DELETE_SQL.toString(), new BeanPropertySqlParameterSource(ticketOrderInfo));
    } catch (Exception e) {
      String errorMessage = "删除影票信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public TicketOrderInfo getTicketOrderInfoByPrimaryKey(String ticketOrderInfoId) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call TicketOrderInfoPersisentImpl.getTicketOrderInfoByPrimaryKey ,parameters : [ ticketOrderInfoId  =  \" + ticketOrderInfoId + \"]");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      sql.append("AND ").append(COLUMN_TICKET_ORDER_INFO_ID).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_TICKET_ORDER_INFO_ID));
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("ticketOrderInfoId", ticketOrderInfoId);
      System.out.println("***********" + sql.toString() + "*********");
      Collection<TicketOrderInfo> ticketOrderInfoList = this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(TicketOrderInfo.class));
      return (ticketOrderInfoList != null && !ticketOrderInfoList.isEmpty() && ticketOrderInfoList.size() > 0) ? ticketOrderInfoList.iterator().next() : null;
    } catch (Exception e) {
      String errorMessage = "通过主键查询影票信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public Collection<TicketOrderInfo> getTicketOrderInfoByObject(TicketOrderInfo ticketOrderInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call TicketOrderInfoPersisentImpl.getTicketOrderInfoByObject ,parameters : [ ticketOrderInfo  =  \" + ticketOrderInfo + \"]");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (ticketOrderInfo.getCinemaName() != null) {
        sql.append(" AND ").append(COLUMN_CINEMA_NAME).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_CINEMA_NAME));
      }
      if (ticketOrderInfo.getMovieName() != null) {
        sql.append(" AND ").append(COLUMN_MOVIE_NAME).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_MOVIE_NAME));
      }
      return this.namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertySqlParameterSource(ticketOrderInfo), BeanPropertyRowMapper.newInstance(TicketOrderInfo.class));
    } catch (Exception e) {
      String errorMessage = "通过对象查询影票信息失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }
}
