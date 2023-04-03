package org.hsqldb.persist;

import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlDeque;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LongKeyHashMap;

public class PersistentStoreCollectionSession implements PersistentStoreCollection {
  private final Session session;
  
  private final LongKeyHashMap rowStoreMapSession = new LongKeyHashMap();
  
  private LongKeyHashMap rowStoreMapTransaction = new LongKeyHashMap();
  
  private LongKeyHashMap rowStoreMapStatement = new LongKeyHashMap();
  
  private LongKeyHashMap rowStoreMapRoutine = new LongKeyHashMap();
  
  private HsqlDeque rowStoreListStack;
  
  DataFileCacheSession resultCache;
  
  public PersistentStoreCollectionSession(Session paramSession) {
    this.session = paramSession;
  }
  
  public void setStore(Object paramObject, PersistentStore paramPersistentStore) {
    TableBase tableBase = (TableBase)paramObject;
    switch (tableBase.persistenceScope) {
      case 20:
        if (paramPersistentStore == null) {
          this.rowStoreMapRoutine.remove(tableBase.getPersistenceId());
        } else {
          this.rowStoreMapRoutine.put(tableBase.getPersistenceId(), paramPersistentStore);
        } 
        return;
      case 21:
        if (paramPersistentStore == null) {
          this.rowStoreMapStatement.remove(tableBase.getPersistenceId());
        } else {
          this.rowStoreMapStatement.put(tableBase.getPersistenceId(), paramPersistentStore);
        } 
        return;
      case 22:
      case 24:
        if (paramPersistentStore == null) {
          this.rowStoreMapTransaction.remove(tableBase.getPersistenceId());
        } else {
          this.rowStoreMapTransaction.put(tableBase.getPersistenceId(), paramPersistentStore);
        } 
        return;
      case 23:
        if (paramPersistentStore == null) {
          this.rowStoreMapSession.remove(tableBase.getPersistenceId());
        } else {
          this.rowStoreMapSession.put(tableBase.getPersistenceId(), paramPersistentStore);
        } 
        return;
    } 
    throw Error.runtimeError(201, "PersistentStoreCollectionSession");
  }
  
  public PersistentStore getViewStore(long paramLong) {
    return (PersistentStore)this.rowStoreMapStatement.get(paramLong);
  }
  
  public PersistentStore getStore(Object paramObject) {
    PersistentStore persistentStore;
    TableBase tableBase = (TableBase)paramObject;
    switch (tableBase.persistenceScope) {
      case 20:
        persistentStore = (PersistentStore)this.rowStoreMapRoutine.get(tableBase.getPersistenceId());
        if (persistentStore == null)
          persistentStore = this.session.database.logger.newStore(this.session, this, tableBase); 
        return persistentStore;
      case 21:
        persistentStore = (PersistentStore)this.rowStoreMapStatement.get(tableBase.getPersistenceId());
        if (persistentStore == null)
          persistentStore = this.session.database.logger.newStore(this.session, this, tableBase); 
        return persistentStore;
      case 22:
      case 24:
        persistentStore = (PersistentStore)this.rowStoreMapTransaction.get(tableBase.getPersistenceId());
        if (persistentStore == null)
          persistentStore = this.session.database.logger.newStore(this.session, this, tableBase); 
        if (tableBase.getTableType() == 1)
          this.session.database.dbInfo.setStore(this.session, (Table)tableBase, persistentStore); 
        return persistentStore;
      case 23:
        persistentStore = (PersistentStore)this.rowStoreMapSession.get(tableBase.getPersistenceId());
        if (persistentStore == null)
          persistentStore = this.session.database.logger.newStore(this.session, this, tableBase); 
        return persistentStore;
    } 
    throw Error.runtimeError(201, "PersistentStoreCollectionSession");
  }
  
  public void clearAllTables() {
    clearSessionTables();
    clearTransactionTables();
    clearStatementTables();
    clearRoutineTables();
    closeSessionDataCache();
  }
  
  public void clearResultTables(long paramLong) {
    if (this.rowStoreMapSession.isEmpty())
      return; 
    Iterator iterator = this.rowStoreMapSession.values().iterator();
    while (iterator.hasNext()) {
      PersistentStore persistentStore = (PersistentStore)iterator.next();
      if (persistentStore.getTimestamp() == paramLong) {
        persistentStore.release();
        iterator.remove();
      } 
    } 
  }
  
  public void clearSessionTables() {
    if (this.rowStoreMapSession.isEmpty())
      return; 
    Iterator iterator = this.rowStoreMapSession.values().iterator();
    while (iterator.hasNext()) {
      PersistentStore persistentStore = (PersistentStore)iterator.next();
      persistentStore.release();
    } 
    this.rowStoreMapSession.clear();
  }
  
  public void clearTransactionTables() {
    if (this.rowStoreMapTransaction.isEmpty())
      return; 
    Iterator iterator = this.rowStoreMapTransaction.values().iterator();
    while (iterator.hasNext()) {
      PersistentStore persistentStore = (PersistentStore)iterator.next();
      persistentStore.release();
    } 
    this.rowStoreMapTransaction.clear();
  }
  
  public void clearStatementTables() {
    if (this.rowStoreMapStatement.isEmpty())
      return; 
    Iterator iterator = this.rowStoreMapStatement.values().iterator();
    while (iterator.hasNext()) {
      PersistentStore persistentStore = (PersistentStore)iterator.next();
      persistentStore.release();
    } 
    this.rowStoreMapStatement.clear();
  }
  
  public void clearRoutineTables() {
    if (this.rowStoreMapRoutine.isEmpty())
      return; 
    Iterator iterator = this.rowStoreMapRoutine.values().iterator();
    while (iterator.hasNext()) {
      PersistentStore persistentStore = (PersistentStore)iterator.next();
      persistentStore.release();
    } 
    this.rowStoreMapRoutine.clear();
  }
  
  public void registerIndex(Session paramSession, Table paramTable) {
    PersistentStore persistentStore = findStore(paramTable);
    if (persistentStore == null)
      return; 
    persistentStore.resetAccessorKeys(paramSession, paramTable.getIndexList());
  }
  
  public PersistentStore findStore(Table paramTable) {
    PersistentStore persistentStore = null;
    switch (paramTable.persistenceScope) {
      case 20:
        persistentStore = (PersistentStore)this.rowStoreMapRoutine.get(paramTable.getPersistenceId());
        break;
      case 21:
        persistentStore = (PersistentStore)this.rowStoreMapStatement.get(paramTable.getPersistenceId());
        break;
      case 22:
      case 24:
        persistentStore = (PersistentStore)this.rowStoreMapTransaction.get(paramTable.getPersistenceId());
        break;
      case 23:
        persistentStore = (PersistentStore)this.rowStoreMapSession.get(paramTable.getPersistenceId());
        break;
    } 
    return persistentStore;
  }
  
  public void moveData(Table paramTable1, Table paramTable2, int paramInt1, int paramInt2) {
    PersistentStore persistentStore1 = findStore(paramTable1);
    if (persistentStore1 == null)
      return; 
    PersistentStore persistentStore2 = getStore(paramTable2);
    try {
      persistentStore2.moveData(this.session, persistentStore1, paramInt1, paramInt2);
    } catch (HsqlException hsqlException) {
      persistentStore2.release();
      setStore(paramTable2, null);
      throw hsqlException;
    } 
    setStore(paramTable1, null);
  }
  
  public void push(boolean paramBoolean) {
    if (this.rowStoreListStack == null)
      this.rowStoreListStack = new HsqlDeque(); 
    Object[] arrayOfObject = this.rowStoreMapStatement.toArray();
    this.rowStoreListStack.add(arrayOfObject);
    this.rowStoreMapStatement.clear();
    if (paramBoolean) {
      arrayOfObject = this.rowStoreMapRoutine.toArray();
      this.rowStoreListStack.add(arrayOfObject);
      this.rowStoreMapRoutine.clear();
    } 
  }
  
  public void pop(boolean paramBoolean) {
    if (paramBoolean) {
      Object[] arrayOfObject1 = (Object[])this.rowStoreListStack.removeLast();
      clearRoutineTables();
      for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
        PersistentStore persistentStore = (PersistentStore)arrayOfObject1[b1];
        this.rowStoreMapRoutine.put(persistentStore.getTable().getPersistenceId(), persistentStore);
      } 
    } 
    Object[] arrayOfObject = (Object[])this.rowStoreListStack.removeLast();
    clearStatementTables();
    for (byte b = 0; b < arrayOfObject.length; b++) {
      PersistentStore persistentStore = (PersistentStore)arrayOfObject[b];
      this.rowStoreMapStatement.put(persistentStore.getTable().getPersistenceId(), persistentStore);
    } 
  }
  
  public DataFileCacheSession getSessionDataCache() {
    if (this.resultCache == null) {
      String str = this.session.database.logger.getTempDirectoryPath();
      if (str == null)
        return null; 
      try {
        this.resultCache = new DataFileCacheSession(this.session.database, str + "/session_" + Long.toString(this.session.getId()));
        this.resultCache.open(false);
      } catch (Throwable throwable) {
        return null;
      } 
    } 
    return this.resultCache;
  }
  
  private void closeSessionDataCache() {
    if (this.resultCache != null) {
      try {
        this.resultCache.release();
        this.resultCache.deleteFile();
      } catch (HsqlException hsqlException) {}
      this.resultCache = null;
    } 
  }
  
  public void release() {
    clearAllTables();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\PersistentStoreCollectionSession.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */