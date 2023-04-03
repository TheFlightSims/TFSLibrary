package org.hsqldb.persist;

import org.hsqldb.Database;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.RowAction;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.rowio.RowInputInterface;

public class RowStoreAVLMemory extends RowStoreAVL implements PersistentStore {
  Database database;
  
  int rowIdSequence = 0;
  
  public RowStoreAVLMemory(PersistentStoreCollection paramPersistentStoreCollection, Table paramTable) {
    this.database = paramTable.database;
    this.manager = paramPersistentStoreCollection;
    this.table = (TableBase)paramTable;
    this.indexList = paramTable.getIndexList();
    this.accessorList = new CachedObject[this.indexList.length];
    paramPersistentStoreCollection.setStore(paramTable, this);
  }
  
  public boolean isMemory() {
    return true;
  }
  
  public int getAccessCount() {
    return 0;
  }
  
  public void set(CachedObject paramCachedObject) {}
  
  public CachedObject get(long paramLong) {
    throw Error.runtimeError(201, "RowStoreAVMemory");
  }
  
  public CachedObject get(long paramLong, boolean paramBoolean) {
    throw Error.runtimeError(201, "RowStoreAVLMemory");
  }
  
  public CachedObject get(CachedObject paramCachedObject, boolean paramBoolean) {
    return paramCachedObject;
  }
  
  public void add(Session paramSession, CachedObject paramCachedObject, boolean paramBoolean) {}
  
  public CachedObject get(RowInputInterface paramRowInputInterface) {
    return null;
  }
  
  public CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean) {
    int i;
    synchronized (this) {
      i = this.rowIdSequence++;
    } 
    RowAVL rowAVL = new RowAVL(this.table, (Object[])paramObject, i, this);
    if (paramBoolean) {
      RowAction rowAction = new RowAction(paramSession, this.table, (byte)1, (Row)rowAVL, null);
      ((Row)rowAVL).rowAction = rowAction;
    } 
    return (CachedObject)rowAVL;
  }
  
  public void removeAll() {
    destroy();
    this.elementCount.set(0L);
    ArrayUtil.fillArray((Object[])this.accessorList, null);
  }
  
  public void remove(CachedObject paramCachedObject) {}
  
  public void release(long paramLong) {}
  
  public void commitPersistence(CachedObject paramCachedObject) {}
  
  public void commitRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2) {
    Object[] arrayOfObject = paramRow.getData();
    switch (paramInt1) {
      case 2:
        this.database.logger.writeDeleteStatement(paramSession, (Table)this.table, arrayOfObject);
        break;
      case 1:
        this.database.logger.writeInsertStatement(paramSession, paramRow, (Table)this.table);
        break;
      case 3:
        delete(paramSession, paramRow);
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
        delete(paramSession, paramRow);
        remove((CachedObject)paramRow);
        break;
      case 4:
        remove((CachedObject)paramRow);
        break;
    } 
  }
  
  public DataFileCache getCache() {
    return null;
  }
  
  public void setCache(DataFileCache paramDataFileCache) {}
  
  public void release() {
    destroy();
    setTimestamp(0L);
    ArrayUtil.fillArray((Object[])this.accessorList, null);
    this.elementCount.set(0L);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\RowStoreAVLMemory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */