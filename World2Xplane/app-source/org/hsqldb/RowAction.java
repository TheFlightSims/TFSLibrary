package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.persist.PersistentStore;

public class RowAction extends RowActionBase {
  final TableBase table;
  
  final PersistentStore store;
  
  Row memoryRow;
  
  long rowId;
  
  boolean isMemory;
  
  RowAction updatedAction;
  
  public static RowAction addInsertAction(Session paramSession, TableBase paramTableBase, Row paramRow) {
    RowAction rowAction = new RowAction(paramSession, paramTableBase, (byte)1, paramRow, null);
    paramRow.rowAction = rowAction;
    return rowAction;
  }
  
  public static RowAction addDeleteAction(Session paramSession, TableBase paramTableBase, Row paramRow, int[] paramArrayOfint) {
    RowAction rowAction = paramRow.rowAction;
    if (rowAction == null) {
      rowAction = new RowAction(paramSession, paramTableBase, (byte)2, paramRow, paramArrayOfint);
      paramRow.rowAction = rowAction;
      return rowAction;
    } 
    return rowAction.addDeleteAction(paramSession, paramArrayOfint);
  }
  
  public static boolean addRefAction(Session paramSession, Row paramRow, int[] paramArrayOfint) {
    RowAction rowAction = paramRow.rowAction;
    if (rowAction == null) {
      rowAction = new RowAction(paramSession, paramRow.getTable(), (byte)5, paramRow, paramArrayOfint);
      paramRow.rowAction = rowAction;
      return true;
    } 
    return rowAction.addRefAction(paramSession, paramArrayOfint);
  }
  
  public RowAction(Session paramSession, TableBase paramTableBase, byte paramByte, Row paramRow, int[] paramArrayOfint) {
    this.session = paramSession;
    this.type = paramByte;
    this.actionTimestamp = paramSession.actionTimestamp;
    this.table = paramTableBase;
    this.store = paramTableBase.getRowStore(paramSession);
    this.isMemory = paramRow.isMemory();
    this.memoryRow = paramRow;
    this.rowId = paramRow.getPos();
    this.changeColumnMap = paramArrayOfint;
  }
  
  private RowAction(RowAction paramRowAction) {
    this.session = paramRowAction.session;
    this.type = paramRowAction.type;
    this.actionTimestamp = paramRowAction.actionTimestamp;
    this.table = paramRowAction.table;
    this.store = paramRowAction.store;
    this.isMemory = paramRowAction.isMemory;
    this.memoryRow = paramRowAction.memoryRow;
    this.rowId = paramRowAction.rowId;
    this.changeColumnMap = paramRowAction.changeColumnMap;
  }
  
  public synchronized int getType() {
    return this.type;
  }
  
  synchronized RowAction addDeleteAction(Session paramSession, int[] paramArrayOfint) {
    if (this.type == 0) {
      setAsAction(paramSession, (byte)2);
      this.changeColumnMap = paramArrayOfint;
    } else {
      RowActionBase rowActionBase1 = this;
      label35: while (true) {
        while (rowActionBase1.rolledback) {
          if (rowActionBase1.next == null)
            break label35; 
          rowActionBase1 = rowActionBase1.next;
        } 
        switch (rowActionBase1.type) {
          case 1:
            if (rowActionBase1.commitTimestamp == 0L && paramSession != rowActionBase1.session)
              throw Error.runtimeError(201, "RowAction"); 
            break;
          case 2:
          case 3:
            if (paramSession != rowActionBase1.session) {
              if (rowActionBase1.commitTimestamp == 0L) {
                if (!paramSession.tempSet.isEmpty())
                  paramSession.tempSet.clear(); 
                paramSession.tempSet.add(rowActionBase1);
              } 
              return null;
            } 
            break;
          case 5:
            if (paramSession != rowActionBase1.session && rowActionBase1.commitTimestamp == 0L && (paramArrayOfint == null || ArrayUtil.haveCommonElement(paramArrayOfint, rowActionBase1.changeColumnMap))) {
              if (!paramSession.tempSet.isEmpty())
                paramSession.tempSet.clear(); 
              paramSession.tempSet.add(rowActionBase1);
              return null;
            } 
            break;
        } 
        if (rowActionBase1.next == null)
          break; 
        rowActionBase1 = rowActionBase1.next;
      } 
      RowActionBase rowActionBase2 = new RowActionBase(paramSession, (byte)2);
      rowActionBase2.changeColumnMap = paramArrayOfint;
      rowActionBase1.next = rowActionBase2;
    } 
    return this;
  }
  
  synchronized boolean addRefAction(Session paramSession, int[] paramArrayOfint) {
    if (this.type == 0) {
      setAsAction(paramSession, (byte)5);
      this.changeColumnMap = paramArrayOfint;
      return true;
    } 
    RowAction rowAction = this;
    while (true) {
      if (paramSession == rowAction.session) {
        if (rowAction.type == 5 && rowAction.changeColumnMap == paramArrayOfint && rowAction.commitTimestamp == 0L)
          return false; 
        if (rowAction.type == 1 && rowAction.commitTimestamp == 0L)
          return false; 
      } else if (rowAction.type == 2 && rowAction.commitTimestamp == 0L && (rowAction.changeColumnMap == null || ArrayUtil.haveCommonElement(paramArrayOfint, rowAction.changeColumnMap))) {
        if (!paramSession.tempSet.isEmpty())
          paramSession.tempSet.clear(); 
        paramSession.tempSet.add(rowAction);
        return false;
      } 
      if (rowAction.next == null) {
        RowActionBase rowActionBase1 = new RowActionBase(paramSession, (byte)5);
        rowActionBase1.changeColumnMap = paramArrayOfint;
        rowAction.next = rowActionBase1;
        return true;
      } 
      RowActionBase rowActionBase = rowAction.next;
    } 
  }
  
  public boolean checkDeleteActions() {
    return false;
  }
  
  public synchronized RowAction duplicate(Row paramRow) {
    return new RowAction(this.session, this.table, this.type, paramRow, this.changeColumnMap);
  }
  
  synchronized void setAsAction(Session paramSession, byte paramByte) {
    this.session = paramSession;
    this.type = paramByte;
    this.actionTimestamp = paramSession.actionTimestamp;
    this.changeColumnMap = null;
  }
  
  synchronized void setAsAction(RowActionBase paramRowActionBase) {
    super.setAsAction(paramRowActionBase);
  }
  
  public void setAsNoOp() {
    this.session = null;
    this.actionTimestamp = 0L;
    this.commitTimestamp = 0L;
    this.rolledback = false;
    this.deleteComplete = false;
    this.changeColumnMap = null;
    this.prepared = false;
    this.type = 0;
    this.next = null;
  }
  
  private void setAsDeleteFinal(long paramLong) {
    this.actionTimestamp = 0L;
    this.commitTimestamp = paramLong;
    this.rolledback = false;
    this.deleteComplete = false;
    this.prepared = false;
    this.changeColumnMap = null;
    this.type = 3;
    this.next = null;
  }
  
  synchronized void prepareCommit(Session paramSession) {
    RowActionBase rowActionBase = this;
    do {
      if (rowActionBase.session == paramSession && rowActionBase.commitTimestamp == 0L)
        rowActionBase.prepared = true; 
      rowActionBase = rowActionBase.next;
    } while (rowActionBase != null);
  }
  
  synchronized int commit(Session paramSession) {
    RowAction rowAction = this;
    byte b = 0;
    while (true) {
      if (rowAction.session == paramSession && rowAction.commitTimestamp == 0L) {
        rowAction.commitTimestamp = paramSession.actionTimestamp;
        rowAction.prepared = false;
        if (rowAction.type == 1) {
          b = rowAction.type;
        } else if (rowAction.type == 2) {
          if (b == 1) {
            b = 4;
          } else {
            b = rowAction.type;
          } 
        } 
      } 
      RowActionBase rowActionBase = rowAction.next;
      if (rowActionBase == null)
        return b; 
    } 
  }
  
  public boolean isDeleted() {
    RowAction rowAction = this;
    while (true) {
      if (rowAction.commitTimestamp != 0L && (rowAction.type == 2 || rowAction.type == 3))
        return true; 
      RowActionBase rowActionBase = rowAction.next;
      if (rowActionBase == null)
        return false; 
    } 
  }
  
  synchronized int getCommitTypeOn(long paramLong) {
    RowAction rowAction = this;
    byte b = 0;
    while (true) {
      if (rowAction.commitTimestamp == paramLong)
        if (rowAction.type == 1) {
          b = rowAction.type;
        } else if (rowAction.type == 2) {
          if (b == 1) {
            b = 4;
          } else {
            b = rowAction.type;
          } 
        }  
      RowActionBase rowActionBase = rowAction.next;
      if (rowActionBase == null)
        return b; 
    } 
  }
  
  synchronized boolean canCommit(Session paramSession, OrderedHashSet paramOrderedHashSet) {
    long l1 = paramSession.transactionTimestamp;
    long l2 = 0L;
    boolean bool1 = (paramSession.isolationLevel == 2) ? true : false;
    boolean bool2 = false;
    RowActionBase rowActionBase = this;
    if (bool1)
      while (true) {
        if (rowActionBase.session == paramSession && rowActionBase.type == 2 && rowActionBase.commitTimestamp == 0L)
          l1 = rowActionBase.actionTimestamp; 
        rowActionBase = rowActionBase.next;
        if (rowActionBase == null) {
          rowActionBase = this;
          break;
        } 
      }  
    while (true) {
      if (rowActionBase.session == paramSession) {
        if (rowActionBase.type == 2)
          bool2 = true; 
      } else {
        if (rowActionBase.rolledback || rowActionBase.type != 2) {
          rowActionBase = rowActionBase.next;
        } else {
          if (rowActionBase.prepared)
            return false; 
          if (rowActionBase.commitTimestamp == 0L) {
            paramOrderedHashSet.add(rowActionBase);
          } else if (rowActionBase.commitTimestamp > l2) {
            l2 = rowActionBase.commitTimestamp;
          } 
          rowActionBase = rowActionBase.next;
        } 
        if (rowActionBase == null)
          return !bool2 ? true : ((l2 < l1)); 
        continue;
      } 
      rowActionBase = rowActionBase.next;
    } 
  }
  
  synchronized void complete(Session paramSession) {
    RowActionBase rowActionBase = this;
    do {
      if (rowActionBase.session == paramSession && rowActionBase.actionTimestamp == 0L)
        rowActionBase.actionTimestamp = paramSession.actionTimestamp; 
      rowActionBase = rowActionBase.next;
    } while (rowActionBase != null);
  }
  
  synchronized boolean complete(Session paramSession, OrderedHashSet paramOrderedHashSet) {
    boolean bool1 = (paramSession.isolationLevel == 2) ? true : false;
    boolean bool2 = true;
    RowAction rowAction = this;
    while (true) {
      RowActionBase rowActionBase;
      if (rowAction.rolledback || rowAction.type == 0) {
        rowActionBase = rowAction.next;
      } else {
        if (rowActionBase.session != paramSession) {
          if (rowActionBase.prepared) {
            paramOrderedHashSet.add(rowActionBase.session);
            return false;
          } 
          if (bool1) {
            if (rowActionBase.commitTimestamp > paramSession.actionTimestamp) {
              paramOrderedHashSet.add(paramSession);
              bool2 = false;
            } else if (rowActionBase.commitTimestamp == 0L) {
              paramOrderedHashSet.add(rowActionBase.session);
              bool2 = false;
            } 
          } else if (rowActionBase.commitTimestamp > paramSession.transactionTimestamp) {
            return false;
          } 
        } 
        rowActionBase = rowActionBase.next;
      } 
      if (rowActionBase == null)
        return bool2; 
    } 
  }
  
  synchronized int getActionType(long paramLong) {
    byte b = 0;
    RowAction rowAction = this;
    while (true) {
      if (rowAction.actionTimestamp == paramLong)
        if (rowAction.type == 2) {
          if (b == 1) {
            b = 4;
          } else {
            b = rowAction.type;
          } 
        } else if (rowAction.type == 1) {
          b = rowAction.type;
        }  
      RowActionBase rowActionBase = rowAction.next;
      if (rowActionBase == null)
        return b; 
    } 
  }
  
  public synchronized long getPos() {
    return this.rowId;
  }
  
  synchronized void setPos(long paramLong) {
    this.rowId = paramLong;
  }
  
  private int getRollbackType(Session paramSession) {
    byte b = 0;
    RowAction rowAction = this;
    while (true) {
      if (rowAction.session == paramSession && rowAction.rolledback)
        if (rowAction.type == 2) {
          if (b == 1) {
            b = 4;
          } else {
            b = rowAction.type;
          } 
        } else if (rowAction.type == 1) {
          b = rowAction.type;
        }  
      RowActionBase rowActionBase = rowAction.next;
      if (rowActionBase == null)
        return b; 
    } 
  }
  
  synchronized void rollback(Session paramSession, long paramLong) {
    RowActionBase rowActionBase = this;
    do {
      if (rowActionBase.session == paramSession && rowActionBase.commitTimestamp == 0L && rowActionBase.actionTimestamp >= paramLong) {
        rowActionBase.commitTimestamp = paramSession.actionTimestamp;
        rowActionBase.rolledback = true;
        rowActionBase.prepared = false;
      } 
      rowActionBase = rowActionBase.next;
    } while (rowActionBase != null);
  }
  
  synchronized int mergeRollback(Session paramSession, long paramLong, Row paramRow) {
    RowAction rowAction1 = this;
    RowAction rowAction2 = null;
    RowAction rowAction3 = null;
    int i = getRollbackType(paramSession);
    while (true) {
      if (rowAction1.session == paramSession && rowAction1.rolledback) {
        if (rowAction3 != null)
          rowAction3.next = null; 
      } else if (rowAction2 == null) {
        rowAction2 = rowAction3 = rowAction1;
      } else {
        rowAction3.next = rowAction1;
        rowAction3 = rowAction1;
      } 
      RowActionBase rowActionBase = rowAction1.next;
      if (rowActionBase == null) {
        if (rowAction2 == null)
          break; 
        if (rowAction2 != this)
          setAsAction(rowAction2); 
        this.commitRollbackType = (byte)i;
        return i;
      } 
    } 
    switch (i) {
      case 1:
      case 4:
        setAsDeleteFinal(paramLong);
        this.commitRollbackType = (byte)i;
        return i;
    } 
    setAsNoOp();
    this.commitRollbackType = (byte)i;
    return i;
  }
  
  synchronized void mergeToTimestamp(long paramLong) {
    RowAction rowAction1 = this;
    RowAction rowAction2 = null;
    RowAction rowAction3 = null;
    int i = getCommitTypeOn(paramLong);
    if (this.type == 3 || this.type == 0)
      return; 
    if (i == 2 || i == 4) {
      setAsDeleteFinal(paramLong);
      return;
    } 
    while (true) {
      boolean bool = false;
      if (rowAction1.commitTimestamp != 0L)
        if (rowAction1.commitTimestamp <= paramLong) {
          bool = true;
        } else if (rowAction1.type == 5) {
          bool = true;
        }  
      if (bool) {
        if (rowAction3 != null)
          rowAction3.next = null; 
      } else if (rowAction2 == null) {
        rowAction2 = rowAction3 = rowAction1;
      } else {
        rowAction3.next = rowAction1;
        rowAction3 = rowAction1;
      } 
      RowActionBase rowActionBase = rowAction1.next;
      if (rowActionBase == null) {
        if (rowAction2 == null)
          break; 
        if (rowAction2 != this)
          setAsAction(rowAction2); 
      } else {
        continue;
      } 
      mergeExpiredRefActions();
      return;
    } 
    switch (i) {
      case 2:
      case 4:
        setAsDeleteFinal(paramLong);
        break;
      default:
        setAsNoOp();
        break;
    } 
    mergeExpiredRefActions();
  }
  
  public synchronized boolean canRead(Session paramSession, int paramInt) {
    long l;
    byte b = 0;
    if (this.type == 3)
      return false; 
    if (this.type == 0)
      return true; 
    RowActionBase rowActionBase = this;
    if (paramSession == null) {
      l = Long.MAX_VALUE;
    } else {
      switch (paramSession.isolationLevel) {
        case 1:
          l = Long.MAX_VALUE;
          break;
        case 2:
          l = paramSession.actionTimestamp;
          break;
        default:
          l = paramSession.transactionTimestamp;
          break;
      } 
    } 
    do {
      if (rowActionBase.type == 5) {
        rowActionBase = rowActionBase.next;
      } else if (rowActionBase.rolledback) {
        if (rowActionBase.type == 1)
          b = 2; 
        rowActionBase = rowActionBase.next;
      } else if (paramSession == rowActionBase.session) {
        if (rowActionBase.type == 2) {
          b = rowActionBase.type;
        } else if (rowActionBase.type == 1) {
          b = rowActionBase.type;
        } 
        rowActionBase = rowActionBase.next;
      } else if (rowActionBase.commitTimestamp == 0L) {
        if (rowActionBase.type == 0)
          throw Error.runtimeError(201, "RowAction"); 
        if (rowActionBase.type == 1) {
          if (paramInt == 0) {
            b = 2;
            break;
          } 
          if (paramInt == 1) {
            b = 1;
            paramSession.tempSet.clear();
            paramSession.tempSet.add(rowActionBase);
            break;
          } 
          if (paramInt == 2)
            b = 2; 
          break;
        } 
        if (rowActionBase.type == 2 && paramInt != 1 && paramInt == 2)
          b = 2; 
        rowActionBase = rowActionBase.next;
      } else {
        if (rowActionBase.commitTimestamp < l) {
          if (rowActionBase.type == 2) {
            b = 2;
          } else if (rowActionBase.type == 1) {
            b = 1;
          } 
        } else if (rowActionBase.type == 1) {
          if (paramInt == 0) {
            b = 2;
          } else if (paramInt == 1) {
            b = 1;
            paramSession.tempSet.clear();
            paramSession.tempSet.add(rowActionBase);
          } else if (paramInt == 2) {
            b = 2;
          } 
        } 
        rowActionBase = rowActionBase.next;
      } 
    } while (rowActionBase != null);
    return (b == 0 || b == 1);
  }
  
  public boolean hasCurrentRefAction() {
    RowAction rowAction = this;
    while (true) {
      if (rowAction.type == 5 && rowAction.commitTimestamp == 0L)
        return true; 
      RowActionBase rowActionBase = rowAction.next;
      if (rowActionBase == null)
        return false; 
    } 
  }
  
  private RowAction mergeExpiredRefActions() {
    if (this.updatedAction != null)
      this.updatedAction = this.updatedAction.mergeExpiredRefActions(); 
    return hasCurrentRefAction() ? this : this.updatedAction;
  }
  
  public synchronized String describe(Session paramSession) {
    StringBuilder stringBuilder = new StringBuilder();
    RowAction rowAction = this;
    while (true) {
      if (rowAction == this)
        stringBuilder.append(this.rowId).append(' '); 
      stringBuilder.append(rowAction.session.getId()).append(' ');
      stringBuilder.append(rowAction.type).append(' ').append(rowAction.actionTimestamp);
      stringBuilder.append(' ').append(rowAction.commitTimestamp);
      if (rowAction.commitTimestamp != 0L)
        if (rowAction.rolledback) {
          stringBuilder.append('r');
        } else {
          stringBuilder.append('c');
        }  
      stringBuilder.append(" - ");
      RowActionBase rowActionBase = rowAction.next;
      if (rowActionBase == null)
        return stringBuilder.toString(); 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\RowAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */