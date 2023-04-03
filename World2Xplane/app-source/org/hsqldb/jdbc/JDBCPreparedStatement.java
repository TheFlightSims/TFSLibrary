package org.hsqldb.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.BatchUpdateException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.CharArrayWriter;
import org.hsqldb.lib.CountdownInputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultLob;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.result.ResultProperties;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.BlobInputStream;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.ClobInputStream;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class JDBCPreparedStatement extends JDBCStatementBase implements PreparedStatement {
  boolean poolable = true;
  
  protected Object[] parameterValues;
  
  protected Boolean[] parameterSet;
  
  protected Type[] parameterTypes;
  
  protected byte[] parameterModes;
  
  protected long[] streamLengths;
  
  protected boolean hasStreams;
  
  protected boolean hasLOBs;
  
  protected boolean isBatch;
  
  protected ResultMetaData resultMetaData;
  
  protected ResultMetaData parameterMetaData;
  
  protected JDBCResultSetMetaData resultSetMetaData;
  
  protected Object pmd;
  
  protected String sql;
  
  protected long statementID;
  
  protected int statementRetType;
  
  protected final boolean isResult = false;
  
  protected SessionInterface session;
  
  public synchronized ResultSet executeQuery() throws SQLException {
    if (this.statementRetType != 2)
      checkStatementType(2); 
    fetchResult();
    return getResultSet();
  }
  
  public synchronized int executeUpdate() throws SQLException {
    if (this.statementRetType != 1)
      checkStatementType(1); 
    fetchResult();
    return this.resultIn.getUpdateCount();
  }
  
  public synchronized void setNull(int paramInt1, int paramInt2) throws SQLException {
    setParameter(paramInt1, (Object)null);
  }
  
  public synchronized void setBoolean(int paramInt, boolean paramBoolean) throws SQLException {
    Boolean bool = paramBoolean ? Boolean.TRUE : Boolean.FALSE;
    setParameter(paramInt, bool);
  }
  
  public synchronized void setByte(int paramInt, byte paramByte) throws SQLException {
    setIntParameter(paramInt, paramByte);
  }
  
  public synchronized void setShort(int paramInt, short paramShort) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    checkSetParameterIndex(paramInt);
    if ((this.parameterTypes[paramInt - 1]).typeCode == 5) {
      this.parameterValues[--paramInt] = Integer.valueOf(paramShort);
      this.parameterSet[paramInt] = Boolean.TRUE;
      return;
    } 
    setIntParameter(paramInt, paramShort);
  }
  
  public synchronized void setInt(int paramInt1, int paramInt2) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    checkSetParameterIndex(paramInt1);
    if ((this.parameterTypes[paramInt1 - 1]).typeCode == 4) {
      this.parameterValues[--paramInt1] = Integer.valueOf(paramInt2);
      this.parameterSet[paramInt1] = Boolean.TRUE;
      return;
    } 
    setIntParameter(paramInt1, paramInt2);
  }
  
  public synchronized void setLong(int paramInt, long paramLong) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    checkSetParameterIndex(paramInt);
    if ((this.parameterTypes[paramInt - 1]).typeCode == 25) {
      this.parameterValues[--paramInt] = Long.valueOf(paramLong);
      this.parameterSet[paramInt] = Boolean.TRUE;
      return;
    } 
    setLongParameter(paramInt, paramLong);
  }
  
  public synchronized void setFloat(int paramInt, float paramFloat) throws SQLException {
    setDouble(paramInt, paramFloat);
  }
  
  public synchronized void setDouble(int paramInt, double paramDouble) throws SQLException {
    Double double_ = new Double(paramDouble);
    setParameter(paramInt, double_);
  }
  
  public synchronized void setBigDecimal(int paramInt, BigDecimal paramBigDecimal) throws SQLException {
    setParameter(paramInt, paramBigDecimal);
  }
  
  public synchronized void setString(int paramInt, String paramString) throws SQLException {
    setParameter(paramInt, paramString);
  }
  
  public synchronized void setBytes(int paramInt, byte[] paramArrayOfbyte) throws SQLException {
    setParameter(paramInt, paramArrayOfbyte);
  }
  
  public synchronized void setDate(int paramInt, Date paramDate) throws SQLException {
    setParameter(paramInt, paramDate);
  }
  
  public synchronized void setTime(int paramInt, Time paramTime) throws SQLException {
    setParameter(paramInt, paramTime);
  }
  
  public synchronized void setTimestamp(int paramInt, Timestamp paramTimestamp) throws SQLException {
    setParameter(paramInt, paramTimestamp);
  }
  
  public synchronized void setAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException {
    setAsciiStream(paramInt1, paramInputStream, paramInt2);
  }
  
  public synchronized void setUnicodeStream(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException {
    checkSetParameterIndex(paramInt1);
    Object object = null;
    if (paramInputStream == null)
      throw JDBCUtil.nullArgument("x"); 
    String str = "UTF8";
    StringWriter stringWriter = new StringWriter();
    try {
      CountdownInputStream countdownInputStream = new CountdownInputStream(paramInputStream);
      InputStreamReader inputStreamReader = new InputStreamReader((InputStream)countdownInputStream, str);
      char[] arrayOfChar = new char[1024];
      countdownInputStream.setCount(paramInt2);
      int i;
      while (-1 != (i = inputStreamReader.read(arrayOfChar)))
        stringWriter.write(arrayOfChar, 0, i); 
    } catch (IOException iOException) {
      throw JDBCUtil.sqlException(401, iOException.toString(), iOException);
    } 
    setParameter(paramInt1, stringWriter.toString());
  }
  
  public synchronized void setBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException {
    setBinaryStream(paramInt1, paramInputStream, paramInt2);
  }
  
  public synchronized void clearParameters() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    ArrayUtil.fillArray(this.parameterValues, null);
    ArrayUtil.fillArray((Object[])this.parameterSet, null);
    ArrayUtil.clearArray(74, this.streamLengths, 0, this.streamLengths.length);
  }
  
  public synchronized void setObject(int paramInt1, Object paramObject, int paramInt2, int paramInt3) throws SQLException {
    if (paramObject instanceof InputStream) {
      setBinaryStream(paramInt1, (InputStream)paramObject, paramInt3);
    } else if (paramObject instanceof Reader) {
      setCharacterStream(paramInt1, (Reader)paramObject, paramInt3);
    } else {
      setObject(paramInt1, paramObject);
    } 
  }
  
  public synchronized void setObject(int paramInt1, Object paramObject, int paramInt2) throws SQLException {
    setObject(paramInt1, paramObject);
  }
  
  public synchronized void setObject(int paramInt, Object paramObject) throws SQLException {
    setParameter(paramInt, paramObject);
  }
  
  public synchronized boolean execute() throws SQLException {
    fetchResult();
    return (this.statementRetType == 2);
  }
  
  public synchronized void addBatch() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    checkParametersSet();
    if (!this.isBatch) {
      this.resultOut.setBatchedPreparedExecuteRequest();
      this.isBatch = true;
    } 
    try {
      performPreExecute();
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
    int i = this.parameterValues.length;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(this.parameterValues, 0, arrayOfObject, 0, i);
    this.resultOut.addBatchedPreparedExecuteRequest(arrayOfObject);
  }
  
  public synchronized void setCharacterStream(int paramInt1, Reader paramReader, int paramInt2) throws SQLException {
    setCharacterStream(paramInt1, paramReader, paramInt2);
  }
  
  public void setRef(int paramInt, Ref paramRef) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public synchronized void setBlob(int paramInt, Blob paramBlob) throws SQLException {
    checkSetParameterIndex(paramInt);
    Type type = this.parameterTypes[paramInt - 1];
    switch (type.typeCode) {
      case 60:
      case 61:
        setBlobForBinaryParameter(paramInt, paramBlob);
        return;
      case 30:
        setBlobParameter(paramInt, paramBlob);
        return;
    } 
    throw JDBCUtil.invalidArgument();
  }
  
  private void setBlobForBinaryParameter(int paramInt, Blob paramBlob) throws SQLException {
    if (paramBlob instanceof JDBCBlob) {
      setParameter(paramInt, ((JDBCBlob)paramBlob).data());
      return;
    } 
    if (paramBlob == null) {
      setParameter(paramInt, (Object)null);
      return;
    } 
    long l = paramBlob.length();
    if (l > 2147483647L) {
      String str = "Maximum Blob input octet length exceeded: " + l;
      throw JDBCUtil.sqlException(422, str);
    } 
    try {
      InputStream inputStream = paramBlob.getBinaryStream();
      HsqlByteArrayOutputStream hsqlByteArrayOutputStream = new HsqlByteArrayOutputStream(inputStream, (int)l);
      setParameter(paramInt, hsqlByteArrayOutputStream.toByteArray());
    } catch (Throwable throwable) {
      throw JDBCUtil.sqlException(422, throwable.toString(), throwable);
    } 
  }
  
  public synchronized void setClob(int paramInt, Clob paramClob) throws SQLException {
    checkSetParameterIndex(paramInt);
    Type type = this.parameterTypes[paramInt - 1];
    switch (type.typeCode) {
      case 1:
      case 12:
        setClobForStringParameter(paramInt, paramClob);
        return;
      case 40:
        setClobParameter(paramInt, paramClob);
        return;
    } 
    throw JDBCUtil.invalidArgument();
  }
  
  private void setClobForStringParameter(int paramInt, Clob paramClob) throws SQLException {
    if (paramClob instanceof JDBCClob) {
      setParameter(paramInt, ((JDBCClob)paramClob).data());
      return;
    } 
    if (paramClob == null) {
      setParameter(paramInt, (Object)null);
      return;
    } 
    long l = paramClob.length();
    if (l > 2147483647L) {
      String str = "Max Clob input character length exceeded: " + l;
      throw JDBCUtil.sqlException(422, str);
    } 
    try {
      Reader reader = paramClob.getCharacterStream();
      CharArrayWriter charArrayWriter = new CharArrayWriter(reader, (int)l);
      setParameter(paramInt, charArrayWriter.toString());
    } catch (Throwable throwable) {
      throw JDBCUtil.sqlException(401, throwable.toString(), throwable);
    } 
  }
  
  public synchronized void setArray(int paramInt, Array paramArray) throws SQLException {
    checkParameterIndex(paramInt);
    Type type = this.parameterMetaData.columnTypes[paramInt - 1];
    if (!type.isArrayType())
      throw JDBCUtil.sqlException(5561); 
    if (paramArray == null) {
      setParameter(paramInt, (Object)null);
      return;
    } 
    Object[] arrayOfObject = null;
    if (paramArray instanceof JDBCArray) {
      arrayOfObject = ((JDBCArray)paramArray).getArrayInternal();
    } else {
      Object object = paramArray.getArray();
      if (object instanceof Object[]) {
        Type type1 = type.collectionBaseType();
        Object[] arrayOfObject1 = (Object[])object;
        arrayOfObject = new Object[arrayOfObject1.length];
        for (byte b = 0; b < arrayOfObject.length; b++)
          arrayOfObject[b] = type1.convertJavaToSQL(this.session, arrayOfObject1[b]); 
      } else {
        throw JDBCUtil.notSupported();
      } 
    } 
    this.parameterValues[paramInt - 1] = arrayOfObject;
    this.parameterSet[paramInt - 1] = Boolean.TRUE;
  }
  
  public synchronized ResultSetMetaData getMetaData() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    if (this.statementRetType != 2)
      return null; 
    if (this.resultSetMetaData == null) {
      boolean bool1 = ResultProperties.isUpdatable(this.rsProperties);
      boolean bool2 = bool1;
      if (bool2)
        for (byte b = 0; b < this.resultMetaData.colIndexes.length; b++) {
          if (this.resultMetaData.colIndexes[b] < 0) {
            bool2 = false;
            break;
          } 
        }  
      this.resultSetMetaData = new JDBCResultSetMetaData(this.resultMetaData, bool1, bool2, this.connection);
    } 
    return this.resultSetMetaData;
  }
  
  public synchronized void setDate(int paramInt, Date paramDate, Calendar paramCalendar) throws SQLException {
    int j;
    checkSetParameterIndex(paramInt);
    int i = paramInt - 1;
    if (paramDate == null) {
      this.parameterValues[i] = null;
      this.parameterSet[i] = Boolean.TRUE;
      return;
    } 
    Type type = this.parameterTypes[i];
    Calendar calendar = (paramCalendar == null) ? this.session.getCalendar() : paramCalendar;
    long l = HsqlDateTime.convertMillisFromCalendar(calendar, paramDate.getTime());
    l = HsqlDateTime.getNormalisedDate(l);
    switch (type.typeCode) {
      case 91:
      case 93:
        this.parameterValues[i] = new TimestampData(l / 1000L);
        break;
      case 95:
        j = HsqlDateTime.getZoneMillis(calendar, l);
        this.parameterValues[i] = new TimestampData(l / 1000L, 0, j / 1000);
        break;
      default:
        throw JDBCUtil.sqlException(5561);
    } 
    this.parameterSet[i] = Boolean.TRUE;
  }
  
  public synchronized void setTime(int paramInt, Time paramTime, Calendar paramCalendar) throws SQLException {
    checkSetParameterIndex(paramInt);
    int i = paramInt - 1;
    if (paramTime == null) {
      this.parameterValues[i] = null;
      this.parameterSet[i] = Boolean.TRUE;
      return;
    } 
    Type type = this.parameterTypes[i];
    long l = paramTime.getTime();
    int j = 0;
    Calendar calendar = (paramCalendar == null) ? this.session.getCalendar() : paramCalendar;
    l = HsqlDateTime.convertMillisFromCalendar(calendar, l);
    l = HsqlDateTime.convertToNormalisedTime(l);
    switch (type.typeCode) {
      case 92:
        break;
      case 94:
        j = HsqlDateTime.getZoneMillis(calendar, l);
        break;
      default:
        throw JDBCUtil.sqlException(5561);
    } 
    this.parameterValues[i] = new TimeData((int)(l / 1000L), 0, j / 1000);
    this.parameterSet[i] = Boolean.TRUE;
  }
  
  public synchronized void setTimestamp(int paramInt, Timestamp paramTimestamp, Calendar paramCalendar) throws SQLException {
    checkSetParameterIndex(paramInt);
    int i = paramInt - 1;
    if (paramTimestamp == null) {
      this.parameterValues[i] = null;
      this.parameterSet[i] = Boolean.TRUE;
      return;
    } 
    Type type = this.parameterTypes[i];
    long l = paramTimestamp.getTime();
    int j = 0;
    Calendar calendar = (paramCalendar == null) ? this.session.getCalendar() : paramCalendar;
    l = HsqlDateTime.convertMillisFromCalendar(calendar, l);
    switch (type.typeCode) {
      case 95:
        j = HsqlDateTime.getZoneMillis(calendar, l);
      case 93:
        this.parameterValues[i] = new TimestampData(l / 1000L, paramTimestamp.getNanos(), j / 1000);
        break;
      case 92:
        l = HsqlDateTime.getNormalisedTime(l);
        this.parameterValues[i] = new TimeData((int)(l / 1000L), paramTimestamp.getNanos(), 0);
        break;
      case 94:
        j = HsqlDateTime.getZoneMillis(calendar, l);
        this.parameterValues[i] = new TimeData((int)(l / 1000L), paramTimestamp.getNanos(), j / 1000);
        break;
      case 91:
        l = HsqlDateTime.getNormalisedDate(l);
        this.parameterValues[i] = new TimestampData(l / 1000L);
        break;
      default:
        throw JDBCUtil.sqlException(5561);
    } 
    this.parameterSet[i] = Boolean.TRUE;
  }
  
  public synchronized void setNull(int paramInt1, int paramInt2, String paramString) throws SQLException {
    setParameter(paramInt1, (Object)null);
  }
  
  public synchronized int[] executeBatch() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    checkStatementType(1);
    if (!this.isBatch)
      throw JDBCUtil.sqlExceptionSQL(1256); 
    this.generatedResult = null;
    int i = this.resultOut.getNavigator().getSize();
    this.resultIn = null;
    try {
      this.resultIn = this.session.execute(this.resultOut);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } finally {
      performPostExecute();
      this.resultOut.getNavigator().clear();
      this.isBatch = false;
    } 
    if (this.resultIn.mode == 2)
      throw JDBCUtil.sqlException(this.resultIn); 
    RowSetNavigator rowSetNavigator = this.resultIn.getNavigator();
    int[] arrayOfInt = new int[rowSetNavigator.getSize()];
    for (byte b = 0; b < arrayOfInt.length; b++) {
      Object[] arrayOfObject = rowSetNavigator.getNext();
      arrayOfInt[b] = ((Integer)arrayOfObject[0]).intValue();
    } 
    if (arrayOfInt.length != i) {
      if (this.errorResult == null)
        throw new BatchUpdateException(arrayOfInt); 
      this.errorResult.getMainString();
      throw new BatchUpdateException(this.errorResult.getMainString(), this.errorResult.getSubString(), this.errorResult.getErrorCode(), arrayOfInt);
    } 
    return arrayOfInt;
  }
  
  public void setEscapeProcessing(boolean paramBoolean) throws SQLException {
    checkClosed();
  }
  
  public void addBatch(String paramString) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public synchronized ResultSet executeQuery(String paramString) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public boolean execute(String paramString) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public int executeUpdate(String paramString) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public synchronized void close() throws SQLException {
    if (isClosed())
      return; 
    closeResultData();
    HsqlException hsqlException = null;
    try {
      if (!this.connection.isClosed)
        this.session.execute(Result.newFreeStmtRequest(this.statementID)); 
    } catch (HsqlException hsqlException1) {
      hsqlException = hsqlException1;
    } 
    this.parameterValues = null;
    this.parameterSet = null;
    this.parameterTypes = null;
    this.parameterModes = null;
    this.resultMetaData = null;
    this.parameterMetaData = null;
    this.resultSetMetaData = null;
    this.pmd = null;
    this.connection = null;
    this.session = null;
    this.resultIn = null;
    this.resultOut = null;
    this.isClosed = true;
    if (hsqlException != null)
      throw JDBCUtil.sqlException(hsqlException); 
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(super.toString());
    String str = this.sql;
    Object[] arrayOfObject = this.parameterValues;
    if (str == null || arrayOfObject == null) {
      stringBuffer.append("[closed]");
      return stringBuffer.toString();
    } 
    stringBuffer.append("[sql=[").append(str).append("]");
    if (arrayOfObject.length > 0) {
      stringBuffer.append(", parameters=[");
      for (byte b = 0; b < arrayOfObject.length; b++) {
        stringBuffer.append('[');
        stringBuffer.append(arrayOfObject[b]);
        stringBuffer.append("], ");
      } 
      stringBuffer.setLength(stringBuffer.length() - 2);
      stringBuffer.append(']');
    } 
    stringBuffer.append(']');
    return stringBuffer.toString();
  }
  
  public void setURL(int paramInt, URL paramURL) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public synchronized ParameterMetaData getParameterMetaData() throws SQLException {
    checkClosed();
    if (this.pmd == null)
      this.pmd = new JDBCParameterMetaData(this.connection, this.parameterMetaData); 
    return (ParameterMetaData)this.pmd;
  }
  
  public int executeUpdate(String paramString, int paramInt) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public boolean execute(String paramString, int paramInt) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public int executeUpdate(String paramString, int[] paramArrayOfint) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public boolean execute(String paramString, int[] paramArrayOfint) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public int executeUpdate(String paramString, String[] paramArrayOfString) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public boolean execute(String paramString, String[] paramArrayOfString) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public synchronized boolean getMoreResults(int paramInt) throws SQLException {
    return super.getMoreResults(paramInt);
  }
  
  public synchronized ResultSet getGeneratedKeys() throws SQLException {
    return getGeneratedResultSet();
  }
  
  public synchronized int getResultSetHoldability() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    return ResultProperties.getJDBCHoldability(this.rsProperties);
  }
  
  public synchronized boolean isClosed() {
    return this.isClosed;
  }
  
  public void setRowId(int paramInt, RowId paramRowId) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public synchronized void setNString(int paramInt, String paramString) throws SQLException {
    setString(paramInt, paramString);
  }
  
  public synchronized void setNCharacterStream(int paramInt, Reader paramReader, long paramLong) throws SQLException {
    setCharacterStream(paramInt, paramReader, paramLong);
  }
  
  public synchronized void setNClob(int paramInt, NClob paramNClob) throws SQLException {
    setClob(paramInt, paramNClob);
  }
  
  public synchronized void setClob(int paramInt, Reader paramReader, long paramLong) throws SQLException {
    setCharacterStream(paramInt, paramReader, paramLong);
  }
  
  public synchronized void setBlob(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
    setBinaryStream(paramInt, paramInputStream, paramLong);
  }
  
  public synchronized void setNClob(int paramInt, Reader paramReader, long paramLong) throws SQLException {
    setClob(paramInt, paramReader, paramLong);
  }
  
  public void setSQLXML(int paramInt, SQLXML paramSQLXML) throws SQLException {
    throw JDBCUtil.notSupported();
  }
  
  public synchronized void setAsciiStream(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
    if (paramLong < 0L)
      throw JDBCUtil.sqlException(423, "length: " + paramLong); 
    setAscStream(paramInt, paramInputStream, paramLong);
  }
  
  void setAscStream(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
    if (paramLong > 2147483647L)
      JDBCUtil.sqlException(3401); 
    if (paramInputStream == null)
      throw JDBCUtil.nullArgument("x"); 
    try {
      String str = StringConverter.inputStreamToString(paramInputStream, "US-ASCII");
      if (paramLong >= 0L && str.length() > paramLong)
        str = str.substring(0, (int)paramLong); 
      setParameter(paramInt, str);
    } catch (IOException iOException) {
      throw JDBCUtil.sqlException(422, null, iOException);
    } 
  }
  
  public synchronized void setBinaryStream(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
    if (paramLong < 0L)
      throw JDBCUtil.sqlException(423, "length: " + paramLong); 
    setBinStream(paramInt, paramInputStream, paramLong);
  }
  
  private void setBinStream(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    if ((this.parameterTypes[paramInt - 1]).typeCode == 30) {
      setBlobParameter(paramInt, paramInputStream, paramLong);
      return;
    } 
    if (paramLong > 2147483647L) {
      String str = "Maximum Blob input length exceeded: " + paramLong;
      throw JDBCUtil.sqlException(422, str);
    } 
    try {
      HsqlByteArrayOutputStream hsqlByteArrayOutputStream;
      if (paramLong < 0L) {
        hsqlByteArrayOutputStream = new HsqlByteArrayOutputStream(paramInputStream);
      } else {
        hsqlByteArrayOutputStream = new HsqlByteArrayOutputStream(paramInputStream, (int)paramLong);
      } 
      setParameter(paramInt, hsqlByteArrayOutputStream.toByteArray());
    } catch (Throwable throwable) {
      throw JDBCUtil.sqlException(422, throwable.toString(), throwable);
    } 
  }
  
  public synchronized void setCharacterStream(int paramInt, Reader paramReader, long paramLong) throws SQLException {
    if (paramLong < 0L)
      throw JDBCUtil.sqlException(423, "length: " + paramLong); 
    setCharStream(paramInt, paramReader, paramLong);
  }
  
  private void setCharStream(int paramInt, Reader paramReader, long paramLong) throws SQLException {
    checkSetParameterIndex(paramInt);
    if ((this.parameterTypes[paramInt - 1]).typeCode == 40) {
      setClobParameter(paramInt, paramReader, paramLong);
      return;
    } 
    if (paramLong > 2147483647L) {
      String str = "Maximum Clob input length exceeded: " + paramLong;
      throw JDBCUtil.sqlException(422, str);
    } 
    try {
      CharArrayWriter charArrayWriter;
      if (paramLong < 0L) {
        charArrayWriter = new CharArrayWriter(paramReader);
      } else {
        charArrayWriter = new CharArrayWriter(paramReader, (int)paramLong);
      } 
      setParameter(paramInt, charArrayWriter.toString());
    } catch (Throwable throwable) {
      throw JDBCUtil.sqlException(422, throwable.toString(), throwable);
    } 
  }
  
  public void setAsciiStream(int paramInt, InputStream paramInputStream) throws SQLException {
    setAscStream(paramInt, paramInputStream, -1L);
  }
  
  public synchronized void setBinaryStream(int paramInt, InputStream paramInputStream) throws SQLException {
    setBinStream(paramInt, paramInputStream, -1L);
  }
  
  public void setCharacterStream(int paramInt, Reader paramReader) throws SQLException {
    setCharStream(paramInt, paramReader, -1L);
  }
  
  public void setNCharacterStream(int paramInt, Reader paramReader) throws SQLException {
    setCharStream(paramInt, paramReader, -1L);
  }
  
  public void setClob(int paramInt, Reader paramReader) throws SQLException {
    setCharStream(paramInt, paramReader, -1L);
  }
  
  public void setBlob(int paramInt, InputStream paramInputStream) throws SQLException {
    setBinStream(paramInt, paramInputStream, -1L);
  }
  
  public void setNClob(int paramInt, Reader paramReader) throws SQLException {
    setCharStream(paramInt, paramReader, -1L);
  }
  
  public synchronized int getMaxFieldSize() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    return 0;
  }
  
  public synchronized void setMaxFieldSize(int paramInt) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    if (paramInt < 0)
      throw JDBCUtil.outOfRangeArgument(); 
  }
  
  public synchronized int getMaxRows() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    return this.maxRows;
  }
  
  public synchronized void setMaxRows(int paramInt) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    if (paramInt < 0)
      throw JDBCUtil.outOfRangeArgument(); 
    this.maxRows = paramInt;
  }
  
  public synchronized int getQueryTimeout() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    return this.queryTimeout;
  }
  
  public synchronized void setQueryTimeout(int paramInt) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    if (paramInt < 0)
      throw JDBCUtil.outOfRangeArgument(); 
    if (paramInt > 32767)
      paramInt = 32767; 
    this.queryTimeout = paramInt;
  }
  
  public void cancel() throws SQLException {
    checkClosed();
  }
  
  public synchronized SQLWarning getWarnings() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    return this.rootWarning;
  }
  
  public synchronized void clearWarnings() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    this.rootWarning = null;
  }
  
  public void setCursorName(String paramString) throws SQLException {
    checkClosed();
  }
  
  public synchronized ResultSet getResultSet() throws SQLException {
    return super.getResultSet();
  }
  
  public synchronized int getUpdateCount() throws SQLException {
    return super.getUpdateCount();
  }
  
  public synchronized boolean getMoreResults() throws SQLException {
    return getMoreResults(1);
  }
  
  public synchronized void setFetchDirection(int paramInt) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    if (paramInt != 1000 && paramInt != 1001 && paramInt != 1002)
      throw JDBCUtil.notSupported(); 
    this.fetchDirection = paramInt;
  }
  
  public synchronized int getFetchDirection() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    return this.fetchDirection;
  }
  
  public synchronized void setFetchSize(int paramInt) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    if (paramInt < 0)
      throw JDBCUtil.outOfRangeArgument(); 
    this.fetchSize = paramInt;
  }
  
  public synchronized int getFetchSize() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    return this.fetchSize;
  }
  
  public synchronized int getResultSetConcurrency() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    return ResultProperties.getJDBCConcurrency(this.rsProperties);
  }
  
  public synchronized int getResultSetType() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    return ResultProperties.getJDBCScrollability(this.rsProperties);
  }
  
  public synchronized void clearBatch() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    if (this.isBatch)
      this.resultOut.getNavigator().clear(); 
  }
  
  public synchronized Connection getConnection() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    return this.connection;
  }
  
  public synchronized void setPoolable(boolean paramBoolean) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    this.poolable = paramBoolean;
  }
  
  public synchronized boolean isPoolable() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    return this.poolable;
  }
  
  public <T> T unwrap(Class<T> paramClass) throws SQLException {
    if (isWrapperFor(paramClass))
      return (T)this; 
    throw JDBCUtil.invalidArgument("iface: " + paramClass);
  }
  
  public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
    return (paramClass != null && paramClass.isAssignableFrom(getClass()));
  }
  
  JDBCPreparedStatement(JDBCConnection paramJDBCConnection, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfint, String[] paramArrayOfString) throws HsqlException, SQLException {
    this.connection = paramJDBCConnection;
    this.connectionIncarnation = this.connection.incarnation;
    this.session = paramJDBCConnection.sessionProxy;
    paramString = paramJDBCConnection.nativeSQL(paramString);
    int[] arrayOfInt = null;
    if (paramArrayOfint != null) {
      arrayOfInt = new int[paramArrayOfint.length];
      for (byte b1 = 0; b1 < paramArrayOfint.length; b1++)
        arrayOfInt[b1] = paramArrayOfint[b1] - 1; 
    } 
    this.resultOut = Result.newPrepareStatementRequest();
    int i = ResultProperties.getValueForJDBC(paramInt1, paramInt2, paramInt3);
    this.resultOut.setPrepareOrExecuteProperties(paramString, 0, 0, 0, this.queryTimeout, i, paramInt4, paramArrayOfint, paramArrayOfString);
    Result result1 = this.session.execute(this.resultOut);
    if (result1.mode == 2)
      throw JDBCUtil.sqlException(result1); 
    this.rootWarning = null;
    Result result2 = result1;
    while (result2.getChainedResult() != null) {
      result2 = result2.getUnlinkChainedResult();
      if (result2.isWarning()) {
        SQLWarning sQLWarning = JDBCUtil.sqlWarning(result2);
        if (this.rootWarning == null) {
          this.rootWarning = sQLWarning;
          continue;
        } 
        this.rootWarning.setNextWarning(sQLWarning);
      } 
    } 
    this.connection.setWarnings(this.rootWarning);
    this.statementID = result1.getStatementID();
    this.statementRetType = result1.getStatementType();
    this.resultMetaData = result1.metaData;
    this.parameterMetaData = result1.parameterMetaData;
    this.parameterTypes = this.parameterMetaData.getParameterTypes();
    this.parameterModes = this.parameterMetaData.paramModes;
    this.rsProperties = result1.rsProperties;
    int j = this.parameterMetaData.getColumnCount();
    this.parameterValues = new Object[j];
    this.parameterSet = new Boolean[j];
    this.streamLengths = new long[j];
    for (byte b = 0; b < j; b++) {
      if (this.parameterTypes[b].isLobType()) {
        this.hasLOBs = true;
        break;
      } 
    } 
    this.resultOut = Result.newPreparedExecuteRequest(this.parameterTypes, this.statementID);
    this.resultOut.setStatement(result1.getStatement());
    this.sql = paramString;
  }
  
  JDBCPreparedStatement(JDBCConnection paramJDBCConnection, Result paramResult) {
    this.connection = paramJDBCConnection;
    this.connectionIncarnation = this.connection.incarnation;
    this.session = paramJDBCConnection.sessionProxy;
    int i = paramResult.metaData.getExtendedColumnCount();
    this.parameterMetaData = paramResult.metaData;
    this.parameterTypes = paramResult.metaData.columnTypes;
    this.parameterModes = new byte[i];
    this.parameterValues = new Object[i];
    this.parameterSet = new Boolean[i];
    this.streamLengths = new long[i];
    for (byte b = 0; b < i; b++) {
      this.parameterModes[b] = 1;
      if (this.parameterTypes[b].isLobType())
        this.hasLOBs = true; 
    } 
    this.resultOut = Result.newUpdateResultRequest(this.parameterTypes, paramResult.getResultId());
  }
  
  protected void checkStatementType(int paramInt) throws SQLException {
    if (paramInt != this.statementRetType) {
      if (this.statementRetType == 1)
        throw JDBCUtil.sqlException(1254); 
      throw JDBCUtil.sqlException(1253);
    } 
  }
  
  protected void checkParameterIndex(int paramInt) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    if (paramInt < 1 || paramInt > this.parameterValues.length) {
      String str = "parameter index out of range: " + paramInt;
      throw JDBCUtil.outOfRangeArgument(str);
    } 
  }
  
  protected void checkSetParameterIndex(int paramInt) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    if (paramInt < 1 || paramInt > this.parameterValues.length) {
      String str = "parameter index out of range: " + paramInt;
      throw JDBCUtil.outOfRangeArgument(str);
    } 
    if (this.parameterModes[paramInt - 1] == 4) {
      String str = "Not IN or INOUT mode for parameter: " + paramInt;
      throw JDBCUtil.invalidArgument(str);
    } 
  }
  
  protected void checkGetParameterIndex(int paramInt) throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    if (paramInt < 1 || paramInt > this.parameterValues.length) {
      String str1 = "parameter index out of range: " + paramInt;
      throw JDBCUtil.outOfRangeArgument(str1);
    } 
    byte b = this.parameterModes[paramInt - 1];
    switch (b) {
      case 0:
      case 2:
      case 4:
        return;
    } 
    String str = "Not OUT or INOUT mode: " + b + " for parameter: " + paramInt;
    throw JDBCUtil.invalidArgument(str);
  }
  
  private void checkParametersSet() throws SQLException {
    if (this.isResult)
      return; 
    for (byte b = 0; b < this.parameterSet.length; b++) {
      if (this.parameterModes[b] != 4 && this.parameterSet[b] == null)
        throw JDBCUtil.sqlException(424); 
    } 
  }
  
  void setParameter(int paramInt, Object paramObject) throws SQLException {
    checkSetParameterIndex(paramInt);
    paramInt--;
    if (paramObject == null) {
      this.parameterValues[paramInt] = null;
      this.parameterSet[paramInt] = Boolean.TRUE;
      return;
    } 
    Type type = this.parameterTypes[paramInt];
    switch (type.typeCode) {
      case 1111:
        try {
          if (paramObject instanceof Serializable) {
            paramObject = new JavaObjectData((Serializable)paramObject);
            break;
          } 
        } catch (HsqlException hsqlException) {
          JDBCUtil.throwError(hsqlException);
        } 
        JDBCUtil.throwError(Error.error(5563));
      case 14:
      case 15:
        try {
          if (paramObject instanceof Boolean) {
            paramObject = type.convertToDefaultType(this.session, paramObject);
            break;
          } 
          if (paramObject instanceof Integer) {
            paramObject = type.convertToDefaultType(this.session, paramObject);
            break;
          } 
          if (paramObject instanceof byte[]) {
            paramObject = type.convertToDefaultType(this.session, paramObject);
            break;
          } 
          if (paramObject instanceof String) {
            paramObject = type.convertToDefaultType(this.session, paramObject);
            break;
          } 
        } catch (HsqlException hsqlException) {
          JDBCUtil.throwError(hsqlException);
        } 
        JDBCUtil.throwError(Error.error(5563));
      case 60:
      case 61:
        if (paramObject instanceof byte[]) {
          paramObject = new BinaryData((byte[])paramObject, !this.connection.isNetConn);
          break;
        } 
        try {
          if (paramObject instanceof String) {
            paramObject = type.convertToDefaultType(this.session, paramObject);
            break;
          } 
        } catch (HsqlException hsqlException) {
          JDBCUtil.throwError(hsqlException);
        } 
        JDBCUtil.throwError(Error.error(5563));
        break;
      case 50:
        if (paramObject instanceof Array) {
          setArray(paramInt + 1, (Array)paramObject);
          return;
        } 
        if (paramObject instanceof ArrayList)
          paramObject = ((ArrayList)paramObject).toArray(); 
        if (paramObject instanceof Object[]) {
          Type type1 = type.collectionBaseType();
          Object[] arrayOfObject1 = (Object[])paramObject;
          Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
          for (byte b = 0; b < arrayOfObject2.length; b++)
            arrayOfObject2[b] = type1.convertJavaToSQL(this.session, arrayOfObject1[b]); 
          paramObject = arrayOfObject2;
          break;
        } 
        JDBCUtil.throwError(Error.error(5563));
      case 30:
        setBlobParameter(paramInt + 1, paramObject);
        return;
      case 40:
        setClobParameter(paramInt + 1, paramObject);
        return;
      case 91:
      case 92:
      case 93:
      case 94:
      case 95:
        try {
          if (paramObject instanceof String) {
            paramObject = type.convertToType(this.session, paramObject, (Type)Type.SQL_VARCHAR);
            break;
          } 
          paramObject = type.convertJavaToSQL(this.session, paramObject);
          break;
        } catch (HsqlException hsqlException) {
          JDBCUtil.throwError(hsqlException);
        } 
      case -6:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 25:
        try {
          if (paramObject instanceof String) {
            paramObject = type.convertToType(this.session, paramObject, (Type)Type.SQL_VARCHAR);
            break;
          } 
          if (paramObject instanceof Boolean) {
            boolean bool = ((Boolean)paramObject).booleanValue();
            paramObject = bool ? Integer.valueOf(1) : Integer.valueOf(0);
          } 
          paramObject = type.convertToDefaultType(this.session, paramObject);
          break;
        } catch (HsqlException hsqlException) {
          JDBCUtil.throwError(hsqlException);
        } 
      case 12:
        if (paramObject instanceof String)
          break; 
        try {
          paramObject = type.convertToDefaultType(this.session, paramObject);
          break;
        } catch (HsqlException hsqlException) {
          JDBCUtil.throwError(hsqlException);
        } 
      case 1:
        if (type.precision == 1L) {
          if (paramObject instanceof Character) {
            paramObject = new String(new char[] { ((Character)paramObject).charValue() });
            break;
          } 
          if (paramObject instanceof Boolean) {
            paramObject = ((Boolean)paramObject).booleanValue() ? "1" : "0";
            break;
          } 
        } 
      default:
        try {
          paramObject = type.convertToDefaultType(this.session, paramObject);
        } catch (HsqlException hsqlException) {
          JDBCUtil.throwError(hsqlException);
        } 
        break;
    } 
    this.parameterValues[paramInt] = paramObject;
    this.parameterSet[paramInt] = Boolean.TRUE;
  }
  
  void setClobParameter(int paramInt, Object paramObject) throws SQLException {
    setClobParameter(paramInt, paramObject, 0L);
  }
  
  void setClobParameter(int paramInt, Object paramObject, long paramLong) throws SQLException {
    if (paramObject instanceof JDBCClobClient) {
      JDBCClobClient jDBCClobClient = (JDBCClobClient)paramObject;
      if (!jDBCClobClient.session.getDatabaseUniqueName().equals(this.session.getDatabaseUniqueName())) {
        paramLong = jDBCClobClient.length();
        Reader reader = jDBCClobClient.getCharacterStream();
        this.parameterValues[paramInt - 1] = reader;
        this.streamLengths[paramInt - 1] = paramLong;
        this.parameterSet[paramInt - 1] = Boolean.FALSE;
        return;
      } 
      this.parameterValues[paramInt - 1] = paramObject;
      this.parameterSet[paramInt - 1] = Boolean.TRUE;
      return;
    } 
    if (paramObject instanceof Clob) {
      this.parameterValues[paramInt - 1] = paramObject;
      this.parameterSet[paramInt - 1] = Boolean.TRUE;
      return;
    } 
    if (paramObject instanceof ClobInputStream) {
      ClobInputStream clobInputStream = (ClobInputStream)paramObject;
      if (clobInputStream.session.getDatabaseUniqueName().equals(this.session.getDatabaseUniqueName()))
        throw JDBCUtil.sqlException(423, "invalid Reader"); 
      this.parameterValues[paramInt - 1] = paramObject;
      this.streamLengths[paramInt - 1] = paramLong;
      this.parameterSet[paramInt - 1] = Boolean.FALSE;
      return;
    } 
    if (paramObject instanceof Reader) {
      this.parameterValues[paramInt - 1] = paramObject;
      this.streamLengths[paramInt - 1] = paramLong;
      this.parameterSet[paramInt - 1] = Boolean.FALSE;
      return;
    } 
    if (paramObject instanceof String) {
      JDBCClob jDBCClob = new JDBCClob((String)paramObject);
      this.parameterValues[paramInt - 1] = jDBCClob;
      this.parameterSet[paramInt - 1] = Boolean.valueOf(false);
      return;
    } 
    throw JDBCUtil.invalidArgument();
  }
  
  void setBlobParameter(int paramInt, Object paramObject) throws SQLException {
    setBlobParameter(paramInt, paramObject, 0L);
  }
  
  void setBlobParameter(int paramInt, Object paramObject, long paramLong) throws SQLException {
    if (paramObject instanceof JDBCBlobClient) {
      JDBCBlobClient jDBCBlobClient = (JDBCBlobClient)paramObject;
      if (!jDBCBlobClient.session.getDatabaseUniqueName().equals(this.session.getDatabaseUniqueName())) {
        paramLong = jDBCBlobClient.length();
        InputStream inputStream = jDBCBlobClient.getBinaryStream();
        this.parameterValues[paramInt - 1] = inputStream;
        this.streamLengths[paramInt - 1] = paramLong;
        this.parameterSet[paramInt - 1] = Boolean.FALSE;
        return;
      } 
      this.parameterValues[paramInt - 1] = paramObject;
      this.parameterSet[paramInt - 1] = Boolean.TRUE;
      return;
    } 
    if (paramObject instanceof Blob) {
      this.parameterValues[paramInt - 1] = paramObject;
      this.parameterSet[paramInt - 1] = Boolean.FALSE;
      return;
    } 
    if (paramObject instanceof BlobInputStream) {
      BlobInputStream blobInputStream = (BlobInputStream)paramObject;
      if (blobInputStream.session.getDatabaseUniqueName().equals(this.session.getDatabaseUniqueName()))
        throw JDBCUtil.sqlException(423, "invalid Reader"); 
      this.parameterValues[paramInt - 1] = paramObject;
      this.streamLengths[paramInt - 1] = paramLong;
      this.parameterSet[paramInt - 1] = Boolean.FALSE;
      return;
    } 
    if (paramObject instanceof InputStream) {
      this.parameterValues[paramInt - 1] = paramObject;
      this.streamLengths[paramInt - 1] = paramLong;
      this.parameterSet[paramInt - 1] = Boolean.FALSE;
      return;
    } 
    if (paramObject instanceof byte[]) {
      JDBCBlob jDBCBlob = new JDBCBlob((byte[])paramObject);
      this.parameterValues[paramInt - 1] = jDBCBlob;
      this.parameterSet[paramInt - 1] = Boolean.TRUE;
      return;
    } 
    throw JDBCUtil.invalidArgument();
  }
  
  void setIntParameter(int paramInt1, int paramInt2) throws SQLException {
    Integer integer;
    Long long_;
    checkSetParameterIndex(paramInt1);
    int i = (this.parameterTypes[paramInt1 - 1]).typeCode;
    switch (i) {
      case -6:
      case 4:
      case 5:
        integer = Integer.valueOf(paramInt2);
        this.parameterValues[paramInt1 - 1] = integer;
        this.parameterSet[paramInt1 - 1] = Boolean.TRUE;
        return;
      case 25:
        long_ = Long.valueOf(paramInt2);
        this.parameterValues[paramInt1 - 1] = long_;
        this.parameterSet[paramInt1 - 1] = Boolean.TRUE;
        return;
      case 60:
      case 61:
      case 1111:
        throw JDBCUtil.sqlException(Error.error(5563));
    } 
    setParameter(paramInt1, Integer.valueOf(paramInt2));
  }
  
  void setLongParameter(int paramInt, long paramLong) throws SQLException {
    Long long_;
    checkSetParameterIndex(paramInt);
    int i = (this.parameterTypes[paramInt - 1]).typeCode;
    switch (i) {
      case 25:
        long_ = Long.valueOf(paramLong);
        this.parameterValues[paramInt - 1] = long_;
        this.parameterSet[paramInt - 1] = Boolean.TRUE;
        return;
      case 60:
      case 61:
      case 1111:
        throw JDBCUtil.sqlException(Error.error(5563));
    } 
    setParameter(paramInt, Long.valueOf(paramLong));
  }
  
  private void performPreExecute() throws SQLException, HsqlException {
    if (!this.hasLOBs)
      return; 
    for (byte b = 0; b < this.parameterValues.length; b++) {
      Object object = this.parameterValues[b];
      if (object != null)
        if ((this.parameterTypes[b]).typeCode == 30) {
          BlobDataID blobDataID = null;
          if (object instanceof JDBCBlobClient) {
            blobDataID = ((JDBCBlobClient)object).blob;
            long l = blobDataID.getId();
          } else if (object instanceof Blob) {
            long l2 = ((Blob)object).length();
            blobDataID = this.session.createBlob(l2);
            long l1 = blobDataID.getId();
            InputStream inputStream = ((Blob)object).getBinaryStream();
            ResultLob resultLob = ResultLob.newLobCreateBlobRequest(this.session.getId(), l1, inputStream, l2);
            this.session.allocateResultLob(resultLob, null);
            this.resultOut.addLobResult(resultLob);
          } else if (object instanceof InputStream) {
            long l2 = this.streamLengths[b];
            long l3 = (l2 > 0L) ? l2 : 0L;
            blobDataID = this.session.createBlob(l3);
            long l1 = blobDataID.getId();
            InputStream inputStream = (InputStream)object;
            ResultLob resultLob = ResultLob.newLobCreateBlobRequest(this.session.getId(), l1, inputStream, l2);
            this.session.allocateResultLob(resultLob, null);
            this.resultOut.addLobResult(resultLob);
          } else if (object instanceof BlobDataID) {
            blobDataID = (BlobDataID)object;
          } 
          this.parameterValues[b] = blobDataID;
        } else if ((this.parameterTypes[b]).typeCode == 40) {
          ClobDataID clobDataID = null;
          if (object instanceof JDBCClobClient) {
            clobDataID = ((JDBCClobClient)object).clob;
            long l = clobDataID.getId();
          } else if (object instanceof Clob) {
            long l2 = ((Clob)object).length();
            Reader reader = ((Clob)object).getCharacterStream();
            clobDataID = this.session.createClob(l2);
            long l1 = clobDataID.getId();
            ResultLob resultLob = ResultLob.newLobCreateClobRequest(this.session.getId(), l1, reader, l2);
            this.session.allocateResultLob(resultLob, null);
            this.resultOut.addLobResult(resultLob);
          } else if (object instanceof Reader) {
            long l2 = this.streamLengths[b];
            long l3 = (l2 > 0L) ? l2 : 0L;
            clobDataID = this.session.createClob(l3);
            long l1 = clobDataID.getId();
            Reader reader = (Reader)object;
            ResultLob resultLob = ResultLob.newLobCreateClobRequest(this.session.getId(), l1, reader, l2);
            this.session.allocateResultLob(resultLob, null);
            this.resultOut.addLobResult(resultLob);
          } else if (object instanceof ClobDataID) {
            clobDataID = (ClobDataID)object;
          } 
          this.parameterValues[b] = clobDataID;
        }  
    } 
  }
  
  void fetchResult() throws SQLException {
    if (this.isClosed || this.connection.isClosed)
      checkClosed(); 
    closeResultData();
    checkParametersSet();
    if (this.isBatch)
      throw JDBCUtil.sqlExceptionSQL(1255); 
    if (this.isResult) {
      this.resultOut.setPreparedResultUpdateProperties(this.parameterValues);
    } else {
      this.resultOut.setPreparedExecuteProperties(this.parameterValues, this.maxRows, this.fetchSize, this.rsProperties, this.queryTimeout);
    } 
    try {
      performPreExecute();
      this.resultIn = this.session.execute(this.resultOut);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } finally {
      performPostExecute();
    } 
    if (this.resultIn.mode == 2)
      throw JDBCUtil.sqlException(this.resultIn); 
    if (this.resultIn.isData()) {
      this.currentResultSet = new JDBCResultSet(this.connection, this, this.resultIn, this.resultIn.metaData);
    } else if (this.statementRetType == 2) {
      getMoreResults();
    } 
  }
  
  boolean isAnyParameterSet() {
    for (byte b = 0; b < this.parameterValues.length; b++) {
      if (this.parameterSet[b] != null)
        return true; 
    } 
    return false;
  }
  
  void performPostExecute() throws SQLException {
    super.performPostExecute();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCPreparedStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */