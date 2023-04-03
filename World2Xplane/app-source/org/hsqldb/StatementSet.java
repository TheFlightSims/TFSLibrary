package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.map.ValuePool;
import org.hsqldb.result.Result;
import org.hsqldb.types.Type;

public class StatementSet extends StatementDMQL {
  Expression expression;
  
  Expression[] targets;
  
  int[] variableIndexes;
  
  Type[] sourceTypes;
  
  final int operationType = 1;
  
  public static final int TRIGGER_SET = 1;
  
  public static final int SELECT_INTO = 2;
  
  public static final int VARIABLE_SET = 3;
  
  StatementSet(Session paramSession, Expression[] paramArrayOfExpression1, Table paramTable, RangeVariable[] paramArrayOfRangeVariable, int[] paramArrayOfint, Expression[] paramArrayOfExpression2, ParserDQL.CompileContext paramCompileContext) {
    super(5, 2004, paramSession.getCurrentSchemaHsqlName());
    this.targets = paramArrayOfExpression1;
    this.targetTable = paramTable;
    this.baseTable = this.targetTable.getBaseTable();
    this.updateColumnMap = paramArrayOfint;
    this.updateExpressions = paramArrayOfExpression2;
    this.updateCheckColumns = this.targetTable.getColumnCheckList(paramArrayOfint);
    this.targetRangeVariables = paramArrayOfRangeVariable;
    this.isTransactionStatement = false;
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
  }
  
  StatementSet(Session paramSession, Expression[] paramArrayOfExpression, Expression paramExpression, int[] paramArrayOfint, ParserDQL.CompileContext paramCompileContext) {
    super(5, 2007, (HsqlNameManager.HsqlName)null);
    this.targets = paramArrayOfExpression;
    this.expression = paramExpression;
    this.variableIndexes = paramArrayOfint;
    this.sourceTypes = this.expression.getNodeDataTypes();
    this.isTransactionStatement = false;
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
  }
  
  StatementSet(Session paramSession, Expression[] paramArrayOfExpression, QueryExpression paramQueryExpression, int[] paramArrayOfint, ParserDQL.CompileContext paramCompileContext) {
    super(5, 2007, (HsqlNameManager.HsqlName)null);
    this.queryExpression = paramQueryExpression;
    this.targets = paramArrayOfExpression;
    this.variableIndexes = paramArrayOfint;
    this.sourceTypes = paramQueryExpression.getColumnTypes();
    this.isTransactionStatement = false;
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
  }
  
  TableDerived[] getSubqueries(Session paramSession) {
    OrderedHashSet orderedHashSet = null;
    if (this.expression != null)
      orderedHashSet = this.expression.collectAllSubqueries(orderedHashSet); 
    if (orderedHashSet == null || orderedHashSet.size() == 0)
      return TableDerived.emptyArray; 
    TableDerived[] arrayOfTableDerived = new TableDerived[orderedHashSet.size()];
    orderedHashSet.toArray((Object[])arrayOfTableDerived);
    for (byte b = 0; b < this.subqueries.length; b++)
      arrayOfTableDerived[b].prepareTable(); 
    return arrayOfTableDerived;
  }
  
  Result getResult(Session paramSession) {
    Result result;
    Object[] arrayOfObject;
    null = null;
    switch (this.operationType) {
      case 1:
        return executeTriggerSetStatement(paramSession);
      case 2:
        arrayOfObject = this.queryExpression.getSingleRowValues(paramSession);
        if (arrayOfObject == null) {
          paramSession.addWarning(HsqlException.noDataCondition);
          result = Result.updateZeroResult;
        } else {
          for (byte b = 0; b < arrayOfObject.length; b++)
            arrayOfObject[b] = this.targets[b].getColumn().getDataType().convertToType(paramSession, arrayOfObject[b], this.sourceTypes[b]); 
          result = executeAssignment(paramSession, arrayOfObject);
        } 
        return result;
      case 3:
        arrayOfObject = getExpressionValues(paramSession);
        if (arrayOfObject == null) {
          result = Result.updateZeroResult;
        } else {
          for (byte b = 0; b < arrayOfObject.length; b++) {
            Type type;
            if (this.targets[b].getType() == 99) {
              type = this.targets[b].getLeftNode().getColumn().getDataType().collectionBaseType();
            } else {
              type = this.targets[b].getColumn().getDataType();
            } 
            arrayOfObject[b] = type.convertToType(paramSession, arrayOfObject[b], this.sourceTypes[b]);
          } 
          result = executeAssignment(paramSession, arrayOfObject);
        } 
        return result;
    } 
    throw Error.runtimeError(201, "StatementSet");
  }
  
  public void resolve(Session paramSession) {
    byte b;
    this.references = new OrderedHashSet();
    switch (this.operationType) {
      case 1:
        for (b = 0; b < this.updateExpressions.length; b++)
          this.updateExpressions[b].collectObjectNames((Set)this.references); 
        return;
      case 2:
      case 3:
        if (this.expression != null)
          this.expression.collectObjectNames((Set)this.references); 
        if (this.queryExpression != null)
          this.queryExpression.collectObjectNames((Set)this.references); 
        return;
    } 
    throw Error.runtimeError(201, "StatementSet");
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    switch (this.operationType) {
      case 1:
        return this.sql;
      case 3:
        stringBuffer.append("SET").append(' ');
        stringBuffer.append((this.targets[0].getColumn().getName()).statementName);
        stringBuffer.append(' ').append('=').append(' ').append(this.expression.getSQL());
        break;
    } 
    return stringBuffer.toString();
  }
  
  protected String describe(Session paramSession, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append('\n');
    for (byte b = 0; b < paramInt; b++)
      stringBuffer.append(' '); 
    stringBuffer.append("STATEMENT");
    return stringBuffer.toString();
  }
  
  public Result execute(Session paramSession) {
    Result result;
    try {
      if (this.subqueries.length > 0)
        materializeSubQueries(paramSession); 
      result = getResult(paramSession);
    } catch (Throwable throwable) {
      result = Result.newErrorResult(throwable, null);
    } 
    if (result.isError())
      result.getException().setStatementType(this.group, this.type); 
    return result;
  }
  
  public String describe(Session paramSession) {
    return "";
  }
  
  Result executeTriggerSetStatement(Session paramSession) {
    Table table = this.targetTable;
    int[] arrayOfInt = this.updateColumnMap;
    Expression[] arrayOfExpression = this.updateExpressions;
    Type[] arrayOfType = table.getColumnTypes();
    int i = (this.targetRangeVariables[1]).rangePosition;
    Object[] arrayOfObject1 = paramSession.sessionContext.triggerArguments[i];
    Object[] arrayOfObject2 = StatementDML.getUpdatedData(paramSession, this.targets, table, arrayOfInt, arrayOfExpression, arrayOfType, arrayOfObject1);
    ArrayUtil.copyArray(arrayOfObject2, arrayOfObject1, arrayOfObject2.length);
    return Result.updateOneResult;
  }
  
  void collectTableNamesForRead(OrderedHashSet paramOrderedHashSet) {
    byte b;
    for (b = 0; b < this.rangeVariables.length; b++) {
      Table table = (this.rangeVariables[b]).rangeTable;
      HsqlNameManager.HsqlName hsqlName = table.getName();
      if (!table.isDataReadOnly() && !table.isTemp() && hsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME)
        paramOrderedHashSet.add(hsqlName); 
    } 
    for (b = 0; b < this.subqueries.length; b++) {
      if ((this.subqueries[b]).queryExpression != null)
        (this.subqueries[b]).queryExpression.getBaseTableNames(paramOrderedHashSet); 
    } 
    for (b = 0; b < this.routines.length; b++)
      paramOrderedHashSet.addAll((Object[])this.routines[b].getTableNamesForRead()); 
  }
  
  void collectTableNamesForWrite(OrderedHashSet paramOrderedHashSet) {}
  
  public void checkIsNotColumnTarget() {
    for (byte b = 0; b < this.targets.length; b++) {
      ColumnSchema columnSchema = this.targets[b].getColumn();
      if (columnSchema.getType() == 9)
        throw Error.error(2500, (columnSchema.getName()).statementName); 
    } 
  }
  
  Object[] getExpressionValues(Session paramSession) {
    Object[] arrayOfObject;
    if (this.expression.getType() == 25) {
      arrayOfObject = this.expression.getRowValue(paramSession);
    } else if (this.expression.getType() == 22) {
      arrayOfObject = this.expression.table.queryExpression.getSingleRowValues(paramSession);
      if (arrayOfObject == null)
        return null; 
    } else {
      arrayOfObject = new Object[1];
      arrayOfObject[0] = this.expression.getValue(paramSession);
    } 
    return arrayOfObject;
  }
  
  Result executeAssignment(Session paramSession, Object[] paramArrayOfObject) {
    for (byte b = 0; b < paramArrayOfObject.length; b++) {
      Object[] arrayOfObject = ValuePool.emptyObjectArray;
      switch (this.targets[b].getColumn().getType()) {
        case 23:
          arrayOfObject = paramSession.sessionContext.routineArguments;
          break;
        case 22:
          arrayOfObject = paramSession.sessionContext.routineVariables;
          break;
        case 9:
          arrayOfObject = paramSession.sessionContext.triggerArguments[1];
          break;
      } 
      int i = this.variableIndexes[b];
      if (this.targets[b].getType() == 99) {
        arrayOfObject[i] = ((ExpressionAccessor)this.targets[b]).getUpdatedArray(paramSession, (Object[])arrayOfObject[i], paramArrayOfObject[b], true);
      } else {
        arrayOfObject[i] = paramArrayOfObject[b];
      } 
    } 
    return Result.updateZeroResult;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */