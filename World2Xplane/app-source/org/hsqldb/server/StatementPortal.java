package org.hsqldb.server;

import java.sql.SQLException;
import java.util.Map;
import org.hsqldb.Session;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.Type;

class StatementPortal {
  public Object[] parameters;
  
  public Result bindResult;
  
  public Result ackResult;
  
  public String lcQuery;
  
  public String handle;
  
  private Map containingMap;
  
  private Session session;
  
  public StatementPortal(String paramString, OdbcPreparedStatement paramOdbcPreparedStatement, Map paramMap) throws RecoverableOdbcFailure {
    this(paramString, paramOdbcPreparedStatement, new Object[0], paramMap);
  }
  
  public StatementPortal(String paramString, OdbcPreparedStatement paramOdbcPreparedStatement, Object[] paramArrayOfObject, Map<String, StatementPortal> paramMap) throws RecoverableOdbcFailure {
    this.handle = paramString;
    this.lcQuery = paramOdbcPreparedStatement.query.toLowerCase();
    this.ackResult = paramOdbcPreparedStatement.ackResult;
    this.session = paramOdbcPreparedStatement.session;
    this.containingMap = paramMap;
    this.bindResult = Result.newPreparedExecuteRequest(paramOdbcPreparedStatement.ackResult.parameterMetaData.getParameterTypes(), paramOdbcPreparedStatement.ackResult.getStatementID());
    switch (this.bindResult.getType()) {
      case 35:
        break;
      case 2:
        throw new RecoverableOdbcFailure(this.bindResult);
      default:
        throw new RecoverableOdbcFailure("Output Result from seconary Statement prep is of unexpected type: " + this.bindResult.getType());
    } 
    if (paramArrayOfObject.length < 1) {
      this.parameters = new Object[0];
    } else {
      ResultMetaData resultMetaData = paramOdbcPreparedStatement.ackResult.parameterMetaData;
      if (resultMetaData == null)
        throw new RecoverableOdbcFailure("No metadata for Result ack"); 
      Type[] arrayOfType = resultMetaData.getParameterTypes();
      if (arrayOfType.length != paramArrayOfObject.length)
        throw new RecoverableOdbcFailure(null, "Client didn't specify all " + arrayOfType.length + " parameters (" + paramArrayOfObject.length + ')', "08P01"); 
      this.parameters = new Object[paramArrayOfObject.length];
      try {
        for (byte b = 0; b < this.parameters.length; b++)
          this.parameters[b] = (paramArrayOfObject[b] instanceof String) ? PgType.getPgType(arrayOfType[b], true).getParameter((String)paramArrayOfObject[b], this.session) : paramArrayOfObject[b]; 
      } catch (SQLException sQLException) {
        throw new RecoverableOdbcFailure("Typing failure: " + sQLException);
      } 
    } 
    paramMap.put(paramString, this);
  }
  
  public void close() {
    this.containingMap.remove(this.handle);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\StatementPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */