package org.hsqldb.jdbc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import org.hsqldb.ColumnBase;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.IntValueHashMap;
import org.hsqldb.lib.StringInputStream;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.result.ResultProperties;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class JDBCResultSet implements ResultSet {
  private RowSetNavigator navigator;
  
  protected ResultMetaData resultMetaData;
  
  private boolean translateTTIType;
  
  private int columnCount;
  
  private boolean wasNullValue;
  
  private ResultSetMetaData resultSetMetaData;
  
  private IntValueHashMap columnMap;
  
  private SQLWarning rootWarning;
  
  JDBCStatementBase statement;
  
  SessionInterface session;
  
  JDBCConnection connection;
  
  boolean isScrollable;
  
  boolean isReadOnly;
  
  boolean isUpdatable;
  
  boolean isInsertable;
  
  int rsProperties;
  
  int fetchSize;
  
  boolean autoClose;
  
  public Result result;
  
  public static final int FETCH_FORWARD = 1000;
  
  public static final int FETCH_REVERSE = 1001;
  
  public static final int FETCH_UNKNOWN = 1002;
  
  public static final int TYPE_FORWARD_ONLY = 1003;
  
  public static final int TYPE_SCROLL_INSENSITIVE = 1004;
  
  public static final int TYPE_SCROLL_SENSITIVE = 1005;
  
  public static final int CONCUR_READ_ONLY = 1007;
  
  public static final int CONCUR_UPDATABLE = 1008;
  
  public static final int HOLD_CURSORS_OVER_COMMIT = 1;
  
  public static final int CLOSE_CURSORS_AT_COMMIT = 2;
  
  JDBCPreparedStatement preparedStatement;
  
  boolean isRowUpdated;
  
  boolean isOnInsertRow;
  
  int currentUpdateRowNumber;
  
  public boolean next() throws SQLException {
    checkClosed();
    this.rootWarning = null;
    return this.navigator.next();
  }
  
  public void close() throws SQLException {
    if (this.navigator == null)
      return; 
    if (ResultProperties.isHeld(this.rsProperties)) {
      this.session.closeNavigator(this.navigator.getId());
    } else {
      this.navigator.release();
    } 
    this.navigator = null;
    if (this.autoClose && this.statement != null)
      this.statement.close(); 
  }
  
  public boolean wasNull() throws SQLException {
    checkClosed();
    return this.wasNullValue;
  }
  
  public String getString(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = this.resultMetaData.columnTypes[paramInt - 1];
    if (type.typeCode == 40) {
      ClobDataID clobDataID = (ClobDataID)getColumnInType(paramInt, type);
      if (clobDataID == null)
        return null; 
      long l = clobDataID.length(this.session);
      if (l > 2147483647L)
        JDBCUtil.throwError(Error.error(5561)); 
      return clobDataID.getSubString(this.session, 0L, (int)l);
    } 
    return (String)getColumnInType(paramInt, (Type)Type.SQL_VARCHAR);
  }
  
  public boolean getBoolean(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_BOOLEAN);
    return (object == null) ? false : ((Boolean)object).booleanValue();
  }
  
  public byte getByte(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.TINYINT);
    return (object == null) ? 0 : ((Number)object).byteValue();
  }
  
  public short getShort(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_SMALLINT);
    return (object == null) ? 0 : ((Number)object).shortValue();
  }
  
  public int getInt(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_INTEGER);
    return (object == null) ? 0 : ((Number)object).intValue();
  }
  
  public long getLong(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_BIGINT);
    return (object == null) ? 0L : ((Number)object).longValue();
  }
  
  public float getFloat(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_DOUBLE);
    return (object == null) ? 0.0F : ((Number)object).floatValue();
  }
  
  public double getDouble(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_DOUBLE);
    return (object == null) ? 0.0D : ((Number)object).doubleValue();
  }
  
  public BigDecimal getBigDecimal(int paramInt1, int paramInt2) throws SQLException {
    if (paramInt2 < 0)
      throw JDBCUtil.outOfRangeArgument(); 
    BigDecimal bigDecimal = getBigDecimal(paramInt1);
    if (bigDecimal != null)
      bigDecimal = bigDecimal.setScale(paramInt2, 1); 
    return bigDecimal;
  }
  
  public byte[] getBytes(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = this.resultMetaData.columnTypes[paramInt - 1];
    if (type.typeCode == 30) {
      BlobDataID blobDataID = (BlobDataID)getColumnInType(paramInt, type);
      if (blobDataID == null)
        return null; 
      long l = blobDataID.length(this.session);
      if (l > 2147483647L)
        JDBCUtil.throwError(Error.error(5561)); 
      return blobDataID.getBytes(this.session, 0L, (int)l);
    } 
    Object object = getColumnInType(paramInt, (Type)Type.SQL_VARBINARY);
    return (object == null) ? null : ((BinaryData)object).getBytes();
  }
  
  public Date getDate(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_DATE);
    return (object == null) ? null : (Date)Type.SQL_DATE.convertSQLToJava(this.session, object);
  }
  
  public Time getTime(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_TIME);
    return (object == null) ? null : (Time)Type.SQL_TIME.convertSQLToJava(this.session, object);
  }
  
  public Timestamp getTimestamp(int paramInt) throws SQLException {
    Object object = getColumnInType(paramInt, (Type)Type.SQL_TIMESTAMP);
    return (object == null) ? null : (Timestamp)Type.SQL_TIMESTAMP.convertSQLToJava(this.session, object);
  }
  
  public InputStream getAsciiStream(int paramInt) throws SQLException {
    String str = getString(paramInt);
    if (str == null)
      return null; 
    try {
      return new ByteArrayInputStream(str.getBytes("US-ASCII"));
    } catch (IOException iOException) {
      return null;
    } 
  }
  
  public InputStream getUnicodeStream(int paramInt) throws SQLException {
    String str = getString(paramInt);
    return (InputStream)((str == null) ? null : new StringInputStream(str));
  }
  
  public InputStream getBinaryStream(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = this.resultMetaData.columnTypes[paramInt - 1];
    Object object = getColumnInType(paramInt, type);
    if (object == null)
      return null; 
    if (object instanceof BlobDataID)
      return ((BlobDataID)object).getBinaryStream(this.session); 
    if (object instanceof Blob)
      return ((Blob)object).getBinaryStream(); 
    if (object instanceof BinaryData) {
      byte[] arrayOfByte = getBytes(paramInt);
      return new ByteArrayInputStream(arrayOfByte);
    } 
    throw JDBCUtil.sqlException(5561);
  }
  
  public String getString(String paramString) throws SQLException {
    return getString(findColumn(paramString));
  }
  
  public boolean getBoolean(String paramString) throws SQLException {
    return getBoolean(findColumn(paramString));
  }
  
  public byte getByte(String paramString) throws SQLException {
    return getByte(findColumn(paramString));
  }
  
  public short getShort(String paramString) throws SQLException {
    return getShort(findColumn(paramString));
  }
  
  public int getInt(String paramString) throws SQLException {
    return getInt(findColumn(paramString));
  }
  
  public long getLong(String paramString) throws SQLException {
    return getLong(findColumn(paramString));
  }
  
  public float getFloat(String paramString) throws SQLException {
    return getFloat(findColumn(paramString));
  }
  
  public double getDouble(String paramString) throws SQLException {
    return getDouble(findColumn(paramString));
  }
  
  public BigDecimal getBigDecimal(String paramString, int paramInt) throws SQLException {
    return getBigDecimal(findColumn(paramString), paramInt);
  }
  
  public byte[] getBytes(String paramString) throws SQLException {
    return getBytes(findColumn(paramString));
  }
  
  public Date getDate(String paramString) throws SQLException {
    return getDate(findColumn(paramString));
  }
  
  public Time getTime(String paramString) throws SQLException {
    return getTime(findColumn(paramString));
  }
  
  public Timestamp getTimestamp(String paramString) throws SQLException {
    return getTimestamp(findColumn(paramString));
  }
  
  public InputStream getAsciiStream(String paramString) throws SQLException {
    return getAsciiStream(findColumn(paramString));
  }
  
  public InputStream getUnicodeStream(String paramString) throws SQLException {
    return getUnicodeStream(findColumn(paramString));
  }
  
  public InputStream getBinaryStream(String paramString) throws SQLException {
    return getBinaryStream(findColumn(paramString));
  }
  
  public SQLWarning getWarnings() throws SQLException {
    checkClosed();
    return this.rootWarning;
  }
  
  public void clearWarnings() throws SQLException {
    checkClosed();
    this.rootWarning = null;
  }
  
  public String getCursorName() throws SQLException {
    checkClosed();
    return (this.result == null) ? "" : this.result.getMainString();
  }
  
  public ResultSetMetaData getMetaData() throws SQLException {
    checkClosed();
    if (this.resultSetMetaData == null)
      this.resultSetMetaData = new JDBCResultSetMetaData(this.resultMetaData, this.isUpdatable, this.isInsertable, this.connection); 
    return this.resultSetMetaData;
  }
  
  public Object getObject(int paramInt) throws SQLException {
    boolean bool;
    Object object;
    checkColumn(paramInt);
    Type type = this.resultMetaData.columnTypes[paramInt - 1];
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
  
  public Object getObject(String paramString) throws SQLException {
    return getObject(findColumn(paramString));
  }
  
  public int findColumn(String paramString) throws SQLException {
    checkClosed();
    if (paramString == null)
      throw JDBCUtil.nullArgument(); 
    if (this.columnMap != null) {
      int i = this.columnMap.get(paramString, -1);
      if (i != -1)
        return i; 
    } 
    String[] arrayOfString = this.resultMetaData.columnLabels;
    byte b = -1;
    for (byte b1 = 0; b1 < this.columnCount; b1++) {
      if (paramString.equalsIgnoreCase(arrayOfString[b1])) {
        b = b1;
        break;
      } 
    } 
    ColumnBase[] arrayOfColumnBase = this.resultMetaData.columns;
    if (b < 0)
      for (byte b2 = 0; b2 < this.columnCount; b2++) {
        if (paramString.equalsIgnoreCase(arrayOfColumnBase[b2].getNameString())) {
          b = b2;
          break;
        } 
      }  
    if (b < 0) {
      int i = paramString.indexOf('.');
      if (i < 0)
        throw JDBCUtil.sqlException(421, paramString); 
      for (byte b2 = 0; b2 < this.columnCount; b2++) {
        String str = arrayOfColumnBase[b2].getTableNameString();
        if (str != null && str.length() != 0) {
          String str1 = arrayOfColumnBase[b2].getNameString();
          if (paramString.equalsIgnoreCase(str + '.' + str1)) {
            b = b2;
            break;
          } 
          String str2 = arrayOfColumnBase[b2].getSchemaNameString();
          if (str2 != null && str2.length() != 0) {
            String str3 = str2 + '.' + str + '.' + str1;
            if (paramString.equalsIgnoreCase(str3)) {
              b = b2;
              break;
            } 
          } 
        } 
      } 
    } 
    if (b < 0)
      throw JDBCUtil.sqlException(421, paramString); 
    b++;
    if (this.columnMap == null)
      this.columnMap = new IntValueHashMap(); 
    this.columnMap.put(paramString, b);
    return b;
  }
  
  public Reader getCharacterStream(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = this.resultMetaData.columnTypes[paramInt - 1];
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
  
  public Reader getCharacterStream(String paramString) throws SQLException {
    return getCharacterStream(findColumn(paramString));
  }
  
  public BigDecimal getBigDecimal(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = this.resultMetaData.columnTypes[paramInt - 1];
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
  
  public BigDecimal getBigDecimal(String paramString) throws SQLException {
    return getBigDecimal(findColumn(paramString));
  }
  
  public boolean isBeforeFirst() throws SQLException {
    checkClosed();
    return this.isOnInsertRow ? false : this.navigator.isBeforeFirst();
  }
  
  public boolean isAfterLast() throws SQLException {
    checkClosed();
    return this.isOnInsertRow ? false : this.navigator.isAfterLast();
  }
  
  public boolean isFirst() throws SQLException {
    checkClosed();
    return this.isOnInsertRow ? false : this.navigator.isFirst();
  }
  
  public boolean isLast() throws SQLException {
    checkClosed();
    return this.isOnInsertRow ? false : this.navigator.isLast();
  }
  
  public void beforeFirst() throws SQLException {
    checkClosed();
    checkNotForwardOnly();
    if (this.isOnInsertRow || this.isRowUpdated)
      throw JDBCUtil.sqlExceptionSQL(3604); 
    this.navigator.beforeFirst();
  }
  
  public void afterLast() throws SQLException {
    checkClosed();
    checkNotForwardOnly();
    if (this.isOnInsertRow || this.isRowUpdated)
      throw JDBCUtil.sqlExceptionSQL(3604); 
    this.navigator.afterLast();
  }
  
  public boolean first() throws SQLException {
    checkClosed();
    checkNotForwardOnly();
    if (this.isOnInsertRow || this.isRowUpdated)
      throw JDBCUtil.sqlExceptionSQL(3604); 
    return this.navigator.first();
  }
  
  public boolean last() throws SQLException {
    checkClosed();
    checkNotForwardOnly();
    if (this.isOnInsertRow || this.isRowUpdated)
      throw JDBCUtil.sqlExceptionSQL(3604); 
    return this.navigator.last();
  }
  
  public int getRow() throws SQLException {
    checkClosed();
    return this.navigator.isAfterLast() ? 0 : (this.navigator.getRowNumber() + 1);
  }
  
  public boolean absolute(int paramInt) throws SQLException {
    checkClosed();
    checkNotForwardOnly();
    if (this.isOnInsertRow || this.isRowUpdated)
      throw JDBCUtil.sqlExceptionSQL(3604); 
    if (paramInt > 0) {
      paramInt--;
    } else if (paramInt == 0) {
      return this.navigator.beforeFirst();
    } 
    return this.navigator.absolute(paramInt);
  }
  
  public boolean relative(int paramInt) throws SQLException {
    checkClosed();
    checkNotForwardOnly();
    if (this.isOnInsertRow || this.isRowUpdated)
      throw JDBCUtil.sqlExceptionSQL(3604); 
    return this.navigator.relative(paramInt);
  }
  
  public boolean previous() throws SQLException {
    checkClosed();
    checkNotForwardOnly();
    if (this.isOnInsertRow || this.isRowUpdated)
      throw JDBCUtil.sqlExceptionSQL(3604); 
    this.rootWarning = null;
    return this.navigator.previous();
  }
  
  public void setFetchDirection(int paramInt) throws SQLException {
    checkClosed();
    switch (paramInt) {
      case 1000:
        return;
      case 1001:
        checkNotForwardOnly();
      case 1002:
        checkNotForwardOnly();
    } 
    throw JDBCUtil.notSupported();
  }
  
  public int getFetchDirection() throws SQLException {
    checkClosed();
    return 1000;
  }
  
  public void setFetchSize(int paramInt) throws SQLException {
    if (paramInt < 0)
      throw JDBCUtil.outOfRangeArgument(); 
  }
  
  public int getFetchSize() throws SQLException {
    checkClosed();
    return this.fetchSize;
  }
  
  public int getType() throws SQLException {
    checkClosed();
    return ResultProperties.getJDBCScrollability(this.rsProperties);
  }
  
  public int getConcurrency() throws SQLException {
    checkClosed();
    return ResultProperties.getJDBCConcurrency(this.rsProperties);
  }
  
  public boolean rowUpdated() throws SQLException {
    checkClosed();
    return this.isRowUpdated;
  }
  
  public boolean rowInserted() throws SQLException {
    checkClosed();
    return false;
  }
  
  public boolean rowDeleted() throws SQLException {
    checkClosed();
    return false;
  }
  
  public void updateNull(int paramInt) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, (Object)null);
  }
  
  public void updateBoolean(int paramInt, boolean paramBoolean) throws SQLException {
    Boolean bool = paramBoolean ? Boolean.TRUE : Boolean.FALSE;
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, bool);
  }
  
  public void updateByte(int paramInt, byte paramByte) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setIntParameter(paramInt, paramByte);
  }
  
  public void updateShort(int paramInt, short paramShort) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setIntParameter(paramInt, paramShort);
  }
  
  public void updateInt(int paramInt1, int paramInt2) throws SQLException {
    startUpdate(paramInt1);
    this.preparedStatement.setIntParameter(paramInt1, paramInt2);
  }
  
  public void updateLong(int paramInt, long paramLong) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setLongParameter(paramInt, paramLong);
  }
  
  public void updateFloat(int paramInt, float paramFloat) throws SQLException {
    Double double_ = new Double(paramFloat);
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, double_);
  }
  
  public void updateDouble(int paramInt, double paramDouble) throws SQLException {
    Double double_ = new Double(paramDouble);
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, double_);
  }
  
  public void updateBigDecimal(int paramInt, BigDecimal paramBigDecimal) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramBigDecimal);
  }
  
  public void updateString(int paramInt, String paramString) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramString);
  }
  
  public void updateBytes(int paramInt, byte[] paramArrayOfbyte) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramArrayOfbyte);
  }
  
  public void updateDate(int paramInt, Date paramDate) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramDate);
  }
  
  public void updateTime(int paramInt, Time paramTime) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramTime);
  }
  
  public void updateTimestamp(int paramInt, Timestamp paramTimestamp) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramTimestamp);
  }
  
  public void updateAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException {
    startUpdate(paramInt1);
    this.preparedStatement.setAsciiStream(paramInt1, paramInputStream, paramInt2);
  }
  
  public void updateBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException {
    startUpdate(paramInt1);
    this.preparedStatement.setBinaryStream(paramInt1, paramInputStream, paramInt2);
  }
  
  public void updateCharacterStream(int paramInt1, Reader paramReader, int paramInt2) throws SQLException {
    startUpdate(paramInt1);
    this.preparedStatement.setCharacterStream(paramInt1, paramReader, paramInt2);
  }
  
  public void updateObject(int paramInt1, Object paramObject, int paramInt2) throws SQLException {
    startUpdate(paramInt1);
    this.preparedStatement.setObject(paramInt1, paramObject, 0, paramInt2);
  }
  
  public void updateObject(int paramInt, Object paramObject) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramObject);
  }
  
  public void updateNull(String paramString) throws SQLException {
    updateNull(findColumn(paramString));
  }
  
  public void updateBoolean(String paramString, boolean paramBoolean) throws SQLException {
    updateBoolean(findColumn(paramString), paramBoolean);
  }
  
  public void updateByte(String paramString, byte paramByte) throws SQLException {
    updateByte(findColumn(paramString), paramByte);
  }
  
  public void updateShort(String paramString, short paramShort) throws SQLException {
    updateShort(findColumn(paramString), paramShort);
  }
  
  public void updateInt(String paramString, int paramInt) throws SQLException {
    updateInt(findColumn(paramString), paramInt);
  }
  
  public void updateLong(String paramString, long paramLong) throws SQLException {
    updateLong(findColumn(paramString), paramLong);
  }
  
  public void updateFloat(String paramString, float paramFloat) throws SQLException {
    updateFloat(findColumn(paramString), paramFloat);
  }
  
  public void updateDouble(String paramString, double paramDouble) throws SQLException {
    updateDouble(findColumn(paramString), paramDouble);
  }
  
  public void updateBigDecimal(String paramString, BigDecimal paramBigDecimal) throws SQLException {
    updateBigDecimal(findColumn(paramString), paramBigDecimal);
  }
  
  public void updateString(String paramString1, String paramString2) throws SQLException {
    updateString(findColumn(paramString1), paramString2);
  }
  
  public void updateBytes(String paramString, byte[] paramArrayOfbyte) throws SQLException {
    updateBytes(findColumn(paramString), paramArrayOfbyte);
  }
  
  public void updateDate(String paramString, Date paramDate) throws SQLException {
    updateDate(findColumn(paramString), paramDate);
  }
  
  public void updateTime(String paramString, Time paramTime) throws SQLException {
    updateTime(findColumn(paramString), paramTime);
  }
  
  public void updateTimestamp(String paramString, Timestamp paramTimestamp) throws SQLException {
    updateTimestamp(findColumn(paramString), paramTimestamp);
  }
  
  public void updateAsciiStream(String paramString, InputStream paramInputStream, int paramInt) throws SQLException {
    updateAsciiStream(findColumn(paramString), paramInputStream, paramInt);
  }
  
  public void updateBinaryStream(String paramString, InputStream paramInputStream, int paramInt) throws SQLException {
    updateBinaryStream(findColumn(paramString), paramInputStream, paramInt);
  }
  
  public void updateCharacterStream(String paramString, Reader paramReader, int paramInt) throws SQLException {
    updateCharacterStream(findColumn(paramString), paramReader, paramInt);
  }
  
  public void updateObject(String paramString, Object paramObject, int paramInt) throws SQLException {
    updateObject(findColumn(paramString), paramObject, paramInt);
  }
  
  public void updateObject(String paramString, Object paramObject) throws SQLException {
    updateObject(findColumn(paramString), paramObject);
  }
  
  public void insertRow() throws SQLException {
    performInsert();
  }
  
  public void updateRow() throws SQLException {
    performUpdate();
  }
  
  public void deleteRow() throws SQLException {
    performDelete();
  }
  
  public void refreshRow() throws SQLException {
    clearUpdates();
  }
  
  public void cancelRowUpdates() throws SQLException {
    clearUpdates();
  }
  
  public void moveToInsertRow() throws SQLException {
    startInsert();
  }
  
  public void moveToCurrentRow() throws SQLException {
    endInsert();
  }
  
  public Statement getStatement() throws SQLException {
    checkClosed();
    return (Statement)this.statement;
  }
  
  public Object getObject(int paramInt, Map paramMap) throws SQLException {
    return getObject(paramInt);
  }
  
  public Ref getRef(int paramInt) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public Blob getBlob(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = this.resultMetaData.columnTypes[paramInt - 1];
    Object object = getColumnInType(paramInt, type);
    if (object == null)
      return null; 
    if (object instanceof BlobDataID) {
      JDBCBlobClient jDBCBlobClient = new JDBCBlobClient(this.session, (BlobDataID)object);
      if (this.isUpdatable && this.resultMetaData.colIndexes[paramInt - 1] > 0 && this.resultMetaData.columns[paramInt - 1].isWriteable())
        jDBCBlobClient.setWritable(this, paramInt - 1); 
      return jDBCBlobClient;
    } 
    if (object instanceof Blob)
      return (Blob)object; 
    if (object instanceof BinaryData) {
      byte[] arrayOfByte = getBytes(paramInt);
      return new JDBCBlob(arrayOfByte);
    } 
    throw JDBCUtil.sqlException(5561);
  }
  
  public Clob getClob(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = this.resultMetaData.columnTypes[paramInt - 1];
    Object object = getColumnInType(paramInt, type);
    if (object == null)
      return null; 
    if (object instanceof ClobDataID) {
      JDBCClobClient jDBCClobClient = new JDBCClobClient(this.session, (ClobDataID)object);
      if (this.isUpdatable && this.resultMetaData.colIndexes[paramInt - 1] > 0 && this.resultMetaData.columns[paramInt - 1].isWriteable())
        jDBCClobClient.setWritable(this, paramInt - 1); 
      return jDBCClobClient;
    } 
    if (object instanceof Clob)
      return (Clob)object; 
    if (object instanceof String)
      return new JDBCClob((String)object); 
    throw JDBCUtil.sqlException(5561);
  }
  
  public Array getArray(int paramInt) throws SQLException {
    checkColumn(paramInt);
    Type type = this.resultMetaData.columnTypes[paramInt - 1];
    Object[] arrayOfObject = (Object[])getCurrent()[paramInt - 1];
    if (!type.isArrayType())
      throw JDBCUtil.sqlException(5561); 
    return trackNull(arrayOfObject) ? null : new JDBCArray(arrayOfObject, type.collectionBaseType(), type, this.connection);
  }
  
  public Object getObject(String paramString, Map paramMap) throws SQLException {
    return getObject(findColumn(paramString), paramMap);
  }
  
  public Ref getRef(String paramString) throws SQLException {
    return getRef(findColumn(paramString));
  }
  
  public Blob getBlob(String paramString) throws SQLException {
    return getBlob(findColumn(paramString));
  }
  
  public Clob getClob(String paramString) throws SQLException {
    return getClob(findColumn(paramString));
  }
  
  public Array getArray(String paramString) throws SQLException {
    return getArray(findColumn(paramString));
  }
  
  public Date getDate(int paramInt, Calendar paramCalendar) throws SQLException {
    TimestampData timestampData = (TimestampData)getColumnInType(paramInt, (Type)Type.SQL_DATE);
    if (timestampData == null)
      return null; 
    long l = timestampData.getSeconds() * 1000L;
    if (paramCalendar != null)
      l = HsqlDateTime.convertMillisToCalendar(paramCalendar, l); 
    return new Date(l);
  }
  
  public Date getDate(String paramString, Calendar paramCalendar) throws SQLException {
    return getDate(findColumn(paramString), paramCalendar);
  }
  
  public Time getTime(int paramInt, Calendar paramCalendar) throws SQLException {
    TimeData timeData = (TimeData)getColumnInType(paramInt, (Type)Type.SQL_TIME);
    if (timeData == null)
      return null; 
    long l = (DateTimeType.normaliseTime(timeData.getSeconds()) * 1000);
    if (!this.resultMetaData.columnTypes[--paramInt].isDateTimeTypeWithZone()) {
      Calendar calendar = (paramCalendar == null) ? this.session.getCalendar() : paramCalendar;
      l = HsqlDateTime.convertMillisToCalendar(calendar, l);
      l = HsqlDateTime.getNormalisedTime(l);
    } 
    return new Time(l);
  }
  
  public Time getTime(String paramString, Calendar paramCalendar) throws SQLException {
    return getTime(findColumn(paramString), paramCalendar);
  }
  
  public Timestamp getTimestamp(int paramInt, Calendar paramCalendar) throws SQLException {
    TimestampData timestampData = (TimestampData)getColumnInType(paramInt, (Type)Type.SQL_TIMESTAMP);
    if (timestampData == null)
      return null; 
    long l = timestampData.getSeconds() * 1000L;
    if (!this.resultMetaData.columnTypes[--paramInt].isDateTimeTypeWithZone()) {
      Calendar calendar = (paramCalendar == null) ? this.session.getCalendar() : paramCalendar;
      if (paramCalendar != null)
        l = HsqlDateTime.convertMillisToCalendar(calendar, l); 
    } 
    Timestamp timestamp = new Timestamp(l);
    timestamp.setNanos(timestampData.getNanos());
    return timestamp;
  }
  
  public Timestamp getTimestamp(String paramString, Calendar paramCalendar) throws SQLException {
    return getTimestamp(findColumn(paramString), paramCalendar);
  }
  
  public URL getURL(int paramInt) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public URL getURL(String paramString) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public void updateRef(int paramInt, Ref paramRef) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public void updateRef(String paramString, Ref paramRef) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public void updateBlob(int paramInt, Blob paramBlob) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setBlobParameter(paramInt, paramBlob);
  }
  
  public void updateBlob(String paramString, Blob paramBlob) throws SQLException {
    int i = findColumn(paramString);
    updateBlob(i, paramBlob);
  }
  
  public void updateClob(int paramInt, Clob paramClob) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setClobParameter(paramInt, paramClob);
  }
  
  public void updateClob(String paramString, Clob paramClob) throws SQLException {
    int i = findColumn(paramString);
    updateClob(i, paramClob);
  }
  
  public void updateArray(int paramInt, Array paramArray) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramArray);
  }
  
  public void updateArray(String paramString, Array paramArray) throws SQLException {
    int i = findColumn(paramString);
    updateArray(i, paramArray);
  }
  
  public RowId getRowId(int paramInt) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public RowId getRowId(String paramString) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public void updateRowId(int paramInt, RowId paramRowId) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public void updateRowId(String paramString, RowId paramRowId) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public int getHoldability() throws SQLException {
    checkClosed();
    return ResultProperties.getJDBCHoldability(this.rsProperties);
  }
  
  public boolean isClosed() throws SQLException {
    return (this.navigator == null);
  }
  
  public void updateNString(int paramInt, String paramString) throws SQLException {
    updateString(paramInt, paramString);
  }
  
  public void updateNString(String paramString1, String paramString2) throws SQLException {
    updateString(paramString1, paramString2);
  }
  
  public void updateNClob(int paramInt, NClob paramNClob) throws SQLException {
    updateClob(paramInt, paramNClob);
  }
  
  public void updateNClob(String paramString, NClob paramNClob) throws SQLException {
    updateClob(paramString, paramNClob);
  }
  
  public NClob getNClob(int paramInt) throws SQLException {
    String str = getString(paramInt);
    return (str == null) ? null : new JDBCNClob(str);
  }
  
  public NClob getNClob(String paramString) throws SQLException {
    return getNClob(findColumn(paramString));
  }
  
  public SQLXML getSQLXML(int paramInt) throws SQLException {
    SQLXML sQLXML;
    Object object;
    checkColumn(paramInt);
    int i = (this.resultMetaData.columnTypes[paramInt - 1]).typeCode;
    switch (i) {
      case 137:
        object = getObject(paramInt);
        if (object == null) {
          sQLXML = null;
        } else if (object instanceof SQLXML) {
          sQLXML = (SQLXML)object;
        } else {
          throw JDBCUtil.notSupported();
        } 
        return sQLXML;
      case 40:
        object = getClob(paramInt);
        if (object == null) {
          sQLXML = null;
        } else {
          sQLXML = new JDBCSQLXML(object.getCharacterStream());
        } 
        return sQLXML;
      case 1:
      case 12:
        object = getCharacterStream(paramInt);
        if (object == null) {
          sQLXML = null;
        } else {
          sQLXML = new JDBCSQLXML((Reader)object);
        } 
        return sQLXML;
      case -9:
      case -8:
        object = getNCharacterStream(paramInt);
        if (object == null) {
          sQLXML = null;
        } else {
          sQLXML = new JDBCSQLXML((Reader)object);
        } 
        return sQLXML;
      case 30:
        object = getBlob(paramInt);
        if (object == null) {
          sQLXML = null;
        } else {
          sQLXML = new JDBCSQLXML(object.getBinaryStream());
        } 
        return sQLXML;
      case 60:
      case 61:
        object = getBinaryStream(paramInt);
        if (object == null) {
          sQLXML = null;
        } else {
          sQLXML = new JDBCSQLXML((InputStream)object);
        } 
        return sQLXML;
      case 1111:
      case 2000:
        object = getObject(paramInt);
        if (object == null) {
          sQLXML = null;
        } else if (object instanceof SQLXML) {
          sQLXML = (SQLXML)object;
        } else if (object instanceof String) {
          sQLXML = new JDBCSQLXML((String)object);
        } else if (object instanceof byte[]) {
          sQLXML = new JDBCSQLXML((byte[])object);
        } else if (object instanceof Blob) {
          Blob blob = (Blob)object;
          sQLXML = new JDBCSQLXML(blob.getBinaryStream());
        } else if (object instanceof Clob) {
          Clob clob = (Clob)object;
          sQLXML = new JDBCSQLXML(clob.getCharacterStream());
        } else {
          throw JDBCUtil.notSupported();
        } 
        return sQLXML;
    } 
    throw JDBCUtil.notSupported();
  }
  
  public SQLXML getSQLXML(String paramString) throws SQLException {
    return getSQLXML(findColumn(paramString));
  }
  
  public void updateSQLXML(int paramInt, SQLXML paramSQLXML) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setSQLXML(paramInt, paramSQLXML);
  }
  
  public void updateSQLXML(String paramString, SQLXML paramSQLXML) throws SQLException {
    updateSQLXML(findColumn(paramString), paramSQLXML);
  }
  
  public String getNString(int paramInt) throws SQLException {
    return getString(paramInt);
  }
  
  public String getNString(String paramString) throws SQLException {
    return getString(findColumn(paramString));
  }
  
  public Reader getNCharacterStream(int paramInt) throws SQLException {
    return getCharacterStream(paramInt);
  }
  
  public Reader getNCharacterStream(String paramString) throws SQLException {
    return getCharacterStream(findColumn(paramString));
  }
  
  public void updateNCharacterStream(int paramInt, Reader paramReader, long paramLong) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setCharacterStream(paramInt, paramReader, paramLong);
  }
  
  public void updateNCharacterStream(String paramString, Reader paramReader, long paramLong) throws SQLException {
    updateCharacterStream(paramString, paramReader, paramLong);
  }
  
  public void updateAsciiStream(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setAsciiStream(paramInt, paramInputStream, paramLong);
  }
  
  public void updateBinaryStream(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setBinaryStream(paramInt, paramInputStream, paramLong);
  }
  
  public void updateCharacterStream(int paramInt, Reader paramReader, long paramLong) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setCharacterStream(paramInt, paramReader, paramLong);
  }
  
  public void updateAsciiStream(String paramString, InputStream paramInputStream, long paramLong) throws SQLException {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setAsciiStream(i, paramInputStream, paramLong);
  }
  
  public void updateBinaryStream(String paramString, InputStream paramInputStream, long paramLong) throws SQLException {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setBinaryStream(i, paramInputStream, paramLong);
  }
  
  public void updateCharacterStream(String paramString, Reader paramReader, long paramLong) throws SQLException {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setCharacterStream(i, paramReader, paramLong);
  }
  
  public void updateBlob(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setBlob(paramInt, paramInputStream, paramLong);
  }
  
  public void updateBlob(String paramString, InputStream paramInputStream, long paramLong) throws SQLException {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setBlob(i, paramInputStream, paramLong);
  }
  
  public void updateClob(int paramInt, Reader paramReader, long paramLong) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setClob(paramInt, paramReader, paramLong);
  }
  
  public void updateClob(String paramString, Reader paramReader, long paramLong) throws SQLException {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setClob(i, paramReader, paramLong);
  }
  
  public void updateNClob(int paramInt, Reader paramReader, long paramLong) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setClob(paramInt, paramReader, paramLong);
  }
  
  public void updateNClob(String paramString, Reader paramReader, long paramLong) throws SQLException {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setClob(i, paramReader, paramLong);
  }
  
  public void updateNCharacterStream(int paramInt, Reader paramReader) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setCharacterStream(paramInt, paramReader);
  }
  
  public void updateNCharacterStream(String paramString, Reader paramReader) throws SQLException {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setCharacterStream(i, paramReader);
  }
  
  public void updateAsciiStream(int paramInt, InputStream paramInputStream) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setAsciiStream(paramInt, paramInputStream);
  }
  
  public void updateBinaryStream(int paramInt, InputStream paramInputStream) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setBinaryStream(paramInt, paramInputStream);
  }
  
  public void updateCharacterStream(int paramInt, Reader paramReader) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setCharacterStream(paramInt, paramReader);
  }
  
  public void updateAsciiStream(String paramString, InputStream paramInputStream) throws SQLException {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setAsciiStream(i, paramInputStream);
  }
  
  public void updateBinaryStream(String paramString, InputStream paramInputStream) throws SQLException {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setBinaryStream(i, paramInputStream);
  }
  
  public void updateCharacterStream(String paramString, Reader paramReader) throws SQLException {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setCharacterStream(i, paramReader);
  }
  
  public void updateBlob(int paramInt, InputStream paramInputStream) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setBlob(paramInt, paramInputStream);
  }
  
  public void updateBlob(String paramString, InputStream paramInputStream) throws SQLException {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setBlob(i, paramInputStream);
  }
  
  public void updateClob(int paramInt, Reader paramReader) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setClob(paramInt, paramReader);
  }
  
  public void updateClob(String paramString, Reader paramReader) throws SQLException {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setClob(i, paramReader);
  }
  
  public void updateNClob(int paramInt, Reader paramReader) throws SQLException {
    startUpdate(paramInt);
    this.preparedStatement.setClob(paramInt, paramReader);
  }
  
  public void updateNClob(String paramString, Reader paramReader) throws SQLException {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setClob(i, paramReader);
  }
  
  public <T> T unwrap(Class<T> paramClass) throws SQLException {
    if (isWrapperFor(paramClass))
      return (T)this; 
    throw JDBCUtil.invalidArgument("iface: " + paramClass);
  }
  
  public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
    return (paramClass != null && paramClass.isAssignableFrom(getClass()));
  }
  
  public <T> T getObject(int paramInt, Class<T> paramClass) throws SQLException {
    return (T)getObject(paramInt);
  }
  
  public <T> T getObject(String paramString, Class<T> paramClass) throws SQLException {
    return getObject(findColumn(paramString), paramClass);
  }
  
  protected Object[] getCurrent() throws SQLException {
    RowSetNavigator rowSetNavigator = this.navigator;
    if (rowSetNavigator == null)
      throw JDBCUtil.sqlException(3601); 
    if (rowSetNavigator.isEmpty())
      throw JDBCUtil.sqlException(3603, 70); 
    if (rowSetNavigator.isBeforeFirst())
      throw JDBCUtil.sqlException(3603, 71); 
    if (rowSetNavigator.isAfterLast())
      throw JDBCUtil.sqlException(3603, 72); 
    Object[] arrayOfObject = rowSetNavigator.getCurrent();
    if (arrayOfObject == null)
      throw JDBCUtil.sqlException(3601); 
    return arrayOfObject;
  }
  
  private void checkClosed() throws SQLException {
    if (this.navigator == null)
      throw JDBCUtil.sqlException(3601); 
  }
  
  protected void checkColumn(int paramInt) throws SQLException {
    if (this.navigator == null)
      throw JDBCUtil.sqlException(3601); 
    if (paramInt < 1 || paramInt > this.columnCount)
      throw JDBCUtil.sqlException(421, String.valueOf(paramInt)); 
  }
  
  protected boolean trackNull(Object paramObject) {
    return this.wasNullValue = (paramObject == null);
  }
  
  protected Object getColumnInType(int paramInt, Type paramType) throws SQLException {
    CharacterType characterType;
    Object[] arrayOfObject = getCurrent();
    checkColumn(paramInt);
    Type type = this.resultMetaData.columnTypes[--paramInt];
    Object object = arrayOfObject[paramInt];
    if (trackNull(object))
      return null; 
    if (this.translateTTIType && paramType.isIntervalType())
      characterType = ((IntervalType)paramType).getCharacterType(); 
    if (type.typeCode != ((Type)characterType).typeCode)
      try {
        object = characterType.convertToTypeJDBC(this.session, object, type);
      } catch (Exception exception) {
        String str1 = (object instanceof Number || object instanceof String) ? object.toString() : ("instance of " + object.getClass().getName());
        String str2 = "from SQL type " + type.getNameString() + " to " + characterType.getJDBCClassName() + ", value: " + str1;
        JDBCUtil.throwError(Error.error(5561, str2));
      }  
    return object;
  }
  
  private void checkNotForwardOnly() throws SQLException {
    if (!this.isScrollable)
      throw JDBCUtil.notSupported(); 
  }
  
  private void checkUpdatable() throws SQLException {
    checkClosed();
    if (!this.isUpdatable)
      throw JDBCUtil.notUpdatableColumn(); 
  }
  
  private void checkUpdatable(int paramInt) throws SQLException {
    checkClosed();
    checkColumn(paramInt);
    if (!this.isUpdatable)
      throw JDBCUtil.notUpdatableColumn(); 
    if (this.resultMetaData.colIndexes[--paramInt] == -1)
      throw JDBCUtil.notUpdatableColumn(); 
    if (!this.resultMetaData.columns[paramInt].isWriteable())
      throw JDBCUtil.notUpdatableColumn(); 
  }
  
  void startUpdate(int paramInt) throws SQLException {
    checkUpdatable(paramInt);
    if (this.currentUpdateRowNumber != this.navigator.getRowNumber())
      this.preparedStatement.clearParameters(); 
    this.currentUpdateRowNumber = this.navigator.getRowNumber();
    this.isRowUpdated = true;
  }
  
  private void clearUpdates() throws SQLException {
    checkUpdatable();
    this.preparedStatement.clearParameters();
    this.isRowUpdated = false;
  }
  
  private void startInsert() throws SQLException {
    checkUpdatable();
    this.isOnInsertRow = true;
  }
  
  private void endInsert() throws SQLException {
    checkUpdatable();
    this.preparedStatement.clearParameters();
    this.isOnInsertRow = false;
  }
  
  private void performUpdate() throws SQLException {
    this.preparedStatement.parameterValues[this.columnCount] = getCurrent()[this.columnCount];
    for (byte b = 0; b < this.columnCount; b++) {
      boolean bool = (this.preparedStatement.parameterSet[b] != null) ? true : false;
      this.preparedStatement.resultOut.metaData.columnTypes[b] = bool ? this.preparedStatement.parameterTypes[b] : Type.SQL_ALL_TYPES;
    } 
    this.preparedStatement.resultOut.setActionType(81);
    this.preparedStatement.fetchResult();
    this.preparedStatement.clearParameters();
    this.rootWarning = this.preparedStatement.getWarnings();
    this.preparedStatement.clearWarnings();
    this.isRowUpdated = false;
  }
  
  private void performInsert() throws SQLException {
    checkUpdatable();
    for (byte b = 0; b < this.columnCount; b++) {
      boolean bool = (this.preparedStatement.parameterSet[b] != null) ? true : false;
      if (!bool)
        throw JDBCUtil.sqlException(3606); 
      this.preparedStatement.resultOut.metaData.columnTypes[b] = this.preparedStatement.parameterTypes[b];
    } 
    this.preparedStatement.resultOut.setActionType(50);
    this.preparedStatement.fetchResult();
    this.preparedStatement.clearParameters();
    this.rootWarning = this.preparedStatement.getWarnings();
    this.preparedStatement.clearWarnings();
  }
  
  private void performDelete() throws SQLException {
    checkUpdatable();
    this.preparedStatement.parameterValues[this.columnCount] = getCurrent()[this.columnCount];
    this.preparedStatement.resultOut.metaData.columnTypes[this.columnCount] = this.resultMetaData.columnTypes[this.columnCount];
    this.preparedStatement.resultOut.setActionType(18);
    this.preparedStatement.fetchResult();
    this.preparedStatement.clearParameters();
    this.rootWarning = this.preparedStatement.getWarnings();
    this.preparedStatement.clearWarnings();
  }
  
  RowSetNavigator getNavigator() {
    return this.navigator;
  }
  
  void setNavigator(RowSetNavigator paramRowSetNavigator) {
    this.navigator = paramRowSetNavigator;
  }
  
  public JDBCResultSet(JDBCConnection paramJDBCConnection, JDBCStatementBase paramJDBCStatementBase, Result paramResult, ResultMetaData paramResultMetaData) {
    this(paramJDBCConnection, paramResult, paramResultMetaData);
    this.statement = paramJDBCStatementBase;
    this.isScrollable = ResultProperties.isScrollable(this.rsProperties);
    if (ResultProperties.isUpdatable(this.rsProperties)) {
      this.isUpdatable = true;
      this.isInsertable = true;
      for (byte b = 0; b < paramResultMetaData.colIndexes.length; b++) {
        if (paramResultMetaData.colIndexes[b] < 0) {
          this.isInsertable = false;
          break;
        } 
      } 
      this.preparedStatement = new JDBCPreparedStatement(paramJDBCStatementBase.connection, this.result);
    } 
  }
  
  public JDBCResultSet(JDBCConnection paramJDBCConnection, Result paramResult, ResultMetaData paramResultMetaData) {
    this.session = (paramJDBCConnection == null) ? null : paramJDBCConnection.sessionProxy;
    this.result = paramResult;
    this.connection = paramJDBCConnection;
    this.rsProperties = paramResult.rsProperties;
    this.navigator = paramResult.getNavigator();
    this.resultMetaData = paramResultMetaData;
    this.columnCount = this.resultMetaData.getColumnCount();
    if (paramJDBCConnection != null && paramJDBCConnection.clientProperties != null)
      this.translateTTIType = paramJDBCConnection.clientProperties.isPropertyTrue("jdbc.translate_tti_types"); 
  }
  
  public static JDBCResultSet newJDBCResultSet(Result paramResult, ResultMetaData paramResultMetaData) {
    return new JDBCResultSetBasic(paramResult, paramResultMetaData);
  }
  
  public static JDBCResultSet newEptyResultSet() {
    ResultMetaData resultMetaData = ResultMetaData.newResultMetaData(1);
    ColumnBase columnBase = new ColumnBase(null, null, null, "C1");
    columnBase.setType((Type)Type.SQL_INTEGER);
    resultMetaData.columnTypes[0] = (Type)Type.SQL_INTEGER;
    resultMetaData.columns[0] = columnBase;
    Result result = Result.newSingleColumnResult(resultMetaData);
    return newJDBCResultSet(result, resultMetaData);
  }
  
  static class JDBCResultSetBasic extends JDBCResultSet {
    JDBCResultSetBasic(Result param1Result, ResultMetaData param1ResultMetaData) {
      super(null, param1Result, param1ResultMetaData);
    }
    
    protected Object getColumnInType(int param1Int, Type param1Type) throws SQLException {
      Object[] arrayOfObject = getCurrent();
      checkColumn(param1Int);
      Type type = this.resultMetaData.columnTypes[--param1Int];
      Object object = arrayOfObject[param1Int];
      if (trackNull(object))
        return null; 
      if (type.typeCode != param1Type.typeCode)
        JDBCUtil.throwError(Error.error(5561)); 
      return object;
    }
    
    public Date getDate(int param1Int) throws SQLException {
      return (Date)getColumnInType(param1Int, (Type)Type.SQL_DATE);
    }
    
    public Time getTime(int param1Int) throws SQLException {
      return (Time)getColumnInType(param1Int, (Type)Type.SQL_DATE);
    }
    
    public Timestamp getTimestamp(int param1Int) throws SQLException {
      return (Timestamp)getColumnInType(param1Int, (Type)Type.SQL_DATE);
    }
    
    public InputStream getBinaryStream(int param1Int) throws SQLException {
      throw JDBCUtil.notSupported();
    }
    
    public Reader getCharacterStream(int param1Int) throws SQLException {
      throw JDBCUtil.notSupported();
    }
    
    public Blob getBlob(int param1Int) throws SQLException {
      checkColumn(param1Int);
      Type type = this.resultMetaData.columnTypes[param1Int - 1];
      Object object = getColumnInType(param1Int, type);
      if (object == null)
        return null; 
      if (object instanceof Blob)
        return (Blob)object; 
      if (object instanceof byte[])
        return new JDBCBlob((byte[])object); 
      throw JDBCUtil.sqlException(5561);
    }
    
    public Clob getClob(int param1Int) throws SQLException {
      checkColumn(param1Int);
      Type type = this.resultMetaData.columnTypes[param1Int - 1];
      Object object = getColumnInType(param1Int, type);
      if (object == null)
        return null; 
      if (object instanceof Clob)
        return (Clob)object; 
      if (object instanceof String)
        return new JDBCClob((String)object); 
      throw JDBCUtil.sqlException(5561);
    }
    
    public Time getTime(int param1Int, Calendar param1Calendar) throws SQLException {
      throw JDBCUtil.notSupported();
    }
    
    public Timestamp getTimestamp(int param1Int, Calendar param1Calendar) throws SQLException {
      throw JDBCUtil.notSupported();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */