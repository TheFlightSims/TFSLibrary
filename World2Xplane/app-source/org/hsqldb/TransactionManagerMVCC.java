package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.HsqlDeque;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.persist.CachedObject;
import org.hsqldb.persist.PersistentStore;

public class TransactionManagerMVCC extends TransactionManagerCommon implements TransactionManager {
  HsqlDeque committedTransactions = new HsqlDeque();
  
  LongDeque committedTransactionTimestamps = new LongDeque();
  
  boolean isLockedMode;
  
  Session catalogWriteSession;
  
  long lockTxTs;
  
  long lockSessionId;
  
  long unlockTxTs;
  
  long unlockSessionId;
  
  int redoCount = 0;
  
  public TransactionManagerMVCC(Database paramDatabase) {
    this.database = paramDatabase;
    this.lobSession = this.database.sessionManager.getSysLobSession();
    this.rowActionMap = new LongKeyHashMap(10000);
    this.txModel = 2;
  }
  
  public long getGlobalChangeTimestamp() {
    return this.globalChangeTimestamp.get();
  }
  
  public boolean isMVRows() {
    return true;
  }
  
  public boolean isMVCC() {
    return true;
  }
  
  public int getTransactionControl() {
    return 2;
  }
  
  public void setTransactionControl(Session paramSession, int paramInt) {
    super.setTransactionControl(paramSession, paramInt);
  }
  
  public void completeActions(Session paramSession) {}
  
  public boolean prepareCommitActions(Session paramSession) {
    if (paramSession.abortTransaction)
      return false; 
    this.writeLock.lock();
    try {
      int i = paramSession.rowActionList.size();
      byte b;
      for (b = 0; b < i; b++) {
        RowAction rowAction = (RowAction)paramSession.rowActionList.get(b);
        if (!rowAction.canCommit(paramSession, paramSession.tempSet))
          return false; 
      } 
      paramSession.actionTimestamp = getNextGlobalChangeTimestamp();
      for (b = 0; b < i; b++) {
        RowAction rowAction = (RowAction)paramSession.rowActionList.get(b);
        rowAction.prepareCommit(paramSession);
      } 
      for (b = 0; b < paramSession.tempSet.size(); b++) {
        Session session = ((RowActionBase)paramSession.tempSet.get(b)).session;
        session.abortTransaction = true;
      } 
      b = 1;
      return b;
    } finally {
      this.writeLock.unlock();
      paramSession.tempSet.clear();
    } 
  }
  
  public boolean commitTransaction(Session paramSession) {
    if (paramSession.abortTransaction)
      return false; 
    this.writeLock.lock();
    try {
      int i = paramSession.rowActionList.size();
      int j;
      for (j = 0; j < i; j++) {
        RowAction rowAction = (RowAction)paramSession.rowActionList.get(j);
        if (!rowAction.canCommit(paramSession, paramSession.tempSet))
          return false; 
      } 
      paramSession.actionTimestamp = getNextGlobalChangeTimestamp();
      paramSession.transactionEndTimestamp = paramSession.actionTimestamp;
      endTransaction(paramSession);
      for (j = 0; j < i; j++) {
        RowAction rowAction = (RowAction)paramSession.rowActionList.get(j);
        rowAction.commit(paramSession);
      } 
      for (j = 0; j < paramSession.tempSet.size(); j++) {
        Session session = ((RowActionBase)paramSession.tempSet.get(j)).session;
        session.abortTransaction = true;
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
      } else if (paramSession.rowActionList.size() > 0) {
        Object[] arrayOfObject = paramSession.rowActionList.toArray();
        addToCommittedQueue(paramSession, arrayOfObject);
      } 
      endTransactionTPL(paramSession);
      paramSession.isTransaction = false;
      countDownLatches(paramSession);
    } finally {
      this.writeLock.unlock();
      paramSession.tempSet.clear();
    } 
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
      paramSession.isTransaction = false;
      countDownLatches(paramSession);
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
  }
  
  public void rollbackPartial(Session paramSession, int paramInt, long paramLong) {
    int i = paramSession.rowActionList.size();
    if (paramInt == i)
      return; 
    for (int j = paramInt; j < i; j++) {
      RowAction rowAction = (RowAction)paramSession.rowActionList.get(j);
      if (rowAction == null)
        throw Error.runtimeError(458, "TXManager - null rollback action "); 
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
    RowAction rowAction = addDeleteActionToRow(paramSession, paramTable, paramPersistentStore, paramRow, paramArrayOfint);
    Session session = null;
    boolean bool = true;
    if (rowAction == null) {
      this.writeLock.lock();
      try {
        rollbackAction(paramSession);
        if (paramSession.isolationLevel == 4 || paramSession.isolationLevel == 8) {
          paramSession.tempSet.clear();
          paramSession.redoAction = false;
          paramSession.abortTransaction = paramSession.txConflictRollback;
          throw Error.error(4871);
        } 
        if (paramRow.rowAction != null && paramRow.rowAction.isDeleted()) {
          paramSession.tempSet.clear();
          paramSession.redoAction = true;
          this.redoCount++;
          throw Error.error(4871);
        } 
        bool = !paramSession.tempSet.isEmpty();
        if (bool) {
          session = ((RowActionBase)paramSession.tempSet.get(0)).session;
          paramSession.tempSet.clear();
          if (session != null)
            bool = checkDeadlock(paramSession, session); 
        } 
        if (bool) {
          paramSession.redoAction = true;
          if (session != null) {
            session.waitingSessions.add(paramSession);
            paramSession.waitedSessions.add(session);
            paramSession.latch.countUp();
          } 
          this.redoCount++;
        } else {
          paramSession.redoAction = false;
          paramSession.abortTransaction = paramSession.txConflictRollback;
        } 
        throw Error.error(4871);
      } finally {
        this.writeLock.unlock();
      } 
    } 
    paramSession.rowActionList.add(rowAction);
    return rowAction;
  }
  
  public void addInsertAction(Session paramSession, Table paramTable, PersistentStore paramPersistentStore, Row paramRow, int[] paramArrayOfint) {
    RowAction rowAction = paramRow.rowAction;
    Session session = null;
    boolean bool = false;
    boolean bool1 = true;
    HsqlException hsqlException = null;
    if (rowAction == null)
      throw Error.runtimeError(458, "TXManager - null insert action "); 
    try {
      paramPersistentStore.indexRow(paramSession, paramRow);
    } catch (HsqlException hsqlException1) {
      if (paramSession.tempSet.isEmpty())
        throw hsqlException1; 
      bool = true;
      hsqlException = hsqlException1;
    } 
    if (!bool) {
      paramSession.rowActionList.add(rowAction);
      return;
    } 
    this.writeLock.lock();
    try {
      rollbackAction(paramSession);
      RowActionBase rowActionBase = (RowActionBase)paramSession.tempSet.get(0);
      session = rowActionBase.session;
      paramSession.tempSet.clear();
      if (rowActionBase.commitTimestamp != 0L)
        bool1 = false; 
      switch (paramSession.isolationLevel) {
        case 4:
        case 8:
          bool = false;
          break;
        default:
          bool = checkDeadlock(paramSession, session);
          break;
      } 
      if (bool) {
        paramSession.redoAction = true;
        if (bool1) {
          session.waitingSessions.add(paramSession);
          paramSession.waitedSessions.add(session);
          paramSession.latch.countUp();
        } 
        this.redoCount++;
      } else {
        paramSession.abortTransaction = paramSession.txConflictRollback;
        paramSession.redoAction = false;
      } 
      throw Error.error(hsqlException, 4871, null);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public boolean canRead(Session paramSession, PersistentStore paramPersistentStore, Row paramRow, int paramInt, int[] paramArrayOfint) {
    RowAction rowAction = paramRow.rowAction;
    return (rowAction == null) ? true : ((rowAction.table.tableType == 3) ? true : ((paramInt == 0) ? rowAction.canRead(paramSession, 0) : ((paramInt == 2) ? rowAction.canRead(paramSession, 0) : rowAction.canRead(paramSession, paramInt))));
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
    if ((row.getTable()).tableType == 5) {
      RowAction rowAction = (RowAction)this.rowActionMap.get(paramCachedObject.getPos());
      if (rowAction != null) {
        HsqlException hsqlException = Error.error(4871, "TXManager - row exists");
        this.database.logger.logSevereEvent("TXManager MVROWS", hsqlException);
        throw hsqlException;
      } 
      this.rowActionMap.put(paramCachedObject.getPos(), row.rowAction);
    } 
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
    Row row = (Row)paramCachedObject;
    if ((row.getTable()).tableType == 5)
      this.rowActionMap.remove(row.getPos()); 
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
      long l1;
      Object[] arrayOfObject;
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
        this.liveTransactionTimestamps.addLast(paramSession.transactionTimestamp);
        this.transactionCount++;
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void beginAction(Session paramSession, Statement paramStatement) {
    if (paramSession.isTransaction)
      return; 
    if (paramStatement == null)
      return; 
    this.writeLock.lock();
    try {
      if (paramStatement.getCompileTimestamp() < this.database.schemaManager.getSchemaChangeTimestamp()) {
        paramStatement = paramSession.statementManager.getStatement(paramSession, paramStatement);
        paramSession.sessionContext.currentStatement = paramStatement;
        if (paramStatement == null)
          return; 
      } 
      paramSession.isPreTransaction = true;
      if (!this.isLockedMode && !paramStatement.isCatalogLock())
        return; 
      beginActionTPL(paramSession, paramStatement);
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
      paramSession.isPreTransaction = false;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  RowAction addDeleteActionToRow(Session paramSession, Table paramTable, PersistentStore paramPersistentStore, Row paramRow, int[] paramArrayOfint) {
    RowAction rowAction = null;
    synchronized (paramRow) {
      switch (paramTable.tableType) {
        case 5:
          this.rowActionMap.getWriteLock().lock();
          try {
            rowAction = (RowAction)this.rowActionMap.get(paramRow.getPos());
            if (rowAction == null) {
              rowAction = RowAction.addDeleteAction(paramSession, paramTable, paramRow, paramArrayOfint);
              if (rowAction != null)
                addTransactionInfo(paramRow); 
            } else {
              paramRow.rowAction = rowAction;
              rowAction = RowAction.addDeleteAction(paramSession, paramTable, paramRow, paramArrayOfint);
            } 
          } finally {
            this.rowActionMap.getWriteLock().unlock();
          } 
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
    return rowAction;
  }
  
  void endTransaction(Session paramSession) {
    long l = paramSession.transactionTimestamp;
    int i = this.liveTransactionTimestamps.indexOf(l);
    if (i >= 0) {
      this.transactionCount--;
      this.liveTransactionTimestamps.remove(i);
      mergeExpiredTransactions(paramSession);
    } 
  }
  
  private void countDownLatches(Session paramSession) {
    for (byte b = 0; b < paramSession.waitingSessions.size(); b++) {
      Session session = (Session)paramSession.waitingSessions.get(b);
      session.waitedSessions.remove(paramSession);
      session.latch.countDown();
    } 
    paramSession.waitingSessions.clear();
  }
  
  void getTransactionSessions(HashSet paramHashSet) {
    Session[] arrayOfSession = this.database.sessionManager.getAllSessions();
    for (byte b = 0; b < arrayOfSession.length; b++) {
      long l = arrayOfSession[b].getTransactionTimestamp();
      if (this.liveTransactionTimestamps.contains(l)) {
        paramHashSet.add(arrayOfSession[b]);
      } else if ((arrayOfSession[b]).isPreTransaction) {
        paramHashSet.add(arrayOfSession[b]);
      } else if ((arrayOfSession[b]).isTransaction) {
        paramHashSet.add(arrayOfSession[b]);
      } 
    } 
  }
  
  void endTransactionTPL(Session paramSession) {
    if (this.catalogWriteSession != paramSession)
      return; 
    Session session = null;
    paramSession.waitingSessions.size();
    byte b;
    for (b = 0; b < paramSession.waitingSessions.size(); b++) {
      Session session1 = (Session)paramSession.waitingSessions.get(b);
      Statement statement = session1.sessionContext.currentStatement;
      if (statement != null && statement.isCatalogLock()) {
        session = session1;
        break;
      } 
    } 
    if (session == null) {
      this.catalogWriteSession = null;
      this.isLockedMode = false;
    } else {
      for (b = 0; b < paramSession.waitingSessions.size(); b++) {
        Session session1 = (Session)paramSession.waitingSessions.get(b);
        if (session1 != session) {
          session1.waitedSessions.add(session);
          session.waitingSessions.add(session1);
          session1.latch.countUp();
        } 
      } 
      this.catalogWriteSession = session;
    } 
    this.unlockTxTs = paramSession.actionTimestamp;
    this.unlockSessionId = paramSession.getId();
  }
  
  boolean beginActionTPL(Session paramSession, Statement paramStatement) {
    if (paramStatement == null)
      return true; 
    if (paramSession.abortTransaction)
      return false; 
    if (paramSession == this.catalogWriteSession)
      return true; 
    paramSession.tempSet.clear();
    if (paramStatement.isCatalogLock() && this.catalogWriteSession == null) {
      this.catalogWriteSession = paramSession;
      this.isLockedMode = true;
      this.lockTxTs = paramSession.actionTimestamp;
      this.lockSessionId = paramSession.getId();
      getTransactionSessions((HashSet)paramSession.tempSet);
      paramSession.tempSet.remove(paramSession);
      if (!paramSession.tempSet.isEmpty())
        setWaitingSessionTPL(paramSession); 
      return true;
    } 
    if (!this.isLockedMode)
      return true; 
    if ((paramStatement.getTableNamesForWrite()).length > 0) {
      if ((paramStatement.getTableNamesForWrite()[0]).schema == SqlInvariants.LOBS_SCHEMA_HSQLNAME)
        return true; 
    } else if ((paramStatement.getTableNamesForRead()).length > 0) {
      if ((paramStatement.getTableNamesForRead()[0]).schema == SqlInvariants.LOBS_SCHEMA_HSQLNAME)
        return true; 
    } else {
      return true;
    } 
    if (paramSession.waitingSessions.contains(this.catalogWriteSession))
      return true; 
    if (this.catalogWriteSession.waitingSessions.add(paramSession)) {
      paramSession.waitedSessions.add(this.catalogWriteSession);
      paramSession.latch.countUp();
    } 
    return true;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\TransactionManagerMVCC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */