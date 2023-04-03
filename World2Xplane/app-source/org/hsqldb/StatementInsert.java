package org.hsqldb;

import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.types.Type;

public class StatementInsert extends StatementDML {
  int overrideUserValue = -1;
  
  StatementInsert(Session paramSession, Table paramTable, int[] paramArrayOfint, Expression paramExpression, boolean[] paramArrayOfboolean, ParserDQL.CompileContext paramCompileContext) {
    super(50, 2004, paramSession.getCurrentSchemaHsqlName());
    this.targetTable = paramTable;
    this.baseTable = paramTable.isTriggerInsertable() ? paramTable : paramTable.getBaseTable();
    this.insertColumnMap = paramArrayOfint;
    this.insertCheckColumns = paramArrayOfboolean;
    this.insertExpression = paramExpression;
    setupChecks();
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
    this.isSimpleInsert = (paramExpression != null && paramExpression.nodes.length == 1 && this.updatableTableCheck == null);
  }
  
  StatementInsert(Session paramSession, Table paramTable, int[] paramArrayOfint, boolean[] paramArrayOfboolean, QueryExpression paramQueryExpression, ParserDQL.CompileContext paramCompileContext, int paramInt) {
    super(50, 2004, paramSession.getCurrentSchemaHsqlName());
    this.targetTable = paramTable;
    this.baseTable = paramTable.isTriggerInsertable() ? paramTable : paramTable.getBaseTable();
    this.insertColumnMap = paramArrayOfint;
    this.insertCheckColumns = paramArrayOfboolean;
    this.queryExpression = paramQueryExpression;
    this.overrideUserValue = paramInt;
    setupChecks();
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
  }
  
  Result getResult(Session paramSession) {
    Result result = null;
    RowSetNavigator rowSetNavigator1 = null;
    PersistentStore persistentStore = this.baseTable.getRowStore(paramSession);
    if (this.generatedIndexes != null) {
      result = Result.newUpdateCountResult(this.generatedResultMetaData, 0);
      rowSetNavigator1 = result.getChainedResult().getNavigator();
    } 
    if (this.isSimpleInsert) {
      Type[] arrayOfType = this.baseTable.getColumnTypes();
      Object[] arrayOfObject = getInsertData(paramSession, arrayOfType, (this.insertExpression.nodes[0]).nodes);
      return insertSingleRow(paramSession, persistentStore, arrayOfObject);
    } 
    RowSetNavigator rowSetNavigator2 = (this.queryExpression == null) ? getInsertValuesNavigator(paramSession) : getInsertSelectNavigator(paramSession);
    int i = rowSetNavigator2.getSize();
    if (i > 0)
      insertRowSet(paramSession, rowSetNavigator1, rowSetNavigator2); 
    if ((this.baseTable.triggerLists[0]).length > 0)
      this.baseTable.fireTriggers(paramSession, 0, rowSetNavigator2); 
    if (result == null) {
      result = new Result(1, i);
    } else {
      result.setUpdateCount(i);
    } 
    if (i == 0)
      paramSession.addWarning(HsqlException.noDataCondition); 
    return result;
  }
  
  RowSetNavigator getInsertSelectNavigator(Session paramSession) {
    Type[] arrayOfType1 = this.baseTable.getColumnTypes();
    int[] arrayOfInt = this.insertColumnMap;
    Result result = this.queryExpression.getResult(paramSession, 0);
    RowSetNavigator rowSetNavigator = result.initialiseNavigator();
    Type[] arrayOfType2 = result.metaData.columnTypes;
    RowSetNavigatorClient rowSetNavigatorClient = new RowSetNavigatorClient(2);
    while (rowSetNavigator.hasNext()) {
      Object[] arrayOfObject1 = this.baseTable.getNewRowData(paramSession);
      Object[] arrayOfObject2 = rowSetNavigator.getNext();
      for (byte b = 0; b < arrayOfInt.length; b++) {
        int i = arrayOfInt[b];
        if (i != this.overrideUserValue) {
          Type type = arrayOfType2[b];
          arrayOfObject1[i] = arrayOfType1[i].convertToType(paramSession, arrayOfObject2[b], type);
        } 
      } 
      rowSetNavigatorClient.add(arrayOfObject1);
    } 
    return (RowSetNavigator)rowSetNavigatorClient;
  }
  
  RowSetNavigator getInsertValuesNavigator(Session paramSession) {
    Type[] arrayOfType = this.baseTable.getColumnTypes();
    Expression[] arrayOfExpression = this.insertExpression.nodes;
    RowSetNavigatorClient rowSetNavigatorClient = new RowSetNavigatorClient(arrayOfExpression.length);
    for (byte b = 0; b < arrayOfExpression.length; b++) {
      Expression[] arrayOfExpression1 = (arrayOfExpression[b]).nodes;
      Object[] arrayOfObject = getInsertData(paramSession, arrayOfType, arrayOfExpression1);
      rowSetNavigatorClient.add(arrayOfObject);
    } 
    return (RowSetNavigator)rowSetNavigatorClient;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementInsert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */