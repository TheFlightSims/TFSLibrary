package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.result.Result;

public class HsqlException extends RuntimeException {
  public static final HsqlException[] emptyArray = new HsqlException[0];
  
  public static final HsqlException noDataCondition = Error.error(1100);
  
  private String message;
  
  private String state;
  
  private int code;
  
  private int level;
  
  private int statementGroup;
  
  private int statementCode;
  
  public Object info;
  
  public HsqlException(Throwable paramThrowable, String paramString1, String paramString2, int paramInt) {
    super(paramThrowable);
    this.message = paramString1;
    this.state = paramString2;
    this.code = paramInt;
  }
  
  public HsqlException(Result paramResult) {
    this.message = paramResult.getMainString();
    this.state = paramResult.getSubString();
    this.code = paramResult.getErrorCode();
  }
  
  public HsqlException(Throwable paramThrowable, String paramString, int paramInt) {
    super(paramThrowable);
    this.message = paramThrowable.toString();
    this.state = paramString;
    this.code = paramInt;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public void setMessage(String paramString) {
    this.message = paramString;
  }
  
  public String getSQLState() {
    return this.state;
  }
  
  public int getErrorCode() {
    return this.code;
  }
  
  public void setLevel(int paramInt) {
    this.level = paramInt;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public int getStatementCode() {
    return this.statementCode;
  }
  
  public void setStatementType(int paramInt1, int paramInt2) {
    this.statementGroup = paramInt1;
    this.statementCode = paramInt2;
  }
  
  public int hashCode() {
    return this.code;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject instanceof HsqlException) {
      HsqlException hsqlException = (HsqlException)paramObject;
      return (this.code == hsqlException.code && equals(this.state, hsqlException.state) && equals(this.message, hsqlException.message));
    } 
    return false;
  }
  
  private static boolean equals(Object paramObject1, Object paramObject2) {
    return (paramObject1 == paramObject2) ? true : ((paramObject1 == null || paramObject2 == null) ? false : paramObject1.equals(paramObject2));
  }
  
  public static class HsqlRuntimeMemoryError extends OutOfMemoryError {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\HsqlException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */