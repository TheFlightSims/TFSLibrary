package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.result.Result;
import org.hsqldb.types.Type;

public class StatementCompound extends Statement implements RangeGroup {
  final boolean isLoop;
  
  HsqlNameManager.HsqlName label;
  
  StatementHandler[] handlers = StatementHandler.emptyExceptionHandlerArray;
  
  boolean hasUndoHandler;
  
  StatementQuery loopCursor;
  
  Statement[] statements;
  
  StatementExpression condition;
  
  boolean isAtomic;
  
  ColumnSchema[] variables = ColumnSchema.emptyArray;
  
  StatementCursor[] cursors = StatementCursor.emptyArray;
  
  HashMappedList scopeVariables = new HashMappedList();
  
  RangeVariable[] rangeVariables = RangeVariable.emptyArray;
  
  Table[] tables = Table.emptyArray;
  
  HashMappedList scopeTables;
  
  public static final StatementCompound[] emptyStatementArray = new StatementCompound[0];
  
  StatementCompound(int paramInt, HsqlNameManager.HsqlName paramHsqlName) {
    super(paramInt, 2007);
    this.label = paramHsqlName;
    this.isTransactionStatement = false;
    switch (paramInt) {
      case 46:
      case 90:
      case 95:
      case 97:
        this.isLoop = true;
        return;
      case 12:
      case 88:
        this.isLoop = false;
        return;
    } 
    throw Error.runtimeError(201, "StatementCompound");
  }
  
  public String getSQL() {
    return this.sql;
  }
  
  protected String describe(Session paramSession, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append('\n');
    for (byte b = 0; b < paramInt; b++)
      stringBuffer.append(' '); 
    stringBuffer.append("STATEMENT");
    return stringBuffer.toString();
  }
  
  public void setLocalDeclarations(Object[] paramArrayOfObject) {
    byte b1 = 0;
    byte b2 = 0;
    byte b3 = 0;
    byte b4 = 0;
    byte b5;
    for (b5 = 0; b5 < paramArrayOfObject.length; b5++) {
      if (paramArrayOfObject[b5] instanceof ColumnSchema) {
        b1++;
      } else if (paramArrayOfObject[b5] instanceof StatementHandler) {
        b2++;
      } else if (paramArrayOfObject[b5] instanceof Table) {
        b4++;
      } else {
        b3++;
      } 
    } 
    if (b1 > 0)
      this.variables = new ColumnSchema[b1]; 
    if (b2 > 0)
      this.handlers = new StatementHandler[b2]; 
    if (b4 > 0)
      this.tables = new Table[b4]; 
    if (b3 > 0)
      this.cursors = new StatementCursor[b3]; 
    b1 = 0;
    b2 = 0;
    b4 = 0;
    b3 = 0;
    for (b5 = 0; b5 < paramArrayOfObject.length; b5++) {
      if (paramArrayOfObject[b5] instanceof ColumnSchema) {
        this.variables[b1++] = (ColumnSchema)paramArrayOfObject[b5];
      } else if (paramArrayOfObject[b5] instanceof StatementHandler) {
        StatementHandler statementHandler = (StatementHandler)paramArrayOfObject[b5];
        statementHandler.setParent(this);
        this.handlers[b2++] = statementHandler;
        if (statementHandler.handlerType == 7)
          this.hasUndoHandler = true; 
      } else if (paramArrayOfObject[b5] instanceof Table) {
        Table table = (Table)paramArrayOfObject[b5];
        this.tables[b4++] = table;
      } else {
        StatementCursor statementCursor = (StatementCursor)paramArrayOfObject[b5];
        this.cursors[b3++] = statementCursor;
      } 
    } 
    setVariables();
    setHandlers();
    setTables();
    setCursors();
  }
  
  public void setLoopStatement(StatementQuery paramStatementQuery) {
    this.loopCursor = paramStatementQuery;
    HsqlNameManager.HsqlName[] arrayOfHsqlName = paramStatementQuery.queryExpression.getResultColumnNames();
    Type[] arrayOfType = paramStatementQuery.queryExpression.getColumnTypes();
    ColumnSchema[] arrayOfColumnSchema = new ColumnSchema[arrayOfHsqlName.length];
    for (byte b = 0; b < arrayOfHsqlName.length; b++) {
      arrayOfColumnSchema[b] = new ColumnSchema(arrayOfHsqlName[b], arrayOfType[b], false, false, null);
      arrayOfColumnSchema[b].setParameterMode((byte)1);
    } 
    setLocalDeclarations((Object[])arrayOfColumnSchema);
  }
  
  void setStatements(Statement[] paramArrayOfStatement) {
    for (byte b = 0; b < paramArrayOfStatement.length; b++)
      paramArrayOfStatement[b].setParent(this); 
    this.statements = paramArrayOfStatement;
  }
  
  public void setCondition(StatementExpression paramStatementExpression) {
    this.condition = paramStatementExpression;
  }
  
  public Result execute(Session paramSession) {
    Result result;
    switch (this.type) {
      case 12:
        initialiseVariables(paramSession);
        result = executeBlock(paramSession);
        break;
      case 46:
        result = executeForLoop(paramSession);
        break;
      case 90:
      case 95:
      case 97:
        result = executeLoop(paramSession);
        break;
      case 88:
        result = executeIf(paramSession);
        break;
      default:
        throw Error.runtimeError(201, "StatementCompound");
    } 
    if (result.isError())
      result.getException().setStatementType(this.group, this.type); 
    return result;
  }
  
  private Result executeBlock(Session paramSession) {
    Result result = Result.updateZeroResult;
    boolean bool = !this.root.isTrigger() ? true : false;
    if (bool) {
      paramSession.sessionContext.push();
      if (this.hasUndoHandler) {
        String str = HsqlNameManager.getAutoSavepointNameString(paramSession.actionTimestamp, paramSession.sessionContext.depth);
        paramSession.savepoint(str);
      } 
    } 
    for (byte b = 0; b < this.statements.length; b++) {
      result = executeProtected(paramSession, this.statements[b]);
      result = handleCondition(paramSession, result);
      if (result.isError() || result.getType() == 42 || result.getType() == 3)
        break; 
    } 
    if (result.getType() == 42 && result.getErrorCode() == 89)
      if (result.getMainString() == null) {
        result = Result.updateZeroResult;
      } else if (this.label != null && this.label.name.equals(result.getMainString())) {
        result = Result.updateZeroResult;
      }  
    if (bool)
      paramSession.sessionContext.pop(); 
    return result;
  }
  
  private Result handleCondition(Session paramSession, Result paramResult) {
    String str = null;
    if (paramResult.isError()) {
      str = paramResult.getSubString();
    } else if (paramSession.getLastWarning() != null) {
      str = paramSession.getLastWarning().getSQLState();
    } else {
      return paramResult;
    } 
    if (str != null) {
      for (byte b = 0; b < this.handlers.length; b++) {
        StatementHandler statementHandler = this.handlers[b];
        paramSession.clearWarnings();
        if (statementHandler.handlesCondition(str)) {
          String str1 = (this.label == null) ? null : this.label.name;
          switch (statementHandler.handlerType) {
            case 5:
              paramResult = Result.updateZeroResult;
              break;
            case 7:
              paramSession.rollbackToSavepoint();
              paramResult = Result.newPSMResult(89, str1, null);
              break;
            case 6:
              paramResult = Result.newPSMResult(89, str1, null);
              break;
          } 
          Result result = executeProtected(paramSession, statementHandler.statement);
          if (result.isError()) {
            paramResult = result;
          } else if (result.getType() == 42) {
            paramResult = result;
          } 
        } 
      } 
      if (paramResult.isError() && this.parent != null)
        return this.parent.handleCondition(paramSession, paramResult); 
    } 
    return paramResult;
  }
  
  private Result executeForLoop(Session paramSession) {
    Result result1 = this.loopCursor.execute(paramSession);
    if (result1.isError())
      return result1; 
    Result result2 = Result.updateZeroResult;
    while (result1.navigator.hasNext()) {
      result1.navigator.next();
      Object[] arrayOfObject = result1.navigator.getCurrent();
      initialiseVariables(paramSession, arrayOfObject, result1.metaData.getColumnCount());
      for (byte b = 0; b < this.statements.length; b++) {
        result2 = executeProtected(paramSession, this.statements[b]);
        result2 = handleCondition(paramSession, result2);
        if (result2.isError() || result2.getType() == 42 || result2.getType() == 3)
          break; 
      } 
      if (result2.isError())
        break; 
      if (result2.getType() == 42) {
        if (result2.getErrorCode() == 102) {
          if (result2.getMainString() == null || (this.label != null && this.label.name.equals(result2.getMainString())))
            continue; 
          break;
        } 
        if (result2.getErrorCode() == 89);
        break;
      } 
      if (result2.getType() == 3)
        break; 
    } 
    result1.navigator.release();
    return result2;
  }
  
  private Result executeLoop(Session paramSession) {
    Result result = Result.updateZeroResult;
    while (true) {
      if (this.type == 97) {
        result = this.condition.execute(paramSession);
        if (result.isError())
          break; 
        if (!Boolean.TRUE.equals(result.getValueObject())) {
          result = Result.updateZeroResult;
          break;
        } 
      } 
      for (byte b = 0; b < this.statements.length; b++) {
        result = executeProtected(paramSession, this.statements[b]);
        result = handleCondition(paramSession, result);
        if (result.getType() == 42 || result.getType() == 3)
          break; 
      } 
      if (result.isError())
        break; 
      if (result.getType() == 42) {
        if (result.getErrorCode() == 102) {
          if (result.getMainString() == null || (this.label != null && this.label.name.equals(result.getMainString())))
            continue; 
          break;
        } 
        if (result.getErrorCode() == 89) {
          if (result.getMainString() == null)
            result = Result.updateZeroResult; 
          if (this.label != null && this.label.name.equals(result.getMainString()))
            result = Result.updateZeroResult; 
        } 
        break;
      } 
      if (result.getType() == 3)
        break; 
      if (this.type == 95) {
        result = this.condition.execute(paramSession);
        if (result.isError())
          break; 
        if (Boolean.TRUE.equals(result.getValueObject())) {
          result = Result.updateZeroResult;
          break;
        } 
      } 
    } 
    return result;
  }
  
  private Result executeIf(Session paramSession) {
    Result result = Result.updateZeroResult;
    boolean bool = false;
    for (byte b = 0; b < this.statements.length; b++) {
      if (this.statements[b].getType() == 1211) {
        if (bool)
          break; 
        result = executeProtected(paramSession, this.statements[b]);
        if (result.isError())
          break; 
        Object object = result.getValueObject();
        bool = Boolean.TRUE.equals(object);
        b++;
      } 
      result = Result.updateZeroResult;
      if (bool) {
        result = executeProtected(paramSession, this.statements[b]);
        result = handleCondition(paramSession, result);
        if (result.isError() || result.getType() == 42)
          break; 
      } 
    } 
    return result;
  }
  
  private Result executeProtected(Session paramSession, Statement paramStatement) {
    int i = paramSession.rowActionList.size();
    paramSession.actionTimestamp = paramSession.database.txManager.getNextGlobalChangeTimestamp();
    Result result = paramStatement.execute(paramSession);
    if (result.isError())
      paramSession.rollbackAction(i, paramSession.actionTimestamp); 
    return result;
  }
  
  public void resolve(Session paramSession) {
    byte b1;
    for (b1 = 0; b1 < this.statements.length; b1++) {
      if (this.statements[b1].getType() == 89 || this.statements[b1].getType() == 102) {
        if (!findLabel((StatementSimple)this.statements[b1]))
          throw Error.error(5508, ((StatementSimple)this.statements[b1]).label.name); 
      } else if (this.statements[b1].getType() == 58 && !this.root.isFunction()) {
        throw Error.error(5602, "RETURN");
      } 
    } 
    for (b1 = 0; b1 < this.statements.length; b1++)
      this.statements[b1].resolve(paramSession); 
    for (b1 = 0; b1 < this.handlers.length; b1++)
      this.handlers[b1].resolve(paramSession); 
    OrderedHashSet orderedHashSet1 = new OrderedHashSet();
    OrderedHashSet orderedHashSet2 = new OrderedHashSet();
    OrderedHashSet orderedHashSet3 = new OrderedHashSet();
    byte b2;
    for (b2 = 0; b2 < this.variables.length; b2++) {
      OrderedHashSet orderedHashSet = this.variables[b2].getReferences();
      if (orderedHashSet != null)
        orderedHashSet3.addAll((Collection)orderedHashSet); 
    } 
    if (this.condition != null) {
      orderedHashSet3.addAll((Collection)this.condition.getReferences());
      orderedHashSet2.addAll((Object[])this.condition.getTableNamesForRead());
    } 
    for (b2 = 0; b2 < this.statements.length; b2++) {
      orderedHashSet3.addAll((Collection)this.statements[b2].getReferences());
      orderedHashSet2.addAll((Object[])this.statements[b2].getTableNamesForRead());
      orderedHashSet1.addAll((Object[])this.statements[b2].getTableNamesForWrite());
    } 
    for (b2 = 0; b2 < this.handlers.length; b2++) {
      orderedHashSet3.addAll((Collection)this.handlers[b2].getReferences());
      orderedHashSet2.addAll((Object[])this.handlers[b2].getTableNamesForRead());
      orderedHashSet1.addAll((Object[])this.handlers[b2].getTableNamesForWrite());
    } 
    orderedHashSet2.removeAll((Collection)orderedHashSet1);
    this.readTableNames = new HsqlNameManager.HsqlName[orderedHashSet2.size()];
    orderedHashSet2.toArray((Object[])this.readTableNames);
    this.writeTableNames = new HsqlNameManager.HsqlName[orderedHashSet1.size()];
    orderedHashSet1.toArray((Object[])this.writeTableNames);
    this.references = orderedHashSet3;
  }
  
  public void setRoot(Routine paramRoutine) {
    this.root = paramRoutine;
  }
  
  public String describe(Session paramSession) {
    return "";
  }
  
  public OrderedHashSet getReferences() {
    return this.references;
  }
  
  public void setAtomic(boolean paramBoolean) {
    this.isAtomic = paramBoolean;
  }
  
  private void setVariables() {
    HashMappedList hashMappedList = new HashMappedList();
    if (this.variables.length == 0) {
      if (this.parent == null) {
        this.rangeVariables = this.root.getRangeVariables();
      } else {
        this.rangeVariables = this.parent.rangeVariables;
      } 
      this.scopeVariables = hashMappedList;
      return;
    } 
    if (this.parent != null && this.parent.scopeVariables != null)
      for (byte b = 0; b < this.parent.scopeVariables.size(); b++)
        hashMappedList.add(this.parent.scopeVariables.getKey(b), this.parent.scopeVariables.get(b));  
    for (byte b1 = 0; b1 < this.variables.length; b1++) {
      String str = (this.variables[b1].getName()).name;
      boolean bool = hashMappedList.add(str, this.variables[b1]);
      if (!bool)
        throw Error.error(5606, str); 
      if (this.root.getParameterIndex(str) != -1)
        throw Error.error(5606, str); 
    } 
    this.scopeVariables = hashMappedList;
    RangeVariable[] arrayOfRangeVariable = this.root.getRangeVariables();
    RangeVariable rangeVariable = new RangeVariable(hashMappedList, null, true, 4);
    this.rangeVariables = new RangeVariable[arrayOfRangeVariable.length + 1];
    for (byte b2 = 0; b2 < arrayOfRangeVariable.length; b2++)
      this.rangeVariables[b2] = arrayOfRangeVariable[b2]; 
    this.rangeVariables[arrayOfRangeVariable.length] = rangeVariable;
    this.root.variableCount = hashMappedList.size();
  }
  
  private void setHandlers() {
    if (this.handlers.length == 0)
      return; 
    HashSet hashSet = new HashSet();
    OrderedIntHashSet orderedIntHashSet = new OrderedIntHashSet();
    for (byte b = 0; b < this.handlers.length; b++) {
      int[] arrayOfInt = this.handlers[b].getConditionTypes();
      for (byte b1 = 0; b1 < arrayOfInt.length; b1++) {
        if (!orderedIntHashSet.add(arrayOfInt[b1]))
          throw Error.error(5601); 
      } 
      String[] arrayOfString = this.handlers[b].getConditionStates();
      for (byte b2 = 0; b2 < arrayOfString.length; b2++) {
        if (!hashSet.add(arrayOfString[b2]))
          throw Error.error(5601); 
      } 
    } 
  }
  
  private void setTables() {
    if (this.tables.length == 0)
      return; 
    HashMappedList hashMappedList = new HashMappedList();
    if (this.parent != null && this.parent.scopeTables != null)
      for (byte b1 = 0; b1 < this.parent.scopeTables.size(); b1++)
        hashMappedList.add(this.parent.scopeTables.getKey(b1), this.parent.scopeTables.get(b1));  
    for (byte b = 0; b < this.tables.length; b++) {
      String str = (this.tables[b].getName()).name;
      boolean bool = hashMappedList.add(str, this.tables[b]);
      if (!bool)
        throw Error.error(5606, str); 
    } 
    this.scopeTables = hashMappedList;
  }
  
  private void setCursors() {
    if (this.cursors.length == 0)
      return; 
    HashSet hashSet = new HashSet();
    for (byte b = 0; b < this.cursors.length; b++) {
      StatementCursor statementCursor = this.cursors[b];
      boolean bool = hashSet.add((statementCursor.getCursorName()).name);
      if (!bool)
        throw Error.error(5606, (statementCursor.getCursorName()).name); 
    } 
  }
  
  private boolean findLabel(StatementSimple paramStatementSimple) {
    return (this.label != null && paramStatementSimple.label.name.equals(this.label.name)) ? (!(!this.isLoop && paramStatementSimple.getType() == 102)) : ((this.parent == null) ? false : this.parent.findLabel(paramStatementSimple));
  }
  
  private void initialiseVariables(Session paramSession) {
    Object[] arrayOfObject = paramSession.sessionContext.routineVariables;
    byte b1 = (this.parent == null) ? 0 : this.parent.scopeVariables.size();
    for (byte b2 = 0; b2 < this.variables.length; b2++) {
      try {
        arrayOfObject[b1 + b2] = this.variables[b2].getDefaultValue(paramSession);
      } catch (HsqlException hsqlException) {}
    } 
  }
  
  private void initialiseVariables(Session paramSession, Object[] paramArrayOfObject, int paramInt) {
    Object[] arrayOfObject = paramSession.sessionContext.routineVariables;
    byte b1 = (this.parent == null) ? 0 : this.parent.scopeVariables.size();
    for (byte b2 = 0; b2 < paramInt; b2++) {
      try {
        arrayOfObject[b1 + b2] = paramArrayOfObject[b2];
      } catch (HsqlException hsqlException) {}
    } 
  }
  
  public RangeVariable[] getRangeVariables() {
    return this.rangeVariables;
  }
  
  public void setCorrelated() {}
  
  public boolean isVariable() {
    return true;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementCompound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */