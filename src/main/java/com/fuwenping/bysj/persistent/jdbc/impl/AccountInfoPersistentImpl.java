package com.fuwenping.bysj.persistent.jdbc.impl;

import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.entity.AccountInfo;
import com.fuwenping.bysj.persistent.jdbc.IAccountInfoPersistent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.UUID;

/**
 * 该类是完成对数据库表WCT_ACCOUNT_INFO的持久化实现，包括对该表的增、删、改、查等基本操作的的具休实现。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
@org.springframework.stereotype.Repository("AccountInfoPersistent")
public class AccountInfoPersistentImpl extends BasePersistentImpl implements IAccountInfoPersistent {

  private static final Log log = LogFactory.getLog(AccountInfoPersistentImpl.class);
  private static final String TABLE_NAME = "WCT_ACCOUNT_INFO";

  private static final String COLUMN_ACCOUNT_ID = "ACCOUNT_ID";
  private static final String COLUMN_ACCOUNT = "ACCOUNT";
  private static final String COLUMN_PASSWORD = "PASSWORD";
  private static final String COLUMN_DESCRIPTION = "DESCRIPTION";

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
    COLUMNS.add(COLUMN_ACCOUNT_ID);
    COLUMNS.add(COLUMN_ACCOUNT);
    COLUMNS.add(COLUMN_PASSWORD);
    COLUMNS.add(COLUMN_DESCRIPTION);

    PRIMARY_KEY.add(COLUMN_ACCOUNT_ID);

    COLUMNS_PARAMETER.put(COLUMN_ACCOUNT_ID, "accountId");
    COLUMNS_PARAMETER.put(COLUMN_ACCOUNT, "account");
    COLUMNS_PARAMETER.put(COLUMN_PASSWORD, "password");
    COLUMNS_PARAMETER.put(COLUMN_DESCRIPTION, "description");
    COLUMNS_PARAMETER.putAll(BASE_COLUMNS_PARAMETER);

    NOT_INSERTABLE_COLUMNS.addAll(BASE_NOT_INSERTABLE_COLUMNS);
    NOT_UPDATABLE_COLUMNS.addAll(BASE_NOT_UPDATABLE_COLUMNS);

    INSERT_SQL = generateInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, NOT_INSERTABLE_COLUMNS);
    UPDATE_SQL = generateUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, NOT_UPDATABLE_COLUMNS);
    DELETE_SQL = generateDeleteSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_BASE_SQL = generateBaseSelectSql(TABLE_NAME, COLUMNS, PRIMARY_KEY);
  }

  @Override
  public void saveAccountInfo(AccountInfo accountInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call AccountInfoPersistent.saveAccountInfo ,parameters : [ accountInfo  =  \" + accountInfo + \"]");
    }
    try {
      // 得到随机的UUID
      accountInfo.setAccountId(UUID.randomUUID().toString());
      accountInfo.setVersion("1l");
      this.namedParameterJdbcTemplate.update(INSERT_SQL.toString(), new BeanPropertySqlParameterSource(accountInfo));
    } catch (Exception e) {
      String errorMessage = "创建账号失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public void updateAccountInfo(AccountInfo accountInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call AccountInfoPersistent.updateAccountInfo ,parameters : [ accountInfo  =  \" + accountInfo + \"]");
    }
    try {
        this.namedParameterJdbcTemplate.update(UPDATE_SQL.toString(), new BeanPropertySqlParameterSource(accountInfo));
    } catch (Exception e) {
      String errorMessage = "修改账号失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public void removeAccountInfo(AccountInfo accountInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call AccountInfoPersistent.removeAccountInfo ,parameters : [ accountInfo  =  \" + accountInfo + \"]");
    }
    try {
      this.namedParameterJdbcTemplate.update(DELETE_SQL.toString(), new BeanPropertySqlParameterSource(accountInfo));
    } catch (Exception e) {
      String errorMessage = "删除账号失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }

  }

  @Override
  public AccountInfo getAccountInfoByPrimaryKey(String accountId) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call AccountInfoPersistent.getAccountInfoByPrimaryKey ,parameters : [ accountId  =  \" + accountId + \"]");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      sql.append("AND").append(COLUMN_ACCOUNT_ID).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_ACCOUNT_ID));
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("accountId", accountId);
      Collection<AccountInfo> accountList = this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(AccountInfo.class));
      return (accountList != null && !accountList.isEmpty() && accountList.size() > 0) ? accountList.iterator().next() : null;
    } catch (Exception e) {
      String errorMessage = "通过主键查询帐号失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }

  @Override
  public Collection<AccountInfo> getAccountInfoByObject(AccountInfo accountInfo) throws WechatTicketException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call AccountInfoPersistent.getAccountInfoByPrimaryKey ,parameters : [ accountId  =  \" + accountId + \"]");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (accountInfo.getAccount() != null) {
        sql.append(" AND ").append(COLUMN_ACCOUNT).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_ACCOUNT));
      }
      if (accountInfo.getDescription() != null) {
        sql.append(" AND ").append(COLUMN_DESCRIPTION).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_DESCRIPTION));
      }
      return this.namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertySqlParameterSource(accountInfo), BeanPropertyRowMapper.newInstance(AccountInfo.class));
    } catch (Exception e) {
      String errorMessage = "通过对象查询帐号失败：数据库操作错误";
      log.error(errorMessage, e);
      throw new WechatTicketException(errorMessage);
    }
  }
}
