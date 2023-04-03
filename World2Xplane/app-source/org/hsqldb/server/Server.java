package org.hsqldb.server;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import org.hsqldb.DatabaseManager;
import org.hsqldb.DatabaseURL;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.StopWatch;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.resources.ResourceBundleHandler;
import org.hsqldb.result.Result;

public class Server implements HsqlSocketRequestHandler {
  protected static final int serverBundleHandle = ResourceBundleHandler.getBundleHandle("org_hsqldb_server_Server_messages", null);
  
  ServerProperties serverProperties;
  
  HashSet serverConnSet;
  
  protected String[] dbAlias;
  
  protected String[] dbType;
  
  protected String[] dbPath;
  
  protected HsqlProperties[] dbProps;
  
  protected int[] dbID;
  
  protected long[] dbActionSequence;
  
  HashSet aliasSet = new HashSet();
  
  protected int maxConnections;
  
  volatile long actionSequence;
  
  protected String serverId;
  
  protected int serverProtocol;
  
  protected ThreadGroup serverConnectionThreadGroup;
  
  protected HsqlSocketFactory socketFactory;
  
  protected volatile ServerSocket socket;
  
  private Thread serverThread;
  
  private Throwable serverError;
  
  private volatile int serverState;
  
  private volatile boolean isSilent;
  
  protected volatile boolean isRemoteOpen;
  
  protected boolean isDaemon;
  
  private PrintWriter logWriter;
  
  private PrintWriter errWriter;
  
  private ServerAcl acl = null;
  
  private volatile boolean isShuttingDown;
  
  public Thread getServerThread() {
    return this.serverThread;
  }
  
  public Server() {
    this(1);
  }
  
  protected Server(int paramInt) {
    init(paramInt);
  }
  
  public void checkRunning(boolean paramBoolean) {
    printWithThread("checkRunning(" + paramBoolean + ") entered");
    int i = getState();
    boolean bool = ((paramBoolean && i != 1) || (!paramBoolean && i != 16)) ? true : false;
    if (bool) {
      String str = "server is " + (paramBoolean ? "not " : "") + "running";
      throw Error.error(458, str);
    } 
    printWithThread("checkRunning(" + paramBoolean + ") exited");
  }
  
  public synchronized void signalCloseAllServerConnections() {
    ServerConnection[] arrayOfServerConnection;
    printWithThread("signalCloseAllServerConnections() entered");
    synchronized (this.serverConnSet) {
      arrayOfServerConnection = new ServerConnection[this.serverConnSet.size()];
      this.serverConnSet.toArray((Object[])arrayOfServerConnection);
    } 
    for (byte b = 0; b < arrayOfServerConnection.length; b++) {
      ServerConnection serverConnection = arrayOfServerConnection[b];
      printWithThread("Closing " + serverConnection);
      serverConnection.signalClose();
    } 
    printWithThread("signalCloseAllServerConnections() exited");
  }
  
  protected void finalize() {
    if (this.serverThread != null)
      releaseServerSocket(); 
  }
  
  public String getAddress() {
    return (this.socket == null) ? this.serverProperties.getProperty("server.address") : this.socket.getInetAddress().getHostAddress();
  }
  
  public String getDatabaseName(int paramInt, boolean paramBoolean) {
    return paramBoolean ? this.serverProperties.getProperty("server.dbname." + paramInt) : ((getState() == 1) ? ((this.dbAlias == null || paramInt < 0 || paramInt >= this.dbAlias.length) ? null : this.dbAlias[paramInt]) : null);
  }
  
  public String getDatabasePath(int paramInt, boolean paramBoolean) {
    return paramBoolean ? this.serverProperties.getProperty("server.database." + paramInt) : ((getState() == 1) ? ((this.dbPath == null || paramInt < 0 || paramInt >= this.dbPath.length) ? null : this.dbPath[paramInt]) : null);
  }
  
  public String getDatabaseType(int paramInt) {
    return (this.dbType == null || paramInt < 0 || paramInt >= this.dbType.length) ? null : this.dbType[paramInt];
  }
  
  public String getDefaultWebPage() {
    return "[IGNORED]";
  }
  
  public String getHelpString() {
    return ResourceBundleHandler.getString(serverBundleHandle, "server.help");
  }
  
  public PrintWriter getErrWriter() {
    return this.errWriter;
  }
  
  public PrintWriter getLogWriter() {
    return this.logWriter;
  }
  
  public int getPort() {
    return this.serverProperties.getIntegerProperty("server.port", ServerConfiguration.getDefaultPort(this.serverProtocol, isTls()));
  }
  
  public String getProductName() {
    return "HSQLDB server";
  }
  
  public String getProductVersion() {
    return "2.3.0";
  }
  
  public String getProtocol() {
    return isTls() ? "HSQLS" : "HSQL";
  }
  
  public Throwable getServerError() {
    return this.serverError;
  }
  
  public String getServerId() {
    return this.serverId;
  }
  
  public int getState() {
    return this.serverState;
  }
  
  public String getStateDescriptor() {
    Throwable throwable = getServerError();
    switch (this.serverState) {
      case 16:
        return "SHUTDOWN";
      case 4:
        return "OPENING";
      case 8:
        return "CLOSING";
      case 1:
        return "ONLINE";
    } 
    return "UNKNOWN";
  }
  
  public String getWebRoot() {
    return "[IGNORED]";
  }
  
  public void handleConnection(Socket paramSocket) {
    WebServerConnection webServerConnection;
    String str;
    printWithThread("handleConnection(" + paramSocket + ") entered");
    if (!allowConnection(paramSocket)) {
      try {
        paramSocket.close();
      } catch (Exception exception) {}
      printWithThread("allowConnection(): connection refused");
      printWithThread("handleConnection() exited");
      return;
    } 
    if (this.socketFactory != null)
      this.socketFactory.configureSocket(paramSocket); 
    if (this.serverProtocol == 1) {
      ServerConnection serverConnection = new ServerConnection(paramSocket, this);
      str = serverConnection.getConnectionThreadName();
    } else {
      webServerConnection = new WebServerConnection(paramSocket, (WebServer)this);
      str = webServerConnection.getConnectionThreadName();
    } 
    Thread thread = new Thread(this.serverConnectionThreadGroup, webServerConnection, str);
    thread.start();
    printWithThread("handleConnection() exited");
  }
  
  public boolean isNoSystemExit() {
    return this.serverProperties.isPropertyTrue("server.no_system_exit");
  }
  
  public boolean isRestartOnShutdown() {
    return this.serverProperties.isPropertyTrue("server.restart_on_shutdown");
  }
  
  public boolean isSilent() {
    return this.isSilent;
  }
  
  public boolean isTls() {
    return this.serverProperties.isPropertyTrue("server.tls");
  }
  
  public boolean isTrace() {
    return this.serverProperties.isPropertyTrue("server.trace");
  }
  
  public boolean putPropertiesFromFile(String paramString) {
    return putPropertiesFromFile(paramString, ".properties");
  }
  
  public boolean putPropertiesFromFile(String paramString1, String paramString2) {
    if (getState() != 16)
      throw Error.error(458, "server properties"); 
    paramString1 = FileUtil.getFileUtil().canonicalOrAbsolutePath(paramString1);
    ServerProperties serverProperties = ServerConfiguration.getPropertiesFromFile(1, paramString1, paramString2);
    if (serverProperties == null || serverProperties.isEmpty())
      return false; 
    printWithThread("putPropertiesFromFile(): [" + paramString1 + ".properties]");
    try {
      setProperties(serverProperties);
    } catch (Exception exception) {
      throw Error.error(exception, 458, 26, new String[] { "Failed to set properties" });
    } 
    return true;
  }
  
  public void putPropertiesFromString(String paramString) {
    if (getState() != 16)
      throw Error.error(458); 
    if (StringUtil.isEmpty(paramString))
      return; 
    printWithThread("putPropertiesFromString(): [" + paramString + "]");
    HsqlProperties hsqlProperties = HsqlProperties.delimitedArgPairsToProps(paramString, "=", ";", "server");
    try {
      setProperties(hsqlProperties);
    } catch (Exception exception) {
      throw Error.error(exception, 458, 26, new String[] { "Failed to set properties" });
    } 
  }
  
  public void setAddress(String paramString) {
    checkRunning(false);
    if (StringUtil.isEmpty(paramString))
      paramString = "0.0.0.0"; 
    printWithThread("setAddress(" + paramString + ")");
    this.serverProperties.setProperty("server.address", paramString);
  }
  
  public void setDatabaseName(int paramInt, String paramString) {
    checkRunning(false);
    printWithThread("setDatabaseName(" + paramInt + "," + paramString + ")");
    this.serverProperties.setProperty("server.dbname." + paramInt, paramString);
  }
  
  public void setDatabasePath(int paramInt, String paramString) {
    checkRunning(false);
    printWithThread("setDatabasePath(" + paramInt + "," + paramString + ")");
    this.serverProperties.setProperty("server.database." + paramInt, paramString);
  }
  
  public void setDefaultWebPage(String paramString) {
    checkRunning(false);
    printWithThread("setDefaultWebPage(" + paramString + ")");
    if (this.serverProtocol != 0)
      return; 
    this.serverProperties.setProperty("server.default_page", paramString);
  }
  
  public void setPort(int paramInt) {
    checkRunning(false);
    printWithThread("setPort(" + paramInt + ")");
    this.serverProperties.setProperty("server.port", paramInt);
  }
  
  public void setErrWriter(PrintWriter paramPrintWriter) {
    this.errWriter = paramPrintWriter;
  }
  
  public void setLogWriter(PrintWriter paramPrintWriter) {
    this.logWriter = paramPrintWriter;
  }
  
  public void setNoSystemExit(boolean paramBoolean) {
    printWithThread("setNoSystemExit(" + paramBoolean + ")");
    this.serverProperties.setProperty("server.no_system_exit", paramBoolean);
  }
  
  public void setRestartOnShutdown(boolean paramBoolean) {
    printWithThread("setRestartOnShutdown(" + paramBoolean + ")");
    this.serverProperties.setProperty("server.restart_on_shutdown", paramBoolean);
  }
  
  public void setSilent(boolean paramBoolean) {
    printWithThread("setSilent(" + paramBoolean + ")");
    this.serverProperties.setProperty("server.silent", paramBoolean);
    this.isSilent = paramBoolean;
  }
  
  public void setTls(boolean paramBoolean) {
    checkRunning(false);
    printWithThread("setTls(" + paramBoolean + ")");
    this.serverProperties.setProperty("server.tls", paramBoolean);
  }
  
  public void setTrace(boolean paramBoolean) {
    printWithThread("setTrace(" + paramBoolean + ")");
    this.serverProperties.setProperty("server.trace", paramBoolean);
    JavaSystem.setLogToSystem(paramBoolean);
  }
  
  public void setDaemon(boolean paramBoolean) {
    checkRunning(false);
    printWithThread("setDaemon(" + paramBoolean + ")");
    this.serverProperties.setProperty("server.daemon", paramBoolean);
  }
  
  public void setWebRoot(String paramString) {
    checkRunning(false);
    paramString = (new File(paramString)).getAbsolutePath();
    printWithThread("setWebRoot(" + paramString + ")");
    if (this.serverProtocol != 0)
      return; 
    this.serverProperties.setProperty("server.root", paramString);
  }
  
  public void setProperties(HsqlProperties paramHsqlProperties) throws IOException, ServerAcl.AclFormatException {
    checkRunning(false);
    if (paramHsqlProperties != null) {
      paramHsqlProperties.validate();
      String[] arrayOfString = paramHsqlProperties.getErrorKeys();
      if (arrayOfString.length > 0)
        throw Error.error(407, arrayOfString[0]); 
      this.serverProperties.addProperties(paramHsqlProperties);
    } 
    this.maxConnections = this.serverProperties.getIntegerProperty("server.maxconnections", 16);
    JavaSystem.setLogToSystem(isTrace());
    this.isSilent = this.serverProperties.isPropertyTrue("server.silent");
    this.isRemoteOpen = this.serverProperties.isPropertyTrue("server.remote_open");
    this.isDaemon = this.serverProperties.isPropertyTrue("server.daemon");
    String str = this.serverProperties.getProperty("server.acl");
    if (str != null) {
      this.acl = new ServerAcl(new File(str));
      if (this.logWriter != null && !this.isSilent)
        this.acl.setPrintWriter(this.logWriter); 
    } 
  }
  
  public int start() {
    printWithThread("start() entered");
    int i = getState();
    if (this.serverThread != null) {
      printWithThread("start(): serverThread != null; no action taken");
      return i;
    } 
    setState(4);
    this.serverThread = new ServerThread("HSQLDB Server ");
    if (this.isDaemon)
      this.serverThread.setDaemon(true); 
    this.serverThread.start();
    while (getState() == 4) {
      try {
        Thread.sleep(100L);
      } catch (InterruptedException interruptedException) {}
    } 
    printWithThread("start() exiting");
    return i;
  }
  
  public int stop() {
    printWithThread("stop() entered");
    int i = getState();
    if (this.serverThread == null) {
      printWithThread("stop() serverThread is null; no action taken");
      return i;
    } 
    releaseServerSocket();
    printWithThread("stop() exiting");
    return i;
  }
  
  protected boolean allowConnection(Socket paramSocket) {
    return this.isShuttingDown ? false : ((this.acl == null) ? true : this.acl.permitAccess(paramSocket.getInetAddress().getAddress()));
  }
  
  protected void init(int paramInt) {
    this.serverState = 16;
    this.serverConnSet = new HashSet();
    this.serverId = toString();
    this.serverId = this.serverId.substring(this.serverId.lastIndexOf('.') + 1);
    this.serverProtocol = paramInt;
    this.serverProperties = ServerConfiguration.newDefaultProperties(paramInt);
    this.logWriter = new PrintWriter(System.out);
    this.errWriter = new PrintWriter(System.err);
    JavaSystem.setLogToSystem(isTrace());
  }
  
  protected synchronized void setState(int paramInt) {
    this.serverState = paramInt;
  }
  
  public final void notify(int paramInt1, int paramInt2) {
    printWithThread("notifiy(" + paramInt1 + "," + paramInt2 + ") entered");
    if (paramInt1 != 0)
      return; 
    releaseDatabase(paramInt2);
    boolean bool = true;
    for (byte b = 0; b < this.dbID.length; b++) {
      if (this.dbAlias[b] != null)
        bool = false; 
    } 
    if (!this.isRemoteOpen && bool)
      stop(); 
  }
  
  final synchronized void releaseDatabase(int paramInt) {
    ServerConnection[] arrayOfServerConnection;
    boolean bool = false;
    printWithThread("releaseDatabase(" + paramInt + ") entered");
    byte b;
    for (b = 0; b < this.dbID.length; b++) {
      if (this.dbID[b] == paramInt && this.dbAlias[b] != null) {
        this.dbID[b] = 0;
        this.dbActionSequence[b] = 0L;
        this.dbAlias[b] = null;
        this.dbPath[b] = null;
        this.dbType[b] = null;
        this.dbProps[b] = null;
      } 
    } 
    synchronized (this.serverConnSet) {
      arrayOfServerConnection = new ServerConnection[this.serverConnSet.size()];
      this.serverConnSet.toArray((Object[])arrayOfServerConnection);
    } 
    for (b = 0; b < arrayOfServerConnection.length; b++) {
      ServerConnection serverConnection = arrayOfServerConnection[b];
      if (serverConnection.dbID == paramInt)
        serverConnection.signalClose(); 
    } 
    printWithThread("releaseDatabase(" + paramInt + ") exiting");
  }
  
  protected void print(String paramString) {
    PrintWriter printWriter = this.logWriter;
    if (printWriter != null) {
      printWriter.println("[" + this.serverId + "]: " + paramString);
      printWriter.flush();
    } 
  }
  
  final void printResource(String paramString) {
    if (serverBundleHandle < 0)
      return; 
    String str = ResourceBundleHandler.getString(serverBundleHandle, paramString);
    if (str == null)
      return; 
    StringTokenizer stringTokenizer = new StringTokenizer(str, "\n\r");
    while (stringTokenizer.hasMoreTokens())
      print(stringTokenizer.nextToken()); 
  }
  
  protected void printStackTrace(Throwable paramThrowable) {
    if (this.errWriter != null) {
      paramThrowable.printStackTrace(this.errWriter);
      this.errWriter.flush();
    } 
  }
  
  final void printWithTimestamp(String paramString) {
    print(HsqlDateTime.getSystemTimeString() + " " + paramString);
  }
  
  protected void printWithThread(String paramString) {
    if (!isSilent())
      print("[" + Thread.currentThread() + "]: " + paramString); 
  }
  
  protected void printError(String paramString) {
    PrintWriter printWriter = this.errWriter;
    if (printWriter != null) {
      printWriter.print("[" + this.serverId + "]: ");
      printWriter.print("[" + Thread.currentThread() + "]: ");
      printWriter.println(paramString);
      printWriter.flush();
    } 
  }
  
  final void printRequest(int paramInt, Result paramResult) {
    if (isSilent())
      return; 
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(paramInt);
    stringBuffer.append(':');
    switch (paramResult.getType()) {
      case 37:
        stringBuffer.append("SQLCLI:SQLPREPARE ");
        stringBuffer.append(paramResult.getMainString());
        break;
      case 34:
        stringBuffer.append(paramResult.getMainString());
        break;
      case 21:
      case 35:
        stringBuffer.append("SQLCLI:SQLEXECUTE:");
        stringBuffer.append(paramResult.getStatementID());
        break;
      case 9:
        stringBuffer.append("SQLCLI:SQLEXECUTE:");
        stringBuffer.append("BATCHMODE:");
        stringBuffer.append(paramResult.getStatementID());
        break;
      case 41:
        stringBuffer.append("SQLCLI:RESULTUPDATE:");
        stringBuffer.append(paramResult.getStatementID());
        break;
      case 36:
        stringBuffer.append("SQLCLI:SQLFREESTMT:");
        stringBuffer.append(paramResult.getStatementID());
        break;
      case 7:
        stringBuffer.append("HSQLCLI:GETSESSIONATTR");
        break;
      case 6:
        stringBuffer.append("HSQLCLI:SETSESSIONATTR:");
        break;
      case 33:
        stringBuffer.append("SQLCLI:SQLENDTRAN:");
        switch (paramResult.getActionType()) {
          case 0:
            stringBuffer.append("COMMIT");
            break;
          case 1:
            stringBuffer.append("ROLLBACK");
            break;
          case 4:
            stringBuffer.append("SAVEPOINT_NAME_RELEASE ");
            stringBuffer.append(paramResult.getMainString());
            break;
          case 2:
            stringBuffer.append("SAVEPOINT_NAME_ROLLBACK ");
            stringBuffer.append(paramResult.getMainString());
            break;
        } 
        stringBuffer.append(paramResult.getActionType());
        break;
      case 39:
        stringBuffer.append("SQLCLI:SQLSTARTTRAN");
        break;
      case 32:
        stringBuffer.append("SQLCLI:SQLDISCONNECT");
        break;
      case 38:
        stringBuffer.append("SQLCLI:SQLSETCONNECTATTR:");
        switch (paramResult.getConnectionAttrType()) {
          case 10027:
            stringBuffer.append("SQL_ATTR_SAVEPOINT_NAME ");
            stringBuffer.append(paramResult.getMainString());
            break;
        } 
        stringBuffer.append(paramResult.getConnectionAttrType());
        break;
      case 40:
        stringBuffer.append("HQLCLI:CLOSE_RESULT:RESULT_ID ");
        stringBuffer.append(paramResult.getResultId());
        break;
      case 13:
        stringBuffer.append("HQLCLI:REQUESTDATA:RESULT_ID ");
        stringBuffer.append(paramResult.getResultId());
        stringBuffer.append(" ROWOFFSET ");
        stringBuffer.append(paramResult.getUpdateCount());
        stringBuffer.append(" ROWCOUNT ");
        stringBuffer.append(paramResult.getFetchSize());
        break;
      default:
        stringBuffer.append("SQLCLI:MODE:");
        stringBuffer.append(paramResult.getType());
        break;
    } 
    print(stringBuffer.toString());
  }
  
  final synchronized int getDBIndex(String paramString) {
    int i = paramString.indexOf(';');
    String str1 = paramString;
    String str2 = null;
    if (i != -1) {
      str1 = paramString.substring(0, i);
      str2 = paramString.substring(i + 1);
    } 
    int j = ArrayUtil.find((Object[])this.dbAlias, str1);
    if (j == -1) {
      if (str2 == null) {
        HsqlException hsqlException = Error.error(458, "database alias does not exist");
        printError("database alias=" + str1 + " does not exist");
        setServerError((Throwable)hsqlException);
        throw hsqlException;
      } 
      return openDatabase(str1, str2);
    } 
    return j;
  }
  
  final int openDatabase(String paramString1, String paramString2) {
    if (!this.isRemoteOpen) {
      HsqlException hsqlException = Error.error(458, "remote open not allowed");
      printError("Remote database open not allowed");
      setServerError((Throwable)hsqlException);
      throw hsqlException;
    } 
    int i = getFirstEmptyDatabaseIndex();
    if (i < -1) {
      i = closeOldestDatabase();
      if (i < -1) {
        HsqlException hsqlException = Error.error(458, "limit of open databases reached");
        printError("limit of open databases reached");
        setServerError((Throwable)hsqlException);
        throw hsqlException;
      } 
    } 
    HsqlProperties hsqlProperties = DatabaseURL.parseURL(paramString2, false, false);
    if (hsqlProperties == null) {
      HsqlException hsqlException = Error.error(458, "invalid database path");
      printError("invalid database path");
      setServerError((Throwable)hsqlException);
      throw hsqlException;
    } 
    String str1 = hsqlProperties.getProperty("database");
    String str2 = hsqlProperties.getProperty("connection_type");
    try {
      int j = DatabaseManager.getDatabase(str2, str1, this, hsqlProperties);
      this.dbID[i] = j;
      this.dbActionSequence[i] = this.actionSequence;
      this.dbAlias[i] = paramString1;
      this.dbPath[i] = str1;
      this.dbType[i] = str2;
      this.dbProps[i] = hsqlProperties;
      return i;
    } catch (HsqlException hsqlException) {
      printError("Database [index=" + i + ", db=" + this.dbType[i] + this.dbPath[i] + ", alias=" + this.dbAlias[i] + "] did not open: " + hsqlException.toString());
      setServerError((Throwable)hsqlException);
      throw hsqlException;
    } 
  }
  
  final int getFirstEmptyDatabaseIndex() {
    for (byte b = 0; b < this.dbAlias.length; b++) {
      if (this.dbAlias[b] == null)
        return b; 
    } 
    return -1;
  }
  
  final boolean openDatabases() {
    printWithThread("openDatabases() entered");
    boolean bool = false;
    setDBInfoArrays();
    for (byte b = 0; b < this.dbAlias.length; b++) {
      if (this.dbAlias[b] != null) {
        int i;
        printWithThread("Opening database: [" + this.dbType[b] + this.dbPath[b] + "]");
        StopWatch stopWatch = new StopWatch();
        try {
          i = DatabaseManager.getDatabase(this.dbType[b], this.dbPath[b], this, this.dbProps[b]);
          this.dbID[b] = i;
          bool = true;
        } catch (HsqlException hsqlException) {
          printError("Database [index=" + b + ", db=" + this.dbType[b] + this.dbPath[b] + ", alias=" + this.dbAlias[b] + "] did not open: " + hsqlException.toString());
          setServerError((Throwable)hsqlException);
          this.dbAlias[b] = null;
          this.dbPath[b] = null;
          this.dbType[b] = null;
          this.dbProps[b] = null;
        } 
        stopWatch.stop();
        String str = "Database [index=" + b + ", id=" + i + ", db=" + this.dbType[b] + this.dbPath[b] + ", alias=" + this.dbAlias[b] + "] opened sucessfully";
        print(stopWatch.elapsedTimeToMessage(str));
      } 
    } 
    printWithThread("openDatabases() exiting");
    if (this.isRemoteOpen)
      bool = true; 
    if (!bool && getServerError() == null)
      setServerError((Throwable)Error.error(407)); 
    return bool;
  }
  
  private void setDBInfoArrays() {
    IntKeyHashMap intKeyHashMap = getDBNameArray();
    int i = intKeyHashMap.size();
    if (this.serverProperties.isPropertyTrue("server.remote_open")) {
      int j = this.serverProperties.getIntegerProperty("server.maxdatabases", 10);
      if (i < j)
        i = j; 
    } 
    this.dbAlias = new String[i];
    this.dbPath = new String[this.dbAlias.length];
    this.dbType = new String[this.dbAlias.length];
    this.dbID = new int[this.dbAlias.length];
    this.dbActionSequence = new long[this.dbAlias.length];
    this.dbProps = new HsqlProperties[this.dbAlias.length];
    Iterator iterator = intKeyHashMap.keySet().iterator();
    for (byte b = 0; iterator.hasNext(); b++) {
      int j = iterator.nextInt();
      String str = getDatabasePath(j, true);
      if (str == null) {
        printWithThread("missing database path: " + intKeyHashMap.get(j));
        continue;
      } 
      HsqlProperties hsqlProperties = DatabaseURL.parseURL(str, false, false);
      if (hsqlProperties == null) {
        printWithThread("malformed database path: " + str);
        continue;
      } 
      this.dbAlias[b] = (String)intKeyHashMap.get(j);
      this.dbPath[b] = hsqlProperties.getProperty("database");
      this.dbType[b] = hsqlProperties.getProperty("connection_type");
      this.dbProps[b] = hsqlProperties;
    } 
  }
  
  private IntKeyHashMap getDBNameArray() {
    int i = "server.dbname.".length();
    IntKeyHashMap intKeyHashMap = new IntKeyHashMap();
    Enumeration<String> enumeration = this.serverProperties.propertyNames();
    while (enumeration.hasMoreElements()) {
      int j;
      String str1 = enumeration.nextElement();
      if (!str1.startsWith("server.dbname."))
        continue; 
      try {
        j = Integer.parseInt(str1.substring(i));
      } catch (NumberFormatException numberFormatException) {
        printWithThread("maformed database enumerator: " + str1);
        continue;
      } 
      String str2 = this.serverProperties.getProperty(str1).toLowerCase();
      if (!this.aliasSet.add(str2))
        printWithThread("duplicate alias: " + str2); 
      Object object = intKeyHashMap.put(j, str2);
      if (object != null)
        printWithThread("duplicate database enumerator: " + str1); 
    } 
    return intKeyHashMap;
  }
  
  private void openServerSocket() throws Exception {
    printWithThread("openServerSocket() entered");
    if (isTls())
      printWithThread("Requesting TLS/SSL-encrypted JDBC"); 
    StopWatch stopWatch = new StopWatch();
    this.socketFactory = HsqlSocketFactory.getInstance(isTls());
    String str = getAddress();
    int i = getPort();
    if (StringUtil.isEmpty(str) || "0.0.0.0".equalsIgnoreCase(str.trim())) {
      this.socket = this.socketFactory.createServerSocket(i);
    } else {
      try {
        this.socket = this.socketFactory.createServerSocket(i, str);
      } catch (UnknownHostException unknownHostException) {
        byte b;
        Object[] arrayOfObject;
        String[] arrayOfString = ServerConfiguration.listLocalInetAddressNames();
        if (arrayOfString.length > 0) {
          b = 61;
          StringBuffer stringBuffer = new StringBuffer();
          for (byte b1 = 0; b1 < arrayOfString.length; b1++) {
            if (stringBuffer.length() > 0)
              stringBuffer.append(", "); 
            stringBuffer.append(arrayOfString[b1]);
          } 
          arrayOfObject = new Object[] { str, stringBuffer.toString() };
        } else {
          b = 62;
          arrayOfObject = new Object[] { str };
        } 
        throw new UnknownHostException(Error.getMessage(b, 0, arrayOfObject));
      } 
    } 
    this.socket.setSoTimeout(1000);
    printWithThread("Got server socket: " + this.socket);
    print(stopWatch.elapsedTimeToMessage("Server socket opened successfully"));
    if (this.socketFactory.isSecure())
      print("Using TLS/SSL-encrypted JDBC"); 
    printWithThread("openServerSocket() exiting");
  }
  
  private void printServerOnlineMessage() {
    String str = getProductName() + " " + getProductVersion() + " is online on port " + getPort();
    printWithTimestamp(str);
    printResource("online.help");
  }
  
  protected void printProperties() {
    if (isSilent())
      return; 
    Enumeration<String> enumeration = this.serverProperties.propertyNames();
    while (enumeration.hasMoreElements()) {
      String str1 = enumeration.nextElement();
      String str2 = this.serverProperties.getProperty(str1);
      printWithThread(str1 + "=" + str2);
    } 
  }
  
  private synchronized void releaseServerSocket() {
    printWithThread("releaseServerSocket() entered");
    if (this.socket != null) {
      printWithThread("Releasing server socket: [" + this.socket + "]");
      setState(8);
      try {
        this.socket.close();
      } catch (IOException iOException) {
        printError("Exception closing server socket");
        printError("releaseServerSocket(): " + iOException);
      } 
      this.socket = null;
    } 
    printWithThread("releaseServerSocket() exited");
  }
  
  private void run() {
    printWithThread("run() entered");
    print("Initiating startup sequence...");
    printProperties();
    StopWatch stopWatch = new StopWatch();
    setServerError(null);
    try {
      openServerSocket();
    } catch (Exception exception) {
      setServerError(exception);
      printError("run()/openServerSocket(): ");
      printStackTrace(exception);
      shutdown(true);
      return;
    } 
    String str = "HSQLDB Connections @" + Integer.toString(hashCode(), 16);
    ThreadGroup threadGroup = new ThreadGroup(str);
    threadGroup.setDaemon(false);
    this.serverConnectionThreadGroup = threadGroup;
    if (!openDatabases()) {
      setServerError(null);
      printError("Shutting down because there are no open databases");
      shutdown(true);
      return;
    } 
    setState(1);
    print(stopWatch.elapsedTimeToMessage("Startup sequence completed"));
    printServerOnlineMessage();
    this.isShuttingDown = false;
    try {
      while (this.socket != null) {
        try {
          handleConnection(this.socket.accept());
        } catch (InterruptedIOException interruptedIOException) {}
      } 
    } catch (IOException iOException) {
      if (getState() == 1) {
        setServerError(iOException);
        printError(this + ".run()/handleConnection(): ");
        printStackTrace(iOException);
      } 
    } catch (Throwable throwable) {
      printWithThread(throwable.toString());
    } finally {
      shutdown(false);
    } 
  }
  
  protected void setServerError(Throwable paramThrowable) {
    this.serverError = paramThrowable;
  }
  
  public void shutdownCatalogs(int paramInt) {
    DatabaseManager.shutdownDatabases(this, paramInt);
  }
  
  public void shutdownWithCatalogs(int paramInt) {
    this.isShuttingDown = true;
    DatabaseManager.shutdownDatabases(this, paramInt);
    shutdown(false);
    this.isShuttingDown = false;
  }
  
  public void shutdown() {
    shutdown(false);
  }
  
  protected synchronized void shutdown(boolean paramBoolean) {
    if (this.serverState == 16)
      return; 
    printWithThread("shutdown() entered");
    StopWatch stopWatch = new StopWatch();
    print("Initiating shutdown sequence...");
    releaseServerSocket();
    DatabaseManager.deRegisterServer(this);
    if (this.dbPath != null)
      for (byte b = 0; b < this.dbPath.length; b++)
        releaseDatabase(this.dbID[b]);  
    if (this.serverConnectionThreadGroup != null) {
      if (!this.serverConnectionThreadGroup.isDestroyed()) {
        int i = this.serverConnectionThreadGroup.activeCount();
        for (byte b = 0; this.serverConnectionThreadGroup.activeCount() > 0 && b < i; b++) {
          try {
            Thread.sleep(100L);
          } catch (Exception exception) {}
        } 
        try {
          this.serverConnectionThreadGroup.destroy();
          printWithThread(this.serverConnectionThreadGroup.getName() + " destroyed");
        } catch (Throwable throwable) {
          printWithThread(this.serverConnectionThreadGroup.getName() + " not destroyed");
          printWithThread(throwable.toString());
        } 
      } 
      this.serverConnectionThreadGroup = null;
    } 
    this.serverThread = null;
    setState(16);
    print(stopWatch.elapsedTimeToMessage("Shutdown sequence completed"));
    if (isNoSystemExit()) {
      printWithTimestamp("SHUTDOWN : System.exit() was not called");
      printWithThread("shutdown() exited");
    } else {
      printWithTimestamp("SHUTDOWN : System.exit() is called next");
      printWithThread("shutdown() exiting...");
      try {
        System.exit(0);
      } catch (Throwable throwable) {
        printWithThread(throwable.toString());
      } 
    } 
  }
  
  synchronized void setActionSequence(int paramInt) {
    this.dbActionSequence[paramInt] = this.actionSequence++;
  }
  
  protected int closeOldestDatabase() {
    return -1;
  }
  
  protected static void printHelp(String paramString) {
    System.out.println(ResourceBundleHandler.getString(serverBundleHandle, paramString));
  }
  
  public static void main(String[] paramArrayOfString) {
    HsqlProperties hsqlProperties = null;
    hsqlProperties = HsqlProperties.argArrayToProps(paramArrayOfString, "server");
    String[] arrayOfString = hsqlProperties.getErrorKeys();
    if (arrayOfString.length != 0) {
      System.out.println("no value for argument:" + arrayOfString[0]);
      printHelp("server.help");
      return;
    } 
    String str1 = hsqlProperties.getProperty("server.props");
    String str2 = "";
    if (str1 == null) {
      str1 = "server";
      str2 = ".properties";
    } else {
      hsqlProperties.removeProperty("server.props");
    } 
    str1 = FileUtil.getFileUtil().canonicalOrAbsolutePath(str1);
    ServerProperties serverProperties1 = ServerConfiguration.getPropertiesFromFile(1, str1, str2);
    ServerProperties serverProperties2 = (serverProperties1 == null) ? new ServerProperties(1) : serverProperties1;
    serverProperties2.addProperties(hsqlProperties);
    ServerConfiguration.translateDefaultDatabaseProperty(serverProperties2);
    ServerConfiguration.translateDefaultNoSystemExitProperty(serverProperties2);
    ServerConfiguration.translateAddressProperty(serverProperties2);
    Server server = new Server();
    try {
      server.setProperties(serverProperties2);
    } catch (Exception exception) {
      server.printError("Failed to set properties");
      server.printStackTrace(exception);
      return;
    } 
    server.print("Startup sequence initiated from main() method");
    if (serverProperties1 != null) {
      server.print("Loaded properties from [" + str1 + str2 + "]");
    } else {
      server.print("Could not load properties from file");
      server.print("Using cli/default properties only");
    } 
    server.start();
  }
  
  private class ServerThread extends Thread {
    ServerThread(String param1String) {
      super(param1String);
      setName(param1String + '@' + Integer.toString(Server.this.hashCode(), 16));
    }
    
    public void run() {
      Server.this.run();
      Server.this.printWithThread("ServerThread.run() exited");
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\Server.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */