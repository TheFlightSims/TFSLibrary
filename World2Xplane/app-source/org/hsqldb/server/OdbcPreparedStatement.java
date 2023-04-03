package org.hsqldb.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hsqldb.Session;
import org.hsqldb.result.Result;

class OdbcPreparedStatement {
  public String handle;
  
  public String query;
  
  public Result ackResult;
  
  public Session session;
  
  private Map containingMap;
  
  private List portals = new ArrayList();
  
  protected OdbcPreparedStatement(OdbcPreparedStatement paramOdbcPreparedStatement) {
    this.handle = paramOdbcPreparedStatement.handle;
    this.ackResult = paramOdbcPreparedStatement.ackResult;
  }
  
  public OdbcPreparedStatement(String paramString1, String paramString2, Map<String, OdbcPreparedStatement> paramMap, Session paramSession) throws RecoverableOdbcFailure {
    this.handle = paramString1;
    this.query = paramString2;
    this.containingMap = paramMap;
    this.session = paramSession;
    Result result = Result.newPrepareStatementRequest();
    result.setPrepareOrExecuteProperties(paramString2, 0, 0, 0, 0, 0, 2, null, null);
    this.ackResult = paramSession.execute(result);
    switch (this.ackResult.getType()) {
      case 4:
        break;
      case 2:
        throw new RecoverableOdbcFailure(this.ackResult);
      default:
        throw new RecoverableOdbcFailure("Output Result from Statement prep is of unexpected type: " + this.ackResult.getType());
    } 
    paramMap.put(paramString1, this);
  }
  
  public void close() {
    this.containingMap.remove(this.handle);
    while (this.portals.size() > 0)
      ((StatementPortal)this.portals.remove(1)).close(); 
  }
  
  public void addPortal(StatementPortal paramStatementPortal) {
    this.portals.add(paramStatementPortal);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\OdbcPreparedStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */