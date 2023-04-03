package org.hsqldb.jdbc;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.hsqldb.ColumnBase;
import org.hsqldb.SessionInterface;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.Type;

public class JDBCArray implements Array {
  volatile boolean closed;
  
  Type arrayType;
  
  Type elementType;
  
  Object[] data;
  
  JDBCConnection connection;
  
  SessionInterface sessionProxy;
  
  public String getBaseTypeName() throws SQLException {
    checkClosed();
    return this.elementType.getNameString();
  }
  
  public int getBaseType() throws SQLException {
    checkClosed();
    return this.elementType.getJDBCTypeCode();
  }
  
  public Object getArray() throws SQLException {
    checkClosed();
    Object[] arrayOfObject = new Object[this.data.length];
    for (byte b = 0; b < this.data.length; b++)
      arrayOfObject[b] = this.elementType.convertSQLToJava(this.sessionProxy, this.data[b]); 
    return arrayOfObject;
  }
  
  public Object getArray(Map<String, Class<?>> paramMap) throws SQLException {
    return getArray();
  }
  
  public Object getArray(long paramLong, int paramInt) throws SQLException {
    checkClosed();
    if (!JDBCClobClient.isInLimits(this.data.length, paramLong - 1L, paramInt))
      throw JDBCUtil.outOfRangeArgument(); 
    Object[] arrayOfObject = new Object[paramInt];
    for (byte b = 0; b < paramInt; b++)
      arrayOfObject[b] = this.elementType.convertSQLToJava(this.sessionProxy, this.data[(int)paramLong + b - 1]); 
    return arrayOfObject;
  }
  
  public Object getArray(long paramLong, int paramInt, Map<String, Class<?>> paramMap) throws SQLException {
    return getArray(paramLong, paramInt);
  }
  
  public ResultSet getResultSet() throws SQLException {
    checkClosed();
    Result result = newColumnResult(0L, this.data.length);
    return new JDBCResultSet(this.connection, result, result.metaData);
  }
  
  public ResultSet getResultSet(Map<String, Class<?>> paramMap) throws SQLException {
    return getResultSet();
  }
  
  public ResultSet getResultSet(long paramLong, int paramInt) throws SQLException {
    checkClosed();
    Result result = newColumnResult(paramLong - 1L, paramInt);
    return new JDBCResultSet(this.connection, result, result.metaData);
  }
  
  public ResultSet getResultSet(long paramLong, int paramInt, Map<String, Class<?>> paramMap) throws SQLException {
    return getResultSet(paramLong, paramInt);
  }
  
  public String toString() {
    if (this.arrayType == null)
      this.arrayType = (Type)Type.getDefaultArrayType(this.elementType.typeCode); 
    return this.arrayType.convertToString(this.data);
  }
  
  public void free() throws SQLException {
    if (!this.closed) {
      this.closed = true;
      this.connection = null;
      this.sessionProxy = null;
    } 
  }
  
  public JDBCArray(Object[] paramArrayOfObject, Type paramType1, Type paramType2, SessionInterface paramSessionInterface) {
    this(paramArrayOfObject, paramType1, paramType2, paramSessionInterface.getJDBCConnection());
    this.sessionProxy = paramSessionInterface;
  }
  
  JDBCArray(Object[] paramArrayOfObject, Type paramType, JDBCConnection paramJDBCConnection) throws SQLException {
    this(paramArrayOfObject, paramType, (Type)null, paramJDBCConnection);
  }
  
  JDBCArray(Object[] paramArrayOfObject, Type paramType1, Type paramType2, JDBCConnection paramJDBCConnection) {
    this.data = paramArrayOfObject;
    this.elementType = paramType1;
    this.arrayType = paramType2;
    this.connection = paramJDBCConnection;
    if (paramJDBCConnection != null)
      this.sessionProxy = paramJDBCConnection.sessionProxy; 
  }
  
  public Object[] getArrayInternal() {
    return this.data;
  }
  
  private Result newColumnResult(long paramLong, int paramInt) throws SQLException {
    if (!JDBCClobClient.isInLimits(this.data.length, paramLong, paramInt))
      throw JDBCUtil.outOfRangeArgument(); 
    Type[] arrayOfType = new Type[2];
    arrayOfType[0] = (Type)Type.SQL_INTEGER;
    arrayOfType[1] = this.elementType;
    ResultMetaData resultMetaData = ResultMetaData.newSimpleResultMetaData(arrayOfType);
    resultMetaData.columnLabels = new String[] { "C1", "C2" };
    resultMetaData.colIndexes = new int[] { -1, -1 };
    resultMetaData.columns = new ColumnBase[2];
    ColumnBase columnBase = new ColumnBase("", "", "", "");
    columnBase.setType(arrayOfType[0]);
    resultMetaData.columns[0] = columnBase;
    columnBase = new ColumnBase("", "", "", "");
    columnBase.setType(arrayOfType[1]);
    resultMetaData.columns[1] = columnBase;
    RowSetNavigatorClient rowSetNavigatorClient = new RowSetNavigatorClient();
    for (int i = (int)paramLong; i < paramLong + paramInt; i++) {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(i + 1);
      arrayOfObject[1] = this.data[i];
      rowSetNavigatorClient.add(arrayOfObject);
    } 
    Result result = Result.newDataResult(resultMetaData);
    result.setNavigator((RowSetNavigator)rowSetNavigatorClient);
    return result;
  }
  
  private void checkClosed() throws SQLException {
    if (this.closed)
      throw JDBCUtil.sqlException(1251); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */