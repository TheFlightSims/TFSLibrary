package org.hsqldb.persist;

import org.hsqldb.Database;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LongKeyHashMap;

public class PersistentStoreCollectionDatabase implements PersistentStoreCollection {
  private Database database;
  
  private long persistentStoreIdSequence;
  
  private final LongKeyHashMap rowStoreMap = new LongKeyHashMap();
  
  public PersistentStoreCollectionDatabase(Database paramDatabase) {
    this.database = paramDatabase;
  }
  
  public void setStore(Object paramObject, PersistentStore paramPersistentStore) {
    long l = ((TableBase)paramObject).getPersistenceId();
    if (paramPersistentStore == null) {
      this.rowStoreMap.remove(l);
    } else {
      this.rowStoreMap.put(l, paramPersistentStore);
    } 
  }
  
  public synchronized PersistentStore getStore(Object paramObject) {
    long l = ((TableBase)paramObject).getPersistenceId();
    PersistentStore persistentStore = (PersistentStore)this.rowStoreMap.get(l);
    if (persistentStore == null) {
      persistentStore = this.database.logger.newStore(null, this, (TableBase)paramObject);
      ((TableBase)paramObject).store = persistentStore;
    } 
    return persistentStore;
  }
  
  public void release() {
    if (this.rowStoreMap.isEmpty())
      return; 
    Iterator iterator = this.rowStoreMap.values().iterator();
    while (iterator.hasNext()) {
      PersistentStore persistentStore = (PersistentStore)iterator.next();
      persistentStore.release();
    } 
    this.rowStoreMap.clear();
  }
  
  public void releaseStore(Table paramTable) {
    PersistentStore persistentStore = (PersistentStore)this.rowStoreMap.get(paramTable.getPersistenceId());
    if (persistentStore != null) {
      persistentStore.removeAll();
      persistentStore.release();
      this.rowStoreMap.remove(paramTable.getPersistenceId());
    } 
  }
  
  public long getNextId() {
    return this.persistentStoreIdSequence++;
  }
  
  public void setNewTableSpaces() {
    DataFileCache dataFileCache = this.database.logger.getCache();
    if (dataFileCache == null)
      return; 
    Iterator iterator = this.rowStoreMap.values().iterator();
    while (iterator.hasNext()) {
      PersistentStore persistentStore = (PersistentStore)iterator.next();
      if (persistentStore == null)
        continue; 
      TableBase tableBase = persistentStore.getTable();
      if (tableBase.getTableType() == 5) {
        TableSpaceManager tableSpaceManager = dataFileCache.spaceManager.getTableSpace(tableBase.getSpaceID());
        persistentStore.setSpaceManager(tableSpaceManager);
      } 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\PersistentStoreCollectionDatabase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */