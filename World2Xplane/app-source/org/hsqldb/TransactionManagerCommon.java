package org.hsqldb;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.lib.MultiValueHashMap;
import org.hsqldb.lib.OrderedHashSet;

class TransactionManagerCommon {
  Database database;
  
  Session lobSession;
  
  int txModel;
  
  HsqlNameManager.HsqlName[] catalogNameList;
  
  ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  
  ReentrantReadWriteLock.WriteLock writeLock = this.lock.writeLock();
  
  LongDeque liveTransactionTimestamps = new LongDeque();
  
  AtomicLong globalChangeTimestamp = new AtomicLong(1L);
  
  int transactionCount = 0;
  
  HashMap tableWriteLocks = new HashMap();
  
  MultiValueHashMap tableReadLocks = new MultiValueHashMap();
  
  public LongKeyHashMap rowActionMap;
  
  void setTransactionControl(Session paramSession, int paramInt) {
    TransactionManagerMVCC transactionManagerMVCC = null;
    if (paramInt == this.txModel)
      return; 
    this.writeLock.lock();
    try {
      TransactionManagerMV2PL transactionManagerMV2PL;
      TransactionManager2PL transactionManager2PL;
      switch (this.txModel) {
        case 1:
        case 2:
          if (this.liveTransactionTimestamps.size() != 1)
            throw Error.error(3701); 
          break;
      } 
      switch (paramInt) {
        case 2:
          transactionManagerMVCC = new TransactionManagerMVCC(this.database);
          transactionManagerMVCC.liveTransactionTimestamps.addLast(paramSession.transactionTimestamp);
          break;
        case 1:
          transactionManagerMV2PL = new TransactionManagerMV2PL(this.database);
          transactionManagerMV2PL.liveTransactionTimestamps.addLast(paramSession.transactionTimestamp);
          break;
        case 0:
          transactionManager2PL = new TransactionManager2PL(this.database);
          break;
      } 
      transactionManager2PL.globalChangeTimestamp.set(this.globalChangeTimestamp.get());
      transactionManager2PL.transactionCount = this.transactionCount;
      this.database.txManager = transactionManager2PL;
      return;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  void adjustLobUsage(Session paramSession) {
    int i = paramSession.rowActionList.size();
    long l = paramSession.actionTimestamp;
    int j;
    for (j = 0; j < i; j++) {
      RowAction rowAction = (RowAction)paramSession.rowActionList.get(j);
      if (rowAction.type != 0 && rowAction.table.hasLobColumn) {
        int k = rowAction.getCommitTypeOn(l);
        Row row = rowAction.memoryRow;
        if (row == null)
          row = (Row)rowAction.store.get(rowAction.getPos(), false); 
        switch (k) {
          case 1:
            paramSession.sessionData.adjustLobUsageCount(rowAction.table, row.getData(), 1);
            break;
          case 2:
            paramSession.sessionData.adjustLobUsageCount(rowAction.table, row.getData(), -1);
            break;
        } 
      } 
    } 
    j = paramSession.rowActionList.size();
    if (j > i)
      for (int k = i; k < j; k++) {
        RowAction rowAction = (RowAction)paramSession.rowActionList.get(k);
        rowAction.commit(paramSession);
      }  
  }
  
  void persistCommit(Session paramSession) {
    int i = paramSession.rowActionList.size();
    boolean bool = false;
    for (byte b = 0; b < i; b++) {
      RowAction rowAction = (RowAction)paramSession.rowActionList.get(b);
      if (rowAction.type != 0) {
        int j = rowAction.getCommitTypeOn(paramSession.actionTimestamp);
        Row row = rowAction.memoryRow;
        if (row == null)
          row = (Row)rowAction.store.get(rowAction.getPos(), false); 
        if (rowAction.table.tableType != 3)
          bool = true; 
        try {
          rowAction.store.commitRow(paramSession, row, j, this.txModel);
          if (this.txModel == 0 || rowAction.table.tableType == 3) {
            rowAction.setAsNoOp();
            row.rowAction = null;
          } 
        } catch (HsqlException hsqlException) {
          this.database.logger.logWarningEvent("data commit failed", hsqlException);
        } 
      } 
    } 
    try {
      if (i > 0 && bool)
        this.database.logger.writeCommitStatement(paramSession); 
    } catch (HsqlException hsqlException) {
      this.database.logger.logWarningEvent("data commit failed", hsqlException);
    } 
  }
  
  void finaliseRows(Session paramSession, Object[] paramArrayOfObject, int paramInt1, int paramInt2) {
    for (int i = paramInt1; i < paramInt2; i++) {
      RowAction rowAction = (RowAction)paramArrayOfObject[i];
      postCommitAction(paramSession, rowAction);
    } 
  }
  
  void postCommitAction(Session paramSession, RowAction paramRowAction) {
    if (paramRowAction.type == 0)
      paramRowAction.store.postCommitAction(paramSession, paramRowAction); 
    if (paramRowAction.type == 3 && !paramRowAction.deleteComplete)
      try {
        paramRowAction.deleteComplete = true;
        if (paramRowAction.table.getTableType() == 3)
          return; 
        Row row = paramRowAction.memoryRow;
        if (row == null)
          row = (Row)paramRowAction.store.get(paramRowAction.getPos(), false); 
        paramRowAction.store.commitRow(paramSession, row, paramRowAction.type, this.txModel);
      } catch (Exception exception) {} 
  }
  
  void mergeRolledBackTransaction(Session paramSession, long paramLong, Object[] paramArrayOfObject, int paramInt1, int paramInt2) {
    int i;
    for (i = paramInt1; i < paramInt2; i++) {
      RowAction rowAction = (RowAction)paramArrayOfObject[i];
      Row row = rowAction.memoryRow;
      if (row == null) {
        if (rowAction.type == 0)
          continue; 
        row = (Row)rowAction.store.get(rowAction.getPos(), false);
      } 
      if (row != null)
        synchronized (row) {
          rowAction.mergeRollback(paramSession, paramLong, row);
        }  
      continue;
    } 
    for (i = paramInt2 - 1; i >= paramInt1; i--) {
      RowAction rowAction = (RowAction)paramArrayOfObject[i];
      rowAction.store.rollbackRow(paramSession, rowAction.memoryRow, rowAction.commitRollbackType, this.txModel);
    } 
  }
  
  void mergeTransaction(Object[] paramArrayOfObject, int paramInt1, int paramInt2, long paramLong) {
    for (int i = paramInt1; i < paramInt2; i++) {
      RowAction rowAction = (RowAction)paramArrayOfObject[i];
      rowAction.mergeToTimestamp(paramLong);
    } 
  }
  
  public long getNextGlobalChangeTimestamp() {
    return this.globalChangeTimestamp.incrementAndGet();
  }
  
  boolean checkDeadlock(Session paramSession, OrderedHashSet paramOrderedHashSet) {
    int i = paramSession.waitingSessions.size();
    for (byte b = 0; b < i; b++) {
      Session session = (Session)paramSession.waitingSessions.get(b);
      if (paramOrderedHashSet.contains(session))
        return false; 
      if (!checkDeadlock(session, paramOrderedHashSet))
        return false; 
    } 
    return true;
  }
  
  boolean checkDeadlock(Session paramSession1, Session paramSession2) {
    int i = paramSession1.waitingSessions.size();
    for (byte b = 0; b < i; b++) {
      Session session = (Session)paramSession1.waitingSessions.get(b);
      if (session == paramSession2)
        return false; 
      if (!checkDeadlock(session, paramSession2))
        return false; 
    } 
    return true;
  }
  
  void endActionTPL(Session paramSession) {
    if (paramSession.isolationLevel == 4 || paramSession.isolationLevel == 8)
      return; 
    if (paramSession.sessionContext.currentStatement == null)
      return; 
    if (paramSession.sessionContext.depth > 0)
      return; 
    HsqlNameManager.HsqlName[] arrayOfHsqlName = paramSession.sessionContext.currentStatement.getTableNamesForRead();
    if (arrayOfHsqlName.length == 0)
      return; 
    this.writeLock.lock();
    try {
      unlockReadTablesTPL(paramSession, arrayOfHsqlName);
      int i = paramSession.waitingSessions.size();
      if (i == 0)
        return; 
      boolean bool = false;
      byte b;
      for (b = 0; b < arrayOfHsqlName.length; b++) {
        if (this.tableWriteLocks.get(arrayOfHsqlName[b]) != paramSession) {
          bool = true;
          break;
        } 
      } 
      if (!bool)
        return; 
      bool = false;
      for (b = 0; b < i; b++) {
        Session session = (Session)paramSession.waitingSessions.get(b);
        if (session.abortTransaction) {
          bool = true;
          break;
        } 
        Statement statement = session.sessionContext.currentStatement;
        if (statement == null) {
          bool = true;
          break;
        } 
        if (ArrayUtil.containsAny((Object[])arrayOfHsqlName, (Object[])statement.getTableNamesForWrite())) {
          bool = true;
          break;
        } 
      } 
      if (!bool)
        return; 
      resetLocks(paramSession);
      resetLatchesMidTransaction(paramSession);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  void endTransactionTPL(Session paramSession) {
    unlockTablesTPL(paramSession);
    int i = paramSession.waitingSessions.size();
    if (i == 0)
      return; 
    resetLocks(paramSession);
    resetLatches(paramSession);
  }
  
  void resetLocks(Session paramSession) {
    int i = paramSession.waitingSessions.size();
    byte b;
    for (b = 0; b < i; b++) {
      Session session = (Session)paramSession.waitingSessions.get(b);
      session.tempUnlocked = false;
      long l = session.latch.getCount();
      if (l == 1L) {
        boolean bool = setWaitedSessionsTPL(session, session.sessionContext.currentStatement);
        if (bool && session.tempSet.isEmpty()) {
          lockTablesTPL(session, session.sessionContext.currentStatement);
          session.tempUnlocked = true;
        } 
      } 
    } 
    for (b = 0; b < i; b++) {
      Session session = (Session)paramSession.waitingSessions.get(b);
      if (!session.tempUnlocked && !session.abortTransaction)
        setWaitedSessionsTPL(session, session.sessionContext.currentStatement); 
    } 
  }
  
  void resetLatches(Session paramSession) {
    int i = paramSession.waitingSessions.size();
    for (byte b = 0; b < i; b++) {
      Session session = (Session)paramSession.waitingSessions.get(b);
      setWaitingSessionTPL(session);
    } 
    paramSession.waitingSessions.clear();
    paramSession.latch.setCount(0);
  }
  
  void resetLatchesMidTransaction(Session paramSession) {
    paramSession.tempSet.clear();
    paramSession.tempSet.addAll((Collection)paramSession.waitingSessions);
    paramSession.waitingSessions.clear();
    int i = paramSession.tempSet.size();
    for (byte b = 0; b < i; b++) {
      Session session = (Session)paramSession.tempSet.get(b);
      if (session.abortTransaction || session.tempSet.isEmpty());
      setWaitingSessionTPL(session);
    } 
    paramSession.tempSet.clear();
  }
  
  boolean setWaitedSessionsTPL(Session paramSession, Statement paramStatement) {
    paramSession.tempSet.clear();
    if (paramStatement == null)
      return true; 
    if (paramSession.abortTransaction)
      return false; 
    HsqlNameManager.HsqlName[] arrayOfHsqlName = paramStatement.getTableNamesForWrite();
    byte b;
    for (b = 0; b < arrayOfHsqlName.length; b++) {
      HsqlNameManager.HsqlName hsqlName = arrayOfHsqlName[b];
      if (hsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME) {
        Session session = (Session)this.tableWriteLocks.get(hsqlName);
        if (session != null && session != paramSession)
          paramSession.tempSet.add(session); 
        Iterator iterator = this.tableReadLocks.get(hsqlName);
        while (iterator.hasNext()) {
          session = (Session)iterator.next();
          if (session != paramSession)
            paramSession.tempSet.add(session); 
        } 
      } 
    } 
    arrayOfHsqlName = paramStatement.getTableNamesForRead();
    if (this.txModel == 1 && paramSession.isReadOnly())
      arrayOfHsqlName = this.catalogNameList; 
    for (b = 0; b < arrayOfHsqlName.length; b++) {
      HsqlNameManager.HsqlName hsqlName = arrayOfHsqlName[b];
      if (hsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME) {
        Session session = (Session)this.tableWriteLocks.get(hsqlName);
        if (session != null && session != paramSession)
          paramSession.tempSet.add(session); 
      } 
    } 
    if (paramSession.tempSet.isEmpty())
      return true; 
    if (checkDeadlock(paramSession, paramSession.tempSet))
      return true; 
    paramSession.tempSet.clear();
    paramSession.abortTransaction = true;
    return false;
  }
  
  void setWaitingSessionTPL(Session paramSession) {
    int i = paramSession.tempSet.size();
    assert paramSession.latch.getCount() <= (i + 1);
    for (byte b = 0; b < i; b++) {
      Session session = (Session)paramSession.tempSet.get(b);
      session.waitingSessions.add(paramSession);
    } 
    paramSession.tempSet.clear();
    paramSession.latch.setCount(i);
  }
  
  void lockTablesTPL(Session paramSession, Statement paramStatement) {
    if (paramStatement == null || paramSession.abortTransaction)
      return; 
    HsqlNameManager.HsqlName[] arrayOfHsqlName = paramStatement.getTableNamesForWrite();
    byte b;
    for (b = 0; b < arrayOfHsqlName.length; b++) {
      HsqlNameManager.HsqlName hsqlName = arrayOfHsqlName[b];
      if (hsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME)
        this.tableWriteLocks.put(hsqlName, paramSession); 
    } 
    arrayOfHsqlName = paramStatement.getTableNamesForRead();
    for (b = 0; b < arrayOfHsqlName.length; b++) {
      HsqlNameManager.HsqlName hsqlName = arrayOfHsqlName[b];
      if (hsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME)
        this.tableReadLocks.put(hsqlName, paramSession); 
    } 
  }
  
  void unlockTablesTPL(Session paramSession) {
    Iterator iterator = this.tableWriteLocks.values().iterator();
    while (iterator.hasNext()) {
      Session session = (Session)iterator.next();
      if (session == paramSession)
        iterator.remove(); 
    } 
    iterator = this.tableReadLocks.values().iterator();
    while (iterator.hasNext()) {
      Session session = (Session)iterator.next();
      if (session == paramSession)
        iterator.remove(); 
    } 
  }
  
  void unlockReadTablesTPL(Session paramSession, HsqlNameManager.HsqlName[] paramArrayOfHsqlName) {
    for (byte b = 0; b < paramArrayOfHsqlName.length; b++)
      this.tableReadLocks.remove(paramArrayOfHsqlName[b], paramSession); 
  }
  
  boolean hasLocks(Session paramSession, Statement paramStatement) {
    if (paramStatement == null)
      return true; 
    HsqlNameManager.HsqlName[] arrayOfHsqlName = paramStatement.getTableNamesForWrite();
    byte b;
    for (b = 0; b < arrayOfHsqlName.length; b++) {
      HsqlNameManager.HsqlName hsqlName = arrayOfHsqlName[b];
      if (hsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME) {
        Session session = (Session)this.tableWriteLocks.get(hsqlName);
        if (session != null && session != paramSession)
          return false; 
        Iterator iterator = this.tableReadLocks.get(hsqlName);
        while (iterator.hasNext()) {
          session = (Session)iterator.next();
          if (session != paramSession)
            return false; 
        } 
      } 
    } 
    arrayOfHsqlName = paramStatement.getTableNamesForRead();
    for (b = 0; b < arrayOfHsqlName.length; b++) {
      HsqlNameManager.HsqlName hsqlName = arrayOfHsqlName[b];
      if (hsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME) {
        Session session = (Session)this.tableWriteLocks.get(hsqlName);
        if (session != null && session != paramSession)
          return false; 
      } 
    } 
    return true;
  }
  
  long getFirstLiveTransactionTimestamp() {
    return this.liveTransactionTimestamps.isEmpty() ? Long.MAX_VALUE : this.liveTransactionTimestamps.get(0);
  }
  
  RowAction[] getRowActionList() {
    this.writeLock.lock();
    try {
      Session[] arrayOfSession = this.database.sessionManager.getAllSessions();
      int[] arrayOfInt = new int[arrayOfSession.length];
      byte b1 = 0;
      int i = 0;
      for (byte b2 = 0; b2 < arrayOfSession.length; b2++)
        i += arrayOfSession[b2].getTransactionSize(); 
      RowAction[] arrayOfRowAction = new RowAction[i];
      while (true) {
        i = 0;
        long l = Long.MAX_VALUE;
        byte b3 = 0;
        for (byte b4 = 0; b4 < arrayOfSession.length; b4++) {
          int j = arrayOfSession[b4].getTransactionSize();
          if (arrayOfInt[b4] < j) {
            RowAction rowAction = (RowAction)(arrayOfSession[b4]).rowActionList.get(arrayOfInt[b4]);
            if (rowAction.actionTimestamp < l) {
              l = rowAction.actionTimestamp;
              b3 = b4;
            } 
            i = 1;
          } 
        } 
        if (i == 0)
          return arrayOfRowAction; 
        HsqlArrayList hsqlArrayList = (arrayOfSession[b3]).rowActionList;
        while (arrayOfInt[b3] < hsqlArrayList.size()) {
          RowAction rowAction = (RowAction)hsqlArrayList.get(arrayOfInt[b3]);
          if (rowAction.actionTimestamp == l + 1L)
            l++; 
          if (rowAction.actionTimestamp == l) {
            arrayOfRowAction[b1++] = rowAction;
            arrayOfInt[b3] = arrayOfInt[b3] + 1;
          } 
        } 
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\TransactionManagerCommon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */