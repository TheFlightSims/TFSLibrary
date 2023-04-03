package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlDeque;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.persist.CachedObject;
import org.hsqldb.persist.PersistentStore;

public class TransactionManagerMV2PL extends TransactionManagerCommon implements TransactionManager {
  HsqlDeque committedTransactions = new HsqlDeque();
  
  LongDeque committedTransactionTimestamps = new LongDeque();
  
  public TransactionManagerMV2PL(Database paramDatabase) {
    this.database = paramDatabase;
    this.lobSession = this.database.sessionManager.getSysLobSession();
    this.rowActionMap = new LongKeyHashMap(10000);
    this.txModel = 1;
    this.catalogNameList = new HsqlNameManager.HsqlName[] { this.database.getCatalogName() };
  }
  
  public long getGlobalChangeTimestamp() {
    return this.globalChangeTimestamp.get();
  }
  
  public boolean isMVRows() {
    return true;
  }
  
  public boolean isMVCC() {
    return false;
  }
  
  public int getTransactionControl() {
    return 1;
  }
  
  public void setTransactionControl(Session paramSession, int paramInt) {
    super.setTransactionControl(paramSession, paramInt);
  }
  
  public void completeActions(Session paramSession) {
    endActionTPL(paramSession);
  }
  
  public boolean prepareCommitActions(Session paramSession) {
    this.writeLock.lock();
    try {
      int i = paramSession.rowActionList.size();
      paramSession.actionTimestamp = getNextGlobalChangeTimestamp();
      byte b;
      for (b = 0; b < i; b++) {
        RowAction rowAction = (RowAction)paramSession.rowActionList.get(b);
        rowAction.prepareCommit(paramSession);
      } 
      b = 1;
      return b;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public boolean commitTransaction(Session paramSession) {
    if (paramSession.abortTransaction)
      return false; 
    this.writeLock.lock();
    try {
      int i = paramSession.rowActionList.size();
      paramSession.actionTimestamp = getNextGlobalChangeTimestamp();
      paramSession.transactionEndTimestamp = paramSession.actionTimestamp;
      endTransaction(paramSession);
      int j;
      for (j = 0; j < i; j++) {
        RowAction rowAction = (RowAction)paramSession.rowActionList.get(j);
        rowAction.commit(paramSession);
      } 
      adjustLobUsage(paramSession);
      persistCommit(paramSession);
      j = paramSession.rowActionList.size();
      if (j > i) {
        Object[] arrayOfObject = paramSession.rowActionList.getArray();
        mergeTransaction(arrayOfObject, i, j, paramSession.actionTimestamp);
        finaliseRows(paramSession, arrayOfObject, i, j);
        paramSession.rowActionList.setSize(i);
      } 
      if (paramSession == this.lobSession || getFirstLiveTransactionTimestamp() > paramSession.actionTimestamp) {
        Object[] arrayOfObject = paramSession.rowActionList.getArray();
        mergeTransaction(arrayOfObject, 0, i, paramSession.actionTimestamp);
        finaliseRows(paramSession, arrayOfObject, 0, i);
      } else {
        Object[] arrayOfObject = paramSession.rowActionList.toArray();
        addToCommittedQueue(paramSession, arrayOfObject);
      } 
      endTransactionTPL(paramSession);
    } finally {
      this.writeLock.unlock();
    } 
    paramSession.tempSet.clear();
    return true;
  }
  
  public void rollback(Session paramSession) {
    this.writeLock.lock();
    try {
      paramSession.abortTransaction = false;
      paramSession.actionTimestamp = getNextGlobalChangeTimestamp();
      paramSession.transactionEndTimestamp = paramSession.actionTimestamp;
      rollbackPartial(paramSession, 0, paramSession.transactionTimestamp);
      endTransaction(paramSession);
      endTransactionTPL(paramSession);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void rollbackSavepoint(Session paramSession, int paramInt) {
    long l = paramSession.sessionContext.savepointTimestamps.get(paramInt);
    Integer integer = (Integer)paramSession.sessionContext.savepoints.get(paramInt);
    int i = integer.intValue();
    while (paramSession.sessionContext.savepoints.size() > paramInt + 1) {
      paramSession.sessionContext.savepoints.remove(paramSession.sessionContext.savepoints.size() - 1);
      paramSession.sessionContext.savepointTimestamps.removeLast();
    } 
    rollbackPartial(paramSession, i, l);
  }
  
  public void rollbackAction(Session paramSession) {
    rollbackPartial(paramSession, paramSession.actionIndex, paramSession.actionStartTimestamp);
    endActionTPL(paramSession);
  }
  
  public void rollbackPartial(Session paramSession, int paramInt, long paramLong) {
    int i = paramSession.rowActionList.size();
    if (paramInt == i)
      return; 
    for (int j = paramInt; j < i; j++) {
      RowAction rowAction = (RowAction)paramSession.rowActionList.get(j);
      if (rowAction == null)
        throw Error.runtimeError(458, "null rollback action "); 
      rowAction.rollback(paramSession, paramLong);
    } 
    this.writeLock.lock();
    try {
      Object[] arrayOfObject = paramSession.rowActionList.getArray();
      mergeRolledBackTransaction(paramSession, paramLong, arrayOfObject, paramInt, i);
    } finally {
      this.writeLock.unlock();
    } 
    paramSession.rowActionList.setSize(paramInt);
  }
  
  public RowAction addDeleteAction(Session paramSession, Table paramTable, PersistentStore paramPersistentStore, Row paramRow, int[] paramArrayOfint) {
    RowAction rowAction;
    synchronized (paramRow) {
      switch (paramTable.tableType) {
        case 5:
          rowAction = RowAction.addDeleteAction(paramSession, paramTable, paramRow, paramArrayOfint);
          addTransactionInfo(paramRow);
          break;
        case 3:
          rowAction = RowAction.addDeleteAction(paramSession, paramTable, paramRow, paramArrayOfint);
          paramPersistentStore.delete(paramSession, paramRow);
          paramRow.rowAction = null;
          break;
        default:
          rowAction = RowAction.addDeleteAction(paramSession, paramTable, paramRow, paramArrayOfint);
          break;
      } 
    } 
    paramSession.rowActionList.add(rowAction);
    return rowAction;
  }
  
  public void addInsertAction(Session paramSession, Table paramTable, PersistentStore paramPersistentStore, Row paramRow, int[] paramArrayOfint) {
    RowAction rowAction = paramRow.rowAction;
    if (rowAction == null)
      throw Error.runtimeError(458, "null insert action "); 
    paramPersistentStore.indexRow(paramSession, paramRow);
    paramSession.rowActionList.add(rowAction);
  }
  
  public boolean canRead(Session paramSession, PersistentStore paramPersistentStore, Row paramRow, int paramInt, int[] paramArrayOfint) {
    RowAction rowAction = paramRow.rowAction;
    return (rowAction == null) ? true : ((rowAction.table.tableType == 3) ? true : rowAction.canRead(paramSession, 0));
  }
  
  public boolean canRead(Session paramSession, PersistentStore paramPersistentStore, long paramLong, int paramInt) {
    if ((paramPersistentStore.getTable()).tableType == 3)
      return true; 
    RowAction rowAction = (RowAction)this.rowActionMap.get(paramLong);
    return (rowAction == null) ? true : rowAction.canRead(paramSession, paramInt);
  }
  
  public void addTransactionInfo(CachedObject paramCachedObject) {
    if (paramCachedObject.isMemory())
      return; 
    Row row = (Row)paramCachedObject;
    if ((row.getTable()).tableType == 5)
      this.rowActionMap.put(paramCachedObject.getPos(), row.rowAction); 
  }
  
  public void setTransactionInfo(PersistentStore paramPersistentStore, CachedObject paramCachedObject) {
    if (paramCachedObject.isMemory())
      return; 
    Row row = (Row)paramCachedObject;
    if ((row.getTable()).tableType == 5) {
      RowAction rowAction = (RowAction)this.rowActionMap.get(row.getPos());
      row.rowAction = rowAction;
    } 
  }
  
  public void removeTransactionInfo(CachedObject paramCachedObject) {
    if (paramCachedObject.isMemory())
      return; 
    this.rowActionMap.remove(paramCachedObject.getPos());
  }
  
  public void removeTransactionInfo(long paramLong) {
    this.rowActionMap.getWriteLock().lock();
    try {
      RowAction rowAction = (RowAction)this.rowActionMap.get(paramLong);
      synchronized (rowAction) {
        if (rowAction.type == 0)
          this.rowActionMap.remove(paramLong); 
      } 
    } finally {
      this.rowActionMap.getWriteLock().unlock();
    } 
  }
  
  void addToCommittedQueue(Session paramSession, Object[] paramArrayOfObject) {
    synchronized (this.committedTransactionTimestamps) {
      this.committedTransactions.addLast(paramArrayOfObject);
      this.committedTransactionTimestamps.addLast(paramSession.actionTimestamp);
    } 
  }
  
  void mergeExpiredTransactions(Session paramSession) {
    long l = getFirstLiveTransactionTimestamp();
    while (true) {
      long l1 = 0L;
      Object[] arrayOfObject = null;
      synchronized (this.committedTransactionTimestamps) {
        if (this.committedTransactionTimestamps.isEmpty())
          break; 
        l1 = this.committedTransactionTimestamps.getFirst();
        if (l1 < l) {
          this.committedTransactionTimestamps.removeFirst();
          arrayOfObject = (Object[])this.committedTransactions.removeFirst();
        } else {
          break;
        } 
      } 
      mergeTransaction(arrayOfObject, 0, arrayOfObject.length, l1);
      finaliseRows(paramSession, arrayOfObject, 0, arrayOfObject.length);
    } 
  }
  
  public void beginTransaction(Session paramSession) {
    this.writeLock.lock();
    try {
      if (!paramSession.isTransaction) {
        paramSession.actionTimestamp = getNextGlobalChangeTimestamp();
        paramSession.transactionTimestamp = paramSession.actionTimestamp;
        paramSession.isTransaction = true;
        this.transactionCount++;
        this.liveTransactionTimestamps.addLast(paramSession.transactionTimestamp);
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void beginAction(Session paramSession, Statement paramStatement) {
    if (paramSession.hasLocks(paramStatement))
      return; 
    this.writeLock.lock();
    try {
      if (paramStatement.getCompileTimestamp() < this.database.schemaManager.getSchemaChangeTimestamp()) {
        paramStatement = paramSession.statementManager.getStatement(paramSession, paramStatement);
        paramSession.sessionContext.currentStatement = paramStatement;
        if (paramStatement == null)
          return; 
      } 
      boolean bool = setWaitedSessionsTPL(paramSession, paramStatement);
      if (bool) {
        if (paramSession.tempSet.isEmpty()) {
          lockTablesTPL(paramSession, paramStatement);
        } else {
          setWaitingSessionTPL(paramSession);
        } 
      } else {
        paramSession.abortTransaction = true;
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void beginActionResume(Session paramSession) {
    this.writeLock.lock();
    try {
      paramSession.actionTimestamp = getNextGlobalChangeTimestamp();
      if (!paramSession.isTransaction) {
        paramSession.transactionTimestamp = paramSession.actionTimestamp;
        paramSession.isTransaction = true;
        this.liveTransactionTimestamps.addLast(paramSession.transactionTimestamp);
        this.transactionCount++;
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  void endTransaction(Session paramSession) {
    long l = paramSession.transactionTimestamp;
    paramSession.isTransaction = false;
    int i = this.liveTransactionTimestamps.indexOf(l);
    if (i >= 0) {
      this.transactionCount--;
      this.liveTransactionTimestamps.remove(i);
      mergeExpiredTransactions(paramSession);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\TransactionManagerMV2PL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */