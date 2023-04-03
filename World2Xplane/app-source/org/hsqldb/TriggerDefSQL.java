package org.hsqldb;

import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.result.Result;

public class TriggerDefSQL extends TriggerDef {
  OrderedHashSet references;
  
  public TriggerDefSQL(HsqlNameManager.HsqlName paramHsqlName, int paramInt1, int paramInt2, boolean paramBoolean, Table paramTable, Table[] paramArrayOfTable, RangeVariable[] paramArrayOfRangeVariable, Expression paramExpression, String paramString, int[] paramArrayOfint, Routine paramRoutine) {
    super(paramHsqlName, paramInt1, paramInt2, paramBoolean, paramTable, paramArrayOfTable, paramArrayOfRangeVariable, paramExpression, paramString, paramArrayOfint);
    this.routine = paramRoutine;
    this.references = paramRoutine.getReferences();
  }
  
  public OrderedHashSet getReferences() {
    return this.routine.getReferences();
  }
  
  public OrderedHashSet getComponents() {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getClassName() {
    return null;
  }
  
  public boolean hasOldTable() {
    return (this.transitions[2] != null);
  }
  
  public boolean hasNewTable() {
    return (this.transitions[3] != null);
  }
  
  synchronized void pushPair(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {
    Result result = Result.updateZeroResult;
    paramSession.sessionContext.push();
    if (this.rangeVars[0] != null || this.rangeVars[1] != null)
      paramSession.sessionContext.triggerArguments = new Object[][] { paramArrayOfObject1, paramArrayOfObject2 }; 
    if (this.condition.testCondition(paramSession)) {
      int i = this.routine.getVariableCount();
      paramSession.sessionContext.routineVariables = new Object[i];
      result = this.routine.statement.execute(paramSession);
    } 
    paramSession.sessionContext.pop();
    if (result.isError())
      throw result.getException(); 
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = getSQLMain();
    stringBuffer.append(this.routine.statement.getSQL());
    return stringBuffer.toString();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\TriggerDefSQL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */