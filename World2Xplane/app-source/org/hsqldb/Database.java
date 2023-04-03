package org.hsqldb;

import org.hsqldb.dbinfo.DatabaseInformation;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FrameworkLogger;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlTimer;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.map.ValuePool;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.persist.LobManager;
import org.hsqldb.persist.Logger;
import org.hsqldb.persist.PersistentStoreCollectionDatabase;
import org.hsqldb.result.Result;
import org.hsqldb.rights.GranteeManager;
import org.hsqldb.rights.User;
import org.hsqldb.rights.UserManager;
import org.hsqldb.types.Collation;

public class Database {
  int databaseID;
  
  String databaseUniqueName;
  
  String databaseType;
  
  private final String canonicalPath;
  
  public HsqlProperties urlProperties;
  
  private final String path;
  
  public Collation collation;
  
  public DatabaseInformation dbInfo;
  
  private volatile int dbState;
  
  public Logger logger;
  
  boolean databaseReadOnly;
  
  private boolean filesReadOnly;
  
  private boolean filesInJar;
  
  public boolean sqlEnforceTypes = false;
  
  public boolean sqlEnforceRefs = false;
  
  public boolean sqlEnforceSize = true;
  
  public boolean sqlEnforceNames = false;
  
  public boolean sqlRegularNames = true;
  
  public boolean sqlEnforceTDCD = true;
  
  public boolean sqlEnforceTDCU = true;
  
  public boolean sqlTranslateTTI = true;
  
  public boolean sqlConcatNulls = true;
  
  public boolean sqlUniqueNulls = true;
  
  public boolean sqlNullsFirst = true;
  
  public boolean sqlNullsOrder = true;
  
  public boolean sqlConvertTruncate = true;
  
  public int sqlAvgScale = 0;
  
  public boolean sqlDoubleNaN = true;
  
  public boolean sqlLongvarIsLob = false;
  
  public boolean sqlIgnoreCase = false;
  
  public boolean sqlSyntaxDb2 = false;
  
  public boolean sqlSyntaxMss = false;
  
  public boolean sqlSyntaxMys = false;
  
  public boolean sqlSyntaxOra = false;
  
  public boolean sqlSyntaxPgs = false;
  
  public int recoveryMode = 0;
  
  private boolean isReferentialIntegrity = true;
  
  public HsqlDatabaseProperties databaseProperties;
  
  private final boolean shutdownOnNoConnection;
  
  int resultMaxMemoryRows;
  
  public UserManager userManager;
  
  public GranteeManager granteeManager;
  
  public HsqlNameManager nameManager;
  
  public SessionManager sessionManager;
  
  public TransactionManager txManager;
  
  public int defaultIsolationLevel = 2;
  
  public boolean txConflictRollback = true;
  
  public SchemaManager schemaManager;
  
  public PersistentStoreCollectionDatabase persistentStoreCollection;
  
  public LobManager lobManager;
  
  public CheckpointRunner checkpointRunner;
  
  public TimeoutRunner timeoutRunner;
  
  Result updateZeroResult = Result.updateZeroResult;
  
  public static final int DATABASE_ONLINE = 1;
  
  public static final int DATABASE_OPENING = 2;
  
  public static final int DATABASE_CLOSING = 3;
  
  public static final int DATABASE_SHUTDOWN = 4;
  
  public static final int CLOSEMODE_IMMEDIATELY = 1;
  
  public static final int CLOSEMODE_NORMAL = 2;
  
  public static final int CLOSEMODE_COMPACT = 3;
  
  public static final int CLOSEMODE_SCRIPT = 4;
  
  Database(String paramString1, String paramString2, String paramString3, HsqlProperties paramHsqlProperties) {
    setState(4);
    this.databaseType = paramString1;
    this.path = paramString2;
    this.canonicalPath = paramString3;
    this.urlProperties = paramHsqlProperties;
    if (this.databaseType == "res:") {
      this.filesInJar = true;
      this.filesReadOnly = true;
    } 
    this.logger = new Logger(this);
    this.shutdownOnNoConnection = this.urlProperties.isPropertyTrue("shutdown");
    this.recoveryMode = this.urlProperties.getIntegerProperty("recover", 0);
  }
  
  synchronized void open() {
    if (!isShutdown())
      return; 
    reopen();
  }
  
  void reopen() {
    boolean bool = false;
    setState(2);
    try {
      this.lobManager = new LobManager(this);
      this.nameManager = new HsqlNameManager(this);
      this.granteeManager = new GranteeManager(this);
      this.userManager = new UserManager(this);
      this.schemaManager = new SchemaManager(this);
      this.persistentStoreCollection = new PersistentStoreCollectionDatabase(this);
      this.isReferentialIntegrity = true;
      this.sessionManager = new SessionManager(this);
      this.collation = Collation.newDatabaseInstance();
      this.dbInfo = DatabaseInformation.newDatabaseInformation(this);
      this.txManager = new TransactionManager2PL(this);
      this.lobManager.createSchema();
      this.sessionManager.getSysLobSession().setSchema("SYSTEM_LOBS");
      this.schemaManager.setSchemaChangeTimestamp();
      this.schemaManager.createSystemTables();
      this.logger.open();
      bool = this.logger.isNewDatabase;
      if (bool) {
        String str1 = this.urlProperties.getProperty("user", "SA");
        String str2 = this.urlProperties.getProperty("password", "");
        this.userManager.createFirstUser(str1, str2);
        this.schemaManager.createPublicSchema();
        this.logger.checkpoint(false);
      } 
      this.lobManager.open();
      this.dbInfo.setWithContent(true);
      this.checkpointRunner = new CheckpointRunner();
      this.timeoutRunner = new TimeoutRunner();
    } catch (Throwable throwable) {
      this.logger.close(1);
      this.logger.releaseLock();
      setState(4);
      clearStructures();
      DatabaseManager.removeDatabase(this);
      if (!(throwable instanceof HsqlException))
        throwable = Error.error(458, throwable); 
      this.logger.logSevereEvent("could not reopen database", throwable);
      throw (HsqlException)throwable;
    } 
    setState(1);
  }
  
  void clearStructures() {
    if (this.schemaManager != null)
      this.schemaManager.release(); 
    if (this.checkpointRunner != null)
      this.checkpointRunner.stop(); 
    if (this.timeoutRunner != null)
      this.timeoutRunner.stop(); 
    this.lobManager = null;
    this.granteeManager = null;
    this.userManager = null;
    this.nameManager = null;
    this.schemaManager = null;
    this.sessionManager = null;
    this.dbInfo = null;
    this.checkpointRunner = null;
    this.timeoutRunner = null;
  }
  
  public int getDatabaseID() {
    return this.databaseID;
  }
  
  public String getUniqueName() {
    return this.databaseUniqueName;
  }
  
  public void setUniqueName(String paramString) {
    this.databaseUniqueName = paramString;
  }
  
  public String getType() {
    return this.databaseType;
  }
  
  public String getPath() {
    return this.path;
  }
  
  public HsqlNameManager.HsqlName getCatalogName() {
    return this.nameManager.getCatalogName();
  }
  
  public HsqlDatabaseProperties getProperties() {
    return this.databaseProperties;
  }
  
  public SessionManager getSessionManager() {
    return this.sessionManager;
  }
  
  public boolean isReadOnly() {
    return this.databaseReadOnly;
  }
  
  boolean isShutdown() {
    return (this.dbState == 4);
  }
  
  synchronized Session connect(String paramString1, String paramString2, String paramString3, int paramInt) {
    if (paramString1.equalsIgnoreCase("SA"))
      paramString1 = "SA"; 
    User user = this.userManager.getUser(paramString1, paramString2);
    return this.sessionManager.newSession(this, user, this.databaseReadOnly, true, paramString3, paramInt);
  }
  
  public void setReadOnly() {
    this.databaseReadOnly = true;
    this.filesReadOnly = true;
  }
  
  public void setFilesReadOnly() {
    this.filesReadOnly = true;
  }
  
  public boolean isFilesReadOnly() {
    return this.filesReadOnly;
  }
  
  public boolean isFilesInJar() {
    return this.filesInJar;
  }
  
  public UserManager getUserManager() {
    return this.userManager;
  }
  
  public GranteeManager getGranteeManager() {
    return this.granteeManager;
  }
  
  public void setReferentialIntegrity(boolean paramBoolean) {
    this.isReferentialIntegrity = paramBoolean;
  }
  
  public boolean isReferentialIntegrity() {
    return this.isReferentialIntegrity;
  }
  
  public int getResultMaxMemoryRows() {
    return this.resultMaxMemoryRows;
  }
  
  public void setResultMaxMemoryRows(int paramInt) {
    this.resultMaxMemoryRows = paramInt;
  }
  
  public void setStrictNames(boolean paramBoolean) {
    this.sqlEnforceNames = paramBoolean;
  }
  
  public void setRegularNames(boolean paramBoolean) {
    this.sqlRegularNames = paramBoolean;
    this.nameManager.setSqlRegularNames(paramBoolean);
  }
  
  public void setStrictColumnSize(boolean paramBoolean) {
    this.sqlEnforceSize = paramBoolean;
  }
  
  public void setStrictReferences(boolean paramBoolean) {
    this.sqlEnforceRefs = paramBoolean;
  }
  
  public void setStrictTypes(boolean paramBoolean) {
    this.sqlEnforceTypes = paramBoolean;
  }
  
  public void setStrictTDCD(boolean paramBoolean) {
    this.sqlEnforceTDCD = paramBoolean;
  }
  
  public void setStrictTDCU(boolean paramBoolean) {
    this.sqlEnforceTDCU = paramBoolean;
  }
  
  public void setTranslateTTI(boolean paramBoolean) {
    this.sqlTranslateTTI = paramBoolean;
  }
  
  public void setNullsFirst(boolean paramBoolean) {
    this.sqlNullsFirst = paramBoolean;
  }
  
  public void setNullsOrder(boolean paramBoolean) {
    this.sqlNullsOrder = paramBoolean;
  }
  
  public void setConcatNulls(boolean paramBoolean) {
    this.sqlConcatNulls = paramBoolean;
  }
  
  public void setUniqueNulls(boolean paramBoolean) {
    this.sqlUniqueNulls = paramBoolean;
  }
  
  public void setConvertTrunc(boolean paramBoolean) {
    this.sqlConvertTruncate = paramBoolean;
  }
  
  public void setDoubleNaN(boolean paramBoolean) {
    this.sqlDoubleNaN = paramBoolean;
  }
  
  public void setAvgScale(int paramInt) {
    this.sqlAvgScale = paramInt;
  }
  
  public void setLongVarIsLob(boolean paramBoolean) {
    this.sqlLongvarIsLob = paramBoolean;
  }
  
  public void setIgnoreCase(boolean paramBoolean) {
    this.sqlIgnoreCase = paramBoolean;
  }
  
  public void setSyntaxDb2(boolean paramBoolean) {
    this.sqlSyntaxDb2 = paramBoolean;
  }
  
  public void setSyntaxMss(boolean paramBoolean) {
    this.sqlSyntaxMss = paramBoolean;
  }
  
  public void setSyntaxMys(boolean paramBoolean) {
    this.sqlSyntaxMys = paramBoolean;
  }
  
  public void setSyntaxOra(boolean paramBoolean) {
    this.sqlSyntaxOra = paramBoolean;
  }
  
  public void setSyntaxPgs(boolean paramBoolean) {
    this.sqlSyntaxPgs = paramBoolean;
  }
  
  protected void finalize() {
    if (getState() != 1)
      return; 
    try {
      close(1);
    } catch (HsqlException hsqlException) {}
  }
  
  void closeIfLast() {
    if (this.sessionManager.isEmpty() && this.dbState == 1)
      if (this.shutdownOnNoConnection) {
        try {
          close(2);
        } catch (HsqlException hsqlException) {}
      } else {
        this.logger.synchLog();
      }  
  }
  
  public void close(int paramInt) {
    HsqlException hsqlException = null;
    synchronized (this) {
      if (getState() != 1)
        return; 
      setState(3);
    } 
    this.sessionManager.closeAllSessions();
    if (this.filesReadOnly)
      paramInt = 1; 
    boolean bool = this.logger.close(paramInt);
    this.lobManager.close();
    this.sessionManager.close();
    try {
      if (bool && paramInt == 3) {
        clearStructures();
        reopen();
        setState(3);
        this.sessionManager.closeAllSessions();
        this.logger.close(2);
        this.lobManager.close();
        this.sessionManager.close();
      } 
    } catch (Throwable throwable) {
      if (throwable instanceof HsqlException) {
        hsqlException = (HsqlException)throwable;
      } else {
        hsqlException = Error.error(458, throwable);
      } 
    } 
    this.logger.releaseLock();
    setState(4);
    clearStructures();
    DatabaseManager.removeDatabase(this);
    FrameworkLogger.clearLoggers("hsqldb.db." + getUniqueName());
    if (hsqlException != null)
      throw hsqlException; 
  }
  
  private void setState(int paramInt) {
    this.dbState = paramInt;
  }
  
  int getState() {
    return this.dbState;
  }
  
  String getStateString() {
    int i = getState();
    switch (i) {
      case 3:
        return "DATABASE_CLOSING";
      case 1:
        return "DATABASE_ONLINE";
      case 2:
        return "DATABASE_OPENING";
      case 4:
        return "DATABASE_SHUTDOWN";
    } 
    return "UNKNOWN";
  }
  
  public String[] getSettingsSQL() {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    StringBuffer stringBuffer = new StringBuffer();
    if (!(getCatalogName()).name.equals("PUBLIC")) {
      String str = (getCatalogName()).statementName;
      stringBuffer.append("ALTER CATALOG PUBLIC RENAME TO ").append(str);
      hsqlArrayList.add(stringBuffer.toString());
      stringBuffer.setLength(0);
    } 
    if (!this.collation.isDefaultCollation())
      hsqlArrayList.add(this.collation.getDatabaseCollationSQL()); 
    HashMappedList hashMappedList = this.schemaManager.getTables("SYSTEM_LOBS");
    for (byte b = 0; b < hashMappedList.size(); b++) {
      Table table = (Table)hashMappedList.get(b);
      if (table.isCached()) {
        stringBuffer.append("SET").append(' ').append("TABLE");
        stringBuffer.append(' ');
        stringBuffer.append(table.getName().getSchemaQualifiedStatementName());
        stringBuffer.append(' ').append("TYPE").append(' ');
        stringBuffer.append("CACHED");
        hsqlArrayList.add(stringBuffer.toString());
        stringBuffer.setLength(0);
      } 
    } 
    String[] arrayOfString = new String[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public Result getScript(boolean paramBoolean) {
    Result result = Result.newSingleColumnResult("COMMAND");
    String[] arrayOfString = this.logger.getPropertiesSQL(paramBoolean);
    addRows(result, arrayOfString);
    arrayOfString = getSettingsSQL();
    addRows(result, arrayOfString);
    arrayOfString = getGranteeManager().getSQL();
    addRows(result, arrayOfString);
    arrayOfString = this.schemaManager.getSQLArray();
    addRows(result, arrayOfString);
    arrayOfString = this.schemaManager.getCommentsArray();
    addRows(result, arrayOfString);
    arrayOfString = this.schemaManager.getTableSpaceSQL();
    addRows(result, arrayOfString);
    if (paramBoolean) {
      arrayOfString = this.schemaManager.getIndexRootsSQL();
      addRows(result, arrayOfString);
    } 
    arrayOfString = this.schemaManager.getTablePropsSQL(!paramBoolean);
    addRows(result, arrayOfString);
    arrayOfString = getUserManager().getAuthenticationSQL();
    addRows(result, arrayOfString);
    arrayOfString = getUserManager().getInitialSchemaSQL();
    addRows(result, arrayOfString);
    arrayOfString = getGranteeManager().getRightstSQL();
    addRows(result, arrayOfString);
    return result;
  }
  
  private static void addRows(Result paramResult, String[] paramArrayOfString) {
    if (paramArrayOfString == null)
      return; 
    for (byte b = 0; b < paramArrayOfString.length; b++) {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = paramArrayOfString[b];
      paramResult.initialiseNavigator().add((Object[])arrayOfString);
    } 
  }
  
  public String getURI() {
    return this.databaseType + this.canonicalPath;
  }
  
  public String getCanonicalPath() {
    return this.canonicalPath;
  }
  
  public HsqlProperties getURLProperties() {
    return this.urlProperties;
  }
  
  public TimeoutRunner getTimeoutRunner() {
    return this.timeoutRunner;
  }
  
  static class TimeoutRunner implements Runnable {
    private Object timerTask;
    
    OrderedHashSet sessionList;
    
    public void run() {
      try {
        for (int i = this.sessionList.size() - 1; i >= 0; i--) {
          Session session = (Session)this.sessionList.get(i);
          if (session.isClosed()) {
            synchronized (this) {
              this.sessionList.remove(i);
            } 
          } else {
            boolean bool = session.timeoutManager.checkTimeout();
          } 
        } 
      } catch (Throwable throwable) {}
    }
    
    public void start() {
      this.sessionList = new OrderedHashSet();
      this.timerTask = DatabaseManager.getTimer().schedulePeriodicallyAfter(0L, 1000L, this, true);
    }
    
    public void stop() {
      synchronized (this) {
        if (this.timerTask == null)
          return; 
        HsqlTimer.cancel(this.timerTask);
        this.sessionList.clear();
        this.timerTask = null;
        this.sessionList = null;
      } 
    }
    
    public void addSession(Session param1Session) {
      synchronized (this) {
        if (this.timerTask == null)
          start(); 
        this.sessionList.add(param1Session);
      } 
    }
  }
  
  class CheckpointRunner implements Runnable {
    private volatile boolean waiting;
    
    private Object timerTask;
    
    public void run() {
      try {
        Session session = Database.this.sessionManager.newSysSession();
        Statement statement = ParserCommand.getAutoCheckpointStatement(Database.this);
        session.executeCompiledStatement(statement, ValuePool.emptyObjectArray, 0);
        session.commit(false);
        session.close();
        this.waiting = false;
      } catch (Throwable throwable) {}
    }
    
    public void start() {
      if (!Database.this.logger.isLogged())
        return; 
      synchronized (this) {
        if (this.waiting)
          return; 
        this.waiting = true;
      } 
      this.timerTask = DatabaseManager.getTimer().scheduleAfter(0L, this);
    }
    
    public void stop() {
      HsqlTimer.cancel(this.timerTask);
      this.timerTask = null;
      this.waiting = false;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\Database.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */