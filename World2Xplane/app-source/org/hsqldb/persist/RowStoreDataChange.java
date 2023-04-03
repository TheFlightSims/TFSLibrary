package org.hsqldb.persist;

import java.io.IOException;
import org.hsqldb.HsqlException;
import org.hsqldb.RowDiskDataChange;
import org.hsqldb.Session;
import org.hsqldb.TableBase;
import org.hsqldb.rowio.RowInputInterface;

public class RowStoreDataChange extends RowStoreAVLHybrid {
  Session session;
  
  public RowStoreDataChange(Session paramSession, PersistentStoreCollection paramPersistentStoreCollection, TableBase paramTableBase) {
    super(paramSession, paramPersistentStoreCollection, paramTableBase, true);
    this.session = paramSession;
    changeToDiskTable(paramSession);
  }
  
  public CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean) {
    RowDiskDataChange rowDiskDataChange = new RowDiskDataChange(this.table, (Object[])paramObject, this, null);
    add(paramSession, (CachedObject)rowDiskDataChange, paramBoolean);
    return (CachedObject)rowDiskDataChange;
  }
  
  public CachedObject get(RowInputInterface paramRowInputInterface) {
    try {
      return (CachedObject)new RowDiskDataChange(this.session, this.table, paramRowInputInterface);
    } catch (HsqlException hsqlException) {
      return null;
    } catch (IOException iOException) {
      return null;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\RowStoreDataChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */