package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;

public class StatementQuery extends StatementDMQL {
  StatementQuery(Session paramSession, QueryExpression paramQueryExpression, ParserDQL.CompileContext paramCompileContext) {
    super(85, 2003, paramSession.getCurrentSchemaHsqlName());
    this.queryExpression = paramQueryExpression;
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
  }
  
  Result getResult(Session paramSession) {
    Result result = this.queryExpression.getResult(paramSession, paramSession.getMaxRows());
    result.setStatement(this);
    return result;
  }
  
  public ResultMetaData getResultMetaData() {
    switch (this.type) {
      case 85:
        return this.queryExpression.getMetaData();
      case 65:
        return this.queryExpression.getMetaData();
    } 
    throw Error.runtimeError(201, "StatementQuery.getResultMetaData()");
  }
  
  void collectTableNamesForRead(OrderedHashSet paramOrderedHashSet) {
    this.queryExpression.getBaseTableNames(paramOrderedHashSet);
    byte b;
    for (b = 0; b < this.subqueries.length; b++) {
      if ((this.subqueries[b]).queryExpression != null)
        (this.subqueries[b]).queryExpression.getBaseTableNames(paramOrderedHashSet); 
    } 
    for (b = 0; b < this.routines.length; b++)
      paramOrderedHashSet.addAll((Object[])this.routines[b].getTableNamesForRead()); 
  }
  
  void collectTableNamesForWrite(OrderedHashSet paramOrderedHashSet) {
    if (this.queryExpression.isUpdatable)
      this.queryExpression.getBaseTableNames(paramOrderedHashSet); 
  }
  
  public int getResultProperties() {
    return this.queryExpression.isUpdatable ? 8 : 0;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */