package org.hsqldb.jdbc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.Type;

public class JDBCResultSetMetaData implements ResultSetMetaData {
  private ResultMetaData resultMetaData;
  
  private boolean useColumnName;
  
  private boolean translateTTIType;
  
  private int columnCount;
  
  public int getColumnCount() throws SQLException {
    return this.resultMetaData.getColumnCount();
  }
  
  public boolean isAutoIncrement(int paramInt) throws SQLException {
    checkColumn(paramInt);
    return this.resultMetaData.columns[--paramInt].isIdentity();
  }
  
  public boolean isCaseSensitive(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = translateType(this.resultMetaData.columnTypes[--paramInt]);
    return type.isCharacterType() ? type.getCollation().isCaseSensitive() : false;
  }
  
  public boolean isSearchable(int paramInt) throws SQLException {
    checkColumn(paramInt);
    return this.resultMetaData.columns[--paramInt].isSearchable();
  }
  
  public boolean isCurrency(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = translateType(this.resultMetaData.columnTypes[--paramInt]);
    return ((type.typeCode == 3 || type.typeCode == 2) && type.scale > 0);
  }
  
  public int isNullable(int paramInt) throws SQLException {
    checkColumn(paramInt);
    return this.resultMetaData.columns[--paramInt].getNullability();
  }
  
  public boolean isSigned(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = translateType(this.resultMetaData.columnTypes[--paramInt]);
    return type.isNumberType();
  }
  
  public int getColumnDisplaySize(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = translateType(this.resultMetaData.columnTypes[--paramInt]);
    return type.displaySize();
  }
  
  public String getColumnLabel(int paramInt) throws SQLException {
    checkColumn(paramInt--);
    String str = this.resultMetaData.columnLabels[paramInt];
    return (str != null && str.length() > 0) ? str : this.resultMetaData.columns[paramInt].getNameString();
  }
  
  public String getColumnName(int paramInt) throws SQLException {
    checkColumn(paramInt--);
    if (this.useColumnName) {
      String str1 = this.resultMetaData.columns[paramInt].getNameString();
      if (str1 != null && str1.length() > 0)
        return str1; 
    } 
    String str = this.resultMetaData.columnLabels[paramInt];
    return (str == null) ? this.resultMetaData.columns[paramInt].getNameString() : str;
  }
  
  public String getSchemaName(int paramInt) throws SQLException {
    checkColumn(paramInt);
    String str = this.resultMetaData.columns[--paramInt].getSchemaNameString();
    return (str == null) ? "" : str;
  }
  
  public int getPrecision(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = translateType(this.resultMetaData.columnTypes[--paramInt]);
    return type.getJDBCPrecision();
  }
  
  public int getScale(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = translateType(this.resultMetaData.columnTypes[--paramInt]);
    return type.getJDBCScale();
  }
  
  public String getTableName(int paramInt) throws SQLException {
    checkColumn(paramInt);
    String str = this.resultMetaData.columns[--paramInt].getTableNameString();
    return (str == null) ? "" : str;
  }
  
  public String getCatalogName(int paramInt) throws SQLException {
    checkColumn(paramInt);
    String str = this.resultMetaData.columns[--paramInt].getCatalogNameString();
    return (str == null) ? "" : str;
  }
  
  public int getColumnType(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = translateType(this.resultMetaData.columnTypes[--paramInt]);
    return type.getJDBCTypeCode();
  }
  
  public String getColumnTypeName(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = translateType(this.resultMetaData.columnTypes[--paramInt]);
    return type.getNameString();
  }
  
  public boolean isReadOnly(int paramInt) throws SQLException {
    checkColumn(paramInt);
    return !this.resultMetaData.columns[--paramInt].isWriteable();
  }
  
  public boolean isWritable(int paramInt) throws SQLException {
    checkColumn(paramInt);
    return (this.resultMetaData.colIndexes != null && this.resultMetaData.colIndexes[--paramInt] > -1);
  }
  
  public boolean isDefinitelyWritable(int paramInt) throws SQLException {
    checkColumn(paramInt);
    return (this.resultMetaData.colIndexes != null && this.resultMetaData.colIndexes[--paramInt] > -1);
  }
  
  public String getColumnClassName(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = translateType(this.resultMetaData.columnTypes[--paramInt]);
    return type.getJDBCClassName();
  }
  
  public <T> T unwrap(Class<T> paramClass) throws SQLException {
    if (isWrapperFor(paramClass))
      return (T)this; 
    throw JDBCUtil.invalidArgument("iface: " + paramClass);
  }
  
  public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
    return (paramClass != null && paramClass.isAssignableFrom(getClass()));
  }
  
  JDBCResultSetMetaData(ResultMetaData paramResultMetaData, boolean paramBoolean1, boolean paramBoolean2, JDBCConnection paramJDBCConnection) throws SQLException {
    init(paramResultMetaData, paramJDBCConnection);
  }
  
  void init(ResultMetaData paramResultMetaData, JDBCConnection paramJDBCConnection) throws SQLException {
    this.resultMetaData = paramResultMetaData;
    this.columnCount = this.resultMetaData.getColumnCount();
    this.useColumnName = true;
    if (paramJDBCConnection == null)
      return; 
    this.useColumnName = paramJDBCConnection.isUseColumnName;
    if (paramJDBCConnection.clientProperties != null)
      this.translateTTIType = paramJDBCConnection.clientProperties.isPropertyTrue("jdbc.translate_tti_types"); 
  }
  
  private void checkColumn(int paramInt) throws SQLException {
    if (paramInt < 1 || paramInt > this.columnCount)
      throw JDBCUtil.sqlException(421, String.valueOf(paramInt)); 
  }
  
  private Type translateType(Type paramType) {
    DateTimeType dateTimeType;
    if (this.translateTTIType) {
      CharacterType characterType;
      if (paramType.isIntervalType()) {
        characterType = ((IntervalType)paramType).getCharacterType();
      } else if (characterType.isDateTimeTypeWithZone()) {
        dateTimeType = ((DateTimeType)characterType).getDateTimeTypeWithoutZone();
      } 
    } 
    return (Type)dateTimeType;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(super.toString());
    if (this.columnCount == 0) {
      stringBuffer.append("[columnCount=0]");
      return stringBuffer.toString();
    } 
    stringBuffer.append('[');
    for (byte b = 0; b < this.columnCount; b++) {
      JDBCColumnMetaData jDBCColumnMetaData = getColumnMetaData(b + 1);
      stringBuffer.append('\n');
      stringBuffer.append("   column_");
      stringBuffer.append(b + 1);
      stringBuffer.append('=');
      stringBuffer.append(jDBCColumnMetaData);
      if (b + 1 < this.columnCount) {
        stringBuffer.append(',');
        stringBuffer.append(' ');
      } 
    } 
    stringBuffer.append('\n');
    stringBuffer.append(']');
    return stringBuffer.toString();
  }
  
  JDBCColumnMetaData getColumnMetaData(int paramInt) {
    JDBCColumnMetaData jDBCColumnMetaData = new JDBCColumnMetaData();
    try {
      jDBCColumnMetaData.catalogName = getCatalogName(paramInt);
      jDBCColumnMetaData.columnClassName = getColumnClassName(paramInt);
      jDBCColumnMetaData.columnDisplaySize = getColumnDisplaySize(paramInt);
      jDBCColumnMetaData.columnLabel = getColumnLabel(paramInt);
      jDBCColumnMetaData.columnName = getColumnName(paramInt);
      jDBCColumnMetaData.columnType = getColumnType(paramInt);
      jDBCColumnMetaData.isAutoIncrement = isAutoIncrement(paramInt);
      jDBCColumnMetaData.isCaseSensitive = isCaseSensitive(paramInt);
      jDBCColumnMetaData.isCurrency = isCurrency(paramInt);
      jDBCColumnMetaData.isDefinitelyWritable = isDefinitelyWritable(paramInt);
      jDBCColumnMetaData.isNullable = isNullable(paramInt);
      jDBCColumnMetaData.isReadOnly = isReadOnly(paramInt);
      jDBCColumnMetaData.isSearchable = isSearchable(paramInt);
      jDBCColumnMetaData.isSigned = isSigned(paramInt);
      jDBCColumnMetaData.isWritable = isWritable(paramInt);
      jDBCColumnMetaData.precision = getPrecision(paramInt);
      jDBCColumnMetaData.scale = getScale(paramInt);
      jDBCColumnMetaData.schemaName = getSchemaName(paramInt);
      jDBCColumnMetaData.tableName = getTableName(paramInt);
    } catch (SQLException sQLException) {}
    return jDBCColumnMetaData;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCResultSetMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */