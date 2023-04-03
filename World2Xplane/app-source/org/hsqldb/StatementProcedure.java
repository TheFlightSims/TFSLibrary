package org.hsqldb;

import java.sql.Connection;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.map.ValuePool;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.Type;

public class StatementProcedure extends StatementDMQL {
  Expression expression;
  
  Routine procedure;
  
  Expression[] arguments = Expression.emptyArray;
  
  ResultMetaData resultMetaData;
  
  StatementProcedure(Session paramSession, Expression paramExpression, ParserDQL.CompileContext paramCompileContext) {
    super(7, 2003, paramSession.getCurrentSchemaHsqlName());
    this.statementReturnType = 2;
    if (paramExpression.opType == 27) {
      FunctionSQLInvoked functionSQLInvoked = (FunctionSQLInvoked)paramExpression;
      if (functionSQLInvoked.routine.returnsTable) {
        this.procedure = functionSQLInvoked.routine;
        this.arguments = functionSQLInvoked.nodes;
      } else {
        this.expression = paramExpression;
      } 
    } else {
      this.expression = paramExpression;
    } 
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
    if (this.procedure != null)
      paramSession.getGrantee().checkAccess(this.procedure); 
  }
  
  StatementProcedure(Session paramSession, Routine paramRoutine, Expression[] paramArrayOfExpression, ParserDQL.CompileContext paramCompileContext) {
    super(7, 2003, paramSession.getCurrentSchemaHsqlName());
    if (paramRoutine.maxDynamicResults > 0)
      this.statementReturnType = 0; 
    this.procedure = paramRoutine;
    this.arguments = paramArrayOfExpression;
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
    paramSession.getGrantee().checkAccess(paramRoutine);
  }
  
  Result getResult(Session paramSession) {
    Result result = (this.expression == null) ? getProcedureResult(paramSession) : getExpressionResult(paramSession);
    result.setStatementType(this.statementReturnType);
    return result;
  }
  
  Result getProcedureResult(Session paramSession) {
    int i;
    Object[] arrayOfObject1 = ValuePool.emptyObjectArray;
    if (this.procedure.isPSM()) {
      i = this.arguments.length;
      if (this.procedure.getMaxDynamicResults() > 0)
        i++; 
    } else {
      i = (this.procedure.javaMethod.getParameterTypes()).length;
      if (this.procedure.javaMethodWithConnection)
        i--; 
    } 
    if (i > 0)
      arrayOfObject1 = new Object[i]; 
    for (byte b1 = 0; b1 < this.arguments.length; b1++) {
      Expression expression = this.arguments[b1];
      if (expression != null) {
        Type type = this.procedure.getParameter(b1).getDataType();
        Object object = expression.getValue(paramSession);
        arrayOfObject1[b1] = type.convertToType(paramSession, object, expression.getDataType());
      } 
    } 
    paramSession.sessionContext.push();
    paramSession.sessionContext.routineArguments = arrayOfObject1;
    paramSession.sessionContext.routineVariables = ValuePool.emptyObjectArray;
    Result result1 = Result.updateZeroResult;
    if (this.procedure.isPSM()) {
      result1 = executePSMProcedure(paramSession);
    } else {
      JDBCConnection jDBCConnection = paramSession.getInternalConnection();
      result1 = executeJavaProcedure(paramSession, (Connection)jDBCConnection);
    } 
    Object[] arrayOfObject2 = paramSession.sessionContext.routineArguments;
    paramSession.sessionContext.pop();
    if (!this.procedure.isPSM())
      paramSession.releaseInternalConnection(); 
    if (result1.isError())
      return result1; 
    for (byte b2 = 0; b2 < this.procedure.getParameterCount(); b2++) {
      ColumnSchema columnSchema = this.procedure.getParameter(b2);
      byte b = columnSchema.getParameterMode();
      if (b != 1)
        if (this.arguments[b2].isDynamicParam()) {
          int j = (this.arguments[b2]).parameterIndex;
          paramSession.sessionContext.dynamicArguments[j] = arrayOfObject2[b2];
        } else {
          int j = this.arguments[b2].getColumnIndex();
          paramSession.sessionContext.routineVariables[j] = arrayOfObject2[b2];
        }  
    } 
    Result result2 = result1;
    result1 = Result.newCallResponse(getParametersMetaData().getParameterTypes(), this.id, paramSession.sessionContext.dynamicArguments);
    if (this.procedure.returnsTable()) {
      result1.addChainedResult(result2);
    } else if (arrayOfObject2.length > this.arguments.length) {
      result2 = (Result)arrayOfObject2[this.arguments.length];
      result1.addChainedResult(result2);
    } 
    return result1;
  }
  
  Result executePSMProcedure(Session paramSession) {
    int i = this.procedure.getVariableCount();
    paramSession.sessionContext.routineVariables = new Object[i];
    Result result = this.procedure.statement.execute(paramSession);
    return result.isError() ? result : result;
  }
  
  Result executeJavaProcedure(Session paramSession, Connection paramConnection) {
    Result result = Result.updateZeroResult;
    Object[] arrayOfObject1 = paramSession.sessionContext.routineArguments;
    Object[] arrayOfObject2 = this.procedure.convertArgsToJava(paramSession, arrayOfObject1);
    if (this.procedure.javaMethodWithConnection)
      arrayOfObject2[0] = paramConnection; 
    result = this.procedure.invokeJavaMethod(paramSession, arrayOfObject2);
    this.procedure.convertArgsToSQL(paramSession, arrayOfObject1, arrayOfObject2);
    return result;
  }
  
  Result getExpressionResult(Session paramSession) {
    Object[] arrayOfObject;
    paramSession.sessionData.startRowProcessing();
    Object object = this.expression.getValue(paramSession);
    if (this.resultMetaData == null)
      getResultMetaData(); 
    Result result = Result.newSingleColumnResult(this.resultMetaData);
    if (this.expression.getDataType().isArrayType()) {
      arrayOfObject = new Object[1];
      arrayOfObject[0] = object;
    } else if (object instanceof Object[]) {
      arrayOfObject = (Object[])object;
    } else {
      arrayOfObject = new Object[1];
      arrayOfObject[0] = object;
    } 
    result.getNavigator().add(arrayOfObject);
    return result;
  }
  
  TableDerived[] getSubqueries(Session paramSession) {
    OrderedHashSet orderedHashSet = null;
    if (this.expression != null)
      orderedHashSet = this.expression.collectAllSubqueries(orderedHashSet); 
    for (byte b1 = 0; b1 < this.arguments.length; b1++)
      orderedHashSet = this.arguments[b1].collectAllSubqueries(orderedHashSet); 
    if (orderedHashSet == null || orderedHashSet.size() == 0)
      return TableDerived.emptyArray; 
    TableDerived[] arrayOfTableDerived = new TableDerived[orderedHashSet.size()];
    orderedHashSet.toArray((Object[])arrayOfTableDerived);
    for (byte b2 = 0; b2 < this.subqueries.length; b2++)
      arrayOfTableDerived[b2].prepareTable(); 
    return arrayOfTableDerived;
  }
  
  public ResultMetaData getResultMetaData() {
    ResultMetaData resultMetaData;
    ColumnBase columnBase;
    if (this.resultMetaData != null)
      return this.resultMetaData; 
    switch (this.type) {
      case 7:
        if (this.expression == null)
          return ResultMetaData.emptyResultMetaData; 
        resultMetaData = ResultMetaData.newResultMetaData(1);
        columnBase = new ColumnBase(null, null, null, "@p0");
        columnBase.setType(this.expression.getDataType());
        resultMetaData.columns[0] = columnBase;
        resultMetaData.prepareData();
        this.resultMetaData = resultMetaData;
        return resultMetaData;
    } 
    throw Error.runtimeError(201, "StatementProcedure");
  }
  
  public ResultMetaData getParametersMetaData() {
    return super.getParametersMetaData();
  }
  
  void collectTableNamesForRead(OrderedHashSet paramOrderedHashSet) {
    if (this.expression == null) {
      paramOrderedHashSet.addAll((Object[])this.procedure.getTableNamesForRead());
    } else {
      byte b;
      for (b = 0; b < this.subqueries.length; b++) {
        if ((this.subqueries[b]).queryExpression != null)
          (this.subqueries[b]).queryExpression.getBaseTableNames(paramOrderedHashSet); 
      } 
      for (b = 0; b < this.routines.length; b++)
        paramOrderedHashSet.addAll((Object[])this.routines[b].getTableNamesForRead()); 
    } 
  }
  
  void collectTableNamesForWrite(OrderedHashSet paramOrderedHashSet) {
    if (this.expression == null)
      paramOrderedHashSet.addAll((Object[])this.procedure.getTableNamesForWrite()); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementProcedure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */