package org.hsqldb;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.map.ValuePool;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultLob;
import org.hsqldb.rowio.RowInputBinary;
import org.hsqldb.rowio.RowOutputBinary;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.server.HsqlSocketFactory;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.TimestampData;

public class ClientConnection implements SessionInterface {
  public static final String NETWORK_COMPATIBILITY_VERSION = "2.1.0.0";
  
  public static final int NETWORK_COMPATIBILITY_VERSION_INT = -2010000;
  
  static final int BUFFER_SIZE = 4096;
  
  final byte[] mainBuffer = new byte[4096];
  
  private boolean isClosed;
  
  private Socket socket;
  
  protected DataOutputStream dataOutput;
  
  protected DataInputStream dataInput;
  
  protected RowOutputInterface rowOut;
  
  protected RowInputBinary rowIn;
  
  private Result resultOut;
  
  private long sessionID;
  
  private long lobIDSequence = -1L;
  
  private boolean isReadOnlyDefault = false;
  
  private boolean isAutoCommit = true;
  
  private int zoneSeconds;
  
  private Scanner scanner;
  
  private String zoneString;
  
  private Calendar calendar;
  
  private Calendar calendarGMT;
  
  SimpleDateFormat simpleDateFormatGMT;
  
  JDBCConnection connection;
  
  String host;
  
  int port;
  
  String path;
  
  String database;
  
  boolean isTLS;
  
  boolean isTLSWrapper;
  
  int databaseID;
  
  String clientPropertiesString;
  
  HsqlProperties clientProperties;
  
  String databaseUniqueName;
  
  public ClientConnection(String paramString1, int paramInt1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, String paramString4, String paramString5, int paramInt2) {
    this.host = paramString1;
    this.port = paramInt1;
    this.path = paramString2;
    this.database = paramString3;
    this.isTLS = paramBoolean1;
    this.isTLSWrapper = paramBoolean2;
    this.zoneSeconds = paramInt2;
    this.zoneString = TimeZone.getDefault().getID();
    initStructures();
    Result result1 = Result.newConnectionAttemptRequest(paramString4, paramString5, paramString3, this.zoneString, paramInt2);
    initConnection(paramString1, paramInt1, paramBoolean1);
    Result result2 = execute(result1);
    if (result2.isError())
      throw Error.error(result2); 
    this.sessionID = result2.getSessionId();
    this.databaseID = result2.getDatabaseId();
    this.databaseUniqueName = result2.getDatabaseName();
    this.clientPropertiesString = result2.getMainString();
  }
  
  private void initStructures() {
    RowOutputBinary rowOutputBinary = new RowOutputBinary(this.mainBuffer);
    this.rowOut = (RowOutputInterface)rowOutputBinary;
    this.rowIn = new RowInputBinary(rowOutputBinary);
    this.resultOut = Result.newSessionAttributesResult();
  }
  
  protected void initConnection(String paramString, int paramInt, boolean paramBoolean) {
    openConnection(paramString, paramInt, paramBoolean);
  }
  
  protected void openConnection(String paramString, int paramInt, boolean paramBoolean) {
    try {
      if (this.isTLSWrapper)
        this.socket = HsqlSocketFactory.getInstance(false).createSocket(paramString, paramInt); 
      this.socket = HsqlSocketFactory.getInstance(paramBoolean).createSocket(this.socket, paramString, paramInt);
      this.socket.setTcpNoDelay(true);
      this.dataOutput = new DataOutputStream(this.socket.getOutputStream());
      this.dataInput = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
      handshake();
    } catch (Exception exception) {
      throw new HsqlException(exception, Error.getStateString(1301), -1301);
    } 
  }
  
  protected void closeConnection() {
    try {
      if (this.socket != null)
        this.socket.close(); 
    } catch (Exception exception) {}
    this.socket = null;
  }
  
  public synchronized Result execute(Result paramResult) {
    if (this.isClosed)
      return Result.newErrorResult(Error.error(1353)); 
    try {
      paramResult.setSessionId(this.sessionID);
      paramResult.setDatabaseId(this.databaseID);
      write(paramResult);
      return read();
    } catch (Throwable throwable) {
      throw Error.error(1305, throwable.toString());
    } 
  }
  
  public synchronized RowSetNavigatorClient getRows(long paramLong, int paramInt1, int paramInt2) {
    try {
      this.resultOut.setResultType(13);
      this.resultOut.setResultId(paramLong);
      this.resultOut.setUpdateCount(paramInt1);
      this.resultOut.setFetchSize(paramInt2);
      Result result = execute(this.resultOut);
      return (RowSetNavigatorClient)result.getNavigator();
    } catch (Throwable throwable) {
      throw Error.error(1305, throwable.toString());
    } 
  }
  
  public synchronized void closeNavigator(long paramLong) {
    try {
      this.resultOut.setResultType(40);
      this.resultOut.setResultId(paramLong);
      execute(this.resultOut);
    } catch (Throwable throwable) {}
  }
  
  public synchronized void close() {
    if (this.isClosed)
      return; 
    try {
      this.resultOut.setResultType(32);
      execute(this.resultOut);
    } catch (Exception exception) {}
    try {
      closeConnection();
    } catch (Exception exception) {}
    this.isClosed = true;
  }
  
  public synchronized Object getAttribute(int paramInt) {
    this.resultOut.setResultType(7);
    this.resultOut.setStatementType(paramInt);
    Result result = execute(this.resultOut);
    if (result.isError())
      throw Error.error(result); 
    Object[] arrayOfObject = result.getSingleRowData();
    switch (paramInt) {
      case 1:
        return arrayOfObject[2];
      case 2:
        return arrayOfObject[2];
      case 0:
        return arrayOfObject[1];
      case 3:
        return arrayOfObject[3];
    } 
    return null;
  }
  
  public synchronized void setAttribute(int paramInt, Object paramObject) {
    this.resultOut.setResultType(6);
    Object[] arrayOfObject = this.resultOut.getSingleRowData();
    arrayOfObject[0] = ValuePool.getInt(paramInt);
    switch (paramInt) {
      case 1:
      case 2:
        arrayOfObject[2] = paramObject;
        break;
      case 0:
        arrayOfObject[1] = paramObject;
        break;
      case 3:
        arrayOfObject[3] = paramObject;
        break;
    } 
    Result result = execute(this.resultOut);
    if (result.isError())
      throw Error.error(result); 
  }
  
  public synchronized boolean isReadOnlyDefault() {
    Object object = getAttribute(2);
    this.isReadOnlyDefault = ((Boolean)object).booleanValue();
    return this.isReadOnlyDefault;
  }
  
  public synchronized void setReadOnlyDefault(boolean paramBoolean) {
    if (paramBoolean != this.isReadOnlyDefault) {
      setAttribute(2, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
      this.isReadOnlyDefault = paramBoolean;
    } 
  }
  
  public synchronized boolean isAutoCommit() {
    Object object = getAttribute(1);
    this.isAutoCommit = ((Boolean)object).booleanValue();
    return this.isAutoCommit;
  }
  
  public synchronized void setAutoCommit(boolean paramBoolean) {
    if (paramBoolean != this.isAutoCommit) {
      setAttribute(1, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
      this.isAutoCommit = paramBoolean;
    } 
  }
  
  public synchronized void setIsolationDefault(int paramInt) {
    setAttribute(0, ValuePool.getInt(paramInt));
  }
  
  public synchronized int getIsolation() {
    Object object = getAttribute(0);
    return ((Integer)object).intValue();
  }
  
  public synchronized boolean isClosed() {
    return this.isClosed;
  }
  
  public Session getSession() {
    return null;
  }
  
  public synchronized void startPhasedTransaction() {}
  
  public synchronized void prepareCommit() {
    this.resultOut.setAsTransactionEndRequest(12, null);
    Result result = execute(this.resultOut);
    if (result.isError())
      throw Error.error(result); 
  }
  
  public synchronized void commit(boolean paramBoolean) {
    this.resultOut.setAsTransactionEndRequest(0, null);
    Result result = execute(this.resultOut);
    if (result.isError())
      throw Error.error(result); 
  }
  
  public synchronized void rollback(boolean paramBoolean) {
    this.resultOut.setAsTransactionEndRequest(1, null);
    Result result = execute(this.resultOut);
    if (result.isError())
      throw Error.error(result); 
  }
  
  public synchronized void rollbackToSavepoint(String paramString) {
    this.resultOut.setAsTransactionEndRequest(2, paramString);
    Result result = execute(this.resultOut);
    if (result.isError())
      throw Error.error(result); 
  }
  
  public synchronized void savepoint(String paramString) {
    Result result1 = Result.newSetSavepointRequest(paramString);
    Result result2 = execute(result1);
    if (result2.isError())
      throw Error.error(result2); 
  }
  
  public synchronized void releaseSavepoint(String paramString) {
    this.resultOut.setAsTransactionEndRequest(4, paramString);
    Result result = execute(this.resultOut);
    if (result.isError())
      throw Error.error(result); 
  }
  
  public void addWarning(HsqlException paramHsqlException) {}
  
  public synchronized long getId() {
    return this.sessionID;
  }
  
  public synchronized void resetSession() {
    Result result1 = Result.newResetSessionRequest();
    Result result2 = execute(result1);
    if (result2.isError()) {
      this.isClosed = true;
      closeConnection();
      throw Error.error(result2);
    } 
    this.sessionID = result2.getSessionId();
    this.databaseID = result2.getDatabaseId();
  }
  
  protected void write(Result paramResult) throws IOException, HsqlException {
    paramResult.write(this, this.dataOutput, this.rowOut);
  }
  
  protected Result read() throws IOException, HsqlException {
    Result result = Result.newResult(this.dataInput, this.rowIn);
    result.readAdditionalResults(this, this.dataInput, this.rowIn);
    this.rowOut.reset(this.mainBuffer);
    this.rowIn.resetRow(this.mainBuffer.length);
    return result;
  }
  
  public synchronized String getInternalConnectionURL() {
    return null;
  }
  
  public synchronized long getLobId() {
    return this.lobIDSequence--;
  }
  
  public BlobDataID createBlob(long paramLong) {
    return new BlobDataID(getLobId());
  }
  
  public ClobDataID createClob(long paramLong) {
    return new ClobDataID(getLobId());
  }
  
  public void allocateResultLob(ResultLob paramResultLob, InputStream paramInputStream) {}
  
  public Scanner getScanner() {
    if (this.scanner == null)
      this.scanner = new Scanner(); 
    return this.scanner;
  }
  
  public Calendar getCalendar() {
    if (this.calendar == null) {
      TimeZone timeZone = TimeZone.getTimeZone(this.zoneString);
      this.calendar = new GregorianCalendar(timeZone);
    } 
    return this.calendar;
  }
  
  public Calendar getCalendarGMT() {
    if (this.calendarGMT == null)
      this.calendarGMT = new GregorianCalendar(TimeZone.getTimeZone("GMT")); 
    return this.calendarGMT;
  }
  
  public SimpleDateFormat getSimpleDateFormatGMT() {
    if (this.simpleDateFormatGMT == null) {
      this.simpleDateFormatGMT = new SimpleDateFormat("MMMM", Locale.ENGLISH);
      GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
      this.simpleDateFormatGMT.setCalendar(gregorianCalendar);
    } 
    return this.simpleDateFormatGMT;
  }
  
  public TimestampData getCurrentDate() {
    long l1 = System.currentTimeMillis();
    long l2 = HsqlDateTime.getCurrentDateMillis(l1) / 1000L;
    return new TimestampData(l2);
  }
  
  public int getZoneSeconds() {
    return this.zoneSeconds;
  }
  
  public int getStreamBlockSize() {
    return 524288;
  }
  
  public HsqlProperties getClientProperties() {
    if (this.clientProperties == null)
      if (this.clientPropertiesString.length() > 0) {
        this.clientProperties = HsqlProperties.delimitedArgPairsToProps(this.clientPropertiesString, "=", ";", null);
      } else {
        this.clientProperties = new HsqlProperties();
      }  
    return this.clientProperties;
  }
  
  public JDBCConnection getJDBCConnection() {
    return this.connection;
  }
  
  public void setJDBCConnection(JDBCConnection paramJDBCConnection) {
    this.connection = paramJDBCConnection;
  }
  
  public String getDatabaseUniqueName() {
    return this.databaseUniqueName;
  }
  
  public static String toNetCompVersionString(int paramInt) {
    StringBuffer stringBuffer = new StringBuffer();
    paramInt *= -1;
    stringBuffer.append(paramInt / 1000000);
    paramInt %= 1000000;
    stringBuffer.append('.');
    stringBuffer.append(paramInt / 10000);
    paramInt %= 10000;
    stringBuffer.append('.');
    stringBuffer.append(paramInt / 100);
    paramInt %= 100;
    stringBuffer.append('.');
    stringBuffer.append(paramInt);
    return stringBuffer.toString();
  }
  
  protected void handshake() throws IOException {
    this.dataOutput.writeInt(-2010000);
    this.dataOutput.flush();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ClientConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */