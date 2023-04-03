package org.hsqldb.persist;

import java.io.UnsupportedEncodingException;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowInputText;
import org.hsqldb.rowio.RowInputTextQuoted;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.rowio.RowOutputText;
import org.hsqldb.rowio.RowOutputTextQuoted;
import org.hsqldb.scriptio.ScriptWriterText;

public class TextCache extends DataFileCache {
  TextFileSettings textFileSettings;
  
  protected String header;
  
  protected Table table;
  
  private LongKeyHashMap uncommittedCache;
  
  HsqlByteArrayOutputStream buffer = new HsqlByteArrayOutputStream(128);
  
  TextCache(Table paramTable, String paramString) {
    super(paramTable.database, paramString);
    this.table = paramTable;
    this.uncommittedCache = new LongKeyHashMap();
  }
  
  protected void initParams(Database paramDatabase, String paramString, boolean paramBoolean) {
    this.database = paramDatabase;
    this.fa = (FileAccess)FileUtil.getFileUtil();
    this.textFileSettings = new TextFileSettings(paramDatabase, paramString);
    this.dataFileName = this.textFileSettings.getFileName();
    if (this.dataFileName == null)
      throw Error.error(301); 
    this.dataFileName = ((FileUtil)this.fa).canonicalOrAbsolutePath(this.dataFileName);
    this.maxCacheRows = this.textFileSettings.getMaxCacheRows();
    this.maxCacheBytes = this.textFileSettings.getMaxCacheBytes();
    this.maxDataFileSize = 274877906816L;
    this.cachedRowPadding = 1;
    this.dataFileScale = 1;
  }
  
  protected void initBuffers() {
    if (this.textFileSettings.isQuoted || this.textFileSettings.isAllQuoted) {
      this.rowIn = (RowInputInterface)new RowInputTextQuoted(this.textFileSettings.fs, this.textFileSettings.vs, this.textFileSettings.lvs, this.textFileSettings.isAllQuoted);
      this.rowOut = (RowOutputInterface)new RowOutputTextQuoted(this.textFileSettings.fs, this.textFileSettings.vs, this.textFileSettings.lvs, this.textFileSettings.isAllQuoted, this.textFileSettings.stringEncoding);
    } else {
      this.rowIn = (RowInputInterface)new RowInputText(this.textFileSettings.fs, this.textFileSettings.vs, this.textFileSettings.lvs, false);
      this.rowOut = (RowOutputInterface)new RowOutputText(this.textFileSettings.fs, this.textFileSettings.vs, this.textFileSettings.lvs, false, this.textFileSettings.stringEncoding);
    } 
  }
  
  public void open(boolean paramBoolean) {
    this.fileFreePosition = 0L;
    try {
      byte b = (this.database.getType() == "res:") ? 2 : 5;
      this.dataFile = RAFile.newScaledRAFile(this.database, this.dataFileName, paramBoolean, b);
      this.fileFreePosition = this.dataFile.length();
      if (this.fileFreePosition > this.maxDataFileSize)
        throw Error.error(468); 
      initBuffers();
      this.spaceManager = new DataSpaceManagerSimple(this);
    } catch (Throwable throwable) {
      throw Error.error(throwable, 452, 42, new Object[] { throwable.toString(), this.dataFileName });
    } 
    this.cacheReadonly = paramBoolean;
  }
  
  public void release() {
    close();
  }
  
  public void close() {
    if (this.dataFile == null)
      return; 
    this.writeLock.lock();
    try {
      this.cache.saveAll();
      boolean bool = (this.dataFile.length() <= TextFileSettings.NL.length()) ? true : false;
      this.dataFile.synch();
      this.dataFile.close();
      this.dataFile = null;
      if (bool && !this.cacheReadonly)
        FileUtil.getFileUtil().delete(this.dataFileName); 
      this.uncommittedCache.clear();
    } catch (Throwable throwable) {
      throw Error.error(throwable, 452, 43, new Object[] { throwable.toString(), this.dataFileName });
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  void purge() {
    this.writeLock.lock();
    try {
      this.uncommittedCache.clear();
      if (this.cacheReadonly) {
        release();
      } else {
        if (this.dataFile != null) {
          this.dataFile.close();
          this.dataFile = null;
        } 
        FileUtil.getFileUtil().delete(this.dataFileName);
      } 
    } catch (Throwable throwable) {
      throw Error.error(throwable, 452, 44, new Object[] { throwable.toString(), this.dataFileName });
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void remove(CachedObject paramCachedObject) {
    this.writeLock.lock();
    try {
      long l = paramCachedObject.getPos();
      CachedObject cachedObject = (CachedObject)this.uncommittedCache.remove(l);
      if (cachedObject != null)
        return; 
      this.cache.release(l);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void removePersistence(CachedObject paramCachedObject) {
    this.writeLock.lock();
    try {
      clearRowImage(paramCachedObject);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  private void clearRowImage(CachedObject paramCachedObject) {
    try {
      int i = paramCachedObject.getStorageSize() - ScriptWriterText.BYTES_LINE_SEP.length;
      this.rowOut.reset();
      HsqlByteArrayOutputStream hsqlByteArrayOutputStream = this.rowOut.getOutputStream();
      hsqlByteArrayOutputStream.fill(32, i);
      hsqlByteArrayOutputStream.write(ScriptWriterText.BYTES_LINE_SEP);
      this.dataFile.seek(paramCachedObject.getPos());
      this.dataFile.write(hsqlByteArrayOutputStream.getBuffer(), 0, hsqlByteArrayOutputStream.size());
    } catch (Throwable throwable) {
      throw Error.runtimeError(201, throwable.getMessage());
    } 
  }
  
  public void addInit(CachedObject paramCachedObject) {
    this.writeLock.lock();
    try {
      this.cache.put(paramCachedObject);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void add(CachedObject paramCachedObject) {
    this.writeLock.lock();
    try {
      this.uncommittedCache.put(paramCachedObject.getPos(), paramCachedObject);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public CachedObject get(CachedObject paramCachedObject, PersistentStore paramPersistentStore, boolean paramBoolean) {
    if (paramCachedObject == null)
      return null; 
    this.writeLock.lock();
    try {
      CachedObject cachedObject = this.cache.get(paramCachedObject.getPos());
      if (cachedObject != null)
        return paramCachedObject; 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public CachedObject get(long paramLong, PersistentStore paramPersistentStore, boolean paramBoolean) {
    throw Error.runtimeError(201, "TextCache");
  }
  
  protected void saveRows(CachedObject[] paramArrayOfCachedObject, int paramInt1, int paramInt2) {}
  
  public void saveRow(CachedObject paramCachedObject) {
    this.writeLock.lock();
    try {
      setFileModified();
      saveRowNoLock(paramCachedObject);
      this.uncommittedCache.remove(paramCachedObject.getPos());
      this.cache.put(paramCachedObject);
    } catch (Throwable throwable) {
      this.database.logger.logSevereEvent("saveRow failed", throwable);
      throw Error.error(466, throwable);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public String getHeader() {
    return this.header;
  }
  
  public void setHeaderInitialise(String paramString) {
    this.header = paramString;
  }
  
  public void setHeader(String paramString) {
    if (this.textFileSettings.ignoreFirst && this.fileFreePosition == 0L) {
      try {
        writeHeader(paramString);
        this.header = paramString;
      } catch (HsqlException hsqlException) {
        throw new HsqlException(hsqlException, Error.getMessage(467), 467);
      } 
      return;
    } 
    throw Error.error(486);
  }
  
  private void writeHeader(String paramString) {
    try {
      byte[] arrayOfByte = null;
      String str = paramString + TextFileSettings.NL;
      try {
        arrayOfByte = str.getBytes(this.textFileSettings.stringEncoding);
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        arrayOfByte = str.getBytes();
      } 
      this.dataFile.seek(0L);
      this.dataFile.write(arrayOfByte, 0, arrayOfByte.length);
      this.fileFreePosition = arrayOfByte.length;
    } catch (Throwable throwable) {
      throw Error.error(484, throwable);
    } 
  }
  
  public int getLineNumber() {
    return ((RowInputText)this.rowIn).getLineNumber();
  }
  
  public TextFileSettings getTextFileSettings() {
    return this.textFileSettings;
  }
  
  public boolean isIgnoreFirstLine() {
    return this.textFileSettings.ignoreFirst;
  }
  
  protected void setFileModified() {
    this.fileModified = true;
  }
  
  public TextFileReader getTextFileReader() {
    return new TextFileReader(this.dataFile, this.textFileSettings, this.rowIn, this.cacheReadonly);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\TextCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */