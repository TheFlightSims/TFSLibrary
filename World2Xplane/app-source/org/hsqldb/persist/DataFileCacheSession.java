package org.hsqldb.persist;

import org.hsqldb.Database;
import org.hsqldb.Row;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.Iterator;

public class DataFileCacheSession extends DataFileCache {
  public DataFileCacheSession(Database paramDatabase, String paramString) {
    super(paramDatabase, paramString);
  }
  
  protected void initParams(Database paramDatabase, String paramString, boolean paramBoolean) {
    this.dataFileName = paramString + ".data.tmp";
    this.database = paramDatabase;
    this.fa = (FileAccess)FileUtil.getFileUtil();
    this.dataFileScale = 64;
    this.cachedRowPadding = this.dataFileScale;
    this.initialFreePos = this.dataFileScale;
    this.maxCacheRows = 2048;
    this.maxCacheBytes = (this.maxCacheRows * 1024);
    this.maxDataFileSize = 2147483647L * this.dataFileScale;
  }
  
  public void open(boolean paramBoolean) {
    try {
      this.dataFile = new RAFile(this.database, this.dataFileName, false, false, false);
      this.fileFreePosition = this.initialFreePos;
      initBuffers();
      this.spaceManager = new DataSpaceManagerSimple(this);
    } catch (Throwable throwable) {
      this.database.logger.logWarningEvent("Failed to open Session RA file", throwable);
      release();
      throw Error.error(throwable, 452, 52, new Object[] { throwable.toString(), this.dataFileName });
    } 
  }
  
  protected void setFileModified() {}
  
  public void close() {
    this.writeLock.lock();
    try {
      clear();
      if (this.dataFile != null) {
        this.dataFile.close();
        this.dataFile = null;
        this.fa.removeElement(this.dataFileName);
      } 
    } catch (Throwable throwable) {
      this.database.logger.logWarningEvent("Failed to close Session RA file", throwable);
      throw Error.error(throwable, 452, 53, new Object[] { throwable.toString(), this.dataFileName });
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void adjustStoreCount(int paramInt) {
    this.writeLock.lock();
    try {
      this.storeCount += paramInt;
      if (this.storeCount == 0)
        clear(); 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  protected void clear() {
    Iterator iterator = this.cache.getIterator();
    while (iterator.hasNext()) {
      Row row = (Row)iterator.next();
      row.setInMemory(false);
      row.destroy();
    } 
    this.cache.clear();
    this.fileStartFreePosition = this.fileFreePosition = this.initialFreePos;
    initBuffers();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\DataFileCacheSession.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */