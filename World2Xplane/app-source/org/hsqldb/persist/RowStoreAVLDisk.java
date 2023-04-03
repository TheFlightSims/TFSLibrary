package org.hsqldb.persist;

import java.io.IOException;
import org.hsqldb.HsqlException;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.RowAVLDisk;
import org.hsqldb.RowAVLDiskLarge;
import org.hsqldb.RowAction;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.index.NodeAVL;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.DoubleIntIndex;
import org.hsqldb.lib.LongLookup;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class RowStoreAVLDisk extends RowStoreAVL implements PersistentStore {
  DataFileCache cache;
  
  RowOutputInterface rowOut;
  
  boolean largeData;
  
  public RowStoreAVLDisk(PersistentStoreCollection paramPersistentStoreCollection, DataFileCache paramDataFileCache, Table paramTable) {
    this(paramPersistentStoreCollection, paramTable);
    this.cache = paramDataFileCache;
    this.rowOut = paramDataFileCache.rowOut.duplicate();
    paramDataFileCache.adjustStoreCount(1);
    this.largeData = this.database.logger.propLargeData;
    this.tableSpace = paramDataFileCache.spaceManager.getTableSpace(paramTable.getSpaceID());
  }
  
  protected RowStoreAVLDisk(PersistentStoreCollection paramPersistentStoreCollection, Table paramTable) {
    this.database = paramTable.database;
    this.manager = paramPersistentStoreCollection;
    this.table = (TableBase)paramTable;
    this.indexList = paramTable.getIndexList();
    this.accessorList = new CachedObject[this.indexList.length];
    paramPersistentStoreCollection.setStore(paramTable, this);
    this.largeData = (this.database.logger.getDataFileFactor() > 1);
  }
  
  public boolean isMemory() {
    return false;
  }
  
  public int getAccessCount() {
    return this.cache.getAccessCount();
  }
  
  public void set(CachedObject paramCachedObject) {
    this.database.txManager.setTransactionInfo(this, paramCachedObject);
  }
  
  public CachedObject get(long paramLong) {
    return this.cache.get(paramLong, this, false);
  }
  
  public CachedObject get(long paramLong, boolean paramBoolean) {
    return this.cache.get(paramLong, this, paramBoolean);
  }
  
  public CachedObject get(CachedObject paramCachedObject, boolean paramBoolean) {
    return this.cache.get(paramCachedObject, this, paramBoolean);
  }
  
  public void add(Session paramSession, CachedObject paramCachedObject, boolean paramBoolean) {
    int i = paramCachedObject.getRealSize(this.rowOut);
    i += this.indexList.length * 16;
    i = this.rowOut.getStorageSize(i);
    paramCachedObject.setStorageSize(i);
    long l = this.tableSpace.getFilePosition(i, false);
    paramCachedObject.setPos(l);
    if (paramBoolean) {
      Row row = (Row)paramCachedObject;
      RowAction rowAction = new RowAction(paramSession, this.table, (byte)1, row, null);
      row.rowAction = rowAction;
      this.database.txManager.addTransactionInfo(paramCachedObject);
    } 
    this.cache.add(paramCachedObject);
    this.storageSize += i;
  }
  
  public CachedObject get(RowInputInterface paramRowInputInterface) {
    try {
      return (CachedObject)(this.largeData ? new RowAVLDiskLarge(this.table, paramRowInputInterface) : new RowAVLDisk(this.table, paramRowInputInterface));
    } catch (IOException iOException) {
      throw Error.error(466, iOException);
    } 
  }
  
  public CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean) {
    RowAVLDisk rowAVLDisk;
    if (this.largeData) {
      RowAVLDiskLarge rowAVLDiskLarge = new RowAVLDiskLarge(this.table, (Object[])paramObject, this);
    } else {
      rowAVLDisk = new RowAVLDisk(this.table, (Object[])paramObject, this);
    } 
    add(paramSession, (CachedObject)rowAVLDisk, paramBoolean);
    return (CachedObject)rowAVLDisk;
  }
  
  public void indexRow(Session paramSession, Row paramRow) {
    try {
      paramRow = (Row)get((CachedObject)paramRow, true);
      super.indexRow(paramSession, paramRow);
      paramRow.keepInMemory(false);
    } catch (HsqlException hsqlException) {
      this.database.txManager.removeTransactionInfo((CachedObject)paramRow);
      throw hsqlException;
    } 
  }
  
  public void removeAll() {
    this.elementCount.set(0L);
    ArrayUtil.fillArray((Object[])this.accessorList, null);
    this.cache.spaceManager.freeTableSpace(this.tableSpace.getSpaceID());
  }
  
  public void remove(CachedObject paramCachedObject) {
    this.cache.remove(paramCachedObject);
    this.tableSpace.release(paramCachedObject.getPos(), paramCachedObject.getStorageSize());
    this.storageSize -= paramCachedObject.getStorageSize();
  }
  
  public void commitPersistence(CachedObject paramCachedObject) {}
  
  public void commitRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2) {
    Object[] arrayOfObject = paramRow.getData();
    switch (paramInt1) {
      case 2:
        this.database.logger.writeDeleteStatement(paramSession, (Table)this.table, arrayOfObject);
        if (paramInt2 == 0)
          remove((CachedObject)paramRow); 
        break;
      case 1:
        this.database.logger.writeInsertStatement(paramSession, paramRow, (Table)this.table);
        break;
      case 4:
        if (paramInt2 == 0)
          remove((CachedObject)paramRow); 
        break;
      case 3:
        delete(paramSession, paramRow);
        this.database.txManager.removeTransactionInfo((CachedObject)paramRow);
        remove((CachedObject)paramRow);
        break;
    } 
  }
  
  public void rollbackRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2) {
    switch (paramInt1) {
      case 2:
        if (paramInt2 == 0) {
          paramRow = (Row)get((CachedObject)paramRow, true);
          ((RowAVL)paramRow).setNewNodes(this);
          paramRow.keepInMemory(false);
          indexRow(paramSession, paramRow);
        } 
        break;
      case 1:
        delete(paramSession, paramRow);
        this.database.txManager.removeTransactionInfo((CachedObject)paramRow);
        remove((CachedObject)paramRow);
        break;
      case 4:
        if (paramInt2 != 0) {
          delete(paramSession, paramRow);
          this.database.txManager.removeTransactionInfo((CachedObject)paramRow);
        } 
        remove((CachedObject)paramRow);
        break;
    } 
  }
  
  public void postCommitAction(Session paramSession, RowAction paramRowAction) {
    this.database.txManager.removeTransactionInfo(paramRowAction.getPos());
  }
  
  public DataFileCache getCache() {
    return this.cache;
  }
  
  public void setCache(DataFileCache paramDataFileCache) {
    throw Error.runtimeError(201, "RowStoreAVLDisk");
  }
  
  public void release() {
    ArrayUtil.fillArray((Object[])this.accessorList, null);
    this.cache.adjustStoreCount(-1);
    this.cache = null;
    this.elementCount.set(0L);
  }
  
  public CachedObject getAccessor(Index paramIndex) {
    NodeAVL nodeAVL = (NodeAVL)this.accessorList[paramIndex.getPosition()];
    if (nodeAVL == null)
      return null; 
    RowAVL rowAVL = (RowAVL)get((CachedObject)nodeAVL.getRow(this), false);
    nodeAVL = rowAVL.getNode(paramIndex.getPosition());
    this.accessorList[paramIndex.getPosition()] = (CachedObject)nodeAVL;
    return (CachedObject)nodeAVL;
  }
  
  public void setAccessor(Index paramIndex, long paramLong) {
    NodeAVL nodeAVL;
    CachedObject cachedObject = get(paramLong, false);
    if (cachedObject != null) {
      NodeAVL nodeAVL1 = ((RowAVL)cachedObject).getNode(paramIndex.getPosition());
      nodeAVL = nodeAVL1;
    } 
    setAccessor(paramIndex, (CachedObject)nodeAVL);
  }
  
  public void resetAccessorKeys(Session paramSession, Index[] paramArrayOfIndex) {
    if (this.indexList.length == 0 || this.accessorList[0] == null) {
      this.indexList = paramArrayOfIndex;
      this.accessorList = new CachedObject[this.indexList.length];
      return;
    } 
    throw Error.runtimeError(201, "RowStoreAVLDisk");
  }
  
  public void setReadOnly(boolean paramBoolean) {}
  
  public void moveDataToSpace() {
    Table table = (Table)this.table;
    long l = elementCount();
    if (l == 0L)
      return; 
    if (l > 2147483647L)
      return; 
    DoubleIntIndex doubleIntIndex = new DoubleIntIndex((int)l, true);
    doubleIntIndex.setKeysSearchTarget();
    this.cache.writeLock.lock();
    try {
      moveDataToSpace(this, this.cache, this.tableSpace, (LongLookup)doubleIntIndex);
      CachedObject[] arrayOfCachedObject = new CachedObject[this.accessorList.length];
      for (byte b = 0; b < this.accessorList.length; b++) {
        long l1 = doubleIntIndex.lookup(this.accessorList[b].getPos());
        if (l1 == -1L)
          throw Error.error(466); 
        arrayOfCachedObject[b] = this.cache.get(l1, this, false);
      } 
      RowIterator rowIterator = rowIterator();
      while (rowIterator.hasNext()) {
        Row row = rowIterator.getNextRow();
        this.cache.remove((CachedObject)row);
        this.tableSpace.release(row.getPos(), row.getStorageSize());
      } 
      this.accessorList = arrayOfCachedObject;
    } finally {
      this.cache.writeLock.unlock();
    } 
    this.database.logger.logDetailEvent("table written " + (table.getName()).name);
  }
  
  public static void moveDataToSpace(PersistentStore paramPersistentStore, DataFileCache paramDataFileCache, TableSpaceManager paramTableSpaceManager, LongLookup paramLongLookup) {
    RowIterator rowIterator = paramPersistentStore.rowIterator();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.getNextRow();
      long l = paramTableSpaceManager.getFilePosition(row.getStorageSize(), false);
      paramLongLookup.addUnsorted(row.getPos(), l);
    } 
    rowIterator = paramPersistentStore.rowIterator();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.getNextRow();
      paramDataFileCache.rowOut.reset();
      row.write(paramDataFileCache.rowOut, paramLongLookup);
      long l = paramLongLookup.lookup(row.getPos());
      paramDataFileCache.saveRowOutput(l);
    } 
  }
  
  long getStorageSizeEstimate() {
    if (this.elementCount.get() == 0L)
      return 0L; 
    CachedObject cachedObject1 = getAccessor(this.indexList[0]);
    CachedObject cachedObject2 = get(cachedObject1.getPos());
    return cachedObject2.getStorageSize() * this.elementCount.get();
  }
  
  public void writeLock() {
    this.cache.writeLock.lock();
  }
  
  public void writeUnlock() {
    this.cache.writeLock.unlock();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\RowStoreAVLDisk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */