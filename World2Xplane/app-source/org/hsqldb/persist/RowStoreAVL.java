package org.hsqldb.persist;

import java.util.concurrent.atomic.AtomicLong;
import org.hsqldb.ColumnSchema;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.RowAction;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.index.IndexAVL;
import org.hsqldb.index.NodeAVL;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.types.Type;

public abstract class RowStoreAVL implements PersistentStore {
  Database database;
  
  PersistentStoreCollection manager;
  
  TableSpaceManager tableSpace;
  
  Index[] indexList = Index.emptyArray;
  
  CachedObject[] accessorList = CachedObject.emptyArray;
  
  TableBase table;
  
  long baseElementCount;
  
  AtomicLong elementCount = new AtomicLong();
  
  long storageSize;
  
  boolean[] nullsList;
  
  double[][] searchCost;
  
  boolean isSchemaStore;
  
  private long timestamp;
  
  PersistentStore[] subStores = PersistentStore.emptyArray;
  
  public TableBase getTable() {
    return this.table;
  }
  
  public long getTimestamp() {
    return this.timestamp;
  }
  
  public void setTimestamp(long paramLong) {
    this.timestamp = paramLong;
  }
  
  public abstract boolean isMemory();
  
  public void setMemory(boolean paramBoolean) {}
  
  public abstract int getAccessCount();
  
  public abstract void set(CachedObject paramCachedObject);
  
  public abstract CachedObject get(long paramLong, boolean paramBoolean);
  
  public abstract CachedObject get(CachedObject paramCachedObject, boolean paramBoolean);
  
  public abstract void add(Session paramSession, CachedObject paramCachedObject, boolean paramBoolean);
  
  public boolean canRead(Session paramSession, long paramLong, int paramInt, int[] paramArrayOfint) {
    return true;
  }
  
  public boolean canRead(Session paramSession, CachedObject paramCachedObject, int paramInt, int[] paramArrayOfint) {
    RowAction rowAction = ((Row)paramCachedObject).rowAction;
    return (rowAction == null) ? true : rowAction.canRead(paramSession, paramInt);
  }
  
  public abstract CachedObject get(RowInputInterface paramRowInputInterface);
  
  public CachedObject get(CachedObject paramCachedObject, RowInputInterface paramRowInputInterface) {
    return paramCachedObject;
  }
  
  public CachedObject getNewInstance(int paramInt) {
    throw Error.runtimeError(201, "RowStoreAVL");
  }
  
  public int getDefaultObjectSize() {
    throw Error.runtimeError(201, "RowStoreAVL");
  }
  
  public abstract CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean);
  
  public abstract void removeAll();
  
  public abstract void remove(CachedObject paramCachedObject);
  
  public abstract void commitPersistence(CachedObject paramCachedObject);
  
  public void postCommitAction(Session paramSession, RowAction paramRowAction) {}
  
  public abstract DataFileCache getCache();
  
  public TableSpaceManager getSpaceManager() {
    return this.tableSpace;
  }
  
  public void setSpaceManager(TableSpaceManager paramTableSpaceManager) {
    this.tableSpace = paramTableSpaceManager;
  }
  
  public abstract void setCache(DataFileCache paramDataFileCache);
  
  public abstract void release();
  
  public PersistentStore getAccessorStore(Index paramIndex) {
    return null;
  }
  
  public CachedObject getAccessor(Index paramIndex) {
    int i = paramIndex.getPosition();
    if (i >= this.accessorList.length)
      throw Error.runtimeError(201, "RowStoreAVL"); 
    return this.accessorList[i];
  }
  
  public void delete(Session paramSession, Row paramRow) {
    paramRow = (Row)get((CachedObject)paramRow, false);
    byte b;
    for (b = 0; b < this.indexList.length; b++)
      this.indexList[b].delete(paramSession, this, paramRow); 
    for (b = 0; b < this.subStores.length; b++)
      this.subStores[b].delete(paramSession, paramRow); 
    paramRow.delete(this);
    long l = this.elementCount.decrementAndGet();
    if (l > 16384L && l < this.baseElementCount / 2L)
      synchronized (this) {
        this.baseElementCount = l;
        this.searchCost = (double[][])null;
      }  
  }
  
  public void indexRow(Session paramSession, Row paramRow) {
    byte b = 0;
    try {
      while (b < this.indexList.length) {
        this.indexList[b].insert(paramSession, this, paramRow);
        b++;
      } 
      byte b1 = 0;
      try {
        for (b1 = 0; b1 < this.subStores.length; b1++)
          this.subStores[b1].indexRow(paramSession, paramRow); 
      } catch (HsqlException hsqlException) {
        byte b2 = b1;
        for (b1 = 0; b1 < b2; b1++)
          this.subStores[b1].delete(paramSession, paramRow); 
        throw hsqlException;
      } 
      long l = this.elementCount.incrementAndGet();
      if (l > 16384L && l > this.baseElementCount * 2L)
        synchronized (this) {
          this.baseElementCount = l;
          this.searchCost = (double[][])null;
        }  
    } catch (HsqlException hsqlException) {
      byte b1 = b;
      for (b = 0; b < b1; b++)
        this.indexList[b].delete(paramSession, this, paramRow); 
      remove((CachedObject)paramRow);
      throw hsqlException;
    } 
  }
  
  public final void indexRows(Session paramSession) {
    for (byte b = 1; b < this.indexList.length; b++)
      setAccessor(this.indexList[b], (CachedObject)null); 
    RowIterator rowIterator = rowIterator();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.getNextRow();
      ((RowAVL)row).clearNonPrimaryNodes();
      for (byte b1 = 1; b1 < this.indexList.length; b1++)
        this.indexList[b1].insert(paramSession, this, row); 
    } 
  }
  
  public final RowIterator rowIterator() {
    Index index = this.indexList[0];
    for (byte b = 0; b < this.indexList.length; b++) {
      if (this.indexList[b].isClustered()) {
        index = this.indexList[b];
        break;
      } 
    } 
    return index.firstRow(this);
  }
  
  public void setAccessor(Index paramIndex, CachedObject paramCachedObject) {
    Index index = paramIndex;
    this.accessorList[index.getPosition()] = paramCachedObject;
  }
  
  public void setAccessor(Index paramIndex, long paramLong) {}
  
  public void resetAccessorKeys(Session paramSession, Index[] paramArrayOfIndex) {
    Index[] arrayOfIndex = this.indexList;
    this.searchCost = (double[][])null;
    if (this.indexList.length == 0 || this.accessorList[0] == null) {
      this.indexList = paramArrayOfIndex;
      this.accessorList = new CachedObject[this.indexList.length];
      return;
    } 
    if (this.indexList == paramArrayOfIndex)
      return; 
    CachedObject[] arrayOfCachedObject = this.accessorList;
    int i = this.indexList.length;
    int j = paramArrayOfIndex.length - this.indexList.length;
    byte b = 0;
    if (j < -1)
      throw Error.runtimeError(201, "RowStoreAVL"); 
    if (j == -1) {
      i = paramArrayOfIndex.length;
    } else {
      if (j == 0)
        throw Error.runtimeError(201, "RowStoreAVL"); 
      if (j != 1) {
        while (b < i && this.indexList[b] == paramArrayOfIndex[b])
          b++; 
        Index[] arrayOfIndex1 = (Index[])ArrayUtil.toAdjustedArray(this.indexList, null, b, 1);
        arrayOfIndex1[b] = paramArrayOfIndex[b];
        resetAccessorKeys(paramSession, arrayOfIndex1);
        resetAccessorKeys(paramSession, paramArrayOfIndex);
        return;
      } 
    } 
    while (b < i && this.indexList[b] == paramArrayOfIndex[b])
      b++; 
    this.accessorList = (CachedObject[])ArrayUtil.toAdjustedArray(this.accessorList, null, b, j);
    this.indexList = paramArrayOfIndex;
    try {
      if (j > 0) {
        insertIndexNodes(paramSession, this.indexList[0], this.indexList[b]);
      } else {
        dropIndexFromRows(this.indexList[0], arrayOfIndex[b]);
      } 
    } catch (HsqlException hsqlException) {
      this.accessorList = arrayOfCachedObject;
      this.indexList = arrayOfIndex;
      throw hsqlException;
    } 
  }
  
  public Index[] getAccessorKeys() {
    return this.indexList;
  }
  
  public synchronized double searchCost(Session paramSession, Index paramIndex, int paramInt1, int paramInt2) {
    if (paramInt2 != 40)
      return (this.elementCount.get() / 2L); 
    if (paramIndex.isUnique() && paramInt1 == paramIndex.getColumnCount())
      return 1.0D; 
    int i = paramIndex.getPosition();
    if (this.searchCost == null || this.searchCost.length <= i)
      this.searchCost = new double[this.indexList.length][]; 
    if (this.searchCost[i] == null)
      this.searchCost[paramIndex.getPosition()] = this.indexList[paramIndex.getPosition()].searchCost(paramSession, this); 
    return this.searchCost[paramIndex.getPosition()][paramInt1 - 1];
  }
  
  public long elementCount() {
    Index index = this.indexList[0];
    if (this.elementCount.get() < 0L)
      this.elementCount.set(((IndexAVL)index).getNodeCount(null, this)); 
    return this.elementCount.get();
  }
  
  public long elementCount(Session paramSession) {
    Index index = this.indexList[0];
    if (this.elementCount.get() < 0L)
      this.elementCount.set(((IndexAVL)index).getNodeCount(paramSession, this)); 
    if (paramSession != null) {
      int i = paramSession.database.txManager.getTransactionControl();
      if (i != 0)
        switch (this.table.getTableType()) {
          case 4:
          case 5:
          case 7:
            return ((IndexAVL)index).getNodeCount(paramSession, this);
        }  
    } 
    return this.elementCount.get();
  }
  
  public long elementCountUnique(Index paramIndex) {
    return 0L;
  }
  
  public void setElementCount(Index paramIndex, long paramLong1, long paramLong2) {
    this.elementCount.set(paramLong1);
  }
  
  public boolean hasNull(int paramInt) {
    return false;
  }
  
  public void moveDataToSpace() {}
  
  public final void moveData(Session paramSession, PersistentStore paramPersistentStore, int paramInt1, int paramInt2) {
    Type type1 = null;
    Type type2 = null;
    Object object = null;
    if (paramInt2 >= 0 && paramInt1 != -1) {
      ColumnSchema columnSchema = ((Table)this.table).getColumn(paramInt1);
      object = columnSchema.getDefaultValue(paramSession);
      type2 = ((Table)this.table).getColumnTypes()[paramInt1];
    } 
    if (paramInt2 <= 0 && paramInt1 != -1)
      type1 = ((Table)paramPersistentStore.getTable()).getColumnTypes()[paramInt1]; 
    try {
      Table table = (Table)this.table;
      RowIterator rowIterator = paramPersistentStore.rowIterator();
      while (rowIterator.hasNext()) {
        Row row1 = rowIterator.getNextRow();
        Object[] arrayOfObject1 = row1.getData();
        Object[] arrayOfObject2 = table.getEmptyRowData();
        Object object1 = null;
        if (paramInt2 == 0 && paramInt1 != -1) {
          object1 = arrayOfObject1[paramInt1];
          object = type2.convertToType((SessionInterface)paramSession, object1, type1);
        } 
        ArrayUtil.copyAdjustArray(arrayOfObject1, arrayOfObject2, object, paramInt1, paramInt2);
        table.systemSetIdentityColumn(paramSession, arrayOfObject2);
        if (table.hasGeneratedColumn())
          table.setGeneratedColumns(paramSession, arrayOfObject2); 
        table.enforceTypeLimits(paramSession, arrayOfObject2);
        table.enforceRowConstraints(paramSession, arrayOfObject2);
        Row row2 = (Row)getNewCachedObject(paramSession, arrayOfObject2, false);
        indexRow(paramSession, row2);
      } 
      if (table.isTemp())
        return; 
      if (type1 != null && type1.isLobType()) {
        rowIterator = paramPersistentStore.rowIterator();
        while (rowIterator.hasNext()) {
          Row row = rowIterator.getNextRow();
          Object[] arrayOfObject = row.getData();
          Object object1 = arrayOfObject[paramInt1];
          if (object1 != null)
            paramSession.sessionData.adjustLobUsageCount(object1, -1); 
        } 
      } 
      if (type2 != null && type2.isLobType()) {
        rowIterator = rowIterator();
        while (rowIterator.hasNext()) {
          Row row = rowIterator.getNextRow();
          Object[] arrayOfObject = row.getData();
          Object object1 = arrayOfObject[paramInt1];
          if (object1 != null)
            paramSession.sessionData.adjustLobUsageCount(object1, 1); 
        } 
      } 
    } catch (OutOfMemoryError outOfMemoryError) {
      throw Error.error(460);
    } 
  }
  
  public void reindex(Session paramSession, Index paramIndex) {
    setAccessor(paramIndex, (CachedObject)null);
    RowIterator rowIterator = this.table.rowIterator(this);
    while (rowIterator.hasNext()) {
      RowAVL rowAVL = (RowAVL)rowIterator.getNextRow();
      rowAVL.getNode(paramIndex.getPosition()).delete();
      paramIndex.insert(paramSession, this, (Row)rowAVL);
    } 
  }
  
  public void setReadOnly(boolean paramBoolean) {}
  
  public void writeLock() {}
  
  public void writeUnlock() {}
  
  void dropIndexFromRows(Index paramIndex1, Index paramIndex2) {
    RowIterator rowIterator = paramIndex1.firstRow(this);
    int i = paramIndex2.getPosition() - 1;
    while (rowIterator.hasNext()) {
      Row row = rowIterator.getNextRow();
      int j = i - 1;
      NodeAVL nodeAVL;
      for (nodeAVL = ((RowAVL)row).getNode(0); j-- > 0; nodeAVL = nodeAVL.nNext);
      nodeAVL.nNext = nodeAVL.nNext.nNext;
    } 
    rowIterator.release();
  }
  
  boolean insertIndexNodes(Session paramSession, Index paramIndex1, Index paramIndex2) {
    int i = paramIndex2.getPosition();
    RowIterator rowIterator = paramIndex1.firstRow(this);
    byte b1 = 0;
    HsqlException hsqlException = null;
    try {
      while (rowIterator.hasNext()) {
        Row row = rowIterator.getNextRow();
        ((RowAVL)row).insertNode(i);
        b1++;
        paramIndex2.insert(paramSession, this, row);
      } 
      rowIterator.release();
      return true;
    } catch (OutOfMemoryError outOfMemoryError) {
      hsqlException = Error.error(460);
    } catch (HsqlException hsqlException1) {
      hsqlException = hsqlException1;
    } 
    rowIterator = paramIndex1.firstRow(this);
    for (byte b2 = 0; b2 < b1; b2++) {
      Row row = rowIterator.getNextRow();
      NodeAVL nodeAVL = ((RowAVL)row).getNode(0);
      int j = i;
      while (--j > 0)
        nodeAVL = nodeAVL.nNext; 
      nodeAVL.nNext = nodeAVL.nNext.nNext;
    } 
    rowIterator.release();
    throw hsqlException;
  }
  
  void destroy() {
    if (this.indexList.length == 0)
      return; 
    IndexAVL indexAVL = (IndexAVL)this.indexList[0];
    NodeAVL nodeAVL = (NodeAVL)this.accessorList[0];
    indexAVL.unlinkNodes(nodeAVL);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\RowStoreAVL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */