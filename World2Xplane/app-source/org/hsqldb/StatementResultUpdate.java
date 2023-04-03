package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorData;
import org.hsqldb.navigator.RowSetNavigatorDataChange;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.types.Type;

public class StatementResultUpdate extends StatementDML {
  int actionType;
  
  Type[] types;
  
  Result result;
  
  StatementResultUpdate() {
    setCompileTimestamp(Long.MAX_VALUE);
  }
  
  public String describe(Session paramSession) {
    return "";
  }
  
  public Result execute(Session paramSession) {
    Result result;
    try {
      result = getResult(paramSession);
      clearStructures(paramSession);
    } catch (Throwable throwable) {
      clearStructures(paramSession);
      result = Result.newErrorResult(throwable, null);
    } 
    return result;
  }
  
  Result getResult(Session paramSession) {
    Row row;
    RowSetNavigatorDataChange rowSetNavigatorDataChange;
    Object[] arrayOfObject2;
    Object[] arrayOfObject3;
    byte b1;
    boolean[] arrayOfBoolean;
    byte b2;
    int[] arrayOfInt;
    checkAccessRights(paramSession);
    Object[] arrayOfObject1 = paramSession.sessionContext.dynamicArguments;
    PersistentStore persistentStore = this.baseTable.getRowStore(paramSession);
    switch (this.actionType) {
      case 81:
        row = getRow(paramSession, arrayOfObject1);
        if (row == null || row.isDeleted(paramSession, persistentStore))
          throw Error.error(3621); 
        rowSetNavigatorDataChange = paramSession.sessionContext.getRowSetDataChange();
        arrayOfObject3 = (Object[])ArrayUtil.duplicateArray(row.getData());
        arrayOfBoolean = this.baseTable.getNewColumnCheckList();
        for (b2 = 0; b2 < this.baseColumnMap.length; b2++) {
          if (this.types[b2] != Type.SQL_ALL_TYPES) {
            arrayOfObject3[this.baseColumnMap[b2]] = arrayOfObject1[b2];
            arrayOfBoolean[this.baseColumnMap[b2]] = true;
          } 
        } 
        arrayOfInt = ArrayUtil.booleanArrayToIntIndexes(arrayOfBoolean);
        rowSetNavigatorDataChange.addRow(paramSession, row, arrayOfObject3, this.baseTable.getColumnTypes(), arrayOfInt);
        rowSetNavigatorDataChange.endMainDataSet();
        update(paramSession, this.baseTable, rowSetNavigatorDataChange, (RowSetNavigator)null);
        break;
      case 18:
        row = getRow(paramSession, arrayOfObject1);
        if (row == null || row.isDeleted(paramSession, persistentStore))
          throw Error.error(3621); 
        rowSetNavigatorDataChange = paramSession.sessionContext.getRowSetDataChange();
        rowSetNavigatorDataChange.addRow(row);
        rowSetNavigatorDataChange.endMainDataSet();
        delete(paramSession, this.baseTable, rowSetNavigatorDataChange);
        break;
      case 50:
        arrayOfObject2 = this.baseTable.getNewRowData(paramSession);
        for (b1 = 0; b1 < arrayOfObject2.length; b1++)
          arrayOfObject2[this.baseColumnMap[b1]] = arrayOfObject1[b1]; 
        return insertSingleRow(paramSession, persistentStore, arrayOfObject2);
    } 
    return Result.updateOneResult;
  }
  
  Row getRow(Session paramSession, Object[] paramArrayOfObject) {
    int i = this.result.metaData.getColumnCount();
    Long long_ = (Long)paramArrayOfObject[i];
    PersistentStore persistentStore = this.baseTable.getRowStore(paramSession);
    Row row = null;
    if (i + 2 == this.result.metaData.getExtendedColumnCount()) {
      Object[] arrayOfObject = ((RowSetNavigatorData)this.result.getNavigator()).getData(long_.longValue());
      if (arrayOfObject != null)
        row = (Row)arrayOfObject[i + 1]; 
    } else {
      int j = (int)long_.longValue();
      row = (Row)persistentStore.get(j, false);
    } 
    this.result = null;
    return row;
  }
  
  void setRowActionProperties(Result paramResult, int paramInt, StatementQuery paramStatementQuery, Type[] paramArrayOfType) {
    QueryExpression queryExpression = paramStatementQuery.queryExpression;
    this.result = paramResult;
    this.actionType = paramInt;
    this.baseTable = queryExpression.getBaseTable();
    this.types = paramArrayOfType;
    this.baseColumnMap = queryExpression.getBaseTableColumnMap();
    this.writeTableNames[0] = this.baseTable.getName();
    this.sql = paramStatementQuery.getSQL();
    this.parameterMetaData = queryExpression.getMetaData();
  }
  
  void checkAccessRights(Session paramSession) {
    switch (this.type) {
      case 50:
        paramSession.getGrantee().checkInsert(this.targetTable, this.insertCheckColumns);
        break;
      case 19:
        paramSession.getGrantee().checkDelete(this.targetTable);
        break;
      case 82:
        paramSession.getGrantee().checkUpdate(this.targetTable, this.updateCheckColumns);
        break;
      case 128:
        paramSession.getGrantee().checkInsert(this.targetTable, this.insertCheckColumns);
        paramSession.getGrantee().checkUpdate(this.targetTable, this.updateCheckColumns);
        break;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementResultUpdate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */