package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlDeque;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.rights.Grantee;

public class TriggerDef implements Runnable, SchemaObject {
  static final int OLD_ROW = 0;
  
  static final int NEW_ROW = 1;
  
  static final int RANGE_COUNT = 2;
  
  static final int OLD_TABLE = 2;
  
  static final int NEW_TABLE = 3;
  
  static final int BEFORE = 4;
  
  static final int AFTER = 5;
  
  static final int INSTEAD = 6;
  
  static final int NUM_TRIGGER_OPS = 3;
  
  static final int NUM_TRIGS = 9;
  
  static final TriggerDef[] emptyArray = new TriggerDef[0];
  
  Table[] transitions;
  
  RangeVariable[] rangeVars;
  
  Expression condition;
  
  boolean hasTransitionTables;
  
  boolean hasTransitionRanges;
  
  String conditionSQL;
  
  Routine routine;
  
  int[] updateColumns;
  
  private HsqlNameManager.HsqlName name;
  
  long changeTimestamp;
  
  int actionTiming;
  
  int operationType;
  
  boolean isSystem;
  
  boolean forEachRow;
  
  boolean nowait;
  
  int maxRowsQueued;
  
  Table table;
  
  Trigger trigger;
  
  String triggerClassName;
  
  int triggerType;
  
  Thread thread;
  
  protected HsqlDeque pendingQueue;
  
  protected int rowsQueued;
  
  protected boolean valid = true;
  
  protected volatile boolean keepGoing = true;
  
  TriggerDef() {}
  
  public TriggerDef(HsqlNameManager.HsqlName paramHsqlName, int paramInt1, int paramInt2, boolean paramBoolean1, Table paramTable, Table[] paramArrayOfTable, RangeVariable[] paramArrayOfRangeVariable, Expression paramExpression, String paramString1, int[] paramArrayOfint, String paramString2, boolean paramBoolean2, int paramInt3) {
    this(paramHsqlName, paramInt1, paramInt2, paramBoolean1, paramTable, paramArrayOfTable, paramArrayOfRangeVariable, paramExpression, paramString1, paramArrayOfint);
    this.triggerClassName = paramString2;
    this.nowait = paramBoolean2;
    this.maxRowsQueued = paramInt3;
    this.rowsQueued = 0;
    this.pendingQueue = new HsqlDeque();
    Class<?> clazz = null;
    try {
      clazz = Class.forName(paramString2, true, Thread.currentThread().getContextClassLoader());
    } catch (Throwable throwable) {
      try {
        clazz = Class.forName(paramString2);
      } catch (Throwable throwable1) {}
    } 
    if (clazz == null) {
      this.valid = false;
      this.trigger = new DefaultTrigger();
    } else {
      try {
        this.trigger = (Trigger)clazz.newInstance();
      } catch (Throwable throwable) {
        this.valid = false;
        this.trigger = new DefaultTrigger();
      } 
    } 
  }
  
  public TriggerDef(HsqlNameManager.HsqlName paramHsqlName, int paramInt1, int paramInt2, boolean paramBoolean, Table paramTable, Table[] paramArrayOfTable, RangeVariable[] paramArrayOfRangeVariable, Expression paramExpression, String paramString, int[] paramArrayOfint) {
    this.name = paramHsqlName;
    this.actionTiming = paramInt1;
    this.operationType = paramInt2;
    this.forEachRow = paramBoolean;
    this.table = paramTable;
    this.transitions = paramArrayOfTable;
    this.rangeVars = paramArrayOfRangeVariable;
    this.condition = (paramExpression == null) ? Expression.EXPR_TRUE : paramExpression;
    this.updateColumns = paramArrayOfint;
    this.conditionSQL = paramString;
    this.hasTransitionRanges = (paramArrayOfRangeVariable[0] != null || paramArrayOfRangeVariable[1] != null);
    this.hasTransitionTables = (paramArrayOfTable[2] != null || paramArrayOfTable[3] != null);
    setUpIndexesAndTypes();
  }
  
  public boolean isValid() {
    return this.valid;
  }
  
  public int getType() {
    return 8;
  }
  
  public HsqlNameManager.HsqlName getName() {
    return this.name;
  }
  
  public HsqlNameManager.HsqlName getCatalogName() {
    return this.name.schema.schema;
  }
  
  public HsqlNameManager.HsqlName getSchemaName() {
    return this.name.schema;
  }
  
  public Grantee getOwner() {
    return this.name.schema.owner;
  }
  
  public OrderedHashSet getReferences() {
    return new OrderedHashSet();
  }
  
  public OrderedHashSet getComponents() {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getSQL() {
    StringBuffer stringBuffer = getSQLMain();
    if (this.maxRowsQueued != 0) {
      stringBuffer.append("QUEUE").append(' ');
      stringBuffer.append(this.maxRowsQueued).append(' ');
      if (this.nowait)
        stringBuffer.append("NOWAIT").append(' '); 
    } 
    stringBuffer.append("CALL").append(' ');
    stringBuffer.append(StringConverter.toQuotedString(this.triggerClassName, '"', false));
    return stringBuffer.toString();
  }
  
  public long getChangeTimestamp() {
    return this.changeTimestamp;
  }
  
  public StringBuffer getSQLMain() {
    StringBuffer stringBuffer = new StringBuffer(256);
    stringBuffer.append("CREATE").append(' ');
    stringBuffer.append("TRIGGER").append(' ');
    stringBuffer.append(this.name.getSchemaQualifiedStatementName()).append(' ');
    stringBuffer.append(getActionTimingString()).append(' ');
    stringBuffer.append(getEventTypeString()).append(' ');
    if (this.updateColumns != null) {
      stringBuffer.append("OF").append(' ');
      for (byte b = 0; b < this.updateColumns.length; b++) {
        if (b != 0)
          stringBuffer.append(','); 
        HsqlNameManager.HsqlName hsqlName = this.table.getColumn(this.updateColumns[b]).getName();
        stringBuffer.append(hsqlName.statementName);
      } 
      stringBuffer.append(' ');
    } 
    stringBuffer.append("ON").append(' ');
    stringBuffer.append(this.table.getName().getSchemaQualifiedStatementName());
    stringBuffer.append(' ');
    if (this.hasTransitionRanges || this.hasTransitionTables) {
      stringBuffer.append("REFERENCING").append(' ');
      if (this.rangeVars[0] != null) {
        stringBuffer.append("OLD").append(' ').append("ROW");
        stringBuffer.append(' ').append("AS").append(' ');
        stringBuffer.append(this.rangeVars[0].getTableAlias().getStatementName());
        stringBuffer.append(' ');
      } 
      if (this.rangeVars[1] != null) {
        stringBuffer.append("NEW").append(' ').append("ROW");
        stringBuffer.append(' ').append("AS").append(' ');
        stringBuffer.append(this.rangeVars[1].getTableAlias().getStatementName());
        stringBuffer.append(' ');
      } 
      if (this.transitions[2] != null) {
        stringBuffer.append("OLD").append(' ').append("TABLE");
        stringBuffer.append(' ').append("AS").append(' ');
        stringBuffer.append((this.transitions[2].getName()).statementName);
        stringBuffer.append(' ');
      } 
      if (this.transitions[3] != null) {
        stringBuffer.append("OLD").append(' ').append("TABLE");
        stringBuffer.append(' ').append("AS").append(' ');
        stringBuffer.append((this.transitions[3].getName()).statementName);
        stringBuffer.append(' ');
      } 
    } 
    if (this.forEachRow) {
      stringBuffer.append("FOR").append(' ');
      stringBuffer.append("EACH").append(' ');
      stringBuffer.append("ROW").append(' ');
    } 
    if (this.condition != Expression.EXPR_TRUE) {
      stringBuffer.append("WHEN").append(' ');
      stringBuffer.append("(").append(this.conditionSQL);
      stringBuffer.append(")").append(' ');
    } 
    return stringBuffer;
  }
  
  public String getClassName() {
    return this.trigger.getClass().getName();
  }
  
  public String getActionTimingString() {
    switch (this.actionTiming) {
      case 4:
        return "BEFORE";
      case 5:
        return "AFTER";
      case 6:
        return "INSTEAD OF";
    } 
    throw Error.runtimeError(201, "TriggerDef");
  }
  
  public String getEventTypeString() {
    switch (this.operationType) {
      case 50:
        return "INSERT";
      case 19:
        return "DELETE";
      case 82:
        return "UPDATE";
    } 
    throw Error.runtimeError(201, "TriggerDef");
  }
  
  public boolean isSystem() {
    return this.isSystem;
  }
  
  public boolean isForEachRow() {
    return this.forEachRow;
  }
  
  public String getConditionSQL() {
    return this.conditionSQL;
  }
  
  public String getProcedureSQL() {
    return (this.routine == null) ? null : this.routine.getSQLBodyDefinition();
  }
  
  public int[] getUpdateColumnIndexes() {
    return this.updateColumns;
  }
  
  public boolean hasOldTable() {
    return false;
  }
  
  public boolean hasNewTable() {
    return false;
  }
  
  public String getOldTransitionRowName() {
    return (this.rangeVars[0] == null) ? null : (this.rangeVars[0].getTableAlias()).name;
  }
  
  public String getNewTransitionRowName() {
    return (this.rangeVars[1] == null) ? null : (this.rangeVars[1].getTableAlias()).name;
  }
  
  public String getOldTransitionTableName() {
    return (this.transitions[2] == null) ? null : (this.transitions[2].getName()).name;
  }
  
  public String getNewTransitionTableName() {
    return (this.transitions[3] == null) ? null : (this.transitions[3].getName()).name;
  }
  
  void setUpIndexesAndTypes() {
    this.triggerType = 0;
    switch (this.operationType) {
      case 50:
        this.triggerType = 0;
        break;
      case 19:
        this.triggerType = 1;
        break;
      case 82:
        this.triggerType = 2;
        break;
      default:
        throw Error.runtimeError(201, "TriggerDef");
    } 
    if (this.forEachRow)
      this.triggerType += 3; 
    if (this.actionTiming == 4 || this.actionTiming == 6)
      this.triggerType += 3; 
  }
  
  static int getOperationType(int paramInt) {
    switch (paramInt) {
      case 135:
        return 50;
      case 79:
        return 19;
      case 303:
        return 82;
    } 
    throw Error.runtimeError(201, "TriggerDef");
  }
  
  static int getTiming(int paramInt) {
    switch (paramInt) {
      case 343:
        return 4;
      case 336:
        return 5;
      case 422:
        return 6;
    } 
    throw Error.runtimeError(201, "TriggerDef");
  }
  
  public int getStatementType() {
    return this.operationType;
  }
  
  public void run() {
    while (this.keepGoing) {
      TriggerData triggerData = popPair();
      if (triggerData != null && triggerData.username != null)
        this.trigger.fire(this.triggerType, this.name.name, (this.table.getName()).name, triggerData.oldRow, triggerData.newRow); 
    } 
    try {
      this.thread.setContextClassLoader(null);
    } catch (Throwable throwable) {}
  }
  
  public synchronized void start() {
    if (this.maxRowsQueued != 0) {
      this.thread = new Thread(this);
      this.thread.start();
    } 
  }
  
  public synchronized void terminate() {
    this.keepGoing = false;
    notify();
  }
  
  synchronized TriggerData popPair() {
    if (this.rowsQueued == 0)
      try {
        wait();
      } catch (InterruptedException interruptedException) {} 
    this.rowsQueued--;
    notify();
    return (this.pendingQueue.size() == 0) ? null : (TriggerData)this.pendingQueue.removeFirst();
  }
  
  synchronized void pushPair(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {
    if (this.maxRowsQueued == 0) {
      paramSession.getInternalConnection();
      try {
        this.trigger.fire(this.triggerType, this.name.name, (this.table.getName()).name, paramArrayOfObject1, paramArrayOfObject2);
      } finally {
        paramSession.releaseInternalConnection();
      } 
      return;
    } 
    if (this.rowsQueued >= this.maxRowsQueued) {
      if (this.nowait) {
        this.pendingQueue.removeLast();
      } else {
        try {
          wait();
        } catch (InterruptedException interruptedException) {}
        this.rowsQueued++;
      } 
    } else {
      this.rowsQueued++;
    } 
    this.pendingQueue.add(new TriggerData(paramSession, paramArrayOfObject1, paramArrayOfObject2));
    notify();
  }
  
  public boolean isBusy() {
    return (this.rowsQueued != 0);
  }
  
  public Table getTable() {
    return this.table;
  }
  
  public String getActionOrientationString() {
    return this.forEachRow ? "ROW" : "STATEMENT";
  }
  
  static class DefaultTrigger implements Trigger {
    public void fire(int param1Int, String param1String1, String param1String2, Object[] param1ArrayOfObject1, Object[] param1ArrayOfObject2) {}
  }
  
  static class TriggerData {
    public Object[] oldRow;
    
    public Object[] newRow;
    
    public String username;
    
    public TriggerData(Session param1Session, Object[] param1ArrayOfObject1, Object[] param1ArrayOfObject2) {
      this.oldRow = param1ArrayOfObject1;
      this.newRow = param1ArrayOfObject2;
      this.username = param1Session.getUsername();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\TriggerDef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */