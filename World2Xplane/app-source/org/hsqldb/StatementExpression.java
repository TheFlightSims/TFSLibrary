package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorData;
import org.hsqldb.result.Result;

public class StatementExpression extends StatementDMQL {
  Expression expression;
  
  StatementExpression(Session paramSession, ParserDQL.CompileContext paramCompileContext, int paramInt, Expression paramExpression) {
    super(paramInt, 2007, (HsqlNameManager.HsqlName)null);
    switch (paramInt) {
      case 58:
      case 1211:
        break;
      default:
        throw Error.runtimeError(201, "");
    } 
    this.isTransactionStatement = false;
    this.expression = paramExpression;
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    switch (this.type) {
      case 58:
        return this.sql;
      case 1211:
        stringBuffer.append(this.expression.getSQL());
        break;
    } 
    return stringBuffer.toString();
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
  
  Result getResult(Session paramSession) {
    Result result;
    switch (this.type) {
      case 58:
      case 1211:
        result = this.expression.getResult(paramSession);
        if (result.isData()) {
          RowSetNavigatorData rowSetNavigatorData = new RowSetNavigatorData(paramSession, result.getNavigator());
          result.setNavigator((RowSetNavigator)rowSetNavigatorData);
        } 
        return result;
    } 
    throw Error.runtimeError(201, "");
  }
  
  public void resolve(Session paramSession) {}
  
  String describeImpl(Session paramSession) throws Exception {
    return getSQL();
  }
  
  void collectTableNamesForRead(OrderedHashSet paramOrderedHashSet) {
    byte b;
    for (b = 0; b < this.subqueries.length; b++) {
      if ((this.subqueries[b]).queryExpression != null)
        (this.subqueries[b]).queryExpression.getBaseTableNames(paramOrderedHashSet); 
    } 
    for (b = 0; b < this.routines.length; b++)
      paramOrderedHashSet.addAll((Object[])this.routines[b].getTableNamesForRead()); 
  }
  
  void collectTableNamesForWrite(OrderedHashSet paramOrderedHashSet) {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */