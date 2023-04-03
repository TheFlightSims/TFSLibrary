package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.navigator.RowSetNavigatorData;
import org.hsqldb.navigator.RowSetNavigatorDataTable;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.types.Type;

public class TableDerived extends Table {
  public static final TableDerived[] emptyArray = new TableDerived[0];
  
  QueryExpression queryExpression;
  
  Expression dataExpression;
  
  boolean uniqueRows;
  
  boolean uniquePredicate;
  
  String sql;
  
  View view;
  
  int depth;
  
  boolean canRecompile = false;
  
  public TableDerived(Database paramDatabase, HsqlNameManager.HsqlName paramHsqlName, int paramInt) {
    super(paramDatabase, paramHsqlName, paramInt);
    switch (paramInt) {
      case 2:
      case 8:
      case 9:
      case 11:
      case 12:
      case 13:
        return;
    } 
    throw Error.runtimeError(201, "Table");
  }
  
  public TableDerived(Database paramDatabase, HsqlNameManager.HsqlName paramHsqlName, int paramInt, Type[] paramArrayOfType, HashMappedList paramHashMappedList, int[] paramArrayOfint) {
    this(paramDatabase, paramHsqlName, paramInt);
    this.colTypes = paramArrayOfType;
    this.columnList = paramHashMappedList;
    this.columnCount = paramHashMappedList.size();
    createPrimaryKey((HsqlNameManager.HsqlName)null, paramArrayOfint, true);
  }
  
  public TableDerived(Database paramDatabase, HsqlNameManager.HsqlName paramHsqlName, int paramInt1, QueryExpression paramQueryExpression, Expression paramExpression, int paramInt2, int paramInt3) {
    super(paramDatabase, paramHsqlName, paramInt1);
    switch (paramInt1) {
      case 2:
      case 8:
        break;
      default:
        throw Error.runtimeError(201, "Table");
    } 
    this.queryExpression = paramQueryExpression;
    this.dataExpression = paramExpression;
    this.depth = paramInt3;
    switch (paramInt2) {
      case 55:
        paramQueryExpression.setSingleRow();
        break;
      case 54:
        if (paramQueryExpression != null)
          paramQueryExpression.setFullOrder(); 
        this.uniqueRows = true;
        break;
      case 57:
        paramQueryExpression.setFullOrder();
        this.uniquePredicate = true;
        break;
    } 
    if (paramExpression != null)
      paramExpression.table = this; 
  }
  
  public TableDerived newDerivedTable(Session paramSession) {
    TableDerived tableDerived = this;
    if (isRecompiled()) {
      ParserDQL parserDQL = new ParserDQL(paramSession, new Scanner(), paramSession.parser.compileContext);
      parserDQL.reset(this.sql);
      parserDQL.read();
      tableDerived = parserDQL.XreadSubqueryTableBody(this.tableName, 23);
      tableDerived.queryExpression.resolve(paramSession);
      tableDerived.columnList = this.columnList;
      tableDerived.columnCount = this.columnList.size();
      tableDerived.triggerList = this.triggerList;
      tableDerived.triggerLists = this.triggerLists;
      tableDerived.view = this.view;
      tableDerived.createPrimaryKey();
    } 
    return tableDerived;
  }
  
  public int getId() {
    return 0;
  }
  
  public boolean isQueryBased() {
    return true;
  }
  
  public boolean isWritable() {
    return true;
  }
  
  public boolean isInsertable() {
    return (this.view != null && this.view.isTriggerInsertable) ? false : ((this.queryExpression == null) ? false : this.queryExpression.isInsertable());
  }
  
  public boolean isUpdatable() {
    return (this.view != null && this.view.isTriggerUpdatable) ? false : ((this.queryExpression == null) ? false : this.queryExpression.isUpdatable());
  }
  
  public int[] getUpdatableColumns() {
    return (this.queryExpression != null) ? this.queryExpression.getBaseTableColumnMap() : this.defaultColumnMap;
  }
  
  public boolean isTriggerInsertable() {
    return (this.view != null) ? this.view.isTriggerInsertable : false;
  }
  
  public boolean isTriggerUpdatable() {
    return (this.view != null) ? this.view.isTriggerUpdatable : false;
  }
  
  public boolean isTriggerDeletable() {
    return (this.view != null) ? this.view.isTriggerDeletable : false;
  }
  
  public Table getBaseTable() {
    return (this.queryExpression == null) ? this : this.queryExpression.getBaseTable();
  }
  
  public int[] getBaseTableColumnMap() {
    return (this.queryExpression == null) ? null : this.queryExpression.getBaseTableColumnMap();
  }
  
  public QueryExpression getQueryExpression() {
    return this.queryExpression;
  }
  
  public Expression getDataExpression() {
    return this.dataExpression;
  }
  
  public void prepareTable() {
    if (this.columnCount > 0)
      return; 
    if (this.dataExpression != null && this.columnCount == 0) {
      TableUtil.addAutoColumns(this, this.dataExpression.nodeDataTypes);
      setTableIndexesForSubquery();
    } 
    if (this.queryExpression != null) {
      this.columnList = this.queryExpression.getColumns();
      this.columnCount = this.queryExpression.getColumnCount();
      setTableIndexesForSubquery();
    } 
  }
  
  public void prepareTable(HsqlNameManager.HsqlName[] paramArrayOfHsqlName) {
    prepareTable();
    if (paramArrayOfHsqlName != null) {
      if (paramArrayOfHsqlName.length != this.columnList.size())
        throw Error.error(5593); 
      for (byte b = 0; b < this.columnCount; b++) {
        this.columnList.setKey(b, (paramArrayOfHsqlName[b]).name);
        ColumnSchema columnSchema = (ColumnSchema)this.columnList.get(b);
        columnSchema.setName(paramArrayOfHsqlName[b]);
      } 
    } 
  }
  
  private void setTableIndexesForSubquery() {
    int[] arrayOfInt1 = null;
    if (this.uniqueRows || this.uniquePredicate) {
      arrayOfInt1 = new int[getColumnCount()];
      ArrayUtil.fillSequence(arrayOfInt1);
    } 
    int[] arrayOfInt2 = this.uniqueRows ? arrayOfInt1 : null;
    if (this.primaryKeyCols == null)
      createPrimaryKey((HsqlNameManager.HsqlName)null, arrayOfInt2, false); 
    if (this.uniqueRows) {
      this.fullIndex = getPrimaryIndex();
    } else if (this.uniquePredicate) {
      this.fullIndex = createIndexForColumns((Session)null, arrayOfInt1);
    } 
  }
  
  void setCorrelated() {
    if (this.dataExpression != null)
      this.dataExpression.isCorrelated = true; 
    if (this.queryExpression != null)
      this.queryExpression.isCorrelated = true; 
  }
  
  boolean isCorrelated() {
    return (this.dataExpression != null) ? this.dataExpression.isCorrelated : ((this.queryExpression != null) ? this.queryExpression.isCorrelated : false);
  }
  
  boolean hasUniqueNotNullRows(Session paramSession) {
    return getNavigator(paramSession).hasUniqueNotNullRows(paramSession);
  }
  
  void resetToView() {
    this.queryExpression = this.view.getQueryExpression();
  }
  
  public void materialise(Session paramSession) {
    paramSession.sessionContext.pushStatementState();
    try {
      if (this.dataExpression != null) {
        PersistentStore persistentStore1 = paramSession.sessionData.getSubqueryRowStore(this);
        this.dataExpression.insertValuesIntoSubqueryTable(paramSession, persistentStore1);
        return;
      } 
      if (this.queryExpression == null)
        return; 
      Result result = this.queryExpression.getResult(paramSession, 0);
      if (this.uniqueRows) {
        RowSetNavigatorData rowSetNavigatorData = (RowSetNavigatorData)result.getNavigator();
        rowSetNavigatorData.removeDuplicates(paramSession);
      } 
      PersistentStore persistentStore = paramSession.sessionData.getSubqueryRowStore(this);
      insertResult(paramSession, persistentStore, result);
      result.getNavigator().release();
    } finally {
      paramSession.sessionContext.popStatementState();
    } 
  }
  
  public void materialiseCorrelated(Session paramSession) {
    if (isCorrelated())
      materialise(paramSession); 
  }
  
  public boolean isRecompiled() {
    if (this.canRecompile && this.queryExpression instanceof QuerySpecification) {
      QuerySpecification querySpecification = (QuerySpecification)this.queryExpression;
      return !(querySpecification.isAggregated || querySpecification.isGrouped || querySpecification.isOrderSensitive);
    } 
    return false;
  }
  
  public Object[] getValues(Session paramSession) {
    RowIterator rowIterator = rowIterator(paramSession);
    if (rowIterator.hasNext()) {
      Row row = rowIterator.getNextRow();
      if (rowIterator.hasNext())
        throw Error.error(3201); 
      return row.getData();
    } 
    return new Object[getColumnCount()];
  }
  
  public Object getValue(Session paramSession) {
    Object[] arrayOfObject = getValues(paramSession);
    return arrayOfObject[0];
  }
  
  public RowSetNavigatorData getNavigator(Session paramSession) {
    return (RowSetNavigatorData)new RowSetNavigatorDataTable(paramSession, this);
  }
  
  public void setSQL(String paramString) {
    this.sql = paramString;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\TableDerived.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */