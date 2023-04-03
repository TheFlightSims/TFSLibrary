package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.map.ValuePool;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorData;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.types.RowType;
import org.hsqldb.types.Type;

public class ExpressionTable extends Expression {
  boolean isTable;
  
  boolean ordinality = false;
  
  ExpressionTable(Expression[] paramArrayOfExpression, boolean paramBoolean) {
    super(30);
    this.nodes = paramArrayOfExpression;
    this.ordinality = paramBoolean;
  }
  
  public String getSQL() {
    return this.isTable ? "TABLE" : "UNNEST";
  }
  
  protected String describe(Session paramSession, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer(64);
    stringBuffer.append('\n');
    for (byte b = 0; b < paramInt; b++)
      stringBuffer.append(' '); 
    if (this.isTable) {
      stringBuffer.append("TABLE").append(' ');
    } else {
      stringBuffer.append("UNNEST").append(' ');
    } 
    stringBuffer.append(this.nodes[0].describe(paramSession, paramInt));
    return stringBuffer.toString();
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {
    int i;
    for (i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null)
        this.nodes[i].resolveTypes(paramSession, this); 
    } 
    if (this.nodes.length == 1 && (this.nodes[0]).dataType.isRowType()) {
      if (this.ordinality)
        throw Error.error(5581, "ORDINALITY"); 
      this.nodeDataTypes = ((RowType)(this.nodes[0]).dataType).getTypesArray();
      this.table.prepareTable();
      this.table.columnList = (((FunctionSQLInvoked)this.nodes[0]).routine.getTable()).columnList;
      this.isTable = true;
      return;
    } 
    for (i = 0; i < this.nodes.length; i++) {
      if (!(this.nodes[i]).dataType.isArrayType())
        throw Error.error(5563, "UNNEST"); 
    } 
    i = this.ordinality ? (this.nodes.length + 1) : this.nodes.length;
    this.nodeDataTypes = new Type[i];
    for (byte b = 0; b < this.nodes.length; b++) {
      this.nodeDataTypes[b] = (this.nodes[b]).dataType.collectionBaseType();
      if (this.nodeDataTypes[b] == null || this.nodeDataTypes[b] == Type.SQL_ALL_TYPES)
        throw Error.error(5567, "UNNEST"); 
    } 
    if (this.ordinality)
      this.nodeDataTypes[this.nodes.length] = (Type)Type.SQL_INTEGER; 
    this.table.prepareTable();
  }
  
  public Result getResult(Session paramSession) {
    RowSetNavigatorData rowSetNavigatorData;
    Result result;
    switch (this.opType) {
      case 30:
        rowSetNavigatorData = this.table.getNavigator(paramSession);
        result = Result.newResult((RowSetNavigator)rowSetNavigatorData);
        result.metaData = this.table.queryExpression.getMetaData();
        return result;
    } 
    throw Error.runtimeError(201, "ExpressionTable");
  }
  
  public Object[] getRowValue(Session paramSession) {
    switch (this.opType) {
      case 30:
        return this.table.queryExpression.getValues(paramSession);
    } 
    throw Error.runtimeError(201, "Expression");
  }
  
  Object getValue(Session paramSession, Type paramType) {
    Object[] arrayOfObject;
    switch (this.opType) {
      case 30:
        materialise(paramSession);
        arrayOfObject = this.table.getValues(paramSession);
        return (arrayOfObject.length == 1) ? arrayOfObject[0] : arrayOfObject;
    } 
    throw Error.runtimeError(201, "Expression");
  }
  
  public Object getValue(Session paramSession) {
    return this.valueData;
  }
  
  void insertValuesIntoSubqueryTable(Session paramSession, PersistentStore paramPersistentStore) {
    if (this.isTable) {
      insertTableValues(paramSession, paramPersistentStore);
    } else {
      insertArrayValues(paramSession, paramPersistentStore);
    } 
  }
  
  private void insertTableValues(Session paramSession, PersistentStore paramPersistentStore) {
    Result result = this.nodes[0].getResult(paramSession);
    RowSetNavigator rowSetNavigator = result.navigator;
    int i = rowSetNavigator.getSize();
    while (rowSetNavigator.hasNext()) {
      Object[] arrayOfObject = rowSetNavigator.getNext();
      Row row = (Row)paramPersistentStore.getNewCachedObject(paramSession, arrayOfObject, false);
      try {
        paramPersistentStore.indexRow(paramSession, row);
      } catch (HsqlException hsqlException) {}
    } 
  }
  
  private void insertArrayValues(Session paramSession, PersistentStore paramPersistentStore) {
    Object[][] arrayOfObject = new Object[this.nodes.length][];
    byte b;
    for (b = 0; b < arrayOfObject.length; b++) {
      Object[] arrayOfObject1 = (Object[])this.nodes[b].getValue(paramSession);
      if (arrayOfObject1 == null)
        arrayOfObject1 = ValuePool.emptyObjectArray; 
      arrayOfObject[b] = arrayOfObject1;
    } 
    for (b = 0;; b++) {
      boolean bool = false;
      Object[] arrayOfObject1 = new Object[this.nodeDataTypes.length];
      for (byte b1 = 0; b1 < arrayOfObject.length; b1++) {
        if (b < (arrayOfObject[b1]).length) {
          arrayOfObject1[b1] = arrayOfObject[b1][b];
          bool = true;
        } 
      } 
      if (!bool)
        break; 
      if (this.ordinality)
        arrayOfObject1[this.nodes.length] = ValuePool.getInt(b + 1); 
      Row row = (Row)paramPersistentStore.getNewCachedObject(paramSession, arrayOfObject1, false);
      paramPersistentStore.indexRow(paramSession, row);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ExpressionTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */