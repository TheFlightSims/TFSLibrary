package org.hsqldb;

import org.hsqldb.lib.LongLookup;
import org.hsqldb.persist.CachedObject;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class Row implements CachedObject {
  long position;
  
  Object[] rowData;
  
  public volatile RowAction rowAction;
  
  protected TableBase table;
  
  public RowAction getAction() {
    return this.rowAction;
  }
  
  public Row(TableBase paramTableBase, Object[] paramArrayOfObject) {
    this.table = paramTableBase;
    this.rowData = paramArrayOfObject;
  }
  
  public Object[] getData() {
    return this.rowData;
  }
  
  boolean isDeleted(Session paramSession, PersistentStore paramPersistentStore) {
    Row row = (Row)paramPersistentStore.get(this, false);
    if (row == null)
      return true; 
    RowAction rowAction = row.rowAction;
    return (rowAction == null) ? false : (!rowAction.canRead(paramSession, 0));
  }
  
  public void setChanged(boolean paramBoolean) {}
  
  public void setStorageSize(int paramInt) {}
  
  public int getStorageSize() {
    return 0;
  }
  
  public final boolean isBlock() {
    return false;
  }
  
  public boolean isMemory() {
    return true;
  }
  
  public void updateAccessCount(int paramInt) {}
  
  public int getAccessCount() {
    return 0;
  }
  
  public long getPos() {
    return this.position;
  }
  
  public long getId() {
    return (this.table.getId() << 40L) + this.position;
  }
  
  public void setPos(long paramLong) {
    this.position = paramLong;
  }
  
  public boolean isNew() {
    return false;
  }
  
  public boolean hasChanged() {
    return false;
  }
  
  public boolean isKeepInMemory() {
    return true;
  }
  
  public boolean keepInMemory(boolean paramBoolean) {
    return true;
  }
  
  public boolean isInMemory() {
    return true;
  }
  
  public void setInMemory(boolean paramBoolean) {}
  
  public void delete(PersistentStore paramPersistentStore) {}
  
  public void restore() {}
  
  public void destroy() {}
  
  public int getRealSize(RowOutputInterface paramRowOutputInterface) {
    return 0;
  }
  
  public TableBase getTable() {
    return this.table;
  }
  
  public int getDefaultCapacity() {
    return 0;
  }
  
  public void read(RowInputInterface paramRowInputInterface) {}
  
  public void write(RowOutputInterface paramRowOutputInterface) {}
  
  public void write(RowOutputInterface paramRowOutputInterface, LongLookup paramLongLookup) {}
  
  public boolean equals(Object paramObject) {
    return (paramObject == this) ? true : ((paramObject instanceof Row) ? ((((Row)paramObject).table == this.table && ((Row)paramObject).position == this.position)) : false);
  }
  
  public int hashCode() {
    return (int)this.position;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\Row.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */