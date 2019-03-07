package com.fuwenping.bysj.persistent.jdbc.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 该类是完成对基础实体类的具休实现。
 *
 * @author 付文萍
 * @version 0.0.1-RELEASE
 */
public class BasePersistentImpl {

  // 声明NamedParameterJdbcTemplate
  protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @javax.annotation.Resource
  public void setDataSource(DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  protected static final LinkedHashSet<String> BASE_COLUMNS = new LinkedHashSet<String>();
  protected static final LinkedHashMap<String, String> BASE_COLUMNS_PARAMETER = new LinkedHashMap<String, String>();

  protected static final LinkedHashSet<String> BASE_NOT_INSERTABLE_COLUMNS = new LinkedHashSet<String>();
  protected static final LinkedHashSet<String> BASE_NOT_UPDATABLE_COLUMNS = new LinkedHashSet<String>();

  // 静态代码段
  static {
    BASE_COLUMNS.add("VERSION");
    BASE_COLUMNS.add("CREATE_TIME");
    BASE_COLUMNS.add("CREATE_USERNAME");
    BASE_COLUMNS.add("MODIFY_TIME");
    BASE_COLUMNS.add("MODIFY_USERNAME");

    BASE_COLUMNS_PARAMETER.put("VERSION", "version");
    BASE_COLUMNS_PARAMETER.put("CREATE_TIME", "createTime");
    BASE_COLUMNS_PARAMETER.put("CREATE_USERNAME", "createUsername");
    BASE_COLUMNS_PARAMETER.put("MODIFY_TIME", "modifyTime");
    BASE_COLUMNS_PARAMETER.put("MODIFY_USERNAME", "modifyUsername");

    BASE_NOT_INSERTABLE_COLUMNS.add("MODIFY_TIME");
    BASE_NOT_INSERTABLE_COLUMNS.add("MODIFY_USERNAME");

    BASE_NOT_UPDATABLE_COLUMNS.add("CREATE_TIME");
    BASE_NOT_UPDATABLE_COLUMNS.add("CREATE_USERNAME");
  }

  protected static final StringBuilder generateInsertSql(String tableName, Set<String> columns, Map<String, String> columnsParameter, Set<String> notInsertableColumns) {
    StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append('(');
    StringBuilder values = new StringBuilder();
    boolean isFirst = true;
    for (String column : columns) {
      if (notInsertableColumns.contains(column)) {
        continue;
      }
      if (isFirst) {
        isFirst = false;
      } else {
        sql.append(',');
        values.append(',');
      }
      sql.append(column);
      values.append(':').append(columnsParameter.get(column));
    }
    sql.append(") VALUES (");
    sql.append(values);
    sql.append(')');
    return sql;
  }

  protected static final StringBuilder generateUpdateSql(String tableName, Set<String> columns, Map<String, String> columnsParameter, Set<String> primaryKey,
                                                         Set<String> notUpdatableColumns) {
    StringBuilder sql = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
    boolean isFirst = true;
    for (String column : columns) {
      if (notUpdatableColumns.contains(column)) {
        continue;
      }
      if (primaryKey.contains(column)) {
        continue;
      }
      if (isFirst) {
        isFirst = false;
      } else {
        sql.append(',');
      }
      sql.append(column).append('=').append(':').append(columnsParameter.get(column));
    }
    sql.append(" WHERE ");
    isFirst = true;
    for (String column : primaryKey) {
      if (isFirst) {
        isFirst = false;
      } else {
        sql.append(" AND ");
      }
      sql.append(column).append('=').append(':').append(columnsParameter.get(column));
    }
    return sql;
  }

  protected static final StringBuilder generateDeleteSql(String tableName, Map<String, String> columnsParameter, Set<String> primaryKey) {
    StringBuilder sql = new StringBuilder("DELETE FROM ").append(tableName).append(" WHERE ");
    boolean isFirst = true;
    for (String column : primaryKey) {
      if (isFirst) {
        isFirst = false;
      } else {
        sql.append(" AND ");
      }
      sql.append(column).append('=').append(':').append(columnsParameter.get(column));
    }
    return sql;
  }

  protected static final StringBuilder generateBaseSelectSql(String tableName, Set<String> columns, Set<String> primaryKey) {
    StringBuilder sql = new StringBuilder("SELECT ");
    boolean isFirst = true;
    for (String column : columns) {
      if (isFirst) {
        isFirst = false;
      } else {
        sql.append(',');
      }
      sql.append(column);
    }
    sql.append(" FROM ").append(tableName).append(" WHERE ");
    isFirst = true;
    for (String column : primaryKey) {
      if (isFirst) {
        isFirst = false;
      } else {
        sql.append(" AND ");
      }
      sql.append(column).append(" IS NOT NULL ");
    }
    return sql;
  }
}

