package org.hsqldb.persist;

import org.hsqldb.HsqlException;
import org.hsqldb.Row;
import org.hsqldb.RowAction;
import org.hsqldb.Session;
import org.hsqldb.TableBase;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.rowio.RowInputInterface;

public abstract class SimpleStore implements PersistentStore {
  public DataFileCache cache;
  
  protected TableSpaceManager spaceManager;
  
  long storageSize;
  
  protected int defaultObjectSize;
  
  public void set(CachedObject paramCachedObject) {}
  
  public CachedObject get(long paramLong) {
    return this.cache.get(paramLong, this, false);
  }
  
  public CachedObject get(CachedObject paramCachedObject, boolean paramBoolean) {
    return this.cache.get(paramCachedObject, this, paramBoolean);
  }
  
  public CachedObject get(long paramLong, boolean paramBoolean) {
    return this.cache.get(paramLong, this, paramBoolean);
  }
  
  public void remove(CachedObject paramCachedObject) {
    if (this.cache != null) {
      this.cache.remove(paramCachedObject);
      this.spaceManager.release(paramCachedObject.getPos(), paramCachedObject.getStorageSize());
    } 
  }
  
  public boolean canRead(Session paramSession, long paramLong, int paramInt, int[] paramArrayOfint) {
    return true;
  }
  
  public boolean canRead(Session paramSession, CachedObject paramCachedObject, int paramInt, int[] paramArrayOfint) {
    return true;
  }
  
  public void commitPersistence(CachedObject paramCachedObject) {}
  
  public int getDefaultObjectSize() {
    return this.defaultObjectSize;
  }
  
  public CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean) throws HsqlException {
    throw Error.runtimeError(201, "PersistentStore");
  }
  
  public void removeAll() {}
  
  public DataFileCache getCache() {
    return this.cache;
  }
  
  public TableSpaceManager getSpaceManager() {
    return this.spaceManager;
  }
  
  public void setSpaceManager(TableSpaceManager paramTableSpaceManager) {
    this.spaceManager = paramTableSpaceManager;
  }
  
  public boolean isMemory() {
    return (this.cache == null);
  }
  
  public void reindex(Session paramSession, Index paramIndex) {}
  
  public void setCache(DataFileCache paramDataFileCache) {
    this.cache = paramDataFileCache;
  }
  
  public void release() {}
  
  public PersistentStore getAccessorStore(Index paramIndex) {
    return null;
  }
  
  public CachedObject getAccessor(Index paramIndex) {
    return null;
  }
  
  public double searchCost(Session paramSession, Index paramIndex, int paramInt1, int paramInt2) {
    return 1.0D;
  }
  
  public long elementCount() {
    return 0L;
  }
  
  public long elementCount(Session paramSession) {
    return 0L;
  }
  
  public long elementCountUnique(Index paramIndex) {
    return 0L;
  }
  
  public void setElementCount(Index paramIndex, long paramLong1, long paramLong2) {}
  
  public void setAccessor(Index paramIndex, long paramLong) {}
  
  public void setAccessor(Index paramIndex, CachedObject paramCachedObject) {}
  
  public boolean hasNull(int paramInt) {
    return false;
  }
  
  public void resetAccessorKeys(Session paramSession, Index[] paramArrayOfIndex) throws HsqlException {}
  
  public void setMemory(boolean paramBoolean) {}
  
  public void delete(Session paramSession, Row paramRow) {}
  
  public CachedObject get(CachedObject paramCachedObject, RowInputInterface paramRowInputInterface) {
    return paramCachedObject;
  }
  
  public void indexRow(Session paramSession, Row paramRow) throws HsqlException {}
  
  public void indexRows(Session paramSession) throws HsqlException {}
  
  public RowIterator rowIterator() {
    return null;
  }
  
  public int getAccessCount() {
    return 0;
  }
  
  public Index[] getAccessorKeys() {
    return null;
  }
  
  public void moveDataToSpace() {}
  
  public void moveData(Session paramSession, PersistentStore paramPersistentStore, int paramInt1, int paramInt2) {}
  
  public void setReadOnly(boolean paramBoolean) {}
  
  public void writeLock() {}
  
  public void writeUnlock() {}
  
  public TableBase getTable() {
    return null;
  }
  
  public long getTimestamp() {
    return 0L;
  }
  
  public void setTimestamp(long paramLong) {}
  
  public void commitRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2) {}
  
  public void rollbackRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2) {}
  
  public void postCommitAction(Session paramSession, RowAction paramRowAction) {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\SimpleStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */