package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.persist.CachedObject;
import org.hsqldb.persist.PersistentStore;

public class TransactionManager2PL extends TransactionManagerCommon implements TransactionManager {
  public TransactionManager2PL(Database paramDatabase) {
    this.database = paramDatabase;
    this.lobSession = this.database.sessionManager.getSysLobSession();
    this.txModel = 0;
  }
  
  public long getGlobalChangeTimestamp() {
    return this.globalChangeTimestamp.get();
  }
  
  public boolean isMVRows() {
    return false;
  }
  
  public boolean isMVCC() {
    return false;
  }
  
  public int getTransactionControl() {
    return 0;
  }
  
  public void setTransactionControl(Session paramSession, int paramInt) {
    super.setTransactionControl(paramSession, paramInt);
  }
  
  public void completeActions(Session paramSession) {
    endActionTPL(paramSession);
  }
  
  public boolean prepareCommitActions(Session paramSession) {
    paramSession.actionTimestamp = getNextGlobalChangeTimestamp();
    return true;
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
      for (byte b = 0; b < i; b++) {
        RowAction rowAction = (RowAction)paramSession.rowActionList.get(b);
        rowAction.commit(paramSession);
      } 
      adjustLobUsage(paramSession);
      persistCommit(paramSession);
      endTransactionTPL(paramSession);
    } finally {
      this.writeLock.unlock();
    } 
    paramSession.tempSet.clear();
    return true;
  }
  
  public void rollback(Session paramSession) {
    paramSession.abortTransaction = false;
    paramSession.actionTimestamp = getNextGlobalChangeTimestamp();
    paramSession.transactionEndTimestamp = paramSession.actionTimestamp;
    rollbackPartial(paramSession, 0, paramSession.transactionTimestamp);
    endTransaction(paramSession);
    this.writeLock.lock();
    try {
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
    for (int j = i - 1; j >= paramInt; j--) {
      RowAction rowAction = (RowAction)paramSession.rowActionList.get(j);
      if (rowAction != null && rowAction.type != 0 && rowAction.type != 3) {
        Row row = rowAction.memoryRow;
        if (row == null)
          row = (Row)rowAction.store.get(rowAction.getPos(), false); 
        if (row != null) {
          rowAction.rollback(paramSession, paramLong);
          int k = rowAction.mergeRollback(paramSession, paramLong, row);
          rowAction.store.rollbackRow(paramSession, row, k, this.txModel);
        } 
      } 
    } 
    paramSession.rowActionList.setSize(paramInt);
  }
  
  public RowAction addDeleteAction(Session paramSession, Table paramTable, PersistentStore paramPersistentStore, Row paramRow, int[] paramArrayOfint) {
    RowAction rowAction;
    synchronized (paramRow) {
      rowAction = RowAction.addDeleteAction(paramSession, paramTable, paramRow, paramArrayOfint);
    } 
    paramSession.rowActionList.add(rowAction);
    paramPersistentStore.delete(paramSession, paramRow);
    paramRow.rowAction = null;
    return rowAction;
  }
  
  public void addInsertAction(Session paramSession, Table paramTable, PersistentStore paramPersistentStore, Row paramRow, int[] paramArrayOfint) {
    RowAction rowAction = paramRow.rowAction;
    if (rowAction == null)
      throw Error.runtimeError(458, "null insert action "); 
    paramPersistentStore.indexRow(paramSession, paramRow);
    paramSession.rowActionList.add(rowAction);
    paramRow.rowAction = null;
  }
  
  public boolean canRead(Session paramSession, PersistentStore paramPersistentStore, Row paramRow, int paramInt, int[] paramArrayOfint) {
    return true;
  }
  
  public boolean canRead(Session paramSession, PersistentStore paramPersistentStore, long paramLong, int paramInt) {
    return true;
  }
  
  public void addTransactionInfo(CachedObject paramCachedObject) {}
  
  public void setTransactionInfo(PersistentStore paramPersistentStore, CachedObject paramCachedObject) {}
  
  public void removeTransactionInfo(CachedObject paramCachedObject) {}
  
  public void beginTransaction(Session paramSession) {
    if (!paramSession.isTransaction) {
      paramSession.actionTimestamp = getNextGlobalChangeTimestamp();
      paramSession.transactionTimestamp = paramSession.actionTimestamp;
      paramSession.isTransaction = true;
      this.transactionCount++;
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
      if (bool)
        if (paramSession.tempSet.isEmpty()) {
          lockTablesTPL(paramSession, paramStatement);
        } else {
          setWaitingSessionTPL(paramSession);
        }  
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void beginActionResume(Session paramSession) {
    paramSession.actionTimestamp = getNextGlobalChangeTimestamp();
    paramSession.actionStartTimestamp = paramSession.actionTimestamp;
    if (!paramSession.isTransaction) {
      paramSession.transactionTimestamp = paramSession.actionTimestamp;
      paramSession.isTransaction = true;
      this.transactionCount++;
    } 
  }
  
  public void removeTransactionInfo(long paramLong) {}
  
  void endTransaction(Session paramSession) {
    if (paramSession.isTransaction) {
      paramSession.isTransaction = false;
      this.transactionCount--;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\TransactionManager2PL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */