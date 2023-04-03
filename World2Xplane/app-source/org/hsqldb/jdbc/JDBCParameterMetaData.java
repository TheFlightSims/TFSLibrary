package org.hsqldb.jdbc;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.sql.Wrapper;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.Type;

public class JDBCParameterMetaData implements ParameterMetaData, Wrapper {
  ResultMetaData rmd;
  
  String[] classNames;
  
  int parameterCount;
  
  private boolean translateTTIType;
  
  public int getParameterCount() throws SQLException {
    return this.parameterCount;
  }
  
  public int isNullable(int paramInt) throws SQLException {
    checkRange(paramInt);
    return 2;
  }
  
  public boolean isSigned(int paramInt) throws SQLException {
    checkRange(paramInt);
    Type type = translateType(this.rmd.columnTypes[--paramInt]);
    return type.isNumberType();
  }
  
  public int getPrecision(int paramInt) throws SQLException {
    checkRange(paramInt);
    Type type = translateType(this.rmd.columnTypes[--paramInt]);
    if (type.isDateTimeType())
      return type.displaySize(); 
    long l = type.precision;
    if (l > 2147483647L)
      l = 0L; 
    return (int)l;
  }
  
  public int getScale(int paramInt) throws SQLException {
    checkRange(paramInt);
    Type type = translateType(this.rmd.columnTypes[--paramInt]);
    return type.scale;
  }
  
  public int getParameterType(int paramInt) throws SQLException {
    checkRange(paramInt);
    Type type = translateType(this.rmd.columnTypes[--paramInt]);
    return type.getJDBCTypeCode();
  }
  
  public String getParameterTypeName(int paramInt) throws SQLException {
    checkRange(paramInt);
    Type type = translateType(this.rmd.columnTypes[--paramInt]);
    return type.getNameString();
  }
  
  public String getParameterClassName(int paramInt) throws SQLException {
    checkRange(paramInt);
    Type type = translateType(this.rmd.columnTypes[--paramInt]);
    return type.getJDBCClassName();
  }
  
  public int getParameterMode(int paramInt) throws SQLException {
    checkRange(paramInt);
    return this.rmd.paramModes[--paramInt];
  }
  
  public <T> T unwrap(Class<T> paramClass) throws SQLException {
    if (isWrapperFor(paramClass))
      return (T)this; 
    throw JDBCUtil.invalidArgument("iface: " + paramClass);
  }
  
  public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
    return (paramClass != null && paramClass.isAssignableFrom(getClass()));
  }
  
  JDBCParameterMetaData(JDBCConnection paramJDBCConnection, ResultMetaData paramResultMetaData) throws SQLException {
    this.rmd = paramResultMetaData;
    this.parameterCount = this.rmd.getColumnCount();
    if (paramJDBCConnection.clientProperties != null)
      this.translateTTIType = paramJDBCConnection.clientProperties.isPropertyTrue("jdbc.translate_tti_types"); 
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
  
  void checkRange(int paramInt) throws SQLException {
    if (paramInt < 1 || paramInt > this.parameterCount) {
      String str = paramInt + " is out of range";
      throw JDBCUtil.outOfRangeArgument(str);
    } 
  }
  
  public String toString() {
    try {
      return toStringImpl();
    } catch (Throwable throwable) {
      return super.toString() + "[toStringImpl_exception=" + throwable + "]";
    } 
  }
  
  private String toStringImpl() throws Exception {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(super.toString());
    int i = getParameterCount();
    if (i == 0) {
      stringBuffer.append("[parameterCount=0]");
      return stringBuffer.toString();
    } 
    Method[] arrayOfMethod = getClass().getDeclaredMethods();
    stringBuffer.append('[');
    int j = arrayOfMethod.length;
    for (byte b = 0; b < i; b++) {
      stringBuffer.append('\n');
      stringBuffer.append("    parameter_");
      stringBuffer.append(b + 1);
      stringBuffer.append('=');
      stringBuffer.append('[');
      for (byte b1 = 0; b1 < j; b1++) {
        Method method = arrayOfMethod[b1];
        if (Modifier.isPublic(method.getModifiers()) && (method.getParameterTypes()).length == 1) {
          stringBuffer.append(method.getName());
          stringBuffer.append('=');
          stringBuffer.append(method.invoke(this, new Object[] { new Integer(b + 1) }));
          if (b1 + 1 < j) {
            stringBuffer.append(',');
            stringBuffer.append(' ');
          } 
        } 
      } 
      stringBuffer.append(']');
      if (b + 1 < i) {
        stringBuffer.append(',');
        stringBuffer.append(' ');
      } 
    } 
    stringBuffer.append('\n');
    stringBuffer.append(']');
    return stringBuffer.toString();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCParameterMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */