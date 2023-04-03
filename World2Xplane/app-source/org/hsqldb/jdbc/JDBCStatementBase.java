package org.hsqldb.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import org.hsqldb.result.Result;

class JDBCStatementBase {
  volatile boolean isClosed;
  
  protected boolean isEscapeProcessing = true;
  
  protected JDBCConnection connection;
  
  protected int maxRows;
  
  protected int fetchSize = 0;
  
  protected int fetchDirection = 1000;
  
  protected Result resultIn;
  
  protected Result errorResult;
  
  protected Result generatedResult;
  
  protected int rsProperties;
  
  protected Result resultOut;
  
  protected Result batchResultOut;
  
  protected JDBCResultSet currentResultSet;
  
  protected JDBCResultSet generatedResultSet;
  
  protected SQLWarning rootWarning;
  
  protected int resultSetCounter;
  
  protected int queryTimeout;
  
  int connectionIncarnation;
  
  static final int CLOSE_CURRENT_RESULT = 1;
  
  static final int KEEP_CURRENT_RESULT = 2;
  
  static final int CLOSE_ALL_RESULTS = 3;
  
  static final int SUCCESS_NO_INFO = -2;
  
  static final int EXECUTE_FAILED = -3;
  
  static final int RETURN_GENERATED_KEYS = 1;
  
  static final int NO_GENERATED_KEYS = 2;
  
  public synchronized void close() throws SQLException {}
  
  void checkClosed() throws SQLException {
    if (this.isClosed)
      throw JDBCUtil.sqlException(1251); 
    if (this.connection.isClosed) {
      close();
      throw JDBCUtil.sqlException(1353);
    } 
    if (this.connectionIncarnation != this.connection.incarnation)
      throw JDBCUtil.sqlException(1353); 
  }
  
  void performPostExecute() throws SQLException {
    this.resultOut.clearLobResults();
    this.generatedResult = null;
    if (this.resultIn == null)
      return; 
    this.rootWarning = null;
    Result result = this.resultIn;
    while (result.getChainedResult() != null) {
      result = result.getUnlinkChainedResult();
      if (result.getType() == 19) {
        SQLWarning sQLWarning = JDBCUtil.sqlWarning(result);
        if (this.rootWarning == null) {
          this.rootWarning = sQLWarning;
          continue;
        } 
        this.rootWarning.setNextWarning(sQLWarning);
        continue;
      } 
      if (result.getType() == 2) {
        this.errorResult = result;
        continue;
      } 
      if (result.getType() == 20) {
        this.generatedResult = result;
        continue;
      } 
      if (result.getType() == 3)
        this.resultIn.addChainedResult(result); 
    } 
    if (this.rootWarning != null)
      this.connection.setWarnings(this.rootWarning); 
  }
  
  int getUpdateCount() throws SQLException {
    checkClosed();
    return (this.resultIn == null || this.resultIn.isData()) ? -1 : this.resultIn.getUpdateCount();
  }
  
  ResultSet getResultSet() throws SQLException {
    checkClosed();
    JDBCResultSet jDBCResultSet = this.currentResultSet;
    if (!this.connection.isCloseResultSet)
      this.currentResultSet = null; 
    return (jDBCResultSet == null && this.resultOut.getStatementType() == 2) ? JDBCResultSet.newEptyResultSet() : jDBCResultSet;
  }
  
  boolean getMoreResults() throws SQLException {
    return getMoreResults(1);
  }
  
  boolean getMoreResults(int paramInt) throws SQLException {
    checkClosed();
    if (this.resultIn == null)
      return false; 
    this.resultIn = this.resultIn.getChainedResult();
    if (this.currentResultSet != null && paramInt != 2)
      this.currentResultSet.close(); 
    this.currentResultSet = null;
    if (this.resultIn != null) {
      this.currentResultSet = new JDBCResultSet(this.connection, this, this.resultIn, this.resultIn.metaData);
      return true;
    } 
    return false;
  }
  
  ResultSet getGeneratedResultSet() throws SQLException {
    if (this.generatedResultSet != null)
      this.generatedResultSet.close(); 
    if (this.generatedResult == null)
      this.generatedResult = Result.emptyGeneratedResult; 
    this.generatedResultSet = new JDBCResultSet(this.connection, null, this.generatedResult, this.generatedResult.metaData);
    return this.generatedResultSet;
  }
  
  void closeResultData() throws SQLException {
    if (this.currentResultSet != null)
      this.currentResultSet.close(); 
    if (this.generatedResultSet != null)
      this.generatedResultSet.close(); 
    this.generatedResultSet = null;
    this.generatedResult = null;
    this.resultIn = null;
    this.currentResultSet = null;
  }
  
  public void closeOnCompletion() throws SQLException {
    checkClosed();
  }
  
  public boolean isCloseOnCompletion() throws SQLException {
    checkClosed();
    return false;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCStatementBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */