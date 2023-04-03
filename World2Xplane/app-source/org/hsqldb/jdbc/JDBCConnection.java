package org.hsqldb.jdbc;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import org.hsqldb.ClientConnection;
import org.hsqldb.ClientConnectionHTTP;
import org.hsqldb.DatabaseManager;
import org.hsqldb.DatabaseURL;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.result.ResultProperties;
import org.hsqldb.types.Type;

public class JDBCConnection implements Connection {
  int rsHoldability = 1;
  
  HsqlProperties connProperties;
  
  HsqlProperties clientProperties;
  
  SessionInterface sessionProxy;
  
  boolean isInternal;
  
  protected boolean isNetConn;
  
  boolean isClosed;
  
  private SQLWarning rootWarning;
  
  private final Object rootWarning_mutex = new Object();
  
  private int savepointIDSequence;
  
  int incarnation;
  
  boolean isPooled;
  
  JDBCConnectionEventListener poolEventListener;
  
  boolean isCloseResultSet;
  
  boolean isUseColumnName = true;
  
  public synchronized Statement createStatement() throws SQLException {
    checkClosed();
    int i = ResultProperties.getValueForJDBC(1003, 1007, this.rsHoldability);
    return new JDBCStatement(this, i);
  }
  
  public synchronized PreparedStatement prepareStatement(String paramString) throws SQLException {
    checkClosed();
    try {
      return new JDBCPreparedStatement(this, paramString, 1003, 1007, this.rsHoldability, 2, null, null);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized CallableStatement prepareCall(String paramString) throws SQLException {
    checkClosed();
    try {
      return new JDBCCallableStatement(this, paramString, 1003, 1007, this.rsHoldability);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized String nativeSQL(String paramString) throws SQLException {
    checkClosed();
    if (paramString == null || paramString.length() == 0 || paramString.indexOf('{') == -1)
      return paramString; 
    boolean bool = false;
    byte b1 = 0;
    int i = paramString.length();
    byte b2 = 0;
    StringBuffer stringBuffer = null;
    int j = 0;
    for (int k = 0; k < i; k++) {
      char c = paramString.charAt(k);
      switch (b1) {
        case false:
          if (c == '\'') {
            b1 = 1;
            break;
          } 
          if (c == '"') {
            b1 = 2;
            break;
          } 
          if (c == '{') {
            if (stringBuffer == null)
              stringBuffer = new StringBuffer(paramString.length()); 
            stringBuffer.append(paramString.substring(j, k));
            k = onStartEscapeSequence(paramString, stringBuffer, k);
            j = k;
            bool = true;
            b2++;
            b1 = 3;
          } 
          break;
        case true:
        case true:
          if (c == '\'')
            b1--; 
          break;
        case true:
        case true:
          if (c == '"')
            b1 -= 2; 
          break;
        case true:
          if (c == '\'') {
            b1 = 4;
            break;
          } 
          if (c == '"') {
            b1 = 5;
            break;
          } 
          if (c == '}') {
            stringBuffer.append(paramString.substring(j, k));
            stringBuffer.append(' ');
            j = ++k;
            bool = true;
            b1 = (--b2 == 0) ? 0 : 3;
            break;
          } 
          if (c == '{') {
            stringBuffer.append(paramString.substring(j, k));
            k = onStartEscapeSequence(paramString, stringBuffer, k);
            j = k;
            bool = true;
            b2++;
            b1 = 3;
          } 
          break;
      } 
    } 
    if (!bool)
      return paramString; 
    stringBuffer.append(paramString.substring(j, paramString.length()));
    return stringBuffer.toString();
  }
  
  public synchronized void setAutoCommit(boolean paramBoolean) throws SQLException {
    checkClosed();
    try {
      this.sessionProxy.setAutoCommit(paramBoolean);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized boolean getAutoCommit() throws SQLException {
    checkClosed();
    try {
      return this.sessionProxy.isAutoCommit();
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized void commit() throws SQLException {
    checkClosed();
    try {
      this.sessionProxy.commit(false);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized void rollback() throws SQLException {
    checkClosed();
    try {
      this.sessionProxy.rollback(false);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized void close() throws SQLException {
    if (this.isInternal || this.isClosed)
      return; 
    this.isClosed = true;
    this.rootWarning = null;
    this.connProperties = null;
    if (this.isPooled) {
      if (this.poolEventListener != null) {
        this.poolEventListener.connectionClosed();
        this.poolEventListener = null;
      } 
    } else if (this.sessionProxy != null) {
      this.sessionProxy.close();
      this.sessionProxy = null;
    } 
  }
  
  public synchronized boolean isClosed() throws SQLException {
    return this.isClosed;
  }
  
  public synchronized DatabaseMetaData getMetaData() throws SQLException {
    checkClosed();
    return new JDBCDatabaseMetaData(this);
  }
  
  public synchronized void setReadOnly(boolean paramBoolean) throws SQLException {
    checkClosed();
    try {
      this.sessionProxy.setReadOnlyDefault(paramBoolean);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized boolean isReadOnly() throws SQLException {
    checkClosed();
    try {
      return this.sessionProxy.isReadOnlyDefault();
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized void setCatalog(String paramString) throws SQLException {
    checkClosed();
    try {
      this.sessionProxy.setAttribute(3, paramString);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized String getCatalog() throws SQLException {
    checkClosed();
    try {
      return (String)this.sessionProxy.getAttribute(3);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized void setTransactionIsolation(int paramInt) throws SQLException {
    checkClosed();
    switch (paramInt) {
      case 1:
      case 2:
      case 4:
      case 8:
        break;
      default:
        throw JDBCUtil.invalidArgument();
    } 
    try {
      this.sessionProxy.setIsolationDefault(paramInt);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized int getTransactionIsolation() throws SQLException {
    checkClosed();
    try {
      return this.sessionProxy.getIsolation();
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized SQLWarning getWarnings() throws SQLException {
    checkClosed();
    return this.rootWarning;
  }
  
  public synchronized void clearWarnings() throws SQLException {
    checkClosed();
    this.rootWarning = null;
  }
  
  public synchronized Statement createStatement(int paramInt1, int paramInt2) throws SQLException {
    checkClosed();
    int i = ResultProperties.getValueForJDBC(paramInt1, paramInt2, this.rsHoldability);
    return new JDBCStatement(this, i);
  }
  
  public synchronized PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2) throws SQLException {
    checkClosed();
    try {
      return new JDBCPreparedStatement(this, paramString, paramInt1, paramInt2, this.rsHoldability, 2, null, null);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized CallableStatement prepareCall(String paramString, int paramInt1, int paramInt2) throws SQLException {
    checkClosed();
    try {
      return new JDBCCallableStatement(this, paramString, paramInt1, paramInt2, this.rsHoldability);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized Map<String, Class<?>> getTypeMap() throws SQLException {
    checkClosed();
    return new HashMap<String, Class<?>>();
  }
  
  public synchronized void setTypeMap(Map<String, Class<?>> paramMap) throws SQLException {
    checkClosed();
    throw JDBCUtil.notSupported();
  }
  
  public synchronized void setHoldability(int paramInt) throws SQLException {
    checkClosed();
    switch (paramInt) {
      case 1:
      case 2:
        break;
      default:
        throw JDBCUtil.invalidArgument();
    } 
    this.rsHoldability = paramInt;
  }
  
  public synchronized int getHoldability() throws SQLException {
    checkClosed();
    return this.rsHoldability;
  }
  
  public synchronized Savepoint setSavepoint() throws SQLException {
    checkClosed();
    if (getAutoCommit())
      throw JDBCUtil.sqlException(4821); 
    JDBCSavepoint jDBCSavepoint = new JDBCSavepoint(this);
    try {
      this.sessionProxy.savepoint(jDBCSavepoint.name);
    } catch (HsqlException hsqlException) {
      JDBCUtil.throwError(hsqlException);
    } 
    return jDBCSavepoint;
  }
  
  public synchronized Savepoint setSavepoint(String paramString) throws SQLException {
    checkClosed();
    if (getAutoCommit())
      throw JDBCUtil.sqlException(4821); 
    if (paramString == null)
      throw JDBCUtil.nullArgument(); 
    if (paramString.startsWith("SYSTEM_SAVEPOINT_"))
      throw JDBCUtil.invalidArgument(); 
    try {
      this.sessionProxy.savepoint(paramString);
    } catch (HsqlException hsqlException) {
      JDBCUtil.throwError(hsqlException);
    } 
    return new JDBCSavepoint(paramString, this);
  }
  
  public synchronized void rollback(Savepoint paramSavepoint) throws SQLException {
    checkClosed();
    if (paramSavepoint == null)
      throw JDBCUtil.nullArgument(); 
    if (!(paramSavepoint instanceof JDBCSavepoint)) {
      String str = Error.getMessage(4821);
      throw JDBCUtil.invalidArgument(str);
    } 
    JDBCSavepoint jDBCSavepoint = (JDBCSavepoint)paramSavepoint;
    if (jDBCSavepoint.name == null) {
      String str = Error.getMessage(4821);
      throw JDBCUtil.invalidArgument(str);
    } 
    if (this != jDBCSavepoint.connection) {
      String str = Error.getMessage(4821);
      throw JDBCUtil.invalidArgument(str);
    } 
    if (getAutoCommit()) {
      jDBCSavepoint.name = null;
      jDBCSavepoint.connection = null;
      throw JDBCUtil.sqlException(4821);
    } 
    try {
      this.sessionProxy.rollbackToSavepoint(jDBCSavepoint.name);
      jDBCSavepoint.connection = null;
      jDBCSavepoint.name = null;
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized void releaseSavepoint(Savepoint paramSavepoint) throws SQLException {
    checkClosed();
    if (paramSavepoint == null)
      throw JDBCUtil.nullArgument(); 
    if (!(paramSavepoint instanceof JDBCSavepoint)) {
      String str = Error.getMessage(4821);
      throw JDBCUtil.invalidArgument(str);
    } 
    JDBCSavepoint jDBCSavepoint = (JDBCSavepoint)paramSavepoint;
    if (jDBCSavepoint.name == null) {
      String str = Error.getMessage(4821);
      throw JDBCUtil.invalidArgument(str);
    } 
    if (this != jDBCSavepoint.connection) {
      String str = Error.getMessage(4821);
      throw JDBCUtil.invalidArgument(str);
    } 
    if (getAutoCommit()) {
      jDBCSavepoint.name = null;
      jDBCSavepoint.connection = null;
      throw JDBCUtil.sqlException(4821);
    } 
    try {
      this.sessionProxy.releaseSavepoint(jDBCSavepoint.name);
      jDBCSavepoint.connection = null;
      jDBCSavepoint.name = null;
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized Statement createStatement(int paramInt1, int paramInt2, int paramInt3) throws SQLException {
    checkClosed();
    int i = ResultProperties.getValueForJDBC(paramInt1, paramInt2, paramInt3);
    return new JDBCStatement(this, i);
  }
  
  public synchronized PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2, int paramInt3) throws SQLException {
    checkClosed();
    try {
      return new JDBCPreparedStatement(this, paramString, paramInt1, paramInt2, paramInt3, 2, null, null);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized CallableStatement prepareCall(String paramString, int paramInt1, int paramInt2, int paramInt3) throws SQLException {
    checkClosed();
    try {
      return new JDBCCallableStatement(this, paramString, paramInt1, paramInt2, paramInt3);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized PreparedStatement prepareStatement(String paramString, int paramInt) throws SQLException {
    checkClosed();
    try {
      if (paramInt != 1 && paramInt != 2)
        throw JDBCUtil.invalidArgument("autoGeneratedKeys"); 
      return new JDBCPreparedStatement(this, paramString, 1003, 1007, this.rsHoldability, paramInt, null, null);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized PreparedStatement prepareStatement(String paramString, int[] paramArrayOfint) throws SQLException {
    checkClosed();
    try {
      return new JDBCPreparedStatement(this, paramString, 1003, 1007, this.rsHoldability, 21, paramArrayOfint, null);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public synchronized PreparedStatement prepareStatement(String paramString, String[] paramArrayOfString) throws SQLException {
    checkClosed();
    try {
      return new JDBCPreparedStatement(this, paramString, 1003, 1007, this.rsHoldability, 11, null, paramArrayOfString);
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public Clob createClob() throws SQLException {
    checkClosed();
    return new JDBCClob();
  }
  
  public Blob createBlob() throws SQLException {
    checkClosed();
    return new JDBCBlob();
  }
  
  public NClob createNClob() throws SQLException {
    checkClosed();
    return new JDBCNClob();
  }
  
  public SQLXML createSQLXML() throws SQLException {
    checkClosed();
    return new JDBCSQLXML();
  }
  
  public boolean isValid(int paramInt) throws SQLException {
    if (paramInt < 0)
      throw JDBCUtil.outOfRangeArgument("timeout: " + paramInt); 
    if (this.isInternal)
      return true; 
    if (!this.isNetConn)
      return !isClosed(); 
    if (isClosed())
      return false; 
    final boolean[] flag = { true };
    Thread thread = new Thread() {
        public void run() {
          try {
            JDBCConnection.this.getMetaData().getDatabaseMajorVersion();
          } catch (Throwable throwable) {
            flag[0] = false;
          } 
        }
      };
    if (paramInt > 60)
      paramInt = 60; 
    paramInt *= 1000;
    try {
      thread.start();
      long l = System.currentTimeMillis();
      thread.join(paramInt);
      try {
        thread.setContextClassLoader(null);
      } catch (Throwable throwable) {}
      return (paramInt == 0) ? arrayOfBoolean[0] : ((arrayOfBoolean[0] && System.currentTimeMillis() - l < paramInt));
    } catch (Throwable throwable) {
      return false;
    } 
  }
  
  public void setClientInfo(String paramString1, String paramString2) throws SQLClientInfoException {
    SQLClientInfoException sQLClientInfoException = new SQLClientInfoException();
    sQLClientInfoException.initCause(JDBCUtil.notSupported());
    throw sQLClientInfoException;
  }
  
  public void setClientInfo(Properties paramProperties) throws SQLClientInfoException {
    if (!this.isClosed && (paramProperties == null || paramProperties.isEmpty()))
      return; 
    SQLClientInfoException sQLClientInfoException = new SQLClientInfoException();
    if (this.isClosed) {
      sQLClientInfoException.initCause(JDBCUtil.connectionClosedException());
    } else {
      sQLClientInfoException.initCause(JDBCUtil.notSupported());
    } 
    throw sQLClientInfoException;
  }
  
  public String getClientInfo(String paramString) throws SQLException {
    checkClosed();
    return null;
  }
  
  public Properties getClientInfo() throws SQLException {
    checkClosed();
    return null;
  }
  
  public Array createArrayOf(String paramString, Object[] paramArrayOfObject) throws SQLException {
    checkClosed();
    if (paramString == null)
      throw JDBCUtil.nullArgument(); 
    paramString = paramString.toUpperCase();
    int i = Type.getTypeNr(paramString);
    if (i < 0)
      throw JDBCUtil.invalidArgument(paramString); 
    Type type = Type.getDefaultType(i);
    if (type.isArrayType() || type.isLobType() || type.isRowType())
      throw JDBCUtil.invalidArgument(paramString); 
    Object[] arrayOfObject = new Object[paramArrayOfObject.length];
    try {
      for (byte b = 0; b < paramArrayOfObject.length; b++) {
        Object object = type.convertJavaToSQL(this.sessionProxy, paramArrayOfObject[b]);
        arrayOfObject[b] = type.convertToTypeLimits(this.sessionProxy, object);
      } 
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
    return new JDBCArray(arrayOfObject, type, this);
  }
  
  public Struct createStruct(String paramString, Object[] paramArrayOfObject) throws SQLException {
    checkClosed();
    throw JDBCUtil.notSupported();
  }
  
  public <T> T unwrap(Class<T> paramClass) throws SQLException {
    checkClosed();
    if (isWrapperFor(paramClass))
      return (T)this; 
    throw JDBCUtil.invalidArgument("iface: " + paramClass);
  }
  
  public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
    checkClosed();
    return (paramClass != null && paramClass.isAssignableFrom(getClass()));
  }
  
  public void setSchema(String paramString) throws SQLException {
    checkClosed();
    if (paramString == null) {
      JDBCUtil.nullArgument("schema");
    } else if (paramString.length() == 0) {
      JDBCUtil.invalidArgument("Zero-length schema");
    } else {
      (new JDBCDatabaseMetaData(this)).setConnectionDefaultSchema(paramString);
    } 
  }
  
  public String getSchema() throws SQLException {
    checkClosed();
    return (new JDBCDatabaseMetaData(this)).getConnectionDefaultSchema();
  }
  
  public void abort(Executor paramExecutor) throws SQLException {
    if (paramExecutor == null)
      throw JDBCUtil.nullArgument("executor"); 
    close();
  }
  
  public void setNetworkTimeout(Executor paramExecutor, int paramInt) throws SQLException {
    checkClosed();
    throw JDBCUtil.notSupported();
  }
  
  public int getNetworkTimeout() throws SQLException {
    return 0;
  }
  
  public JDBCConnection(HsqlProperties paramHsqlProperties) throws SQLException {
    String str1 = paramHsqlProperties.getProperty("user");
    String str2 = paramHsqlProperties.getProperty("password");
    String str3 = paramHsqlProperties.getProperty("connection_type");
    String str4 = paramHsqlProperties.getProperty("host");
    int i = paramHsqlProperties.getIntegerProperty("port", 0);
    String str5 = paramHsqlProperties.getProperty("path");
    String str6 = paramHsqlProperties.getProperty("database");
    boolean bool1 = (str3 == "hsqls://" || str3 == "https://");
    boolean bool2 = paramHsqlProperties.isPropertyTrue("tls_wrapper", false);
    bool2 &= bool1;
    if (str1 == null)
      str1 = "SA"; 
    if (str2 == null)
      str2 = ""; 
    Calendar calendar = Calendar.getInstance();
    int j = HsqlDateTime.getZoneSeconds(calendar);
    try {
      if (DatabaseURL.isInProcessDatabaseType(str3)) {
        this.sessionProxy = (SessionInterface)DatabaseManager.newSession(str3, str6, str1, str2, paramHsqlProperties, null, j);
      } else if (str3 == "hsql://" || str3 == "hsqls://") {
        this.sessionProxy = (SessionInterface)new ClientConnection(str4, i, str5, str6, bool1, bool2, str1, str2, j);
        this.isNetConn = true;
      } else if (str3 == "http://" || str3 == "https://") {
        this.sessionProxy = (SessionInterface)new ClientConnectionHTTP(str4, i, str5, str6, bool1, bool2, str1, str2, j);
        this.isNetConn = true;
      } else {
        throw JDBCUtil.invalidArgument(str3);
      } 
      this.sessionProxy.setJDBCConnection(this);
      this.connProperties = paramHsqlProperties;
      this.clientProperties = this.sessionProxy.getClientProperties();
      if (this.connProperties != null) {
        this.isCloseResultSet = this.connProperties.isPropertyTrue("close_result", false);
        this.isUseColumnName = this.connProperties.isPropertyTrue("get_column_name", true);
      } 
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(hsqlException);
    } 
  }
  
  public JDBCConnection(SessionInterface paramSessionInterface) {
    this.isInternal = true;
    this.sessionProxy = paramSessionInterface;
  }
  
  public JDBCConnection(JDBCConnection paramJDBCConnection, JDBCConnectionEventListener paramJDBCConnectionEventListener) {
    this.sessionProxy = paramJDBCConnection.sessionProxy;
    this.connProperties = paramJDBCConnection.connProperties;
    this.clientProperties = paramJDBCConnection.clientProperties;
    this.isPooled = true;
    this.poolEventListener = paramJDBCConnectionEventListener;
    if (this.connProperties != null) {
      this.isCloseResultSet = this.connProperties.isPropertyTrue("close_result", false);
      this.isUseColumnName = this.connProperties.isPropertyTrue("get_column_name", true);
    } 
  }
  
  protected void finalize() {
    try {
      close();
    } catch (SQLException sQLException) {}
  }
  
  synchronized int getSavepointID() {
    return this.savepointIDSequence++;
  }
  
  synchronized String getURL() throws SQLException {
    checkClosed();
    return this.isInternal ? this.sessionProxy.getInternalConnectionURL() : this.connProperties.getProperty("url");
  }
  
  synchronized void checkClosed() throws SQLException {
    if (this.isClosed)
      throw JDBCUtil.connectionClosedException(); 
  }
  
  void addWarning(SQLWarning paramSQLWarning) {
    synchronized (this.rootWarning_mutex) {
      if (this.rootWarning == null) {
        this.rootWarning = paramSQLWarning;
      } else {
        this.rootWarning.setNextWarning(paramSQLWarning);
      } 
    } 
  }
  
  void setWarnings(SQLWarning paramSQLWarning) {
    synchronized (this.rootWarning_mutex) {
      this.rootWarning = paramSQLWarning;
    } 
  }
  
  public void reset() throws SQLException {
    try {
      this.incarnation++;
      this.sessionProxy.resetSession();
    } catch (HsqlException hsqlException) {
      throw JDBCUtil.sqlException(1305, hsqlException.getMessage(), hsqlException);
    } 
  }
  
  public void closeFully() {
    try {
      close();
    } catch (Throwable throwable) {}
    try {
      if (this.sessionProxy != null) {
        this.sessionProxy.close();
        this.sessionProxy = null;
      } 
    } catch (Throwable throwable) {}
  }
  
  public SessionInterface getSession() {
    return this.sessionProxy;
  }
  
  private int onStartEscapeSequence(String paramString, StringBuffer paramStringBuffer, int paramInt) throws SQLException {
    paramStringBuffer.append(' ');
    paramInt = StringUtil.skipSpaces(paramString, ++paramInt);
    if (paramString.regionMatches(true, paramInt, "fn ", 0, 3) || paramString.regionMatches(true, paramInt, "oj ", 0, 3)) {
      paramInt += 2;
    } else if (paramString.regionMatches(true, paramInt, "ts ", 0, 3)) {
      paramStringBuffer.append("TIMESTAMP");
      paramInt += 2;
    } else if (paramString.regionMatches(true, paramInt, "d ", 0, 2)) {
      paramStringBuffer.append("DATE");
      paramInt++;
    } else if (paramString.regionMatches(true, paramInt, "t ", 0, 2)) {
      paramStringBuffer.append("TIME");
      paramInt++;
    } else if (paramString.regionMatches(true, paramInt, "call ", 0, 5)) {
      paramStringBuffer.append("CALL");
      paramInt += 4;
    } else if (paramString.regionMatches(true, paramInt, "?= call ", 0, 8)) {
      paramStringBuffer.append("CALL");
      paramInt += 7;
    } else if (paramString.regionMatches(true, paramInt, "? = call ", 0, 8)) {
      paramStringBuffer.append("CALL");
      paramInt += 8;
    } else if (paramString.regionMatches(true, paramInt, "escape ", 0, 7)) {
      paramInt += 6;
    } else {
      throw JDBCUtil.sqlException(Error.error(425, paramString.substring(--paramInt)));
    } 
    return paramInt;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */