package org.hsqldb.server;

import org.hsqldb.result.Result;

class RecoverableOdbcFailure extends Exception {
  private String clientMessage = null;
  
  private String sqlStateCode = null;
  
  private Result errorResult = null;
  
  public String getSqlStateCode() {
    return this.sqlStateCode;
  }
  
  public Result getErrorResult() {
    return this.errorResult;
  }
  
  public RecoverableOdbcFailure(Result paramResult) {
    this.errorResult = paramResult;
  }
  
  public RecoverableOdbcFailure(String paramString) {
    super(paramString);
    this.clientMessage = paramString;
  }
  
  public RecoverableOdbcFailure(String paramString1, String paramString2) {
    this(paramString1);
    this.sqlStateCode = paramString2;
  }
  
  public RecoverableOdbcFailure(String paramString1, String paramString2, String paramString3) {
    super(paramString1);
    this.clientMessage = paramString2;
    this.sqlStateCode = paramString3;
  }
  
  public String getClientMessage() {
    return this.clientMessage;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\RecoverableOdbcFailure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */