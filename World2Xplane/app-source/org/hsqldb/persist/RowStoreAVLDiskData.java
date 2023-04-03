package org.hsqldb.persist;

import java.io.IOException;
import org.hsqldb.HsqlException;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.RowAVLDiskData;
import org.hsqldb.RowAction;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class RowStoreAVLDiskData extends RowStoreAVL {
  DataFileCache cache;
  
  RowOutputInterface rowOut;
  
  public RowStoreAVLDiskData(PersistentStoreCollection paramPersistentStoreCollection, Table paramTable) {
    this.database = paramTable.database;
    this.manager = paramPersistentStoreCollection;
    this.table = (TableBase)paramTable;
    this.indexList = paramTable.getIndexList();
    this.accessorList = new CachedObject[this.indexList.length];
    paramPersistentStoreCollection.setStore(paramTable, this);
  }
  
  public CachedObject get(long paramLong, boolean paramBoolean) {
    return this.cache.get(paramLong, this, paramBoolean);
  }
  
  public CachedObject get(CachedObject paramCachedObject, boolean paramBoolean) {
    return this.cache.get(paramCachedObject, this, paramBoolean);
  }
  
  public void add(Session paramSession, CachedObject paramCachedObject, boolean paramBoolean) {
    this.cache.writeLock.lock();
    try {
      int i = paramCachedObject.getRealSize(this.cache.rowOut);
      paramCachedObject.setStorageSize(i);
      long l = this.tableSpace.getFilePosition(i, false);
      paramCachedObject.setPos(l);
      if (paramBoolean)
        RowAction.addInsertAction(paramSession, this.table, (Row)paramCachedObject); 
      this.cache.add(paramCachedObject);
    } finally {
      this.cache.writeLock.unlock();
    } 
  }
  
  public CachedObject get(RowInputInterface paramRowInputInterface) {
    try {
      RowAVLDiskData rowAVLDiskData = new RowAVLDiskData(this, this.table, paramRowInputInterface);
      rowAVLDiskData.setPos(paramRowInputInterface.getPos());
      rowAVLDiskData.setStorageSize(paramRowInputInterface.getSize());
      rowAVLDiskData.setChanged(false);
      ((TextCache)this.cache).addInit((CachedObject)rowAVLDiskData);
      return (CachedObject)rowAVLDiskData;
    } catch (IOException iOException) {
      throw Error.error(484, iOException);
    } 
  }
  
  public CachedObject get(CachedObject paramCachedObject, RowInputInterface paramRowInputInterface) {
    try {
      ((RowAVLDiskData)paramCachedObject).getRowData(this.table, paramRowInputInterface);
      return paramCachedObject;
    } catch (IOException iOException) {
      throw Error.error(484, iOException);
    } 
  }
  
  public CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean) {
    RowAVLDiskData rowAVLDiskData = new RowAVLDiskData(this, this.table, (Object[])paramObject);
    add(paramSession, (CachedObject)rowAVLDiskData, paramBoolean);
    return (CachedObject)rowAVLDiskData;
  }
  
  public void indexRow(Session paramSession, Row paramRow) {
    super.indexRow(paramSession, paramRow);
  }
  
  public boolean isMemory() {
    return false;
  }
  
  public int getAccessCount() {
    return this.cache.getAccessCount();
  }
  
  public void set(CachedObject paramCachedObject) {}
  
  public CachedObject get(long paramLong) {
    return this.cache.get(paramLong, this, false);
  }
  
  public void removeAll() {
    destroy();
    this.elementCount.set(0L);
    ArrayUtil.fillArray((Object[])this.accessorList, null);
  }
  
  public void remove(CachedObject paramCachedObject) {
    this.cache.remove(paramCachedObject);
  }
  
  public CachedObject getAccessor(Index paramIndex) {
    int i = paramIndex.getPosition();
    if (i >= this.accessorList.length)
      throw Error.runtimeError(201, "RowStoreAVL"); 
    return this.accessorList[i];
  }
  
  public void commitPersistence(CachedObject paramCachedObject) {
    try {
      this.cache.saveRow(paramCachedObject);
    } catch (HsqlException hsqlException) {}
  }
  
  public void commitRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2) {
    switch (paramInt1) {
      case 2:
        this.cache.removePersistence((CachedObject)paramRow);
        break;
      case 1:
        commitPersistence((CachedObject)paramRow);
        break;
      case 4:
        if (paramInt2 == 0) {
          remove((CachedObject)paramRow);
          break;
        } 
        delete(paramSession, paramRow);
        remove((CachedObject)paramRow);
        break;
      case 3:
        if (paramInt2 != 0) {
          delete(paramSession, paramRow);
          remove((CachedObject)paramRow);
        } 
        break;
    } 
  }
  
  public void rollbackRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2) {
    switch (paramInt1) {
      case 2:
        if (paramInt2 == 0) {
          ((RowAVL)paramRow).setNewNodes(this);
          indexRow(paramSession, paramRow);
        } 
        break;
      case 1:
        if (paramInt2 == 0) {
          delete(paramSession, paramRow);
          remove((CachedObject)paramRow);
        } 
        break;
      case 4:
        if (paramInt2 == 0) {
          remove((CachedObject)paramRow);
          break;
        } 
        delete(paramSession, paramRow);
        remove((CachedObject)paramRow);
        break;
    } 
  }
  
  public DataFileCache getCache() {
    return this.cache;
  }
  
  public void setCache(DataFileCache paramDataFileCache) {
    this.cache = paramDataFileCache;
    this.tableSpace = paramDataFileCache.spaceManager.getTableSpace(7);
  }
  
  public void release() {
    destroy();
    ArrayUtil.fillArray((Object[])this.accessorList, null);
    this.table.database.logger.closeTextCache((Table)this.table);
    this.cache = null;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\RowStoreAVLDiskData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */