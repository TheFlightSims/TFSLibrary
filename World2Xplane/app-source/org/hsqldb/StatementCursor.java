package org.hsqldb;

import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.result.Result;

public class StatementCursor extends StatementQuery {
  public static final StatementCursor[] emptyArray = new StatementCursor[0];
  
  StatementCursor(Session paramSession, QueryExpression paramQueryExpression, ParserDQL.CompileContext paramCompileContext) {
    super(paramSession, paramQueryExpression, paramCompileContext);
  }
  
  Result getResult(Session paramSession) {
    Object[] arrayOfObject = paramSession.sessionContext.routineArguments;
    Result result1 = (Result)arrayOfObject[arrayOfObject.length - 1];
    Result result2 = result1;
    while (result1 != null) {
      if ((getCursorName()).name.equals(result1.getMainString())) {
        result1.navigator.release();
        if (result2 == result1)
          result2 = result1.getChainedResult(); 
      } 
      if (result1.getChainedResult() == null)
        break; 
      result1 = result1.getChainedResult();
    } 
    arrayOfObject[arrayOfObject.length - 1] = result2;
    Result result3 = this.queryExpression.getResult(paramSession, 0);
    result3.setStatement(this);
    if (result3.isError())
      return result3; 
    result3.setMainString((getCursorName()).name);
    if (result2 == null) {
      arrayOfObject[arrayOfObject.length - 1] = result3;
    } else {
      ((Result)arrayOfObject[arrayOfObject.length - 1]).addChainedResult(result3);
    } 
    return Result.updateZeroResult;
  }
  
  void collectTableNamesForWrite(OrderedHashSet paramOrderedHashSet) {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementCursor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */