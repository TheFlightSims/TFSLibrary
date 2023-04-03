package org.hsqldb.persist;

import org.hsqldb.Row;
import org.hsqldb.RowAction;
import org.hsqldb.Session;
import org.hsqldb.TableBase;
import org.hsqldb.index.Index;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.rowio.RowInputInterface;

public interface PersistentStore {
  public static final int SHORT_STORE_SIZE = 2;
  
  public static final int INT_STORE_SIZE = 4;
  
  public static final int LONG_STORE_SIZE = 8;
  
  public static final PersistentStore[] emptyArray = new PersistentStore[0];
  
  TableBase getTable();
  
  long getTimestamp();
  
  void setTimestamp(long paramLong);
  
  boolean isMemory();
  
  void setMemory(boolean paramBoolean);
  
  int getAccessCount();
  
  void set(CachedObject paramCachedObject);
  
  CachedObject get(long paramLong);
  
  CachedObject get(long paramLong, boolean paramBoolean);
  
  CachedObject get(CachedObject paramCachedObject, boolean paramBoolean);
  
  void add(Session paramSession, CachedObject paramCachedObject, boolean paramBoolean);
  
  boolean canRead(Session paramSession, long paramLong, int paramInt, int[] paramArrayOfint);
  
  boolean canRead(Session paramSession, CachedObject paramCachedObject, int paramInt, int[] paramArrayOfint);
  
  CachedObject get(RowInputInterface paramRowInputInterface);
  
  CachedObject get(CachedObject paramCachedObject, RowInputInterface paramRowInputInterface);
  
  CachedObject getNewInstance(int paramInt);
  
  int getDefaultObjectSize();
  
  CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean);
  
  void removeAll();
  
  void remove(CachedObject paramCachedObject);
  
  void commitPersistence(CachedObject paramCachedObject);
  
  void delete(Session paramSession, Row paramRow);
  
  void indexRow(Session paramSession, Row paramRow);
  
  void commitRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2);
  
  void rollbackRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2);
  
  void postCommitAction(Session paramSession, RowAction paramRowAction);
  
  void indexRows(Session paramSession);
  
  RowIterator rowIterator();
  
  DataFileCache getCache();
  
  void setCache(DataFileCache paramDataFileCache);
  
  TableSpaceManager getSpaceManager();
  
  void setSpaceManager(TableSpaceManager paramTableSpaceManager);
  
  void release();
  
  PersistentStore getAccessorStore(Index paramIndex);
  
  CachedObject getAccessor(Index paramIndex);
  
  void setAccessor(Index paramIndex, CachedObject paramCachedObject);
  
  void setAccessor(Index paramIndex, long paramLong);
  
  double searchCost(Session paramSession, Index paramIndex, int paramInt1, int paramInt2);
  
  long elementCount();
  
  long elementCount(Session paramSession);
  
  long elementCountUnique(Index paramIndex);
  
  void setElementCount(Index paramIndex, long paramLong1, long paramLong2);
  
  boolean hasNull(int paramInt);
  
  void resetAccessorKeys(Session paramSession, Index[] paramArrayOfIndex);
  
  Index[] getAccessorKeys();
  
  void moveDataToSpace();
  
  void moveData(Session paramSession, PersistentStore paramPersistentStore, int paramInt1, int paramInt2);
  
  void reindex(Session paramSession, Index paramIndex);
  
  void setReadOnly(boolean paramBoolean);
  
  void writeLock();
  
  void writeUnlock();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\PersistentStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */