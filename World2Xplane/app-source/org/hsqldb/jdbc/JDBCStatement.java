package org.hsqldb.jdbc;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Wrapper;
import org.hsqldb.HsqlException;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultProperties;

public class JDBCStatement extends JDBCStatementBase implements Statement, Wrapper {
  boolean poolable = false;
  
  public synchronized ResultSet executeQuery(String paramString) throws SQLException {
    fetchResult(paramString, 2, 2, (int[])null, (String[])null);
    return getResultSet();
  }
  
  public synchronized int executeUpdate(String paramString) throws SQLException {
    fetchResult(paramString, 1, 2, (int[])null, (String[])null);
    return this.resultIn.getUpdateCount();
  }
  
  public synchronized void close() throws SQLException {
    if (this.isClosed)
      return; 
    closeResultData();
    this.batchResultOut = null;
    this.connection = null;
    this.resultIn = null;
    this.resultOut = null;
    this.isClosed = true;
  }
  
  public synchronized int getMaxFieldSize() throws SQLException {
    checkClosed();
    return 0;
  }
  
  public void setMaxFieldSize(int paramInt) throws SQLException {
    checkClosed();
    if (paramInt < 0)
      throw JDBCUtil.outOfRangeArgument(); 
  }
  
  public synchronized int getMaxRows() throws SQLException {
    checkClosed();
    return this.maxRows;
  }
  
  public synchronized void setMaxRows(int paramInt) throws SQLException {
    checkClosed();
    if (paramInt < 0)
      throw JDBCUtil.outOfRangeArgument(); 
    this.maxRows = paramInt;
  }
  
  public void setEscapeProcessing(boolean paramBoolean) throws SQLException {
    checkClosed();
    this.isEscapeProcessing = paramBoolean;
  }
  
  public synchronized int getQueryTimeout() throws SQLException {
    checkClosed();
    return this.queryTimeout;
  }
  
  public void setQueryTimeout(int paramInt) throws SQLException {
    checkClosed();
    if (paramInt < 0)
      throw JDBCUtil.outOfRangeArgument(); 
    if (paramInt > 32767)
      paramInt = 32767; 
    this.queryTimeout = paramInt;
  }
  
  public synchronized void cancel() throws SQLException {
    checkClosed();
  }
  
  public synchronized SQLWarning getWarnings() throws SQLException {
    checkClosed();
    return this.rootWarning;
  }
  
  public synchronized void clearWarnings() throws SQLException {
    checkClosed();
    this.rootWarning = null;
  }
  
  public void setCursorName(String paramString) throws SQLException {
    checkClosed();
  }
  
  public synchronized boolean execute(String paramString) throws SQLException {
    fetchResult(paramString, 0, 2, (int[])null, (String[])null);
    return (this.currentResultSet != null);
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
    checkClosed();
    checkClosed();
    switch (paramInt) {
      case 1000:
      case 1001:
      case 1002:
        this.fetchDirection = paramInt;
        return;
    } 
    throw JDBCUtil.invalidArgument();
  }
  
  public int getFetchDirection() throws SQLException {
    checkClosed();
    return this.fetchDirection;
  }
  
  public synchronized void setFetchSize(int paramInt) throws SQLException {
    checkClosed();
    if (paramInt < 0)
      throw JDBCUtil.outOfRangeArgument(); 
    this.fetchSize = paramInt;
  }
  
  public synchronized int getFetchSize() throws SQLException {
    checkClosed();
    return this.fetchSize;
  }
  
  public synchronized int getResultSetConcurrency() throws SQLException {
    checkClosed();
    return ResultProperties.getJDBCConcurrency(this.rsProperties);
  }
  
  public synchronized int getResultSetType() throws SQLException {
    checkClosed();
    return ResultProperties.getJDBCScrollability(this.rsProperties);
  }
  
  public synchronized void addBatch(String paramString) throws SQLException {
    checkClosed();
    if (this.isEscapeProcessing)
      paramString = this.connection.nativeSQL(paramString); 
    if (this.batchResultOut == null)
      this.batchResultOut = Result.newBatchedExecuteRequest(); 
    this.batchResultOut.getNavigator().add(new Object[] { paramString });
  }
  
  public synchronized void clearBatch() throws SQLException {
    checkClosed();
    if (this.batchResultOut != null)
      this.batchResultOut.getNavigator().clear(); 
  }
  
  public synchronized int[] executeBatch() throws SQLException {
    checkClosed();
    this.generatedResult = null;
    if (this.batchResultOut == null)
      this.batchResultOut = Result.newBatchedExecuteRequest(); 
    int i = this.batchResultOut.getNavigator().getSize();
    try {
      this.resultIn = this.connection.sessionProxy.execute(this.batchResultOut);
      performPostExecute();
    } catch (HsqlException hsqlException) {
      this.batchResultOut.getNavigator().clear();
      throw JDBCUtil.sqlException(hsqlException);
    } 
    this.batchResultOut.getNavigator().clear();
    if (this.resultIn.isError())
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
  
  public synchronized Connection getConnection() throws SQLException {
    checkClosed();
    return this.connection;
  }
  
  public synchronized boolean getMoreResults(int paramInt) throws SQLException {
    return super.getMoreResults(paramInt);
  }
  
  public synchronized ResultSet getGeneratedKeys() throws SQLException {
    return getGeneratedResultSet();
  }
  
  public synchronized int executeUpdate(String paramString, int paramInt) throws SQLException {
    if (paramInt != 1 && paramInt != 2)
      throw JDBCUtil.invalidArgument("autoGeneratedKeys"); 
    fetchResult(paramString, 1, paramInt, (int[])null, (String[])null);
    if (this.resultIn.isError())
      throw JDBCUtil.sqlException(this.resultIn); 
    return this.resultIn.getUpdateCount();
  }
  
  public synchronized int executeUpdate(String paramString, int[] paramArrayOfint) throws SQLException {
    if (paramArrayOfint == null || paramArrayOfint.length == 0)
      throw JDBCUtil.invalidArgument("columnIndexes"); 
    fetchResult(paramString, 1, 21, paramArrayOfint, (String[])null);
    return this.resultIn.getUpdateCount();
  }
  
  public synchronized int executeUpdate(String paramString, String[] paramArrayOfString) throws SQLException {
    if (paramArrayOfString == null || paramArrayOfString.length == 0)
      throw JDBCUtil.invalidArgument("columnIndexes"); 
    fetchResult(paramString, 1, 11, (int[])null, paramArrayOfString);
    return this.resultIn.getUpdateCount();
  }
  
  public synchronized boolean execute(String paramString, int paramInt) throws SQLException {
    if (paramInt != 1 && paramInt != 2)
      throw JDBCUtil.invalidArgument("autoGeneratedKeys"); 
    fetchResult(paramString, 0, paramInt, (int[])null, (String[])null);
    return this.resultIn.isData();
  }
  
  public synchronized boolean execute(String paramString, int[] paramArrayOfint) throws SQLException {
    if (paramArrayOfint == null || paramArrayOfint.length == 0)
      throw JDBCUtil.invalidArgument("columnIndexes"); 
    fetchResult(paramString, 0, 21, paramArrayOfint, (String[])null);
    return this.resultIn.isData();
  }
  
  public synchronized boolean execute(String paramString, String[] paramArrayOfString) throws SQLException {
    if (paramArrayOfString == null || paramArrayOfString.length == 0)
      throw JDBCUtil.invalidArgument("columnIndexes"); 
    fetchResult(paramString, 0, 11, (int[])null, paramArrayOfString);
    return this.resultIn.isData();
  }
  
  public synchronized int getResultSetHoldability() throws SQLException {
    return ResultProperties.getJDBCHoldability(this.rsProperties);
  }
  
  public synchronized boolean isClosed() throws SQLException {
    return this.isClosed;
  }
  
  public synchronized void setPoolable(boolean paramBoolean) throws SQLException {
    checkClosed();
    this.poolable = paramBoolean;
  }
  
  public synchronized boolean isPoolable() throws SQLException {
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
  
  JDBCStatement(JDBCConnection paramJDBCConnection, int paramInt) {
    this.connection = paramJDBCConnection;
    this.connectionIncarnation = this.connection.incarnation;
    this.rsProperties = paramInt;
  }
  
  private void fetchResult(String paramString, int paramInt1, int paramInt2, int[] paramArrayOfint, String[] paramArrayOfString) throws SQLException {
    checkClosed();
    closeResultData();
    if (this.isEscapeProcessing)
      paramString = this.connection.nativeSQL(paramString); 
    this.resultOut.setPrepareOrExecuteProperties(paramString, this.maxRows, this.fetchSize, paramInt1, this.queryTimeout, this.rsProperties, paramInt2, paramArrayOfint, paramArrayOfString);
    try {
      this.resultIn = this.connection.sessionProxy.execute(this.resultOut);
      performPostExecute();
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
    if (this.resultIn.isError())
      throw JDBCUtil.sqlException(this.resultIn); 
    if (this.resultIn.isData()) {
      this.currentResultSet = new JDBCResultSet(this.connection, this, this.resultIn, this.resultIn.metaData);
    } else if (this.resultIn.getStatementType() == 2) {
      getMoreResults();
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */