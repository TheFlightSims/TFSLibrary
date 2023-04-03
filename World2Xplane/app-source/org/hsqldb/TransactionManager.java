package org.hsqldb;

import org.hsqldb.persist.CachedObject;
import org.hsqldb.persist.PersistentStore;

public interface TransactionManager {
  public static final int LOCKS = 0;
  
  public static final int MVLOCKS = 1;
  
  public static final int MVCC = 2;
  
  public static final int ACTION_READ = 0;
  
  public static final int ACTION_DUP = 1;
  
  public static final int ACTION_REF = 2;
  
  long getGlobalChangeTimestamp();
  
  long getNextGlobalChangeTimestamp();
  
  RowAction addDeleteAction(Session paramSession, Table paramTable, PersistentStore paramPersistentStore, Row paramRow, int[] paramArrayOfint);
  
  void addInsertAction(Session paramSession, Table paramTable, PersistentStore paramPersistentStore, Row paramRow, int[] paramArrayOfint);
  
  void beginAction(Session paramSession, Statement paramStatement);
  
  void beginActionResume(Session paramSession);
  
  void beginTransaction(Session paramSession);
  
  boolean canRead(Session paramSession, PersistentStore paramPersistentStore, Row paramRow, int paramInt, int[] paramArrayOfint);
  
  boolean canRead(Session paramSession, PersistentStore paramPersistentStore, long paramLong, int paramInt);
  
  boolean commitTransaction(Session paramSession);
  
  void completeActions(Session paramSession);
  
  int getTransactionControl();
  
  boolean isMVRows();
  
  boolean isMVCC();
  
  boolean prepareCommitActions(Session paramSession);
  
  void rollback(Session paramSession);
  
  void rollbackAction(Session paramSession);
  
  void rollbackSavepoint(Session paramSession, int paramInt);
  
  void rollbackPartial(Session paramSession, int paramInt, long paramLong);
  
  void setTransactionControl(Session paramSession, int paramInt);
  
  void addTransactionInfo(CachedObject paramCachedObject);
  
  void setTransactionInfo(PersistentStore paramPersistentStore, CachedObject paramCachedObject);
  
  void removeTransactionInfo(CachedObject paramCachedObject);
  
  void removeTransactionInfo(long paramLong);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\TransactionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */