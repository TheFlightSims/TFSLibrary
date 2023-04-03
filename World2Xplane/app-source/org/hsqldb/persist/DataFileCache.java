package org.hsqldb.persist;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.FileArchiver;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.Iterator;
import org.hsqldb.map.BitMap;
import org.hsqldb.rowio.RowInputBinary180;
import org.hsqldb.rowio.RowInputBinaryDecode;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputBinary180;
import org.hsqldb.rowio.RowOutputBinaryEncode;
import org.hsqldb.rowio.RowOutputInterface;

public class DataFileCache {
  protected FileAccess fa;
  
  public static final int FLAG_ISSHADOWED = 1;
  
  public static final int FLAG_ISSAVED = 2;
  
  public static final int FLAG_ROWINFO = 3;
  
  public static final int FLAG_190 = 4;
  
  public static final int FLAG_HX = 5;
  
  static final int LONG_EMPTY_SIZE = 4;
  
  static final int LONG_FREE_POS_POS = 12;
  
  static final int INT_SPACE_LIST_POS = 24;
  
  static final int FLAGS_POS = 28;
  
  static final int MIN_INITIAL_FREE_POS = 32;
  
  public DataSpaceManager spaceManager;
  
  static final int initIOBufferSize = 4096;
  
  private static final int diskBlockSize = 4096;
  
  protected String dataFileName;
  
  protected String backupFileName;
  
  protected Database database;
  
  protected boolean logEvents = true;
  
  protected boolean fileModified;
  
  protected boolean cacheModified;
  
  protected int dataFileScale;
  
  protected boolean cacheReadonly;
  
  protected int cachedRowPadding;
  
  protected long initialFreePos;
  
  protected long lostSpaceSize;
  
  protected long spaceManagerPosition;
  
  protected long fileStartFreePosition;
  
  protected boolean hasRowInfo = false;
  
  protected int storeCount;
  
  protected RowInputInterface rowIn;
  
  public RowOutputInterface rowOut;
  
  public long maxDataFileSize;
  
  boolean is180;
  
  protected RandomAccessInterface dataFile;
  
  protected volatile long fileFreePosition;
  
  protected int maxCacheRows;
  
  protected long maxCacheBytes;
  
  protected Cache cache;
  
  private RAShadowFile shadowFile;
  
  ReadWriteLock lock = new ReentrantReadWriteLock();
  
  Lock readLock = this.lock.readLock();
  
  Lock writeLock = this.lock.writeLock();
  
  public DataFileCache(Database paramDatabase, String paramString) {
    initParams(paramDatabase, paramString, false);
    this.cache = new Cache(this);
  }
  
  public DataFileCache(Database paramDatabase, String paramString, boolean paramBoolean) {
    initParams(paramDatabase, paramString, true);
    this.cache = new Cache(this);
    try {
      if (this.database.logger.isStoredFileAccess()) {
        this.dataFile = RAFile.newScaledRAFile(this.database, this.dataFileName, false, 3);
      } else {
        this.dataFile = new RAFileSimple(this.database, this.dataFileName, "rw");
      } 
    } catch (Throwable throwable) {
      throw Error.error(452, throwable);
    } 
    initNewFile();
    initBuffers();
    if (this.database.logger.isDataFileSpaces()) {
      this.spaceManager = new DataSpaceManagerBlocks(this);
    } else {
      this.spaceManager = new DataSpaceManagerSimple(this);
    } 
  }
  
  protected void initParams(Database paramDatabase, String paramString, boolean paramBoolean) {
    this.dataFileName = paramString + ".data";
    this.backupFileName = paramString + ".backup";
    this.database = paramDatabase;
    this.fa = paramDatabase.logger.getFileAccess();
    this.dataFileScale = paramDatabase.logger.getDataFileScale();
    this.cachedRowPadding = 8;
    if (this.dataFileScale > 8)
      this.cachedRowPadding = this.dataFileScale; 
    this.initialFreePos = 32L;
    if (this.initialFreePos < this.dataFileScale)
      this.initialFreePos = this.dataFileScale; 
    this.cacheReadonly = paramDatabase.isFilesReadOnly();
    this.maxCacheRows = paramDatabase.logger.propCacheMaxRows;
    this.maxCacheBytes = paramDatabase.logger.propCacheMaxSize;
    this.maxDataFileSize = 2147483647L * this.dataFileScale * paramDatabase.logger.getDataFileFactor();
    if (paramBoolean) {
      this.dataFileName = paramString + ".new";
      this.backupFileName = paramString + ".new";
      this.maxCacheRows = 1024;
      this.maxCacheBytes = 4194304L;
    } 
  }
  
  public void open(boolean paramBoolean) {
    if (this.database.logger.isStoredFileAccess()) {
      openStoredFileAccess(paramBoolean);
      return;
    } 
    this.fileFreePosition = this.initialFreePos;
    logInfoEvent("dataFileCache open start");
    try {
      boolean bool;
      boolean bool1 = this.database.logger.propNioDataFile;
      if (this.database.isFilesInJar()) {
        bool = true;
      } else if (bool1) {
        bool = true;
      } else {
        bool = false;
      } 
      if (paramBoolean || this.database.isFilesInJar()) {
        this.dataFile = RAFile.newScaledRAFile(this.database, this.dataFileName, paramBoolean, bool);
        this.dataFile.seek(28L);
        int i = this.dataFile.readInt();
        this.is180 = !BitMap.isSet(i, 4);
        if (BitMap.isSet(i, 5))
          throw Error.error(453); 
        this.dataFile.seek(12L);
        this.fileFreePosition = this.dataFile.readLong();
        this.dataFile.seek(24L);
        this.spaceManagerPosition = this.dataFile.readInt() * 4096L;
        initBuffers();
        this.spaceManager = new DataSpaceManagerSimple(this);
        return;
      } 
      boolean bool2 = this.fa.isStreamElement(this.dataFileName);
      boolean bool3 = this.database.logger.propIncrementBackup;
      boolean bool4 = false;
      if (bool2) {
        this.dataFile = new RAFileSimple(this.database, this.dataFileName, "r");
        long l = this.dataFile.length();
        boolean bool5 = false;
        if (l > this.initialFreePos) {
          this.dataFile.seek(28L);
          int i = this.dataFile.readInt();
          bool4 = BitMap.isSet(i, 2);
          bool3 = BitMap.isSet(i, 1);
          this.is180 = !BitMap.isSet(i, 4);
          if (BitMap.isSet(i, 5))
            bool5 = true; 
        } 
        this.dataFile.close();
        if (bool5)
          throw Error.error(453); 
        if (!this.database.logger.propLargeData && l > this.maxDataFileSize / 8L * 7L) {
          this.database.logger.propLargeData = true;
          this.maxDataFileSize = 2147483647L * this.dataFileScale * this.database.logger.getDataFileFactor();
        } 
        if (l > this.maxDataFileSize)
          throw Error.error(468, String.valueOf(this.maxDataFileSize)); 
        if (bool4 && bool3) {
          boolean bool6 = this.fa.isStreamElement(this.backupFileName);
          if (bool6) {
            int i = this.database.databaseProperties.getDBModified();
            if (i == 1) {
              bool4 = false;
              logInfoEvent("data file was saved but inc backup exists - restoring");
            } 
          } 
        } 
      } 
      if (bool4) {
        if (bool3) {
          deleteBackup();
        } else {
          boolean bool5 = this.fa.isStreamElement(this.backupFileName);
          if (!bool5)
            backupDataFile(false); 
        } 
      } else if (bool3) {
        bool2 = restoreBackupIncremental();
      } else {
        bool2 = restoreBackup();
      } 
      this.dataFile = RAFile.newScaledRAFile(this.database, this.dataFileName, paramBoolean, bool);
      if (bool2) {
        this.dataFile.seek(28L);
        int i = this.dataFile.readInt();
        this.is180 = !BitMap.isSet(i, 4);
        this.dataFile.seek(4L);
        this.lostSpaceSize = this.dataFile.readLong();
        this.dataFile.seek(12L);
        this.fileFreePosition = this.dataFile.readLong();
        this.fileStartFreePosition = this.fileFreePosition;
        this.dataFile.seek(24L);
        this.spaceManagerPosition = this.dataFile.readInt() * 4096L;
        openShadowFile();
      } else {
        initNewFile();
      } 
      initBuffers();
      this.fileModified = false;
      this.cacheModified = false;
      if (this.spaceManagerPosition != 0L || this.database.logger.isDataFileSpaces()) {
        this.spaceManager = new DataSpaceManagerBlocks(this);
      } else {
        this.spaceManager = new DataSpaceManagerSimple(this);
      } 
      logInfoEvent("dataFileCache open end");
    } catch (Throwable throwable) {
      logSevereEvent("DataFileCache.open", throwable);
      release();
      throw Error.error(throwable, 452, 52, new Object[] { throwable.toString(), this.dataFileName });
    } 
  }
  
  boolean setTableSpaceManager(boolean paramBoolean) {
    if (paramBoolean && this.spaceManagerPosition == 0L) {
      this.spaceManager.reset();
      this.spaceManager = new DataSpaceManagerBlocks(this);
      return true;
    } 
    if (!paramBoolean && this.spaceManagerPosition != 0L) {
      this.spaceManager.reset();
      this.spaceManager = new DataSpaceManagerSimple(this);
      return true;
    } 
    return false;
  }
  
  void openStoredFileAccess(boolean paramBoolean) {
    this.fileFreePosition = this.initialFreePos;
    logInfoEvent("dataFileCache open start");
    try {
      byte b = 3;
      if (paramBoolean) {
        this.dataFile = RAFile.newScaledRAFile(this.database, this.dataFileName, paramBoolean, b);
        this.dataFile.seek(28L);
        int i = this.dataFile.readInt();
        this.is180 = !BitMap.isSet(i, 4);
        this.dataFile.seek(12L);
        this.fileFreePosition = this.dataFile.readLong();
        initBuffers();
        return;
      } 
      long l = 0L;
      boolean bool1 = this.fa.isStreamElement(this.dataFileName);
      boolean bool2 = this.database.logger.propIncrementBackup;
      boolean bool = (this.database.getProperties().getDBModified() == 1) ? true : false;
      if (bool1 && bool)
        if (bool2) {
          bool1 = restoreBackupIncremental();
        } else {
          bool1 = restoreBackup();
        }  
      this.dataFile = RAFile.newScaledRAFile(this.database, this.dataFileName, paramBoolean, b);
      if (bool1) {
        this.dataFile.seek(4L);
        l = this.dataFile.readLong();
        this.dataFile.seek(12L);
        this.fileFreePosition = this.dataFile.readLong();
        this.fileStartFreePosition = this.fileFreePosition;
        this.dataFile.seek(28L);
        int i = this.dataFile.readInt();
        this.is180 = !BitMap.isSet(i, 4);
        openShadowFile();
      } else {
        initNewFile();
      } 
      initBuffers();
      this.fileModified = false;
      this.cacheModified = false;
      this.spaceManager = new DataSpaceManagerSimple(this);
      logInfoEvent("dataFileCache open end");
    } catch (Throwable throwable) {
      logSevereEvent("dataFileCache open failed", throwable);
      release();
      throw Error.error(throwable, 452, 52, new Object[] { throwable.toString(), this.dataFileName });
    } 
  }
  
  void initNewFile() {
    try {
      this.fileFreePosition = this.initialFreePos;
      this.fileStartFreePosition = this.initialFreePos;
      this.dataFile.seek(12L);
      this.dataFile.writeLong(this.fileFreePosition);
      int i = 0;
      if (this.database.logger.propIncrementBackup)
        i = BitMap.set(i, 1); 
      i = BitMap.set(i, 2);
      i = BitMap.set(i, 4);
      this.dataFile.seek(28L);
      this.dataFile.writeInt(i);
      this.dataFile.synch();
      this.is180 = false;
    } catch (Throwable throwable) {
      throw Error.error(452, throwable);
    } 
  }
  
  private void openShadowFile() {
    if (this.database.logger.propIncrementBackup && this.fileFreePosition != this.initialFreePos)
      this.shadowFile = new RAShadowFile(this.database, this.dataFile, this.backupFileName, this.fileFreePosition, 16384); 
  }
  
  void setIncrementBackup(boolean paramBoolean) {
    this.writeLock.lock();
    try {
      this.dataFile.seek(28L);
      int i = this.dataFile.readInt();
      if (paramBoolean) {
        i = BitMap.set(i, 1);
      } else {
        i = BitMap.unset(i, 1);
      } 
      this.dataFile.seek(28L);
      this.dataFile.writeInt(i);
      this.dataFile.synch();
      this.fileModified = true;
    } catch (Throwable throwable) {
      logSevereEvent("DataFileCache.setIncrementalBackup", throwable);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  private boolean restoreBackup() {
    return restoreBackup(this.database, this.dataFileName, this.backupFileName);
  }
  
  static boolean restoreBackupFile(Database paramDatabase, String paramString1, String paramString2) {
    return paramDatabase.logger.propIncrementBackup ? restoreBackupIncremental(paramDatabase, paramString1, paramString2) : restoreBackup(paramDatabase, paramString1, paramString2);
  }
  
  static boolean restoreBackup(Database paramDatabase, String paramString1, String paramString2) {
    try {
      FileAccess fileAccess = paramDatabase.logger.getFileAccess();
      deleteFile(paramDatabase, paramString1);
      if (fileAccess.isStreamElement(paramString2)) {
        FileArchiver.unarchive(paramString2, paramString1, fileAccess, 1);
        return true;
      } 
      return false;
    } catch (Throwable throwable) {
      paramDatabase.logger.logSevereEvent("DataFileCache.restoreBackup", throwable);
      throw Error.error(throwable, 452, 26, new Object[] { throwable.toString(), paramString2 });
    } 
  }
  
  private boolean restoreBackupIncremental() {
    return restoreBackupIncremental(this.database, this.dataFileName, this.backupFileName);
  }
  
  static boolean restoreBackupIncremental(Database paramDatabase, String paramString1, String paramString2) {
    try {
      FileAccess fileAccess = paramDatabase.logger.getFileAccess();
      if (fileAccess.isStreamElement(paramString2)) {
        RAShadowFile.restoreFile(paramDatabase, paramString2, paramString1);
        deleteFile(paramDatabase, paramString2);
        return true;
      } 
      return false;
    } catch (Throwable throwable) {
      paramDatabase.logger.logSevereEvent("DataFileCache.restoreBackupIncremental", throwable);
      throw Error.error(452, throwable);
    } 
  }
  
  public void release() {
    this.writeLock.lock();
    try {
      if (this.dataFile == null)
        return; 
      if (this.shadowFile != null) {
        this.shadowFile.close();
        this.shadowFile = null;
      } 
      this.dataFile.close();
      logDetailEvent("dataFileCache file closed");
      this.dataFile = null;
    } catch (Throwable throwable) {
      logSevereEvent("DataFileCache.release", throwable);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void close() {
    this.writeLock.lock();
    try {
      if (this.dataFile == null)
        return; 
      reset();
      this.dataFile.close();
      logDetailEvent("dataFileCache file close end");
      this.dataFile = null;
      boolean bool = (this.fileFreePosition == this.initialFreePos) ? true : false;
      if (bool) {
        deleteFile();
        deleteBackup();
      } 
    } catch (HsqlException hsqlException) {
      throw hsqlException;
    } catch (Throwable throwable) {
      logSevereEvent("DataFileCache.close", throwable);
      throw Error.error(throwable, 452, 53, new Object[] { throwable.toString(), this.dataFileName });
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  protected void clear() {
    this.writeLock.lock();
    try {
      this.cache.clear();
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void adjustStoreCount(int paramInt) {
    this.writeLock.lock();
    try {
      this.storeCount += paramInt;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void reopen() {
    this.spaceManager.initialiseSpaces();
    openShadowFile();
  }
  
  public void reset() {
    this.writeLock.lock();
    try {
      if (this.cacheReadonly)
        return; 
      logInfoEvent("dataFileCache commit start");
      this.spaceManager.reset();
      this.cache.saveAll();
      this.dataFile.seek(4L);
      this.dataFile.writeLong(this.spaceManager.getLostBlocksSize());
      this.dataFile.seek(12L);
      this.dataFile.writeLong(this.fileFreePosition);
      this.dataFile.seek(24L);
      int i = (int)(this.spaceManagerPosition / 4096L);
      this.dataFile.writeInt(i);
      this.dataFile.seek(28L);
      int j = this.dataFile.readInt();
      j = BitMap.set(j, 2);
      this.dataFile.seek(28L);
      this.dataFile.writeInt(j);
      this.dataFile.synch();
      logDetailEvent("file sync end");
      this.fileModified = false;
      this.cacheModified = false;
      this.fileStartFreePosition = this.fileFreePosition;
      if (this.shadowFile != null) {
        this.shadowFile.close();
        this.shadowFile = null;
      } 
      logInfoEvent("dataFileCache commit end");
    } catch (Throwable throwable) {
      logSevereEvent("DataFileCache.reset commit", throwable);
      throw Error.error(throwable, 452, 53, new Object[] { throwable.toString(), this.dataFileName });
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  protected void initBuffers() {
    if (this.rowOut == null)
      if (this.is180) {
        this.rowOut = (RowOutputInterface)new RowOutputBinary180(4096, this.cachedRowPadding);
      } else {
        this.rowOut = (RowOutputInterface)new RowOutputBinaryEncode(this.database.logger.getCrypto(), 4096, this.cachedRowPadding);
      }  
    if (this.rowIn == null)
      if (this.is180) {
        this.rowIn = (RowInputInterface)new RowInputBinary180(new byte[4096]);
      } else {
        this.rowIn = (RowInputInterface)new RowInputBinaryDecode(this.database.logger.getCrypto(), new byte[4096]);
      }  
  }
  
  DataFileDefrag defrag() {
    this.writeLock.lock();
    try {
      this.cache.saveAll();
      DataFileDefrag dataFileDefrag = new DataFileDefrag(this.database, this, this.dataFileName);
      dataFileDefrag.process();
      close();
      this.cache.clear();
      if (!this.database.logger.propIncrementBackup)
        backupNewDataFile(true); 
      this.database.schemaManager.setTempIndexRoots(dataFileDefrag.getIndexRoots());
      try {
        this.database.logger.log.writeScript(false);
      } finally {
        this.database.schemaManager.setTempIndexRoots((long[][])null);
      } 
      this.database.getProperties().setProperty("hsqldb.script_format", this.database.logger.propScriptFormat);
      this.database.getProperties().setDBModified(2);
      this.database.logger.log.closeLog();
      this.database.logger.log.deleteLog();
      this.database.logger.log.renameNewScript();
      renameBackupFile();
      renameDataFile();
      this.database.getProperties().setDBModified(0);
      open(false);
      this.database.schemaManager.setIndexRoots(dataFileDefrag.getIndexRoots());
      if (this.database.logger.log.dbLogWriter != null)
        this.database.logger.log.openLog(); 
      return dataFileDefrag;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void remove(CachedObject paramCachedObject) {
    this.writeLock.lock();
    try {
      release(paramCachedObject.getPos());
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void removePersistence(CachedObject paramCachedObject) {}
  
  public void add(CachedObject paramCachedObject) {
    this.writeLock.lock();
    try {
      this.cacheModified = true;
      this.cache.put(paramCachedObject);
      if (paramCachedObject.getStorageSize() > 4096)
        this.rowOut.reset(paramCachedObject.getStorageSize()); 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public int getStorageSize(long paramLong) {
    this.readLock.lock();
    try {
      CachedObject cachedObject = this.cache.get(paramLong);
      if (cachedObject != null)
        return cachedObject.getStorageSize(); 
    } finally {
      this.readLock.unlock();
    } 
    return readSize(paramLong);
  }
  
  public void replace(CachedObject paramCachedObject) {
    this.writeLock.lock();
    try {
      long l = paramCachedObject.getPos();
      this.cache.replace(l, paramCachedObject);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public CachedObject get(CachedObject paramCachedObject, PersistentStore paramPersistentStore, boolean paramBoolean) {
    long l;
    this.readLock.lock();
    try {
      if (paramCachedObject.isInMemory()) {
        if (paramBoolean)
          paramCachedObject.keepInMemory(true); 
        return paramCachedObject;
      } 
      l = paramCachedObject.getPos();
      if (l < 0L)
        return null; 
      paramCachedObject = this.cache.get(l);
      if (paramCachedObject != null) {
        if (paramBoolean)
          paramCachedObject.keepInMemory(true); 
        return paramCachedObject;
      } 
    } finally {
      this.readLock.unlock();
    } 
    return getFromFile(l, paramPersistentStore, paramBoolean);
  }
  
  public CachedObject get(long paramLong, int paramInt, PersistentStore paramPersistentStore, boolean paramBoolean) {
    if (paramLong < 0L)
      return null; 
    this.readLock.lock();
    try {
      CachedObject cachedObject = this.cache.get(paramLong);
      if (cachedObject != null) {
        if (paramBoolean)
          cachedObject.keepInMemory(true); 
        return cachedObject;
      } 
    } finally {
      this.readLock.unlock();
    } 
    return getFromFile(paramLong, paramInt, paramPersistentStore, paramBoolean);
  }
  
  public CachedObject get(long paramLong, PersistentStore paramPersistentStore, boolean paramBoolean) {
    if (paramLong < 0L)
      return null; 
    this.readLock.lock();
    try {
      CachedObject cachedObject = this.cache.get(paramLong);
      if (cachedObject != null) {
        if (paramBoolean)
          cachedObject.keepInMemory(true); 
        return cachedObject;
      } 
    } finally {
      this.readLock.unlock();
    } 
    return getFromFile(paramLong, paramPersistentStore, paramBoolean);
  }
  
  private CachedObject getFromFile(long paramLong, PersistentStore paramPersistentStore, boolean paramBoolean) {
    CachedObject cachedObject = null;
    this.writeLock.lock();
    try {
      cachedObject = this.cache.get(paramLong);
      if (cachedObject != null) {
        if (paramBoolean)
          cachedObject.keepInMemory(true); 
        return cachedObject;
      } 
      byte b = 0;
      while (b < 2) {
        try {
          readObject(paramLong);
          cachedObject = paramPersistentStore.get(this.rowIn);
          break;
        } catch (Throwable throwable) {
          if (throwable instanceof OutOfMemoryError) {
            this.cache.clearUnchanged();
            System.gc();
            logSevereEvent(this.dataFileName + " getFromFile out of mem " + paramLong, throwable);
            if (b > 0) {
              HsqlException hsqlException = Error.error(460, throwable);
              hsqlException.info = this.rowIn;
              throw hsqlException;
            } 
          } else {
            HsqlException hsqlException = Error.error(467, throwable);
            hsqlException.info = this.rowIn;
            throw hsqlException;
          } 
          b++;
        } 
      } 
      this.cache.put(cachedObject);
      if (paramBoolean)
        cachedObject.keepInMemory(true); 
      paramPersistentStore.set(cachedObject);
      return cachedObject;
    } catch (HsqlException hsqlException) {
      logSevereEvent(this.dataFileName + " getFromFile failed " + paramLong, (Throwable)hsqlException);
      throw hsqlException;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  private CachedObject getFromFile(long paramLong, int paramInt, PersistentStore paramPersistentStore, boolean paramBoolean) {
    CachedObject cachedObject = null;
    this.writeLock.lock();
    try {
      cachedObject = this.cache.get(paramLong);
      if (cachedObject != null) {
        if (paramBoolean)
          cachedObject.keepInMemory(true); 
        return cachedObject;
      } 
      byte b = 0;
      while (b < 2) {
        try {
          readObject(paramLong, paramInt);
          cachedObject = paramPersistentStore.get(this.rowIn);
          break;
        } catch (OutOfMemoryError outOfMemoryError) {
          this.cache.clearUnchanged();
          System.gc();
          logSevereEvent(this.dataFileName + " getFromFile out of mem " + paramLong, outOfMemoryError);
          if (b > 0)
            throw outOfMemoryError; 
          b++;
        } 
      } 
      this.cache.put(cachedObject);
      if (paramBoolean)
        cachedObject.keepInMemory(true); 
      paramPersistentStore.set(cachedObject);
      return cachedObject;
    } catch (HsqlException hsqlException) {
      logSevereEvent(this.dataFileName + " getFromFile failed " + paramLong, (Throwable)hsqlException);
      throw hsqlException;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  RowInputInterface getRaw(int paramInt) {
    this.writeLock.lock();
    try {
      readObject(paramInt);
      return this.rowIn;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  private int readSize(long paramLong) {
    this.writeLock.lock();
    try {
      this.dataFile.seek(paramLong * this.dataFileScale);
      return this.dataFile.readInt();
    } catch (Throwable throwable) {
      logSevereEvent("DataFileCache.readSize", throwable, paramLong);
      throw Error.error(466, throwable);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  private void readObject(long paramLong) {
    try {
      this.dataFile.seek(paramLong * this.dataFileScale);
      int i = this.dataFile.readInt();
      this.rowIn.resetRow(paramLong, i);
      this.dataFile.read(this.rowIn.getBuffer(), 4, i - 4);
    } catch (Throwable throwable) {
      logSevereEvent("DataFileCache.readObject", throwable, paramLong);
      HsqlException hsqlException = Error.error(466, throwable);
      if (this.rowIn.getPos() != paramLong)
        this.rowIn.resetRow(paramLong, 0); 
      hsqlException.info = this.rowIn;
      throw hsqlException;
    } 
  }
  
  protected void readObject(long paramLong, int paramInt) {
    try {
      this.rowIn.resetBlock(paramLong, paramInt);
      this.dataFile.seek(paramLong * this.dataFileScale);
      this.dataFile.read(this.rowIn.getBuffer(), 0, paramInt);
    } catch (Throwable throwable) {
      logSevereEvent("DataFileCache.readObject", throwable, paramLong);
      HsqlException hsqlException = Error.error(466, throwable);
      hsqlException.info = this.rowIn;
      throw hsqlException;
    } 
  }
  
  public void releaseRange(long paramLong1, long paramLong2) {
    this.writeLock.lock();
    try {
      Iterator iterator = this.cache.getIterator();
      while (iterator.hasNext()) {
        CachedObject cachedObject = (CachedObject)iterator.next();
        long l = cachedObject.getPos();
        if (l >= paramLong1 && l < paramLong2) {
          cachedObject.setInMemory(false);
          iterator.remove();
        } 
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public CachedObject release(long paramLong) {
    this.writeLock.lock();
    try {
      return this.cache.release(paramLong);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  protected void saveRows(CachedObject[] paramArrayOfCachedObject, int paramInt1, int paramInt2) {
    if (paramInt2 == 0)
      return; 
    copyShadow(paramArrayOfCachedObject, paramInt1, paramInt2);
    setFileModified();
    for (int i = paramInt1; i < paramInt1 + paramInt2; i++) {
      CachedObject cachedObject = paramArrayOfCachedObject[i];
      saveRowNoLock(cachedObject);
      paramArrayOfCachedObject[i] = null;
    } 
  }
  
  public void saveRow(CachedObject paramCachedObject) {
    this.writeLock.lock();
    try {
      copyShadow(paramCachedObject);
      setFileModified();
      saveRowNoLock(paramCachedObject);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void saveRowOutput(long paramLong) {
    try {
      this.dataFile.seek(paramLong * this.dataFileScale);
      this.dataFile.write(this.rowOut.getOutputStream().getBuffer(), 0, this.rowOut.getOutputStream().size());
    } catch (Throwable throwable) {
      logSevereEvent("DataFileCache.saveRowOutput", throwable, paramLong);
      throw Error.error(466, throwable);
    } 
  }
  
  protected void saveRowNoLock(CachedObject paramCachedObject) {
    try {
      this.rowOut.reset();
      paramCachedObject.write(this.rowOut);
      this.dataFile.seek(paramCachedObject.getPos() * this.dataFileScale);
      this.dataFile.write(this.rowOut.getOutputStream().getBuffer(), 0, this.rowOut.getOutputStream().size());
    } catch (Throwable throwable) {
      logSevereEvent("DataFileCache.saveRowNoLock", throwable, paramCachedObject.getPos());
      throw Error.error(466, throwable);
    } 
  }
  
  protected void copyShadow(CachedObject[] paramArrayOfCachedObject, int paramInt1, int paramInt2) {
    if (this.shadowFile != null) {
      long l1 = this.cache.saveAllTimer.elapsedTime();
      long l2 = 0L;
      try {
        for (int i = paramInt1; i < paramInt1 + paramInt2; i++) {
          CachedObject cachedObject = paramArrayOfCachedObject[i];
          l2 = cachedObject.getPos() * this.dataFileScale;
          this.shadowFile.copy(l2, cachedObject.getStorageSize());
        } 
        this.shadowFile.synch();
      } catch (Throwable throwable) {
        logSevereEvent("DataFileCache.copyShadow", throwable, l2);
        throw Error.error(466, throwable);
      } 
      l1 = this.cache.saveAllTimer.elapsedTime() - l1;
      logDetailEvent("copyShadow [size, time] " + this.shadowFile.getSavedLength() + " " + l1);
    } 
  }
  
  protected void copyShadow(CachedObject paramCachedObject) {
    if (this.shadowFile != null) {
      long l = paramCachedObject.getPos() * this.dataFileScale;
      try {
        this.shadowFile.copy(l, paramCachedObject.getStorageSize());
        this.shadowFile.synch();
      } catch (Throwable throwable) {
        logSevereEvent("DataFileCache.copyShadow", throwable, paramCachedObject.getPos());
        throw Error.error(466, throwable);
      } 
    } 
  }
  
  void backupDataFile(boolean paramBoolean) {
    backupFile(this.database, this.dataFileName, this.backupFileName, paramBoolean);
  }
  
  void backupNewDataFile(boolean paramBoolean) {
    backupFile(this.database, this.dataFileName + ".new", this.backupFileName, paramBoolean);
  }
  
  static void backupFile(Database paramDatabase, String paramString1, String paramString2, boolean paramBoolean) {
    try {
      FileAccess fileAccess = paramDatabase.logger.getFileAccess();
      if (paramDatabase.logger.propIncrementBackup) {
        if (fileAccess.isStreamElement(paramString2)) {
          deleteFile(paramDatabase, paramString2);
          if (fileAccess.isStreamElement(paramString2))
            throw Error.error(466, "cannot delete old backup file"); 
        } 
        return;
      } 
      if (fileAccess.isStreamElement(paramString1)) {
        if (paramBoolean) {
          paramString2 = paramString2 + ".new";
        } else {
          deleteFile(paramDatabase, paramString2);
          if (fileAccess.isStreamElement(paramString2))
            throw Error.error(466, "cannot delete old backup file"); 
        } 
        FileArchiver.archive(paramString1, paramString2, fileAccess, 1);
      } 
    } catch (Throwable throwable) {
      paramDatabase.logger.logSevereEvent("DataFileCache.backupFile", throwable);
      throw Error.error(466, throwable);
    } 
  }
  
  void renameBackupFile() {
    renameBackupFile(this.database, this.backupFileName);
  }
  
  static void renameBackupFile(Database paramDatabase, String paramString) {
    FileAccess fileAccess = paramDatabase.logger.getFileAccess();
    if (paramDatabase.logger.propIncrementBackup) {
      deleteFile(paramDatabase, paramString);
      return;
    } 
    if (fileAccess.isStreamElement(paramString + ".new")) {
      deleteFile(paramDatabase, paramString);
      fileAccess.renameElement(paramString + ".new", paramString);
    } 
  }
  
  void renameDataFile() {
    renameDataFile(this.database, this.dataFileName);
  }
  
  static void renameDataFile(Database paramDatabase, String paramString) {
    FileAccess fileAccess = paramDatabase.logger.getFileAccess();
    if (fileAccess.isStreamElement(paramString + ".new")) {
      deleteFile(paramDatabase, paramString);
      fileAccess.renameElement(paramString + ".new", paramString);
    } 
  }
  
  void deleteFile() {
    deleteFile(this.database, this.dataFileName);
  }
  
  static void deleteFile(Database paramDatabase, String paramString) {
    FileAccess fileAccess = paramDatabase.logger.getFileAccess();
    fileAccess.removeElement(paramString);
    if (paramDatabase.logger.isStoredFileAccess())
      return; 
    if (fileAccess.isStreamElement(paramString)) {
      paramDatabase.logger.log.deleteOldDataFiles();
      fileAccess.removeElement(paramString);
      if (fileAccess.isStreamElement(paramString)) {
        String str = FileUtil.newDiscardFileName(paramString);
        fileAccess.renameElement(paramString, str);
      } 
    } 
  }
  
  void deleteBackup() {
    deleteFile(this.database, this.backupFileName);
  }
  
  public long enlargeFileSpace(long paramLong) {
    this.writeLock.lock();
    try {
      long l = this.fileFreePosition;
      if (l + paramLong > this.maxDataFileSize) {
        logSevereEvent("data file reached maximum size " + this.dataFileName, null);
        throw Error.error(468);
      } 
      boolean bool = this.dataFile.ensureLength(l + paramLong);
      if (!bool) {
        logSevereEvent("data file cannot be enlarged - disk spacee " + this.dataFileName, null);
        throw Error.error(468);
      } 
      this.fileFreePosition += paramLong;
      return l;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public int capacity() {
    return this.maxCacheRows;
  }
  
  public long bytesCapacity() {
    return this.maxCacheBytes;
  }
  
  public long getTotalCachedBlockSize() {
    return this.cache.getTotalCachedBlockSize();
  }
  
  public long getLostBlockSize() {
    this.readLock.lock();
    try {
      return this.spaceManager.getLostBlocksSize();
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public long getFileFreePos() {
    return this.fileFreePosition;
  }
  
  public int getCachedObjectCount() {
    return this.cache.size();
  }
  
  public int getAccessCount() {
    return this.cache.incrementAccessCount();
  }
  
  public String getFileName() {
    return this.dataFileName;
  }
  
  public int getDataFileScale() {
    return this.dataFileScale;
  }
  
  public boolean hasRowInfo() {
    return this.hasRowInfo;
  }
  
  public boolean isFileModified() {
    return this.fileModified;
  }
  
  public boolean isModified() {
    return this.cacheModified;
  }
  
  public boolean isFileOpen() {
    return (this.dataFile != null);
  }
  
  protected void setFileModified() {
    try {
      if (!this.fileModified) {
        long l = this.cache.saveAllTimer.elapsedTime();
        this.dataFile.seek(28L);
        int i = this.dataFile.readInt();
        i = BitMap.unset(i, 2);
        this.dataFile.seek(28L);
        this.dataFile.writeInt(i);
        this.dataFile.synch();
        logDetailEvent("setFileModified flag set ");
        this.fileModified = true;
      } 
    } catch (Throwable throwable) {
      logSevereEvent("DataFileCache.setFileModified", throwable);
      throw Error.error(466, throwable);
    } 
  }
  
  public int getFlags() {
    try {
      this.dataFile.seek(28L);
      return this.dataFile.readInt();
    } catch (Throwable throwable) {
      logSevereEvent("DataFileCache.setFlags", throwable);
      return 0;
    } 
  }
  
  public boolean isDataReadOnly() {
    return this.cacheReadonly;
  }
  
  public RAShadowFile getShadowFile() {
    return this.shadowFile;
  }
  
  private void logSevereEvent(String paramString, Throwable paramThrowable, long paramLong) {
    if (this.logEvents) {
      StringBuffer stringBuffer = new StringBuffer(paramString);
      stringBuffer.append(' ').append(paramLong);
      paramString = stringBuffer.toString();
      this.database.logger.logSevereEvent(paramString, paramThrowable);
    } 
  }
  
  void logSevereEvent(String paramString, Throwable paramThrowable) {
    if (this.logEvents)
      this.database.logger.logSevereEvent(paramString, paramThrowable); 
  }
  
  void logInfoEvent(String paramString) {
    if (this.logEvents)
      this.database.logger.logInfoEvent(paramString); 
  }
  
  void logDetailEvent(String paramString) {
    if (this.logEvents)
      this.database.logger.logDetailEvent(paramString); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\DataFileCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */