package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.result.Result;

public class StatementSimple extends Statement {
  String sqlState;
  
  Expression messageExpression;
  
  HsqlNameManager.HsqlName label;
  
  ColumnSchema[] variables;
  
  int[] variableIndexes;
  
  StatementSimple(int paramInt, HsqlNameManager.HsqlName paramHsqlName) {
    super(paramInt, 2007);
    this.label = paramHsqlName;
  }
  
  StatementSimple(int paramInt, String paramString, Expression paramExpression) {
    super(paramInt, 2007);
    this.sqlState = paramString;
    this.messageExpression = paramExpression;
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    switch (this.type) {
      case 92:
        stringBuffer.append("SIGNAL").append(' ');
        stringBuffer.append("SQLSTATE");
        stringBuffer.append(' ').append('\'').append(this.sqlState).append('\'');
        break;
      case 91:
        stringBuffer.append("RESIGNAL").append(' ');
        stringBuffer.append("SQLSTATE");
        stringBuffer.append(' ').append('\'').append(this.sqlState).append('\'');
        break;
      case 102:
        stringBuffer.append("ITERATE").append(' ').append(this.label);
        break;
      case 89:
        stringBuffer.append("LEAVE").append(' ').append(this.label);
        break;
    } 
    return stringBuffer.toString();
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
      result = getResult(paramSession);
    } catch (Throwable throwable) {
      result = Result.newErrorResult(throwable, null);
    } 
    if (result.isError())
      result.getException().setStatementType(this.group, this.type); 
    return result;
  }
  
  Result getResult(Session paramSession) {
    HsqlException hsqlException;
    switch (this.type) {
      case 91:
      case 92:
        hsqlException = Error.error(getMessage(paramSession), this.sqlState);
        return Result.newErrorResult(hsqlException);
      case 89:
      case 102:
        return Result.newPSMResult(this.type, this.label.name, null);
    } 
    throw Error.runtimeError(201, "");
  }
  
  String getMessage(Session paramSession) {
    return (this.messageExpression == null) ? null : (String)this.messageExpression.getValue(paramSession);
  }
  
  public void resolve(Session paramSession) {
    StatementCompound statementCompound;
    boolean bool = false;
    switch (this.type) {
      case 91:
      case 92:
        bool = true;
        break;
      case 102:
        for (statementCompound = this.parent; statementCompound != null; statementCompound = statementCompound.parent) {
          if (statementCompound.isLoop) {
            if (this.label == null) {
              bool = true;
              break;
            } 
            if (statementCompound.label != null && this.label.name.equals(statementCompound.label.name)) {
              bool = true;
              break;
            } 
          } 
        } 
        break;
      case 89:
        bool = true;
        break;
      default:
        throw Error.runtimeError(201, "");
    } 
    if (!bool)
      throw Error.error(5602); 
  }
  
  public String describe(Session paramSession) {
    return "";
  }
  
  public boolean isCatalogLock() {
    return false;
  }
  
  public boolean isCatalogChange() {
    return false;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementSimple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */