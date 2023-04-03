package org.hsqldb.persist;

import java.io.IOException;
import org.hsqldb.HsqlException;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.RowAVLDisk;
import org.hsqldb.Session;
import org.hsqldb.TableBase;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.index.IndexAVL;
import org.hsqldb.index.NodeAVL;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.rowio.RowInputInterface;

public class RowStoreAVLHybrid extends RowStoreAVL implements PersistentStore {
  DataFileCache cache;
  
  private int maxMemoryRowCount;
  
  private boolean useDisk;
  
  boolean isCached;
  
  int rowIdSequence = 0;
  
  public RowStoreAVLHybrid(Session paramSession, PersistentStoreCollection paramPersistentStoreCollection, TableBase paramTableBase, boolean paramBoolean) {
    this.manager = paramPersistentStoreCollection;
    this.table = paramTableBase;
    this.maxMemoryRowCount = paramSession.getResultMemoryRowCount();
    this.useDisk = paramBoolean;
    if (this.maxMemoryRowCount == 0)
      this.useDisk = false; 
    if (paramTableBase.getTableType() == 9)
      setTimestamp(paramSession.getActionTimestamp()); 
    resetAccessorKeys(paramSession, paramTableBase.getIndexList());
    paramPersistentStoreCollection.setStore(paramTableBase, this);
    this.nullsList = new boolean[paramTableBase.getColumnCount()];
  }
  
  public boolean isMemory() {
    return !this.isCached;
  }
  
  public void setMemory(boolean paramBoolean) {
    this.useDisk = !paramBoolean;
  }
  
  public synchronized int getAccessCount() {
    return this.isCached ? this.cache.getAccessCount() : 0;
  }
  
  public void set(CachedObject paramCachedObject) {}
  
  public CachedObject get(long paramLong) {
    try {
      if (this.isCached)
        return this.cache.get(paramLong, this, false); 
      throw Error.runtimeError(201, "RowStoreAVLHybrid");
    } catch (HsqlException hsqlException) {
      return null;
    } 
  }
  
  public CachedObject get(long paramLong, boolean paramBoolean) {
    try {
      if (this.isCached)
        return this.cache.get(paramLong, this, paramBoolean); 
      throw Error.runtimeError(201, "RowStoreAVLHybrid");
    } catch (HsqlException hsqlException) {
      return null;
    } 
  }
  
  public CachedObject get(CachedObject paramCachedObject, boolean paramBoolean) {
    try {
      return this.isCached ? this.cache.get(paramCachedObject, this, paramBoolean) : paramCachedObject;
    } catch (HsqlException hsqlException) {
      return null;
    } 
  }
  
  public void add(Session paramSession, CachedObject paramCachedObject, boolean paramBoolean) {
    if (this.isCached) {
      int i = paramCachedObject.getRealSize(this.cache.rowOut);
      i += this.indexList.length * 16;
      i = this.cache.rowOut.getStorageSize(i);
      paramCachedObject.setStorageSize(i);
      long l = this.tableSpace.getFilePosition(i, false);
      paramCachedObject.setPos(l);
      this.cache.add(paramCachedObject);
    } 
    Object[] arrayOfObject = ((Row)paramCachedObject).getData();
    for (byte b = 0; b < this.nullsList.length; b++) {
      if (arrayOfObject[b] == null)
        this.nullsList[b] = true; 
    } 
  }
  
  public CachedObject get(RowInputInterface paramRowInputInterface) {
    try {
      if (this.isCached)
        return (CachedObject)new RowAVLDisk(this.table, paramRowInputInterface); 
    } catch (HsqlException hsqlException) {
      return null;
    } catch (IOException iOException) {
      return null;
    } 
    return null;
  }
  
  public CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean) {
    RowAVL rowAVL;
    if (!this.isCached && this.useDisk && this.elementCount.get() >= this.maxMemoryRowCount)
      changeToDiskTable(paramSession); 
    if (this.isCached) {
      RowAVLDisk rowAVLDisk = new RowAVLDisk(this.table, (Object[])paramObject, this);
    } else {
      int i = this.rowIdSequence++;
      rowAVL = new RowAVL(this.table, (Object[])paramObject, i, this);
    } 
    add(paramSession, (CachedObject)rowAVL, paramBoolean);
    return (CachedObject)rowAVL;
  }
  
  public void indexRow(Session paramSession, Row paramRow) {
    try {
      paramRow = (Row)get((CachedObject)paramRow, true);
      super.indexRow(paramSession, paramRow);
      paramRow.keepInMemory(false);
    } catch (HsqlException hsqlException) {
      throw hsqlException;
    } 
  }
  
  public void removeAll() {
    if (!this.isCached)
      destroy(); 
    this.elementCount.set(0L);
    ArrayUtil.fillArray((Object[])this.accessorList, null);
    for (byte b = 0; b < this.nullsList.length; b++)
      this.nullsList[b] = false; 
  }
  
  public void remove(CachedObject paramCachedObject) {
    if (this.isCached)
      this.cache.remove(paramCachedObject); 
  }
  
  public void commitPersistence(CachedObject paramCachedObject) {}
  
  public void commitRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2) {
    switch (paramInt1) {
      case 2:
        remove((CachedObject)paramRow);
        break;
      case 4:
        remove((CachedObject)paramRow);
        break;
      case 3:
        delete(paramSession, paramRow);
        remove((CachedObject)paramRow);
        break;
    } 
  }
  
  public void rollbackRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2) {
    switch (paramInt1) {
      case 2:
        paramRow = (Row)get((CachedObject)paramRow, true);
        ((RowAVL)paramRow).setNewNodes(this);
        paramRow.keepInMemory(false);
        indexRow(paramSession, paramRow);
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
    return this.cache;
  }
  
  public void setCache(DataFileCache paramDataFileCache) {
    throw Error.runtimeError(201, "RowStoreAVLHybrid");
  }
  
  public void release() {
    if (!this.isCached)
      destroy(); 
    ArrayUtil.fillArray((Object[])this.accessorList, null);
    if (this.isCached) {
      this.cache.adjustStoreCount(-1);
      this.cache = null;
      this.isCached = false;
    } 
    this.manager.setStore(this.table, null);
    this.elementCount.set(0L);
  }
  
  public void delete(Session paramSession, Row paramRow) {
    super.delete(paramSession, paramRow);
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
  
  public synchronized void resetAccessorKeys(Session paramSession, Index[] paramArrayOfIndex) {
    if (this.indexList.length == 0 || this.accessorList[0] == null) {
      this.indexList = paramArrayOfIndex;
      this.accessorList = new CachedObject[this.indexList.length];
      return;
    } 
    if (this.isCached)
      throw Error.runtimeError(201, "RowStoreAVLHybrid"); 
    super.resetAccessorKeys(paramSession, paramArrayOfIndex);
  }
  
  public boolean hasNull(int paramInt) {
    return this.nullsList[paramInt];
  }
  
  public final void changeToDiskTable(Session paramSession) {
    this.cache = ((PersistentStoreCollectionSession)this.manager).getSessionDataCache();
    if (this.cache != null) {
      this.tableSpace = this.cache.spaceManager.getTableSpace(7);
      IndexAVL indexAVL = (IndexAVL)this.indexList[0];
      NodeAVL nodeAVL = (NodeAVL)this.accessorList[0];
      RowIterator rowIterator = this.table.rowIterator(this);
      ArrayUtil.fillArray((Object[])this.accessorList, null);
      this.elementCount.set(0L);
      this.isCached = true;
      this.cache.adjustStoreCount(1);
      while (rowIterator.hasNext()) {
        Row row1 = rowIterator.getNextRow();
        Row row2 = (Row)getNewCachedObject(paramSession, row1.getData(), false);
        indexRow(paramSession, row2);
      } 
      indexAVL.unlinkNodes(nodeAVL);
    } 
    this.maxMemoryRowCount = Integer.MAX_VALUE;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\RowStoreAVLHybrid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */