package org.hsqldb.persist;

import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.RowAction;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.index.Index;
import org.hsqldb.index.NodeAVL;
import org.hsqldb.navigator.RowIterator;

public class RowStoreAVLHybridExtended extends RowStoreAVLHybrid {
  Session session;
  
  public RowStoreAVLHybridExtended(Session paramSession, PersistentStoreCollection paramPersistentStoreCollection, TableBase paramTableBase, boolean paramBoolean) {
    super(paramSession, paramPersistentStoreCollection, paramTableBase, paramBoolean);
    this.session = paramSession;
  }
  
  public void set(CachedObject paramCachedObject) {}
  
  public CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean) {
    if (this.indexList != this.table.getIndexList())
      resetAccessorKeys(paramSession, this.table.getIndexList()); 
    return super.getNewCachedObject(paramSession, paramObject, paramBoolean);
  }
  
  public void add(Session paramSession, CachedObject paramCachedObject, boolean paramBoolean) {
    if (this.isCached) {
      int i = paramCachedObject.getRealSize(this.cache.rowOut);
      i += this.indexList.length * 16;
      i = this.cache.rowOut.getStorageSize(i);
      paramCachedObject.setStorageSize(i);
      long l = this.tableSpace.getFilePosition(i, false);
      paramCachedObject.setPos(l);
      if (paramBoolean)
        RowAction.addInsertAction(paramSession, this.table, (Row)paramCachedObject); 
      this.cache.add(paramCachedObject);
    } else if (paramBoolean) {
      RowAction.addInsertAction(paramSession, this.table, (Row)paramCachedObject);
    } 
    Object[] arrayOfObject = ((Row)paramCachedObject).getData();
    for (byte b = 0; b < this.nullsList.length; b++) {
      if (arrayOfObject[b] == null)
        this.nullsList[b] = true; 
    } 
  }
  
  public void indexRow(Session paramSession, Row paramRow) {
    NodeAVL nodeAVL = ((RowAVL)paramRow).getNode(0);
    byte b = 0;
    while (nodeAVL != null) {
      b++;
      nodeAVL = nodeAVL.nNext;
    } 
    if (b != this.indexList.length) {
      resetAccessorKeys(paramSession, this.table.getIndexList());
      ((RowAVL)paramRow).setNewNodes(this);
    } 
    super.indexRow(paramSession, paramRow);
  }
  
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
  
  public void delete(Session paramSession, Row paramRow) {
    NodeAVL nodeAVL = ((RowAVL)paramRow).getNode(0);
    byte b = 0;
    while (nodeAVL != null) {
      b++;
      nodeAVL = nodeAVL.nNext;
    } 
    if ((this.isCached && paramRow.isMemory()) || b != this.indexList.length)
      paramRow = ((Table)this.table).getDeleteRowFromLog(paramSession, paramRow.getData()); 
    if (paramRow != null)
      super.delete(paramSession, paramRow); 
  }
  
  public CachedObject getAccessor(Index paramIndex) {
    int i = paramIndex.getPosition();
    if (i >= this.accessorList.length || this.indexList[i] != paramIndex) {
      resetAccessorKeys(this.session, this.table.getIndexList());
      return getAccessor(paramIndex);
    } 
    return this.accessorList[i];
  }
  
  public synchronized void resetAccessorKeys(Session paramSession, Index[] paramArrayOfIndex) {
    if (this.indexList.length == 0 || this.accessorList[0] == null) {
      this.indexList = paramArrayOfIndex;
      this.accessorList = new CachedObject[this.indexList.length];
      return;
    } 
    if (this.isCached) {
      resetAccessorKeysForCached();
      return;
    } 
    super.resetAccessorKeys(paramSession, paramArrayOfIndex);
  }
  
  private void resetAccessorKeysForCached() {
    RowStoreAVLHybridExtended rowStoreAVLHybridExtended = new RowStoreAVLHybridExtended(this.session, this.manager, this.table, true);
    rowStoreAVLHybridExtended.changeToDiskTable(this.session);
    RowIterator rowIterator = this.table.rowIterator(this);
    while (rowIterator.hasNext()) {
      Row row1 = rowIterator.getNextRow();
      Row row2 = (Row)rowStoreAVLHybridExtended.getNewCachedObject(this.session, row1.getData(), false);
      rowStoreAVLHybridExtended.indexRow(this.session, row2);
    } 
    this.indexList = rowStoreAVLHybridExtended.indexList;
    this.accessorList = rowStoreAVLHybridExtended.accessorList;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\RowStoreAVLHybridExtended.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */