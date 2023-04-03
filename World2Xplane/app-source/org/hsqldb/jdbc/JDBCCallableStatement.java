package org.hsqldb.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.error.Error;
import org.hsqldb.lib.IntValueHashMap;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class JDBCCallableStatement extends JDBCPreparedStatement implements CallableStatement {
  private IntValueHashMap parameterNameMap = new IntValueHashMap();
  
  private boolean wasNullValue;
  
  public synchronized void registerOutParameter(int paramInt1, int paramInt2) throws SQLException {
    checkGetParameterIndex(paramInt1);
    if (this.parameterModes[--paramInt1] == 1)
      throw JDBCUtil.invalidArgument(); 
  }
  
  public ResultSet getResultSet() throws SQLException {
    return super.getResultSet();
  }
  
  public synchronized void registerOutParameter(int paramInt1, int paramInt2, int paramInt3) throws SQLException {
    registerOutParameter(paramInt1, paramInt2);
  }
  
  public synchronized boolean wasNull() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    return this.wasNullValue;
  }
  
  public synchronized String getString(int paramInt) throws SQLException {
    return (String)getColumnInType(paramInt, (Type)Type.SQL_VARCHAR);
  }
  
  public synchronized boolean getBoolean(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_BOOLEAN);
    return (object == null) ? false : ((Boolean)object).booleanValue();
  }
  
  public synchronized byte getByte(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.TINYINT);
    return (object == null) ? 0 : ((Number)object).byteValue();
  }
  
  public synchronized short getShort(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_SMALLINT);
    return (object == null) ? 0 : ((Number)object).shortValue();
  }
  
  public synchronized int getInt(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_INTEGER);
    return (object == null) ? 0 : ((Number)object).intValue();
  }
  
  public synchronized long getLong(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_BIGINT);
    return (object == null) ? 0L : ((Number)object).longValue();
  }
  
  public synchronized float getFloat(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_DOUBLE);
    return (object == null) ? 0.0F : ((Number)object).floatValue();
  }
  
  public synchronized double getDouble(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_DOUBLE);
    return (object == null) ? 0.0D : ((Number)object).doubleValue();
  }
  
  public synchronized BigDecimal getBigDecimal(int paramInt1, int paramInt2) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    if (paramInt2 < 0)
      throw JDBCUtil.outOfRangeArgument(); 
    BigDecimal bigDecimal = getBigDecimal(paramInt1);
    if (bigDecimal != null)
      bigDecimal = bigDecimal.setScale(paramInt2, 1); 
    return bigDecimal;
  }
  
  public synchronized byte[] getBytes(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_VARBINARY);
    return (object == null) ? null : ((BinaryData)object).getBytes();
  }
  
  public synchronized Date getDate(int paramInt) throws SQLException {
    TimestampData timestampData = (TimestampData)getColumnInType(paramInt, (Type)Type.SQL_DATE);
    return (timestampData == null) ? null : (Date)Type.SQL_DATE.convertSQLToJava(this.session, timestampData);
  }
  
  public synchronized Time getTime(int paramInt) throws SQLException {
    TimeData timeData = (TimeData)getColumnInType(paramInt, (Type)Type.SQL_TIME);
    return (timeData == null) ? null : (Time)Type.SQL_TIME.convertSQLToJava(this.session, timeData);
  }
  
  public synchronized Timestamp getTimestamp(int paramInt) throws SQLException {
    TimestampData timestampData = (TimestampData)getColumnInType(paramInt, (Type)Type.SQL_TIMESTAMP);
    return (timestampData == null) ? null : (Timestamp)Type.SQL_TIMESTAMP.convertSQLToJava(this.session, timestampData);
  }
  
  public synchronized Object getObject(int paramInt) throws SQLException {
    boolean bool;
    Object object;
    checkGetParameterIndex(paramInt);
    Type type = this.parameterTypes[paramInt - 1];
    switch (type.typeCode) {
      case 50:
        return getArray(paramInt);
      case 91:
        return getDate(paramInt);
      case 92:
      case 94:
        return getTime(paramInt);
      case 93:
      case 95:
        return getTimestamp(paramInt);
      case 60:
      case 61:
        return getBytes(paramInt);
      case 14:
        bool = getBoolean(paramInt);
        return wasNull() ? null : (bool ? Boolean.TRUE : Boolean.FALSE);
      case 40:
        return getClob(paramInt);
      case 30:
        return getBlob(paramInt);
      case 1111:
      case 2000:
        object = getColumnInType(paramInt, type);
        if (object == null)
          return null; 
        try {
          return ((JavaObjectData)object).getObject();
        } catch (HsqlException hsqlException) {
          throw JDBCUtil.sqlException(hsqlException);
        } 
    } 
    return getColumnInType(paramInt, type);
  }
  
  public synchronized BigDecimal getBigDecimal(int paramInt) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    Type type = this.parameterMetaData.columnTypes[paramInt - 1];
    switch (type.typeCode) {
      case 2:
      case 3:
        return (BigDecimal)getColumnInType(paramInt, type);
      case -6:
      case 4:
      case 5:
      case 25:
        numberType = Type.SQL_DECIMAL;
    } 
    NumberType numberType = Type.SQL_DECIMAL_DEFAULT;
  }
  
  public Object getObject(int paramInt, Map<String, Class<?>> paramMap) throws SQLException {
    checkGetParameterIndex(paramInt);
    throw JDBCUtil.notSupported();
  }
  
  public Ref getRef(int paramInt) throws SQLException {
    checkGetParameterIndex(paramInt);
    throw JDBCUtil.notSupported();
  }
  
  public synchronized Blob getBlob(int paramInt) throws SQLException {
    checkGetParameterIndex(paramInt);
    Type type = this.parameterMetaData.columnTypes[paramInt - 1];
    Object object = getColumnInType(paramInt, type);
    if (object == null)
      return null; 
    if (object instanceof BlobDataID)
      return new JDBCBlobClient(this.session, (BlobDataID)object); 
    throw JDBCUtil.sqlException(5561);
  }
  
  public synchronized Clob getClob(int paramInt) throws SQLException {
    checkGetParameterIndex(paramInt);
    Type type = this.parameterMetaData.columnTypes[paramInt - 1];
    Object object = getColumnInType(paramInt, type);
    if (object == null)
      return null; 
    if (object instanceof ClobDataID)
      return new JDBCClobClient(this.session, (ClobDataID)object); 
    throw JDBCUtil.sqlException(5561);
  }
  
  public Array getArray(int paramInt) throws SQLException {
    checkGetParameterIndex(paramInt);
    Type type = this.parameterMetaData.columnTypes[paramInt - 1];
    if (!type.isArrayType())
      throw JDBCUtil.sqlException(5561); 
    Object[] arrayOfObject = (Object[])this.parameterValues[paramInt - 1];
    return (arrayOfObject == null) ? null : new JDBCArray(arrayOfObject, type.collectionBaseType(), type, this.connection);
  }
  
  public synchronized Date getDate(int paramInt, Calendar paramCalendar) throws SQLException {
    TimestampData timestampData = (TimestampData)getColumnInType(paramInt, (Type)Type.SQL_DATE);
    if (timestampData == null)
      return null; 
    long l = timestampData.getSeconds() * 1000L;
    if (paramCalendar != null)
      l = HsqlDateTime.convertMillisToCalendar(paramCalendar, l); 
    return new Date(l);
  }
  
  public synchronized Time getTime(int paramInt, Calendar paramCalendar) throws SQLException {
    TimeData timeData = (TimeData)getColumnInType(paramInt, (Type)Type.SQL_TIME);
    if (timeData == null)
      return null; 
    long l = (DateTimeType.normaliseTime(timeData.getSeconds()) * 1000);
    if (!this.parameterMetaData.columnTypes[--paramInt].isDateTimeTypeWithZone()) {
      Calendar calendar = (paramCalendar == null) ? this.session.getCalendar() : paramCalendar;
      l = HsqlDateTime.convertMillisToCalendar(calendar, l);
      l = HsqlDateTime.getNormalisedTime(l);
    } 
    return new Time(l);
  }
  
  public synchronized Timestamp getTimestamp(int paramInt, Calendar paramCalendar) throws SQLException {
    TimestampData timestampData = (TimestampData)getColumnInType(paramInt, (Type)Type.SQL_TIMESTAMP);
    if (timestampData == null)
      return null; 
    long l = timestampData.getSeconds() * 1000L;
    if (!this.parameterMetaData.columnTypes[--paramInt].isDateTimeTypeWithZone()) {
      Calendar calendar = (paramCalendar == null) ? this.session.getCalendar() : paramCalendar;
      if (paramCalendar != null)
        l = HsqlDateTime.convertMillisToCalendar(calendar, l); 
    } 
    Timestamp timestamp = new Timestamp(l);
    timestamp.setNanos(timestampData.getNanos());
    return timestamp;
  }
  
  public synchronized void registerOutParameter(int paramInt1, int paramInt2, String paramString) throws SQLException {
    registerOutParameter(paramInt1, paramInt2);
  }
  
  public synchronized void registerOutParameter(String paramString, int paramInt) throws SQLException {
    registerOutParameter(findParameterIndex(paramString), paramInt);
  }
  
  public synchronized void registerOutParameter(String paramString, int paramInt1, int paramInt2) throws SQLException {
    registerOutParameter(findParameterIndex(paramString), paramInt1);
  }
  
  public synchronized void registerOutParameter(String paramString1, int paramInt, String paramString2) throws SQLException {
    registerOutParameter(findParameterIndex(paramString1), paramInt);
  }
  
  public URL getURL(int paramInt) throws SQLException {
    checkGetParameterIndex(paramInt);
    throw JDBCUtil.notSupported();
  }
  
  public void setURL(String paramString, URL paramURL) throws SQLException {
    setURL(findParameterIndex(paramString), paramURL);
  }
  
  public synchronized void setNull(String paramString, int paramInt) throws SQLException {
    setNull(findParameterIndex(paramString), paramInt);
  }
  
  public synchronized void setBoolean(String paramString, boolean paramBoolean) throws SQLException {
    setBoolean(findParameterIndex(paramString), paramBoolean);
  }
  
  public synchronized void setByte(String paramString, byte paramByte) throws SQLException {
    setByte(findParameterIndex(paramString), paramByte);
  }
  
  public synchronized void setShort(String paramString, short paramShort) throws SQLException {
    setShort(findParameterIndex(paramString), paramShort);
  }
  
  public synchronized void setInt(String paramString, int paramInt) throws SQLException {
    setInt(findParameterIndex(paramString), paramInt);
  }
  
  public synchronized void setLong(String paramString, long paramLong) throws SQLException {
    setLong(findParameterIndex(paramString), paramLong);
  }
  
  public synchronized void setFloat(String paramString, float paramFloat) throws SQLException {
    setFloat(findParameterIndex(paramString), paramFloat);
  }
  
  public synchronized void setDouble(String paramString, double paramDouble) throws SQLException {
    setDouble(findParameterIndex(paramString), paramDouble);
  }
  
  public synchronized void setBigDecimal(String paramString, BigDecimal paramBigDecimal) throws SQLException {
    setBigDecimal(findParameterIndex(paramString), paramBigDecimal);
  }
  
  public synchronized void setString(String paramString1, String paramString2) throws SQLException {
    setString(findParameterIndex(paramString1), paramString2);
  }
  
  public synchronized void setBytes(String paramString, byte[] paramArrayOfbyte) throws SQLException {
    setBytes(findParameterIndex(paramString), paramArrayOfbyte);
  }
  
  public synchronized void setDate(String paramString, Date paramDate) throws SQLException {
    setDate(findParameterIndex(paramString), paramDate);
  }
  
  public synchronized void setTime(String paramString, Time paramTime) throws SQLException {
    setTime(findParameterIndex(paramString), paramTime);
  }
  
  public synchronized void setTimestamp(String paramString, Timestamp paramTimestamp) throws SQLException {
    setTimestamp(findParameterIndex(paramString), paramTimestamp);
  }
  
  public synchronized void setAsciiStream(String paramString, InputStream paramInputStream, int paramInt) throws SQLException {
    setAsciiStream(findParameterIndex(paramString), paramInputStream, paramInt);
  }
  
  public synchronized void setBinaryStream(String paramString, InputStream paramInputStream, int paramInt) throws SQLException {
    setBinaryStream(findParameterIndex(paramString), paramInputStream, paramInt);
  }
  
  public synchronized void setObject(String paramString, Object paramObject, int paramInt1, int paramInt2) throws SQLException {
    setObject(findParameterIndex(paramString), paramObject, paramInt1, paramInt2);
  }
  
  public synchronized void setObject(String paramString, Object paramObject, int paramInt) throws SQLException {
    setObject(findParameterIndex(paramString), paramObject, paramInt);
  }
  
  public synchronized void setObject(String paramString, Object paramObject) throws SQLException {
    setObject(findParameterIndex(paramString), paramObject);
  }
  
  public synchronized void setCharacterStream(String paramString, Reader paramReader, int paramInt) throws SQLException {
    setCharacterStream(findParameterIndex(paramString), paramReader, paramInt);
  }
  
  public synchronized void setDate(String paramString, Date paramDate, Calendar paramCalendar) throws SQLException {
    setDate(findParameterIndex(paramString), paramDate, paramCalendar);
  }
  
  public synchronized void setTime(String paramString, Time paramTime, Calendar paramCalendar) throws SQLException {
    setTime(findParameterIndex(paramString), paramTime, paramCalendar);
  }
  
  public synchronized void setTimestamp(String paramString, Timestamp paramTimestamp, Calendar paramCalendar) throws SQLException {
    setTimestamp(findParameterIndex(paramString), paramTimestamp, paramCalendar);
  }
  
  public synchronized void setNull(String paramString1, int paramInt, String paramString2) throws SQLException {
    setNull(findParameterIndex(paramString1), paramInt, paramString2);
  }
  
  public synchronized String getString(String paramString) throws SQLException {
    return getString(findParameterIndex(paramString));
  }
  
  public synchronized boolean getBoolean(String paramString) throws SQLException {
    return getBoolean(findParameterIndex(paramString));
  }
  
  public synchronized byte getByte(String paramString) throws SQLException {
    return getByte(findParameterIndex(paramString));
  }
  
  public synchronized short getShort(String paramString) throws SQLException {
    return getShort(findParameterIndex(paramString));
  }
  
  public synchronized int getInt(String paramString) throws SQLException {
    return getInt(findParameterIndex(paramString));
  }
  
  public synchronized long getLong(String paramString) throws SQLException {
    return getLong(findParameterIndex(paramString));
  }
  
  public synchronized float getFloat(String paramString) throws SQLException {
    return getFloat(findParameterIndex(paramString));
  }
  
  public synchronized double getDouble(String paramString) throws SQLException {
    return getDouble(findParameterIndex(paramString));
  }
  
  public synchronized byte[] getBytes(String paramString) throws SQLException {
    return getBytes(findParameterIndex(paramString));
  }
  
  public synchronized Date getDate(String paramString) throws SQLException {
    return getDate(findParameterIndex(paramString));
  }
  
  public synchronized Time getTime(String paramString) throws SQLException {
    return getTime(findParameterIndex(paramString));
  }
  
  public synchronized Timestamp getTimestamp(String paramString) throws SQLException {
    return getTimestamp(findParameterIndex(paramString));
  }
  
  public synchronized Object getObject(String paramString) throws SQLException {
    return getObject(findParameterIndex(paramString));
  }
  
  public synchronized BigDecimal getBigDecimal(String paramString) throws SQLException {
    return getBigDecimal(findParameterIndex(paramString));
  }
  
  public synchronized Object getObject(String paramString, Map<String, Class<?>> paramMap) throws SQLException {
    return getObject(findParameterIndex(paramString), paramMap);
  }
  
  public synchronized Ref getRef(String paramString) throws SQLException {
    return getRef(findParameterIndex(paramString));
  }
  
  public synchronized Blob getBlob(String paramString) throws SQLException {
    return getBlob(findParameterIndex(paramString));
  }
  
  public synchronized Clob getClob(String paramString) throws SQLException {
    return getClob(findParameterIndex(paramString));
  }
  
  public synchronized Array getArray(String paramString) throws SQLException {
    return getArray(findParameterIndex(paramString));
  }
  
  public synchronized Date getDate(String paramString, Calendar paramCalendar) throws SQLException {
    return getDate(findParameterIndex(paramString), paramCalendar);
  }
  
  public synchronized Time getTime(String paramString, Calendar paramCalendar) throws SQLException {
    return getTime(findParameterIndex(paramString), paramCalendar);
  }
  
  public synchronized Timestamp getTimestamp(String paramString, Calendar paramCalendar) throws SQLException {
    return getTimestamp(findParameterIndex(paramString), paramCalendar);
  }
  
  public URL getURL(String paramString) throws SQLException {
    return getURL(findParameterIndex(paramString));
  }
  
  public RowId getRowId(int paramInt) throws SQLException {
    checkGetParameterIndex(paramInt);
    throw JDBCUtil.notSupported();
  }
  
  public synchronized RowId getRowId(String paramString) throws SQLException {
    return getRowId(findParameterIndex(paramString));
  }
  
  public synchronized void setRowId(String paramString, RowId paramRowId) throws SQLException {
    setRowId(findParameterIndex(paramString), paramRowId);
  }
  
  public synchronized void setNString(String paramString1, String paramString2) throws SQLException {
    setNString(findParameterIndex(paramString1), paramString2);
  }
  
  public synchronized void setNCharacterStream(String paramString, Reader paramReader, long paramLong) throws SQLException {
    setNCharacterStream(findParameterIndex(paramString), paramReader, paramLong);
  }
  
  public synchronized void setNClob(String paramString, NClob paramNClob) throws SQLException {
    setNClob(findParameterIndex(paramString), paramNClob);
  }
  
  public synchronized void setClob(String paramString, Reader paramReader, long paramLong) throws SQLException {
    setClob(findParameterIndex(paramString), paramReader, paramLong);
  }
  
  public synchronized void setBlob(String paramString, InputStream paramInputStream, long paramLong) throws SQLException {
    setBlob(findParameterIndex(paramString), paramInputStream, paramLong);
  }
  
  public synchronized void setNClob(String paramString, Reader paramReader, long paramLong) throws SQLException {
    setNClob(findParameterIndex(paramString), paramReader, paramLong);
  }
  
  public NClob getNClob(int paramInt) throws SQLException {
    checkGetParameterIndex(paramInt);
    throw JDBCUtil.notSupported();
  }
  
  public synchronized NClob getNClob(String paramString) throws SQLException {
    return getNClob(findParameterIndex(paramString));
  }
  
  public synchronized void setSQLXML(String paramString, SQLXML paramSQLXML) throws SQLException {
    setSQLXML(findParameterIndex(paramString), paramSQLXML);
  }
  
  public SQLXML getSQLXML(int paramInt) throws SQLException {
    checkGetParameterIndex(paramInt);
    throw JDBCUtil.notSupported();
  }
  
  public synchronized SQLXML getSQLXML(String paramString) throws SQLException {
    return getSQLXML(findParameterIndex(paramString));
  }
  
  public String getNString(int paramInt) throws SQLException {
    checkGetParameterIndex(paramInt);
    throw JDBCUtil.notSupported();
  }
  
  public synchronized String getNString(String paramString) throws SQLException {
    return getNString(findParameterIndex(paramString));
  }
  
  public Reader getNCharacterStream(int paramInt) throws SQLException {
    checkGetParameterIndex(paramInt);
    throw JDBCUtil.notSupported();
  }
  
  public synchronized Reader getNCharacterStream(String paramString) throws SQLException {
    return getNCharacterStream(findParameterIndex(paramString));
  }
  
  public Reader getCharacterStream(int paramInt) throws SQLException {
    checkGetParameterIndex(paramInt);
    Type type = this.parameterMetaData.columnTypes[paramInt - 1];
    Object object = getColumnInType(paramInt, type);
    if (object == null)
      return null; 
    if (object instanceof ClobDataID)
      return ((ClobDataID)object).getCharacterStream(this.session); 
    if (object instanceof Clob)
      return ((Clob)object).getCharacterStream(); 
    if (object instanceof String)
      return new StringReader((String)object); 
    throw JDBCUtil.sqlException(5561);
  }
  
  public synchronized Reader getCharacterStream(String paramString) throws SQLException {
    return getCharacterStream(findParameterIndex(paramString));
  }
  
  public synchronized void setBlob(String paramString, Blob paramBlob) throws SQLException {
    setBlob(findParameterIndex(paramString), paramBlob);
  }
  
  public synchronized void setClob(String paramString, Clob paramClob) throws SQLException {
    setClob(findParameterIndex(paramString), paramClob);
  }
  
  public synchronized void setAsciiStream(String paramString, InputStream paramInputStream, long paramLong) throws SQLException {
    if (paramLong > 2147483647L) {
      String str = "Maximum ASCII input octet length exceeded: " + paramLong;
      throw JDBCUtil.sqlException(422, str);
    } 
    setAsciiStream(paramString, paramInputStream, (int)paramLong);
  }
  
  public synchronized void setBinaryStream(String paramString, InputStream paramInputStream, long paramLong) throws SQLException {
    if (paramLong > 2147483647L) {
      String str = "Maximum Binary input octet length exceeded: " + paramLong;
      throw JDBCUtil.sqlException(422, str);
    } 
    setBinaryStream(paramString, paramInputStream, (int)paramLong);
  }
  
  public synchronized void setCharacterStream(String paramString, Reader paramReader, long paramLong) throws SQLException {
    if (paramLong > 2147483647L) {
      String str = "Maximum character input length exceeded: " + paramLong;
      throw JDBCUtil.sqlException(422, str);
    } 
    setCharacterStream(paramString, paramReader, (int)paramLong);
  }
  
  public synchronized void setAsciiStream(String paramString, InputStream paramInputStream) throws SQLException {
    setAsciiStream(findParameterIndex(paramString), paramInputStream);
  }
  
  public synchronized void setBinaryStream(String paramString, InputStream paramInputStream) throws SQLException {
    setBinaryStream(findParameterIndex(paramString), paramInputStream);
  }
  
  public synchronized void setCharacterStream(String paramString, Reader paramReader) throws SQLException {
    setCharacterStream(findParameterIndex(paramString), paramReader);
  }
  
  public synchronized void setNCharacterStream(String paramString, Reader paramReader) throws SQLException {
    setNCharacterStream(findParameterIndex(paramString), paramReader);
  }
  
  public synchronized void setClob(String paramString, Reader paramReader) throws SQLException {
    setClob(findParameterIndex(paramString), paramReader);
  }
  
  public synchronized void setBlob(String paramString, InputStream paramInputStream) throws SQLException {
    setBlob(findParameterIndex(paramString), paramInputStream);
  }
  
  public synchronized void setNClob(String paramString, Reader paramReader) throws SQLException {
    setNClob(findParameterIndex(paramString), paramReader);
  }
  
  public <T> T getObject(int paramInt, Class<T> paramClass) throws SQLException {
    return (T)getObject(paramInt);
  }
  
  public <T> T getObject(String paramString, Class<T> paramClass) throws SQLException {
    return getObject(findParameterIndex(paramString), paramClass);
  }
  
  public JDBCCallableStatement(JDBCConnection paramJDBCConnection, String paramString, int paramInt1, int paramInt2, int paramInt3) throws HsqlException, SQLException {
    super(paramJDBCConnection, paramString, paramInt1, paramInt2, paramInt3, 2, (int[])null, (String[])null);
    if (this.parameterMetaData != null) {
      String[] arrayOfString = this.parameterMetaData.columnLabels;
      for (byte b = 0; b < arrayOfString.length; b++) {
        String str = arrayOfString[b];
        if (str != null && str.length() != 0)
          this.parameterNameMap.put(str, b); 
      } 
    } 
  }
  
  void fetchResult() throws SQLException {
    super.fetchResult();
    if (this.resultIn.getType() == 43) {
      Object[] arrayOfObject = this.resultIn.getParameterData();
      for (byte b = 0; b < this.parameterValues.length; b++)
        this.parameterValues[b] = arrayOfObject[b]; 
    } 
  }
  
  int findParameterIndex(String paramString) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    int i = this.parameterNameMap.get(paramString, -1);
    if (i >= 0)
      return i + 1; 
    throw JDBCUtil.sqlException(421, paramString);
  }
  
  public synchronized void close() throws SQLException {
    if (isClosed())
      return; 
    this.parameterNameMap = null;
    super.close();
  }
  
  private Object getColumnInType(int paramInt, Type paramType) throws SQLException {
    checkGetParameterIndex(paramInt);
    Type type = this.parameterTypes[--paramInt];
    Object object = this.parameterValues[paramInt];
    if (trackNull(object))
      return null; 
    if (type.typeCode != paramType.typeCode)
      try {
        object = paramType.convertToTypeJDBC(this.session, object, type);
      } catch (HsqlException hsqlException1) {
        String str1 = (object instanceof Number || object instanceof String || object instanceof java.util.Date) ? object.toString() : ("instance of " + object.getClass().getName());
        String str2 = "from SQL type " + type.getNameString() + " to " + paramType.getJDBCClassName() + ", value: " + str1;
        HsqlException hsqlException2 = Error.error(5561, str2);
        throw JDBCUtil.sqlException(hsqlException2, hsqlException1);
      }  
    return object;
  }
  
  private boolean trackNull(Object paramObject) {
    return this.wasNullValue = (paramObject == null);
  }
  
  public void closeOnCompletion() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public boolean isCloseOnCompletion() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public synchronized ResultSet executeQuery() throws SQLException {
    fetchResult();
    ResultSet resultSet = getResultSet();
    if (resultSet != null)
      return resultSet; 
    if (getMoreResults())
      return getResultSet(); 
    throw JDBCUtil.sqlException(1254);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCCallableStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */