package org.hsqldb;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultLob;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.TimestampData;

public interface SessionInterface {
  public static final int INFO_ID = 0;
  
  public static final int INFO_INTEGER = 1;
  
  public static final int INFO_BOOLEAN = 2;
  
  public static final int INFO_VARCHAR = 3;
  
  public static final int INFO_LIMIT = 4;
  
  public static final int INFO_ISOLATION = 0;
  
  public static final int INFO_AUTOCOMMIT = 1;
  
  public static final int INFO_CONNECTION_READONLY = 2;
  
  public static final int INFO_CATALOG = 3;
  
  public static final int TX_READ_UNCOMMITTED = 1;
  
  public static final int TX_READ_COMMITTED = 2;
  
  public static final int TX_REPEATABLE_READ = 4;
  
  public static final int TX_SERIALIZABLE = 8;
  
  public static final int lobStreamBlockSize = 524288;
  
  Result execute(Result paramResult);
  
  RowSetNavigatorClient getRows(long paramLong, int paramInt1, int paramInt2);
  
  void closeNavigator(long paramLong);
  
  void close();
  
  boolean isClosed();
  
  boolean isReadOnlyDefault();
  
  void setReadOnlyDefault(boolean paramBoolean);
  
  boolean isAutoCommit();
  
  void setAutoCommit(boolean paramBoolean);
  
  int getIsolation();
  
  void setIsolationDefault(int paramInt);
  
  void startPhasedTransaction();
  
  void prepareCommit();
  
  void commit(boolean paramBoolean);
  
  void rollback(boolean paramBoolean);
  
  void rollbackToSavepoint(String paramString);
  
  void savepoint(String paramString);
  
  void releaseSavepoint(String paramString);
  
  void addWarning(HsqlException paramHsqlException);
  
  Object getAttribute(int paramInt);
  
  void setAttribute(int paramInt, Object paramObject);
  
  long getId();
  
  void resetSession();
  
  String getInternalConnectionURL();
  
  BlobDataID createBlob(long paramLong);
  
  ClobDataID createClob(long paramLong);
  
  void allocateResultLob(ResultLob paramResultLob, InputStream paramInputStream);
  
  Scanner getScanner();
  
  Calendar getCalendar();
  
  Calendar getCalendarGMT();
  
  SimpleDateFormat getSimpleDateFormatGMT();
  
  TimestampData getCurrentDate();
  
  int getZoneSeconds();
  
  int getStreamBlockSize();
  
  HsqlProperties getClientProperties();
  
  JDBCConnection getJDBCConnection();
  
  void setJDBCConnection(JDBCConnection paramJDBCConnection);
  
  String getDatabaseUniqueName();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\SessionInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */