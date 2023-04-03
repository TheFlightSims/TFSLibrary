package org.hsqldb.jdbc;

import java.lang.reflect.Field;

public final class JDBCColumnMetaData {
  public String catalogName;
  
  public String columnClassName;
  
  public int columnDisplaySize;
  
  public String columnLabel;
  
  public String columnName;
  
  public int columnType;
  
  public int precision;
  
  public int scale;
  
  public String schemaName;
  
  public String tableName;
  
  public boolean isAutoIncrement;
  
  public boolean isCaseSensitive;
  
  public boolean isCurrency;
  
  public boolean isDefinitelyWritable;
  
  public int isNullable;
  
  public boolean isReadOnly;
  
  public boolean isSearchable;
  
  public boolean isSigned;
  
  public boolean isWritable;
  
  public String toString() {
    try {
      return toStringImpl();
    } catch (Exception exception) {
      return super.toString() + "[" + exception + "]";
    } 
  }
  
  private String toStringImpl() throws Exception {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append('[');
    for (Field field : getClass().getFields()) {
      stringBuffer.append(field.getName());
      stringBuffer.append('=');
      stringBuffer.append(field.get(this));
      if (null + 1 < null) {
        stringBuffer.append(',');
        stringBuffer.append(' ');
      } 
    } 
    stringBuffer.append(']');
    return stringBuffer.toString();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCColumnMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */