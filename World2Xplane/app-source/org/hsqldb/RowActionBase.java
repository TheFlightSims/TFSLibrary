package org.hsqldb;

public class RowActionBase {
  public static final byte ACTION_NONE = 0;
  
  public static final byte ACTION_INSERT = 1;
  
  public static final byte ACTION_DELETE = 2;
  
  public static final byte ACTION_DELETE_FINAL = 3;
  
  public static final byte ACTION_INSERT_DELETE = 4;
  
  public static final byte ACTION_REF = 5;
  
  public static final byte ACTION_CHECK = 6;
  
  public static final byte ACTION_DEBUG = 7;
  
  RowActionBase next;
  
  Session session;
  
  long actionTimestamp;
  
  long commitTimestamp;
  
  byte type;
  
  byte commitRollbackType;
  
  boolean deleteComplete;
  
  boolean rolledback;
  
  boolean prepared;
  
  int[] changeColumnMap;
  
  RowActionBase() {}
  
  RowActionBase(Session paramSession, byte paramByte) {
    this.session = paramSession;
    this.type = paramByte;
    this.actionTimestamp = paramSession.actionTimestamp;
  }
  
  void setAsAction(RowActionBase paramRowActionBase) {
    this.next = paramRowActionBase.next;
    this.session = paramRowActionBase.session;
    this.actionTimestamp = paramRowActionBase.actionTimestamp;
    this.commitTimestamp = paramRowActionBase.commitTimestamp;
    this.type = paramRowActionBase.type;
    this.deleteComplete = paramRowActionBase.deleteComplete;
    this.rolledback = paramRowActionBase.rolledback;
    this.prepared = paramRowActionBase.prepared;
    this.changeColumnMap = paramRowActionBase.changeColumnMap;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\RowActionBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */