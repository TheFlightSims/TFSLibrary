package org.hsqldb.persist;

import java.io.File;
import java.io.IOException;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.NumberSequence;
import org.hsqldb.Row;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.scriptio.ScriptReaderDecode;
import org.hsqldb.scriptio.ScriptReaderText;
import org.hsqldb.scriptio.ScriptWriterBase;
import org.hsqldb.scriptio.ScriptWriterEncode;
import org.hsqldb.scriptio.ScriptWriterText;

public class Log {
  private HsqlDatabaseProperties properties;
  
  private String fileName;
  
  private Database database;
  
  private FileAccess fa;
  
  ScriptWriterBase dbLogWriter;
  
  private String scriptFileName;
  
  private String logFileName;
  
  private boolean filesReadOnly;
  
  private long maxLogSize;
  
  private int writeDelay;
  
  private DataFileCache cache;
  
  private boolean isModified;
  
  Log(Database paramDatabase) {
    this.database = paramDatabase;
    this.fa = paramDatabase.logger.getFileAccess();
    this.fileName = paramDatabase.getPath();
    this.properties = paramDatabase.getProperties();
  }
  
  void initParams() {
    this.maxLogSize = this.database.logger.propLogSize * 1024L * 1024L;
    this.writeDelay = this.database.logger.propWriteDelay;
    this.filesReadOnly = this.database.isFilesReadOnly();
    this.scriptFileName = this.fileName + ".script";
  }
  
  void setupLogFile() {
    if (this.logFileName == null)
      this.logFileName = this.fileName + ".log"; 
  }
  
  void open() {
    initParams();
    int i = this.properties.getDBModified();
    switch (i) {
      case 1:
        this.database.logger.logInfoEvent("open start - state modified");
        deleteNewAndOldFiles();
        deleteOldTempFiles();
        if (this.properties.isVersion18()) {
          if (this.fa.isStreamElement(this.scriptFileName)) {
            processScript();
          } else {
            this.database.schemaManager.createPublicSchema();
          } 
          HsqlNameManager.HsqlName hsqlName = this.database.schemaManager.findSchemaHsqlName("PUBLIC");
          if (hsqlName != null)
            this.database.schemaManager.setDefaultSchemaHsqlName(hsqlName); 
        } else {
          processScript();
        } 
        processLog();
        checkpoint();
        break;
      case 2:
        this.database.logger.logInfoEvent("open start - state new files");
        renameNewDataFile();
        renameNewScript();
        deleteLog();
        backupData();
        this.properties.setDBModified(0);
      case 0:
        this.database.logger.logInfoEvent("open start - state not modified");
        processScript();
        if (!this.filesReadOnly && isAnyCacheModified()) {
          this.properties.setDBModified(1);
          checkpoint();
        } 
        break;
    } 
    if (!this.filesReadOnly)
      openLog(); 
  }
  
  void close(boolean paramBoolean) {
    closeLog();
    deleteOldDataFiles();
    deleteOldTempFiles();
    deleteTempFileDirectory();
    writeScript(paramBoolean);
    this.database.logger.closeAllTextCaches(paramBoolean);
    if (this.cache != null)
      this.cache.close(); 
    this.properties.setProperty("hsqldb.script_format", this.database.logger.propScriptFormat);
    this.properties.setDBModified(2);
    deleteLog();
    boolean bool = true;
    if (this.cache != null)
      if (paramBoolean) {
        this.cache.deleteFile();
        this.cache.deleteBackup();
        if (this.fa.isStreamElement(this.cache.dataFileName)) {
          this.database.logger.logInfoEvent("delete .data file failed ");
          bool = false;
        } 
        if (this.fa.isStreamElement(this.cache.backupFileName)) {
          this.database.logger.logInfoEvent("delete .backup file failed ");
          bool = false;
        } 
      } else {
        this.cache.backupDataFile(false);
      }  
    if (this.fa.isStreamElement(this.logFileName)) {
      this.database.logger.logInfoEvent("delete .log file failed ");
      bool = false;
    } 
    renameNewScript();
    if (bool)
      this.properties.setDBModified(0); 
  }
  
  void shutdown() {
    if (this.cache != null)
      this.cache.release(); 
    this.database.logger.closeAllTextCaches(false);
    closeLog();
  }
  
  void deleteNewAndOldFiles() {
    deleteOldDataFiles();
    this.fa.removeElement(this.fileName + ".data" + ".new");
    this.fa.removeElement(this.fileName + ".backup" + ".new");
    this.fa.removeElement(this.scriptFileName + ".new");
  }
  
  void deleteBackup() {
    this.fa.removeElement(this.fileName + ".backup");
  }
  
  void backupData() {
    DataFileCache.backupFile(this.database, this.fileName + ".data", this.fileName + ".backup", false);
  }
  
  void renameNewDataFile() {
    DataFileCache.renameDataFile(this.database, this.fileName + ".data");
  }
  
  void renameNewBackup() {
    DataFileCache.renameBackupFile(this.database, this.fileName + ".backup");
  }
  
  void renameNewScript() {
    if (this.fa.isStreamElement(this.scriptFileName + ".new")) {
      this.fa.removeElement(this.scriptFileName);
      this.fa.renameElement(this.scriptFileName + ".new", this.scriptFileName);
    } 
  }
  
  boolean renameNewDataFileDone() {
    return (this.fa.isStreamElement(this.fileName + ".data") && !this.fa.isStreamElement(this.fileName + ".data" + ".new"));
  }
  
  boolean renameNewScriptDone() {
    return (this.fa.isStreamElement(this.scriptFileName) && !this.fa.isStreamElement(this.scriptFileName + ".new"));
  }
  
  void deleteNewScript() {
    this.fa.removeElement(this.scriptFileName + ".new");
  }
  
  void deleteNewBackup() {
    this.fa.removeElement(this.fileName + ".backup" + ".new");
  }
  
  void deleteLog() {
    setupLogFile();
    this.fa.removeElement(this.logFileName);
  }
  
  boolean isAnyCacheModified() {
    return (this.cache != null && this.cache.isModified()) ? true : this.database.logger.isAnyTextCacheModified();
  }
  
  void checkpoint() {
    if (this.filesReadOnly)
      return; 
    boolean bool = checkpointClose();
    checkpointReopen();
    if (bool) {
      this.database.lobManager.deleteUnusedLobs();
    } else {
      this.database.logger.logSevereEvent("checkpoint failed - see previous error", null);
    } 
  }
  
  void checkpoint(boolean paramBoolean) {
    if (this.filesReadOnly)
      return; 
    if (this.cache == null) {
      paramBoolean = false;
    } else if (forceDefrag()) {
      paramBoolean = true;
    } 
    if (paramBoolean) {
      defrag();
    } else {
      checkpoint();
    } 
  }
  
  boolean checkpointClose() {
    if (this.filesReadOnly)
      return true; 
    this.database.logger.logInfoEvent("checkpointClose start");
    synchLog();
    this.database.lobManager.synch();
    deleteOldDataFiles();
    try {
      writeScript(false);
      if (this.cache != null) {
        this.cache.reset();
        this.cache.backupDataFile(true);
      } 
      this.properties.setProperty("hsqldb.script_format", this.database.logger.propScriptFormat);
      this.properties.setDBModified(2);
    } catch (Throwable throwable) {
      deleteNewScript();
      deleteNewBackup();
      this.database.logger.logSevereEvent("checkpoint failed - recovered", throwable);
      return false;
    } 
    closeLog();
    deleteLog();
    renameNewScript();
    renameNewBackup();
    try {
      this.properties.setDBModified(0);
    } catch (Throwable throwable) {
      this.database.logger.logSevereEvent("logger.checkpointClose properties file save failed", throwable);
    } 
    this.database.logger.logInfoEvent("checkpointClose end");
    return true;
  }
  
  boolean checkpointReopen() {
    if (this.filesReadOnly)
      return true; 
    this.database.sessionManager.resetLoggedSchemas();
    try {
      if (this.cache != null)
        this.cache.reopen(); 
      if (this.dbLogWriter != null)
        openLog(); 
    } catch (Throwable throwable) {
      return false;
    } 
    return true;
  }
  
  public void defrag() {
    this.database.logger.logInfoEvent("defrag start");
    try {
      synchLog();
      this.database.lobManager.synch();
      deleteOldDataFiles();
      DataFileDefrag dataFileDefrag = this.cache.defrag();
      this.database.persistentStoreCollection.setNewTableSpaces();
      this.database.sessionManager.resetLoggedSchemas();
    } catch (HsqlException hsqlException) {
      throw hsqlException;
    } catch (Throwable throwable) {
      this.database.logger.logSevereEvent("defrag failure", throwable);
      throw Error.error(466, throwable);
    } 
    this.database.logger.logInfoEvent("defrag end");
  }
  
  boolean forceDefrag() {
    long l1 = this.database.logger.propCacheDefragLimit * this.cache.getFileFreePos() / 100L;
    if (l1 == 0L)
      return false; 
    long l2 = this.cache.getLostBlockSize();
    this.cache.spaceManager.getLostBlocksSize();
    return (l2 > l1);
  }
  
  boolean hasCache() {
    return (this.cache != null);
  }
  
  DataFileCache getCache() {
    if (this.cache == null) {
      this.cache = new DataFileCache(this.database, this.fileName);
      this.cache.open(this.filesReadOnly);
    } 
    return this.cache;
  }
  
  void setLogSize(int paramInt) {
    this.maxLogSize = paramInt * 1024L * 1024L;
  }
  
  int getWriteDelay() {
    return this.writeDelay;
  }
  
  void setWriteDelay(int paramInt) {
    this.writeDelay = paramInt;
    if (this.dbLogWriter != null && this.dbLogWriter.getWriteDelay() != paramInt) {
      this.dbLogWriter.forceSync();
      this.dbLogWriter.stop();
      this.dbLogWriter.setWriteDelay(paramInt);
      this.dbLogWriter.start();
    } 
  }
  
  public void setIncrementBackup(boolean paramBoolean) {
    if (this.cache != null)
      this.cache.setIncrementBackup(paramBoolean); 
  }
  
  void writeOtherStatement(Session paramSession, String paramString) {
    try {
      this.dbLogWriter.writeOtherStatement(paramSession, paramString);
    } catch (IOException iOException) {
      throw Error.error(452, this.logFileName);
    } 
    if (this.maxLogSize > 0L && this.dbLogWriter.size() > this.maxLogSize)
      this.database.logger.setCheckpointRequired(); 
    setModified();
  }
  
  void writeInsertStatement(Session paramSession, Row paramRow, Table paramTable) {
    try {
      this.dbLogWriter.writeInsertStatement(paramSession, paramRow, paramTable);
    } catch (IOException iOException) {
      throw Error.error(452, this.logFileName);
    } 
    if (this.maxLogSize > 0L && this.dbLogWriter.size() > this.maxLogSize)
      this.database.logger.setCheckpointRequired(); 
  }
  
  void writeDeleteStatement(Session paramSession, Table paramTable, Object[] paramArrayOfObject) {
    try {
      this.dbLogWriter.writeDeleteStatement(paramSession, paramTable, paramArrayOfObject);
    } catch (IOException iOException) {
      throw Error.error(452, this.logFileName);
    } 
    if (this.maxLogSize > 0L && this.dbLogWriter.size() > this.maxLogSize)
      this.database.logger.setCheckpointRequired(); 
  }
  
  void writeSequenceStatement(Session paramSession, NumberSequence paramNumberSequence) {
    try {
      this.dbLogWriter.writeSequenceStatement(paramSession, paramNumberSequence);
    } catch (IOException iOException) {
      throw Error.error(452, this.logFileName);
    } 
    if (this.maxLogSize > 0L && this.dbLogWriter.size() > this.maxLogSize)
      this.database.logger.setCheckpointRequired(); 
    setModified();
  }
  
  void writeCommitStatement(Session paramSession) {
    try {
      this.dbLogWriter.writeCommitStatement(paramSession);
    } catch (IOException iOException) {
      throw Error.error(452, this.logFileName);
    } 
    if (this.maxLogSize > 0L && this.dbLogWriter.size() > this.maxLogSize)
      this.database.logger.setCheckpointRequired(); 
    setModified();
  }
  
  private void setModified() {
    if (!this.isModified) {
      this.database.databaseProperties.setDBModified(1);
      this.isModified = true;
    } 
  }
  
  void synchLog() {
    if (this.dbLogWriter != null)
      this.dbLogWriter.forceSync(); 
  }
  
  void openLog() {
    if (this.filesReadOnly)
      return; 
    setupLogFile();
    deleteLog();
    Crypto crypto = this.database.logger.getCrypto();
    try {
      if (crypto == null) {
        this.dbLogWriter = (ScriptWriterBase)new ScriptWriterText(this.database, this.logFileName, false, false, false);
      } else {
        this.dbLogWriter = (ScriptWriterBase)new ScriptWriterEncode(this.database, this.logFileName, crypto);
      } 
      this.dbLogWriter.setWriteDelay(this.writeDelay);
      this.dbLogWriter.start();
      this.isModified = false;
    } catch (Throwable throwable) {
      throw Error.error(452, this.logFileName);
    } 
  }
  
  synchronized void closeLog() {
    if (this.dbLogWriter != null) {
      this.database.logger.logDetailEvent("log close size: " + this.dbLogWriter.size());
      this.dbLogWriter.close();
    } 
  }
  
  void writeScript(boolean paramBoolean) {
    deleteNewScript();
    Crypto crypto = this.database.logger.getCrypto();
    if (crypto == null) {
      boolean bool = (this.database.logger.propScriptFormat == 3) ? true : false;
      ScriptWriterText scriptWriterText = new ScriptWriterText(this.database, this.scriptFileName + ".new", paramBoolean, bool);
    } else {
      scriptWriterEncode = new ScriptWriterEncode(this.database, this.scriptFileName + ".new", paramBoolean, crypto);
    } 
    scriptWriterEncode.writeAll();
    scriptWriterEncode.close();
    ScriptWriterEncode scriptWriterEncode = null;
  }
  
  private void processScript() {
    ScriptReaderDecode scriptReaderDecode;
    ScriptReaderText scriptReaderText = null;
    try {
      Crypto crypto = this.database.logger.getCrypto();
      if (crypto == null) {
        boolean bool = (this.database.logger.propScriptFormat == 3) ? true : false;
        scriptReaderText = new ScriptReaderText(this.database, this.scriptFileName, bool);
      } else {
        scriptReaderDecode = new ScriptReaderDecode(this.database, this.scriptFileName, crypto, false);
      } 
      Session session = this.database.sessionManager.getSysSessionForScript(this.database);
      scriptReaderDecode.readAll(session);
      scriptReaderDecode.close();
    } catch (Throwable throwable) {
      if (scriptReaderDecode != null) {
        scriptReaderDecode.close();
        if (this.cache != null)
          this.cache.release(); 
        this.database.logger.closeAllTextCaches(false);
      } 
      this.database.logger.logWarningEvent("Script processing failure", throwable);
      if (throwable instanceof HsqlException)
        throw (HsqlException)throwable; 
      if (throwable instanceof IOException)
        throw Error.error(452, throwable); 
      if (throwable instanceof OutOfMemoryError)
        throw Error.error(460); 
      throw Error.error(458, throwable);
    } 
  }
  
  private void processLog() {
    setupLogFile();
    if (this.fa.isStreamElement(this.logFileName))
      ScriptRunner.runScript(this.database, this.logFileName); 
  }
  
  void deleteOldDataFiles() {
    if (this.database.logger.isStoredFileAccess())
      return; 
    try {
      File file = new File(this.database.getCanonicalPath());
      File[] arrayOfFile = file.getParentFile().listFiles();
      if (arrayOfFile == null)
        return; 
      for (byte b = 0; b < arrayOfFile.length; b++) {
        if (arrayOfFile[b].getName().startsWith(file.getName()) && arrayOfFile[b].getName().endsWith(".old"))
          arrayOfFile[b].delete(); 
      } 
    } catch (Throwable throwable) {}
  }
  
  void deleteOldTempFiles() {
    try {
      if (this.database.logger.tempDirectoryPath == null)
        return; 
      File file = new File(this.database.logger.tempDirectoryPath);
      File[] arrayOfFile = file.listFiles();
      if (arrayOfFile == null)
        return; 
      for (byte b = 0; b < arrayOfFile.length; b++)
        arrayOfFile[b].delete(); 
    } catch (Throwable throwable) {}
  }
  
  void deleteTempFileDirectory() {
    try {
      if (this.database.logger.tempDirectoryPath == null)
        return; 
      File file = new File(this.database.logger.tempDirectoryPath);
      file.delete();
    } catch (Throwable throwable) {}
  }
  
  String getLogFileName() {
    return this.logFileName;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\Log.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */