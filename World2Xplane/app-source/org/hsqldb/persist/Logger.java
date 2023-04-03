package org.hsqldb.persist;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import org.hsqldb.Database;
import org.hsqldb.DatabaseURL;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.NumberSequence;
import org.hsqldb.Row;
import org.hsqldb.Session;
import org.hsqldb.Statement;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.TransactionManager;
import org.hsqldb.TransactionManagerMV2PL;
import org.hsqldb.TransactionManagerMVCC;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.index.IndexAVL;
import org.hsqldb.index.IndexAVLMemory;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.FrameworkLogger;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.InputStreamInterface;
import org.hsqldb.lib.InputStreamWrapper;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.SimpleLog;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.lib.tar.DbBackup;
import org.hsqldb.lib.tar.TarMalformatException;
import org.hsqldb.result.Result;
import org.hsqldb.scriptio.ScriptWriterBase;
import org.hsqldb.scriptio.ScriptWriterText;
import org.hsqldb.types.RowType;
import org.hsqldb.types.Type;

public class Logger {
  public SimpleLog appLog;
  
  public SimpleLog sqlLog;
  
  FrameworkLogger fwLogger;
  
  FrameworkLogger sqlLogger;
  
  private Database database;
  
  public boolean checkpointRequired;
  
  public boolean checkpointDue;
  
  public boolean checkpointDisabled;
  
  private boolean logsStatements;
  
  private boolean loggingEnabled;
  
  private boolean syncFile = false;
  
  boolean propIsFileDatabase;
  
  boolean propIncrementBackup;
  
  boolean propNioDataFile;
  
  long propNioMaxSize = 268435456L;
  
  int propMaxFreeBlocks = 512;
  
  int propCacheMaxRows;
  
  int propCacheMaxSize;
  
  int propCacheDefragLimit;
  
  int propDataFileScale;
  
  String propTextSourceDefault = "";
  
  boolean propTextAllowFullPath;
  
  int propWriteDelay;
  
  int propLogSize;
  
  boolean propLogData = true;
  
  int propEventLogLevel;
  
  int propSqlLogLevel;
  
  int propGC;
  
  int propTxMode = 0;
  
  boolean propRefIntegrity = true;
  
  int propLobBlockSize = 32768;
  
  boolean propCompressLobs;
  
  int propScriptFormat = 0;
  
  boolean propLargeData;
  
  boolean propFileSpaces;
  
  int propCheckPersistence;
  
  Log log;
  
  private LockFile lockFile;
  
  private Crypto crypto;
  
  boolean cryptLobs;
  
  public FileAccess fileAccess;
  
  public boolean isStoredFileAccess;
  
  public boolean isNewStoredFileAccess;
  
  String tempDirectoryPath;
  
  private HashMap textCacheList = new HashMap();
  
  public boolean isNewDatabase;
  
  public boolean isSingleFile;
  
  AtomicInteger backupState = new AtomicInteger();
  
  static final int largeDataFactor = 128;
  
  static final int stateNormal = 0;
  
  static final int stateBackup = 1;
  
  static final int stateCheckpoint = 2;
  
  public static final String oldFileExtension = ".old";
  
  public static final String newFileExtension = ".new";
  
  public static final String appLogFileExtension = ".app.log";
  
  public static final String sqlLogFileExtension = ".sql.log";
  
  public static final String logFileExtension = ".log";
  
  public static final String scriptFileExtension = ".script";
  
  public static final String propertiesFileExtension = ".properties";
  
  public static final String dataFileExtension = ".data";
  
  public static final String backupFileExtension = ".backup";
  
  public static final String lobsFileExtension = ".lobs";
  
  public static final String lockFileExtension = ".lck";
  
  private SimpleDateFormat backupFileFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
  
  private Character runtimeFileDelim = new Character(System.getProperty("file.separator").charAt(0));
  
  DbBackup backup;
  
  public Logger(Database paramDatabase) {
    this.database = paramDatabase;
  }
  
  public void open() {
    String str1 = this.database.getURLProperties().getProperty("fileaccess_class_name");
    String str2 = this.database.getURLProperties().getProperty("storage_class_name");
    boolean bool1 = false;
    boolean bool2 = false;
    if (str1 != null) {
      String str = this.database.getURLProperties().getProperty("storage_key");
      try {
        Class<?> clazz1 = null;
        Class<?> clazz2 = null;
        try {
          ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
          clazz1 = classLoader.loadClass(str1);
          clazz2 = classLoader.loadClass(str2);
        } catch (ClassNotFoundException classNotFoundException) {
          clazz1 = Class.forName(str1);
          clazz2 = Class.forName(str2);
        } 
        if (clazz2.isAssignableFrom(RandomAccessInterface.class))
          this.isNewStoredFileAccess = true; 
        Constructor<?> constructor = clazz1.getConstructor(new Class[] { Object.class });
        this.fileAccess = (FileAccess)constructor.newInstance(new Object[] { str });
        this.isStoredFileAccess = true;
      } catch (ClassNotFoundException classNotFoundException) {
        System.out.println("ClassNotFoundException");
      } catch (InstantiationException instantiationException) {
        System.out.println("InstantiationException");
      } catch (IllegalAccessException illegalAccessException) {
        System.out.println("IllegalAccessException");
      } catch (Exception exception) {
        System.out.println("Exception");
      } 
    } else {
      this.fileAccess = FileUtil.getFileAccess(this.database.isFilesInJar());
    } 
    this.propIsFileDatabase = DatabaseURL.isFileBasedDatabaseType(this.database.getType());
    this.database.databaseProperties = new HsqlDatabaseProperties(this.database);
    this.propTextAllowFullPath = this.database.databaseProperties.isPropertyTrue("textdb.allow_full_path");
    if (this.propIsFileDatabase) {
      boolean bool;
      bool1 = this.database.databaseProperties.load();
      bool2 = this.fileAccess.isStreamElement(this.database.getPath() + ".script");
      if (this.database.databaseProperties.isVersion18()) {
        bool = bool1;
        this.database.databaseProperties.setProperty("hsqldb.inc_backup", false);
      } else {
        bool = bool2;
        if (!bool) {
          bool = this.fileAccess.isStreamElement(this.database.getPath() + ".script" + ".new");
          if (bool)
            this.database.databaseProperties.setDBModified(2); 
        } 
      } 
      this.isNewDatabase = !bool;
    } else {
      this.isNewDatabase = true;
    } 
    if (this.isNewDatabase) {
      String str = newUniqueName();
      this.database.setUniqueName(str);
      boolean bool = this.database.isFilesInJar();
      int j = bool | ((this.database.urlProperties.isPropertyTrue("ifexists") || !this.database.urlProperties.isPropertyTrue("create", true)) ? 1 : 0);
      if (j != 0)
        throw Error.error(465, this.database.getPath()); 
      this.database.databaseProperties.setURLProperties(this.database.urlProperties);
    } else {
      if (!bool1)
        this.database.databaseProperties.setDBModified(1); 
      if (this.database.urlProperties.isPropertyTrue("files_readonly"))
        this.database.databaseProperties.setProperty("files_readonly", true); 
      if (this.database.urlProperties.isPropertyTrue("readonly"))
        this.database.databaseProperties.setProperty("readonly", true); 
      if (!this.database.urlProperties.isPropertyTrue("hsqldb.lock_file", true))
        this.database.databaseProperties.setProperty("hsqldb.lock_file", false); 
      int j = this.database.urlProperties.getIntegerProperty("hsqldb.cache_free_count", -1);
      if (j >= 512)
        this.database.databaseProperties.setProperty("hsqldb.cache_free_count", ArrayUtil.getTwoPowerFloor(j)); 
    } 
    setVariables();
    String str3 = null;
    String str4 = null;
    if (this.propIsFileDatabase && !this.database.isFilesReadOnly()) {
      str3 = this.database.getPath() + ".app.log";
      str4 = this.database.getPath() + ".sql.log";
    } 
    this.appLog = new SimpleLog(str3, this.propEventLogLevel, false);
    this.sqlLog = new SimpleLog(str4, this.propSqlLogLevel, true);
    this.database.setReferentialIntegrity(this.propRefIntegrity);
    if (!isFileDatabase())
      return; 
    this.checkpointRequired = false;
    this.logsStatements = false;
    boolean bool3 = this.database.getProperties().isPropertyTrue("hsqldb.lock_file");
    if (bool3 && !this.database.isFilesReadOnly())
      acquireLock(this.database.getPath()); 
    boolean bool4 = this.database.databaseProperties.isVersion18();
    if (bool4) {
      this.database.setUniqueName(newUniqueName());
      this.database.schemaManager.createPublicSchema();
      HsqlNameManager.HsqlName hsqlName = this.database.schemaManager.findSchemaHsqlName("PUBLIC");
      this.database.schemaManager.setDefaultSchemaHsqlName(hsqlName);
    } 
    this.log = new Log(this.database);
    this.log.open();
    this.logsStatements = true;
    this.loggingEnabled = (this.propLogData && !this.database.isFilesReadOnly());
    if (bool4)
      checkpoint(false); 
    if (this.database.getUniqueName() == null)
      this.database.setUniqueName(newUniqueName()); 
    int i = this.database.urlProperties.getIntegerProperty("hsqldb.applog", -1);
    if (i >= 0)
      setEventLogLevel(i, false); 
    i = this.database.urlProperties.getIntegerProperty("hsqldb.sqllog", -1);
    if (i >= 0)
      setEventLogLevel(i, true); 
  }
  
  private void setVariables() {
    String str1 = this.database.urlProperties.getProperty("crypt_key");
    if (str1 != null) {
      String str5 = this.database.urlProperties.getProperty("crypt_type");
      String str6 = this.database.urlProperties.getProperty("crypt_provider");
      this.crypto = new Crypto(str1, str5, str6);
      this.cryptLobs = this.database.urlProperties.isPropertyTrue("crypt_lobs", true);
    } 
    if (this.database.databaseProperties.isPropertyTrue("readonly"))
      this.database.setReadOnly(); 
    if (this.database.databaseProperties.isPropertyTrue("files_readonly"))
      this.database.setFilesReadOnly(); 
    if (!this.database.isFilesReadOnly()) {
      if (this.database.getType() == "mem:" || this.isStoredFileAccess) {
        this.tempDirectoryPath = this.database.getProperties().getStringProperty("hsqldb.temp_directory");
      } else {
        this.tempDirectoryPath = this.database.getPath() + ".tmp";
      } 
      if (this.tempDirectoryPath != null)
        this.tempDirectoryPath = FileUtil.makeDirectories(this.tempDirectoryPath); 
    } 
    this.propScriptFormat = this.database.databaseProperties.getIntegerProperty("hsqldb.script_format");
    boolean bool = this.database.databaseProperties.isVersion18();
    this.propMaxFreeBlocks = this.database.databaseProperties.getIntegerProperty("hsqldb.cache_free_count");
    this.propMaxFreeBlocks = ArrayUtil.getTwoPowerFloor(this.propMaxFreeBlocks);
    if (this.database.urlProperties.isPropertyTrue("hsqldb.large_data", false))
      this.propLargeData = true; 
    this.propCheckPersistence = this.database.databaseProperties.getIntegerProperty("hsqldb.files_check");
    if (!this.database.urlProperties.isPropertyTrue("sql.pad_space", true))
      this.database.collation.setPadding(false); 
    if (bool && this.isStoredFileAccess)
      this.database.collation.setPadding(false); 
    if (!this.isNewDatabase && !bool)
      return; 
    if (this.database.urlProperties.isPropertyTrue("hsqldb.files_space", false))
      this.propFileSpaces = true; 
    if (this.tempDirectoryPath != null) {
      int i = this.database.databaseProperties.getIntegerProperty("hsqldb.result_max_memory_rows");
      this.database.setResultMaxMemoryRows(i);
    } 
    String str2 = this.database.databaseProperties.getStringProperty("hsqldb.default_table_type");
    if ("CACHED".equalsIgnoreCase(str2))
      this.database.schemaManager.setDefaultTableType(5); 
    String str3 = this.database.databaseProperties.getStringProperty("hsqldb.tx");
    if ("MVCC".equalsIgnoreCase(str3)) {
      this.propTxMode = 2;
    } else if ("MVLOCKS".equalsIgnoreCase(str3)) {
      this.propTxMode = 1;
    } else if ("LOCKS".equalsIgnoreCase(str3)) {
      this.propTxMode = 0;
    } 
    switch (this.propTxMode) {
      case 1:
        this.database.txManager = (TransactionManager)new TransactionManagerMV2PL(this.database);
        break;
      case 2:
        this.database.txManager = (TransactionManager)new TransactionManagerMVCC(this.database);
        break;
    } 
    String str4 = this.database.databaseProperties.getStringProperty("hsqldb.tx_level");
    if ("SERIALIZABLE".equalsIgnoreCase(str4)) {
      this.database.defaultIsolationLevel = 8;
    } else {
      this.database.defaultIsolationLevel = 2;
    } 
    this.database.txConflictRollback = this.database.databaseProperties.isPropertyTrue("hsqldb.tx_conflict_rollback");
    this.database.sqlEnforceNames = this.database.databaseProperties.isPropertyTrue("sql.enforce_names");
    this.database.sqlRegularNames = this.database.databaseProperties.isPropertyTrue("sql.regular_names");
    this.database.sqlEnforceRefs = this.database.databaseProperties.isPropertyTrue("sql.enforce_refs");
    this.database.sqlEnforceSize = this.database.databaseProperties.isPropertyTrue("sql.enforce_size");
    this.database.sqlEnforceTypes = this.database.databaseProperties.isPropertyTrue("sql.enforce_types");
    this.database.sqlEnforceTDCD = this.database.databaseProperties.isPropertyTrue("sql.enforce_tdc_delete");
    this.database.sqlEnforceTDCU = this.database.databaseProperties.isPropertyTrue("sql.enforce_tdc_update");
    this.database.sqlTranslateTTI = this.database.databaseProperties.isPropertyTrue("jdbc.translate_tti_types");
    this.database.sqlConcatNulls = this.database.databaseProperties.isPropertyTrue("sql.concat_nulls");
    this.database.sqlNullsFirst = this.database.databaseProperties.isPropertyTrue("sql.nulls_first");
    this.database.sqlNullsOrder = this.database.databaseProperties.isPropertyTrue("sql.nulls_order");
    this.database.sqlUniqueNulls = this.database.databaseProperties.isPropertyTrue("sql.unique_nulls");
    this.database.sqlConvertTruncate = this.database.databaseProperties.isPropertyTrue("sql.convert_trunc");
    this.database.sqlAvgScale = this.database.databaseProperties.getIntegerProperty("sql.avg_scale");
    this.database.sqlDoubleNaN = this.database.databaseProperties.isPropertyTrue("sql.double_nan");
    this.database.sqlLongvarIsLob = this.database.databaseProperties.isPropertyTrue("sql.longvar_is_lob");
    this.database.sqlIgnoreCase = this.database.databaseProperties.isPropertyTrue("sql.ignore_case");
    this.database.sqlSyntaxDb2 = this.database.databaseProperties.isPropertyTrue("sql.syntax_db2");
    this.database.sqlSyntaxMss = this.database.databaseProperties.isPropertyTrue("sql.syntax_mss");
    this.database.sqlSyntaxMys = this.database.databaseProperties.isPropertyTrue("sql.syntax_mys");
    this.database.sqlSyntaxOra = this.database.databaseProperties.isPropertyTrue("sql.syntax_ora");
    this.database.sqlSyntaxPgs = this.database.databaseProperties.isPropertyTrue("sql.syntax_pgs");
    if (this.database.databaseProperties.isPropertyTrue("sql.compare_in_locale"))
      this.database.collation.setCollationAsLocale(); 
    this.propEventLogLevel = this.database.databaseProperties.getIntegerProperty("hsqldb.applog");
    this.propSqlLogLevel = this.database.databaseProperties.getIntegerProperty("hsqldb.sqllog");
    if (this.database.databaseProperties.isPropertyTrue("files_readonly"))
      this.database.setFilesReadOnly(); 
    if (this.database.databaseProperties.isPropertyTrue("readonly"))
      this.database.setReadOnly(); 
    this.propIncrementBackup = this.database.databaseProperties.isPropertyTrue("hsqldb.inc_backup");
    this.propNioDataFile = this.database.databaseProperties.isPropertyTrue("hsqldb.nio_data_file");
    this.propNioMaxSize = (this.database.databaseProperties.getIntegerProperty("hsqldb.nio_max_size") * 1024) * 1024L;
    this.propCacheMaxRows = this.database.databaseProperties.getIntegerProperty("hsqldb.cache_rows");
    this.propCacheMaxSize = this.database.databaseProperties.getIntegerProperty("hsqldb.cache_size") * 1024;
    setLobFileScaleNoCheck(this.database.databaseProperties.getIntegerProperty("hsqldb.lob_file_scale"));
    setDataFileScaleNoCheck(this.database.databaseProperties.getIntegerProperty("hsqldb.cache_file_scale"));
    this.propCacheDefragLimit = this.database.databaseProperties.getIntegerProperty("hsqldb.defrag_limit");
    this.propWriteDelay = this.database.databaseProperties.getIntegerProperty("hsqldb.write_delay_millis");
    if (!this.database.databaseProperties.isPropertyTrue("hsqldb.write_delay"))
      this.propWriteDelay = 0; 
    this.propLogSize = this.database.databaseProperties.getIntegerProperty("hsqldb.log_size");
    this.propLogData = this.database.databaseProperties.isPropertyTrue("hsqldb.log_data");
    this.propGC = this.database.databaseProperties.getIntegerProperty("runtime.gc_interval");
    this.propRefIntegrity = this.database.databaseProperties.isPropertyTrue("sql.ref_integrity");
  }
  
  public boolean close(int paramInt) {
    boolean bool = true;
    if (this.log == null) {
      closeAllTextCaches(false);
      return true;
    } 
    this.log.synchLog();
    this.database.lobManager.synch();
    try {
      switch (paramInt) {
        case 1:
          this.log.shutdown();
          break;
        case 2:
          this.log.close(false);
          break;
        case 3:
        case 4:
          this.log.close(true);
          break;
      } 
      this.database.persistentStoreCollection.release();
    } catch (Throwable throwable) {
      this.database.logger.logSevereEvent("error closing log", throwable);
      bool = false;
    } 
    logInfoEvent("Database closed");
    this.log = null;
    this.appLog.close();
    this.sqlLog.close();
    this.logsStatements = false;
    this.loggingEnabled = false;
    return bool;
  }
  
  String newUniqueName() {
    null = StringUtil.toPaddedString(Long.toHexString(System.currentTimeMillis()), 16, '0', false);
    return "HSQLDB" + null.substring(6).toUpperCase(Locale.ENGLISH);
  }
  
  public boolean isLogged() {
    return (this.propIsFileDatabase && !this.database.isFilesReadOnly());
  }
  
  public boolean isAllowedFullPath() {
    return this.propTextAllowFullPath;
  }
  
  private void getEventLogger() {
    if (this.fwLogger != null)
      return; 
    String str = this.database.getUniqueName();
    if (str == null)
      return; 
    this.fwLogger = FrameworkLogger.getLog("ENGINE", "hsqldb.db." + this.database.getUniqueName());
  }
  
  public void setEventLogLevel(int paramInt, boolean paramBoolean) {
    if (paramInt < 0 || paramInt > 4)
      throw Error.error(5556); 
    if (paramBoolean) {
      this.propSqlLogLevel = paramInt;
      this.sqlLog.setLevel(paramInt);
    } else {
      if (paramInt > 3)
        paramInt = 3; 
      this.propEventLogLevel = paramInt;
      this.appLog.setLevel(paramInt);
    } 
  }
  
  public void logSevereEvent(String paramString, Throwable paramThrowable) {
    getEventLogger();
    if (this.fwLogger != null)
      this.fwLogger.severe(paramString, paramThrowable); 
    if (this.appLog != null)
      if (paramThrowable == null) {
        this.appLog.logContext(1, paramString);
      } else {
        this.appLog.logContext(paramThrowable, paramString, 1);
      }  
  }
  
  public void logWarningEvent(String paramString, Throwable paramThrowable) {
    getEventLogger();
    if (this.fwLogger != null)
      this.fwLogger.warning(paramString, paramThrowable); 
    this.appLog.logContext(paramThrowable, paramString, 1);
  }
  
  public void logInfoEvent(String paramString) {
    getEventLogger();
    if (this.fwLogger != null)
      this.fwLogger.info(paramString); 
    this.appLog.logContext(2, paramString);
  }
  
  public void logDetailEvent(String paramString) {
    getEventLogger();
    if (this.fwLogger != null)
      this.fwLogger.finest(paramString); 
    if (this.appLog != null)
      this.appLog.logContext(3, paramString); 
  }
  
  public void logStatementEvent(Session paramSession, Statement paramStatement, Object[] paramArrayOfObject, Result paramResult, int paramInt) {
    if (this.sqlLog != null && paramInt <= this.propSqlLogLevel) {
      String str1 = Long.toString(paramSession.getId());
      String str2 = paramStatement.getSQL();
      String str3 = "";
      byte b = 0;
      if (this.propSqlLogLevel < 3) {
        if (str2.length() > 256)
          str2 = str2.substring(0, 256); 
        b = 32;
      } 
      if (paramArrayOfObject != null && paramArrayOfObject.length > 0)
        str3 = RowType.convertToSQLString(paramArrayOfObject, paramStatement.getParametersMetaData().getParameterTypes(), b); 
      if (this.propSqlLogLevel == 4) {
        StringBuffer stringBuffer = new StringBuffer(str3);
        stringBuffer.append(' ').append('[');
        if (paramResult.isError()) {
          stringBuffer.append(paramResult.getErrorCode());
        } else if (paramResult.isData()) {
          stringBuffer.append(paramResult.getNavigator().getSize());
        } else if (paramResult.isUpdateCount()) {
          stringBuffer.append(paramResult.getUpdateCount());
        } 
        stringBuffer.append(']');
        str3 = stringBuffer.toString();
      } 
      this.sqlLog.logContext(paramInt, str1, str2, str3);
    } 
  }
  
  public int getSqlEventLogLevel() {
    return this.propSqlLogLevel;
  }
  
  public DataFileCache getCache() {
    return (this.log == null) ? null : this.log.getCache();
  }
  
  private boolean hasCache() {
    return (this.log == null) ? false : this.log.hasCache();
  }
  
  public synchronized void writeOtherStatement(Session paramSession, String paramString) {
    if (this.loggingEnabled)
      this.log.writeOtherStatement(paramSession, paramString); 
  }
  
  public synchronized void writeInsertStatement(Session paramSession, Row paramRow, Table paramTable) {
    if (this.loggingEnabled)
      this.log.writeInsertStatement(paramSession, paramRow, paramTable); 
  }
  
  public synchronized void writeDeleteStatement(Session paramSession, Table paramTable, Object[] paramArrayOfObject) {
    if (this.loggingEnabled)
      this.log.writeDeleteStatement(paramSession, paramTable, paramArrayOfObject); 
  }
  
  public synchronized void writeSequenceStatement(Session paramSession, NumberSequence paramNumberSequence) {
    if (this.loggingEnabled)
      this.log.writeSequenceStatement(paramSession, paramNumberSequence); 
  }
  
  public synchronized void writeCommitStatement(Session paramSession) {
    if (this.loggingEnabled)
      this.log.writeCommitStatement(paramSession); 
  }
  
  public synchronized void synchLog() {
    if (this.loggingEnabled)
      this.log.synchLog(); 
  }
  
  public synchronized void checkpoint(boolean paramBoolean) {
    if (!this.backupState.compareAndSet(0, 2))
      throw Error.error(457); 
    try {
      checkpointInternal(paramBoolean);
    } finally {
      this.backupState.set(0);
    } 
  }
  
  void checkpointInternal(boolean paramBoolean) {
    if (this.logsStatements) {
      logInfoEvent("Checkpoint start");
      this.log.checkpoint(paramBoolean);
      logInfoEvent("Checkpoint end - txts: " + this.database.txManager.getGlobalChangeTimestamp());
    } else if (!isFileDatabase()) {
      this.database.lobManager.deleteUnusedLobs();
    } 
    this.checkpointRequired = false;
    this.checkpointDue = false;
  }
  
  public synchronized void setLogSize(int paramInt) {
    this.propLogSize = paramInt;
    if (this.log != null)
      this.log.setLogSize(this.propLogSize); 
  }
  
  public synchronized void setLogData(boolean paramBoolean) {
    this.propLogData = paramBoolean;
    this.loggingEnabled = (this.propLogData && !this.database.isFilesReadOnly());
    this.loggingEnabled &= this.logsStatements;
  }
  
  public synchronized void setScriptType(int paramInt) {
    if (paramInt == this.propScriptFormat)
      return; 
    this.propScriptFormat = paramInt;
    this.checkpointRequired = true;
  }
  
  public synchronized void setWriteDelay(int paramInt) {
    this.propWriteDelay = paramInt;
    if (this.log != null) {
      this.syncFile = (paramInt == 0);
      this.log.setWriteDelay(paramInt);
    } 
  }
  
  public Crypto getCrypto() {
    return this.crypto;
  }
  
  public int getWriteDelay() {
    return this.propWriteDelay;
  }
  
  public int getLogSize() {
    return this.propLogSize;
  }
  
  public int getLobBlockSize() {
    return this.propLobBlockSize;
  }
  
  public synchronized void setIncrementBackup(boolean paramBoolean) {
    if (paramBoolean == this.propIncrementBackup)
      return; 
    if (this.log != null) {
      this.log.setIncrementBackup(paramBoolean);
      if (this.log.hasCache())
        this.checkpointRequired = true; 
    } 
    this.propIncrementBackup = paramBoolean;
  }
  
  public void setCacheMaxRows(int paramInt) {
    this.propCacheMaxRows = paramInt;
  }
  
  public int getCacheRowsDefault() {
    return this.propCacheMaxRows;
  }
  
  public void setCacheSize(int paramInt) {
    this.propCacheMaxSize = paramInt * 1024;
  }
  
  public int getCacheSize() {
    return this.propCacheMaxSize;
  }
  
  public void setDataFileScale(int paramInt) {
    if (this.propDataFileScale == paramInt)
      return; 
    checkPower(paramInt, 10);
    if (paramInt < 8 && paramInt != 1)
      throw Error.error(5556); 
    if (hasCache())
      throw Error.error(469); 
    this.propDataFileScale = paramInt;
  }
  
  public void setDataFileScaleNoCheck(int paramInt) {
    checkPower(paramInt, 10);
    if (paramInt < 8 && paramInt != 1)
      throw Error.error(5556); 
    this.propDataFileScale = paramInt;
  }
  
  public int getDataFileScale() {
    return this.propDataFileScale;
  }
  
  public int getDataFileFactor() {
    return this.propLargeData ? 128 : 1;
  }
  
  public void setDataFileSpaces(boolean paramBoolean) {
    this.propFileSpaces = paramBoolean;
    if (hasCache()) {
      DataFileCache dataFileCache = getCache();
      boolean bool = dataFileCache.setTableSpaceManager(paramBoolean);
      if (!bool)
        return; 
      this.database.persistentStoreCollection.setNewTableSpaces();
    } 
  }
  
  public boolean isDataFileSpaces() {
    return this.propFileSpaces;
  }
  
  public void setFilesCheck(int paramInt) {
    if (paramInt == 1 || paramInt == 0)
      this.propCheckPersistence = paramInt; 
  }
  
  public void setLobFileScale(int paramInt) {
    if (this.propLobBlockSize == paramInt * 1024)
      return; 
    checkPower(paramInt, 5);
    if (this.database.lobManager.getLobCount() > 0)
      throw Error.error(469); 
    this.propLobBlockSize = paramInt * 1024;
    this.database.lobManager.close();
    this.database.lobManager.open();
  }
  
  public void setLobFileScaleNoCheck(int paramInt) {
    checkPower(paramInt, 5);
    this.propLobBlockSize = paramInt * 1024;
  }
  
  public int getLobFileScale() {
    return this.propLobBlockSize / 1024;
  }
  
  public void setLobFileCompressed(boolean paramBoolean) {
    if (this.propCompressLobs == paramBoolean)
      return; 
    if (this.database.lobManager.getLobCount() > 0)
      throw Error.error(469); 
    this.propCompressLobs = paramBoolean;
    this.database.lobManager.close();
    this.database.lobManager.open();
  }
  
  public void setLobFileCompressedNoCheck(boolean paramBoolean) {
    this.propCompressLobs = paramBoolean;
  }
  
  public void setDefagLimit(int paramInt) {
    this.propCacheDefragLimit = paramInt;
  }
  
  public int getDefragLimit() {
    return this.propCacheDefragLimit;
  }
  
  public void setDefaultTextTableProperties(String paramString, HsqlProperties paramHsqlProperties) {
    paramHsqlProperties.setProperty("check_props", true);
    this.database.getProperties().setURLProperties(paramHsqlProperties);
    this.propTextSourceDefault = paramString;
  }
  
  public void setNioDataFile(boolean paramBoolean) {
    this.propNioDataFile = paramBoolean;
  }
  
  public void setNioMaxSize(int paramInt) {
    if (paramInt < 8)
      throw Error.error(5556); 
    if (!ArrayUtil.isTwoPower(paramInt, 10) && (paramInt < 1024 || paramInt % 512 != 0))
      throw Error.error(5556); 
    this.propNioMaxSize = paramInt * 1024L * 1024L;
  }
  
  public FileAccess getFileAccess() {
    return this.fileAccess;
  }
  
  public boolean isStoredFileAccess() {
    return this.isStoredFileAccess;
  }
  
  public boolean isNewStoredFileAccess() {
    return this.isNewStoredFileAccess;
  }
  
  public boolean isFileDatabase() {
    return this.propIsFileDatabase;
  }
  
  public String getTempDirectoryPath() {
    return this.tempDirectoryPath;
  }
  
  static void checkPower(int paramInt1, int paramInt2) {
    if (!ArrayUtil.isTwoPower(paramInt1, paramInt2))
      throw Error.error(5556); 
  }
  
  public synchronized void setCheckpointRequired() {
    this.checkpointRequired = true;
  }
  
  public synchronized boolean needsCheckpointReset() {
    if (this.checkpointRequired && !this.checkpointDue && !this.checkpointDisabled) {
      this.checkpointDue = true;
      this.checkpointRequired = false;
      return true;
    } 
    return false;
  }
  
  public boolean hasLockFile() {
    return (this.lockFile != null);
  }
  
  public void acquireLock(String paramString) {
    if (this.lockFile != null)
      return; 
    this.lockFile = LockFile.newLockFileLock(paramString);
  }
  
  public void releaseLock() {
    try {
      if (this.lockFile != null)
        this.lockFile.tryRelease(); 
    } catch (Exception exception) {}
    this.lockFile = null;
  }
  
  public PersistentStore newStore(Session paramSession, PersistentStoreCollection paramPersistentStoreCollection, TableBase paramTableBase) {
    DataFileCache dataFileCache;
    switch (paramTableBase.getTableType()) {
      case 5:
        dataFileCache = getCache();
        if (dataFileCache == null)
          break; 
        return new RowStoreAVLDisk(paramPersistentStoreCollection, dataFileCache, (Table)paramTableBase);
      case 4:
      case 12:
        return new RowStoreAVLMemory(paramPersistentStoreCollection, (Table)paramTableBase);
      case 7:
        return new RowStoreAVLDiskData(paramPersistentStoreCollection, (Table)paramTableBase);
      case 1:
        return new RowStoreAVLHybridExtended(paramSession, paramPersistentStoreCollection, paramTableBase, false);
      case 3:
        return new RowStoreAVLHybridExtended(paramSession, paramPersistentStoreCollection, paramTableBase, true);
      case 13:
        return new RowStoreDataChange(paramSession, paramPersistentStoreCollection, paramTableBase);
      case 2:
      case 8:
      case 9:
      case 10:
      case 11:
        return (paramSession == null) ? null : new RowStoreAVLHybrid(paramSession, paramPersistentStoreCollection, paramTableBase, true);
    } 
    throw Error.runtimeError(201, "Logger");
  }
  
  public Index newIndex(HsqlNameManager.HsqlName paramHsqlName, long paramLong, TableBase paramTableBase, int[] paramArrayOfint, boolean[] paramArrayOfboolean1, boolean[] paramArrayOfboolean2, Type[] paramArrayOfType, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    switch (paramTableBase.getTableType()) {
      case 1:
      case 4:
      case 12:
        return (Index)new IndexAVLMemory(paramHsqlName, paramLong, paramTableBase, paramArrayOfint, paramArrayOfboolean1, paramArrayOfboolean2, paramArrayOfType, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
      case 2:
      case 3:
      case 5:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 13:
        return (Index)new IndexAVL(paramHsqlName, paramLong, paramTableBase, paramArrayOfint, paramArrayOfboolean1, paramArrayOfboolean2, paramArrayOfType, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
    } 
    throw Error.runtimeError(201, "Logger");
  }
  
  public Index newIndex(Table paramTable, Index paramIndex, int[] paramArrayOfint) {
    boolean[] arrayOfBoolean = new boolean[paramArrayOfint.length];
    Type[] arrayOfType = new Type[paramArrayOfint.length];
    ArrayUtil.projectRow((Object[])paramTable.getColumnTypes(), paramArrayOfint, (Object[])arrayOfType);
    return newIndex(paramIndex.getName(), paramIndex.getPersistenceId(), (TableBase)paramTable, paramArrayOfint, arrayOfBoolean, arrayOfBoolean, arrayOfType, false, false, false, false);
  }
  
  public String getValueStringForProperty(String paramString) {
    String str = "";
    if ("hsqldb.tx".equals(paramString)) {
      switch (this.database.txManager.getTransactionControl()) {
        case 2:
          str = "MVCC".toLowerCase();
          break;
        case 1:
          str = "MVLOCKS".toLowerCase();
          break;
        case 0:
          str = "LOCKS".toLowerCase();
          break;
      } 
      return str;
    } 
    if ("hsqldb.tx_level".equals(paramString)) {
      switch (this.database.defaultIsolationLevel) {
        case 2:
          str = ("READ" + ' ' + "COMMITTED").toLowerCase();
          break;
        case 8:
          str = "SERIALIZABLE".toLowerCase();
          break;
      } 
      return str;
    } 
    return "hsqldb.applog".equals(paramString) ? String.valueOf(this.appLog.getLevel()) : ("hsqldb.sqllog".equals(paramString) ? String.valueOf(this.sqlLog.getLevel()) : ("hsqldb.lob_file_scale".equals(paramString) ? String.valueOf(this.propLobBlockSize / 1024) : ("hsqldb.lob_compressed".equals(paramString) ? String.valueOf(this.propCompressLobs) : ("hsqldb.cache_file_scale".equals(paramString) ? String.valueOf(this.propDataFileScale) : ("hsqldb.cache_free_count".equals(paramString) ? String.valueOf(this.propMaxFreeBlocks) : ("hsqldb.cache_rows".equals(paramString) ? String.valueOf(this.propCacheMaxRows) : ("hsqldb.cache_size".equals(paramString) ? String.valueOf(this.propCacheMaxSize / 1024) : ("hsqldb.default_table_type".equals(paramString) ? ((this.database.schemaManager.getDefaultTableType() == 5) ? "cached" : "memory") : ("hsqldb.defrag_limit".equals(paramString) ? String.valueOf(this.propCacheDefragLimit) : ("hsqldb.files_check".equals(paramString) ? String.valueOf(this.propCheckPersistence) : ("hsqldb.files_space".equals(paramString) ? String.valueOf(this.propFileSpaces) : ("files_readonly".equals(paramString) ? this.database.databaseProperties.getPropertyString("files_readonly") : ("hsqldb.inc_backup".equals(paramString) ? String.valueOf(this.propIncrementBackup) : ("hsqldb.large_data".equals(paramString) ? String.valueOf(this.propLargeData) : ("hsqldb.large_data".equals(paramString) ? String.valueOf(this.propLargeData) : ("hsqldb.lock_file".equals(paramString) ? this.database.databaseProperties.getPropertyString("hsqldb.lock_file") : ("hsqldb.log_data".equals(paramString) ? String.valueOf(this.propLogData) : ("hsqldb.log_size".equals(paramString) ? String.valueOf(this.propLogSize) : ("hsqldb.nio_data_file".equals(paramString) ? String.valueOf(this.propNioDataFile) : ("hsqldb.nio_max_size".equals(paramString) ? String.valueOf(this.propNioMaxSize / 1048576L) : ("hsqldb.script_format".equals(paramString) ? ScriptWriterBase.LIST_SCRIPT_FORMATS[0].toLowerCase() : ("hsqldb.temp_directory".equals(paramString) ? this.tempDirectoryPath : ("hsqldb.tx_conflict_rollback".equals(paramString) ? String.valueOf(this.database.txConflictRollback) : ("hsqldb.result_max_memory_rows".equals(paramString) ? String.valueOf(this.database.getResultMaxMemoryRows()) : ("hsqldb.write_delay".equals(paramString) ? String.valueOf((this.propWriteDelay != 0)) : ("hsqldb.write_delay_millis".equals(paramString) ? String.valueOf(this.propWriteDelay) : ("sql.avg_scale".equals(paramString) ? String.valueOf(this.database.sqlAvgScale) : ("sql.concat_nulls".equals(paramString) ? String.valueOf(this.database.sqlConcatNulls) : ("sql.convert_trunc".equals(paramString) ? String.valueOf(this.database.sqlConvertTruncate) : ("sql.double_nan".equals(paramString) ? String.valueOf(this.database.sqlDoubleNaN) : ("sql.enforce_names".equals(paramString) ? String.valueOf(this.database.sqlEnforceNames) : ("sql.enforce_refs".equals(paramString) ? String.valueOf(this.database.sqlEnforceRefs) : ("sql.enforce_size".equals(paramString) ? String.valueOf(this.database.sqlEnforceSize) : ("sql.enforce_tdc_delete".equals(paramString) ? String.valueOf(this.database.sqlEnforceTDCD) : ("sql.enforce_tdc_update".equals(paramString) ? String.valueOf(this.database.sqlEnforceTDCU) : ("sql.enforce_types".equals(paramString) ? String.valueOf(this.database.sqlEnforceTypes) : ("sql.ignore_case".equals(paramString) ? String.valueOf(this.database.sqlIgnoreCase) : ("sql.longvar_is_lob".equals(paramString) ? String.valueOf(this.database.sqlLongvarIsLob) : ("sql.nulls_first".equals(paramString) ? String.valueOf(this.database.sqlNullsFirst) : ("sql.nulls_order".equals(paramString) ? String.valueOf(this.database.sqlNullsOrder) : ("sql.syntax_db2".equals(paramString) ? String.valueOf(this.database.sqlSyntaxDb2) : ("sql.syntax_mss".equals(paramString) ? String.valueOf(this.database.sqlSyntaxMss) : ("sql.syntax_mys".equals(paramString) ? String.valueOf(this.database.sqlSyntaxMys) : ("sql.syntax_ora".equals(paramString) ? String.valueOf(this.database.sqlSyntaxOra) : ("sql.syntax_pgs".equals(paramString) ? String.valueOf(this.database.sqlSyntaxPgs) : ("sql.ref_integrity".equals(paramString) ? String.valueOf(this.database.isReferentialIntegrity()) : ("sql.regular_names".equals(paramString) ? String.valueOf(this.database.sqlRegularNames) : ("sql.unique_nulls".equals(paramString) ? String.valueOf(this.database.sqlUniqueNulls) : ("jdbc.translate_tti_types".equals(paramString) ? String.valueOf(this.database.sqlTranslateTTI) : null)))))))))))))))))))))))))))))))))))))))))))))))));
  }
  
  public String[] getPropertiesSQL(boolean paramBoolean) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("SET DATABASE ").append("UNIQUE").append(' ');
    stringBuffer.append("NAME").append(' ').append(this.database.getUniqueName());
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("GC").append(' ');
    stringBuffer.append(this.propGC);
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("DEFAULT").append(' ');
    stringBuffer.append("RESULT").append(' ').append("MEMORY");
    stringBuffer.append(' ').append("ROWS").append(' ');
    stringBuffer.append(this.database.getResultMaxMemoryRows());
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("EVENT").append(' ');
    stringBuffer.append("LOG").append(' ').append("LEVEL");
    stringBuffer.append(' ').append(this.propEventLogLevel);
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    if (this.propSqlLogLevel != 0) {
      stringBuffer.append("SET DATABASE ").append("EVENT").append(' ');
      stringBuffer.append("LOG").append(' ').append("SQL");
      stringBuffer.append(' ').append("LEVEL");
      stringBuffer.append(' ').append(this.propEventLogLevel);
      hsqlArrayList.add(stringBuffer.toString());
      stringBuffer.setLength(0);
    } 
    stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
    stringBuffer.append("NAMES").append(' ');
    stringBuffer.append(this.database.sqlEnforceNames ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    if (!this.database.sqlRegularNames) {
      stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
      stringBuffer.append("REGULAR").append(' ');
      stringBuffer.append("NAMES").append(' ');
      stringBuffer.append(this.database.sqlRegularNames ? "TRUE" : "FALSE");
      hsqlArrayList.add(stringBuffer.toString());
      stringBuffer.setLength(0);
    } 
    stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
    stringBuffer.append("REFERENCES").append(' ');
    stringBuffer.append(this.database.sqlEnforceRefs ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
    stringBuffer.append("SIZE").append(' ');
    stringBuffer.append(this.database.sqlEnforceSize ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
    stringBuffer.append("TYPES").append(' ');
    stringBuffer.append(this.database.sqlEnforceTypes ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
    stringBuffer.append("TDC").append(' ');
    stringBuffer.append("DELETE").append(' ');
    stringBuffer.append(this.database.sqlEnforceTDCD ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
    stringBuffer.append("TDC").append(' ');
    stringBuffer.append("UPDATE").append(' ');
    stringBuffer.append(this.database.sqlEnforceTDCU ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
    stringBuffer.append("TRANSLATE").append(' ').append("TTI");
    stringBuffer.append(' ').append("TYPES").append(' ');
    stringBuffer.append(this.database.sqlTranslateTTI ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
    stringBuffer.append("CONCAT").append(' ');
    stringBuffer.append("NULLS").append(' ');
    stringBuffer.append(this.database.sqlConcatNulls ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    if (!this.database.sqlNullsFirst) {
      stringBuffer.setLength(0);
      stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
      stringBuffer.append("NULLS").append(' ');
      stringBuffer.append("FIRST").append(' ');
      stringBuffer.append(this.database.sqlNullsFirst ? "TRUE" : "FALSE");
      hsqlArrayList.add(stringBuffer.toString());
    } 
    if (!this.database.sqlNullsOrder) {
      stringBuffer.setLength(0);
      stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
      stringBuffer.append("NULLS").append(' ');
      stringBuffer.append("ORDER").append(' ');
      stringBuffer.append(this.database.sqlNullsOrder ? "TRUE" : "FALSE");
      hsqlArrayList.add(stringBuffer.toString());
    } 
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
    stringBuffer.append("UNIQUE").append(' ');
    stringBuffer.append("NULLS").append(' ');
    stringBuffer.append(this.database.sqlUniqueNulls ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
    stringBuffer.append("CONVERT").append(' ');
    stringBuffer.append("TRUNCATE").append(' ');
    stringBuffer.append(this.database.sqlConvertTruncate ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
    stringBuffer.append("AVG").append(' ');
    stringBuffer.append("SCALE").append(' ');
    stringBuffer.append(this.database.sqlAvgScale);
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
    stringBuffer.append("DOUBLE").append(' ');
    stringBuffer.append("NAN").append(' ');
    stringBuffer.append(this.database.sqlDoubleNaN ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    if (this.database.sqlLongvarIsLob) {
      stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
      stringBuffer.append("LONGVAR").append(' ');
      stringBuffer.append("IS").append(' ');
      stringBuffer.append("LOB").append(' ');
      stringBuffer.append(this.database.sqlLongvarIsLob ? "TRUE" : "FALSE");
      hsqlArrayList.add(stringBuffer.toString());
      stringBuffer.setLength(0);
    } 
    if (this.database.sqlIgnoreCase) {
      stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
      stringBuffer.append("IGNORECASE").append(' ');
      stringBuffer.append(this.database.sqlIgnoreCase ? "TRUE" : "FALSE");
      hsqlArrayList.add(stringBuffer.toString());
      stringBuffer.setLength(0);
    } 
    if (this.database.sqlSyntaxDb2) {
      stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
      stringBuffer.append("SYNTAX").append(' ');
      stringBuffer.append("DB2").append(' ');
      stringBuffer.append(this.database.sqlSyntaxOra ? "TRUE" : "FALSE");
      hsqlArrayList.add(stringBuffer.toString());
      stringBuffer.setLength(0);
    } 
    if (this.database.sqlSyntaxMss) {
      stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
      stringBuffer.append("SYNTAX").append(' ');
      stringBuffer.append("MSS").append(' ');
      stringBuffer.append(this.database.sqlSyntaxMss ? "TRUE" : "FALSE");
      hsqlArrayList.add(stringBuffer.toString());
      stringBuffer.setLength(0);
    } 
    if (this.database.sqlSyntaxMys) {
      stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
      stringBuffer.append("SYNTAX").append(' ');
      stringBuffer.append("MYS").append(' ');
      stringBuffer.append(this.database.sqlSyntaxMys ? "TRUE" : "FALSE");
      hsqlArrayList.add(stringBuffer.toString());
      stringBuffer.setLength(0);
    } 
    if (this.database.sqlSyntaxOra) {
      stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
      stringBuffer.append("SYNTAX").append(' ');
      stringBuffer.append("ORA").append(' ');
      stringBuffer.append(this.database.sqlSyntaxOra ? "TRUE" : "FALSE");
      hsqlArrayList.add(stringBuffer.toString());
      stringBuffer.setLength(0);
    } 
    if (this.database.sqlSyntaxPgs) {
      stringBuffer.append("SET DATABASE ").append("SQL").append(' ');
      stringBuffer.append("SYNTAX").append(' ');
      stringBuffer.append("PGS").append(' ');
      stringBuffer.append(this.database.sqlSyntaxPgs ? "TRUE" : "FALSE");
      hsqlArrayList.add(stringBuffer.toString());
      stringBuffer.setLength(0);
    } 
    stringBuffer.append("SET DATABASE ").append("TRANSACTION");
    stringBuffer.append(' ').append("CONTROL").append(' ');
    switch (this.database.txManager.getTransactionControl()) {
      case 2:
        stringBuffer.append("MVCC");
        break;
      case 1:
        stringBuffer.append("MVLOCKS");
        break;
      case 0:
        stringBuffer.append("LOCKS");
        break;
    } 
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("DEFAULT").append(' ');
    stringBuffer.append("ISOLATION").append(' ').append("LEVEL");
    stringBuffer.append(' ');
    switch (this.database.defaultIsolationLevel) {
      case 2:
        stringBuffer.append("READ").append(' ').append("COMMITTED");
        break;
      case 8:
        stringBuffer.append("SERIALIZABLE");
        break;
    } 
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("TRANSACTION");
    stringBuffer.append(' ').append("ROLLBACK").append(' ');
    stringBuffer.append("ON").append(' ');
    stringBuffer.append("CONFLICT").append(' ');
    stringBuffer.append(this.database.txConflictRollback ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET DATABASE ").append("TEXT").append(' ');
    stringBuffer.append("TABLE").append(' ').append("DEFAULTS");
    stringBuffer.append(' ').append('\'');
    stringBuffer.append(this.propTextSourceDefault).append('\'');
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    if (this.database.schemaManager.getDefaultTableType() == 5)
      hsqlArrayList.add("SET DATABASE DEFAULT TABLE TYPE CACHED"); 
    int i = this.propWriteDelay;
    boolean bool = (i > 0 && i < 1000) ? true : false;
    if (bool) {
      if (i < 20)
        i = 20; 
    } else {
      i /= 1000;
    } 
    stringBuffer.setLength(0);
    stringBuffer.append("SET FILES ").append("WRITE").append(' ');
    stringBuffer.append("DELAY").append(' ').append(i);
    if (bool)
      stringBuffer.append(' ').append("MILLIS"); 
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET FILES ").append("BACKUP");
    stringBuffer.append(' ').append("INCREMENT").append(' ');
    stringBuffer.append(this.propIncrementBackup ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET FILES ").append("CACHE");
    stringBuffer.append(' ').append("SIZE").append(' ');
    stringBuffer.append(this.propCacheMaxSize / 1024);
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET FILES ").append("CACHE");
    stringBuffer.append(' ').append("ROWS").append(' ');
    stringBuffer.append(this.propCacheMaxRows);
    hsqlArrayList.add(stringBuffer.toString());
    int j = this.propDataFileScale;
    if (!paramBoolean && j < 32)
      j = 32; 
    stringBuffer.setLength(0);
    stringBuffer.append("SET FILES ").append("SCALE");
    stringBuffer.append(' ').append(j);
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET FILES ").append("LOB").append(' ').append("SCALE");
    stringBuffer.append(' ').append(getLobFileScale());
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    if (this.propCompressLobs) {
      stringBuffer.append("SET FILES ").append("LOB").append(' ').append("COMPRESSED");
      stringBuffer.append(' ').append(this.propCompressLobs ? "TRUE" : "FALSE");
      hsqlArrayList.add(stringBuffer.toString());
      stringBuffer.setLength(0);
    } 
    stringBuffer.append("SET FILES ").append("DEFRAG");
    stringBuffer.append(' ').append(this.propCacheDefragLimit);
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET FILES ").append("NIO");
    stringBuffer.append(' ').append(this.propNioDataFile ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET FILES ").append("NIO").append(' ').append("SIZE");
    stringBuffer.append(' ').append(this.propNioMaxSize / 1048576L);
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET FILES ").append("LOG").append(' ');
    stringBuffer.append(this.propLogData ? "TRUE" : "FALSE");
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    stringBuffer.append("SET FILES ").append("LOG").append(' ');
    stringBuffer.append("SIZE").append(' ').append(this.propLogSize);
    hsqlArrayList.add(stringBuffer.toString());
    stringBuffer.setLength(0);
    if (this.propCheckPersistence != 0) {
      stringBuffer.append("SET FILES ").append("CHECK").append(' ');
      stringBuffer.append(this.propCheckPersistence);
      hsqlArrayList.add(stringBuffer.toString());
      stringBuffer.setLength(0);
    } 
    if (this.propFileSpaces) {
      stringBuffer.append("SET FILES ").append("SPACE").append(' ');
      stringBuffer.append(this.propFileSpaces ? "TRUE" : "FALSE");
      hsqlArrayList.add(stringBuffer.toString());
      stringBuffer.setLength(0);
    } 
    String[] arrayOfString = new String[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public void backup(String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    if (!this.backupState.compareAndSet(0, 1))
      throw Error.error(470, "backup in progress"); 
    try {
      backupInternal(paramString, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
    } finally {
      this.backupState.set(0);
    } 
  }
  
  void backupInternal(String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    File file;
    String str1 = null;
    String str2 = this.database.getPath();
    String str3 = (new File(str2)).getName();
    char c = paramString.charAt(paramString.length() - 1);
    boolean bool = (c == '/' || c == this.runtimeFileDelim.charValue()) ? true : false;
    if (paramBoolean4) {
      if (!bool)
        throw Error.error(null, 462, 0, new String[] { "", "/" }); 
      paramString = getSecurePath(paramString, true, false);
      if (paramString == null)
        throw Error.error(470, "access to directory denied"); 
      file = new File(paramString);
      file.mkdirs();
      File[] arrayOfFile = FileUtil.getDatabaseMainFileList(paramString + str3);
      if (arrayOfFile == null || arrayOfFile.length != 0)
        throw Error.error(470, "files exists in directory"); 
    } else {
      String str = paramBoolean3 ? ".tar.gz" : ".tar";
      if (bool) {
        file = new File(paramString.substring(0, paramString.length() - 1), str3 + '-' + this.backupFileFormat.format(new Date()) + str);
      } else {
        file = new File(paramString);
      } 
      boolean bool1 = (file.getName().endsWith(".tar.gz") || file.getName().endsWith(".tgz"));
      if (!bool1 && !file.getName().endsWith(".tar"))
        throw Error.error(null, 462, 0, new String[] { file.getName(), ".tar, .tar.gz, .tgz" }); 
      if (paramBoolean3 != bool1)
        throw Error.error(null, 463, 0, new Object[] { new Boolean(paramBoolean3), file.getName() }); 
      if (file.exists())
        throw Error.error(null, 470, 0, new Object[] { "file exists", file.getName() }); 
    } 
    if (paramBoolean2)
      this.log.checkpointClose(); 
    try {
      logInfoEvent("Initiating backup of instance '" + str3 + "'");
      if (paramBoolean1) {
        String str = getTempDirectoryPath();
        if (str == null)
          return; 
        str = str + "/" + (new File(this.database.getPath())).getName();
        str1 = str + ".script";
        ScriptWriterText scriptWriterText = new ScriptWriterText(this.database, str1, true, true, true);
        scriptWriterText.writeAll();
        scriptWriterText.close();
        this.backup = new DbBackup(file, str, true);
        this.backup.write();
      } else {
        this.backup = new DbBackup(file, str2);
        this.backup.setAbortUponModify(false);
        if (!paramBoolean2) {
          File file1 = null;
          if (hasCache()) {
            DataFileCache dataFileCache = getCache();
            RAShadowFile rAShadowFile = dataFileCache.getShadowFile();
            if (rAShadowFile == null) {
              this.backup.setFileIgnore(".data");
            } else {
              file1 = new File(dataFileCache.dataFileName);
              InputStreamWrapper inputStreamWrapper = new InputStreamWrapper(new FileInputStream(file1));
              inputStreamWrapper.setSizeLimit(dataFileCache.fileStartFreePosition);
              this.backup.setStream(".data", (InputStreamInterface)inputStreamWrapper);
              InputStreamInterface inputStreamInterface = rAShadowFile.getInputStream();
              this.backup.setStream(".backup", inputStreamInterface);
            } 
          } 
          file1 = new File(this.log.getLogFileName());
          long l = file1.length();
          if (l == 0L) {
            this.backup.setFileIgnore(".log");
          } else {
            InputStreamWrapper inputStreamWrapper = new InputStreamWrapper(new FileInputStream(file1));
            inputStreamWrapper.setSizeLimit(l);
            this.backup.setStream(".log", (InputStreamInterface)inputStreamWrapper);
          } 
        } 
        if (paramBoolean4) {
          this.backup.writeAsFiles();
        } else {
          this.backup.write();
        } 
      } 
      logInfoEvent("Successfully backed up instance '" + str3 + "' to '" + paramString + "'");
    } catch (IOException iOException) {
      throw Error.error(452, iOException.toString());
    } catch (TarMalformatException tarMalformatException) {
      throw Error.error(452, tarMalformatException.toString());
    } finally {
      if (str1 != null)
        FileUtil.getFileUtil().delete(str1); 
      if (paramBoolean2)
        this.log.checkpointReopen(); 
    } 
  }
  
  public String getSecurePath(String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    if (this.database.getType() == "res:")
      return paramBoolean2 ? paramString : null; 
    if (this.database.getType() == "mem:")
      return this.propTextAllowFullPath ? paramString : null; 
    if (paramString.startsWith("/") || paramString.startsWith("\\") || paramString.indexOf(":") > -1)
      return (paramBoolean1 || this.propTextAllowFullPath) ? paramString : null; 
    if (paramString.indexOf("..") <= -1 || paramBoolean1 || this.propTextAllowFullPath) {
      String str = (new File((new File(this.database.getPath() + ".properties")).getAbsolutePath())).getParent();
      if (str != null)
        paramString = str + File.separator + paramString; 
      return paramString;
    } 
    return null;
  }
  
  public DataFileCache openTextFilePersistence(Table paramTable, String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    closeTextCache(paramTable);
    paramString = getSecurePath(paramString, false, true);
    if (paramString == null)
      throw Error.error(457, paramString); 
    TextCache textCache = new TextCache(paramTable, paramString);
    textCache.open((paramBoolean1 || this.database.isFilesReadOnly()));
    this.textCacheList.put(paramTable.getName(), textCache);
    return textCache;
  }
  
  public void closeTextCache(Table paramTable) {
    TextCache textCache = (TextCache)this.textCacheList.remove(paramTable.getName());
    if (textCache != null)
      try {
        textCache.close();
      } catch (HsqlException hsqlException) {} 
  }
  
  void closeAllTextCaches(boolean paramBoolean) {
    Iterator iterator = this.textCacheList.values().iterator();
    while (iterator.hasNext()) {
      TextCache textCache = (TextCache)iterator.next();
      if (paramBoolean && !textCache.table.isDataReadOnly()) {
        textCache.purge();
        continue;
      } 
      textCache.close();
    } 
  }
  
  boolean isAnyTextCacheModified() {
    Iterator iterator = this.textCacheList.values().iterator();
    while (iterator.hasNext()) {
      if (((TextCache)iterator.next()).isModified())
        return true; 
    } 
    return false;
  }
  
  public boolean isNewDatabase() {
    return this.isNewDatabase;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\Logger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */