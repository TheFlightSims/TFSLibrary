package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.OrderedHashSet;

public class View extends TableDerived {
  private String statement;
  
  private HsqlNameManager.HsqlName[] columnNames;
  
  private OrderedHashSet schemaObjectNames;
  
  private int checkOption;
  
  private Table baseTable;
  
  boolean isTriggerInsertable;
  
  boolean isTriggerUpdatable;
  
  boolean isTriggerDeletable;
  
  View(Database paramDatabase, HsqlNameManager.HsqlName paramHsqlName, HsqlNameManager.HsqlName[] paramArrayOfHsqlName, int paramInt) {
    super(paramDatabase, paramHsqlName, 8);
    this.columnNames = paramArrayOfHsqlName;
    this.checkOption = paramInt;
  }
  
  public int getType() {
    return 4;
  }
  
  public OrderedHashSet getReferences() {
    return this.schemaObjectNames;
  }
  
  public OrderedHashSet getComponents() {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {
    ParserDQL parserDQL = new ParserDQL(paramSession, new Scanner(this.statement), null);
    parserDQL.read();
    TableDerived tableDerived = parserDQL.XreadViewSubqueryTable(this, true);
    this.queryExpression = tableDerived.queryExpression;
    if (getColumnCount() == 0) {
      if (this.columnNames == null)
        this.columnNames = tableDerived.queryExpression.getResultColumnNames(); 
      if (this.columnNames.length != tableDerived.queryExpression.getColumnCount())
        throw Error.error(5593, (getName()).statementName); 
      TableUtil.setColumnsInSchemaTable(this, this.columnNames, this.queryExpression.getColumnTypes());
    } 
    this.schemaObjectNames = parserDQL.compileContext.getSchemaObjectNames();
    this.canRecompile = true;
    this.baseTable = this.queryExpression.getBaseTable();
    if (this.baseTable == null)
      return; 
    switch (this.checkOption) {
      case 0:
      case 1:
      case 2:
        return;
    } 
    throw Error.runtimeError(201, "View");
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer(128);
    stringBuffer.append("CREATE").append(' ').append("VIEW");
    stringBuffer.append(' ');
    stringBuffer.append(getName().getSchemaQualifiedStatementName()).append(' ');
    stringBuffer.append('(');
    int i = getColumnCount();
    for (byte b = 0; b < i; b++) {
      stringBuffer.append((getColumn(b).getName()).statementName);
      if (b < i - 1)
        stringBuffer.append(','); 
    } 
    stringBuffer.append(')').append(' ').append("AS").append(' ');
    stringBuffer.append(getStatement());
    return stringBuffer.toString();
  }
  
  public int[] getUpdatableColumns() {
    return this.queryExpression.getBaseTableColumnMap();
  }
  
  public boolean isTriggerInsertable() {
    return this.isTriggerInsertable;
  }
  
  public boolean isTriggerUpdatable() {
    return this.isTriggerUpdatable;
  }
  
  public boolean isTriggerDeletable() {
    return this.isTriggerDeletable;
  }
  
  public boolean isInsertable() {
    return this.isTriggerInsertable ? false : super.isInsertable();
  }
  
  public boolean isUpdatable() {
    return this.isTriggerUpdatable ? false : super.isUpdatable();
  }
  
  void addTrigger(TriggerDef paramTriggerDef, HsqlNameManager.HsqlName paramHsqlName) {
    switch (paramTriggerDef.operationType) {
      case 50:
        if (this.isTriggerInsertable)
          throw Error.error(5538); 
        this.isTriggerInsertable = true;
        break;
      case 19:
        if (this.isTriggerDeletable)
          throw Error.error(5538); 
        this.isTriggerDeletable = true;
        break;
      case 82:
        if (this.isTriggerUpdatable)
          throw Error.error(5538); 
        this.isTriggerUpdatable = true;
        break;
      default:
        throw Error.runtimeError(201, "View");
    } 
    super.addTrigger(paramTriggerDef, paramHsqlName);
  }
  
  void removeTrigger(TriggerDef paramTriggerDef) {
    switch (paramTriggerDef.operationType) {
      case 50:
        this.isTriggerInsertable = false;
        break;
      case 19:
        this.isTriggerDeletable = false;
        break;
      case 82:
        this.isTriggerUpdatable = false;
        break;
      default:
        throw Error.runtimeError(201, "View");
    } 
    super.removeTrigger(paramTriggerDef);
  }
  
  public void setDataReadOnly(boolean paramBoolean) {
    throw Error.error(4000);
  }
  
  public int getCheckOption() {
    return this.checkOption;
  }
  
  public String getStatement() {
    return this.statement;
  }
  
  public void setStatement(String paramString) {
    this.statement = paramString;
  }
  
  public TableDerived newDerivedTable(Session paramSession) {
    ParserDQL parserDQL = new ParserDQL(paramSession, new Scanner(), paramSession.parser.compileContext);
    parserDQL.reset(this.statement);
    parserDQL.read();
    return parserDQL.XreadViewSubqueryTable(this, false);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\View.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */